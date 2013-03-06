package shatteringstone.sandbox.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Iterator;

public class TreeNode<T> implements Iterable<TreeNode<T>>{
	private final List<TreeNode<T>> children = new ArrayList<TreeNode<T>>();
	private final T value;

	public TreeNode(T value) {
		this.value = value;
	}

	public void addChild(TreeNode<T> child ) {
		this.children.add(child);
	}

	public TreeNode<T> getChild(int childNumber) {
		return this.children.get(childNumber);
	}

	public List<TreeNode<T>> getChildren() {
		return this.children;
	}

	public boolean hasChildren() {
		return (this.children.size() > 0);
	}

	@Override
	public String toString() {
		return "TreeNode [value=" + value + "]";
	}

	@Override
	public Iterator<TreeNode<T>> iterator() {
		return new Iterator<TreeNode<T>>() {
			TreeNode<T> prev = null;
			TreeNode<T> curr = TreeNode.this;
			final LinkedList<TreeNode<T>> stack = new LinkedList<TreeNode<T>>();

			/* Initializer */ {
				stack.push(curr);
			}

			@Override
			public boolean hasNext() {
				return (this.stack.size() > 0);
			}

			@Override
			public TreeNode<T> next() {
				TreeNode<T> retVal = null;
				if(!curr.getChildren().contains(prev)) {
					while (curr.hasChildren()) {
						List<TreeNode<T>> list = curr.getChildren();
						for (int i = list.size()-1; i >= 0; i--) {
							stack.push(list.get(i));
						}
						curr = curr.getChild(0);
					}
				}
				retVal = stack.pop();
				prev = curr;
				curr = stack.peek();
				return retVal;
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();

			}
		};
	}
}