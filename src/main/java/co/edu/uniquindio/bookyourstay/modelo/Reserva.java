package co.edu.uniquindio.bookyourstay.modelo;

import co.edu.uniquindio.bookyourstay.modelo.factory.Alojamiento;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class Reserva {

    String id;
    Cliente cliente;
    Alojamiento alojamiento;
    LocalDate fechaInicio, fechaFin;
    int numHuespedes;
    Factura factura;
}
