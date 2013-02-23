package shatteringstone.sandbox.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SeqStream_iterating {
	public static void main(String[] args) throws Exception {
		
		// Create some test data
		List<byte[]> data = new ArrayList<byte[]>();
		int intToWrite = 0;
		
		for (int i = 0; i < 5; i++) {
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			DataOutputStream dataOutputStream= new DataOutputStream(byteArrayOutputStream);
			for (int j = 0; j < 3; j++) {
				dataOutputStream.writeInt(intToWrite++);
			}
			data.add(byteArrayOutputStream.toByteArray());
		}
		
		// Create a stream around the data automatically
		InputStream inputStream_default = new IteratingInputStream(data);

		// Create a stream around the data based upon a custom Iterator
		InputStream inputStream_custom = new IteratingInputStream(new ByteArrayInputStreamIter(data.iterator()));
		
		// Read from both streams and output the results
		InputStream[] streams = new InputStream[] { inputStream_custom, inputStream_default };
		for (InputStream inputStream : streams) {
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
	
	/**
	 * Custom iterator for the test above
	 */
	private static class ByteArrayInputStreamIter implements Iterator<InputStream> {
		private final Iterator<byte[]> iter;
		
		public ByteArrayInputStreamIter(Iterator<byte[]> iter) {
			this.iter = iter;
		}

		@Override
		public boolean hasNext() {
			return this.iter.hasNext();
		}

		@Override
		public InputStream next() {
			return new ByteArrayInputStream((byte[]) this.iter.next());
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException("Operation remove() is not implemented in class " + this.getClass().getSimpleName());
		}
	}
}



