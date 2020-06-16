import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.sql.ResultSet;

//package com.ibm.training;

public class Main {
	

	public static void main(String[] args) {
		try {
			//load the driver
			Class.forName("com.mysql.jdbc.Driver");
			Connection dbCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/ibmtraining?serverTimezone=UTC","root","");
			System.out.println("Connected Successfully!!!");
			new Main().getAllData(dbCon);
		}catch(ClassNotFoundException e){
			System.out.println("Exception while loading driver");
		}catch(SQLException e) {
			System.out.println("Error connecting to database"+e.getMessage());
		}
		

	}
	
	void getAllData(Connection dbCon) {
		try {
			Statement stmt = dbCon.createStatement();
			
			//write the query to fetch the data from the table: user_details
			String fetchQry = "select * from user_details";
			
			//execute query
			ResultSet rs = stmt.executeQuery(fetchQry);
			
			//Traverse through ResultSet
			while(rs.next()) {
				System.out.println("User ID: " + rs.getInt("userId"));
				System.out.println("User name: " + rs.getString("userName"));
			}
			
			//Close the connection
			dbCon.close();
		} catch (SQLException e) {
			System.out.println("Exception while creating the statement "+ e.getMessage());
		}
	}
	
	//insert a new row into the table: userDetails
	void insertData(Connection dbCon) {
		//Write the query to insert a new row in the table
		String insertQry = "insert into user_details values('Vinay',5,'Delhi')";
		try {
			Statement stmt = dbCon.createStatement();
			if(stmt.executeUpdate(insertQry)>0) {
				System.out.println("Successfully inserted a new row..");
			}
			else {
				System.out.println("Some issues while inserting");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	void insertFromRuntime(Connection dbCon) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Please enter your name and address:");
		String userName = scan.nextLine();
		String userAddress = scan.nextLine();
		
		String insertQry = "insert into user_details values"+"("+"'"+userName+"'"+","+"'"+userAddress+"'"+")";
	}

}
