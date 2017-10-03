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
    @Test
    public void testCrearUser() {
        System.out.println("crearUser");
        Usuario u = new Usuario();
        u.setNombre("david2");
        u.setApp("her2");
        u.setApm("her2");
        u.setCorreo("ole5@gmail.com");
        u.setPass("abc2");
        u.setUser("dher2");
        u.setFace("dher2");
        u.setEdad(13);
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
    
    /*
    @Test
    public void testEditaUser() {
        System.out.println("editaUser");
        Usuario u = null;
        UsuariosDAOImp instance = new UsuariosDAOImp();
        boolean expResult = false;
        boolean result = instance.editaUser(u);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testConsultaUser() {
        System.out.println("consultaUser");
        String us = "";
        UsuariosDAOImp instance = new UsuariosDAOImp();
        String[] expResult = null;
        String[] result = instance.consultaUser(us);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }


    @Test
    public void testEliminaUser() {
        System.out.println("eliminaUser");
        Usuario u = null;
        UsuariosDAOImp instance = new UsuariosDAOImp();
        instance.eliminaUser(u);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }


    @Test
    public void testBuscauser() {
        System.out.println("buscauser");
        Usuario u = null;
        UsuariosDAOImp instance = new UsuariosDAOImp();
        boolean expResult = false;
        boolean result = instance.buscauser(u);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }


    @Test
    public void testBuscacorreo() {
        System.out.println("buscacorreo");
        Usuario u = null;
        UsuariosDAOImp instance = new UsuariosDAOImp();
        boolean expResult = false;
        boolean result = instance.buscacorreo(u);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }


    @Test
    public void testLogin() {
        System.out.println("login");
        String us = "";
        String pass = "";
        UsuariosDAOImp instance = new UsuariosDAOImp();
        boolean expResult = false;
        boolean result = instance.login(us, pass);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }


    @Test
    public void testBloqueado() {
        System.out.println("bloqueado");
        Usuario u = null;
        UsuariosDAOImp instance = new UsuariosDAOImp();
        boolean expResult = false;
        boolean result = instance.bloqueado(u);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
    */
}
