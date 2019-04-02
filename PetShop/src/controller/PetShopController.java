package controller;

import java.sql.Date;
import java.util.List;

import database.Database;
import database.DatabaseConnection;
import model.Adresa;
import model.Angajat;
import model.AngajatDepartament;
import model.BonFiscal;
import model.Client;
import model.Departament;
import model.Produs;
import util.AdresaOperations;
import util.AngajatDepartamentOperations;
import util.AngajatOperations;
import util.BonFiscalOperations;
import util.BonProdusOperations;
import util.ClientOperations;
import util.DepartamentOperations;
import util.FurnizorOperations;
import util.PetShopOperations;
import util.ProdusOperations;

public class PetShopController {
	Database database = new Database("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/petshopbd", "root",
			"1q2w3e");

	DatabaseConnection databaseConnection = new DatabaseConnection(database);

	AdresaOperations adresaOperations = new AdresaOperations(databaseConnection);
	AngajatOperations angajatOperations = new AngajatOperations(databaseConnection);
	DepartamentOperations departamentOperations = new DepartamentOperations(databaseConnection);
	ClientOperations clientOperations = new ClientOperations(databaseConnection);
	FurnizorOperations furnizorOperations = new FurnizorOperations(databaseConnection);
	AngajatDepartamentOperations angajatDepartamentOperations = new AngajatDepartamentOperations(databaseConnection);
	BonFiscalOperations bonFiscalOperations = new BonFiscalOperations(databaseConnection);
	BonProdusOperations bonProdusOperations = new BonProdusOperations(databaseConnection);
	ProdusOperations produsOperations = new ProdusOperations(databaseConnection);
	PetShopOperations petShopOperations = new PetShopOperations(databaseConnection);

	/* Controller for angajat */

	public void createAngajat(int id, String nume, String prenume, float salariu, int idPetShop) {
		if (petShopOperations.checkPetShop(idPetShop) == false)
			System.out.println("PetShop not found.");
		if (angajatOperations.checkAngajat(id) == true)
			System.out.println("Angajat existent.");
		Angajat angajatToAdd = new Angajat(id, nume, prenume, salariu, idPetShop);
		List<Angajat> angajati = angajatOperations.getAllAngajati();
		System.out.println("After adding...");
		angajatOperations.addAngajat(angajatToAdd);
		angajatOperations.printListOfAngajati(angajati);

	}

	public void updateAngajat(int id, String nume, String prenume, float salariu) {

		if (angajatOperations.checkAngajat(id) == false)
			System.out.println("Not found.");
		angajatOperations.updateAngajat(id, nume, prenume, salariu);
	}

	public void deleteAngajat(int id) {

		if (angajatOperations.checkAngajat(id) == false)
			System.out.println("Not found.");
		angajatOperations.deleteAngajat(id);

	}
	/* Controller for client */

	public void createClient(int id, String nume, String prenume, int idPetShop) {
		if (petShopOperations.checkPetShop(idPetShop) == false)
			System.out.println("PetShop not found.");

		Client clientToAdd = new Client(id, nume, prenume, idPetShop);
		System.out.println("Before adding...");
		List<Client> list = clientOperations.getAllClienti();
		clientOperations.addClient(clientToAdd);
		System.out.println("After adding...");
		clientOperations.printListOfClienti(list);
	}

	public void updateClient(int id, String nume, String prenume) {

		if (clientOperations.checkClient(id) == false)
			System.out.println("Not found.");
		clientOperations.updateClient(id, nume, prenume);
	}

	public void deleteClient(int id) {

		if (clientOperations.checkClient(id) == false)
			System.out.println("Not found.");
		clientOperations.deleteClient(id);

	}

	/* Util function if you want to set a departament for your employee */

	public void createDepartament(int id, String denumire, int numarRaioane) {
		if (departamentOperations.checkDepartament(id) == true)
			System.out.println("Departament existent!");
		Departament newDepartament = new Departament(id, denumire, numarRaioane);
		List<Departament> list = departamentOperations.getAllDepartamente();
		departamentOperations.addDepartament(newDepartament);
		departamentOperations.printListOfDepartamente(list);
	}

	/* Angajat-Departament */

	public void createAngajatDepartament(int idAngajat, int idDepartament) {
		AngajatDepartament repartizare = new AngajatDepartament(idAngajat, idDepartament);
		angajatDepartamentOperations.addAngajatDepartament(repartizare);
	}

	/* Controller for bon fiscal */

	public void createBonFiscal(int idBonFiscal, Date data, float suma, int idClient) {
		if (bonFiscalOperations.checkBonFiscal(idBonFiscal) == true) {
			System.out.println("Bon fiscal existent!");

			BonFiscal bonFiscal = new BonFiscal(idBonFiscal, data, suma);
			bonFiscal.setIdClient(idClient);
			List<BonFiscal> list = bonFiscalOperations.getAllBonuri();
			bonFiscalOperations.addBonFiscal(bonFiscal);
			bonFiscalOperations.printListOfBonuri(list);
		}
	}

	public void updateBonFiscal(int id, Date data, float suma) {

		if (bonFiscalOperations.checkBonFiscal(id))
			System.out.println("Not found.");
		bonFiscalOperations.updateBonFiscal(id, suma, data);
	}

	public void deleteBonFiscal(int id) {

		if (bonFiscalOperations.checkBonFiscal(id))
			System.out.println("Not found.");
		bonFiscalOperations.deleteBonFiscal(id);

	}

	/* Util for JOIN examples */

	/* Util function - gets all employees from a departament */

	public void getAngajatiFromDepartament(int id) {
		List<Angajat> list = angajatOperations.getAllAngajatiFromDepartament(id);
		angajatOperations.printListOfAngajati(list);
	}

	/* Gets a client from a bill */

	public void getClientFromBon(int id) {
		Client client = clientOperations.getClientFromBon(id);
		System.out.println(client.toString());
	}

	/* Gets all bills from a client */

	public void getBonuri(int id) {
		List<BonFiscal> bonuriClient = bonFiscalOperations.getAllBonuriFromClient(id);
		bonFiscalOperations.printListOfBonuri(bonuriClient);
	}

	/* Gets employees with a specific salary (>1500) */

	public void getAngajati() {
		List<Angajat> angajatiWithSalary = angajatOperations.getAngajatiBySalaryCondition();
		angajatOperations.printListOfAngajati(angajatiWithSalary);
	}

	/* Get all products from a specific provider */

	public void getProduseFromFurnizor(int id) {
		List<Produs> productsFromProvider = produsOperations.getAllProduseFromFurnizor(id);
		produsOperations.printListOfProduse(productsFromProvider);
	}

	/* Gets all departaments knowing the employee's id */

	public void getDepartamenteAngajat(int id) {
		List<Departament> departamentsOfEmployee = departamentOperations.getAllDepartamenteFromAngajat(id);
		departamentOperations.printListOfDepartamente(departamentsOfEmployee);
	}

	/* Gets address */

	public void getAdresaPetShop() {
		Adresa adresa = adresaOperations.getAdresaPetShop();
		adresaOperations.printAdresa(adresa);
	}
}
