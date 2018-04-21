import java.util.Scanner;
import java.rmi.*;
import java.rmi.registry.*;
import java.net.MalformedURLException;

public class ImageServerB{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		String portNum, registryUrl;

		try{
			// System.out.println("Enter the RMIregistry port number: ");
			// portNum = sc.next();

			portNum = "12346";

			System.out.println(portNum);

			int rmiPortNum = Integer.parseInt(portNum);

			startRegistry(rmiPortNum);

			ImageImplmntB exportedObj = new ImageImplmntB();

			registryUrl = "rmi://localhost:" + portNum + "/image";

			Naming.rebind(registryUrl, exportedObj);

			System.out.println("server registered.");

			listRegistry(registryUrl);

			System.out.println(" Server ready.");


		} // end try

		catch(Exception re){
			System.out.println("Exception in Server.main: " + re);

		} // end catch

	}// end main

	private static void startRegistry(int rmiPortNum) throws RemoteException{
		try{
			Registry registry = LocateRegistry.getRegistry(rmiPortNum);
			registry.list();
		}
		catch(RemoteException e){
			System.out.println("RMIregistry can not be located at port: " + rmiPortNum);
			Registry registry = LocateRegistry.createRegistry(rmiPortNum);
			System.out.println("RMIregistry is created at port: " + rmiPortNum);

		}

	} // end startRegistry

	private static void listRegistry(String registryURL) throws RemoteException, MalformedURLException{
		System.out.println("Registry \"" + registryURL + "\" contains: ");
		String []names = Naming.list(registryURL);
		for(int i = 0; i < names.length; i++){
			System.out.println(names[i]);
		}
	}

}// end server clas
