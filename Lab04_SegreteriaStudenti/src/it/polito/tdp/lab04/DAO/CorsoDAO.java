package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class CorsoDAO {

	/*
	 * Ottengo tutti i corsi salvati nel Db
	 */
	public List<Corso> getTuttiICorsi() {

		final String sql = "SELECT * FROM corso";

		List<Corso> corsi = new LinkedList<Corso>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				String codins = rs.getString("codins");
				int numeroCrediti = rs.getInt("crediti");
				String nome = rs.getString("nome");
				int periodoDidattico = rs.getInt("pd");
				Corso c=new Corso(codins,numeroCrediti,nome,periodoDidattico);
                corsi.add(c);
				// Crea un nuovo JAVA Bean Corso
				// Aggiungi il nuovo oggetto Corso alla lista corsi
			}
			conn.close();

			return corsi;

			
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}

	/*
	 * Dato un codice insegnamento, ottengo il corso
	 */
	public void getCorso(Corso corso) {
		 try {
		Connection conn = ConnectDB.getConnection();
		String parola=corso.getCodins();
		String sql="SELECT * FROM corsi "+
				"WHERE codins = ? ";
		 PreparedStatement statement= conn.prepareStatement(sql);
		 statement.setString(1, parola);
		 ResultSet rs=statement.executeQuery();
		    
		    if(rs.next()) {
				String nome=rs.getString("nome");
				String codins=rs.getString("codins");
				int crediti=rs.getInt("crediti");
				int pd=rs.getInt("pd");
				corso.setNome(nome);
				corso.setCodins(codins);
				corso.setCredit(crediti);
				corso.setPeriod(pd);
		    }
		    
		    conn.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}	
		    
		    
	}

	/*
	 * Ottengo tutti gli studenti iscritti al Corso
	 */
	public void getStudentiIscrittiAlCorso(Corso corso) {
		
		 try {
				Connection conn = ConnectDB.getConnection();
				String parola=corso.getCodins();
				String sql="SELECT matricola FROM iscrizione WHERE codins = ?";
				 PreparedStatement statement= conn.prepareStatement(sql);
				 statement.setString(1, parola);
				 ResultSet rs=statement.executeQuery();
				    
				 List<String> lmatricole=new LinkedList<String>();
				    while(rs.next()) {
						String risultato=rs.getString("matricola");
						lmatricole.add(risultato);
						}
				    
				    sql="SELECT * FROM studente WHERE matricola = ?";
				    for(String m: lmatricole) {
					    statement= conn.prepareStatement(sql);
						statement.setString(1, m);
						rs=statement.executeQuery();
						if(rs.next()) {
							Studente s=new Studente(rs.getInt("matricola"),rs.getString("nome"),rs.getString("cognome"),rs.getString("cds"));
							corso.setStudentiIscritti(s);
							s.setCorsiFrequentati(corso);
						}
				    }
				    
				    conn.close();
					} catch (SQLException e) {
						
						e.printStackTrace();
					}	
				    
				    
	}

	/*
	 * Data una matricola ed il codice insegnamento, iscrivi lo studente al corso.
	 */
	public boolean inscriviStudenteACorso(Studente studente, Corso corso) {

		 try {
				Connection conn = ConnectDB.getConnection();
				int id=studente.getMatricola();
				String sql="SELECT * FROM studente WHERE matricola = ?";
				PreparedStatement statement= conn.prepareStatement(sql);
				statement.setInt(1, id);
				ResultSet rs=statement.executeQuery();  
				    if(rs.next()) {
						int matricola=rs.getInt("matricola");
						String nome=rs.getString("nome");
						String cognome=rs.getString("cognome");
						String cds=rs.getString("CDS");
						Studente s=new Studente(matricola,nome,cognome,cds);
						corso.setStudentiIscritti(s);
						studente.setCorsiFrequentati(corso);
						return true;
				    }
				    
				    
				    conn.close();
			 } catch (SQLException e) {
						
						e.printStackTrace();
				 }	
		// ritorna true se l'iscrizione e' avvenuta con successo
		return false;
	}
}
