package shatteringstone.sandbox.io;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import javax.activation.UnsupportedDataTypeException;

/**
 * A <code>IteratingInputStream</code> represents
 * the logical concatenation of other input
 * streams. It starts out with an ordered
 * collection of input streams and reads from
 * the first one until end of file is reached,
 * whereupon it reads from the second one,
 * and so on, until end of file is reached
 * on the last of the contained input streams.
 *
 * This functionality is already provided by java.io.SequenceInputStream. 
 * However java's implementation relies on Vectors and Enumerations instead 
 * of Collections and Iterators. This class attempts to update SequenceInputStream 
 * to use these modern conventions.
 * 
 * @see java.io.SequenceInputStream 
 */
public class IteratingInputStream extends InputStream {
    private final Iterator<? extends InputStream> inputStreamIterator;
    private InputStream inputStream;

    /**
     * Initializes a newly created <code>IteratingInputStream</code>
     * by remembering the argument, which must
     * be an <code>Iterator</code>  that produces
     * objects whose run-time type is <code>InputStream</code>.
     * The input streams that are  produced by
     * the enumeration will be read, in order,
     * to provide the bytes to be read  from this
     * <code>IteratingInputStream</code>. After
     * each input stream from the iterator
     * is exhausted, it is closed by calling its
     * <code>close</code> method.
     *
     * @param   inputStreamIterator   an iterator of input streams.
     * @see     java.util.Iterator
     */
    public IteratingInputStream(Iterator<? extends InputStream> inputStreamIterator) {
        this.inputStreamIterator = inputStreamIterator;
        try {
            nextStream();
        } catch (IOException ex) {
            // This should never happen
            throw new Error("panic");
        }
    }
    
    /**
     * Initializes a newly created <code>IteratingInputStream</code>
     * by creating Input streams around the elements of the argument
     * be an <code>List</code> of <code>byte[]</code> or <code>File</code>.
     * Additional InputStream types must either use a different constructor
     * or by modifying the InputStreamIter inner class.
     * 
     * The input streams that are  produced will be read, in order,
     * to provide the bytes to be read  from this
     * <code>IteratingInputStream</code>. After
     * each input stream from the created iterator
     * is exhausted, it is closed by calling its
     * <code>close</code> method.
     * 
     * @param	data	an Ordered list of 
     * @see		java.util.List
     */
    public <ElemType, ListType extends List<ElemType>> IteratingInputStream(ListType data) {
        this.inputStreamIterator = new InputStreamIter<ElemType>(data.iterator());
        try {
            nextStream();
        } catch (IOException ex) {
            // This should never happen
            throw new Error("panic");
        }
    }

    /**
     *  Continues reading in the next stream if an EOF is reached.
     */
    final void nextStream() throws IOException {
        if (inputStream != null) {
            inputStream.close();
        }

        if (inputStreamIterator.hasNext()) {
            inputStream = (InputStream) inputStreamIterator.next();
            if (inputStream == null)
                throw new NullPointerException();
        }
        else inputStream = null;
    }

    /**
     * Returns an estimate of the number of bytes that can be read (or
     * skipped over) from the current underlying input stream without
     * blocking by the next invocation of a method for the current
     * underlying input stream. The next invocation might be
     * the same thread or another thread.  A single read or skip of this
     * many bytes will not block, but may read or skip fewer bytes.
     * <p>
     * This method simply calls {@code available} of the current underlying
     * input stream and returns the result.
     *
     * @return an estimate of the number of bytes that can be read (or
     *         skipped over) from the current underlying input stream
     *         without blocking or {@code 0} if this input stream
     *         has been closed by invoking its {@link #close()} method
     * @exception  IOException  if an I/O error occurs.
     */
    public int available() throws IOException {
        if(inputStream == null) {
            return 0; // no way to signal EOF from available()
        }
        return inputStream.available();
    }

