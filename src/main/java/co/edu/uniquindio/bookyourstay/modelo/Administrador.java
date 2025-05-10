package co.edu.uniquindio.bookyourstay.modelo;

import lombok.Builder;
import lombok.Setter;

@Setter
@Builder
public class Administrador extends Usuario{

    public Administrador(String nombre, String cedula, String telefono, String email, String contrasenia, Boolean estadoCuenta) {
        super(nombre, cedula, telefono, email, contrasenia, estadoCuenta);
    }

}
