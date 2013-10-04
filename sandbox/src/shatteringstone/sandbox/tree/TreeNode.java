package shatteringstone.sandbox.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Iterator;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

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

    public Iterator<TreeNode<T>> getPostOrderIter() {
        final LinkedList<TreeNode<T>> stack = new LinkedList<TreeNode<T>>();
        populatePostOrder(this, stack);
        return stack.iterator();
    }

    public Iterator<TreeNode<T>> getPreOrderIter() {
        final LinkedList<TreeNode<T>> stack = new LinkedList<TreeNode<T>>();
        populatePreOrder(this, stack);
        return stack.iterator();
    }
    
    public Iterator<TreeNode<T>> getInOrderIter() {
        final LinkedList<TreeNode<T>> stack = new LinkedList<TreeNode<T>>();
        populateInOrder(this, stack);
        return stack.iterator();
    }
    
    private void populateInOrder(TreeNode<T> currentNode, final LinkedList<TreeNode<T>> stack) {
        List<TreeNode<T>> children = currentNode.getChildren();
        if (children.size() == 0) {
            stack.addLast(currentNode);
        } else if (children.size() == 1) {
            stack.addLast(currentNode);
            populateInOrder(children.get(0), stack);
        } else if (children.size() == 2) {
            populateInOrder(children.get(0), stack);
            stack.addLast(currentNode);
            populateInOrder(children.get(1), stack);
        } else {
            throw new IllegalArgumentException("Can't create an in-order iterator for a node with " + children.size() + " children.");
        }
    }

    private void populatePostOrder(TreeNode<T> currentNode, final LinkedList<TreeNode<T>> stack) {
        List<TreeNode<T>> children = currentNode.getChildren();
        for (TreeNode<T> childNode : children) {
            populatePostOrder(childNode, stack);
        }
        stack.addLast(currentNode);
    }

    private void populatePreOrder(TreeNode<T> currentNode, final LinkedList<TreeNode<T>> stack) {
        List<TreeNode<T>> children = currentNode.getChildren();
        stack.addLast(currentNode);
        for (TreeNode<T> childNode : children) {
            populatePreOrder(childNode, stack);
        }
    }

}