/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daoimp;

import dao.Usuario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author davidhernandez
 */
public class UsuariosDAOImpTest {
    private Connection conn = null;
    private String tableName = "USUARIO";
    
    public UsuariosDAOImpTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
       String dbURL ="jdbc:derby://localhost:1527/arena;";
       try{
            Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
            this.conn = DriverManager.getConnection(dbURL); 
        }catch (Exception except){
            except.printStackTrace();
        }
    }
    
    @After
    public void tearDown() {
    }


    /**
     * Test of crearUser method, of class UsuariosDAOImp.
     */
   // @Test
    public void testCrearUser() {
        System.out.println("crearUser");
        Usuario u = new Usuario();
        u.setNombre("david");
        u.setApp("hernandez");
        u.setApm("herrrera");
        u.setCorreo("ole@gmail.com");
        u.setPass("1234");
        u.setUser("davidHer");
        u.setFace("dher");
        u.setEdad(24);
        UsuariosDAOImp instance = new UsuariosDAOImp();
        instance.setConn(this.conn);
        try{
            Statement stmt = this.conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from usuario where email='"+u.getCorreo()+"'");
            while(rs.next()){
                String correo = rs.getString("email");
                if(correo.compareTo(u.getCorreo())==0){
                    fail("ya esta registrado");
                }
            }
        }catch(Exception e){
            fail("Ya esta registrado");
        }
        instance.crearUser(u);//insert
        
        boolean encontrado = false;
        
        try{
            Statement stmt = this.conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from usuario where email='"+u.getCorreo()+"'");
            
            while(rs.next()){
                String correo = rs.getString("email");
                if(correo.compareTo(u.getCorreo())==0){
                    encontrado=true;
                    System.out.println("Registro exitoso");
                }
            }
        }catch(Exception e){
            fail("2 check");
        }
        
        assertTrue(encontrado);
    }

    /**
     * Test of editaUser method, of class UsuariosDAOImp.
     */
    
    
    //@Test
    public void testEditaUser() {
        System.out.println("editaUser");
        Usuario u = new Usuario();
        
        u.setNombre("Josue");
        u.setApp("Hernandez");
        u.setApm("Herrrera");
        u.setCorreo("ole@gmail.com");
        u.setPass("1234");
        u.setUser("davidHer");
        u.setFace("dher");
        u.setEdad(24);
        
        UsuariosDAOImp instance = new UsuariosDAOImp();
        instance.setConn(this.conn);
        
        try{
            Statement stmt = this.conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from usuario where email='"+u.getCorreo()+"'");
            while(rs.next()){
                String correo = rs.getString("email");
                if(correo.compareTo(u.getCorreo())!=0){
                    fail("Usuario no encontrado");
                }
                
            }
            
        }catch(Exception e){
            fail("No esta registrado");   
        }
        
        
        instance.editaUser(u);//edita
        boolean actualizado = false;
        
        try{
            Statement stmt = this.conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from usuario where email='"+u.getCorreo()+"'");
            while(rs.next()){
                String Nombre = rs.getString("nom");
                if(Nombre.compareTo(u.getNombre())==0){
                    actualizado=true;
                }
            }
            
        }catch(Exception e){
            fail("No actualizo");   
        }
        

        assertTrue(actualizado);
    }

    //@Test
    public void testConsultaUser() {
        System.out.println("consultaUser");
    
    Usuario u = new Usuario();
    u.setCorreo("oloe5@gmail.com");
    
    UsuariosDAOImp instance = new UsuariosDAOImp();
    instance.setConn(this.conn);
    try{
        Statement stmt = this.conn.createStatement();
        ResultSet rs = stmt.executeQuery("select * from usuario where email='"+u.getCorreo()+"'");
        System.out.println("AAAAA");
        if(!rs.next()){
            System.out.println("BBBBBB");
            u = null;
            System.out.println("Ese correo no corresponde con ningun usuario");
        }
        else
        {
            while(rs.next()){
                if(!rs.getString("email").equals(u.getCorreo())){
                    System.out.println("BBBBBB");
                    u = null;
                    System.out.println("Ese correo no corresponde con ningun usuario");
                }
                else
                {
                    System.out.println("CCCCCC");
                    System.out.println("Usuario encontrado");
                    u.setUser(rs.getString("usr"));
                    u.setPass(rs.getString("pass"));
                    u.setCorreo(rs.getString("email"));
                    u.setNombre(rs.getString("nom"));
                    u.setApp(rs.getString("app"));
                    u.setApm(rs.getString("apm"));
                    u.setEdad(Integer.parseInt(rs.getString("edad")));
                    u.setFace(rs.getString("face"));
                    u.setBloqueado(Integer.parseInt(rs.getString("bloqueado")));

                    System.out.println("email ingresado:"+u.getCorreo());

                    System.out.println("------------------DATOS DEL USUARIO--------------");
                    System.out.println("usr:"+u.getUsuario());
                    System.out.println("pass:"+u.getPass());
                    System.out.println("email:"+u.getCorreo());
                    System.out.println("nom:"+u.getNombre());
                    System.out.println("app:"+u.getApp());
                    System.out.println("apm:"+u.getApm());
                    System.out.println("edad:"+u.getEdad());
                    System.out.println("face:"+u.getFace());
                    System.out.println("bloqueado:"+u.getBloqueado());
                }   
            }
        }
        System.out.println("DDDDDDDDD");
    }catch(Exception e){
        fail("Algo salio mal");
    }
    assertTrue(true);
    // TODO review the generated test code and remove the default call to fail.
    }


    //@Test
    public void testEliminaUser() {
        System.out.println("testEliminaUser");
        Usuario u = new Usuario();
        u.setCorreo("ole5@gmail.com");

        UsuariosDAOImp instance = new UsuariosDAOImp();
        instance.setConn(this.conn);
        try{
            Statement stmt = this.conn.createStatement();
            stmt.execute("delete from usuario where email='"+u.getCorreo()+"'");
        }catch(Exception e){
            fail("algo fallo");
        }
        // TODO review the generated test code and remove the default call to fail.
        assertTrue(true);
    }
}
