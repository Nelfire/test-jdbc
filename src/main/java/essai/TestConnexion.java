package essai;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class TestConnexion {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
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

		boolean valide = connection.isValid(500);

		if (valide == true) {
			System.out.println("La connexion fonctionne correctement.");
		} else {
			System.out.println("Erreure dans la connexion.");
		}

		connection.close();
		valide = connection.isValid(500);
		System.out.println(valide);

	}

}
