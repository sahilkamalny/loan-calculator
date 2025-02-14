module org.example.module3homework {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.module3homework to javafx.fxml;
    exports org.example.module3homework;
}