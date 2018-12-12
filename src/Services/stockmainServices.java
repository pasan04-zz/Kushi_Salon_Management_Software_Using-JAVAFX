/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Utils.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.stock;

/**
 *
 * @author Pasan
 */
public class stockmainServices {

    
 
 public String registerStock(stock stockbean)
 {
         String date = stockbean.getDate();
	 //int bill = stockbean.getbill();
         String supplier = stockbean.getSupplier();
	 String category = stockbean.getCateg();
         String name = stockbean.getName();
         int quantity= stockbean.getQty();
	 double price4 = stockbean.getPrice();
 
	 Connection con = null;
	 PreparedStatement preparedStatement = null;
 
 try
 {
	 con = DBConnection.getDBConnection();
	 String query = "insert into stock(date,supplier,categ,name,qty,price) values (?,?,?,?,?,?)"; 
	 preparedStatement = con.prepareStatement(query); 
	 preparedStatement.setString(1,date);
	 //preparedStatement.setInt(2, bill);
         preparedStatement.setString(2, supplier);
         preparedStatement.setString(3, category);
         preparedStatement.setString(4, name);
         preparedStatement.setInt(5, quantity);
	 preparedStatement.setDouble(6, price4);
	 int i= preparedStatement.executeUpdate();
 
 	if (i>0)  
 		return "SUCCESS"; 
 	}
 
 	catch(SQLException | ClassNotFoundException e)
 	{
               Logger.getLogger(stockmainServices.class.getName()).log(Level.SEVERE, null, e);
 		e.printStackTrace();
 	} 
 	return "Oops.. Something went wrong there..!";  // On failure, send a message from here.
 	}
}
    
    
    
  
