package fr.diginamic.jdbc.entites;

public class Fournisseur {

	/** id du fournisseur */
	private int id;
	/** nom du fournisseur */
	private String nom;

	/**
	 * Constructeur
	 * 
	 * @param id  du fournisseur
	 * @param nom du fournisseur
	 */
	public Fournisseur(int id, String nom) {
		this.id = id;
		this.nom = nom;
	}

	/**
	 * Getter
	 * 
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Setter
	 * 
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Getter
	 * 
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Setter
	 * 
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	@Override
	public String toString() {
		return "Fournisseur [id=" + id + ", nom=" + nom + "]";
	}

}
