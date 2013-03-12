package shatteringstone.sandbox;

import org.javatuples.*;
/*
There are ten tuple classes in javatuples:

Unit<A> (1 element)
Pair<A,B> (2 elements)
Triplet<A,B,C> (3 elements)
Quartet<A,B,C,D> (4 elements)
Quintet<A,B,C,D,E> (5 elements)
Sextet<A,B,C,D,E,F> (6 elements)
Septet<A,B,C,D,E,F,G> (7 elements)
Octet<A,B,C,D,E,F,G,H> (8 elements)
Ennead<A,B,C,D,E,F,G,H,I> (9 elements)
Decade<A,B,C,D,E,F,G,H,I,J> (10 elements)
Plus a couple of very common 2-element tuple classes equivalent to Pair, just for the sake of code semantics:

KeyValue<A,B>
LabelValue<A,B>
All tuple classes are:

Typesafe
Immutable
Iterable
Serializable
Comparable (implements Comparable<Tuple>)
Implementing equals(...) and hashCode()
Implementing toString()
 */
public class Tuples {
    Pair<String, Integer> pair1 = Pair.with("hello", Integer.valueOf(23));
    //pair1.getValue0();
    Pair<String, Integer> pair2 = new Pair<String, Integer>("hello",
            Integer.valueOf(23));
    Quintet<String, Integer, Double, String, String> quintet = Quintet.with(
            "a", Integer.valueOf(3), Double.valueOf(34.2), "b", "c");
}
