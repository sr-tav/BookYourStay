package co.edu.uniquindio.bookyourstay.modelo;

import co.edu.uniquindio.bookyourstay.util.QRUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
public class Factura {

    String id;
    LocalDate fecha;
    float subtotal, total;


}
