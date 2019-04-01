package util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.DatabaseConnection;
import model.Angajat;
import model.AngajatDepartament;
import model.Departament;

public class AngajatDepartamentOperations {

	private DatabaseConnection databaseConnection;

	public AngajatDepartamentOperations(DatabaseConnection databaseConnection) {
		this.databaseConnection = databaseConnection;
	}

	public List<AngajatDepartament> getAngajatDepartament() {
		databaseConnection.createConnection();
		String query = "SELECT * FROM petshopbd.angajatdepartament";
		List<AngajatDepartament> repartizari = new ArrayList<AngajatDepartament>();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = databaseConnection.getConnection().prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				AngajatDepartament repartizare = new AngajatDepartament();
				repartizare.setIndex(rs.getInt("index"));
				repartizare.setAngajat(rs.getInt("idAngajat"));
				repartizare.setDepartament(rs.getInt("idDepartament"));

				repartizari.add(repartizare);
			}
		} catch (SQLException e) {
			System.err.println("Error when creating query: " + e.getMessage());
		} finally {
			try {
				rs.close();
				ps.close();
				databaseConnection.getConnection().close();
			} catch (SQLException e) {
				System.err.println("Failed closing streams: " + e.getMessage());
			}
		}

		return repartizari;
	}

	public void addAngajatDepartament(AngajatDepartament angajatDepartament) {
		databaseConnection.createConnection();
		int index = lastAngajatDepartamentIndex();
		angajatDepartament.setIndex(index);

		String query = "INSERT INTO angajatdepartament VALUES (?, ?, ?)";
		PreparedStatement ps = null;
		try {
			ps = databaseConnection.getConnection().prepareStatement(query);
			ps.setInt(1, angajatDepartament.getIndex());
			ps.setInt(2, angajatDepartament.getAngajat());
			ps.setInt(3, angajatDepartament.getDepartament());

			ps.executeUpdate();
		} catch (SQLException e) {
			System.err.println("Error when creating query: " + e.getMessage());

		} finally {
			try {

				databaseConnection.getConnection().close();
			} catch (SQLException e) {
				System.err.println("Failed closing streams: " + e.getMessage());
			}
		}
	}

	/* Update */

	public boolean updateAngajatDepartament(AngajatDepartament repartizare, int idAngajat, int idDepartament) {
		databaseConnection.createConnection();

		String query = "UPDATE angajatdepartament SET idAngajat=?, idDepartament=? WHERE index = \""
				+ repartizare.getIndex() + "\"";
		PreparedStatement ps = null;

		try {
			ps = databaseConnection.getConnection().prepareStatement(query);
			ps.setInt(1, idAngajat);
			ps.setInt(2, idDepartament);
			ps.executeUpdate();
		} catch (SQLException e) {
			System.err.println("Error when creating query: " + e.getMessage());
			return false;

		} finally {
			try {
				ps.close();
				databaseConnection.getConnection().close();
			} catch (SQLException e) {
				System.err.println("Failed closing streams: " + e.getMessage());
			}
		}

		return true;
	}

	/* Delete */

	public boolean deleteAngajatDepartament(AngajatDepartament repartizare) {
		databaseConnection.createConnection();

		String query = "DELETE FROM angajatdepartament WHERE index = \"" + repartizare.getIndex() + "\" ";

		PreparedStatement ps = null;
		try {
			ps = databaseConnection.getConnection().prepareStatement(query);
			ps.executeUpdate();

		} catch (SQLException e) {
			System.err.println("Error when creating query: " + e.getMessage());
			return false;
		} finally {
			try {
				ps.close();
				databaseConnection.getConnection().close();
			} catch (SQLException e) {
				System.err.println("Failed closing streams: " + e.getMessage());
			}
		}

		return true;
	}

	public Integer lastAngajatIndex() {
		Integer index;
		AngajatOperations operation = new AngajatOperations(databaseConnection);
		List<Angajat> list = operation.getAllAngajati();
		if (list.size() > 0)
			index = list.get(list.size() - 1).getIdAngajat() + 1;
		else
			index = 1;
		return index;
	}

	public Integer lastDepartamentIndex() {
		Integer index;
		DepartamentOperations operation = new DepartamentOperations(databaseConnection);
		List<Departament> list = operation.getAllDepartamente();
		if (list.size() > 0)
			index = list.get(list.size() - 1).getIdDepartament() + 1;
		else
			index = 1;
		return index;
	}

	public Integer lastAngajatDepartamentIndex() {
		Integer index;
		List<AngajatDepartament> list = getAngajatDepartament();
		if (list.size() > 0)
			index = list.get(list.size() - 1).getIndex() + 1;
		else
			index = 1;
		return index;
	}

	public void printListOfRepartizari(List<AngajatDepartament> repartizari) {
		for (AngajatDepartament repartizare : repartizari) {
			System.out.println(repartizare.toString());
		}
	}

}
