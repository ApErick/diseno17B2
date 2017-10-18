package mx.uaemex.fi.disenio.nrda;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import daoimp.*;
import dao.*;

/**
 *
 * @author worm_
 */
@WebServlet(urlPatterns = {"/Login"})
public class Login extends HttpServlet{

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private DataSource ds;
    public String username;
    public String password;
    public String paswd;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
     
        
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Login</title>");            
            out.println("</head>");
            out.println("<body>");
            
            Usuario u = new Usuario();
            Usuario uR = new Usuario();
            UsuariosDAOImp daoU = new UsuariosDAOImp();
            daoU.setConn(this.ds.getConnection());
            UsuariosDao daoInt = daoU;
            username=request.getParameter("username");
            password=request.getParameter("password");
            u.setUser(username);
            uR = daoInt.consultaUser(u);
            paswd=uR.getPass();
            String usrbd=uR.getUsuario();
            if(usrbd!=null){
                if(password.equals(paswd)){
                    out.println("<h1>BIENVENIDO</h1>");
                    out.println("<h4>"+uR.getNombre()+"</h4>");
                }else {
                     out.println("<h1>USUARIO O CONTRASEÑA INCORRECTA</h1>");
                }
            } else{
                out.println("<h1>ACCESO DENEGADO!<br/></h1><h3>USUARIO O CONTRASEÑA INCORRECTA<h3>");
            }
           
            out.println("</body>");
            out.println("</html>");
            /* TODO output your page here. You may use following sample code. */
            

            
        }catch(Exception e){
            
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
     @Override
    public void init(ServletConfig config) throws ServletException {
        try{
            super.init(config);
            InitialContext cxt = new InitialContext();
            if(cxt == null){
                throw new Exception("No tenemos contexto!");
            }
            this.ds = (DataSource) cxt.lookup("java:/comp/env/jdbc/derby");
            if(this.ds == null){
                throw new Exception("No encuentro el DataSource");
            }
        } catch (NamingException ex) {
            Logger.getLogger(ToPruebaJNDI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ToPruebaJNDI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

}
