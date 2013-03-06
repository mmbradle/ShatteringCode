package shatteringstone.sandbox.interviewquestions;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * From: http://www.pidby.com/2009/07/puzzling-take-home-interview-assignment.html
 */
public class TeamBuilder {
	private final Node[] nodes;
	
	public TeamBuilder(final int[][] nodePaths) {
		nodes = new Node[nodePaths.length];
		for (int parentNdx = 0; parentNdx < nodePaths.length; parentNdx++) {
			for (int childNdx = 0; childNdx < nodePaths.length; childNdx++) {
				nodes[parentNdx].addChild(nodes[nodePaths[parentNdx][childNdx]]);
			}
		}
	}
	
	public int canReachAll() {
		return 0;
	}
}

class Node implements Iterable<Node>{
	
	private final Set<Node> children = new HashSet<Node>();
	
	public Node(int[] paths) {
		
	}
	
	public void addChild(Node node) {
		children.add(node);
	}
	
	public boolean canReach(Node node) {
		Node child;
		Iterator<Node> iterator = this.iterator();
		while (iterator.hasNext()) {
			child = iterator.next();
			if (child == node) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Iterator<Node> iterator() {
		return new Iterator<Node>() {
			Iterator<Node> outerIter = children.iterator();
			Iterator<Node> innerIter = null;// = outerIter.next().iterator();

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
			public Node next() {
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

