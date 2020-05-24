package comparador;



public class Movil{
	
	private String marca;
	private Float precio;
	private String links;
	private String urlImagen;


	
	public Movil(String marca, Float precio,String urls, String urlImagen) {
		this.marca = marca;
		this.precio = precio;
		this.links = urls;
		this.urlImagen = urlImagen;

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



	public String getUrlImagen() {
		return urlImagen;
	}



	public void setUrlImagenes(String urlImagen) {
		this.urlImagen = urlImagen;
	}

	

	@Override
	public String toString() {
		return "marca=" + marca + " &precio=" + precio + " &link=" + links + " &imagen= " + urlImagen + " #" ;
	}

	
	
	
}
