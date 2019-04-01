package model;

public class BonProdus {

	private int index;
	private int idBonFiscal;
	private int idProdus;
	
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public int getIdBonFiscal() {
		return idBonFiscal;
	}
	public void setIdBonFiscal(int idBonFiscal) {
		this.idBonFiscal = idBonFiscal;
	}
	public int getIdProdus() {
		return idProdus;
	}
	public void setIdProdus(int idProdus) {
		this.idProdus = idProdus;
	}
	@Override
	public String toString() {
		return "BonProdus -> " + index + " " + idBonFiscal + " " + idProdus;
	}
	
}
