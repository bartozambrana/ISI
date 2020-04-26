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
				
				<input type="search" id="Busqueda" name="movil" autofocus>
				<input type="submit" id="Enviar" value="Enviar">
			
			</fieldset>
				
		</form>
	</main>
	
	<footer>
		<a href="mailto:bartozambrana@correo.ugr.es">Contacto</a>
	</footer>
	
	
  </body>
</html>
