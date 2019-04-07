package it.polito.tdp.lab04.model;

import java.util.*;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {

	private List<Corso> corsi;
	private List<Studente> studenti;
	private CorsoDAO c;
	private StudenteDAO s;
	
	
	public Model(){
		
		corsi=new LinkedList<Corso>();
		studenti=new LinkedList<Studente>();
		c=new CorsoDAO();
		s=new StudenteDAO();
		corsi.clear();
		corsi.addAll(c.getTuttiICorsi());
		studenti.clear();
		studenti.addAll(s.getTuttiGliStudenti());
		
	
	}
	
	
    
	public String riconosciMatricola(int matricola) {
		for(Studente ss: studenti) {
			if(ss.getMatricola()== matricola) {
				return ss.getNome()+"/"+ss.getCognome() ;
			}
		}
		return "";
	}
	
	public List<Studente> studentiIscrittiCorso(String nomeCorso){
		for(Corso cc: corsi){
			if(cc.getNome().equals(nomeCorso)) {
				c.getStudentiIscrittiAlCorso(cc);
			    return cc.getStudentiIscritti();
			    }
		}
		 
	 return null;
	}
	public List<Corso> corsiIscritti(int matricola){
	
		for(Studente ss: studenti) {
			if(ss.getMatricola()==matricola) {
				s.getCorsiFrequentatiStudente(ss);
				return ss.getCorsiFrequentati();
			}
		}
		return null;
	}
	
	public boolean iscrizione(int matricola,String codins) {
		
		boolean iscritto= false;
		for(Studente ss: studenti) {
			if(ss.getMatricola()==matricola) {
				s.getCorsiFrequentatiStudente(ss);
			for(Corso cc: ss.getCorsiFrequentati()){
			if(cc.getCodins().equals(codins)) {
				iscritto=true;
			}
			}
			}
			}
		if(iscritto==false) {
		for(Studente ss: studenti) {
			if(ss.getMatricola()==matricola) {
			for(Corso cc: corsi){
			if(cc.getCodins().equals(codins) && cc.getCredit()!=0) {
					ss.setCorsiFrequentati(cc);
					cc.setStudentiIscritti(ss);
			}
			}
			}
		}
		}
		return iscritto;
	}
	
	public boolean eIscritto(int matricola,String codins) {
		for(Studente ss: studenti) {
			if(ss.getMatricola()==matricola) {
			s.getCorsiFrequentatiStudente(ss);
				for(Corso cc: ss.getCorsiFrequentati()){
			if(cc.getCodins().equals(codins)) {
				
				return true;
			}
			}
			}
			}
		return false;
	}

}

