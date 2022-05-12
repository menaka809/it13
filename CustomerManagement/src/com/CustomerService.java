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
 import model.Customer;

 @Path("/Customer")
 public class CustomerService {
 	Customer CustomerObj = new Customer();

 	@GET
 	@Path("/")
 	@Produces(MediaType.TEXT_HTML)
 	public String readCustomer() {
 	return CustomerObj.readCustomer();
 		
 	}
 	
 	@POST
 	@Path("/")
 	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
 	@Produces(MediaType.TEXT_PLAIN)
 	public String insertCustomer(@FormParam("Name") String Name,			
 	 @FormParam("Email") String Email,
 	 @FormParam("Address") String Address,
 	 @FormParam("NIC") String NIC,
 	 @FormParam("AccountNumber") String AccountNumber,
 	 @FormParam("ContactNumber") String ContactNumber)
 	{
 	 String output = CustomerObj.insertCustomer (Name, Email, Address, NIC, AccountNumber ,ContactNumber);
 	return output;
 	}
 	
 	@PUT
 	@Path("/")
 	@Consumes(MediaType.APPLICATION_JSON)
 	@Produces(MediaType.TEXT_PLAIN)
 	public String updateCustomer(String customerData)
 	{
 	//Convert the input string to a JSON object
 	 JsonObject customerObject = new JsonParser().parse(customerData).getAsJsonObject();
 	//Read the values from the JSON object
 	 String CusID = customerObject.get("CusID").getAsString();
 	 String Name = customerObject.get("Name").getAsString();
 	 String Email= customerObject.get("Email").getAsString();
 	 String Address= customerObject.get("Address").getAsString();
 	 String NIC =customerObject.get("NIC").getAsString();
 	 String AccountNumber =customerObject.get("AccountNumber").getAsString();
 	 String ContactNumber = customerObject.get("ContactNumber").getAsString();
 	 String output = CustomerObj.updateCustomer(CusID, Name, Email, Address, NIC, AccountNumber, ContactNumber);
 	return output;
 	} 
 	
 	@DELETE
 	@Path("/")
 	@Consumes(MediaType.APPLICATION_XML)
 	@Produces(MediaType.TEXT_PLAIN)
 	public String deleteCustomer(String customerData)
 	{
 	//Convert the input string to an XML document
 	 Document doc = Jsoup.parse(customerData, "", Parser.xmlParser());

 	//Read the value from the element <ID>
 	 String CusID = doc.select("CusID").text();
 	 String output = CustomerObj.deleteCustomer(CusID);
 	return output;
 	}
 }
