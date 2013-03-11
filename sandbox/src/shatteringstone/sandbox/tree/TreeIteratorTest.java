package shatteringstone.sandbox.tree;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

public class TreeIteratorTest {

	@Test
	public void test() {
		TreeNode<String> rootNode = buildTree();
		
		TreeNode<String> prev = null;
		TreeNode<String> curr = rootNode;
		
		LinkedList<TreeNode<String>> stack = new LinkedList<TreeNode<String>>();
		stack.push(rootNode);
		while (null != curr) {
			if(!curr.getChildren().contains(prev)) {
				while (curr.hasChildren()) {
					List<TreeNode<String>> list = curr.getChildren();
					for (int i = list.size()-1; i >= 0; i--) {
						stack.push(list.get(i));
					}
					curr = curr.getChild(0);
				}
			}
			prev = curr;
			curr = stack.pop();
			System.out.println(curr);
			curr = stack.peek();
		}
	}
	
	@Test
	public void test2() {
		TreeNode<String> root = buildTree();
		
		for (TreeNode<String> treeNode : root) {
			System.out.println(treeNode);
		}
	}
	
	private static TreeNode<String> buildTree() {
		TreeNode<String> addNode = new TreeNode<String>("3- ADD");
		addNode.addChild(new TreeNode<String>("1- 1"));
		addNode.addChild(new TreeNode<String>("2- 2"));
		
		TreeNode<String> ltNode = new TreeNode<String>("5- <");
		ltNode.addChild(addNode);
		ltNode.addChild(new TreeNode<String>("4- 3"));
		
		TreeNode<String> notNode = new TreeNode<String>("6- NOT");
		notNode.addChild(ltNode);
		
		TreeNode<String> root = new TreeNode<String>("7- AND");
		root.addChild(new TreeNode<String>("0- SELECTED"));
		root.addChild(notNode);
		
		return root;
	}
	


}
