<%@page import="comparador.*" %>
<%@page import="java.util.ArrayList" %>
<%@page import="org.w3c.dom.Document"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
  <head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  	<link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet" type="text/css">
  	<link href="https://fonts.googleapis.com/css?family=Lato" rel="stylesheet" type="text/css">
  	<link href="./estilos.css" rel="stylesheet" type="text/css">
    <title>Comparador de precio</title>
  </head>
  <body>
  
  	<header>
  		<nav class="navbar navbar-default navbar-fixed-top">
		  <div class="container">
		    <div class="navbar-header">
		      <p class="navbar-brand">Comparador</p>
		    </div>
		    <div class="collapse navbar-collapse" id="myNavbar">
		      <ul class="nav navbar-nav navbar-right">
		        <li><a href="mailto:alonsobueno13@correo.ugr.es?subject:proyectoIsi">CONTACTO</a></li>
		      </ul>
		    </div>
		  </div>
		</nav>
		<div class="jumbotron text-center">
  		  <h1>Comparador de Precios</h1> 
		  <p>Ingenier&iacute;a de Sistemas de Informac&iacute;on</p> 
		</div>
  	</header>
  	
	<main>
	
		<form action="./busqueda.jsp" method="get" class="center">
		    <div class="input-group">
		      <input type="text" class="form-control" id="barra" placeholder="Marca" name="marca" id="Busqueda" required>
		      <input type="text" class="form-control" id="barra" placeholder="Modelo" name="modelo" id="modeloBusqueda" required>
		      <div class="input-group-btn">
		        <input type="submit"  class="btn btn-danger" id="enviar" value="Enviar">
		      </div>
		    </div>
	    </form>

	</main>
	
	<footer class="container-fluid text-center">
		<p>Este es un proyecto para la asignatura ISI, a&ntilde;o 2020</p>
	</footer>
	
	
  </body>
</html>
