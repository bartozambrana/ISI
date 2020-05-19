package comparador;



//import org.joda.time.LocalDate;
//
//import java.util.ArrayList;

public class Movil{
	
	private String marca;
	private Float precio;
	private String links;
	private String urlImagen;
	private FuentesDatos proveedor;
//	private String caracteristicas;
	
	public Movil(String marca, Float precio,String urls, String urlImagen, FuentesDatos proveedor, String caracteristicas) {
		this.marca = marca;
		this.precio = precio;
		this.links = urls;
		this.urlImagen = urlImagen;
		this.proveedor = proveedor;
//		this.caracteristicas = caracteristicas;
	}
	


	public String getMarca() {
		return marca;
	}
	
//	public String getCaracteristicas() {
//		return caracteristicas;
//	}
//


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



	public String getUrlImagen() {
		return urlImagen;
	}



	public void setUrlImagenes(String urlImagen) {
		this.urlImagen = urlImagen;
	}

	

//	public LocalDate getFecha() {
//		return fecha;
//	}



	@Override
	public String toString() {
		return "marca=" + marca + " &precio=" + precio + " &link=" + links + " &imagen= " + urlImagen + " #" ;
	}

	
	
	
}
