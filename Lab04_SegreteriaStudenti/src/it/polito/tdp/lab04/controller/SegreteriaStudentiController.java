package it.polito.tdp.lab04.controller;

import java.net.URL;
import java.util.*;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SegreteriaStudentiController {

	private Model model=new Model();
	private CorsoDAO c=new CorsoDAO();
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Corso> btnCombo;

    @FXML
    private Button btnCercaIscritti;

    @FXML
    private TextField txtMatricola;

    @FXML
    private Button btnCollegaStudente;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtCognome;

    @FXML
    private Button btnCercaCorsi;

    @FXML
    private Button btnIscrivi;

    @FXML
    private TextArea txtResult;

    @FXML
    private Button btnReset;

    @FXML
    void doGetStudent(ActionEvent event) {
    	try {
    	String res=model.riconosciMatricola(Integer.parseInt(txtMatricola.getText()));
    	String array[]=res.trim().split("/");
    	txtNome.appendText(array[0]);
    	txtCognome.appendText(array[1]);
    	}
    	catch(NumberFormatException nfe) {
        	txtResult.appendText("Formato non valido\n");
        }
    	catch(NullPointerException npe) {
    		txtResult.appendText("Inserire una selezione\n");
    	}
    	}

    @FXML
    void doRegistration(ActionEvent event) {
    	try {
    	if(model.iscrizione(Integer.parseInt(txtMatricola.getText()), btnCombo.getValue().getCodins())==true) {
    		txtResult.appendText("Studente già iscritto al corso\n");
    	}
    	else {
    		txtResult.appendText("Studente iscritto al corso\n");
    	}
    	}
    	catch(NumberFormatException nfe) {
        	txtResult.appendText("Formato non valido\n");
        }
    	catch(NullPointerException npe) {
    		txtResult.appendText("Inserire una selezione\n");
    	}
    }

    @FXML
    void doReset(ActionEvent event) {
    	this.txtCognome.clear();
    	this.txtMatricola.clear();
    	this.txtNome.clear();
    	this.txtResult.clear();
    	this.btnCombo.getSelectionModel().clearSelection();
    }

    @FXML
    void doSearch(ActionEvent event) {
    try {
    if(btnCombo.getValue().getNome().equals("")) {	
    	if(model.corsiIscritti(Integer.parseInt(txtMatricola.getText())).equals(null)) {
    		txtResult.appendText("Matricola non presente nel database\n");
    	}
    	else {
    		for(Corso cc:model.corsiIscritti(Integer.parseInt(txtMatricola.getText())))
    		txtResult.appendText(cc.toString()+"\n");
    	}
    }
    else {
    	if(model.eIscritto(Integer.parseInt(txtMatricola.getText()), btnCombo.getValue().getCodins() )==false) {
    		txtResult.appendText("Lo studente non è iscritto al corso\n");
    	}
    	else {
    		txtResult.appendText("Lo studente è iscritto al corso\n");
    	}
    }
    }
    catch(NumberFormatException nfe) {
    	txtResult.appendText("Formato non valido\n");
    }
    catch(NullPointerException npe) {
		txtResult.appendText("Inserire una selezione\n");
	}
    }

    @FXML
    void doSearchStudents(ActionEvent event) {
    	try {
    	for(Studente ss: model.studentiIscrittiCorso(btnCombo.getValue().getNome())) {
    		txtResult.appendText(ss.toString()+"\n");
    	}
    	}
    	catch(NullPointerException npe) {
    		txtResult.appendText("Inserire una selezione\n");
    	}
    }

    @FXML
    void initialize() {
        assert btnCombo != null : "fx:id=\"btnCombo\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnCercaIscritti != null : "fx:id=\"btnCercaIscritti\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnCollegaStudente != null : "fx:id=\"btnCollegaStudente\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnCercaCorsi != null : "fx:id=\"btnCercaCorsi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnIscrivi != null : "fx:id=\"btnIscrivi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";

    }

    public void setModel(Model model) {
    	this.model=model;
    	btnCombo.getItems().addAll(c.getTuttiICorsi());	   	
    	Corso cc = new Corso("",0,"",0);
    	btnCombo.getItems().addAll(cc);
    }
}
