package model;

import java.util.Date;

public class BonFiscal {

	private int idBonFiscal;
	private float suma;
	private Date data;
	private int idClient;

	public BonFiscal() {
	}

	public BonFiscal(int idBonFiscal, Date data, float suma) {
		this.idBonFiscal = idBonFiscal;
		this.data = data;
		this.suma = suma;
	}

	public int getIdBonFiscal() {
		return idBonFiscal;
	}

	public void setIdBonFiscal(int idBonFiscal) {
		this.idBonFiscal = idBonFiscal;
	}

	public float getSuma() {
		return suma;
	}

	public void setSuma(float suma) {
		this.suma = suma;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public int getIdClient() {
		return idClient;
	}

	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}

	@Override
	public String toString() {
		return "BonFiscal -> " + idBonFiscal + " " + suma + " " + data;
	}

}
