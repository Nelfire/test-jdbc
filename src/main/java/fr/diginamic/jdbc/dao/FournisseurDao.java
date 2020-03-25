package fr.diginamic.jdbc.dao;

import java.sql.SQLException;
import java.util.List;

import fr.diginamic.jdbc.entites.Fournisseur;

public interface FournisseurDao {
	List<Fournisseur> extraire() throws SQLException, ClassNotFoundException;

	void insert(Fournisseur fournisseur) throws SQLException, ClassNotFoundException;

	int update(String ancienNom, String nouveauNom) throws SQLException, ClassNotFoundException;

	boolean delete(Fournisseur fournisseur) throws SQLException, ClassNotFoundException;
}
