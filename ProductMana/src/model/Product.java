package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Product {
	
	private Connection connect()
	 {
	 Connection con = null;
	 try
	 {
	 Class.forName("com.mysql.jdbc.Driver");

	 //Provide the correct details: DBServer/DBName, username, password
	 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/product", "root", "");
	 }
	 catch (Exception e)
	 {e.printStackTrace();}
	 return con;
	 }
	
	
	public String insetProduct(String Pcode, String Pname, String Pprice, String Pdesc, String Ptype, String Pquan)
	 {
		String output = "";
		
		try {
			 Connection con = connect();
			 if (con == null)
			 {
				 return "Error while connecting to the database for inserting."; 
			 
			 }
			 
			// create a prepared statement
			 String query = " insert into product(`productID`,`productCode`,`productName`,`productPrice`,`productDesc`,`productType`,`quantity`)"
			 + " values (?, ?, ?, ?, ?, ?,?)";
			 
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			 
			 // binding values
			 preparedStmt.setInt(1, 0);
			 preparedStmt.setString(2, Pcode);
			 preparedStmt.setString(3, Pname);
			 preparedStmt.setDouble(4, Double.parseDouble(Pprice));
			 preparedStmt.setString(5, Pdesc);
			 preparedStmt.setString(6, Ptype);
			 preparedStmt.setString(7, Pquan);
			 
			// execute the statement
			 
			 preparedStmt.execute();
			 con.close();
			 output = "Product Inserted successfully";
		}catch (Exception e) {
			
			
			 output = "Error while inserting the Product.";
			 System.err.println(e.getMessage());
		}
		
		 return output;
	 }
	
	public String readProducts() {
		
		 String output = "";
		 try {
			 
			 Connection con = connect();
			 if (con == null) {
				 
				 return "Error while connecting to the database for reading.";
			 }
			 
			 output = "<table border='1'><tr><th>Product Code</th><th>Product Name</th>" + "<th>Product Price</th>" +
					 "<th>Product Description</th>" + "<th>Product Type</th>" + "<th>Quantity</th>" + 
					 "<th>Update</th><th>Remove</th></tr>";
			 
			 String query = "select * from product";
			 
			 Statement stmt = con.createStatement();
			 ResultSet rs = stmt.executeQuery(query);
			 // iterate through the rows in the result set
			 
			 while (rs.next())
			 {
				 String productID = Integer.toString(rs.getInt("productID"));
				 String productCode = rs.getString("productCode");
				 String productName = rs.getString("productName");
				 String productPrice = Double.toString(rs.getDouble("productPrice"));
				 String productDesc = rs.getString("productDesc");
				 String productType = rs.getString("productType");
				 String quantity = rs.getString("quantity");
				 
				// Add into the html table
				 
				 output += "<tr><td>" + productCode + "</td>";
				 output += "<td>" + productName + "</td>";
				 output += "<td>" +productPrice + "</td>";
				 output += "<td>" + productDesc + "</td>";
				 output += "<td>" + productType + "</td>";
				 output += "<td>" + quantity + "</td>";
				// buttons
				 
				 output += "<td><input name='btnUpdate' "
						 + " type='button' value='Update'></td>"
						 + "<td><form method='post' action='products.jsp'>"
						 + "<input name='btnRemove' "
						 + " type='submit' value='Remove'>"
						 + "<input name='productID' type='hidden' "
						 + " value='" + productID  + "'>" + "</form></td></tr>";
			 }
			 
			 con.close();
			 // Complete the html table
			 output += "</table>";
		 }catch (Exception e) {
			 
			 output = "Error while reading the Products.";
			 System.err.println(e.getMessage());
		 }
		 return output;
	}
	
	
	public String updateProducts(String productID , String productCode, String productName, String productPrice, String productDesc,String productType,String quantity)
	{
		 String output = "";
		 try {
			 Connection con = connect();
			 if (con == null) {
				 
				 return "Error while connecting to the database for updating."; 
			 }
			 
			 // create a prepared statement
			 String query = "UPDATE product SET productCode=?,productName=?,productPrice=?,productDesc=? ,productType=? ,quantity=?  WHERE productID =?";
			 
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			 // binding values
			 preparedStmt.setString(1, productCode);
			 preparedStmt.setString(2, productName);
			 preparedStmt.setDouble(3, Double.parseDouble(productPrice));
			 preparedStmt.setString(4, productDesc);
			 preparedStmt.setString(5, productType);
			 preparedStmt.setString(6, quantity);
			 preparedStmt.setInt(7, Integer.parseInt(productID));
			 preparedStmt.execute();
			 con.close();
			 output = "Product Updated successfully";
		 } catch (Exception e) {
			 
			 output = "Error while updating the Product.";
			 System.err.println(e.getMessage());
		 }
		 
		 return output;
	}
	
	public String deleteProduct(String productID) {
		 String output = "";
		 try {
			 
			 Connection con = connect();
			 if (con == null) {
				 
				 return "Error while connecting to the database for deleting.";
			 }
			 // create a prepared statement
			 String query = "delete from product where productID=?";
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			 // binding values
			 preparedStmt.setInt(1, Integer.parseInt(productID));
			 
			 // execute the statement
			 preparedStmt.execute();
			 con.close();
			 output = "Product Deleted successfully";
		 } catch (Exception e) {
			 
			 output = "Error while deleting the Product.";
			 System.err.println(e.getMessage());
			 
		 }
		 return output;
	}

}
