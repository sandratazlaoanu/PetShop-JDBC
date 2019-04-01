package model;

import model.Client;

import java.util.ArrayList;
import java.util.List;

import model.Angajat;
import model.Produs;

public class PetShop {

	private int idPetShop;
	private String nume;
	private String nrTelefon;
	private String email;
	private int idAdresa;
	private List<Client> clienti;
	private List<Angajat> angajati;
	private List<Produs> produse;

	public PetShop() {
		clienti = new ArrayList<>();
		angajati = new ArrayList<>();
		produse = new ArrayList<>();
	}

	public PetShop(int id, String nume, String nrTelefon, String email, int idAdresa, List<Client> clienti,
			List<Angajat> angajati, List<Produs> produse) {
		this.idPetShop = id;
		this.nume = nume;
		this.nrTelefon = nrTelefon;
		this.email = email;
		this.idAdresa = idAdresa;
		clienti = new ArrayList<>();
		angajati = new ArrayList<>();
		produse = new ArrayList<>();
	}

	public int getIdPetShop() {
		return idPetShop;
	}

	public void setIdPetShop(int idPetShop) {
		this.idPetShop = idPetShop;
	}

	public String getNume() {
		return nume;
	}

	public void setNume(String nume) {
		this.nume = nume;
	}

	public String getNrTelefon() {
		return nrTelefon;
	}

	public void setNrTelefon(String nrTelefon) {
		this.nrTelefon = nrTelefon;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Client> getClienti() {
		return clienti;
	}

	public void setClienti(List<Client> clienti) {
		this.clienti = clienti;
	}

	public List<Angajat> getAngajati() {
		return angajati;
	}

	public void setAngajati(List<Angajat> angajati) {
		this.angajati = angajati;
	}

	public List<Produs> getProduse() {
		return produse;
	}

	public void setProduse(List<Produs> produse) {
		this.produse = produse;
	}

	public int getIdAdresa() {
		return idAdresa;
	}

	public void setIdAdresa(int idAdresa) {
		this.idAdresa = idAdresa;
	}

	@Override
	public String toString() {
		return "PetShop " + idPetShop + " " + nume + " " + nrTelefon + " " + email + " " + idAdresa;
	}

}
