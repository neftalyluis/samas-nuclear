/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.ejb.timers;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author neftaly
 */
public class DocumentConnection {

    public List<String> filesToUpload = new LinkedList<>();

    public DocumentConnection() {
    }

    public List<String> listAllCSVFiles() {
        List<String> a = new LinkedList<>();
        String directoryPath = "/home/neftaly/VectorAnalitico";
        File[] filesInDirectory = new File(directoryPath).listFiles();
        for (File f : filesInDirectory) {
            String filePath = f.getAbsolutePath();
            String fileExtenstion = filePath.substring(filePath.lastIndexOf(".") + 1, filePath.length());
            if ("csv".equals(fileExtenstion)) {
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

}