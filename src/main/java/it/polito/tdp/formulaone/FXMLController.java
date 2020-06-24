package it.polito.tdp.formulaone;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.formulaone.model.Driver;
import it.polito.tdp.formulaone.model.Model;
import it.polito.tdp.formulaone.model.Season;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

//controller del turno A --> switchare al branch master_turnoB o master_turnoC per turno B o C

public class FXMLController {
	
	private Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Season> boxAnno;

    @FXML
    private TextField textInputK;

    @FXML
    private TextArea txtResult;

    @FXML
    void doCreaGrafo(ActionEvent event) {
    	txtResult.clear();
    	try {
        	Season sea = boxAnno.getValue();
        	if(sea == null) {
        		txtResult.appendText("Selezionare una stagione per proseguire.\n");
        		return;
        	}
        	model.creaGrafo(sea);
        	txtResult.appendText("GRAFO CREATO\n");
    		txtResult.appendText("vertici: "+model.nVertici()+"\n");
    		txtResult.appendText("archi: "+model.nArchi()+"\n");
    		
    		txtResult.appendText("MIGLIOR PILOTA: "+model.getMigliorPilota());
    	} catch(RuntimeException e) {
    		e.printStackTrace();
    		System.out.println("Errore di connessione al DB.\n");
    	}
    	
    }

    @FXML
    void doTrovaDreamTeam(ActionEvent event) {
    	txtResult.clear();
    	try {
        	Season sea = boxAnno.getValue();
        	if(sea == null) {
        		txtResult.appendText("Selezionare una stagione per proseguire.\n");
        		return;
        	}
        	try {
        		int k = Integer.parseInt(textInputK.getText());
        		if(k<=0) {
        			txtResult.setText("Inserire k >= 0.\n");
        			return;
        		}
        		List<Driver> drivers = model.getDreamTeam(k);
        		txtResult.setText(drivers.toString());
        	} catch(NumberFormatException e) {
        		txtResult.setText("Inserire k >= 0.\n");
        		return;
        	}
    	} catch(RuntimeException e) {
    		e.printStackTrace();
    		System.out.println("Errore di connessione al DB.\n");
    	}
    	
    }

    @FXML
    void initialize() {
        assert boxAnno != null : "fx:id=\"boxAnno\" was not injected: check your FXML file 'FormulaOne.fxml'.";
        assert textInputK != null : "fx:id=\"textInputK\" was not injected: check your FXML file 'FormulaOne.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'FormulaOne.fxml'.";

    }

	public void setModel(Model model) {
		this.model = model;
		boxAnno.getItems().addAll(model.getSeasons());
	}
}
