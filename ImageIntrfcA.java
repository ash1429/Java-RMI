import java.rmi.*;
import java.awt.image.BufferedImage;
import java.io.IOException;


public interface ImageIntrfcA extends Remote{
	public byte[] makingGreen(byte[] rawImage) throws java.rmi.RemoteException,java.io.IOException;
}