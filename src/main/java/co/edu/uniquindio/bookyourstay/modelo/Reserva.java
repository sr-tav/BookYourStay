package co.edu.uniquindio.bookyourstay.modelo;

import co.edu.uniquindio.bookyourstay.modelo.factory.Alojamiento;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class Reserva implements Serializable {

    String id;
    Cliente cliente;
    Alojamiento alojamiento;
    LocalDate fechaInicio, fechaFin;
    int numHuespedes;
    Factura factura;
}
