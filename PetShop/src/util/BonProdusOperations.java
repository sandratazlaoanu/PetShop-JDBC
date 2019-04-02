package util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.DatabaseConnection;
import model.BonFiscal;
import model.BonProdus;
import model.Produs;

public class BonProdusOperations {

	private DatabaseConnection databaseConnection;

	public BonProdusOperations(DatabaseConnection databaseConnection) {
		this.databaseConnection = databaseConnection;
	}

	public List<BonProdus> getBonProdus() {
		databaseConnection.createConnection();
		String query = "SELECT * FROM petshopbd.bonprodus";
		List<BonProdus> list = new ArrayList<BonProdus>();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = databaseConnection.getConnection().prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				BonProdus bonProdus = new BonProdus();
				bonProdus.setIndex(rs.getInt("index"));
				bonProdus.setIdBonFiscal(rs.getInt("indexBonFiscal"));
				bonProdus.setIdProdus(rs.getInt("indexProdus"));

				list.add(bonProdus);
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

		return list;
	}

	public void addBonProdus(BonProdus bonProdus) {
		databaseConnection.createConnection();
		int index = lastBonProdusIndex();
		bonProdus.setIndex(index);

		String query = "INSERT INTO bonprodus VALUES (?, ?, ?)";
		PreparedStatement ps = null;
		try {
			ps = databaseConnection.getConnection().prepareStatement(query);
			ps.setInt(1, bonProdus.getIndex());
			ps.setInt(2, bonProdus.getIdBonFiscal());
			ps.setInt(3, bonProdus.getIdProdus());

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

	public boolean updateBonProdus(int idToCheck, int idBonFiscal, int idProdus) {
		databaseConnection.createConnection();

		String query = "UPDATE bonprodus SET indexBonFiscal=?, indexProdus=? WHERE index = \"" + idToCheck + "\"";
		PreparedStatement ps = null;

		try {
			ps = databaseConnection.getConnection().prepareStatement(query);
			ps.setInt(1, idBonFiscal);
			ps.setInt(2, idProdus);
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

	public boolean deleteBonProdus(int idToCheck) {
		databaseConnection.createConnection();

		String query = "DELETE FROM bonprodus WHERE index = \"" + idToCheck + "\" ";

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

	public Integer lastBonIndex() {
		Integer index;
		BonFiscalOperations operation = new BonFiscalOperations(databaseConnection);
		List<BonFiscal> list = operation.getAllBonuri();
		if (list.size() > 0)
			index = list.get(list.size() - 1).getIdBonFiscal() + 1;
		else
			index = 1;
		return index;
	}

	public Integer lastProdusIndex() {
		Integer index;
		ProdusOperations operation = new ProdusOperations(databaseConnection);
		List<Produs> list = operation.getAllProduse();
		if (list.size() > 0)
			index = list.get(list.size() - 1).getId() + 1;
		else
			index = 1;
		return index;
	}

	public Integer lastBonProdusIndex() {
		Integer index;
		List<BonProdus> list = getBonProdus();
		if (list.size() > 0)
			index = list.get(list.size() - 1).getIndex() + 1;
		else
			index = 1;
		return index;
	}

	public void printListOfBonProdus(List<BonProdus> bonProdus) {
		for (BonProdus bp : bonProdus) {
			System.out.println(bp.toString());
		}
	}

}
