package comparador;

import com.aafanasev.fonoapi.DeviceEntity;
import com.aafanasev.fonoapi.retrofit.FonoApiFactory;
import com.aafanasev.fonoapi.retrofit.FonoApiService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;

public class JavaSample { 

    // TODO: https://fonoapi.freshpixl.com/token/generate
    private static final String TOKEN = "1a8a0f78ca189c64d69a9d245a45f7125700a49494a039be";

    public static void main(String[] args) throws IOException {
        showcaseRetrofit();
    }
    
    @SuppressWarnings("empty-statement")
    private static void showcaseRetrofit() throws IOException {
        String TOKEN = "1a8a0f78ca189c64d69a9d245a45f7125700a49494a039be";
        FonoApiService api = new FonoApiFactory().create();

         Call<List<DeviceEntity>> response = api.getDevice(TOKEN, "note 8", "xiaomi", 0); //
        
        //Response<List<DeviceEntity>> response;
        //response =  api.getLatest(TOKEN, "motorola", 30).execute();       
                 
        String nombre; 
        ArrayList<String> caracteristicas;
        
        List<DeviceEntity> listaSacada = null; 
        
        try{
            listaSacada = response.execute().body();
            
            for(int i=0; i < listaSacada.size(); i++)
            {
                // sacar nombre
                nombre = listaSacada.get(i).getDeviceName();

                // sacar características 
                //caracteristicas = listaSacada.get(i).getCamera();
                
                // sacar características
                caracteristicas = sacarCaracteristicasFonoapi(listaSacada.get(i));

                System.out.println("Nombre = " + nombre + "\tCaracterísticas: " + caracteristicas);
                
                
                
            }
        }catch(Exception e){  
            System.out.println("NO SE HA ENCONTRADO NADA: " + e.getMessage());
            
        }; 
                
    }
    
    
    
    public static ArrayList<String> sacarCaracteristicasFonoapi( DeviceEntity item ){
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

}