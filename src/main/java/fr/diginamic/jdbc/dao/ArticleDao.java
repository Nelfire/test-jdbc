package fr.diginamic.jdbc.dao;

import java.sql.SQLException;
import java.util.List;

import fr.diginamic.jdbc.entites.Article;
import fr.diginamic.jdbc.entites.Fournisseur;

public interface ArticleDao {

	List<Article> extraire() throws SQLException, ClassNotFoundException;

	void insert(Article article, Fournisseur fournisseur) throws SQLException, ClassNotFoundException;

	int updateNomArticle(String ancienNom, String nouveauNom) throws SQLException, ClassNotFoundException;

	int updateFournisseur(int ancienFournisseur, int nouveauFournisseur) throws SQLException, ClassNotFoundException;

	boolean delete(Article article) throws SQLException, ClassNotFoundException;
}
