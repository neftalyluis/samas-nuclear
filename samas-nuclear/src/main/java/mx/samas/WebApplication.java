package mx.samas;

import mx.samas.domain.Activo;
import mx.samas.repository.ActivoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WebApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(WebApplication.class);
    }

    @Bean
    public CommandLineRunner initializeDb(final ActivoRepository activoRepository) {
        return new CommandLineRunner() {

            private static final String TICKER = "1A_ASDF_*";

            @Override
            public void run(String[] args) throws Exception {

                Activo test = activoRepository.findFirstByClavePizarra(TICKER);
                if (test == null) {
                    test.setNombre("ASDF");
                    test.setPujaMinima(45678.0);
                    test.setTipoValor("yrgfbhunj");
                    test.setSerie("urfbnf");
                    test.setVentaEnCorto(Boolean.TRUE);
                    test.setClavePizarra("1A_ASDF_*");
                    activoRepository.save(test);
                }

                System.out.println("I'm Ok");
            }
        };
    }
}
