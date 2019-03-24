package util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.DatabaseConnection;
import model.Angajat;

public class AngajatOperations {

	private DatabaseConnection databaseConnection;

	public AngajatOperations(DatabaseConnection databaseConnection) {
		this.databaseConnection = databaseConnection;
	}

	public List<Angajat> getAllAngajati() {
		databaseConnection.createConnection();
		String query = "SELECT idAngajat, nume, prenume, salariu, idPetShopAngajat FROM petshopbd.angajat";
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
				angajat.setIdPetShopAngajat(rs.getInt("idPetShopAngajat"));

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
			ps.setInt(5, angajat.getIdPetShopAngajat());
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

	public static void printListOfAngajati(List<Angajat> angajati) {
		for (Angajat a : angajati) {
			System.out.println(a.toString());
		}
	}
}
