package model;

public class AngajatDepartament {

	private int index;
	private int idAngajat;
	private int idDepartament;
	
	public AngajatDepartament() {}
	
	public AngajatDepartament(int idAngajat, int idDepartament) {
		this.idAngajat = idAngajat;
		this.idDepartament = idDepartament;
	}

	public AngajatDepartament(int index, int idAngajat, int idDepartament) {
		this.index = index;
		this.idAngajat = idAngajat;
		this.idDepartament = idDepartament;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getAngajat() {
		return idAngajat;
	}

	public void setAngajat(int idAngajat) {
		this.idAngajat = idAngajat;
	}

	public int getDepartament() {
		return idDepartament;
	}

	public void setDepartament(int idDepartament) {
		this.idDepartament = idDepartament;
	}

	@Override
	public String toString() {
		return "Repartizare angajat-departament: " + index + " " + idAngajat + " " + idDepartament;
	}
	
	
}
