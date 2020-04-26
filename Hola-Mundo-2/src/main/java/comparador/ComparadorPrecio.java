package comparador;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Collections;


public class ComparadorPrecio {
	private String producto;
	private ArrayList<Movil> listaMoviles;
	
	public ComparadorPrecio(String busqueda) {
		producto = busqueda;
	}

	public void busqueda() throws Exception {
		Scraping scraping = new Scraping(producto);
		
		
			scraping.buscarCorteIngles();
		
		
		
			scraping.buscarPccomponentes();
		
		
		
		
			
			//scraping.buscarPhoneHouse();
		
		
		//Búsqueda Amazon
		
		//Cogemos la lista de los móviles.
		listaMoviles = scraping.getListaMoviles();
		Collections.sort(listaMoviles, compararPorPrecioMenor);
	}


	/**
	 * Método encargado de ordenar los elementos de la clase Producto 
	 * de menor a mayor en base al atributo precio.
	 */
	Comparator<Movil> compararPorPrecioMenor= new Comparator<Movil>() {
		@Override
		public int compare(Movil movil1, Movil movil2) {
			float precio1 = movil1.getPrecio();
			float precio2 = movil2.getPrecio();
			
			return Float.compare(precio1, precio2);
		}

	};
	
	public ArrayList<Movil> getListaMoviles(){
		return listaMoviles;
	}
	
	@Override
	public String toString() {
		return "ComparadorPrecio [listaMoviles=" + listaMoviles + ", " + "]";
	}
	
//	public static void main(String[] args) {
//		ComparadorPrecio comparador = new ComparadorPrecio("Xiaomi Note 4");
//		
//		try {
//			comparador.busqueda();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		ArrayList<Movil> moviles = comparador.getListaMoviles();
//		for(int i = 0; i< moviles.size(); i++){
//			System.out.println(moviles.get(i).toString());
//		}
//			
//		System.out.println("Lo que sea");
//	}
	
}
