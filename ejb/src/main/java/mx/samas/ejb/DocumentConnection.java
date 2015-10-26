/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.ejb;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author neftaly
 */
public class DocumentConnection {

    //private final FTPConnection pip;
    private List<String> routesPIP;
    public List<String> filesToUpload = new LinkedList<>();

    public DocumentConnection() {
        System.out.println("Comenzamos a Listar todos los CSV");
        //TBD  pip = new FTPConnection();
    }

    public List<String> listAllFiles() {
        List<String> a = new LinkedList<>();
        String directoryPath = "/home/neftaly/VectorAnalitico";
        File[] filesInDirectory = new File(directoryPath).listFiles();

        for (File f : filesInDirectory) {
            String filePath = f.getAbsolutePath();
            String fileExtenstion = filePath.substring(filePath.lastIndexOf(".") + 1, filePath.length());
            if ("csv".equals(fileExtenstion)) {
                /*
                 try {
                 String content = FileUtils.readFileToString(f, "ISO8859_1");
                 FileUtils.write(f, content, "UTF-8");
                 } catch (IOException ex) {
                 Logger.getLogger(DocumentConnection.class.getName()).log(Level.SEVERE, null, ex);
                 }
                 */
                a.add(filePath);

            }
        }
        return a;
    }

    public List<String> listDatesFromAllFiles() {
        List<String> a = new LinkedList<>();
        String directoryPath = "/home/neftaly/VectorAnalitico";
        File[] filesInDirectory = new File(directoryPath).listFiles();
        for (File f : filesInDirectory) {
            String filePath = f.getAbsolutePath();
            String fileName = f.getName();
            String fileExtenstion = filePath.substring(filePath.lastIndexOf(".") + 1, filePath.length());
            if ("csv".equals(fileExtenstion)) {
                fileName = fileName.replaceAll("[^\\d]", "");
                a.add(fileName);
            }
        }
        return a;
    }

    //Obtengo la lista de archivos de PIP y las comparo con las del 
    //filesystem para descargar y descomprimir en 
    //TODO
    /*
     public void downloadFromPIP() {
     routesPIP = pip.getFiles();
     filesToUpload = listAllFiles();
     for (String ftp : routesPIP) {
     for (String comp : filesToUpload) {
     if (comp.contains(ftp)) {

     }
     }
     }
     }
     */
}
