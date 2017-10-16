<%-- 
    Document   : Grabador
    Created on : 16/10/2017, 12:15:04 PM
    Author     : worm_
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Grabador</title>
    </head>
    <body>
        <h1>SOY UN GRABADOR</h1>
        
        <%
               //CODIGO PARA JALAR LOS DATOS DEL FORMULARIO
        %>
        
        <%
            javax.sql.DataSource ds;
            
            public void jspInit(){
                javax.naming.InitialContext cxt;
                try{
                    cxt = new javax.naming.InitialContext();
                    if(cxt == null){
                        throw new Exception("No tenemos contexto!");
                    }
                    this.ds = (javax.sql.DataSource) cxt.lookup( "java:/comp/env/jdbc/derby" );
                } catch (javax.naming.NamingException ex) {
                    java.util.logging.Logger.getLogger("Grabador").log(java.util.loggin.Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    java.util.logging.Logger.getLogger("Grabador").log(java.util.loggin.Level.SEVERE, null, ex);
                }
            }

                
        %>
    </body>
</html>
