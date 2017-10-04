/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daoimp;

import dao.Juego;
import dao.JuegoDao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author wildc
 */
public class JuegoDAOImp implements JuegoDao{
    
    private static String dbURL ="jdbc:derby://localhost:1527/arena;";
    
    private static Connection conn = null;
    private static Statement stmt = null;
    private static String tableName = "juego";
    
    public void setConn(Connection conn) {
        JuegoDAOImp.conn = conn;
    }
    
    @Override
    public void creaJuego(Juego nj) {
        try{
            stmt = conn.createStatement();
            stmt.execute("insert into juego (nombre) values('"+nj.getNJuego()+"')");
            stmt.close();
        } catch(SQLException sqlExcept) {
            sqlExcept.printStackTrace();
        }
    }

    @Override
    public boolean buscanomjue(Juego nj) {
        Boolean ban = false;
        try{
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from "+tableName+"where nombre='"+nj.getNJuego()+"'");
            String nameJ; //almacena el correo
            nameJ = rs.getString(4); //ingresa a la columna de correo y asigna a mail
            if(nameJ != null) //compara que existe
                ban = true;
            stmt.close();
        }catch (SQLException sqlExcept){
            sqlExcept.printStackTrace();
        }
        return ban;
    }

    @Override
    public void eliminajuego(Juego nj) {
        
    }
}
