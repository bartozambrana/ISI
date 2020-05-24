package comparador;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.aafanasev.fonoapi.DeviceEntity;
import com.aafanasev.fonoapi.retrofit.FonoApiFactory;
import com.aafanasev.fonoapi.retrofit.FonoApiService;

import retrofit2.Call;

public class ExtraccionFonoApi {
	
	private String TOKEN;
	private String marca;
	private String modelo;

	public  ExtraccionFonoApi() {
		TOKEN = "1a8a0f78ca189c64d69a9d245a45f7125700a49494a039be";
	}
	
	public ArrayList<String> showcaseRetrofit() throws IOException {
        FonoApiService api = new FonoApiFactory().create();
        
        Call<List<DeviceEntity>> response = api.getDevice(TOKEN, modelo, marca, 0);   
                 
        String nombre; 
        ArrayList<String> caracteristicas = null;
        
        List<DeviceEntity> listaSacada = null; 
        
        try{
            listaSacada = response.execute().body();
            
            for(int i=0; i < listaSacada.size(); i++)
            {
                // sacar nombre
                nombre = listaSacada.get(i).getDeviceName();
                
                // sacar características
                caracteristicas = sacarCaracteristicasFonoapi(listaSacada.get(i));
                
                
                
            }
        }catch(Exception e){  
            System.err.println("NO SE HA ENCONTRADO NADA : " + e.getMessage());
            
        }; 
       
       return caracteristicas;
    }
       
    private ArrayList<String> sacarCaracteristicasFonoapi( DeviceEntity item ){
        ArrayList<String> res = new ArrayList<>(); 
        
        if(item.getAlarm()!= null){
            res.add("¿Tiene alarma?: " + item.getAlarm());
        }
        if(item.getAudio_quality() != null){
            res.add("Calidad de audio: " + item.getAudio_quality());
        }
        if(item.getBattery_c()!= null){
            res.add("Batería: " + item.getBattery_c());
        }
        if(item.getBattery_life()!= null){
            res.add("Duración de batería: " + item.getBattery_life());
        }
        if(item.getBrowser()!= null){
            res.add("Navegador: " + item.getBrowser());
        }
        if(item.getCamera()!= null){
            res.add("Cámara: " + item.getCamera());
        }
        if(item.getChipset()!= null){
            res.add("Chipset: " + item.getChipset());
        }
        if(item.getColors()!= null){
            res.add("Colores: " + item.getColors());
        }
        if(item.getDimensions()!= null){
            res.add("Dimensiones: " + item.getDimensions());
        }
        if(item.getGames()!= null){
            res.add("Juegos: " + item.getGames());
        }
        if(item.getLanguages()!= null){
            res.add("Idiomas: " + item.getLanguages());
        }
        if(item.getResolution()!= null){
            res.add("Resolución: " + item.getResolution());
        }
        if(item.getSpeed()!= null){
            res.add("Velocidad estimada: " + item.getSpeed());
        }
        
        
        return res; 
    }
    
    public void setMarca(String marca) {
		this.marca = marca.trim();
	}

	public void setModelo(String modelo) {
		this.modelo = modelo.trim();
	}
}