    /**
     * Reads the next byte of data from this input stream. The byte is
     * returned as an <code>int</code> in the range <code>0</code> to
     * <code>255</code>. If no byte is available because the end of the
     * stream has been reached, the value <code>-1</code> is returned.
     * This method blocks until input data is available, the end of the
     * stream is detected, or an exception is thrown.
     * <p>
     * This method
     * tries to read one character from the current substream. If it
     * reaches the end of the stream, it calls the <code>close</code>
     * method of the current substream and begins reading from the next
     * substream.
     *
     * @return     the next byte of data, or <code>-1</code> if the end of the
     *             stream is reached.
     * @exception  IOException  if an I/O error occurs.
     */
    public int read() throws IOException {
        if (inputStream == null) {
            return -1;
        }
        int c = inputStream.read();
        if (c == -1) {
            nextStream();
            return read();
        }
        return c;
    }

    /**
     * Reads up to <code>len</code> bytes of data from this input stream
     * into an array of bytes.  If <code>len</code> is not zero, the method
     * blocks until at least 1 byte of input is available; otherwise, no
     * bytes are read and <code>0</code> is returned.
     * <p>
     * The <code>read</code> method of <code>SequenceInputStream</code>
     * tries to read the data from the current substream. If it fails to
     * read any characters because the substream has reached the end of
     * the stream, it calls the <code>close</code> method of the current
     * substream and begins reading from the next substream.
     *
     * @param      b     the buffer into which the data is read.
     * @param      off   the start offset in array <code>b</code>
     *                   at which the data is written.
     * @param      len   the maximum number of bytes read.
     * @return     int   the number of bytes read.
     * @exception  NullPointerException If <code>b</code> is <code>null</code>.
     * @exception  IndexOutOfBoundsException If <code>off</code> is negative,
     * <code>len</code> is negative, or <code>len</code> is greater than
     * <code>b.length - off</code>
     * @exception  IOException  if an I/O error occurs.
     */
    public int read(byte b[], int off, int len) throws IOException {
        if (inputStream == null) {
            return -1;
        } else if (b == null) {
            throw new NullPointerException();
        } else if (off < 0 || len < 0 || len > b.length - off) {
            throw new IndexOutOfBoundsException();
        } else if (len == 0) {
            return 0;
        }

        int n = inputStream.read(b, off, len);
        if (n <= 0) {
            nextStream();
            return read(b, off, len);
        }
        return n;
    }

    /**
     * Closes this input stream and releases any system resources
     * associated with the stream.
     * A closed <code>SequenceInputStream</code>
     * cannot  perform input operations and cannot
     * be reopened.
     * <p>
     * If this stream was created
     * from an enumeration, all remaining elements
     * are requested from the enumeration and closed
     * before the <code>close</code> method returns.
     *
     * @exception  IOException  if an I/O error occurs.
     */
    public void close() throws IOException {
        do {
            nextStream();
        } while (inputStream != null);
    }
    
    /**
     * A default implementation of an Iterator that can take Iterators of byte[] or File
     * and iterates over InputStreams built around the original iterator.
     *
     * @param <T>
     */
    private static class InputStreamIter<T> implements Iterator<InputStream> {
    	private final Iterator<T> iter;
    	
    	public InputStreamIter(Iterator<T> iter) {
    		this.iter = iter;
    	}

    	@Override
    	public boolean hasNext() {
    		return this.iter.hasNext();
    	}

    	@Override
    	public InputStream next() {
    		T dataElement = iter.next();
    		InputStream retVal = null;
    		
    		try {
    			if(dataElement instanceof byte[]) {
    				retVal = new ByteArrayInputStream((byte[]) dataElement);
    			} else if (dataElement instanceof File) {
    				retVal = new FileInputStream((File) dataElement);
    			} else {
    				throw new UnsupportedDataTypeException("Cannot create an InputStream around " + dataElement.getClass().getSimpleName());
    			}
    		} catch (Exception e) {
    			throw new RuntimeException(e);
    		}
    		
    		return retVal;
    	}

    	@Override
    	public void remove() {
    		throw new UnsupportedOperationException("Operation remove() is not implemented in class " + this.getClass().getSimpleName());
    	}
    }
}
