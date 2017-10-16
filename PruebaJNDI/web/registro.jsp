<%-- 
    Document   : registro
    Created on : 16/10/2017, 11:29:55 AM
    Author     : worm_
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registro</title>
        
    </head>
    <body>
        <p><%= new java.util.Date()%></p>
        <h1>FI Arcade</h1>
        <form action="Grabador.jsp" method="POST">
            <table>
                <tr><td>Usuario:                </td><td><input type="text" placeholder="Nombre de Usuario" name="username"></td></tr>
                <tr><td>Contraseña:             </td><td><input type="text" placeholder="Contraseña" name="pwd"></td></tr>
                <tr><td>Repite Constraseña:     </td><td><input type="password" placeholder="Contraseña" name="rpwd"></td></tr>
                <tr><td>Nombre:                 </td><td><input type="password" placeholder="Nombre" name="name"></td></tr>
                <tr><td>Apellido Parterno:      </td><td><input type="text" placeholder="Primer Apellido" name="appat"></td></tr>
                <tr><td>Apellido Materno:       </td><td><input type="text" placeholder="Segundo Apellido" name="apmat"></td></tr>
                <tr><td>Correo:                 </td><td><input type="text" placeholder="ejemplo@dominio.com" name="mail"></td></tr>
                <tr><td>Facebook:               </td><td><input type="text" placeholder="fb.com/User"name="fb"></td></tr>
                <tr><td>Edad:                   </td><td><input type="number" placeholder="18" name="age"></td></tr>
                <tr><td><input type="submit" name="send" value="Enviar"></td><td><input type="reset" name="clear" value="Borrar"></td></tr>        
            </table>         
        </form>
    </body>
</html>
