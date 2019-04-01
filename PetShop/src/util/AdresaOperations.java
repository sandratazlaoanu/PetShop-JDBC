package util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import database.DatabaseConnection;
import model.Adresa;

public class AdresaOperations {

	private DatabaseConnection databaseConnection;

	public AdresaOperations(DatabaseConnection databaseConnection) {
		this.databaseConnection = databaseConnection;
	}

	public Adresa getAdresa() {
		databaseConnection.createConnection();
		String query = "SELECT zip, judet, oras, strada, numar FROM petshopbd.adresa";
		Adresa adresa = new Adresa();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = databaseConnection.getConnection().prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {

				adresa.setZip(rs.getInt("zip"));
				adresa.setJudet(rs.getString("judet"));
				adresa.setOras(rs.getString("oras"));
				adresa.setStrada(rs.getString("strada"));
				adresa.setNumar(rs.getInt("numar"));
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

		return adresa;
	}

	public boolean addAdresa(Adresa adresa) {
		databaseConnection.createConnection();
		String query = "INSERT INTO angajat VALUES (?, ?, ?, ?, ?)";
		PreparedStatement ps = null;
		try {
			ps = databaseConnection.getConnection().prepareStatement(query);
			ps.setInt(1, adresa.getZip());
			ps.setString(2, adresa.getJudet());
			ps.setString(3, adresa.getOras());
			ps.setString(4, adresa.getStrada());
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

	public boolean updateAdresa(int zip, String judet, String oras, String strada, int numar) {

		databaseConnection.createConnection();

		String query = "UPDATE adresa SET judet=?, oras=?, strada=? numar=? WHERE zip = \"" + zip + "\"";
		PreparedStatement ps = null;

		try {
			ps = databaseConnection.getConnection().prepareStatement(query);
			ps.setString(1, judet);
			ps.setString(2, oras);
			ps.setString(3, strada);
			ps.setInt(4, numar);

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
	public boolean deleteAdresa(int zip) {
		databaseConnection.createConnection();

		String query = "DELETE FROM adresa WHERE zip = \"" + zip + "\" ";

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

	public void printAdresa(Adresa adresa) {
		System.out.println(adresa);
	}

	public boolean checkAdresa(int zipToCheck) {
		databaseConnection.createConnection();
		String query = "SELECT zip FROM adresa WHERE zip = \"" + zipToCheck + "\"";
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

	public Adresa getAdresaPetShop() {
		databaseConnection.createConnection();
		String query = "SELECT adresa.zip, adresa.judet, adresa.oras, adresa.strada, adresa.numar FROM adresa INNER JOIN petshop ON adresa.zip = petshop.zip ";
		Adresa adresa = new Adresa();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = databaseConnection.getConnection().prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {

				adresa.setZip(rs.getInt("zip"));
				adresa.setJudet(rs.getString("judet"));
				adresa.setOras(rs.getString("oras"));
				adresa.setStrada(rs.getString("strada"));
				adresa.setNumar(rs.getInt("numar"));
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

		return adresa;
	}

}
