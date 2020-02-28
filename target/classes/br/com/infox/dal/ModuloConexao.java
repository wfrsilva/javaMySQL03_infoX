package br.com.infox.dal;

import java.sql.*;

public class ModuloConexao {
    /*conexao banco*/
    public static Connection conector(){
        java.sql.Connection conexao = null;
        /*Chamar o driver de conexão*/
        String driver = "com.mysql.jdbc.Driver";
        /*informacoes Banco*/
        String url = "jdbc:mysql://localhost:3306/dbinfox";
        String user = "root";
        String password = "";
        /*estabelecendo conexao com banco*/
        
        try {
            Class.forName(driver);
            conexao = DriverManager.getConnection(url, user, password);
            return conexao;
                        
        } //try
        catch (Exception e) {
            System.out.println(e);
            return null;            
        } //catch
        
    }//conector
    
    
}//class
