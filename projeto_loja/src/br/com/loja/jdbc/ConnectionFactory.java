package br.com.loja.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author maico
 */
public class ConnectionFactory {
    
    public Connection getConnection(){
        try {
            return DriverManager.getConnection("jdbc:mysql://127.0.0.1/projeto_loja","root","12345678");
        } catch (Exception erro) {
            throw new RuntimeException(erro);
        }
    }
}
