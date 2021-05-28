package fr.eni.tp_papeterie.dal.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcTools {
    private final static String url = Settings.getPropriete("url");

    public static Connection recupConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(url);
        return connection;
    }

}
