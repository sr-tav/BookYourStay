package co.edu.uniquindio.bookyourstay.modelo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public abstract class Usuario implements Serializable {

    private String nombre, cedula, telefono, email, contrasenia, codigoActivacion, codigoRecuperacion;
    private Boolean estadoCuenta;

}
