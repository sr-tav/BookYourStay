package co.edu.uniquindio.bookyourstay.modelo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public abstract class Usuario {

    private String nombre, cedula, telefono, email, contrasenia, codigoActivacion, codigoRecuperacion;
    private Boolean estadoCuenta;

}
