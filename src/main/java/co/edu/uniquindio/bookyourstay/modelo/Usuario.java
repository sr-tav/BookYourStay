package co.edu.uniquindio.bookyourstay.modelo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public abstract class Usuario {

    private String nombre, cedula, telefono, email, contrasenia, codigoActivacion;
    private Boolean estadoCuenta;

}
