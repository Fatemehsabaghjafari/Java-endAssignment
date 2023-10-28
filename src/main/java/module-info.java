module com.example.fatemesabagh697797endassignment {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    opens com.example.fatemesabagh697797endassignment to javafx.fxml;
    opens com.example.fatemesabagh697797endassignment.Controllers to javafx.fxml;
    exports com.example.fatemesabagh697797endassignment.Controllers;

    exports com.example.fatemesabagh697797endassignment;
    exports com.example.fatemesabagh697797endassignment.Data;
    opens com.example.fatemesabagh697797endassignment.Data to javafx.fxml;
    exports com.example.fatemesabagh697797endassignment.Model;
    opens com.example.fatemesabagh697797endassignment.Model to javafx.base, javafx.fxml;
}