package mx.samas.exception;

/**
 * Excepcion lanzada cuando existe un error de conexion entre un cluster de
 * ElasticSearch y SAMAS
 *
 * @author samas
 */
public class ElasticException extends RuntimeException {

    /**
     * @param msg El mensaje para describir la causa de la Excepcion
     */
    public ElasticException(String msg) {
        super(msg);
    }
}
