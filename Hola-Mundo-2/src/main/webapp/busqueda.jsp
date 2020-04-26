<%@page import="comparador.*" %>
<%@page import="java.util.ArrayList" %>
<%@page import="org.w3c.dom.Document"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
  <head>
    <meta charset="utf-8">
    <title>Comparador de precio</title>
  </head>
  <body>
  
  	<header>
  		<h1>Comparador de Precios de M&oacute;viles</h1>
  	</header>
  	
	<main>
		<form action="busqueda.jsp" method="get">
			<fieldset>
			
				<legend>B&uacute;squeda</legend>
				
				<input type="text" id="Busqueda" name="busqueda" autofocus>
				<input type="submit" id="Enviar" value="Enviar">
			
			</fieldset>
				
		</form>
		
		<%
			String movil = request.getParameter("busqueda");

			
			ComparadorPrecio comparador = new ComparadorPrecio(movil);
			
			comparador.busqueda();
			ArrayList<Movil> moviles = comparador.getListaMoviles();
			for(int i = 0; i< moviles.size(); i++){
				out.println("<p> Hola Diso </p>");
			}
				
			System.out.println("Lo que sea");
		%>
	</main>
	
	<footer>
		<a href="mailto:bartozambrana@correo.ugr.es">Contacto</a>
	</footer>
	
	
  </body>
</html>
