/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TrabajoGrupo3;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 *
 * @author Carlos
 */
public class iTunes {

    /*
     codigo.itn
     int codCancion
     int codDownload
    
     ----------------
     songs.itn
     int codigo
     String cancion
     String artista
     double precio
     int estrellas
     int reviews
    
     ----------------
     downloads.itn
     int codDown
     long fechaDescarga
     int codCancionDescargada
     String cliente
     double precio
     */
    
    public static final String ROOT = "iTunesApp";
    
    public void Itunes() throws IOException{
        RandomAccessFile icodigos = new RandomAccessFile(ROOT+ "/codigo.itn", "rw");
        RandomAccessFile isongs = new RandomAccessFile(ROOT+ "/songs.itn", "rw");
        RandomAccessFile idownloads = new RandomAccessFile(ROOT+ "/codigo.itn", "rw");
                      
        if(icodigos.length() == 0){
            icodigos.writeInt(1);
            icodigos.writeInt(1);        
        }       
        
    }
    
    public void addSong(String nombre, String cantante, double precio) throws IOException{
        RandomAccessFile isongs = new RandomAccessFile(ROOT+ "/songs.itn", "rw");
        isongs.writeInt(getCodigo(0));
        isongs.writeUTF(nombre);
        isongs.writeUTF(cantante);
        isongs.writeDouble(precio);
        isongs.writeInt(0);
        isongs.writeInt(0);
    }
    
    public void reviewSong(int code, int stars) throws IOException{
        RandomAccessFile isongs = new RandomAccessFile(ROOT+ "/songs.itn", "rw");
        isongs.seek(0);
        
        while(isongs.getFilePointer() < isongs.length()){
            if(isongs.getCodigo(0) == code){
                if(stars < 0 && stars > 5)
                    throw new IllegalArgumentException("La cantidad de estrellas no es aceptada.");
                isongs.writeInt(isongs.getCodigo(4)+1);
            }
            isongs.readInt();
        }
    }
    
    public void songs(String txtFile) throws IOException{
        
    }
}
