import java.rmi.*;
import java.util.Scanner;

import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;

public class ImageClient{
	public static void main(String[] args) {

		String portServerA, registryUrlServerA;
		String portServerB, registryUrlServerB;

		Scanner sc = new Scanner(System.in);

		try{

			// System.out.println("Enter the RMIregistry port number for ServerA: ");
			// portServerA = sc.next();
			portServerA = "12345";
			registryUrlServerA = "rmi://localhost:" + portServerA + "/image";
			ImageIntrfcA intfcObjImageA = (ImageIntrfcA)Naming.lookup(registryUrlServerA);
			System.out.println("lookup for port " + portServerA + " completed");

			// System.out.println("Enter the RMIregistry port number for ServerB: ");
			// portServerB = sc.next();
			portServerB = "12346";
			registryUrlServerB = "rmi://localhost:" + portServerB + "/image";
			ImageIntrfcB intfcObjImageB = (ImageIntrfcB)Naming.lookup(registryUrlServerB);
			System.out.println("lookup for port " + portServerB + " completed");


			BufferedImage originalImage = ImageIO.read(new File("pic.png"));
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(originalImage, "png", baos);

			byte[] rawImageA = intfcObjImageA.makingGreen(baos.toByteArray());
			byte[] rawImageB = intfcObjImageB.makingRed(baos.toByteArray());

			BufferedImage returnedImageA = javax.imageio.ImageIO.read(new ByteArrayInputStream(rawImageA));
			BufferedImage returnedImageB = javax.imageio.ImageIO.read(new ByteArrayInputStream(rawImageB));

			System.out.println(returnedImageA.getWidth());
			System.out.println(returnedImageB.getHeight());

			File outPutFileA = new File("returnedFromServerA.png");
			ImageIO.write(returnedImageA, "png", outPutFileA);

			File outPutFileB = new File("returnedFromServerB.png");
			ImageIO.write(returnedImageB, "png", outPutFileB);


		}catch(Exception e){
			System.out.println("Exception in clinet: " + e);
		}
	}// main ends
}