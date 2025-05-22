package co.edu.uniquindio.bookyourstay.modelo;

import co.edu.uniquindio.bookyourstay.modelo.factory.Alojamiento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
@AllArgsConstructor
@Getter
@Setter

public class Oferta implements Serializable {

    String id;
    Alojamiento alojamiento;
    LocalDate fechaInicio, fechaFin;
    float porcentaje;
}
