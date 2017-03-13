package com.stockexchange.restapi;

import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.jcs.JCS;
import org.apache.commons.jcs.access.CacheAccess;
import org.apache.commons.jcs.access.exception.CacheException;

import com.stockexchange.entities.TickerBean;

public class CacheDataHandler {

	  private  static CacheAccess<String,TickerBean> cache ;
	  private static CacheDataHandler instance;
	    private static int checkedOut = 0;
	    
	    
	public CacheDataHandler() {
		try{
			Properties prop = new Properties();
            InputStream in = getClass().getResourceAsStream("JCS.properties");
            prop.load(in);
            JCS.setConfigProperties(prop);
			cache = JCS.getInstance("tickerData");
		
			
		} catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public static CacheDataHandler getInstance()
    {
        synchronized (CacheDataHandler.class)
        {
            if (instance == null)
            {
                instance = new CacheDataHandler();
            }
        }

        synchronized (instance)
        {
            instance.checkedOut++;
        }

        return instance;
    }
	
	public void addTicker( TickerBean lTicker)
    {
        try
        {
            cache.put( lTicker.getTicker(), lTicker );
        }
        catch( CacheException e )
        {
            e.printStackTrace();
        }
    }

	public TickerBean getTicker( String ticker )
    {
      if(checkedOut<=250){						// if count increases above 250 for a ticker
    	  TickerBean lreturn = cache.get(ticker);
    	  return  lreturn;
      } else {
    	  cache.remove(ticker);
    	  checkedOut--;
    	  return null;
      }
    }
	
	
	public void removeTicker( String id )
    {
        try
        {
            cache.remove(id);
        }
        catch( CacheException e )
        {
            e.printStackTrace();
        }
    }
	
	
}
