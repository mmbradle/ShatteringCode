package shatteringstone.sandbox.interviewquestions;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;

public class MatrixReachability {
	private final int numNodes;
	private final RealMatrix connectionGraph;
	private final RealMatrix reachabilityMatrix;

	public MatrixReachability(final double[][] nodePaths) {
		if (
				nodePaths.length <= 0 || // Too small 
				nodePaths.length != nodePaths[0].length // Not square
				) { 
			throw new IllegalArgumentException();
		}
		
		connectionGraph = new Array2DRowRealMatrix(nodePaths);
		numNodes = nodePaths.length;
		reachabilityMatrix = computeReachabilityMatrix();
	}

	public int numUniversallyReachingNodes() {
		int count = 0;
		
		//Sum # rows which do not contain a 0
		for (int i = 0; i < numNodes; i++) {
			double[] row = reachabilityMatrix.getRow(i);
			if (connectsToAll(row)) {
				count++;
			}
		}
		return count;
	}

	public int numUniversallyReachableNodes() {
		int count = 0;
		
		//Sum # cols which do not contain a 0
		for (int i = 0; i < numNodes; i++) {
			double[] col = reachabilityMatrix.getColumn(i);
			if (connectsToAll(col)) {
				count++;
			}
		}
		return count;
	}

	/**
	 * Based on: http://www.cs.utexas.edu/users/misra/Classes.dir/ClassNotes.dir/Graphs.pdf<br>
	 * Computational Complexity O(n^4). Assuming matrix power operation is non-optimized i.e. O(n^3)
	 */
	private final RealMatrix computeReachabilityMatrix() {
		RealMatrix tempMatrix = this.connectionGraph;
		for (int i = 1; i < numNodes; i++) {
			tempMatrix = tempMatrix.add(connectionGraph.power(i+1));
		}
		
		// Set diagonals to 1's
		for (int i = 0; i < numNodes; i++) {
			tempMatrix.setEntry(i, i, 1);
		}
		
		return tempMatrix.copy();
	}
	
	private final boolean connectsToAll(double[] array) {
		for (double entry : array) {
			// double comparison can be tricky, cast to an int and compare to 0
			if ((int)entry == 0) {
				return false;
			}
		}
		return true;
	}
}
