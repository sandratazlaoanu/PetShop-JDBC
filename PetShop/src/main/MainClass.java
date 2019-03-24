package main;

import java.util.List;

import database.Database;
import database.DatabaseConnection;
import model.Angajat;
import util.AngajatOperations;

public class MainClass {
	public static void main(String[] args) {
		Database database = new Database("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/petshopbd", "root", "1q2w3e");
		DatabaseConnection databaseConnection = new DatabaseConnection(database);
		AngajatOperations studentOperations = new AngajatOperations(databaseConnection);
		
		System.out.println("Before adding...");
		List<Angajat> angajati = studentOperations.getAllAngajati();
		studentOperations.printListOfAngajati(angajati);
		
		Angajat  angajatToAdd = new Angajat(11,"Serban","Andreea",1900.0f,1);
		
		studentOperations.addAngajat(angajatToAdd);
		
		System.out.println("After adding...");
		angajati  = studentOperations.getAllAngajati();
		AngajatOperations.printListOfAngajati(angajati);
	}
}