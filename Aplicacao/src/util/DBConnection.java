package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import view.Main;

public class DBConnection {
    private static ThreadLocal<Connection> con = new ThreadLocalConnection();
    private static boolean initialized = true;

    public static Connection getConnection() {
        if (!initialized) {
            throw new IllegalStateException("DatabaseConnection not initialized");
        }
        return con.get();
    }

    public static void release() throws SQLException {
        con.get().close();
        con.remove();
    }

    private static class ThreadLocalConnection extends ThreadLocal<Connection> {
        static {
            try {
                Class.forName("org.postgresql.Driver"); // touch the postgre driver
            } catch (ClassNotFoundException e) {
                System.out.println("Could not locate the JDBC postgre driver.");
            }
        }

        @Override
        protected Connection initialValue() {
            return getConnection();
        }

        private Connection getConnection() {
            DriverManager.setLoginTimeout(15); // Throw an exception after waiting 15 seconds for a connection.
            try {
                return DriverManager.getConnection(Constants.DB_URL, Constants.DB_USER, Constants.DB_PASSWORD);
            } catch (SQLException sql) {
                System.out.println("Could not create a SQL Connection object. Please make sure you've correctly configured db.properties.");
                return null;
            }
        }

        @Override
        public Connection get() {
            Connection con = super.get();
            try {
                if (!con.isClosed()) {
                    return con;
                }
            } catch (SQLException sql) {
                // Munch munch, we'll get a new connection. :)
            }
            con = getConnection();
            super.set(con);
            return con;
        }
    }
}