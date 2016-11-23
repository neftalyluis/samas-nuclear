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
import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.node.NodeBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

/**
 *
 * @author samas
 */
@Configuration
public class ElasticConfig {

    @Value("${elasticsearch.home:/home/samas/elasticsearch}")
    private String elasticsearchHome;

    private static final Logger logger = Logger.getLogger(ElasticConfig.class.getName());

    @Bean
    public Client client() {
        try {
            final Path tmpDir = Files.createTempDirectory(Paths.get(System.getProperty("java.io.tmpdir")), "elasticsearch_data");
            logger.log(Level.INFO, "Deploy en: {0}", tmpDir.toAbsolutePath().toString());

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
            logger.warning("Cannot create temp dir");
            throw new RuntimeException();
        }
    }

    @Bean
    public ElasticsearchOperations elasticsearchTemplate() {
        return new ElasticsearchTemplate(client());
    }
}
