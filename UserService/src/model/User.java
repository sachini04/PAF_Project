package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
public class User {
	
	private Connection connect()
	 {
	 Connection con = null;
	 try
	 {
	 Class.forName("com.mysql.jdbc.Driver");

	 //Provide the correct details: DBServer/DBName, username, password
	 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/userdb", "root", "");
	 }
	 catch (Exception e)
	 {e.printStackTrace();}
	 return con;
	 }
	
	public String insetUser(String fname, String lname, String email, String phone, String NIC, String type)
	 {
		String output = "";
		
		try {
			 Connection con = connect();
			 if (con == null)
			 {
				 return "Error while connecting to the database for inserting."; 
			 
			 }
			// create a prepared statement
			 String query = " insert into useraccount(`userID`,`fname`,`lname`,`email`,`phone`,`NIC`,`type`)"
			 + " values (?, ?, ?, ?, ?, ?,?)";
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			 
			 
			 // binding values
			 preparedStmt.setInt(1, 0);
			 preparedStmt.setString(2, fname);
			 preparedStmt.setString(3, lname);
			 preparedStmt.setString(4,email );
			 preparedStmt.setString(5, phone);
			 preparedStmt.setString(6, NIC);
			 preparedStmt.setString(7, type);
// execute the statement
			 
			 preparedStmt.execute();
			 con.close();
			 output = " Inserted successfully";
		}catch (Exception e) {
			
			
			 output = "Error while inserting the .";
			 System.err.println(e.getMessage());
		}
		
		 return output;
	 }
	public String readUser() {
		
		 String output = "";
		 try {
			 
			 Connection con = connect();
			 if (con == null) {
				 
				 return "Error while connecting to the database for reading.";
			 }
			 output = "<table border='1'><tr><th>Frist Name</th><th>Last Name</th>" + "<th>Email</th>" +
					 "<th>Phone Number</th>" + "<th>NIC</th>" + "<th>Type</th>" + 
					 "<th>Update</th><th>Remove</th></tr>";
			 
			 String query = "select * from useraccount";
			 
			 Statement stmt = con.createStatement();
			 ResultSet rs = stmt.executeQuery(query);
			 // iterate through the rows in the result set
			 
			 
			 while (rs.next())
			 {
				 String userID = Integer.toString(rs.getInt("userID"));
				 String fname = rs.getString("fname");
				 String lname = rs.getString("lname");
				 String email = Double.toString(rs.getDouble("email"));
				 String phone = rs.getString("phone");
				 String NIC = rs.getString("NIC");
				 String type = rs.getString("type");
				 
				 output += "<tr><td>" + fname + "</td>";
				 output += "<td>" + lname + "</td>";
				 output += "<td>" +email + "</td>";
				 output += "<td>" + phone + "</td>";
				 output += "<td>" + NIC + "</td>";
				 output += "<td>" + type + "</td>";
				 
				 
				 output += "<td><input name='btnUpdate' "
						 + " type='button' value='Update'></td>"
						 + "<td><form method='post' action='User.jsp'>"
						 + "<input name='btnRemove' "
						 + " type='submit' value='Remove'>"
						 + "<input name='userID' type='hidden' "
						 + " value='" + userID  + "'>" + "</form></td></tr>";
	 }
			 
			 con.close();
			 // Complete the html table
			 output += "</table>";
		 }catch (Exception e) {
			 
			 output = "Error while reading.";
			 System.err.println(e.getMessage());
		 }
		 return output;
	}
	
	public String updateUser(String userID , String fname, String lname, String email, String phone,String NIC,String type)
	{
		 String output = "";
		 try {
			 Connection con = connect();
			 if (con == null) {
				 
				 return "Error while connecting to the database for updating."; 
			 }
	
			 String query = "UPDATE useraccount SET fname=?,lname=?,email=?,phone=? ,NIC=? ,type=?  WHERE userID =?";
			 
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			 // binding values
			 
			 preparedStmt.setString(1, fname);
			 preparedStmt.setString(2, lname);
			 preparedStmt.setString(3,email );
			 preparedStmt.setString(4, phone);
			 preparedStmt.setString(5, NIC);
			 preparedStmt.setString(6, type);
			 preparedStmt.setInt(7, Integer.parseInt(userID));
			 preparedStmt.execute();
			 con.close();
			 output = " Updated successfully";
			 
		 } catch (Exception e) {
			 
			 output = "Error while updating the User.";
			 System.err.println(e.getMessage());
		 }
		 

		 return output;
	}
	
	public String deleteUser(String userID) {
		 String output = "";
		 try {
			 
			 Connection con = connect();
			 if (con == null) {
				 
				 return "Error while connecting to the database for deleting.";
			 }
			 
			 
			 // create a prepared statement
			 String query = "delete from useraccount where userID=?";
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			 // binding values
			 	preparedStmt.setInt(1, Integer.parseInt(userID));
			 
			 // execute the statement
			 preparedStmt.execute();
			 con.close();
			 output = " Deleted successfully";
		 	} catch (Exception e) {
			 
			 output = "Error while deleting the User.";
			 System.err.println(e.getMessage());
			 
		 }
		 return output;
	}
}
