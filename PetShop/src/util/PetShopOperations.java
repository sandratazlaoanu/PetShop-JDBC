package util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import database.DatabaseConnection;
import model.PetShop;

public class PetShopOperations {
	private DatabaseConnection databaseConnection;

	public PetShopOperations(DatabaseConnection databaseConnection) {
		this.databaseConnection = databaseConnection;
	}

	/* Get list */

	public PetShop getPetShopInfo() {
		databaseConnection.createConnection();
		String query = "SELECT nume, nrTelefon, email, zip FROM petshopbd.petshop";
		PetShop petShop = new PetShop();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = databaseConnection.getConnection().prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				petShop.setIdPetShop(rs.getInt("idPetShop"));
				petShop.setNume(rs.getString("nume"));
				petShop.setNrTelefon(rs.getString("nrTelefon"));
				petShop.setEmail(rs.getString("email"));
				petShop.setIdAdresa(rs.getInt("zip"));
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

		return petShop;
	}

	/* Add to list */

	public boolean addPetShop(PetShop petShop) {
		databaseConnection.createConnection();

		String query = "INSERT INTO petshop VALUES (?, ?, ?, ?)";
		PreparedStatement ps = null;
		try {
			ps = databaseConnection.getConnection().prepareStatement(query);
			ps.setInt(1, petShop.getIdPetShop());
			ps.setString(2, petShop.getNume());
			ps.setString(3, petShop.getNrTelefon());
			ps.setString(4, petShop.getEmail());

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

	public boolean updatePetShop(int idToCheck, String nume, String nrTelefon, String email) {

		databaseConnection.createConnection();

		String query = "UPDATE petshop SET nume=?, nrTelefon=?, email=? WHERE idPetShop = \"" + idToCheck + "\"";
		PreparedStatement ps = null;

		try {
			ps = databaseConnection.getConnection().prepareStatement(query);
			ps.setString(1, nume);
			ps.setString(2, nrTelefon);
			ps.setString(3, email);

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

	public boolean deleteClient(int idToCheck) {
		databaseConnection.createConnection();

		String query = "DELETE FROM petshop WHERE idPetShop = \"" + idToCheck + "\" ";

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

	public void printPetShop(List<PetShop> petShop) {
		for (PetShop ps : petShop) {
			System.out.println(ps.toString());
		}
	}

	/* Check */

	public boolean checkPetShop(int idToCheck) {
		databaseConnection.createConnection();
		String query = "SELECT idPetShop FROM petshopbd.petshop WHERE idPetShop = \"" + idToCheck + "\"";
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
}
