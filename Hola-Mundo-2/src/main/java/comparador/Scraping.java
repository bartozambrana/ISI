package comparador;

import java.io.IOException;
import java.util.ArrayList;
//import java.util.Date;
//
//import org.joda.time.LocalDate;
//biliotecas para el web scraping.
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.Connection.Response;
//import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;




public class Scraping {
	/*Url del producto*/
	private String producto;
	/*Conjunto de productos de las páginas**/
	private ArrayList<Movil> listaMoviles = new ArrayList<Movil>();
	
	/**
	 @brief Constructor de la clase
	 * */
	
	public Scraping(String producto) {
		this.producto = producto;
		
		
	}
	
	/**
	 @brief Reemplaza los espacios insertados por el usuario
	 por el signo +
	 @param producto: Movil introducido.
	 *
	 */
	public String reemplazaEspaciosPorMas(String producto) {
		return producto.replace(" ", "+");
	}
	
	public String reemplazaEspaciosPhoneHouse(String producto) {
		return producto.replace(" ", "%20");
	}
	
	public void buscarPccomponentes () throws Exception
    {
		
		String url = "https://www.pccomponentes.com/buscar/?query="+ reemplazaEspaciosPorMas(producto) + "#pg-0&or-search&fm-1116";
		
		// Componer la URL y crear el objeto Document para hacer el web-scraping 

        Document doc = this.getHtmlDocument(url);

        // extraer cada teléfono móvil (la información que nos hace falta) 
        Elements moviles = doc.getElementsByClass("tarjeta-articulo__elementos-basicos");
        
        
        // sacar info de cada móvil
        for( int i=0; i < moviles.size(); i++ ){
            
            // ---------- DATOS DEL MÓVIL ------------
            String nombre = null;
            String urlMovil = null;
            String urlImagen = null; 
            float precio_util;      // precio con el formato adecuado 
                                    // (en float, sin espacios ni el '€' y con '.' en lugar de ',' ) 
                                    
            // ---------------------------------------
            String precio_actual = null;    // VARIABLE para guardar la cadena "virgen" con el precio, no será definitiva 
            
            
            // extraer y comprobar si es móvil o accesorio
            // Esto se realiza porque el filtro de la página web nos incluye (posiblemente por error) 
            // componentes dentro del filtrado por Smartphones/Móviles, por eso comprobamos para cada elemento
            // que sacamos si su categoría (individualmente) es de Smartphone/Móviles o Accesorios
            Elements aux = moviles.get(i).getElementsByTag("a");       // en la etiqueta <a></a> está el tipo de elemento que es
            String tipo = aux.attr("data-category");    // extraer valor del atributo                                           
            
            if (tipo.equals("Smartphone/Móviles") ) {   // si no es móvil, LO IGNORO 
                try 
                {
                    nombre = moviles.get(i).getElementsByClass("tarjeta-articulo__nombre").text();
                    precio_actual = moviles.get(i).getElementsByClass("tarjeta-articulo__precio-actual").text();

                    
                    // procesar y formatear el precio                
                    precio_util = limpiarYConvertirPrecio(precio_actual); 
                   
                    // sacar la URL
                    urlMovil = "https://www.pccomponentes.com" + aux.attr("href");
                    

                    // sacar enlace imagen
                    Elements tagImage = moviles.get(i).getElementsByTag("img");   // paso previo a sacar la imagen
                    urlImagen = "https:" + tagImage.attr("src");
                    
                    //Creación del objeto móvil.
                   
                    Movil movil = new Movil(nombre, precio_util, urlMovil, urlImagen);
                    listaMoviles.add(movil);
                    
                }catch (Exception e) {
                    System.err.println(e.getMessage());
                }
                
                
            }
        }
        
    }
	
	public void buscarMaxMovil() throws Exception
    {
		
		
		//String url = "https://www.inmovil.es/buscar?orderby=position&orderway=desc&search-cat-select=0&search_query=xiaomi+note+8";
		String url = "https://www.inmovil.es/buscar?orderby=position&orderway=desc&search-cat-select=0&search_query=" + this.reemplazaEspaciosPorMas(producto);
		
        Document doc = Jsoup.connect(url).get();;
        
        // extraer cada teléfono móvil (la información que nos hace falta) 
        Elements moviles = doc.getElementsByClass("product-container");
               
        // sacar info de cada móvil
        for( int i=0; i < moviles.size(); i++ ){
            
            // ---------- DATOS DEL MÓVIL ------------
            String nombre = null;
            String urlMovil = null;
            String urlImagen = null; 
            float precio_util;      // precio con el formato adecuado 
                                    // (en float, sin espacios ni el '€' y con '.' en lugar de ',' ) 
                                    
            // ---------------------------------------
            String precio_actual = null;    // VARIABLE para guardar la cadena "virgen" con el precio, no será definitiva 
            
            try 
            {
                nombre = moviles.get(i).getElementsByClass("product-name").text();
                
                precio_actual = moviles.get(i).getElementsByClass("price product-price").text();
                precio_actual = precio_actual.replace(" ","");
                precio_actual = precio_actual.replace(".","");
                precio_actual = precio_actual.replace(",", ".");
                precio_actual = precio_actual.substring(0,precio_actual.indexOf("€"));
                precio_util = Float.parseFloat(precio_actual);
                
                // sacar la URL
                urlMovil = moviles.get(i).getElementsByClass("product-name").attr("href");
                
                // sacar enlace imagen
                Elements tagImage = moviles.get(i).getElementsByTag("img");   // paso previo a sacar la imagen
                urlImagen = tagImage.attr("data-original");
                //Creación del objeto móvil.
               

                Movil movil = new Movil(nombre, precio_util, urlMovil, urlImagen);
                listaMoviles.add(movil);
                    
            }catch (Exception e) {
                System.err.println(e.getMessage());
            }
                
                
            
        }
        
    }
    
    public ArrayList<Movil> getListaMoviles(){
    	return listaMoviles;
    }
    
    private float limpiarYConvertirPrecio(String p)
    {
        // quitar la subcadena " €" 
        String aux = p.substring(0, p.length()-2);

        // cambiar ',' por '.' para uso correcto
        aux = aux.replace(",", ".") ;   

        // convertir a float 
        float a_devolver =   Float.parseFloat(aux);

        return a_devolver; 
    }
    

	
	/**
	 * Con este método devuelvo un objeto de la clase Document con el contenido del
	 * HTML de la web que me permitirá parsearlo con los métodos de la librelia JSoup
	 * @param url
	 * @return Documento con el HTML
	 */
	public  Document getHtmlDocument(String url) {

	    Document documento = null;
		try {
		    documento = (Document) Jsoup.connect(url).get();
		} catch (IOException ex) {
			System.out.println("Excepción al obtener el HTML de la página" + ex.getMessage());
		}
	    return documento;
	}
	

}

