package mx.samas;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import java.util.logging.Logger;
import mx.samas.repository.ActivoRepository;
import mx.samas.repository.ClienteRepository;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class WebApplicationTests {

    private static final Logger LOG = Logger.getLogger(WebApplicationTests.class.getName());

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ActivoRepository activoRepository;

    @Test
    public void testBootstraping() {
        LOG.info("Probamos que Solo Existan 3 Clientes");
        Assert.assertEquals(3, clienteRepository.count());
    }
//
//    @Test
//    public void testActivoRepository() {
//        LOG.info("Probamos que Exista el Activo AMAZON usando una NamedQuery");
////        Assert.assertEquals(Boolean.TRUE, true);
//    }
}
