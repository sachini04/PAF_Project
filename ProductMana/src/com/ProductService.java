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

import model.Product;

@Path("/Product") 
public class ProductService {

	Product productObj = new Product();
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readProducts()
	 {
	 return productObj.readProducts();
	 } 
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insetProduct(@FormParam("productCode") String productCode,
	 @FormParam("productName") String productName,
	 @FormParam("productPrice") String productPrice,
	 @FormParam("productDesc") String productDesc,
	 @FormParam("productType") String productType,
	 @FormParam("quantity") String quantity)
	{
	 String output = productObj.insetProduct(productCode, productName, productPrice, productDesc,productType,quantity);
	return output;
	}


	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateProducts(String ProductData)
	{
	//Convert the input string to a JSON object
	 JsonObject productObject = new JsonParser().parse(ProductData).getAsJsonObject();
	//Read the values from the JSON object
	 String productID = productObject.get("productID").getAsString();
	 String productCode = productObject.get("productCode").getAsString();
	 String productName = productObject.get("productName").getAsString();
	 String productPrice = productObject.get("productPrice").getAsString();
	 String productDesc = productObject.get("productDesc").getAsString();
	 String productType = productObject.get("productType").getAsString();
	 String quantity = productObject.get("quantity").getAsString();
	 String output = productObj.updateProducts(productID, productCode, productName, productPrice, productDesc,productType,quantity);
	return output;
	}
	
	
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteProduct(String productData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(productData, "", Parser.xmlParser());

	//Read the value from the element <itemID>
	 String productID = doc.select("productID").text();
	 String output = productObj.deleteProduct(productID);
	return output;
	}
	
	
}
