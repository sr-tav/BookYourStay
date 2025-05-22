package co.edu.uniquindio.bookyourstay.modelo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.sql.rowset.serial.SerialArray;
import java.io.Serializable;

@AllArgsConstructor
@Setter
@Getter
public class BilleteraVirtual implements Serializable {

    private float saldo;

}
