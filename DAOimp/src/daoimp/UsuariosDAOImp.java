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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author davidhernandez
 */
public class UsuariosDAOImp implements UsuariosDao{
    
    private static String dbURL ="jdbc:derby://localhost:1527/arena;";
    
    private static Connection conn = null;
    private static Statement stmt = null;
    private static String tableName = "usuario";

    
  
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
    public boolean editaUser(Usuario u) {
        Boolean ban=false;
        try{
            stmt = conn.createStatement();            
            ban = stmt.execute("update "+tableName+"set pass="+u.getPass()+",nom="+u.getNombre()+",app="+u.getApp()+",apm="+u.getApm()+",edad="+u.getEdad()+",face="+u.getFace()+"where usr="+u.getUsuario());
            stmt.close();
        }
        catch (SQLException sqlExcept){
            sqlExcept.printStackTrace();
        }
        return ban;
    }
    
     @Override
    public String[] consultaUser(String us){
        String[] arr = null;
        try{
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from "+tableName+"where usr="+us);
            while(rs.next()){
                arr[0] = rs.getString(1);
                arr[1] = rs.getString(2);
                arr[2] = rs.getString(3);
                arr[3] = rs.getString(4);
                arr[4] = rs.getString(5);
                arr[5] = rs.getString(6);
                arr[6] = rs.getString(7);
                arr[7] = rs.getString(8);
                arr[8] = rs.getString(9);
                arr[9] = rs.getString(10);
            }
            stmt.close();
        }
        catch (SQLException sqlExcept){
            sqlExcept.printStackTrace();
        }
        return arr;
    }

    @Override
    public void eliminaUser(Usuario u) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean buscauser(Usuario u) {
        Boolean ban = false; 
        try{
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from "+tableName+"where usr="+u.getUsuario());
            String us;  //Almacena el valor del usuario
            us = rs.getString(2); //ingresa a la columna de usuario
            if(us!=null) //compara si existe el usuario
                ban = true;
            
            stmt.close();
        }catch (SQLException sqlExcept){
            sqlExcept.printStackTrace();
        }
        return ban;
    }

    @Override
    public boolean buscacorreo(Usuario u) {
                Boolean ban = false; 
        try{
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from "+tableName+"where email="+u.getCorreo());
            String mail; //almacena el correo
            mail = rs.getString(4); //ingresa a la columna de correo y asigna a mail
            if(mail!=null) //compara que existe
                ban = true;
            
            stmt.close();
        }catch (SQLException sqlExcept){
            sqlExcept.printStackTrace();
        }
        return ban;
    }

    @Override
    public boolean login(String us, String pass) {
        Boolean ban=false;
        try{
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select user,pass from "+tableName+"where usr="+us);
            String usuario; //almacena el usuario
            String contraseña;
            usuario = rs.getString(1); //ingresa a la columna de usuario y asigna a usuario
            contraseña = rs.getString(2);
            if(us==usuario && pass==contraseña){
                ban = true;
            }            
            stmt.close();
        }catch (SQLException sqlExcept){
            sqlExcept.printStackTrace();
        }
        return ban;
    }

    @Override
    public boolean bloqueado(Usuario u) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    
}
