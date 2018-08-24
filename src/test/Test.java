package test;
import java.util.ArrayList;

import saitMLS.exceptions.clientale.*;
import saitMLS.persistance.clientale.ClientBroker;
import saitMLS.problemDomain.clientale.Client;

/**
 *	Test.java
 *
 * @author Macarena Hevia
 * @version 1.0
 *
 * This class is used to test connection with the database, specifically testing the client database
 */
public class Test {
	/**
	 * @param args
	 * This method creates a new Client, a new ClientBrocker and tests the search, persist, remove and close methods
	 */
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		try {
			Client c = new Client( 0,
					              "Fred", 
					              "Flintstone",
					              "34 Flintrock Way",
					              "T3R 5B6",
					              "403-295-9076", 
					              'C' );
			
			System.out.println(c);
			
			ClientBroker cb = ClientBroker.getBroker();
			
			//cb.persist(c);
			
			ArrayList <Client>searchResult = (ArrayList<Client>) cb.search("Flintstone", "name");
			
			for(Client client : searchResult) {
				System.out.println(client);
			}
			
			searchResult = (ArrayList<Client>) cb.search("25", "id");
			searchResult = (ArrayList<Client>) cb.search("C", "type");
			/*cb.remove(new Client( 63,
		              "Fred", 
		              "Flintstone",
		              "34 Flintrock Way",
		              "T3R 5B6",
		              "403-295-9076", 
		              'C' ));*/
			
		
			
			/*cb.persist(new Client( 63,
		              "Freddddddy", 
		              "Flintstone1",
		              "34 Flintrock Way1",
		              "T3R 5B6",
		              "403-295-9076", 
		              'C' ));*/
			
			searchResult = (ArrayList<Client>) cb.search("63", "id");
			for(Client client : searchResult) {
				System.out.println(client);
			}
			
			cb.closeBroker();
			
			
		} catch (InvalidPhoneNumberException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidPostalCodeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
