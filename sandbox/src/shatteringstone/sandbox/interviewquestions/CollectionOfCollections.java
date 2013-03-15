package shatteringstone.sandbox.interviewquestions;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CollectionOfCollections<T> implements Iterable<T> {
    private List<List<T>> list;
    private int pick = 0;

    CollectionOfCollections(int size, int pick) {
        list = new ArrayList<List<T>>(size);
        this.pick = pick;
    }

    public void add(List<T> item) {
        list.add(item);
    }

    private class DoubleCountingIterator implements Iterator<T>{
        int i = 0;
        int j = 0;
        private boolean innerHasNext(){return (j)<list.get(i).size();}
        private boolean outerHasNext(){return (i+1)<list.size();}

        @Override
        public boolean hasNext() {  
            return innerHasNext() || outerHasNext();
        }

        @Override
        public T next() {
            if (!innerHasNext()){
                i++;
                j=0;
            }
            return list.get(i).get(j++);
        }

        @Override
        public void remove() {
            list.remove(i);
        }        
    }

    private class CountingIterator implements Iterator<T>{
        int i = 0;
        Iterator<T> innerIter = list.get(i).iterator(); //break here if empty

        @Override
        public boolean hasNext() {  
            return innerIter.hasNext() || (i+1)<list.size();
        }

        @Override
        public T next() {
            if (!innerIter.hasNext()){
                i++;
                innerIter = list.get(i).iterator();
            }
            return innerIter.next();
        }

        @Override
        public void remove() {
            list.remove(i);
        }        
    }

    private class IteratingIterator implements Iterator<T> {
        Iterator<List<T>> outerIter = list.iterator();
        Iterator<T> innerIter = outerIter.next().iterator(); //break here if empty

        @Override
        public boolean hasNext() {  
            return innerIter.hasNext() || outerIter.hasNext();
        }

        @Override
        public T next() {
            if (!innerIter.hasNext()){
                innerIter = outerIter.next().iterator();
            }
            return innerIter.next();
        }

        @Override
        public void remove() {
        }
    }

    @Override
    public Iterator<T> iterator() {
        if (pick == 0) return new DoubleCountingIterator();
        else if (pick == 1) return new CountingIterator();
        else return new IteratingIterator();
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

        CollectionOfCollections<String> cOfC1= new CollectionOfCollections<String>(3, 0);
        CollectionOfCollections<String> cOfC2= new CollectionOfCollections<String>(3, 1);
        CollectionOfCollections<String> cOfC3= new CollectionOfCollections<String>(3, 2);
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
