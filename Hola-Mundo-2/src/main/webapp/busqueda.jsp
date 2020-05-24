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
     <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  	<link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet" type="text/css">
  	<link href="https://fonts.googleapis.com/css?family=Lato" rel="stylesheet" type="text/css">
  	<link href="./estilos.css" rel="stylesheet" type="text/css">
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
	    
		<%
			String marca = request.getParameter("marca");
			String modelo = request.getParameter("modelo");
			
			ComparadorPrecio comparador = new ComparadorPrecio(marca, modelo);
			
			comparador.busqueda();
			ArrayList<String> caracteristicas = comparador.getCaracteristicas();
			if(caracteristicas != null){
				out.println("<div class='container-fluid bg-grey'><ul> ");
				out.println("<h2 align='center'><strong> Estas son las características generales para la búsqueda realizada: </strong></h2><br> ");
				for (int i = 0; i < caracteristicas.size(); i++){
					out.println("<li>" +  caracteristicas.get(i).toString() + "</li>" );
					
				}
				out.println("</ul></div>");
			}else{
				out.println("<p align='center'> No hemos encontrado características para su búsqueda </p> ");
			}
			
			ArrayList<Movil> moviles = comparador.getListaMoviles();
			
			if(moviles != null && moviles.size() != 0){
				for(int i = 0; i< moviles.size(); i++){
					out.println(
							" <div class='container-fluid bg-grey'> " +
						 		"<div class='row'>" +
				    			   " <div class='col-sm-4 '> " + 
				     					 "<img class='thumbnail' src='" + moviles.get(i).getUrlImagen() + "'> " + 
				    			    " </div>" +
				   					"<div class='col-sm-8'> " +
				      					"<h2><strong>" + moviles.get(i).getMarca() + "</strong></h2><br>" +
				      					"<ul>" + 
				      						"<li>" + moviles.get(i).getPrecio() + "€</li>" +
				      						"<li>" + "<a href='"+ moviles.get(i).getLinks() +"' class='enlacePagina' > Pinche aquí para vistar la página web </a>" + "</li>" +
				      					"</ul>"+
				    				"</div>"+
				  				"</div>" +
							"</div>"
							);
				}
			}else{
				out.println("<p align='center'> No hemos encontrado móviles para su búsqueda </p> ");
			}
			
	
		%>
		 
	</main>
	<!--  			      					"<h1>Caracter&iacute;sticas</h1>" + 
	//		      					 "<div class='scrollbar'>" + moviles.get(i).getCaracteristicas()  + "</div>"	+ -->
	<footer class="container-fluid text-center">
		<p>Este es un proyecto para la asignatura ISI, a&ntilde;o 2020</p>
	</footer>
	
  </body>
</html>
