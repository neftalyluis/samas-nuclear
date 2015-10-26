package mx.samas.ejb;

import javax.ejb.Remote;
import javax.ejb.Stateless;

/**
 *
 * @author jasonlee
 */
@Stateless
@Remote
public class DummyBean implements Dummy {

    @Override
    public String sayHello(String name) {
        return "Hello, " + name;
    }
}
