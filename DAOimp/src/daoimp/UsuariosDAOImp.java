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
    
    public static Connection conn = null;
    private static Statement stmt = null;
    private static String tableName = "app.usuario";


    public void setConn(Connection conn) {
        UsuariosDAOImp.conn = conn;
    }

    @Override
    public void crearUser(Usuario u) {
        try{
            stmt = conn.createStatement();
            int bloqueado=0;
            stmt.execute("insert into app.usuario (usr,pass,email,nom,app,apm,edad,face,bloqueado) values('"+u.getUsuario()+"','"+u.getPass()+"','"+u.getCorreo()+"','"+u.getNombre()+"','"+u.getApp()+"','"+u.getApm()+"',13,'"+u.getFace()+"',0)");
            stmt.close();
        }
        catch (SQLException sqlExcept){
            sqlExcept.printStackTrace();
        }
    }

    @Override
    public void editaUser(Usuario u) {
        try{
            stmt = conn.createStatement();
            //stmt.execute("update "+tableName+"set pass='"+u.getPass()+"', nom='"+u.getNombre()+"', app='"+u.getApp()+"', apm='"+u.getApm()+"', edad="+u.getEdad()+", face='"+u.getFace()+"' where email='"+u.getCorreo()+"'");
            stmt.execute("update app.usuario set usr='"+u.getUsuario()+"', pass='"+u.getPass()+"', nom='"+u.getNombre()+"', app='"+u.getApp()+"', apm='"+u.getApm()+"', edad="+u.getEdad()+", face='"+u.getFace()+"', bloqueado=0 where email='"+u.getCorreo()+"'");
            stmt.close();
        }
        catch (SQLException sqlExcept){
            sqlExcept.printStackTrace();
        }
    }
    
     @Override
    public Usuario consultaUser(Usuario u){
        System.out.println("RECIBE LOGIN: "+u.getUsuario()+"");
        
        try{
            
            stmt = conn.createStatement();
            int ban = 0;
            ResultSet rs = stmt.executeQuery("select * from "+tableName+" where usr='"+u.getUsuario()+"'");
            //System.out.println("RECIBE BD: "+rs.getString(1));
            while(rs.next()){            
             u.setUser(rs.getString("usr"));
             u.setPass(rs.getString("pass"));
             u.setCorreo(rs.getString("email"));
             u.setNombre(rs.getString("nom"));
             u.setApp(rs.getString("app"));
             u.setApm(rs.getString("apm"));
             u.setEdad(Integer.parseInt(rs.getString("edad")));
             u.setFace(rs.getString("face"));
             u.setBloqueado(Integer.parseInt(rs.getString("bloqueado")));
             ban=1;   
            } 
            if (ban==0) {
                System.out.println("HAY NULOS");
                u.setUser(null);
                u.setPass(null);
                u.setCorreo(null);
                u.setNombre(null);
                u.setApp(null);
                u.setApm(null);
                u.setEdad(0);
                u.setFace(null);
                u.setBloqueado(0);
            }
                
            stmt.close();
        }
        catch (SQLException sqlExcept){
            sqlExcept.printStackTrace();
        }
        return u;
    }

    @Override
    public void eliminaUser(Usuario u) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
/*
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

*/
    
}
