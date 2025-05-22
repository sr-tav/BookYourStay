package co.edu.uniquindio.bookyourstay.modelo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


@Setter
@Builder
@AllArgsConstructor
public class Administrador extends Usuario implements Serializable {

    public Administrador(String nombre, String cedula, String telefono, String email, String contrasenia, String codigoActivacion, String codigoRecuperacion, Boolean estadoCuenta) {
        super(nombre, cedula, telefono, email, contrasenia, codigoActivacion, codigoRecuperacion, estadoCuenta);
    }

}
