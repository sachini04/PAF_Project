package com;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.User;

@Path("/User") 
public class UserService {
	
	
	User UserObj = new User();
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readUser()
	 {
	 return UserObj.readUser();
	 } 
	
	
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insetUser(@FormParam("fname") String fname,
	 @FormParam("lname") String lname,
	 @FormParam("email") String email,
	 @FormParam("phone") String phone,
	 @FormParam("NIC") String NIC,
	 @FormParam("type") String type)
	{
	 String output = UserObj.insetUser(fname, lname, email, phone,NIC,type);
	return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateUser(String userData)
	{
	//Convert the input string to a JSON object
	 JsonObject UserObject = new JsonParser().parse(userData).getAsJsonObject();
	//Read the values from the JSON object
	 String userID = UserObject.get("userID").getAsString();
	 String fname = UserObject.get("fname").getAsString();
	 String lname = UserObject.get("lname").getAsString();
	 String email = UserObject.get("email").getAsString();
	 String phone = UserObject.get("phone").getAsString();
	 String NIC = UserObject.get("NIC").getAsString();
	 String type = UserObject.get("type").getAsString();
	 String output = UserObj.updateUser(userID, fname, fname, email, phone,NIC,type);
	return output;
	}
	
	
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteUser(String userData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(userData, "", Parser.xmlParser());

	//Read the value from the element <itemID>
	 String userID = doc.select("userID").text();
	 String output = UserObj.deleteUser(userID);
	return output;
	}

}
