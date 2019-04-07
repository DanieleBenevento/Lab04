package it.polito.tdp.lab04.model;

import java.util.*;

public class Corso {

	private String codins;
	private int credit;
	private String nome;
	private int period;
	private List<Studente> studentiIscritti;
	
	public Corso(String codins, int credit, String nome, int period) {
		
		this.codins = codins;
		this.credit = credit;
		this.nome = nome;
		this.period = period;
		this.studentiIscritti=new LinkedList<Studente>();
	}

	public String getCodins() {
		return codins;
	}

	public void setCodins(String codins) {
		this.codins = codins;
	}

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getPeriod() {
		return period;
	}

	public void setPeriod(int period) {
		this.period = period;
	}

	public List<Studente> getStudentiIscritti() {
		return studentiIscritti;
	}

	public void setStudentiIscritti(Studente studente) {
		boolean presente=false;
		for(Studente s: studentiIscritti) {
			if(s.getMatricola()==studente.getMatricola())
				presente=true;
		}
		if(presente==false) 
		this.studentiIscritti.add(studente);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codins == null) ? 0 : codins.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Corso other = (Corso) obj;
		if (codins == null) {
			if (other.codins != null)
				return false;
		} else if (!codins.equals(other.codins))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return nome;
	}


	
	
	
	
	
}
