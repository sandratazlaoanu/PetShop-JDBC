package main;

import java.io.IOException;
import java.sql.SQLException;

import menu.MenuClass;

public class MainClass {
	public static void main(String[] args) throws NumberFormatException, IOException, SQLException {
		MenuClass ob = new MenuClass();
		ob.menu();
	}
}