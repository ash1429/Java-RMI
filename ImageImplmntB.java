import java.rmi.*;
import java.rmi.server.*;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;


public class ImageImplmntB extends UnicastRemoteObject implements ImageIntrfcB{
	public ImageImplmntB() throws RemoteException{
		super();
	}

	public byte[] makingRed(byte[] rawImage) throws java.rmi.RemoteException,java.io.IOException{

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
                int r = (p>>16)&0xff; //for red

                //set new RGB
				p = (a<<24) | (r<<16) | (0<<8) | 0;
				img.setRGB(x, y, p);
		      }
		    }



			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(img, "png", baos);


			rawImage2 = baos.toByteArray();

			return rawImage2;

	}


}