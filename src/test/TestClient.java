package test;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import saitMLS.exceptions.clientale.InvalidPostalCodeException;
import saitMLS.persistance.clientale.ClientBroker;
import saitMLS.persistance.property.CommercialPropertyBroker;
import saitMLS.problemDomain.clientale.Client;

/**
 *	TestClient.java
 *
 * @author Macarena Hevia
 * @version 1.0
 *
 * This class is used to test the client classes including Client, ClientBroker and Exceptions
 */
public class TestClient {

	public static void main(String[] args) throws FileNotFoundException, InvalidPostalCodeException {
//		ClientBroker broker = new ClientBroker();
//		broker.print();
//		ClientBroker brk = ClientBroker.getBroker();
		CommercialPropertyBroker b = CommercialPropertyBroker.getBroker();
//		brk.printClients();
//			ArrayList<Client> alc = brk.search("Dog","name");
//			for(int i=0; i<alc.size();i++) {
//				System.out.println(alc.get(i));
//			}
		
//		Client c;
//		c = new Client("1041;Inny Outies;219;3.45");
//			broker.addGizmo(c);
//			broker.printGizmos();
//			broker.deleteGizmo(c);
//			broker.printGizmos();

		
		
	}

}
