/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daoimp;

import dao.Juego;
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
public class JuegoDAOImpTest {
    
    private Connection conn = null;
    private String tableName = "JUEGO";

    
    public JuegoDAOImpTest() {
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
     * Test of setConn method, of class JuegoDAOImp.
     */
    
    @Test
    public void testCreaJuego() {
        System.out.println("creaJuego");
        Juego nj = new Juego();
        nj.setNJuego("pulpo Paul");
        JuegoDAOImp instance = new JuegoDAOImp();
        instance.setConn(this.conn);
        try{
         Statement stmt = this.conn.createStatement();
         ResultSet rs = stmt.executeQuery("select * from juego where nombre='"+nj.getNJuego()+"'");
         while (rs.next()){
                    String nombre = rs.getString("nombre");
                    if(nombre.compareTo(nj.getNJuego())==0){
                        fail("El juego esta registrado");
                    }
                 }
            }catch(Exception e){
                fail("Ya esta registrado");
        }
            instance.creaJuego(nj);//insert
            
            boolean encontrado = false;
        
        try{
            Statement stmt = this.conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from juego where nombre='"+nj.getNJuego()+"'");
            
            while(rs.next()){
                String nombre = rs.getString("nombre");
                if(nombre.compareTo(nj.getNJuego())==0){
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
     * Test of validanomjue method, of class JuegoDAOImp.
     */
    
    @Test
    public void testbuscanomjue() {
        System.out.println("validanomjue");
        Juego nj = new Juego();
        nj.setNJuego("Ahorcado");
        Boolean ban = false;
        JuegoDAOImp instance = new JuegoDAOImp();
        try{
            Statement stmt = this.conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from juego where nombre='"+nj.getNJuego()+"'");
            while(rs.next()){
                String nombre = rs.getString("nombre");
                if(nombre.compareTo(nj.getNJuego())==0){
                    ban = true;
                }
            }
        }catch(Exception e){
            fail("No esta registrado.");
        }
        assertTrue(ban);
        //instance.validanomjue(nj);
    }

    /**
     * Test of eliminajuego method, of class JuegoDAOImp.
     */
    /*@Test
    public void testEliminajuego() {
        System.out.println("eliminajuego");
        Juego nj = null;
        JuegoDAOImp instance = new JuegoDAOImp();
        instance.eliminajuego(nj);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    */
    
    
}
