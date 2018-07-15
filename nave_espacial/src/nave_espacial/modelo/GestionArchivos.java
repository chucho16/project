/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nave_espacial.modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lenovo
 */
public class GestionArchivos {
    static String ruta = "src\\nave_espacial\\archivos\\";

    public static LinkedList<String> leerArchivo(String ruta) {
        try {
            LinkedList<String> lista = new LinkedList<>();
            String linea;
            FileReader fr = new FileReader(ruta);
            BufferedReader br = new BufferedReader(fr);
            while ((linea = br.readLine()) != null) {
                lista.add(linea);
            }

            br.close();
            fr.close();
            return lista;
        } catch (Exception ex) {
            Logger.getLogger(GestionArchivos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static boolean guardarArchivo(LinkedList<String> lineas, String nArchivo) {
        File archivo = new File(ruta+nArchivo);
        BufferedWriter bw;
        try {
            bw = new BufferedWriter(new FileWriter(archivo));
            for (String linea: lineas) {
                bw.write(linea+"\n");
            }
            bw.close();
        } catch (Exception e) {
            System.err.println("Error " + e);
        }
        return false;
    }
}
