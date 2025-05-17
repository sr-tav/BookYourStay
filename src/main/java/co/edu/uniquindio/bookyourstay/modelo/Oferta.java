package co.edu.uniquindio.bookyourstay.modelo;

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
