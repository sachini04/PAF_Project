package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.*;
import java.sql.ResultSet;
import java.sql.Statement;

public class Fund {
	
	
	
	private Connection connect()
	 {
	 Connection con = null;
	 try
	 {
	 Class.forName("com.mysql.jdbc.Driver");

	 //Provide the correct details: DBServer/DBName, username, password
	 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test", "root", "");
	 
	 
	 }
	 catch (Exception e)
	 {e.printStackTrace();}
	 return con;
	 }
	public String insertFund(String researcherid, String researchername, String date,String amount, String desc)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for inserting."; }
	 // create a prepared statement
	 String query = " insert into funds(`fundID`,`ResearcherId`,`ResearcherName`, `fundDate`,`fundAmount`,`fundDesc`)"
	 + " values (?, ?, ?, ?, ?,?)";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setInt(1, 0);
	 preparedStmt.setString(2, researcherid);
	 preparedStmt.setString(3, researchername);
	 preparedStmt.setString(4, date);
	 preparedStmt.setDouble(5, Double.parseDouble(amount));
	 preparedStmt.setString(6, desc);
	// execute the statement
	 
	 preparedStmt.execute();
	 con.close();
	 output = "Inserted successfully";
	 }
	 catch (Exception e)
	 {
	 output = "Error while inserting the fund.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 } 
	
	
	public String readFunds()
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for reading."; }
	 // Prepare the html table to be displayed
	 output = "<table border='1'><tr><th>Researcher Id</th><th>Researcher Name</th>" +
	 "<th>Fund Date</th>" +
	 "<th>Fund Amount</th>" +
	 "<th>Fund Description</th>" +
	 "<th>Update</th><th>Remove</th></tr>";

	 String query = "select * from funds";
	 Statement stmt = con.createStatement();
	 ResultSet rs = stmt.executeQuery(query);
	 // iterate through the rows in the result set
	 while (rs.next())
	 {
	 String fundID = Integer.toString(rs.getInt("fundID"));
	 String ResearcherId = rs.getString("ResearcherId");
	 String ResearcherName = rs.getString("ResearcherName");
	 String fundDate = rs.getString("fundDate");
	 String fundAmount = Double.toString(rs.getDouble("fundAmount"));
	 String fundDesc = rs.getString("fundDesc");
	 // Add into the html table
	 output += "<tr><td>" + ResearcherId + "</td>";
	 output += "<td>" + ResearcherName + "</td>";
	 output += "<td>" + fundDate + "</td>";
	 output += "<td>" + fundAmount + "</td>";
	 output += "<td>" + fundDesc + "</td>";
	 
	 // buttons
	 output += "<td><input name='btnUpdate' "
			 + " type='button' value='Update'></td>"
			 + "<td><form method='post' action='funds.jsp'>"
			 + "<input name='btnRemove' "
			 + " type='submit' value='Remove'>"
			 + "<input name='fundID' type='hidden' "
			 + " value='" + fundID + "'>" + "</form></td></tr>";
	 }
	 con.close();
	 // Complete the html table
	 output += "</table>";
	 }
	 catch (Exception e)
	 {
	 output = "Error while reading the funds.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 } 
	
	public String updateFund(String fundID,String researcherid, String researchername, String date,String amount, String desc)
	{
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for updating."; }
		 // create a prepared statement
		 String query = "UPDATE funds SET ResearcherId=?,ResearcherName=?,fundDate=?,fundAmount=?,fundDesc=? WHERE fundID=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 preparedStmt.setString(1, researcherid);
		 preparedStmt.setString(2, researchername);
		 preparedStmt.setString(3, date);
		 preparedStmt.setDouble(4, Double.parseDouble(amount));
		 preparedStmt.setString(5, desc);
		 preparedStmt.setInt(6, Integer.parseInt(fundID));
		 // execute the statement
		 preparedStmt.execute();
		 con.close();
		 output = "Updated successfully";
		 }
		 catch (Exception e)
		 {
		 output = "Error while updating the fund.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 } 
	
	
	public String deleteFund(String fundID)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for deleting."; }
	 // create a prepared statement
	 String query = "delete from funds where fundID=?";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setInt(1, Integer.parseInt(fundID));
	 // execute the statement
	 preparedStmt.execute();
	 con.close();
	 output = "Deleted successfully";
	 }
	 catch (Exception e)
	 {
	 output = "Error while deleting the fund.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 } 
	
}

	


