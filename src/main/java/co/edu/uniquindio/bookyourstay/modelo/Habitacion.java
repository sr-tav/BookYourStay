package co.edu.uniquindio.bookyourstay.modelo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@Getter
@Setter

public class Habitacion implements Serializable {
    String numero, descripcion;
    int precio;
    byte capacidad;

}
