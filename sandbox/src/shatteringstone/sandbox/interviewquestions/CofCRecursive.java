package shatteringstone.sandbox.interviewquestions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class CofCRecursive<T> implements Iterable<T> {
    Collection<Collection<T>> collection = new ArrayList<Collection<T>>();

    public CofCRecursive() {
    }

    public CofCRecursive(Collection<Collection<T>> collection) {
        this.collection = collection;
    }

    public void add(Collection<T> item) {
        collection.add(item);
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Iterator<Collection<T>> outerIter = collection.iterator();
            Iterator<T> innerIter = null;// = outerIter.next().iterator();

            /**
             * Ensures the innerIter is setup to a place where calling .next()
             * on it will yield a valid result. Otherwise sets the innerIter to
             * null.
             */
            private void setupInnerIter() {
                if (null != innerIter && innerIter.hasNext()) {
                    /* Everything is ready to go, do nothing */
                } else if (!outerIter.hasNext()
                        && (null == innerIter || !innerIter.hasNext())) {
                    /* That was the last element set innerIter to null */
                    innerIter = null;
                } else {
                    /* Neither ready nor done. Causes recursion. */
                    innerIter = outerIter.next().iterator();
                    setupInnerIter();
                }
                return;
            }

            @Override
            public boolean hasNext() {
                setupInnerIter();
                if (innerIter == null) {
                    return false;
                }
                assert innerIter.hasNext() : "Failed sanity check";
                return true;
            }

            @Override
            public T next() {
                setupInnerIter();
                if (null == innerIter) {
                    throw new IndexOutOfBoundsException();
                }
                return innerIter.next();
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException(
                        "Remove is not implemented");
            }
        };
    }
}
