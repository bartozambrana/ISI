package comparador;



import org.joda.time.LocalDate;

import java.util.ArrayList;

public class Movil{
	
	private String marca;
	private Float precio;
	private String links;
	private String urlImagenes;
	private FuentesDatos proveedor;
	private LocalDate fecha;
	
	public Movil(String marca, Float precio,String urls, String urlImagenes,
				 LocalDate fecha, FuentesDatos proveedor) {
		this.marca = marca;
		this.precio = precio;
		this.links = urls;
		this.urlImagenes = urlImagenes;
		this.fecha = fecha;
		this.proveedor = proveedor;
	}
	


	public String getMarca() {
		return marca;
	}



	public void setMarca(String marca) {
		this.marca = marca;
	}



	public Float getPrecio() {
		return precio;
	}



	public void setPprecio(Float precio) {
		this.precio = precio;
	}



	public String getLinks() {
		return links;
	}



	public void setLinks(String links) {
		this.links = links;
	}



	public String getUrlImagenes() {
		return urlImagenes;
	}



	public void setUrlImagenes(String urlImagenes) {
		this.urlImagenes = urlImagenes;
	}

	

	public LocalDate getFecha() {
		return fecha;
	}



	@Override
	public String toString() {
		return "Movil [marca=" + marca + "\n, modelo="  + "\n listaprecio=" + precio + "\n links="
				+ links + "\n URL imagenes= " + urlImagenes + "]";
	}

	
	
	
}
