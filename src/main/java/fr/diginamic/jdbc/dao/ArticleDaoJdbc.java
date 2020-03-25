package fr.diginamic.jdbc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import fr.diginamic.jdbc.entites.Article;
import fr.diginamic.jdbc.entites.Fournisseur;

public class ArticleDaoJdbc implements ArticleDao {

	@Override
	public List<Article> extraire() throws SQLException, ClassNotFoundException {

		Fournisseur fournisseur = null;
		// Cr�ation d'un tableau
		ArrayList<Article> listeArticles = new ArrayList<>();

		// �tape 0 - lecture fichier "db.properties"
		ResourceBundle db = ResourceBundle.getBundle("db");

		// �tape 1 - enregistrer le pilote
		Class.forName(db.getString("db.driver"));

		// �tape 2 - cr�er la connexion
		Connection connection = DriverManager.getConnection(db.getString("db.url"), db.getString("db.user"),
				db.getString("db.pass"));

		// �tape 3 - cr�er un "preparedStatement"
		// Statement statement = connection.createStatement();
		PreparedStatement preparedStatement = connection.prepareStatement("select * from article");

		// Execution du statement
		ResultSet resultats = preparedStatement.executeQuery();
		while (resultats.next()) {

			// R�cup�rer ID fournisseur
			PreparedStatement preparedStatementFournisseur = connection
					.prepareStatement("select * from fournisseur WHERE id =" + resultats.getInt("fk_fournisseur"));

			// Execution du statement
			ResultSet resultatsFournisseur = preparedStatementFournisseur.executeQuery();
			while (resultatsFournisseur.next()) {
				fournisseur = new Fournisseur(resultatsFournisseur.getInt("id"), resultatsFournisseur.getString("nom"));
			}

			listeArticles.add(new Article(resultats.getInt("id"), resultats.getString("ref"),
					resultats.getString("designation"), resultats.getDouble("prix"), fournisseur));
		}

		// �tape 5 => lib�ration des ressources
		resultats.close();
		preparedStatement.close();
		connection.close();
		return listeArticles;
	}

	@Override
	public void insert(Article article, Fournisseur fournisseur) throws SQLException, ClassNotFoundException {
		// �tape 0 - lecture fichier "db.properties"
		ResourceBundle db = ResourceBundle.getBundle("db");

		int idArticle = article.getId();
		String refArticle = article.getRef();
		String designationArticle = article.getDesignation();
		double prixArticle = article.getPrix();

		int idFournisseur = fournisseur.getId();

		// �tape 1 - enregistrer le pilote
		Class.forName(db.getString("db.driver"));

		// �tape 2 - cr�er la connexion
		Connection connection = DriverManager.getConnection(db.getString("db.url"), db.getString("db.user"),
				db.getString("db.pass"));

		// �tape 3 - cr�er un "preparedStatement"
		// Statement statement = connection.createStatement();
		PreparedStatement preparedStatement = connection.prepareStatement(
				"INSERT INTO `article`(`id`, `ref`, `designation`, `prix`, `fk_fournisseur`) VALUES (?,?,?,?,?)");

		preparedStatement.setInt(1, idArticle);
		preparedStatement.setString(2, refArticle);
		preparedStatement.setString(3, designationArticle);
		preparedStatement.setDouble(4, prixArticle);
		preparedStatement.setInt(5, idFournisseur);
		// Execution du statement
		preparedStatement.executeUpdate();

		// �tape 5 => lib�ration des ressources
		preparedStatement.close();
		connection.close();

	}

	@Override
	public int updateNomArticle(String ancienNom, String nouveauNom) throws SQLException, ClassNotFoundException {
		// �tape 0 - lecture fichier "db.properties"
		ResourceBundle db = ResourceBundle.getBundle("db");

		// �tape 1 - enregistrer le pilote
		Class.forName(db.getString("db.driver"));

		// �tape 2 - cr�er la connexion
		Connection connection = DriverManager.getConnection(db.getString("db.url"), db.getString("db.user"),
				db.getString("db.pass"));

		// �tape 3 - cr�er un "preparedStatement"
		// Statement statement = connection.createStatement();
		PreparedStatement preparedStatement = connection
				.prepareStatement("UPDATE `article` SET `designation`='" + nouveauNom + "' WHERE `designation` LIKE ?");

		// D�finir "parametres?"
		preparedStatement.setString(1, ancienNom);

		// Execution du statement
		preparedStatement.executeUpdate();

		// �tape 5 => lib�ration des ressources
		preparedStatement.close();
		connection.close();
		return 0;
	}

	@Override
	public int updateFournisseur(int ancienFournisseur, int nouveauFournisseur)
			throws SQLException, ClassNotFoundException {

		// �tape 0 - lecture fichier "db.properties"
		ResourceBundle db = ResourceBundle.getBundle("db");

		// �tape 1 - enregistrer le pilote
		Class.forName(db.getString("db.driver"));

		// �tape 2 - cr�er la connexion
		Connection connection = DriverManager.getConnection(db.getString("db.url"), db.getString("db.user"),
				db.getString("db.pass"));

		// �tape 3 - cr�er un "preparedStatement"
		// Statement statement = connection.createStatement();
		PreparedStatement preparedStatement = connection.prepareStatement(
				"UPDATE `article` SET `fk_fournisseur`=" + nouveauFournisseur + " WHERE `fk_fournisseur`= ?");

		// D�finir "parametres?"
		preparedStatement.setInt(1, ancienFournisseur);

		// Execution du statement
		preparedStatement.executeUpdate();

		// �tape 5 => lib�ration des ressources
		preparedStatement.close();
		connection.close();
		return 0;
	}

	@Override
	public boolean delete(Article article) throws SQLException, ClassNotFoundException {

		// �tape 0 - lecture fichier "db.properties"
		ResourceBundle db = ResourceBundle.getBundle("db");

		int idArticle = article.getId();

		// �tape 1 - enregistrer le pilote
		Class.forName(db.getString("db.driver"));

		// �tape 2 - cr�er la connexion
		Connection connection = DriverManager.getConnection(db.getString("db.url"), db.getString("db.user"),
				db.getString("db.pass"));

		// �tape 3 - cr�er un "preparedStatement"
		// Statement statement = connection.createStatement();
		PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM `article` WHERE `id` = ? ");

		// D�finir "parametres?"
		preparedStatement.setInt(1, idArticle);

		// Execution du statement
		preparedStatement.executeUpdate();

		// �tape 5 => lib�ration des ressources
		preparedStatement.close();
		connection.close();
		return true;
	}

}
