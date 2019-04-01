package util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.DatabaseConnection;
import model.Departament;

public class DepartamentOperations {

	private static DatabaseConnection databaseConnection;

	public DepartamentOperations(DatabaseConnection databaseConnection) {
		DepartamentOperations.databaseConnection = databaseConnection;
	}

	/* Read */

	public List<Departament> getAllDepartamente() {
		databaseConnection.createConnection();
		String query = "SELECT idDepartament, denumire, nrRaioane FROM petshopbd.departament";
		List<Departament> departamente = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = databaseConnection.getConnection().prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				Departament departament = new Departament();
				departament.setIdDepartament(rs.getInt("idDepartament"));
				departament.setDenumire(rs.getString("denumire"));
				departament.setNrRaioane(rs.getInt("nrRaioane"));

				departamente.add(departament);
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

		return departamente;
	}

	/* Add function */

	public boolean addDepartament(Departament departament) {
		databaseConnection.createConnection();
		String query = "INSERT INTO departament VALUES (?, ?, ?)";
		PreparedStatement ps = null;
		try {
			ps = databaseConnection.getConnection().prepareStatement(query);
			ps.setInt(1, departament.getIdDepartament());
			ps.setString(2, departament.getDenumire());
			ps.setInt(3, departament.getNrRaioane());
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

	/* Update function */

	public boolean updateDepartament(int idToCheck, String denumire, int numarRaioane) {
		databaseConnection.createConnection();

		String query = "UPDATE departament SET denumre = ?, nrRaioane = ? WHERE idDepartament = \"" + idToCheck + "\"";
		PreparedStatement ps = null;

		try {
			ps = databaseConnection.getConnection().prepareStatement(query);
			ps.setString(1, denumire);
			ps.setInt(2, numarRaioane);
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

	public boolean deleteDepartament(int idToCheck) {
		databaseConnection.createConnection();

		String query = "DELETE FROM departament WHERE idDepartament = \"" + idToCheck + "\" ";

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

	/* Print list */
	public void printListOfDepartamente(List<Departament> departamente) {
		for (Departament d : departamente) {
			System.out.println(d.toString());
		}
	}

	/* Check if we have a specific departament */

	public boolean checkDepartament(int idToCheck) {
		databaseConnection.createConnection();
		String query = "SELECT idDepartament FROM petshopbd.departament WHERE idDepartament = \"" + idToCheck + "\"";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = databaseConnection.getConnection().prepareStatement(query);
			rs = ps.executeQuery();

			if (rs == null)
				return false;
			else
				return true;

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

		return false;
	}

	/* Get all departaments for a specific employee */

	public List<Departament> getAllDepartamenteFromAngajat(int idAngajat) {
		databaseConnection.createConnection();
		String query = "SELECT departament.idDepartament, departament.denumire, departament.nrRaioane FROM departament INNER JOIN angajatdepartament "
				+ "ON departament.idDepartament = angajatdepartament.idDepartament INNER JOIN angajat ON "
				+ "angajatdepartament.idAngajat = angajat.idAngajat WHERE angajat.idAngajat = \"" + idAngajat + "\"";
		List<Departament> departamente = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = databaseConnection.getConnection().prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				Departament departament = new Departament();
				departament.setIdDepartament(rs.getInt("idDepartament"));
				departament.setDenumire(rs.getString("denumire"));
				departament.setNrRaioane(rs.getInt("nrRaioane"));

				departamente.add(departament);
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

		return departamente;
	}

}
