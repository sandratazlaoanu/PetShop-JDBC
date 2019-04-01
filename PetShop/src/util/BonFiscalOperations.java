package util;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.DatabaseConnection;

import model.BonFiscal;

public class BonFiscalOperations {

	private DatabaseConnection databaseConnection;

	public BonFiscalOperations(DatabaseConnection databaseConnection) {
		this.databaseConnection = databaseConnection;
	}

	/* Get list */

	public List<BonFiscal> getAllBonuri() {
		databaseConnection.createConnection();
		String query = "SELECT idBonFiscal, suma, data FROM petshopbd.bonfiscal";
		List<BonFiscal> bonuri = new ArrayList<BonFiscal>();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = databaseConnection.getConnection().prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				BonFiscal bonFiscal = new BonFiscal();
				bonFiscal.setIdBonFiscal(rs.getInt("idBonFiscal"));
				bonFiscal.setSuma(rs.getFloat("suma"));
				bonFiscal.setData(rs.getDate("data"));

				bonuri.add(bonFiscal);
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

		return bonuri;
	}

	/* Add to list */

	public boolean addBonFiscal(BonFiscal bonFiscal) {
		databaseConnection.createConnection();

		String query = "INSERT INTO bonfiscal VALUES (?, ?, ?, ?)";
		PreparedStatement ps = null;
		try {
			ps = databaseConnection.getConnection().prepareStatement(query);
			ps.setInt(1, bonFiscal.getIdBonFiscal());
			ps.setFloat(2, bonFiscal.getSuma());
			ps.setDate(3, (Date) bonFiscal.getData());
			ps.setInt(4, bonFiscal.getIdClient());

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

	public boolean updateBonFiscal(int idToCheck, float suma, Date data) {
		databaseConnection.createConnection();

		String query = "UPDATE bonfiscal SET suma=?, data=? WHERE idBonFiscal = \"" + idToCheck + "\"";
		PreparedStatement ps = null;

		try {
			ps = databaseConnection.getConnection().prepareStatement(query);
			ps.setFloat(1, suma);
			ps.setDate(2, data);

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

	public boolean deleteBonFiscal(int idToCheck) {
		databaseConnection.createConnection();

		String query = "DELETE FROM bonfiscal WHERE idBonFiscal = \"" + idToCheck + "\" ";

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

	public void printListOfBonuri(List<BonFiscal> bonuri) {
		for (BonFiscal bonFiscal : bonuri) {
			System.out.println(bonFiscal.toString());
		}
	}

	/* Check */

	public boolean checkBonFiscal(int idToCheck) {
		databaseConnection.createConnection();
		String query = "SELECT idBonFiscal FROM petshopbd.bonfiscal WHERE idBonFiscal = \"" + idToCheck + "\"";
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

	/* Get all bills which belong to a specific client */

	public List<BonFiscal> getAllBonuriFromClient(int idClient) {
		databaseConnection.createConnection();
		String query = "SELECT idBonFiscal, data, suma FROM bonfiscal"
				+ " INNER JOIN client ON bonfiscal.idClient = client.idClient WHERE client.idClient = \"" + idClient
				+ "\"";
		List<BonFiscal> bonuri = new ArrayList<BonFiscal>();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = databaseConnection.getConnection().prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				BonFiscal bonFiscal = new BonFiscal();
				bonFiscal.setIdBonFiscal(rs.getInt("idBonFiscal"));
				bonFiscal.setSuma(rs.getFloat("suma"));
				bonFiscal.setData(rs.getDate("data"));

				bonuri.add(bonFiscal);
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

		return bonuri;
	}

}
