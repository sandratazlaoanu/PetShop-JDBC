package util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.DatabaseConnection;
import model.Client;

public class ClientOperations {

	private DatabaseConnection databaseConnection;

	public ClientOperations(DatabaseConnection databaseConnection) {
		this.databaseConnection = databaseConnection;
	}

	/* Get list */

	public List<Client> getAllClienti() {
		databaseConnection.createConnection();
		String query = "SELECT idClient, nume, prenume, idPetShopClient FROM petshopbd.client";
		List<Client> clienti = new ArrayList<Client>();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = databaseConnection.getConnection().prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				Client client = new Client();
				client.setIdClient(rs.getInt("idClient"));
				client.setNume(rs.getString("nume"));
				client.setPrenume(rs.getString("prenume"));
				client.setPetShop(rs.getInt("idPetShopClient"));

				clienti.add(client);
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

		return clienti;
	}

	/* Add to list */

	public boolean addClient(Client client) {
		databaseConnection.createConnection();

		String query = "INSERT INTO client VALUES (?, ?, ?, ?)";
		PreparedStatement ps = null;
		try {
			ps = databaseConnection.getConnection().prepareStatement(query);
			ps.setInt(1, client.getIdClient());
			ps.setString(2, client.getNume());
			ps.setString(3, client.getPrenume());
			ps.setInt(4, client.getPetShop());

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

	public boolean updateClient(int idToCheck, String nume, String prenume) {

		databaseConnection.createConnection();

		String query = "UPDATE client SET nume=?, prenume=? WHERE idClient= \"" + idToCheck + "\"";
		PreparedStatement ps = null;

		try {
			ps = databaseConnection.getConnection().prepareStatement(query);
			ps.setString(1, nume);
			ps.setString(2, prenume);

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

		String query = "DELETE FROM client WHERE idClient = \"" + idToCheck + "\" ";

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

	public void printListOfClienti(List<Client> clienti) {
		for (Client client : clienti) {
			System.out.println(client.toString());
		}
	}

	/* Check */

	public boolean checkClient(int idToCheck) {
		databaseConnection.createConnection();
		String query = "SELECT idClient FROM petshopbd.client WHERE idClient = \"" + idToCheck + "\"";
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

	/* Get a client which has a specific bill */

	public Client getClientFromBon(int idBon) {
		databaseConnection.createConnection();
		String query = "SELECT client.idClient, client.nume, client.prenume FROM client"
				+ " INNER JOIN bonfiscal ON client.idClient = bonfiscal.idClient" + " WHERE bonfiscal.idBonFiscal = \""
				+ idBon + "\"";
		PreparedStatement ps = null;
		ResultSet rs = null;
		Client client = new Client();

		try {
			ps = databaseConnection.getConnection().prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				client.setIdClient(rs.getInt("idClient"));
				client.setNume(rs.getString("nume"));
				client.setPrenume(rs.getString("prenume"));

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

		return client;
	}
}
