import java.rmi.*;
import java.rmi.server.*;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;


public class ImageImplmntA extends UnicastRemoteObject implements ImageIntrfcA{
	public ImageImplmntA() throws RemoteException{
		super();
	}

	public byte[] makingGreen(byte[] rawImage) throws java.rmi.RemoteException,java.io.IOException{

		byte[] rawImage2;

			BufferedImage img = javax.imageio.ImageIO.read(new ByteArrayInputStream(rawImage));

			System.out.println(img.getWidth());
			System.out.println(img.getHeight());

			int width = img.getWidth();
            int height = img.getHeight();
		    for(int y = 0; y < height; y++){
		      for(int x = 0; x < width; x++){
		        int p = img.getRGB(x,y);

		        int a = (p>>24)&0xff;
		        int g = (p>>8)&0xff;

		        //set new RGB
		        p = (a<<24) | (0<<16) | (g<<8) | 0;

		        img.setRGB(x, y, p);
		      }
		    }



			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(img, "png", baos);


			rawImage2 = baos.toByteArray();

			return rawImage2;



	}


}