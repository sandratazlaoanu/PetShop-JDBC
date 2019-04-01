package model;

import java.util.ArrayList;
import java.util.List;

import model.Produs;

public class Furnizor {

	private int idFurnizor;
	private String denumire;
	private String idCont;
	private List<Produs> produse;

	public Furnizor() {
		produse = new ArrayList<>();
	}

	public Furnizor(int id, String denumire, String idCont, List<Produs> produse) {
		this.idFurnizor = id;
		this.denumire = denumire;
		this.idCont = idCont;
		produse = new ArrayList<>();
	}

	public int getIdFurnizor() {
		return idFurnizor;
	}

	public void setIdFurnizor(int idFurnizor) {
		this.idFurnizor = idFurnizor;
	}

	public String getDenumire() {
		return denumire;
	}

	public void setDenumire(String denumire) {
		this.denumire = denumire;
	}

	public String getIdCont() {
		return idCont;
	}

	public void setIdCont(String idCont) {
		this.idCont = idCont;
	}

	public List<Produs> getProduse() {
		return produse;
	}

	public void setProduse(List<Produs> produse) {
		this.produse = produse;
	}

	@Override
	public String toString() {
		return "Furnizor -> " + idFurnizor + " " + denumire + " " + idCont;
	}
}
