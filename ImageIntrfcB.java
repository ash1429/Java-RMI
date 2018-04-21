import java.rmi.*;
import java.awt.image.BufferedImage;
import java.io.IOException;


public interface ImageIntrfcB extends Remote{
	public byte[] makingRed(byte[] rawImage) throws java.rmi.RemoteException,java.io.IOException;
}