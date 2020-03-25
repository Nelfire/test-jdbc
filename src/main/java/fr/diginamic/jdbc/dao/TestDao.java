package fr.diginamic.jdbc.dao;

import java.sql.SQLException;

import fr.diginamic.jdbc.entites.Fournisseur;

public class TestDao {

	public static void main(String[] args) {

		Fournisseur fournisseur = new Fournisseur(8, "L’Espace Création");
		FournisseurDao interDao = new FournisseurDaoJdbc();
		try {
			// Ajout du fournisseur.
			interDao.insert(fournisseur);

			// Modification du nom du fournisseur.
			interDao.update("L’Espace Création", "Lespace creation");

			// Affichage de la liste des fournisseurs.
			System.out.println("--Liste Fournisseurs --");
			System.out.println(interDao.extraire());

			// Suppression du dernier fournisseur ajouté.
			interDao.delete(fournisseur);

			// Affichage de la liste des fournisseurs apres suppression
			System.out.println("--Liste Fournisseurs apres suppression--");
			System.out.println(interDao.extraire());

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
