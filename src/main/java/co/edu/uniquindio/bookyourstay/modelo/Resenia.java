package co.edu.uniquindio.bookyourstay.modelo;

import co.edu.uniquindio.bookyourstay.modelo.factory.Alojamiento;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class Resenia implements Serializable {

    Cliente cliente;
    Alojamiento alojamiento;
    String comentario;
    LocalDate fecha;
    int calificacion;

}
