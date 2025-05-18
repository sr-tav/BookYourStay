package co.edu.uniquindio.bookyourstay.modelo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class EstadisticaAlojamiento {
    private String idAlojamiento;
    private double porcentajeOcupacion;
    private double ingresosTotales;


    @AllArgsConstructor
    @Getter
    @Setter
    public static class Estadisticas {
        private double porcentajeOcupacion;
        private double ingresosTotales;
    }
}
