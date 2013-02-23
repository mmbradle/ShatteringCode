package shatteringstone.sandbox.io;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import javax.activation.UnsupportedDataTypeException;

public class IteratingInputStream extends InputStream {
    Iterator<? extends InputStream> e;
    InputStream in;

    public IteratingInputStream(Iterator<? extends InputStream> e) {
        this.e = e;
        try {
            nextStream();
        } catch (IOException ex) {
            // This should never happen
            throw new Error("panic");
        }
    }
    
    public <ElemType, ListType extends List<ElemType>> IteratingInputStream(ListType data) {
        this.e = new InputStreamIter<ElemType>(data.iterator());
        try {
            nextStream();
        } catch (IOException ex) {
            // This should never happen
            throw new Error("panic");
        }
    }

    final void nextStream() throws IOException {
        if (in != null) {
            in.close();
        }

        if (e.hasNext()) {
            in = (InputStream) e.next();
            if (in == null)
                throw new NullPointerException();
        }
        else in = null;
    }

    public int available() throws IOException {
        if(in == null) {
            return 0; // no way to signal EOF from available()
        }
        return in.available();
    }

    public int read() throws IOException {
        if (in == null) {
            return -1;
        }
        int c = in.read();
        if (c == -1) {
            nextStream();
            return read();
        }
        return c;
    }

    public int read(byte b[], int off, int len) throws IOException {
        if (in == null) {
            return -1;
        } else if (b == null) {
            throw new NullPointerException();
        } else if (off < 0 || len < 0 || len > b.length - off) {
            throw new IndexOutOfBoundsException();
        } else if (len == 0) {
            return 0;
        }

        int n = in.read(b, off, len);
        if (n <= 0) {
            nextStream();
            return read(b, off, len);
        }
        return n;
    }

    public void close() throws IOException {
        do {
            nextStream();
        } while (in != null);
    }
    
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
