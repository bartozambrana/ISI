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
import org.jsoup.nodes.Element;
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
	
//	public void buscarPhoneHouse() throws IOException {
//		
//		// URL de búsqueda final
//		String urlFinal = "";
//		
//		// URL de Phone House para la búsqueda de items
//		String urlPhoneHouse = "https://www.phonehouse.es/buscar.html?buscar-texto=";
//
//		
//		// Unimos toda la URL para la búsqueda
//		urlFinal = urlPhoneHouse + reemplazaEspaciosPhoneHouse(producto) + "&subcategoria=Smartphones";
//
//		// Realiza conexion y comprueba status OK = 200
//		if(getStatusConnectionCode(urlFinal) == 200){
//
//			// Obtenemos el documento HTML de la pagina solicitada
//			Document doc = getHtmlDocument(urlFinal);
//
//			// Buscamos las entradas 
//			Elements entradas = doc.select("div.row");
//			System.out.println("Número de móviles PhoneHouse:  " +  entradas.size());
//			
//		    // Recorrer entradas
//	        for (Element elem : entradas) {
//	            String titulo = elem.getElementsByClass("marca-item").text();
//	            System.out.println("Este es el titulo: " + titulo);
//	            String precio =  elem.getElementsByClass("precios-libre").text();
//	            System.out.println("Este es el precio: " + precio);
//	            if(precio.indexOf("€") >= 0) {
//	            	int indice = precio.indexOf("€");
//		            precio = precio.substring(0,indice);
//	            }
//	            precio = precio.replace(",", ".");
//	            float precioReal = Float.parseFloat(precio);
//	            String link = elem.getElementsByClass("item-listado-mosaico").attr("href");
//	            String urlImagen = elem.getElementsByClass("imagen-item").attr("href");
//	            Movil p = new Movil(titulo,precioReal,link,urlImagen,LocalDate.now(),FuentesDatos.PHONE_HOUSE);;
//				listaMoviles.add(p);                			
//	        }
//
//		} else {
//			System.out.println("Conexion rechazada");
//		}
//	}
//	
	public  void buscarCorteIngles() throws IOException{
		//Url para la búsqueda en Amazon;
		
		//String url = "http://www.elcorteingles.es/electronica/moviles-y-smartphones/search/?level=6&s=" + reemplazaEspaciosPorMas(producto);
		String url = "https://www.maxmovil.com/es/moviles-libres/comprar-smartphones-libres.html#/dffullscreen/query=" + this.reemplazaEspaciosPhoneHouse(producto) 
		  + "&filter%5Bcategories%5D%5B0%5D=Smartphone&query_name=match_and";
			
			//Obtenemos el documento HTML de la web.
			Document documento = Jsoup.connect(url).userAgent("Mozilla/5.0 (Windows; U; Windows NT 6.1; zh-CN; rv:1.9.2.8) Gecko/20100722 Firefox/3.6.8").get();

//			Document documentoCaracteristicas = null;
			if(documento != null) {
				String comprobacion = documento.select("h1.truncate.wrap").text();
				System.out.println(comprobacion);
				
				if(comprobacion != null && comprobacion.equals("Móviles y Smartphones")) {
					// Buscamos las entradas 
					Elements entradas = documento.select("div.product-preview");
					System.out.println("El número de elemtnos es " + entradas.size());
					 for (Element elem : entradas) {
				            String titulo = elem.getElementsByClass("c12").attr("title");
				            String precio = elem.getElementsByClass("current").text();
				            System.out.println("El precio es: " + precio);
				            if(!precio.equals("€")) {
				            	int indice = precio.indexOf("€");
					            precio = precio.substring(0,indice);
					            precio = precio.replace(".","");
					            precio = precio.replace(",", ".");
					            float precioReal = Float.parseFloat(precio);
					            String link = "https://www.elcorteingles.es" + elem.getElementsByAttributeValue("data-event", "product_click").attr("href");
					            String urlImagen = "https:" + elem.getElementsByClass("c12").attr("src");
					            
					            
					            //Obtenemos las características: 
					            //documentoCaracteristicas = getHtmlDocument(link);
					            //Elements entradas2 = documentoCaracteristicas.getElementsByAttributeValue("id", "features");
					            //String datos = entradas2.html();
					            String datos ="";
					            Movil movil = new Movil(titulo,precioReal,link,urlImagen,FuentesDatos.CORTEINGLES,datos);
					            listaMoviles.add(movil);
					            
				            }
				                    			
				     }            
				}
			}
	
	}
	
	
	public void buscarPccomponentes () throws Exception
    {
		
		String url = "https://www.pccomponentes.com/buscar/?query="+ reemplazaEspaciosPorMas(producto) + "#pg-0&or-search&fm-1116";
        
		//String url = "https://www.pccomponentes.com/buscar/?query=xiami+note+4#pg-0&or-search&fm-1116";
		
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
                   
                    //Document doc2 = (Document) Jsoup.connect(urlMovil).get(); 
                    //String datos = doc2.getElementById("ficha-producto-caracteristicas").html();   // 
                    String datos = "";
                    Movil movil = new Movil(nombre, precio_util, urlMovil, urlImagen, FuentesDatos.PCCOMPONENTES,datos);
                    listaMoviles.add(movil);
                    
                }catch (Exception e) {
                    System.err.println(e.getMessage());
                }
                
                
            }
        }
        
    }
	
	public void buscarMaxMovil() throws Exception
    {
		
		
		String url = "https://www.maxmovil.com/es/moviles-libres/comprar-smartphones-libres.html#/dffullscreen/query=" + this.reemplazaEspaciosPhoneHouse(producto) 
					  + "&filter%5Bcategories%5D%5B0%5D=Smartphone&query_name=match_and";
        
		//String url = "https://www.pccomponentes.com/buscar/?query=xiami+note+4#pg-0&or-search&fm-1116";
		
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
                   
                    //Document doc2 = (Document) Jsoup.connect(urlMovil).get(); 
                    //String datos = doc2.getElementById("ficha-producto-caracteristicas").html();   // 
                    String datos = "";
                    Movil movil = new Movil(nombre, precio_util, urlMovil, urlImagen, FuentesDatos.PCCOMPONENTES,datos);
                    listaMoviles.add(movil);
                    
                }catch (Exception e) {
                    System.err.println(e.getMessage());
                }
                
                
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
    
