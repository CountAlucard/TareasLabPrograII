/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TrabajoGrupo3;

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

    public void Itunes(){
        try {
            RandomAccessFile icodigos = new RandomAccessFile(ROOT + "/codigo.itn", "rw");
            RandomAccessFile isongs = new RandomAccessFile(ROOT + "/songs.itn", "rw");
            RandomAccessFile idownloads = new RandomAccessFile(ROOT + "/codigo.itn", "rw");

            if (icodigos.length() == 0) {
                icodigos.writeInt(1);
                icodigos.writeInt(1);
            }
        }catch(IOException e){
            System.out.println(e.getMessage());
        }

    }

    public int getCodigo(long offset) throws IOException {
        RandomAccessFile icodigos = new RandomAccessFile(ROOT + "/codigo.itn", "rw");
        icodigos.seek(0);

        if (offset == 0) {
            int cancion = icodigos.readInt();
            icodigos.seek(offset);
            icodigos.writeInt(cancion + 1);
            return cancion;
        }

        if (offset == 4) {

            icodigos.seek(offset);
            int download = icodigos.readInt();
            icodigos.seek(offset);
            icodigos.writeInt(download + 1);
            return download;

        }

        return -1;

    }

    public void addSong(String nombre, String cantante, double precio) throws IOException {
        RandomAccessFile isongs = new RandomAccessFile(ROOT + "/songs.itn", "rw");
        isongs.writeInt(getCodigo(0));
        isongs.writeUTF(nombre);
        isongs.writeUTF(cantante);
        isongs.writeDouble(precio);
        isongs.writeInt(0);
        isongs.writeInt(0);
    }

    public void reviewSong(int code, int stars) throws IOException {
        RandomAccessFile isongs = new RandomAccessFile(ROOT + "/songs.itn", "rw");
        isongs.seek(0);

        while (isongs.getFilePointer() < isongs.length()) {
            if (getCodigo(0) == code) {
                if (stars < 0 && stars > 5) {
                    throw new IllegalArgumentException("La cantidad de estrellas no es aceptada.");
                }
                isongs.writeInt(getCodigo(4) + 1);
            }
            isongs.readInt();
        }
    }

    public void songs(String txtFile) throws IOException {
        RandomAccessFile report = new RandomAccessFile(ROOT + "/" + txtFile, "rw");
        RandomAccessFile isongs = new RandomAccessFile(ROOT + "/songs.itn", "rw");
        report.seek(0);
        isongs.seek(0);

        while (isongs.getFilePointer() < isongs.length()) {
            int code = isongs.readInt();
            String name = isongs.readUTF();
            String artist = isongs.readUTF();
            double price = isongs.readDouble();
            double rating = isongs.readInt();
            isongs.readInt();

            report.writeInt(code);
            report.writeUTF(name);
            report.writeUTF(artist);
            report.writeDouble(price);
            report.writeDouble(rating);
        }
        
        report.seek(0);
        
        while(report.getFilePointer() < report.length()){
            int cod = report.readInt();
            String nm = report.readUTF();
            String art = report.readUTF();
            double prc = report.readDouble();
            double stars = report.readDouble();
            
            System.out.println("Codigo: "+cod+" Cancion: "+nm+" Artista: "+art+" Precio: "+prc+" Rating: "+stars+"\n");
        }

    }
}
