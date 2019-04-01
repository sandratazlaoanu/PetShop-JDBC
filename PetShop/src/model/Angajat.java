package model;

import java.util.List;

public class Angajat {
	
	private int idAngajat;

	private String nume;

	private String prenume;

	private float salariu;

	private int idPetShop;
	
	private List<AngajatDepartament> repartizare;

	public Angajat() {

	}

	public Angajat(int id, String nume, String prenume, float salariu, int idPetShop) {
		this.idAngajat = id;
		this.nume = nume;
		this.prenume = prenume;
		this.salariu = salariu;
		this.idPetShop = idPetShop;
	}

	public int getIdAngajat() {
		return idAngajat;
	}

	public void setIdAngajat(int idAngajat) {
		this.idAngajat = idAngajat;
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

	public float getSalariu() {
		return salariu;
	}

	public void setSalariu(float salariu) {
		this.salariu = salariu;
	}

	public int getPetShop() {
		return idPetShop;
	}

	public void setPetShop(int  idPetShop) {
		this.idPetShop = idPetShop;
	}

	@Override
	public String toString() {
		
		return idAngajat + " " + nume + " " + prenume + " " + salariu;
	}

	public List<AngajatDepartament> getRepartizare() {
		return repartizare;
	}

	public void setRepartizare(List<AngajatDepartament> repartizare) {
		this.repartizare = repartizare;
	}

}
