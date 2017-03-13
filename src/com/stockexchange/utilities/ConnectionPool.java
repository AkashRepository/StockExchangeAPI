package com.stockexchange.utilities;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Vector;

/**
 *
 * @author Akash
 */
public class ConnectionPool {

    static final int MAX_CONNECTIONS = 20;
    static Vector connections = null;
    static ConnectionPool instance = null;

    public synchronized void removeAllConnections() {
        try {
            if (connections == null) {
                return;
            }
            int sz = connections.size();
            for (int i = 0; i < sz; i++) {
                Connection c = (Connection) connections.elementAt(i);
                c.close();
            }
            connections.removeAllElements();
            connections = null;
        } catch (SQLException sqlE) {
            System.out.println(sqlE);
        }
    }

    public static synchronized ConnectionPool getInstance() {
        if (instance == null) {
            instance = new ConnectionPool();
        }
        return instance;
    }

    public synchronized void initialize() {
        if (connections == null) {
            try {
                System.out.println("1");
                Properties prop = new Properties();
                InputStream in = getClass().getResourceAsStream("StockExchange.properties");
                prop.load(in);
                String userName = prop.getProperty("UserName");
                String password = prop.getProperty("Password");
                String url = prop.getProperty("URL");
                String driver = prop.getProperty("DRIVER");
                System.out.println(url);
                System.out.println(driver);
                Class.forName(driver).newInstance();
                connections = new Vector();
                int count = 0;
                while (count < MAX_CONNECTIONS) {
                    Connection c = DriverManager.getConnection(url, userName, password);
                    connections.addElement(c);
                    count++;
                }

            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("Cannot connect to database server");
            }

        }
    }

    public synchronized Connection getConnection() {
        Connection c = null;
        if (connections == null) {
            return null;
        }
        if (connections.size() > 0) {
            c = (Connection) connections.elementAt(0);
            connections.removeElementAt(0);
        }
        return c;
    }

    public synchronized void putConnection(Connection c) {
        connections.addElement(c);
        notifyAll();
    }
}

