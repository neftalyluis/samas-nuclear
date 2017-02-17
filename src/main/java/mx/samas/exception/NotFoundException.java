package mx.samas.exception;

/**
 * Excepcion que se lanza cuando no se encuentra determinado recurso usando JPA
 *
 * @author samas
 */
public class NotFoundException extends RuntimeException {

    /**
     * @param msg El mensaje de la causa de la Excepcion
     */
    public NotFoundException(String msg) {
        super(msg);
    }
}
