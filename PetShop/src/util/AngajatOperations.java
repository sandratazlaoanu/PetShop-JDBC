package util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.DatabaseConnection;
import model.Angajat;
import util.PetShopOperations;

public class AngajatOperations {

	private DatabaseConnection databaseConnection;

	public AngajatOperations(DatabaseConnection databaseConnection) {
		this.databaseConnection = databaseConnection;
	}

	PetShopOperations petShop = new PetShopOperations(databaseConnection);

	/* Get list */

	public List<Angajat> getAllAngajati() {
		databaseConnection.createConnection();
		String query = "SELECT idAngajat, nume, prenume, salariu FROM petshopbd.angajat";
		List<Angajat> angajati = new ArrayList<Angajat>();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = databaseConnection.getConnection().prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				Angajat angajat = new Angajat();
				angajat.setIdAngajat(rs.getInt("idAngajat"));
				angajat.setNume(rs.getString("nume"));
				angajat.setPrenume(rs.getString("prenume"));
				angajat.setSalariu(rs.getFloat("salariu"));

				angajati.add(angajat);
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

		return angajati;
	}

	/* Add to list */

	public boolean addAngajat(Angajat angajat) {
		databaseConnection.createConnection();

		String query = "INSERT INTO angajat VALUES (?, ?, ?, ?, ?)";
		PreparedStatement ps = null;
		try {
			ps = databaseConnection.getConnection().prepareStatement(query);
			ps.setInt(1, angajat.getIdAngajat());
			ps.setString(2, angajat.getNume());
			ps.setString(3, angajat.getPrenume());
			ps.setFloat(4, angajat.getSalariu());
			ps.setInt(5, angajat.getPetShop());

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

	/* Update */

	public boolean updateAngajat(int idToCheck, String nume, String prenume, float salariu) {
		databaseConnection.createConnection();

		String query = "UPDATE angajat SET nume=?, prenume=?, salariu=? WHERE idAngajat = \"" + idToCheck + "\"";
		PreparedStatement ps = null;

		try {
			ps = databaseConnection.getConnection().prepareStatement(query);
			ps.setString(1, nume);
			ps.setString(2, prenume);
			ps.setFloat(3, salariu);
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

	public boolean deleteAngajat(int idToCheck) {
		databaseConnection.createConnection();

		String query = "DELETE FROM angajat WHERE idAngajat = \"" + idToCheck + "\" ";

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

	/* Print */

	public void printListOfAngajati(List<Angajat> angajati) {
		for (Angajat a : angajati) {
			System.out.println(a.toString());
		}
	}

	/* Check if we have a specific employee */

	public boolean checkAngajat(int idToCheck) {
		databaseConnection.createConnection();
		String query = "SELECT idAngajat FROM petshopbd.angajat WHERE idAngajat = \"" + idToCheck + "\"";
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
	/* Get List of employees from a specific departament */

	public List<Angajat> getAllAngajatiFromDepartament(int idDepartament) {
		databaseConnection.createConnection();
		String query = "SELECT angajat.idAngajat, angajat.nume, angajat.prenume, angajat.salariu FROM angajat "
				+ " INNER JOIN angajatdepartament ON angajat.idAngajat = angajatdepartament.idAngajat "
				+ " INNER JOIN departament ON angajatdepartament.idDepartament = departament.idDepartament "
				+ " WHERE departament.idDepartament = \"" + idDepartament + "\"";
		List<Angajat> angajati = new ArrayList<Angajat>();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = databaseConnection.getConnection().prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				Angajat angajat = new Angajat();
				angajat.setIdAngajat(rs.getInt("idAngajat"));
				angajat.setNume(rs.getString("nume"));
				angajat.setPrenume(rs.getString("prenume"));
				angajat.setSalariu(rs.getFloat("salariu"));

				angajati.add(angajat);
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

		return angajati;
	}
	/* Get employees list with salary greater than 1500 RON */

	public List<Angajat> getAngajatiBySalaryCondition() {
		databaseConnection.createConnection();
		String query = "SELECT angajat.idAngajat, angajat.nume, angajat.prenume, angajat.salariu FROM angajat INNER JOIN petshop ON angajat.idPetShopAngajat = petshop.idPetShop\r\n"
				+ " WHERE angajat.salariu > 1500";
		List<Angajat> angajati = new ArrayList<Angajat>();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = databaseConnection.getConnection().prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				Angajat angajat = new Angajat();
				angajat.setIdAngajat(rs.getInt("idAngajat"));
				angajat.setNume(rs.getString("nume"));
				angajat.setPrenume(rs.getString("prenume"));
				angajat.setSalariu(rs.getFloat("salariu"));

				angajati.add(angajat);
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

		return angajati;
	}
}
