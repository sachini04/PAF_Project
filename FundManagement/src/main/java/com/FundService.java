package com;
import model.Fund;


//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 
//For JSON
import com.google.gson.*; 
//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 
@Path("/Funds") 

public class FundService {
	
	Fund fundObj = new Fund();
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readFunds()
	 {
	 return fundObj.readFunds();
	 } 
	
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertFund(@FormParam("ResearcherId") String ResearcherId,
	 @FormParam("ResearcherName") String ResearcherName,
	 @FormParam("fundDate") String fundDate,
	 @FormParam("fundAmount") String fundAmount,
	 @FormParam("fundDesc") String fundDesc)
	{
	 String output = fundObj.insertFund(ResearcherId, ResearcherName, fundDate, fundAmount,fundDesc);
	return output;
	}
	
	
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateFund(String fundData)
	{
	
	//Convert the input string to a JSON object
	 JsonObject fundObject = new JsonParser().parse(fundData).getAsJsonObject();
	//Read the values from the JSON object
	 String fundID = fundObject.get("fundID").getAsString();
	 String ResearcherId = fundObject.get("ResearcherId").getAsString();
	 String ResearcherName = fundObject.get("ResearcherName").getAsString();
	 String fundDate = fundObject.get("fundDate").getAsString();
	 String fundAmount = fundObject.get("fundAmount").getAsString();
	 String fundDesc = fundObject.get("fundDesc").getAsString();
	 String output = fundObj.updateFund(fundID, ResearcherId, ResearcherName, fundDate, fundAmount,fundDesc);
	return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteFund(String fundData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(fundData, "", Parser.xmlParser());

	//Read the value from the element <fundID>
	 String fundID = doc.select("fundID").text();
	 String output = fundObj.deleteFund(fundID);
	return output;
	}

}

