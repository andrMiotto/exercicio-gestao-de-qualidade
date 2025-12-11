package org.example.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private static String URL = "jdbc:mysql://localhost:3306/diaDaProva?useSSL=false&serverTimezone=UTC";
    private static String USER = "root";
    private static String SENHA = "mysqlPW";


    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USER, SENHA);
    }
}
