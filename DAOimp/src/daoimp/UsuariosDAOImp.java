/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daoimp;

import dao.Usuario;
import dao.UsuariosDao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author davidhernandez
 */
public class UsuariosDAOImp implements UsuariosDao{
    
    private static String dbURL ="jdbc:derby://localhost:1527/arena;";
    
    private static Connection conn = null;
    private static Statement stmt = null;
    private static String tableName = "usuario";
    private static String Nombre="David";
    private static String App="Hernandez";
    private static String Apm="Herrera";
    private static String Usr="David";
    
  
    private static void createConnection(){   
        try{
            Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
            conn = DriverManager.getConnection(dbURL); 
        }catch (Exception except){
            except.printStackTrace();
        }
    }

    public static void setConn(Connection conn) {
        UsuariosDAOImp.conn = conn;
    }

    @Override
    public void crearUser(Usuario u) {
        try{
            stmt = conn.createStatement();
            String bloqueado="false";
            stmt.execute("insert into " + tableName + " values (" + "','"+
                    u.getUsuario() + ",'" + u.getPass() + "','" + u.getCorreo() + "','" + u.getNombre() + "','"+u.getApp()+ "','"+u.getApm()+ "','"+u.getEdad()+ "','" +u.getFace()+ "','"+bloqueado+"')");
            stmt.close();
        }
        catch (SQLException sqlExcept){
            sqlExcept.printStackTrace();
        }
    }

    @Override
    public Usuario editaUser(String u) {
        try{
            stmt = conn.createStatement();
            stmt.execute("");
            stmt.close();
        }
        catch (SQLException sqlExcept){
            sqlExcept.printStackTrace();
        }
        return null;
    }

    @Override
    public void eliminaUser(String u) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean login(String user, String Pass) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean bloqueado(String user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean buscauser(String user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean buscacorreo(String correo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
