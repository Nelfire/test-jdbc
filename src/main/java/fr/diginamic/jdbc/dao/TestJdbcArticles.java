package fr.diginamic.jdbc.dao;

import java.sql.SQLException;

import fr.diginamic.jdbc.entites.Article;
import fr.diginamic.jdbc.entites.Fournisseur;

public class TestJdbcArticles {

	public static void main(String[] args) {
		Fournisseur fournisseur = new Fournisseur(12, "La Maison de la Peinture");
		FournisseurDao fournisseurDao = new FournisseurDaoJdbc();
		ArticleDao articleDao = new ArticleDaoJdbc();
		try {
			// Ajout du fournisseur.
			fournisseurDao.insert(fournisseur);

			// Ajout de 4 articles

			articleDao.insert(new Article(30, "B14", "Peinture blanche 1L", 12.5, fournisseur), fournisseur);
			articleDao.insert(new Article(31, "H74", "Peinture rouge mate 1L", 15.5, fournisseur), fournisseur);
			articleDao.insert(new Article(32, "E58", "Peinture noire laquée 1L »", 17.8, fournisseur), fournisseur);
			articleDao.insert(new Article(33, "K02", "Peinture bleue mate 1L", 15.5, fournisseur), fournisseur);

			// Affichage des articles
			System.out.println(articleDao.extraire());

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
