package mx.samas.cliente;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author samas
 */
public class Launch {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            ObjectMapper mapper = new ObjectMapper();

            Activo student = new Activo();
            student.setClavePizarra("1A_134_qw");
            student.setIsin("KMUHIJMI");

            String resp = mapper.writeValueAsString(student);

            String credentials = Credentials.basic(Configuration.USER, Configuration.PASSWORD);
            OkHttpClient client = new OkHttpClient();
            RequestBody body = RequestBody.create(Configuration.JSON, resp);
            Request request = new Request.Builder()
                    .header("Authorization", credentials)
                    .url(Configuration.URL + "/activo")
                    .post(body)
                    .build();

            Response response = client.newCall(request).execute();
            System.out.println(response.body().string());
            System.out.println(response.code());
        } catch (IOException ex) {
            Logger.getLogger(Launch.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
