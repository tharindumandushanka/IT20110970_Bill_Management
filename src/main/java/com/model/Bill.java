package com.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class Bill {
	
	//Database Connection
	
	private Connection connect()
	{
		 Connection con = null;
		 
		 try
		 {
			 Class.forName("com.mysql.jdbc.Driver");

			 //Provide the correct details: DBServer/DBName, username, password
			 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/electro", "root", "root");
			 
		 //For testing
			 
		 System.out.print("Successfully connected");	 
		 }
		 catch (Exception e)
		 {e.printStackTrace();}
		 
		 return con;
	}
	
	
	//Insert Admin Item
	public String insertBillDetails(String billCode, String cusID, String units , String kwhCharge, String fixCharge, String total)
	{
		String output = "";
		
		
		
		try
		{
   		   Connection con = connect();
   		   
		   if (con == null)
           {return "Error while connecting to the database for inserting."; }
		   System.out.println("Hello");
			 // create a prepared statement
			 String query = " insert into bill (`billID`,`billCode`,`cusID`,`units`,`kwhCharge`,`fixCharge`,`total`)"
			 + " values (?, ?, ?, ?, ?, ?, ?)";
			 
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, billCode);
			preparedStmt.setString(3, cusID);
			preparedStmt.setInt(4, Integer.parseInt(units));
			preparedStmt.setString(5, (kwhCharge));
			preparedStmt.setString(6, (fixCharge));
			preparedStmt.setString(7, total);
			
			// execute the statement
			
			
			
				preparedStmt.execute();
				con.close();
				String newBill = readBill();
				output = "{\"status\":\"success\", \"data\": \"" +
						newBill + "\"}";
				
		}
		catch (Exception e)
		{
			output = "{\"status\":\"error\", \"data\": \"Registration failed !.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	
	//Read Bill
		public String readBill()
	    {
		   String output = "";
		   try
		   {
			 Connection con = connect();
			 if (con == null)
			 {return "Error while connecting to the database for reading."; }
			 
		     // Prepare the html table to be displayed
		     output = "<table border='1'><tr><th>Bill Code</th>" +
					   "<th>Customer ID</th>" +
					   "<th>Units</th>" +
					  
					   "<th>KWH Charge</th>" +
					   "<th> Fix Charge</th>" +
					   "<th> Total</th>" +
					   "<th>Update</th><th>Remove</th></tr>";
		     
		     System.out.println("Hello");
			 String query = "select * from bill";
			 Statement stmt = con.createStatement();
			 ResultSet rs = stmt.executeQuery(query);
			 System.out.println(rs);
			 
			 // iterate through the rows in the result set
			 while (rs.next())
			 {
				 String billID = Integer.toString(rs.getInt("billID"));//see
				 String billCode = rs.getString("billCode");
				 String cusID = rs.getString("cusID");
				 String units = Integer.toString(rs.getInt("units"));
				 String kwhCharge = rs.getString("kwhCharge");
				 String fixCharge = rs.getString("fixCharge");
				 String total = rs.getString("total");
				 System.out.println("byyee");
				 // Add into the html table
				 output += "<tr><td><input id='hidbillIDUpdate' name='hidBillIDUpdate' type='hidden' value='" + billID
						 + "'>" + billCode + "</td>";
				 
				 output += "<td>" + cusID + "</td>";
				 output += "<td>" + units + "</td>";
				 output += "<td>" + kwhCharge + "</td>";
				 output += "<td>" + fixCharge + "</td>";
				 output += "<td>" + total + "</td>";
				 System.out.println("hiiii");
				 
				 // buttons
				 output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>" + "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-billid='"
						 + billID + "'>" + "</td></tr>"; }
			 
			 
			 con.close();
			 
			 // Complete the html table
			 output += "</table>";
		   }
		   catch (Exception e)
		   {
			 output = "Error while reading the bill.";
			 System.err.println(e.getMessage());
		   }
		   
		   return output;
	    }
		
		//update bill
		
		
		public String updateBill(String billID, String billCode, String cusID, String units, String kwhCharge, String fixCharge, String total)
		 
		 {System.out.println(billID);
		 String output = "";
		 
		    
		 
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for updating."; }
		 
		 // create a prepared statement
		 String query = "UPDATE bill SET billCode=?,cusID=?,units=?,kwhCharge=?,fixCharge=?,total=?  WHERE billID=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 
		 // binding values
		 
		 preparedStmt.setString(1, billCode);
		 preparedStmt.setString(2, cusID);
		 preparedStmt.setDouble(3, Integer.parseInt(units));
		 preparedStmt.setString(4, kwhCharge);
		 preparedStmt.setString(5,  (fixCharge));
		 preparedStmt.setString(6, total);
		 preparedStmt.setInt(7, Integer.parseInt(billID));
		 
		
		 
		// execute the statement
			
			
					preparedStmt.execute();
					con.close();
					String newBill = readBill();
					output = "{\"status\":\"success\", \"data\": \"" +
							newBill + "\"}";
						 
				}
				catch (Exception e)
				{
					output = "{\"status\":\"error\", \"data\": \"Error while updating the Bill.\"}";
					System.err.println(e.getMessage());
				}
				return output;
			}
		 
		 
		
		
		//delete
		public String deleteBill(String billID)
		
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for deleting."; }
		 
		 // create a prepared statement
		 String query = "delete from bill where billID=?";
		 
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 
		 // binding values
		 
		 System.out.println( preparedStmt);
		 preparedStmt.setInt(1, Integer.parseInt(billID));
		 // execute the statement
		 
		 preparedStmt.execute();
		 con.close();
		 String newBill = readBill();
		 output = "{\"status\":\"success\", \"data\": \"" +
				 newBill + "\"}";
		 }
		 catch (Exception e)
		 {
		 output = "{\"status\":\"error\", \"data\": \"Error while deleting the Bill.\"}";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 }
		
		

}
