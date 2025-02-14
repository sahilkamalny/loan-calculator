package org.example.module3homework;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

// Necessary for button alignment
import javafx.geometry.HPos;

public class HelloApplication extends Application {
    
    // Declares text fields and buttons
    private TextField annualInterestRateField = new TextField();
    private TextField numberOfYearsField = new TextField();
    private TextField loanAmountField = new TextField();
    private TextField monthlyPaymentField = new TextField();
    private TextField totalPaymentField = new TextField();
    private Button calculateButton = new Button("Calculate");

    @Override
    public void start(Stage primaryStage) {

        // Creates the grid pane
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setHgap(5);
        gridPane.setVgap(5);

        // Sets the alignment for all text fields to the right
        annualInterestRateField.setAlignment(Pos.CENTER_RIGHT);
        numberOfYearsField.setAlignment(Pos.CENTER_RIGHT);
        loanAmountField.setAlignment(Pos.CENTER_RIGHT);
        monthlyPaymentField.setAlignment(Pos.CENTER_RIGHT);
        totalPaymentField.setAlignment(Pos.CENTER_RIGHT);

        // Adds the UI elements to the grid pane
        gridPane.add(new Label("Annual Interest Rate:"), 0, 0);
        gridPane.add(annualInterestRateField, 1, 0);

        gridPane.add(new Label("Number of Years:"), 0, 1);
        gridPane.add(numberOfYearsField, 1, 1);

        gridPane.add(new Label("Loan Amount:"), 0, 2);
        gridPane.add(loanAmountField, 1, 2);

        gridPane.add(new Label("Monthly Payment:"), 0, 3);
        gridPane.add(monthlyPaymentField, 1, 3);

        gridPane.add(new Label("Total Payment:"), 0, 4);
        gridPane.add(totalPaymentField, 1, 4);

        gridPane.add(calculateButton, 1, 5);

        // Sets the alignment for the Calculate button to the right
        GridPane.setHalignment(calculateButton, HPos.RIGHT);

        // Makes the last two text fields un-editable
        monthlyPaymentField.setEditable(false);
        totalPaymentField.setEditable(false);

        // Sets the column widths
        annualInterestRateField.setPrefColumnCount(15);
        numberOfYearsField.setPrefColumnCount(15);
        loanAmountField.setPrefColumnCount(15);
        monthlyPaymentField.setPrefColumnCount(15);
        totalPaymentField.setPrefColumnCount(15);

        // Handles the button click action
        calculateButton.setOnAction(e -> calculateLoanPayment());

        // Creates the scene and shows it in the stage
        Scene scene = new Scene(gridPane);
        primaryStage.setTitle("LoanCalculator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void calculateLoanPayment() {
        try {

            // Gets the values from the text fields
            double annualInterestRate = Double.parseDouble(annualInterestRateField.getText());
            double numberOfYears = Double.parseDouble(numberOfYearsField.getText());
            double loanAmount = Double.parseDouble(loanAmountField.getText());

            // Calculates the monthly interest rate
            double monthlyInterestRate = annualInterestRate / 1200;

            // Calculates the monthly payment
            double monthlyPayment = loanAmount * monthlyInterestRate / (1 - 1 / Math.pow(1 + monthlyInterestRate, numberOfYears * 12));

            // Calculates the total payment
            double totalPayment = monthlyPayment * numberOfYears * 12;

            // Displays the results with 2 decimal places included
            monthlyPaymentField.setText(String.format("$%.2f", monthlyPayment));
            totalPaymentField.setText(String.format("$%.2f", totalPayment));

        } catch (NumberFormatException ex) {

            // Displays message if inout is invalid
            monthlyPaymentField.setText("Invalid Input!");
            totalPaymentField.setText("Invalid Input!");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}