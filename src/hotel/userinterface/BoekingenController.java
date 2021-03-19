package hotel.userinterface;

import hotel.model.Hotel;
import hotel.model.KamerType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.time.LocalDate;

public class BoekingenController {

    @FXML public Label gegevensLabel;
    @FXML public DatePicker aankomstDatePicker;
    @FXML public DatePicker vertrekDatePicker;
    @FXML public ComboBox kamerTypeComboBox;
    @FXML public TextField naamField;
    @FXML public TextField adresField;

    public void initialize() {
        aankomstDatePicker.setValue(LocalDate.now());
        vertrekDatePicker.setValue(LocalDate.now().plusDays(1));

        for (KamerType kamerType : Hotel.getHotel().getKamerTypen()) {
            kamerTypeComboBox.getItems().add(kamerType);
        }
    }

    public void handleButtonBoek(ActionEvent actionEvent) throws Exception{
        if (naamField != null && adresField != null && kamerTypeComboBox.getValue() != null && (aankomstDatePicker.getValue().isAfter(LocalDate.now()) || aankomstDatePicker.getValue().isEqual(LocalDate.now())) && (vertrekDatePicker.getValue().isAfter(LocalDate.now()) || vertrekDatePicker.getValue().isEqual(LocalDate.now()))) {
            String naamInvoer = naamField.getText();
            String adresInvoer = adresField.getText();
            LocalDate aankomstDatum = aankomstDatePicker.getValue();
            LocalDate vertrekDatum = vertrekDatePicker.getValue();
            KamerType kT = (KamerType) kamerTypeComboBox.getSelectionModel().getSelectedItem();

            Hotel.getHotel().voegBoekingToe(aankomstDatum, vertrekDatum, naamInvoer, adresInvoer, kT);

            handleButtonReset();
        }
        else {
            gegevensLabel.setText("Er zijn velden leeg gelaten of verkeerd ingevuld!");
            gegevensLabel.setTextFill(Color.rgb(210, 39, 30)); //rood
        }

        Button source = (Button)actionEvent.getSource();
        Stage stage = (Stage)source.getScene().getWindow();
        stage.close();
    }

    public void handleButtonReset() {
        naamField.setText(null);
        adresField.setText(null);
        aankomstDatePicker.setValue(LocalDate.now());
        vertrekDatePicker.setValue(LocalDate.now().plusDays(1));
        kamerTypeComboBox.setValue(null);
    }
}
