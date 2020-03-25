package fr.diginamic.jdbc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import fr.diginamic.jdbc.entites.Fournisseur;

public class FournisseurDaoJdbcSANSPREPAREDSTATEMENT implements FournisseurDao {

	@Override
	public List<Fournisseur> extraire() throws SQLException, ClassNotFoundException {
		// Cr�ation d'un tableau
		ArrayList<Fournisseur> listeFournisseurs = new ArrayList<>();
		// �tape 0 - lecture fichier "db.properties"
		ResourceBundle db = ResourceBundle.getBundle("db");

		// �tape 1 - enregistrer le pilote
		// option 1
		// DriverManager.registerDriver(new Driver());
		// option 2
		Class.forName(db.getString("db.driver"));

		// �tape 2 - cr�er la connexion
		Connection connection = DriverManager.getConnection(db.getString("db.url"), db.getString("db.user"),
				db.getString("db.pass"));

		// �tape 3 - cr�er un "statement" (outil pour faire des requ�tes)
		Statement statement = connection.createStatement();

		// �tape 4 - ex�cuter la requ�te
		ResultSet resultSet = statement.executeQuery("select * from fournisseur");

		while (resultSet.next()) {
			listeFournisseurs.add(new Fournisseur(resultSet.getInt("id"), resultSet.getString("nom")));
		}

		// �tape 5 => lib�ration des ressources
		resultSet.close();
		statement.close();
		connection.close();
		return listeFournisseurs;
	}

	@Override
	public void insert(Fournisseur fournisseur) throws SQLException, ClassNotFoundException {
		// �tape 0 - lecture fichier "db.properties"
		ResourceBundle db = ResourceBundle.getBundle("db");

		int idFournisseur = fournisseur.getId();
		String nomFournisseur = fournisseur.getNom();
		// �tape 1 - enregistrer le pilote
		// option 1
		// DriverManager.registerDriver(new Driver());
		// option 2
		Class.forName(db.getString("db.driver"));

		// �tape 2 - cr�er la connexion
		Connection connection = DriverManager.getConnection(db.getString("db.url"), db.getString("db.user"),
				db.getString("db.pass"));

		// �tape 3 - cr�er un "statement" (outil pour faire des requ�tes)
		Statement statement = connection.createStatement();

		// �tape 4 - ex�cuter la requ�te
		// 4.1 - exemple insert, update, delete
		statement.executeUpdate(
				"insert into fournisseur(id,nom) values(" + idFournisseur + ", '" + nomFournisseur + "')");

		// �tape 5 => lib�ration des ressources
		statement.close();
		connection.close();

	}

	@Override
	public int update(String ancienNom, String nouveauNom) throws SQLException, ClassNotFoundException {
		// �tape 0 - lecture fichier "db.properties"
		ResourceBundle db = ResourceBundle.getBundle("db");

		// �tape 1 - enregistrer le pilote
		// option 1
		// DriverManager.registerDriver(new Driver());
		// option 2
		Class.forName(db.getString("db.driver"));

		// �tape 2 - cr�er la connexion
		Connection connection = DriverManager.getConnection(db.getString("db.url"), db.getString("db.user"),
				db.getString("db.pass"));

		// �tape 3 - cr�er un "statement" (outil pour faire des requ�tes)
		Statement statement = connection.createStatement();

		// �tape 4 - ex�cuter la requ�te
		// 4.1 - exemple insert, update, delete
		statement.executeUpdate(
				"UPDATE fournisseur SET `nom`='" + nouveauNom + "' WHERE `nom` LIKE '" + ancienNom + "'");

		// �tape 5 => lib�ration des ressources
		statement.close();
		connection.close();
		return 0;
	}

	@Override
	public boolean delete(Fournisseur fournisseur) throws SQLException, ClassNotFoundException {
		// �tape 0 - lecture fichier "db.properties"
		ResourceBundle db = ResourceBundle.getBundle("db");

		int idFournisseur = fournisseur.getId();
		// String nomFournisseur = fournisseur.getNom();
		// �tape 1 - enregistrer le pilote
		// option 1
		// DriverManager.registerDriver(new Driver());
		// option 2
		Class.forName(db.getString("db.driver"));

		// �tape 2 - cr�er la connexion
		Connection connection = DriverManager.getConnection(db.getString("db.url"), db.getString("db.user"),
				db.getString("db.pass"));

		// �tape 3 - cr�er un "statement" (outil pour faire des requ�tes)
		Statement statement = connection.createStatement();

		// �tape 4 - ex�cuter la requ�te
		// 4.1 - exemple insert, update, delete
		statement.executeUpdate("DELETE FROM `fournisseur` WHERE `id` = " + idFournisseur);

		// �tape 5 => lib�ration des ressources
		statement.close();
		connection.close();
		return true;
	}

}
