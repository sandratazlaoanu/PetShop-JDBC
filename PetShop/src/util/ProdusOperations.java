package util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.DatabaseConnection;
import model.Produs;

public class ProdusOperations {

	private DatabaseConnection databaseConnection;

	public ProdusOperations(DatabaseConnection databaseConnection) {
		this.databaseConnection = databaseConnection;
	}

	/* Get list */

	public List<Produs> getAllProduse() {
		databaseConnection.createConnection();
		String query = "SELECT idProdus, produs.denumire, cantitate, pret, idFurnizor, idPetShopProdus FROM petshopbd.produs ";
		List<Produs> produse = new ArrayList<Produs>();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = databaseConnection.getConnection().prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				Produs produs = new Produs();
				produs.setId(rs.getInt("idProdus"));
				produs.setDenumire(rs.getString("denumire"));
				produs.setCantitate(rs.getInt("cantitate"));
				produs.setPret(rs.getFloat("pret"));
				produs.setIdFurnizor(rs.getInt("idFurnizor"));
				produs.setIdPetShop(rs.getInt("idPetShopProdus"));

				produse.add(produs);
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

		return produse;
	}

	/* Add to list */

	public boolean addProdus(Produs produs) {
		databaseConnection.createConnection();

		String query = "INSERT INTO produs VALUES (?, ?, ?, ?, ?, ?)";
		PreparedStatement ps = null;
		try {
			ps = databaseConnection.getConnection().prepareStatement(query);
			ps.setInt(1, produs.getId());
			ps.setString(2, produs.getDenumire());
			ps.setInt(3, produs.getCantitate());
			ps.setFloat(4, produs.getPret());
			ps.setInt(5, produs.getIdFurnizor());
			ps.setInt(6, produs.getIdPetShop());

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

	public boolean updateProdus(int idToCheck, String denumire, int cantitate, float pret) {

		databaseConnection.createConnection();

		String query = "UPDATE produs SET denumire=?, cantitate=?, pret=? WHERE idProdus = \"" + idToCheck + "\"";
		PreparedStatement ps = null;

		try {
			ps = databaseConnection.getConnection().prepareStatement(query);
			ps.setString(1, denumire);
			ps.setInt(2, cantitate);
			ps.setFloat(3, pret);

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

	public boolean deleteProdus(int idToCheck) {
		databaseConnection.createConnection();

		String query = "DELETE FROM produs WHERE idProdus = \"" + idToCheck + "\" ";

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

	public void printListOfProduse(List<Produs> produse) {
		for (Produs produs : produse) {
			System.out.println(produs.toString());
		}
	}

	/* Check */

	public boolean checkProdus(int idToCheck) {
		databaseConnection.createConnection();
		String query = "SELECT idProdus FROM produs WHERE idProdus = \"" + idToCheck + "\"";
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

	/* Get list of products from a specific provider */

	public List<Produs> getAllProduseFromFurnizor(int idFurnizor) {
		databaseConnection.createConnection();
		String query = "SELECT produs.idProdus, produs.denumire, produs.cantitate, produs.pret FROM produs INNER JOIN furnizor "
				+ " ON produs.idFurnizor = furnizor.idFurnizor WHERE furnizor.idFurnizor = \"" + idFurnizor + "\"";
		List<Produs> produse = new ArrayList<Produs>();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = databaseConnection.getConnection().prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				Produs produs = new Produs();
				produs.setId(rs.getInt("idProdus"));
				produs.setDenumire(rs.getString("denumire"));
				produs.setCantitate(rs.getInt("cantitate"));
				produs.setPret(rs.getFloat("pret"));

				produse.add(produs);
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

		return produse;
	}

}
