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
		// Création d'un tableau
		ArrayList<Fournisseur> listeFournisseurs = new ArrayList<>();
		// étape 0 - lecture fichier "db.properties"
		ResourceBundle db = ResourceBundle.getBundle("db");

		// étape 1 - enregistrer le pilote
		// option 1
		// DriverManager.registerDriver(new Driver());
		// option 2
		Class.forName(db.getString("db.driver"));

		// étape 2 - créer la connexion
		Connection connection = DriverManager.getConnection(db.getString("db.url"), db.getString("db.user"),
				db.getString("db.pass"));

		// étape 3 - créer un "statement" (outil pour faire des requêtes)
		Statement statement = connection.createStatement();

		// étape 4 - exécuter la requête
		ResultSet resultSet = statement.executeQuery("select * from fournisseur");

		while (resultSet.next()) {
			listeFournisseurs.add(new Fournisseur(resultSet.getInt("id"), resultSet.getString("nom")));
		}

		// étape 5 => libération des ressources
		resultSet.close();
		statement.close();
		connection.close();
		return listeFournisseurs;
	}

	@Override
	public void insert(Fournisseur fournisseur) throws SQLException, ClassNotFoundException {
		// étape 0 - lecture fichier "db.properties"
		ResourceBundle db = ResourceBundle.getBundle("db");

		int idFournisseur = fournisseur.getId();
		String nomFournisseur = fournisseur.getNom();
		// étape 1 - enregistrer le pilote
		// option 1
		// DriverManager.registerDriver(new Driver());
		// option 2
		Class.forName(db.getString("db.driver"));

		// étape 2 - créer la connexion
		Connection connection = DriverManager.getConnection(db.getString("db.url"), db.getString("db.user"),
				db.getString("db.pass"));

		// étape 3 - créer un "statement" (outil pour faire des requêtes)
		Statement statement = connection.createStatement();

		// étape 4 - exécuter la requête
		// 4.1 - exemple insert, update, delete
		statement.executeUpdate(
				"insert into fournisseur(id,nom) values(" + idFournisseur + ", '" + nomFournisseur + "')");

		// étape 5 => libération des ressources
		statement.close();
		connection.close();

	}

	@Override
	public int update(String ancienNom, String nouveauNom) throws SQLException, ClassNotFoundException {
		// étape 0 - lecture fichier "db.properties"
		ResourceBundle db = ResourceBundle.getBundle("db");

		// étape 1 - enregistrer le pilote
		// option 1
		// DriverManager.registerDriver(new Driver());
		// option 2
		Class.forName(db.getString("db.driver"));

		// étape 2 - créer la connexion
		Connection connection = DriverManager.getConnection(db.getString("db.url"), db.getString("db.user"),
				db.getString("db.pass"));

		// étape 3 - créer un "statement" (outil pour faire des requêtes)
		Statement statement = connection.createStatement();

		// étape 4 - exécuter la requête
		// 4.1 - exemple insert, update, delete
		statement.executeUpdate(
				"UPDATE fournisseur SET `nom`='" + nouveauNom + "' WHERE `nom` LIKE '" + ancienNom + "'");

		// étape 5 => libération des ressources
		statement.close();
		connection.close();
		return 0;
	}

	@Override
	public boolean delete(Fournisseur fournisseur) throws SQLException, ClassNotFoundException {
		// étape 0 - lecture fichier "db.properties"
		ResourceBundle db = ResourceBundle.getBundle("db");

		int idFournisseur = fournisseur.getId();
		// String nomFournisseur = fournisseur.getNom();
		// étape 1 - enregistrer le pilote
		// option 1
		// DriverManager.registerDriver(new Driver());
		// option 2
		Class.forName(db.getString("db.driver"));

		// étape 2 - créer la connexion
		Connection connection = DriverManager.getConnection(db.getString("db.url"), db.getString("db.user"),
				db.getString("db.pass"));

		// étape 3 - créer un "statement" (outil pour faire des requêtes)
		Statement statement = connection.createStatement();

		// étape 4 - exécuter la requête
		// 4.1 - exemple insert, update, delete
		statement.executeUpdate("DELETE FROM `fournisseur` WHERE `id` = " + idFournisseur);

		// étape 5 => libération des ressources
		statement.close();
		connection.close();
		return true;
	}

}
