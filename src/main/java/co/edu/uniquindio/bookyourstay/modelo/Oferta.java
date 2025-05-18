package co.edu.uniquindio.bookyourstay.modelo;

import co.edu.uniquindio.bookyourstay.modelo.factory.Alojamiento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@AllArgsConstructor
@Getter
@Setter

public class Oferta {

    String id;
    Alojamiento alojamiento;
    LocalDate fechaInicio, fechaFin;
    float porcentaje;
}
