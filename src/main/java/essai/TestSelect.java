package essai;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

import fr.diginamic.jdbc.entites.Fournisseur;

public class TestSelect {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {

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

		// Liste des fournisseurs
		for (Fournisseur valeur : listeFournisseurs) {
			System.out.println(valeur.toString());
		}
		// étape 5 => libération des ressources
		resultSet.close();
		statement.close();
		connection.close();

	}

}
