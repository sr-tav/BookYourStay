package co.edu.uniquindio.bookyourstay.modelo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class Factura {

    String id;
    LocalDate fecha;
    float subtotal, total;


}
