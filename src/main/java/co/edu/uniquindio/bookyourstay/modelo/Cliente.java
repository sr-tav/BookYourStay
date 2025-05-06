package co.edu.uniquindio.bookyourstay.modelo;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Cliente extends Usuario{

    BilleteraVirtual billeteraVirtual;

    public Cliente(String nombre, String cedula, String telefono, String email, String contraseña, Boolean estadoCuenta) {
        super(nombre, cedula, telefono, email, contraseña, estadoCuenta);
    }


}
