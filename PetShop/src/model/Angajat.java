package model;

public class Angajat {
	private int idAngajat;

	private String nume;

	private String prenume;

	private float salariu;

	private int idPetShopAngajat;

	public Angajat() {

	}

	public Angajat(int id, String nume, String prenume, float salariu, int idPetShopAngajat) {
		this.idAngajat = id;
		this.nume = nume;
		this.prenume = prenume;
		this.salariu = salariu;
		this.idPetShopAngajat = idPetShopAngajat;
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

	public int getIdPetShopAngajat() {
		return idPetShopAngajat;
	}

	public void setIdPetShopAngajat(int idPetShopAngajat) {
		this.idPetShopAngajat = idPetShopAngajat;
	}

	@Override
	public String toString() {
		
		return idAngajat + " " + nume + " " + prenume + " " + salariu
				+ " " + idPetShopAngajat;
	}

}
