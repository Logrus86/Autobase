package com.epam.bp.autobase.connectionPool;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.concurrent.ArrayBlockingQueue;

public class ConnectionPool {
    private static final ResourceBundle rb = ResourceBundle.getBundle("db"); //property manager
    private String driver = rb.getString("db.driver");
    private String url = rb.getString("db.url");
    private String user = rb.getString("db.user");
    private String password = rb.getString("db.password");
    private int poolSize = Integer.parseInt(rb.getString("db.pool_size"));
    private ArrayBlockingQueue<ProxyConnection> connectionQueue;

    public ConnectionPool() throws SQLException, ClassNotFoundException {
        connectionQueue = new ArrayBlockingQueue<>(poolSize);
        Class.forName(driver);
        for (int i = 0; i < poolSize; i++) {
            ProxyConnection connection = new ProxyConnection(DriverManager.getConnection(url, user, password));
            connectionQueue.offer(connection);
        }
    }

    public ProxyConnection getConnection() throws InterruptedException {
        ProxyConnection connection = null;
        connection = connectionQueue.take();
        return connection;
    }

    public void closeConnection(ProxyConnection connection) {
        connectionQueue.offer(connection);
    }
}
