package model;

public class Produs {

	private int id;
	private String denumire;
	private float pret;
	private int cantitate;
	private int idFurnizor;
	private int idPetShop;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDenumire() {
		return denumire;
	}

	public void setDenumire(String denumire) {
		this.denumire = denumire;
	}

	public float getPret() {
		return pret;
	}

	public void setPret(float pret) {
		this.pret = pret;
	}

	public int getCantitate() {
		return cantitate;
	}

	public void setCantitate(int cantitate) {
		this.cantitate = cantitate;
	}

	public int getIdFurnizor() {
		return idFurnizor;
	}

	public void setIdFurnizor(int idFurnizor) {
		this.idFurnizor = idFurnizor;
	}

	public int getIdPetShop() {
		return idPetShop;
	}

	public void setIdPetShop(int idPetShop) {
		this.idPetShop = idPetShop;
	}

	@Override
	public String toString() {
		return "Produs -> " + id + " " + denumire + " " + pret + " " + cantitate;
	}

}
