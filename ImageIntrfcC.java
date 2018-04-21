import java.rmi.*;
import java.awt.image.BufferedImage;
import java.io.IOException;


public interface ImageIntrfcC extends Remote{
	public byte[] makingBlue(byte[] rawImage) throws java.rmi.RemoteException,java.io.IOException;
}