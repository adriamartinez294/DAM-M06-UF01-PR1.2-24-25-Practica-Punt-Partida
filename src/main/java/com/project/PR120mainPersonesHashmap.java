package com.project;

import java.io.*;
import java.util.HashMap;

import com.project.excepcions.IOFitxerExcepcio;

public class PR120mainPersonesHashmap {
    private static String filePath = System.getProperty("user.dir") + "/data/PR120persones.dat";

    public static void main(String[] args) throws FileNotFoundException, IOException {
        HashMap<String, Integer> persones = new HashMap<>();
        persones.put("Anna", 25);
        persones.put("Bernat", 30);
        persones.put("Carla", 22);
        persones.put("David", 35);
        persones.put("Elena", 28);

        try {
            escriurePersones(persones);
            llegirPersones();
        } catch (IOFitxerExcepcio e) {
            System.err.println("Error en treballar amb el fitxer: " + e.getMessage());
        }
    }

    // Getter per a filePath
    public static String getFilePath() {
        return filePath;
    }

    // Setter per a filePath
    public static void setFilePath(String newFilePath) {
        filePath = newFilePath;
    }

    // Mètode per escriure les persones al fitxer
    public static void escriurePersones(HashMap<String, Integer> persones) throws IOFitxerExcepcio {
       // *************** CODI PRÀCTICA **********************/

            try (FileOutputStream fos = new FileOutputStream(getFilePath());
             DataOutputStream dos = new DataOutputStream(fos)) {

                for (HashMap.Entry<String, Integer> entry : persones.entrySet()) {
                    String nom = entry.getKey();
                    Integer edad = entry.getValue();

                    dos.writeUTF(nom);
                    dos.writeInt(edad);

                    dos.flush();
   
            }
       } catch (IOException e) {
            throw new IOFitxerExcepcio("Error en escriure les persones al fitxer");
       }
    }

    // Mètode per llegir les persones des del fitxer
    public static void llegirPersones() throws IOFitxerExcepcio {
        // *************** CODI PRÀCTICA **********************/
        HashMap<String, Integer> persones = new HashMap<>();

        try (FileInputStream fis = new FileInputStream(getFilePath());
             DataInputStream dis = new DataInputStream(fis)) {
                while (true){
                    try {
                        String key = dis.readUTF();
                        int value = dis.readInt();
                        persones.put(key, value);
    
                    } catch (EOFException eof) {
                        break;
                    }
                }
                for (HashMap.Entry<String, Integer> entry : persones.entrySet()) {
                    System.out.println(entry.getKey() + ": " + entry.getValue() + " anys");
                }
            } catch (IOException e) {
                throw new IOFitxerExcepcio("Error en llegir les persones del fitxer");
            }
    }
}
