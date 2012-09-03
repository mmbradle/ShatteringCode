package shatteringstone.sandbox.interviewquestions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class CofCRecursive<T> implements Iterable<T>{
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

			private void setupInnerIter() {
				if(null != innerIter && innerIter.hasNext()) {
					/*Everything is ready to go, do nothing*/
				} else if (!outerIter.hasNext() && (null == innerIter || !innerIter.hasNext())) {
					/*That was the last element set innerIter to null*/
					innerIter = null;
				} else {
					/*Default case*/
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
				assert innerIter.hasNext():"Failed sanity check";
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
				throw new UnsupportedOperationException("Remove is not implemented");
			}
		};
	}
	
    public static void main(String[] args) {
        List<String> al1 = new ArrayList<String>(10);
        List<String> al2 = new ArrayList<String>(10);
        List<String> al3 = new ArrayList<String>(10);
        List<String> alEmpty = new ArrayList<String>(10);
        al1.add("Easy as");
        al2.add("A");
        al2.add("B");
        al2.add("C");
        al3.add("1");
        al3.add("2");
        al3.add("3");
        
        CofCRecursive<String> cOfC1= new CofCRecursive<String>();
        CofCRecursive<String> cOfC2= new CofCRecursive<String>();
        CofCRecursive<String> cOfC3= new CofCRecursive<String>();
        cOfC1.add(al1);
        cOfC1.add(al2);
        cOfC1.add(al3);
        cOfC2.add(al1);
        cOfC2.add(al2);
        cOfC2.add(al3);
        
        cOfC3.add(alEmpty);
        cOfC3.add(al1);
        cOfC3.add(alEmpty);
        cOfC3.add(al2);
        cOfC3.add(alEmpty);
        cOfC3.add(al3);
        cOfC3.add(alEmpty);
        Iterator<String> itr1 = cOfC1.iterator();
        Iterator<String> itr2 = cOfC2.iterator();
        Iterator<String> itr3 = cOfC3.iterator();

        while (itr1.hasNext()) {
            Object element = itr1.next();
            System.out.print(element);
            if(itr1.hasNext()) System.out.print(", ");
        }
        System.out.println();
        while (itr2.hasNext()) {
            Object element = itr2.next();
            System.out.print(element);
            if(itr2.hasNext()) System.out.print(", ");
        }
        System.out.println();
        while (itr3.hasNext()) {
            Object element = itr3.next();
            System.out.print(element);
            if(itr3.hasNext()) System.out.print(", ");
        }
    }

}
