package mx.samas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**Este clase inicializa la aplicacion de Spring y la configura para poder hacer uso de ella.
 * 
 * @author samas
 */
@SpringBootApplication
@EnableJpaRepositories(basePackages = "mx.samas.repository")
@EnableElasticsearchRepositories(basePackages = "mx.samas.elastic.repository")
public class WebApplication extends SpringBootServletInitializer {
    
    /** Corre la aplicacion de Spring
     * @param args. Arreglo de cadenas vacio. */
    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(WebApplication.class);
    }
}
