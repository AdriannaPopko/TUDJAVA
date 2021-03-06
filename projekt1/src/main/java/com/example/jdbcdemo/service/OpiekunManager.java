package com.example.jdbcdemo.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.jdbcdemo.domain.Opiekun;

public class OpiekunManager {

	private Connection connection;

	private String url = "jdbc:hsqldb:hsql://localhost/workdb";

	private String createTableOpiekun = "CREATE TABLE Opiekun(opiekun_id bigint GENERATED BY DEFAULT AS IDENTITY, imie varchar(20), nazwisko varchar(20), nr_tel integer)";

	private PreparedStatement DodajOpiekuna;
	private PreparedStatement UsunOpiekunow;
	private PreparedStatement UsunOpiekuna;
	private PreparedStatement GetOpiekunow;
	private PreparedStatement UpdateOpiekun;

	private Statement statement;

	public OpiekunManager() {
		try {
			connection = DriverManager.getConnection(url);
			statement = connection.createStatement();

			ResultSet rs = connection.getMetaData().getTables(null, null, null,
					null);
			boolean tableExists = false;
			while (rs.next()) {
				if ("Opiekun".equalsIgnoreCase(rs.getString("TABLE_NAME"))) {
					tableExists = true;
					break;
				}
			}

			if (!tableExists)
				statement.executeUpdate(createTableOpiekun);

			DodajOpiekuna = connection
					.prepareStatement("INSERT INTO Opiekun (imie, nazwisko, nr_tel) VALUES (?, ?, ?)");
			UsunOpiekunow = connection
					.prepareStatement("DELETE FROM Opiekun");
			UsunOpiekuna = connection
					.prepareStatement("DELETE FROM Opiekun WHERE opiekun_id = ?");
			GetOpiekunow = connection
					.prepareStatement("SELECT opiekun_id, imie, nazwisko, nr_tel FROM Opiekun");
			UpdateOpiekun = connection
					.prepareStatement("UPDATE Opiekun SET imie = ?, nazwisko = ?, nr_tel = ? WHERE opiekun_id = ?");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	Connection getConnection() {
		return connection;
	}

	void wyczyscOpiekunow() {
		try {
			UsunOpiekunow.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int usunOpiekuna(Opiekun opiekun) {
		int count = 0;
		try {
			UsunOpiekuna.setLong(1, opiekun.getId());

			count = UsunOpiekuna.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	public int dodajOpiekuna(Opiekun opiekun) {
		int count = 0;
		try {
			DodajOpiekuna.setString(1, opiekun.getImie());
			DodajOpiekuna.setString(2, opiekun.getNazwisko());
			DodajOpiekuna.setInt(3, opiekun.getNr());

			count = DodajOpiekuna.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

/*	public List<Opiekun> GetOpiekunow() {
		List<Opiekun> opiekunowie = new ArrayList<Opiekun>();

		try {
			ResultSet rs = GetOpiekunow.executeQuery();

			while (rs.next()) {
				Opiekun p = new Opiekun();
				p.setId(rs.getInt("opiekun_id"));
				p.setImie(rs.getString("imie"));
				p.setNazwisko(rs.getString("nazwisko"));
				p.setNr(rs.getInt("nr_tel"));
				opiekunowie.add(p);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return opiekunowie;
	} */

	public List<Opiekun> GetOpiekunow(){
		List<Opiekun> opiekunowie = new ArrayList<Opiekun>();

		try{
			ResultSet rs = GetOpiekunow.executeQuery();

			while(rs.next()){
				Opiekun o = new Opiekun();
				o.setId(rs.getLong("opiekun_id"));
				o.setImie(rs.getString("imie"));
				o.setNazwisko(rs.getString("nazwisko"));
				o.setNr(rs.getInt("nr_tel"));
				opiekunowie.add(o);
			}
		} catch(SQLException e){
			e.printStackTrace();
		}
		return opiekunowie;
	}

	public int updateOpiekun(Opiekun opiekun){
		int count = 0;
		try{
			UpdateOpiekun.setString(1, opiekun.getImie());
			UpdateOpiekun.setString(2, opiekun.getNazwisko());
			UpdateOpiekun.setLong(3, opiekun.getId());
			UpdateOpiekun.setInt(4, opiekun.getNr());


			count = UpdateOpiekun.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return count;
	}


}