//    private static float limpiarYConvertirPrecio2(String p)
//    {
//        // quitar la subcadena " €" 
//        String aux = p.substring(0, p.length()-2);
//
//        // cambiar ',' por '.' para uso correcto
//        aux = aux.replace(",", ".") ;   
//
//        // convertir a float 
//        float a_devolver =   Float.parseFloat(aux);
//
//        return a_devolver; 
//    }
//	
	/**
	 * Con esta método compruebo el Status code de la respuesta que recibo al hacer la petición
	 * EJM:
	 * 		200 OK			300 Multiple Choices
	 * 		301 Moved Permanently	305 Use Proxy
	 * 		400 Bad Request		403 Forbidden
	 * 		404 Not Found		500 Internal Server Error
	 * 		502 Bad Gateway		503 Service Unavailable
	 * @param url
	 * @return Status Code
	 */
	public static int getStatusConnectionCode(String url) {
			
	    Response response = null;
		
	    try {
		response = Jsoup.connect(url).userAgent("Mozilla/5.0").timeout(100000).ignoreHttpErrors(true).execute();
	    } catch (IOException ex) {
		System.out.println("Excepción al obtener el Status Code: " + ex.getMessage());
	    }
	    return response.statusCode();
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
	
//	public static void main(String[] args) {
//		String url = "https://www.maxmovil.com/es/moviles-libres/comprar-smartphones-libres.html#/dffullscreen/query=" + reemplazaEspaciosPhoneHouse("Xiaomi note 8") 
//		  + "&filter%5Bcategories%5D%5B0%5D=Smartphone&query_name=match_and";
//		
//		Document doc = getHtmlDocument(url);
//	}
	
}

