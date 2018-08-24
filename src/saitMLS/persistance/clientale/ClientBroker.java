package saitMLS.persistance.clientale;


import java.io.*;
import java.sql.*;
import java.util.ArrayList;

import saitMLS.exceptions.clientale.InvalidPhoneNumberException;
import saitMLS.exceptions.clientale.InvalidPostalCodeException;
import saitMLS.persistance.Broker;
import saitMLS.problemDomain.clientale.Client;
import utility.Loggable;

/**
 *	ClientBroker.java - Class describing all attributes and operations for a Client object. 
 *
 * @author Macarena Hevia
 * @version 1.0
 * 
 */
public class ClientBroker implements Broker, Loggable{

	private static final String INPUT_FILE = "res/clients.txt";
	private static final String SELECT_MAX_ID = "SELECT MAX(CLIENTID) FROM client2";
	private static final String INSERT_STMT = "INSERT INTO client2(CLIENTID, FNAME, LNAME, ADDRESS, TYPE, PHONE, POSTAL) values(?,?,?,?,?,?,?)";
	private static final String UPDATE_STMT = "UPDATE client2 SET FNAME = ?, LNAME = ?, ADDRESS = ?, TYPE = ?, PHONE = ?, POSTAL = ? where CLIENTID = ?";
	private static final String DELETE_STMT = "DELETE FROM client2 WHERE CLIENTID = ?";
	private static final String SELECT_CLIENTID_STMT = "SELECT * FROM client2 where CLIENTID = ?";
	private static final String SELECT_TYPE_STMT = "SELECT * FROM client2 where TYPE = ?";
	private static final String SELECT_NAME_STMT = "SELECT * FROM client2 where LNAME = ?";
	
	
	private Connection 				conn;
	private Statement				stmt;
	private ResultSet				rs;
	private static ClientBroker		clientBroker;
	
	/**
	 * Constructor of the ClientBroker class
	 * is called only when the getBroker method is called.
	 */
	private ClientBroker()
	{
		setConnection();
		if(!tableExists())
		{
			createClientTablePreparedStatement();
			loadClientTable();
		}
	}
	
	/**
	 * Method controls the generation of the ClientBroker objects.
	 * @return an instance of clientBroker
	 */
	public static ClientBroker getBroker()
	{
		if(clientBroker == null)
		{
			clientBroker = new ClientBroker();
		}
		return clientBroker;
	}
	
