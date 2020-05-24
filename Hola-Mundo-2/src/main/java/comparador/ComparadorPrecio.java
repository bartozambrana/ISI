package comparador;


import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import com.google.appengine.api.memcache.stdimpl.GCacheFactory;
import javax.cache.Cache;
import javax.cache.CacheException;
import javax.cache.CacheFactory;
import javax.cache.CacheManager;


public class ComparadorPrecio {
	private String producto;
	private ArrayList<Movil> listaMoviles = new ArrayList<Movil>();
	private static Cache cache = null;
	private Scraping scraping = null;
	private ExtraccionFonoApi api = new ExtraccionFonoApi();
	private ArrayList<String> caracteristicas = null;
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		try {
			req.setAttribute("listaMoviles", listaMoviles);
			RequestDispatcher rd = req.getRequestDispatcher("busqueda.jsp");
			rd.forward(req, resp);
		}catch(Exception error) {
			resp.sendRedirect("/error.html");
		}
	}
	
	public ComparadorPrecio(String marca, String modelo) {
		producto = marca.trim() + " " + modelo.trim();
		scraping = new Scraping(producto);
		api.setMarca(marca);
		api.setModelo(modelo);
		//Establecemos la conexión con la caché: 
		try {
			CacheFactory cacheFactory = CacheManager.getInstance().getCacheFactory();
            HashMap<Object, Object> properties = new HashMap<>();
            properties.put(GCacheFactory.EXPIRATION_DELTA, TimeUnit.DAYS.toSeconds(1));
            cache = cacheFactory.createCache(properties);
		}catch(CacheException e) {
			System.out.println("Error de createCache " + e );
		}
	}


	public void busqueda() throws Exception {
		

		if(! cache.containsKey(producto)) {
			//Búsqueda scraping
			scraping.buscarMaxMovil();
			scraping.buscarPccomponentes();
			
			//Cogemos la lista de los móviles.
			listaMoviles = scraping.getListaMoviles();
			
			//Búsqueda en la api
			caracteristicas = api.showcaseRetrofit();
			
			
			Collections.sort(listaMoviles, compararPorPrecioMenor);
			cache.put(producto, listaMoviles.toString());
		}else {
			procesarCadena(cache.get(producto).toString());
			//Búsqueda en la api
			caracteristicas = api.showcaseRetrofit();
			
		}
		
	}
	
	public void procesarCadena(String cadena){
		
		cadena = cadena.replaceAll("]", "");
		
		String[] moviles = cadena.split("#");
		
		ArrayList<String> aList = new ArrayList<String>( Arrays.asList(moviles) );
		
		String[] propiedades = null;

		for(int i = 0 ;i < aList.size(); i++) {
			
			String[] atributos = aList.get(i).split(" &");
			ArrayList<String> aList2 = new ArrayList<String>( Arrays.asList(atributos) );
			ArrayList<String> aList3 = new ArrayList<String>();
			
			for(int j= 0 ; j < aList2.size(); j++) {

				propiedades = aList2.get(j).split("=");

				aList3.add(propiedades[0]);
				aList3.add(propiedades[1]);
			}
		
			String marca = aList3.get(1).toString();
			String precio = aList3.get(3).toString();
			String link = aList3.get(5).toString();
			String imagen = aList3.get(7).toString();

			Movil movil = new Movil(marca,Float.parseFloat(precio),link,imagen);
			listaMoviles.add(movil);	
		}
	}
	
	public void setProducto(String producto) {
		this.producto = producto;
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
	
	public ArrayList<String> getCaracteristicas(){
		return caracteristicas;
	}
	
	@Override
	public String toString() {
		return "ComparadorPrecio [listaMoviles=" + listaMoviles + ", " + "]";
	}
	
	
}
