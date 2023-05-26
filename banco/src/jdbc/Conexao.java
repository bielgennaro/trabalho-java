package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    public static void main(String[] args) {
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
    }

    public Connection conectar() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306", "root", "admin");
            System.out.println("Conectou no banco de dados.");
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } catch (ClassNotFoundException ex) {
            System.out.println("Erro: Não encontrou o driver do BD.");
        }

        return conn;
    }
}