import java.io.File;
import java.nio.file.Files;

public class Archivo {
    //Obtiene los archivos de la carpeta resources y las guarda en un array de tipo File
    public static File[] obtenerArchivos(){
        File carpeta = new File("resources");
        File [] archivos = carpeta.listFiles();
        return archivos;
    }
    //Se obtiene el nombre del archivo sin su extensión
    public static String obtenerNombreSinFormato(File file){
        String nombreArchivo = file.getName();
        int lugarPunto = nombreArchivo.lastIndexOf(".");
        if(lugarPunto>0 && lugarPunto < nombreArchivo.length() -1){
            nombreArchivo = nombreArchivo.substring(0, lugarPunto);
        }
        return  nombreArchivo;
    }
    //En este método, se guarda en contenido binario del archivo en un objeto de tipo byte[] y se retorna
    public static byte[] leerContenido(File archivo){

        try {
            byte[] data = Files.readAllBytes(archivo.toPath());
            return data;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
            return null;
        }
    }
}
