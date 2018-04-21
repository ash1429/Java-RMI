import java.rmi.*;
import java.util.Scanner;

import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;

public class ImageClient{
	public static void main(String[] args) {

		String portServerA, registryUrlServerA;
		String portServerB, registryUrlServerB;
		String portServerC, registryUrlServerC;
		String portServerD, registryUrlServerD;

		Scanner sc = new Scanner(System.in);

		try{

	//ServerA
			// System.out.println("Enter the RMIregistry port number for ServerA: ");
			// portServerA = sc.next();
			portServerA = "12345";

			registryUrlServerA = "rmi://localhost:" + portServerA + "/image";
			ImageIntrfcA intfcObjImageA = (ImageIntrfcA)Naming.lookup(registryUrlServerA);
			System.out.println("lookup for port " + portServerA + " completed");



	//ServerB
			// System.out.println("Enter the RMIregistry port number for ServerB: ");
			// portServerB = sc.next();
			portServerB = "12346";

			registryUrlServerB = "rmi://localhost:" + portServerB + "/image";
			ImageIntrfcB intfcObjImageB = (ImageIntrfcB)Naming.lookup(registryUrlServerB);
			System.out.println("lookup for port " + portServerB + " completed");


	//ServerC
			// System.out.println("Enter the RMIregistry port number for ServerB: ");
			// portServerB = sc.next();
			portServerC = "12347";

			registryUrlServerC = "rmi://localhost:" + portServerC + "/image";
			ImageIntrfcC intfcObjImageC = (ImageIntrfcC)Naming.lookup(registryUrlServerC);
			System.out.println("lookup for port " + portServerC + " completed");

	//ServerD
			// System.out.println("Enter the RMIregistry port number for ServerB: ");
			// portServerB = sc.next();
			portServerD = "12348";
			registryUrlServerD = "rmi://localhost:" + portServerD + "/image";
			ImageIntrfcD intfcObjImageD = (ImageIntrfcD)Naming.lookup(registryUrlServerD);
			System.out.println("lookup for port " + portServerD + " completed");


	//taking input of original Image (Have to make changes here when splitting images into pieces)
			BufferedImage originalImage = ImageIO.read(new File("pic.png"));

			// ByteArrayOutputStream baos = new ByteArrayOutputStream();
			// ImageIO.write(originalImage, "png", baos);
	//splitting up
		ByteArrayOutputStream baosA = new ByteArrayOutputStream();
		ByteArrayOutputStream baosB = new ByteArrayOutputStream();
		ByteArrayOutputStream baosC = new ByteArrayOutputStream();
		ByteArrayOutputStream baosD = new ByteArrayOutputStream();

		BufferedImage subImageA = originalImage.getSubimage(0,0,(originalImage.getWidth()/2),(originalImage.getHeight()/2));
		BufferedImage subImageB = originalImage.getSubimage((originalImage.getWidth()/2),0,(originalImage.getWidth()/2),(originalImage.getHeight()/2));
		BufferedImage subImageC = originalImage.getSubimage(0,(originalImage.getHeight()/2),(originalImage.getWidth()/2),(originalImage.getHeight()/2));
		BufferedImage subImageD = originalImage.getSubimage((originalImage.getWidth()/2),(originalImage.getHeight()/2),(originalImage.getWidth()/2),(originalImage.getHeight()/2));

		ImageIO.write(subImageA, "png", baosA);
		ImageIO.write(subImageB, "png", baosB);
		ImageIO.write(subImageC, "png", baosC);
		ImageIO.write(subImageD, "png", baosD);


	//sending Image
			byte[] rawImageA = intfcObjImageA.makingGreen(baosA.toByteArray());
			byte[] rawImageB = intfcObjImageB.makingRed(baosB.toByteArray());
			byte[] rawImageC = intfcObjImageC.makingBlue(baosC.toByteArray());
			byte[] rawImageD = intfcObjImageD.makingGray(baosD.toByteArray());

	//receiving Image
			BufferedImage returnedImageA = javax.imageio.ImageIO.read(new ByteArrayInputStream(rawImageA));
			BufferedImage returnedImageB = javax.imageio.ImageIO.read(new ByteArrayInputStream(rawImageB));
			BufferedImage returnedImageC = javax.imageio.ImageIO.read(new ByteArrayInputStream(rawImageC));
			BufferedImage returnedImageD = javax.imageio.ImageIO.read(new ByteArrayInputStream(rawImageD));

	//hudai ensure houar jonno
			System.out.println(returnedImageA.getWidth());
			System.out.println(returnedImageB.getHeight());
			System.out.println(returnedImageC.getHeight());
			System.out.println(returnedImageD.getHeight());

	//writing output of each server
			File outPutFileA = new File("returnedFromServerA.png");
			ImageIO.write(returnedImageA, "png", outPutFileA);

			File outPutFileB = new File("returnedFromServerB.png");
			ImageIO.write(returnedImageB, "png", outPutFileB);


			File outPutFileC = new File("returnedFromServerC.png");
			ImageIO.write(returnedImageC, "png", outPutFileC);

			File outPutFileD = new File("returnedFromServerD.png");
			ImageIO.write(returnedImageD, "png", outPutFileD);


		}catch(Exception e){
			System.out.println("Exception in clinet: " + e);
		}
	}// main ends
}