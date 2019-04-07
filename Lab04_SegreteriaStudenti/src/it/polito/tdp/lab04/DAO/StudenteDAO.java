package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {

	public List<Studente> getTuttiGliStudenti() {

		final String sql = "SELECT * FROM studente";

		List<Studente> studenti = new LinkedList<Studente>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				int matricola = rs.getInt("matricola");
				String nome = rs.getString("nome");
				String cognome = rs.getString("cognome");
				String cds = rs.getString("CDS");
				Studente s=new Studente(matricola,nome,cognome,cds);
                studenti.add(s);
				// Crea un nuovo JAVA Bean Corso
				// Aggiungi il nuovo oggetto Corso alla lista corsi
			}

			return studenti;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}
	public void getStudente(Studente student) {
		 try {
		Connection conn = ConnectDB.getConnection();
		int id=student.getMatricola();
		String sql="SELECT * FROM studenti "+
				"WHERE matricola = ? ";
		 PreparedStatement statement= conn.prepareStatement(sql);
		 statement.setInt(1, id);
		 ResultSet rs=statement.executeQuery();
		    
		    if(rs.next()) {
				String nome=rs.getString("nome");
				String cognome=rs.getString("cognome");
				int matricola=rs.getInt("matricola");
				String cds=rs.getString("CDS");
				student.setNome(nome);
				student.setCognome(cognome);
				student.setMatricola(matricola);
				student.setCds(cds);
		    }
		    
		    conn.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
	}
		    
		 public void getCorsiFrequentatiStudente(Studente studente) {
				
			 try {
					Connection conn = ConnectDB.getConnection();
					int id=studente.getMatricola();
					String sql="SELECT codins FROM iscrizione WHERE matricola = ?";
					 PreparedStatement statement= conn.prepareStatement(sql);
					 statement.setInt(1, id);
					 ResultSet rs=statement.executeQuery();
					    
					 List<String> lcodins=new LinkedList<String>();
					    while(rs.next()) {
							String risultato=rs.getString("codins");
							lcodins.add(risultato);
							}
					    
					    sql="SELECT * FROM corso WHERE codins = ?";
					    for(String m: lcodins) {
						    statement= conn.prepareStatement(sql);
							statement.setString(1, m);
							rs=statement.executeQuery();
							if(rs.next()) {
								Corso c=new Corso(rs.getString("codins"),rs.getInt("crediti"),rs.getString("nome"),rs.getInt("pd"));
								studente.setCorsiFrequentati(c);
								c.setStudentiIscritti(studente);
							}
					    }
					    
					    conn.close();
						} catch (SQLException e) {
							
							e.printStackTrace();
						}	
					    
					    
		}
		    
	}

