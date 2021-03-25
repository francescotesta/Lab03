
 package it.polito.tdp.spellchecker;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import model.Dictionary;
import model.RichWord;

public class FXMLController {
	
	Dictionary dizionario;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> boxLingua;

    @FXML
    private TextArea txtDaCorreggere;

    @FXML
    private Button spellCheckButton;

    @FXML
    private TextArea txtCorrretto;

    @FXML
    private Label lblErrori;

    @FXML
    private Button clearTextButton;

    @FXML
    private Label lblStato;

    @FXML
    void doClearText(ActionEvent event) {
    	txtCorrretto.clear();
    	txtDaCorreggere.clear();
    	boxLingua.setValue(null);
    	lblErrori.setText("");

    }

    @FXML
    void doSpellCheck(ActionEvent event) {
    	
    	txtCorrretto.clear();

    	if(boxLingua.getValue()==null) {
    		txtDaCorreggere.setText("Inserire una lingua");
    		return;
    	} 
    	
    	if(dizionario.loadDictionary(boxLingua.getValue())==false) {
    		txtDaCorreggere.setText("Errore nel caricamento del dizionario");
    	}
    	
    	String ricerca = txtDaCorreggere.getText();
    	ricerca.replaceAll("\n", " ");
    	ricerca.replaceAll("[.,\\/#!$%\\^&\\*;:{}=\\-_`~()\\[\\]]", "");
    	String[] campi = ricerca.split(" ");
    	
    	List<String> input = new ArrayList<>();
    	
    	for(String s:campi) {
    		input.add(s);
    	}
    	long start = System.nanoTime();
    	//List<RichWord> output = dizionario.spellCheckText(input);
    	List<RichWord> output = dizionario.spellCheckTextLinear(input);
    	long end = System.nanoTime();
    	
    	int numErrori=0;
    	for(RichWord r:output) {
    		if(r.isCorretto() == false) {
    			txtCorrretto.appendText(r.getParola()+" ");
    			numErrori++;
    		}
    	}
    	
    	lblErrori.setText("La frase contiene: "+numErrori + " errori");
    	lblStato.setText("risultato ottenuto in: " +(end-start));
    	
    }

    @FXML
    void initialize() {
        assert boxLingua != null : "fx:id=\"boxLingua\" was not injected: check your FXML file 'SceneLab03.fxml'.";
        assert txtDaCorreggere != null : "fx:id=\"txtDaCorreggere\" was not injected: check your FXML file 'SceneLab03.fxml'.";
        assert spellCheckButton != null : "fx:id=\"spellCheckButton\" was not injected: check your FXML file 'SceneLab03.fxml'.";
        assert txtCorrretto != null : "fx:id=\"txtCorrretto\" was not injected: check your FXML file 'SceneLab03.fxml'.";
        assert lblErrori != null : "fx:id=\"lblErrori\" was not injected: check your FXML file 'SceneLab03.fxml'.";
        assert clearTextButton != null : "fx:id=\"clearTextButton\" was not injected: check your FXML file 'SceneLab03.fxml'.";
        assert lblStato != null : "fx:id=\"lblStato\" was not injected: check your FXML file 'SceneLab03.fxml'.";

    }
    
    public void setModel(Dictionary model) {
    	this.dizionario= model;
    	boxLingua.getItems().addAll("Italian","English");
    }
    
}


