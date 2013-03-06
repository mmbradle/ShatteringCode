package shatteringstone.sandbox.interviewquestions;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

public class CofCRecursiveTest {
	final static Collection<String> strSet = new HashSet(1);
	static {
		strSet.add("Str");
	}
	
	final static Collection<Integer> blah = new LinkedList<Integer>(); 
	
	final static Collection<Object> objCol = new ArrayList<Object>(3);
	static {
		objCol.add("String");
		objCol.add(1);
		objCol.add(strSet);
	}
	
	@Test
	public void testEmptyCollection() {
		Collection<Object> col = new ArrayList<Object>();
		Collection<Collection<Object>> col2 = new ArrayList<Collection<Object>>();
		col2.add(col);
		
		CofCRecursive<Object> cOfC  = new CofCRecursive<Object>(col2);
		for (Object object : cOfC) {
			fail("empty collection should not have: " + object.toString());
		}
	}
	
	@Test
	public void visualTest() {
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
