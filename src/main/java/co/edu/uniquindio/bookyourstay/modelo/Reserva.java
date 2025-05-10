package co.edu.uniquindio.bookyourstay.modelo;

import co.edu.uniquindio.bookyourstay.modelo.factory.Alojamiento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class Reserva {

    Cliente cliente;
    Alojamiento alojamiento;
    LocalDate fechaInicio, fechaFin;
    int numHuespedes;
    Factura factura;
}
