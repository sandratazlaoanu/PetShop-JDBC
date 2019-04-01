package model;

import java.util.List;

public class Departament {

	private int idDepartament;
	private String denumire;
	private int nrRaioane;
	private List<AngajatDepartament> repartizare;

	public Departament() {}
	public Departament(int id, String nume,int nrRaioane) {
		this.idDepartament = id;
		this.denumire = nume;
		this.nrRaioane = nrRaioane;
		}
	public int getIdDepartament() {
		return idDepartament;
	}

	public void setIdDepartament(int idDepartament) {
		this.idDepartament = idDepartament;
	}

	public String getDenumire() {
		return denumire;
	}

	public void setDenumire(String denumire) {
		this.denumire = denumire;
	}

	public int getNrRaioane() {
		return nrRaioane;
	}

	public void setNrRaioane(int nrRaioane) {
		this.nrRaioane = nrRaioane;
	}

	@Override
	public String toString() {
		return "Departament -> " + idDepartament + " " + denumire + " " + nrRaioane;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((denumire == null) ? 0 : denumire.hashCode());
		result = prime * result + idDepartament;
		result = prime * result + nrRaioane;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Departament other = (Departament) obj;
		if (denumire == null) {
			if (other.denumire != null)
				return false;
		} else if (!denumire.equals(other.denumire))
			return false;
		if (idDepartament != other.idDepartament)
			return false;
		if (nrRaioane != other.nrRaioane)
			return false;
		return true;
	}

	public List<AngajatDepartament> getRepartizare() {
		return repartizare;
	}

	public void setRepartizare(List<AngajatDepartament> repartizare) {
		this.repartizare = repartizare;
	}
}
