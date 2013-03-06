package shatteringstone.sandbox.io;

import static org.junit.Assert.fail;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

public class IteratingInputStreamTest {

	@Test
	public void testRead() throws Exception {
		List<byte[]> data = createData(2, 2);
		InputStream inputStream = new IteratingInputStream(data);
		inputStream.available();
		//inputStream.read();
		byte[] tmp = null;
		
		boolean caught = false;
		try {
			inputStream.read(tmp, 0, 4);
		} catch (Exception e) {
			caught = true;
		}
		Assert.assertTrue(caught);
		
		caught = false;
		try {
			inputStream.read(tmp, -4, 4);
		} catch (Exception e) {
			caught = true;
		}
		Assert.assertTrue(caught);
		
		tmp = new byte[4];
		inputStream.read(tmp, 0, 4);
		Assert.assertEquals(0, ByteBuffer.wrap(tmp).getInt());
		inputStream.read(tmp, 0, 4);
		Assert.assertEquals(1, ByteBuffer.wrap(tmp).getInt());
		inputStream.read(tmp, 0, 4);
		Assert.assertEquals(2, ByteBuffer.wrap(tmp).getInt());
		
		inputStream.close();
	}
	
	@Test
	public void testReadBytes() throws Exception {
		int[][] testData = new int[][] {
				{3, 7},
				{1, 9},
				{5, 1},
				{0, 2},
				{2, 0}
		};
		
		for (int[] data : testData) {
			testReadBytes1(data[0], data[1]);
			testReadBytes2(data[0], data[1]);
		}
	}
	
	public void testReadBytes1(final int maxArrays, final int maxBytes) throws Exception {
		// Create some test data
		List<byte[]> data = createData(maxArrays, maxBytes);

		// Create a stream around the data automatically
		InputStream inputStream = new IteratingInputStream(data);

		// Read from the stream and test the results
		DataInputStream dataInputStream = new DataInputStream(inputStream);

		List<Integer> out = new ArrayList<Integer>();
		try {
			while (true) {
				out.add(dataInputStream.readInt());
			}
		} catch (EOFException e) {
			dataInputStream.close();
		}

		// Ensure # elements read is correct
		Assert.assertEquals(maxArrays*maxBytes, out.size());

		// Ensure elements were read in correct order
		int count = 0;
		for (Integer actual : out) {
			Assert.assertEquals(count++, actual.intValue());
		}
	}

	private List<byte[]> createData(final int maxArrays, final int maxBytes) throws IOException {
		List<byte[]> data = new ArrayList<byte[]>();
		int intToWrite = 0;

		for (int i = 0; i < maxArrays; i++) {
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			DataOutputStream dataOutputStream= new DataOutputStream(byteArrayOutputStream);
			for (int j = 0; j < maxBytes; j++) {
				dataOutputStream.writeInt(intToWrite++);
			}
			data.add(byteArrayOutputStream.toByteArray());
		}
		return data;
	}

	public void testReadBytes2(final int maxArrays, final int maxBytes) throws Exception {
		// Create some test data
		List<byte[]> data = createData(maxArrays, maxBytes);

		// Create a stream around the data automatically
		InputStream inputStream = new IteratingInputStream(new ByteArrayInputStreamIter(data.iterator()));

		// Read from the stream and test the results
		DataInputStream dataInputStream = new DataInputStream(inputStream);

		List<Integer> out = new ArrayList<Integer>();
		try {
			while (true) {
				out.add(dataInputStream.readInt());
			}
		} catch (EOFException e) {
			dataInputStream.close();
		}

		// Ensure # elements read is correct
		Assert.assertEquals(maxArrays*maxBytes, out.size());

		// Ensure elements were read in correct order
		int count = 0;
		for (Integer actual : out) {
			Assert.assertEquals(count++, actual.intValue());
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
		}
	}
}
