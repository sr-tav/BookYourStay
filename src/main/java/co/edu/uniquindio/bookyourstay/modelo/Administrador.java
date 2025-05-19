package co.edu.uniquindio.bookyourstay.modelo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Administrador extends Usuario{

    public Administrador(String nombre, String cedula, String telefono, String email, String contrasenia, String codigoActivacion, String codigoRecuperacion, Boolean estadoCuenta) {
        super(nombre, cedula, telefono, email, contrasenia, codigoActivacion, codigoRecuperacion, estadoCuenta);
    }

    public static AdministradorBuilder<?, ?> builder() {
        return new AdministradorBuilderImpl();
    }

    // Clase interna necesaria
    private static final class AdministradorBuilderImpl extends AdministradorBuilder<Administrador, AdministradorBuilderImpl> {
        public Administrador build() {
            return new Administrador(this);
        }
    }

}
