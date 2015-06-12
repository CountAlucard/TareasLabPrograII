/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TrabajoGrupo3;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Calendar;
import java.util.Date;

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

    public void Itunes() {
        try {
            RandomAccessFile icodigos = new RandomAccessFile(ROOT + "/codigo.itn", "rw");
            RandomAccessFile isongs = new RandomAccessFile(ROOT + "/songs.itn", "rw");
            RandomAccessFile idownloads = new RandomAccessFile(ROOT + "/downloads.itn", "rw");

            if (icodigos.length() == 0) {
                icodigos.writeInt(1);
                icodigos.writeInt(1);
            }
        } catch (IOException e) {
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

    public void downloadSong(int codeSong, String cliente) throws IOException {

        RandomAccessFile isongs = new RandomAccessFile(ROOT + "/songs.itn", "rw");
        RandomAccessFile idownloads = new RandomAccessFile(ROOT + "/codigo.itn", "rw");
        Calendar c = Calendar.getInstance();
        int codigo = -1;
        Double Precio = -1.0;
        String nombreC = "";

        isongs.seek(0);

        while (isongs.getFilePointer() < isongs.length()) {
            if (codeSong == isongs.readInt()) {
                codigo = getCodigo(4);
            }
            nombreC = isongs.readUTF();
            isongs.readUTF();
            Precio = isongs.readDouble();
            isongs.readInt();
            isongs.readInt();

        }

        if (codigo != -1 && codigo != -1.0) {

            idownloads.seek(idownloads.length());
            long pos = idownloads.getFilePointer();
            int codigoDi = idownloads.read() + 1;
            idownloads.seek(pos);
            idownloads.writeInt(codigoDi);
            idownloads.writeLong(c.getTimeInMillis());
            idownloads.writeInt(codigo);
            idownloads.writeUTF(cliente);
            idownloads.writeDouble(Precio);

            System.out.println("GRACIAS" + cliente + "Por bajar" + nombreC + "a un costo de Lps." + Precio);

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

        while (report.getFilePointer() < report.length()) {
            int cod = report.readInt();
            String nm = report.readUTF();
            String art = report.readUTF();
            double prc = report.readDouble();
            double stars = report.readDouble();

            System.out.println("Codigo: " + cod + " Cancion: " + nm + " Artista: " + art + " Precio: " + prc + " Rating: " + stars + "\n");
        }

    }

    public void infoSong(int codeSong) throws IOException {
        RandomAccessFile isongs = new RandomAccessFile(ROOT + "/songs.itn", "rw");
        RandomAccessFile idownloads = new RandomAccessFile(ROOT + "/downloads.itn", "rw");

        int descargas = 0;
        boolean complete = false;

        isongs.seek(0);
        while (isongs.getFilePointer() < isongs.length()) {
            if (isongs.readInt() == codeSong) {
                System.out.println("Informacion de la Cancion---------------");
                isongs.seek(isongs.getFilePointer() - 4);
                int codigo = isongs.readInt();
                String name = isongs.readUTF();
                String artista = isongs.readUTF();
                double precio = isongs.readDouble();
                int estrellas = isongs.readInt();
                int reviews = isongs.readInt();

                System.out.println("Codigo: " + codigo + " Cancion: " + name + " Artista: " + artista
                        + " Precio: " + precio + " Estrellas: " + estrellas + " Reviews: " + reviews);

                complete = true;
            }
        }

        idownloads.seek(0);
        while (idownloads.getFilePointer() < idownloads.length()) {
            idownloads.readInt();
            Date fecha = new Date(idownloads.readLong());
            if (idownloads.readInt() == codeSong) {
                descargas += 1;
                System.out.println("Listado de Descargas--------------------");
                String cliente = idownloads.readUTF();
                double pcompra = idownloads.readDouble();

                System.out.println("Cliente: " + cliente + " Fecha de Descarga: " + fecha.toString() + " Costo de la Descarga: " + pcompra + "\n");
            }
        }

        if (complete) {
            System.out.println("Descargas Totales: " + descargas);
        }
    }
}
