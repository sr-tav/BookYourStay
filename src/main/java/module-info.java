module co.edu.uniquindio.bookyourstay {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires org.simplejavamail.core;
    requires org.simplejavamail;
    requires java.desktop;
    requires java.sql.rowset;


    opens co.edu.uniquindio.bookyourstay.controladores to javafx.fxml;
    exports co.edu.uniquindio.bookyourstay;
}