package essai;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class TestUpdate {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
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
				"UPDATE fournisseur SET `nom`='La Maison des Peintures' WHERE `nom` LIKE 'La Maison de la Peinture'");

		// 4.2 - exemple select
		ResultSet resultSet = statement.executeQuery("select * from fournisseur");

		while (resultSet.next()) {
			System.out.println(resultSet.getInt("id") + " " + resultSet.getString("nom"));
		}

		// �tape 5 => lib�ration des ressources
		resultSet.close();
		statement.close();
		connection.close();

	}

}