	/**
	 * Method to connect to the database
	 */
	private void setConnection()
	{
		try 
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:SAIT",
														USER, PASSWORD);
		}
		catch(ClassNotFoundException cExp)
		{
			System.out.println("Unable to find Driver");
			System.exit(-1);
		}
		catch(SQLException sExp)
		{
			System.out.println("Problem connecting to database"+ sExp.getMessage());
			System.exit(-1);
		}

	}
	/**
	 * Method to check for the existence of the table
	 * @return true if the table exists, false if not
	 */
	private boolean tableExists()
	{
		boolean exists = false;
		try
		{
			
			stmt = conn.createStatement();
			String query = "SELECT * FROM client2";
			rs = stmt.executeQuery(query);
			exists = true;
			
			stmt.close();
			rs.close();
		}
		catch (SQLException e)
		{
		}
		System.out.println("table exists" + exists);
		return exists;
	}
	/**
	 * Method to create the client table in the database
	 */
	private void createClientTablePreparedStatement()
	{
		try
		{
			
		String create = "CREATE TABLE client2 (CLIENTID        NUMBER(4) PRIMARY KEY, " +
					 					  "FNAME               VARCHAR2(20), " +
					 					  "LNAME               VARCHAR2(20), " +
					 					  "ADDRESS             VARCHAR2(40)," +
					 					  "TYPE		           CHAR(1), " + 
					 					  "PHONE               CHAR(12), " +
					 					  "POSTAL              CHAR(7))";
		
			PreparedStatement pStat = conn.prepareStatement(create);
		
			int rowCount = pStat.executeUpdate();
			pStat.close();
		}	
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	/**
	 * Method to insert the client records from the client.txt file to the database
	 */
	private void loadClientTable()
	{
		try
		{
			BufferedReader fin = new BufferedReader(new FileReader(INPUT_FILE));

			String line = fin.readLine();
			long id=1;
			
			while (line != null)
			{
				Client cl = new Client(line);
				cl.setId(id);
				insertPreparedStatement(cl);
				line = fin.readLine();
				id++;
			}

			fin.close();
		}
		
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		} catch (InvalidPhoneNumberException e) {
			e.printStackTrace();
		} catch (InvalidPostalCodeException e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * Method to insert a new client to the database
	 * @param cl the client to insert
	 * @return true if the insert was successful
	 */
	private boolean insertPreparedStatement(Client cl)
	{
		try
		{
				
			PreparedStatement pStat = conn.prepareStatement(INSERT_STMT);
			
			pStat.setInt(1, (int)cl.getId());
			pStat.setString(2, cl.getFirstName());
			pStat.setString(3, cl.getLastName());
			pStat.setString(4, cl.getAddress());
			pStat.setString(5, String.valueOf(cl.getClientType()));
			pStat.setString(6, cl.getPhoneNumber());
			pStat.setString(7, cl.getPostalCode());
			
			int rowCount = pStat.executeUpdate();
			pStat.close();
			return rowCount == 1;
		}
		catch(SQLException e)
		{
				e.printStackTrace();
		}
		return false;
	}
	/**
	 * Method to update an existing client record
	 * @param cl the client to update
	 * @return true if the update was successful
	 */
	private boolean updatePreparedStatement(Client cl)
	{
		try
		{
				
			PreparedStatement pStat = conn.prepareStatement(UPDATE_STMT);
			
			pStat.setString(1, cl.getFirstName());
			pStat.setString(2, cl.getLastName());
			pStat.setString(3, cl.getAddress());
			pStat.setString(4, String.valueOf(cl.getClientType()));
			pStat.setString(5, cl.getPhoneNumber());
			pStat.setString(6, cl.getPostalCode());
			pStat.setLong(7, cl.getId());
			
			int rowCount = pStat.executeUpdate();
			
			pStat.close();
			return rowCount == 1;
			
		}
		catch(SQLException e)
		{
				e.printStackTrace();
		}
		return false;
		
	}
	
	
	/**
	 * Method to release resources allocated to the broker and save all modified data.
	 */
	@Override
	public void closeBroker() {
		try
		{
			conn.close();
			clientBroker = null;
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	/**
	 * Method to delete a record from the client table in the database
	 */
	@Override
	public boolean remove(Object o) {
		Client cl = (Client)o;
		try
		{
				
			PreparedStatement pStat = conn.prepareStatement(DELETE_STMT);
			
			pStat.setInt(1, (int)cl.getId());
			
			
			int rowCount = pStat.executeUpdate();
			pStat.close();
			return rowCount == 1;
		}
		catch(SQLException e)
		{
				e.printStackTrace();
		}
		return false;
	}
	/**
	 * Method to add a new or modified record to the client table in the database
	 */
	@Override
	public boolean persist(Object o) {
		Client cl = (Client)o;
		if(cl.getId() == 0)
		{
			try
			{
				PreparedStatement pstat = conn.prepareStatement(SELECT_MAX_ID);
			
				rs = pstat.executeQuery();
			
				while(rs.next())
				{
					int max = rs.getInt(1);
					cl.setId(max+1);
				}
					
			}catch(SQLException e)
			{
				e.printStackTrace();
			}
			
			return insertPreparedStatement(cl);
		}
		else
			return updatePreparedStatement(cl);
	}
	/**
	 * Method to search the Client table on the following criteria: membership id, name, or client type.
	 */
	@Override
	public java.util.List search(String item, String type) {
		ArrayList<Client>  searchResult = new ArrayList<>();
		PreparedStatement pstat=null;
		
		try {
			if(type.equalsIgnoreCase("id"))
			{
				pstat = conn.prepareStatement(SELECT_CLIENTID_STMT);
				pstat.setInt(1, Integer.parseInt(item));
			}	
			else if(type.equalsIgnoreCase("type"))
			{
				pstat = conn.prepareStatement(SELECT_TYPE_STMT);
				pstat.setString(1, item.toUpperCase());
			}
			else if(type.equalsIgnoreCase("name"))
			{
				pstat = conn.prepareStatement(SELECT_NAME_STMT);
				pstat.setString(1, item);
			}
			
			
			rs = pstat.executeQuery();
			
			while(rs.next()){
				Client cl = new Client(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(7), rs.getString(6),  rs.getString(5).charAt(0));
				searchResult.add(cl);
			}
			pstat.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InvalidPhoneNumberException e) {
			e.printStackTrace();
		} catch (InvalidPostalCodeException e) {
			e.printStackTrace();
		}
		return searchResult;
	}

	
}
