package util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.DatabaseConnection;
import model.Furnizor;

public class FurnizorOperations {

	private DatabaseConnection databaseConnection;

	public FurnizorOperations(DatabaseConnection databaseConnection) {
		this.databaseConnection = databaseConnection;
	}

	/* Get list */

	public List<Furnizor> getAllFurnizori() {
		databaseConnection.createConnection();
		String query = "SELECT idFurnizor, denumire, idCont FROM petshopbd.furnizor";
		List<Furnizor> furnizori = new ArrayList<Furnizor>();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = databaseConnection.getConnection().prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				Furnizor furnizor = new Furnizor();
				furnizor.setIdFurnizor(rs.getInt("idFurnizor"));
				furnizor.setDenumire(rs.getString("denumire"));
				furnizor.setIdCont(rs.getString("idCont"));

				furnizori.add(furnizor);
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

		return furnizori;
	}

	/* Add to list */

	public boolean addFurnizor(Furnizor furnizor) {
		databaseConnection.createConnection();

		String query = "INSERT INTO furnizor VALUES (?, ?, ?)";
		PreparedStatement ps = null;
		try {
			ps = databaseConnection.getConnection().prepareStatement(query);
			ps.setInt(1, furnizor.getIdFurnizor());
			ps.setString(2, furnizor.getDenumire());
			ps.setString(3, furnizor.getIdCont());

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

	public boolean updateFurnzior(int idToCheck, String denumire, String idCont) {

		databaseConnection.createConnection();

		String query = "UPDATE furnizor SET denumire=?, idCont=? WHERE idFurnizor= \"" + idToCheck + "\"";
		PreparedStatement ps = null;

		try {
			ps = databaseConnection.getConnection().prepareStatement(query);
			ps.setString(1, denumire);
			ps.setString(2, idCont);

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

	public boolean deleteFurnizor(int idToCheck) {
		databaseConnection.createConnection();

		String query = "DELETE FROM furnizor WHERE idFurnizor = \"" + idToCheck + "\" ";

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

	public void printListOfFurnizori(List<Furnizor> furnizori) {
		for (Furnizor furnizor : furnizori) {
			System.out.println(furnizor.toString());
		}
	}

	/* Check */

	public boolean checkFurnizor(int idToCheck) {
		databaseConnection.createConnection();
		String query = "SELECT idFurnizor FROM furnizor WHERE idFurnizor= \"" + idToCheck + "\"";
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
