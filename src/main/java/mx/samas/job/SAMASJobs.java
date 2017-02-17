package mx.samas.job;

/**
 * Enumeracion para leer Jobs implementados en SAMAS
 *
 * @author samas
 */
public enum SAMASJobs {

    VALUACION_VECTOR {
        @Override
        public String toString() {
            return "valuacionJob";
        }
    },
    BOOTSTRAP_ACTIVO {
        @Override
        public String toString() {
            return "agregarActivosJob";
        }
    },
    POSICION_PORTAFOLIO {
        @Override
        public String toString() {
            return "sumatoriaPosicionJob";
        }
    },
    DEVENGOS_PORTAFOLIO {
        @Override
        public String toString() {
            return "devengosJob";
        }
    },
    ELASTIC_BOOTSTRAP {
        @Override
        public String toString() {
            return "elasticJob";
        }
    },

}
