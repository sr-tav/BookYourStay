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
public class Resenia {

    Cliente cliente;
    Alojamiento alojamiento;
    String comentario;
    LocalDate fecha;
    int calificacion;

}
