package model;

public class Adresa {

	private int zip;
	private String judet;
	private String oras;
	private String strada;
	private int numar;
	private int idPetShop;

	public Adresa() {
	}

	public Adresa(int zip, String judet, String oras, String strada, int numar) {
		this.zip = zip;
		this.judet = judet;
		this.oras = oras;
		this.numar = numar;
	}

	public int getZip() {
		return zip;
	}

	public void setZip(int zip) {
		this.zip = zip;
	}

	public String getJudet() {
		return judet;
	}

	public void setJudet(String judet) {
		this.judet = judet;
	}

	public String getOras() {
		return oras;
	}

	public void setOras(String oras) {
		this.oras = oras;
	}

	public String getStrada() {
		return strada;
	}

	public void setStrada(String strada) {
		this.strada = strada;
	}

	public int getPetShop() {
		return idPetShop;
	}

	public void setPetShop(int idPetShop) {
		this.idPetShop = idPetShop;
	}

	public int getNumar() {
		return numar;
	}

	public void setNumar(int numar) {
		this.numar = numar;
	}

	@Override
	public String toString() {
		return "Adresa: " + zip + " " + judet + " " + oras + " " + strada + " " + numar;
	}

}
