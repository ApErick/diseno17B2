<%-- 
    Document   : Grabador
    Created on : 16/10/2017, 12:15:04 PM
    Author     : worm_
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Grabador</title>
    </head>
    
    <h1>USUARIO REGISTRADO CON EXITO<BR/></h1>
        <a href="index.html">INICIA SESIÓN</a>
        
        <%
                dao.Usuario usuario = new dao.Usuario();
                usuario.setUser(request.getParameter("username"));
                usuario.setPass(request.getParameter("pwd"));
                usuario.setNombre(request.getParameter("name"));
                usuario.setApp(request.getParameter("appat"));
                usuario.setApm(request.getParameter("apmat"));
                usuario.setCorreo(request.getParameter("mail"));
                usuario.setFace(request.getParameter("fb"));
                usuario.setEdad(Integer.parseInt(request.getParameter("age")));

                daoimp.UsuariosDAOImp usuario_dao = new daoimp.UsuariosDAOImp();
                usuario_dao.setConn(ds.getConnection());
                usuario_dao.crearUser(usuario);
        %>
        <%@ page errorPage="datosNoValidos.jsp"%>
        
        <!DOCTYPE html>
        <%!
            javax.sql.DataSource ds;

            public void jspInit() {
                javax.naming.InitialContext cxt;
                try {
                    cxt = new javax.naming.InitialContext();
                    if(cxt == null) {
                        throw new Exception("No context");
                    }
                    this.ds = (javax.sql.DataSource) cxt.lookup("java:/comp/env/jdbc/derby");
                } catch (javax.naming.NamingException ex) {
                    java.util.logging.Logger.getLogger("grabador").log(java.util.logging.Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    java.util.logging.Logger.getLogger("grabador").log(java.util.logging.Level.SEVERE, null, ex);
                }
            }
        %>
    

