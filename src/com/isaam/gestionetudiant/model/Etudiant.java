package com.isaam.gestionetudiant.model;

public class Etudiant {

	private int id;
	private String nom;
	private String prenom;
	private Float moyenne;
	private String login;
	private String password;
	public Etudiant(int id, String nom, String prenom, Float moyenne, String login, String password) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.moyenne = moyenne;
		this.login = login;
		this.password = password;
	}
	public Etudiant( String nom, String prenom, Float moyenne, String login, String password) {
		
		this.nom = nom;
		this.prenom = prenom;
		this.moyenne = moyenne;
		this.login = login;
		this.password = password;
	}
	public Etudiant() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public Float getMoyenne() {
		return moyenne;
	}
	public void setMoyenne(Float moyenne) {
		this.moyenne = moyenne;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "Etudiant [nom=" + nom + ", prenom=" + prenom + ", moyenne=" + moyenne + ", login=" + login
				+ ", password=" + password + "]";
	}

	
	
	
	
	
}
