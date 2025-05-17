package co.edu.uniquindio.bookyourstay.modelo;
import lombok.*;
import lombok.experimental.SuperBuilder;


@Getter
@Setter
@SuperBuilder
public class Cliente extends Usuario{

    private BilleteraVirtual billeteraVirtual;

    public Cliente(String nombre, String cedula, String telefono, String email, String contrasenia, String codigoActivacion, String codigoRecuperacion, Boolean estadoCuenta) {
        super(nombre, cedula, telefono, email, contrasenia, codigoActivacion, codigoRecuperacion, true);
        this.billeteraVirtual=new BilleteraVirtual(0);
    }


}
