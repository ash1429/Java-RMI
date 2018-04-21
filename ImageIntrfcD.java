import java.rmi.*;
import java.awt.image.BufferedImage;
import java.io.IOException;


public interface ImageIntrfcD extends Remote{
	public byte[] makingGray(byte[] rawImage) throws java.rmi.RemoteException,java.io.IOException;
}