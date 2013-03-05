package shatteringstone.sandbox.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.InputStream;
import java.io.SequenceInputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;

/**
 * A SequenceInputStream is an outdated Java 1.0 class that will stream over an ordered collection of streams. 
 * The API for this class relies heavily on Enumeration and Vector, which are also outdated.
 * {@link SequenceInputStream}
 */
public class SeqStream {
	public static void main(String[] args) throws Exception {
		Vector<byte[]> data = new Vector<byte[]>();
		int intToWrite = 0;
		
		for (int i = 0; i < 5; i++) {
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			DataOutputStream dataOutputStream= new DataOutputStream(byteArrayOutputStream);
			for (int j = 0; j < 3; j++) {
				dataOutputStream.writeInt(intToWrite++);
			}
			data.add(byteArrayOutputStream.toByteArray());
		}

		InputStreamEnumeration byteArrayInputStreamEnumeration = new InputStreamEnumeration(data);		
		InputStream inputStream = new SequenceInputStream(byteArrayInputStreamEnumeration);
		DataInputStream dataInputStream = new DataInputStream(inputStream);
		
		List<Integer> out = new ArrayList<Integer>();
		try {
			while (true) {
				out.add(dataInputStream.readInt());
			}
		} catch (EOFException e) {
			dataInputStream.close();
		}
		
		System.out.println(out.toString());
	}
}

class InputStreamEnumeration implements Enumeration<InputStream> {
	private final Enumeration<byte[]> data;
	
	public InputStreamEnumeration(Vector<byte[]> data) {
		this.data = data.elements();
	}

	@Override
	public boolean hasMoreElements() {
		return this.data.hasMoreElements();
	}

	@Override
	public InputStream nextElement() {
		return new ByteArrayInputStream(data.nextElement());
	}
}