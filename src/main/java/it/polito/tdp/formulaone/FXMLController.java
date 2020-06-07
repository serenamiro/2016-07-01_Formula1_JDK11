package it.polito.tdp.formulaone;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.formulaone.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

//controller del turno C --> switchare al branch master_turnoA o master_turnoB per turno A o B

public class FXMLController {
	
	private Model model;

	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<?> boxCostruttori;

    @FXML
    private TextField textInputK;

    @FXML
    private TextArea txtResult;

    @FXML
    void doCreaGrafo(ActionEvent event) {

    }

    @FXML
    void doTrovaDreamTeam(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert boxCostruttori != null : "fx:id=\"boxCostruttori\" was not injected: check your FXML file 'FormulaOne.fxml'.";
        assert textInputK != null : "fx:id=\"textInputK\" was not injected: check your FXML file 'FormulaOne.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'FormulaOne.fxml'.";

    }
    
	public void setModel(Model model) {
		this.model = model;
	}
}
