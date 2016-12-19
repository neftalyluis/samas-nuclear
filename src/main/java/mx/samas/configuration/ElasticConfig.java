/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.configuration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import mx.samas.exception.ElasticException;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.node.NodeBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

/**Esta clase se encarga de la configuracion de Elasticsearch.
 * 
 * @author samas
 */
@Configuration
public class ElasticConfig {

    @Value("${elasticsearch.home:/home/samas/elasticsearch}")
    private String elasticsearchHome;

    private static final Logger LOG = Logger.getLogger(ElasticConfig.class.getName());

    /** @return  */
    @Bean
    public Client client() {
        try {
            final Path tmpDir = Files.createTempDirectory(Paths.get(System.getProperty("java.io.tmpdir")), "elasticsearch_data");
            LOG.log(Level.INFO, "Deploy en: {0}", tmpDir.toAbsolutePath().toString());

            final Settings.Builder elasticsearchSettings
                    = Settings.settingsBuilder().put("http.enabled", "true")
                    .put("path.data", tmpDir.toAbsolutePath().toString())
                    .put("path.home", elasticsearchHome);

            return new NodeBuilder()
                    .local(true)
                    .clusterName("SAMAS-Dyn")
                    .settings(elasticsearchSettings)
                    .node()
                    .client();

        } catch (final IOException ioex) {
            LOG.log(Level.WARNING, "No se pudo crear el directorio: {0}", ioex);
            throw new ElasticException(ioex.getMessage());
        }
    }

    /** @return . Regresa un nuevo modelo de Elasticsearch que contiene un cliente. */
    @Bean
    public ElasticsearchOperations elasticsearchTemplate() {
        return new ElasticsearchTemplate(client());
    }
}
