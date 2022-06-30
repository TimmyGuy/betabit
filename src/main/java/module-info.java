module com.example.betabit {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.betabit to javafx.fxml;
    exports com.example.betabit;
    exports com.example.betabit.user;
    exports com.example.betabit.container;
    exports com.example.betabit.transport;
    exports com.example.betabit.log;
    exports com.example.betabit.store;
}