package model;

import java.util.ArrayList;
import java.util.List;

import model.BonFiscal;

public class Client {

	private int idClient;
	private String nume;
	private String prenume;
	private int idPetShop;
	private List<BonFiscal> bonuri;

	public Client() {
		bonuri = new ArrayList<>();
	}

	public Client(int id, String nume, String prenume, int idPetShop) {
		this.idClient = id;
		this.nume = nume;
		this.prenume = prenume;
		this.idPetShop = idPetShop;
		bonuri = new ArrayList<>();
	}

	public int getIdClient() {
		return idClient;
	}

	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}

	public String getNume() {
		return nume;
	}

	public void setNume(String nume) {
		this.nume = nume;
	}

	public String getPrenume() {
		return prenume;
	}

	public void setPrenume(String prenume) {
		this.prenume = prenume;
	}

	public List<BonFiscal> getBonuri() {
		return bonuri;
	}

	public void setBonuri(List<BonFiscal> bonuri) {
		this.bonuri = bonuri;
	}

	public int getPetShop() {
		return idPetShop;
	}

	public void setPetShop(int idPetShop) {
		this.idPetShop = idPetShop;
	}

	@Override
	public String toString() {
		return "Client info -> " + idClient + " " + nume + " " + prenume;
	}

}
