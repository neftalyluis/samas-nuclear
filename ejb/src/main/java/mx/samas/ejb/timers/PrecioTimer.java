/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.ejb.timers;

import javax.ejb.Schedule;
import javax.ejb.Stateless;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import org.apache.commons.io.FileUtils;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

/**
 *
 * @author neftaly
 */
@Stateless
public class PrecioTimer {

    String server = "ftp.piplatam.com";
    int port = 21;
    String user = "HSBCCB";
    String pass = "H2C4bolsa";
    FTPClient ftpClient;
    List<String> fileList;

    @Schedule(dayOfWeek = "Mon-Fri", month = "*", hour = "20", dayOfMonth = "*", year = "*", minute = "0", second = "0")
    public void doWork() {
        System.out.println("Checamos nuevos vectores :^]");
        try {
            initializeFTP();
            List<String> toDownload = compareLists(getLocalVectorList(), getRemoteVectorList());
            for (String route : toDownload) {
                if (downloadVector(route)) {
                    System.out.println(route + "descargado");
                    unZipIt(route, "/root/VectorAnalitico/");
                }
            }
        } catch (Exception e) {
            System.out.println("Error al descargar");
        } finally {
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            } catch (IOException ex) {
                System.out.println("Tenemos un pedo en la conexion FTP");
            }
        }

    }

    private void initializeFTP() {

        ftpClient = new FTPClient();
        try {

            ftpClient.connect(server, port);
            ftpClient.login(user, pass);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

        } catch (IOException ex) {
            System.out.println("Error al conectar: " + ex.getMessage());
        }

    }

    private boolean downloadVector(String route) throws Exception {

        File destination = new File("/root/VectorAnalitico/" + route);
        OutputStream stream = new BufferedOutputStream(new FileOutputStream(destination));
        boolean success = ftpClient.retrieveFile(route, stream);
        stream.close();

        return success;
    }

    private List<File> getLocalVectorList() {
        File dir = new File("/root/VectorAnalitico/");
        String[] extensions = new String[]{"csv"};
        List<File> files = (List<File>) FileUtils.listFiles(dir, extensions, true);
        return files;
    }

    private FTPFile[] getRemoteVectorList() {
        FTPFile[] remoteFiles = null;
        try {
            remoteFiles = ftpClient.listFiles();
        } catch (Exception e) {
            System.out.println("Error al listar los archivos");
        }
        return remoteFiles;
    }

    private List<String> compareLists(List<File> local, FTPFile[] remote) {
        List<String> toDownload = new LinkedList<>();
        for (FTPFile remoteFile : remote) {
            for (File localFile : local) {
                if (remoteFile.getName().equals(localFile.getName())) {
                    toDownload.add(remoteFile.getName());
                    System.out.println(remoteFile.getName() + " faltante");
                }
            }
        }
        return toDownload;
    }

    public void unZipIt(String zipFile, String outputFolder) {
        byte[] buffer = new byte[1024];
        try {

            File folder = new File(outputFolder);
            if (!folder.exists()) {
                folder.mkdir();
            }

            ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFile));
            ZipEntry ze = zis.getNextEntry();

            while (ze != null) {
                String fileName = ze.getName();
                File newFile = new File(outputFolder + File.separator + fileName);
                System.out.println("file unzip : " + newFile.getAbsoluteFile());
                new File(newFile.getParent()).mkdirs();
                FileOutputStream fos = new FileOutputStream(newFile);
                int len;
                while ((len = zis.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }
                fos.close();
                ze = zis.getNextEntry();
            }

            zis.closeEntry();
            zis.close();
            System.out.println("Done");

        } catch (IOException ex) {
            System.out.println("Oops!" + ex.getMessage());
        }
    }
}
