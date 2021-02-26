package bst;

import java.util.ArrayList;
import java.util.Comparator;


public class BinarySearchTree<E> {
  BinaryNode<E> root;  // Används också i BSTVisaulizer
  int size;            // Används också i BSTVisaulizer
  private Comparator<E> comparator;

	public static void main(String[] args) throws InterruptedException {
		BinarySearchTree<String> bst= new BinarySearchTree<>((s1, s2) -> {
			return s2.compareTo(s1);
		});
		BSTVisualizer bstVisualizer = new BSTVisualizer("Tree", 400, 400);
		bst.add("Kim");
		bst.add("Moritz");
		bst.add("Bitch");
		bst.add("Cunt");
		bst.add("Dick");
		bst.add("Gay");
		bst.add("Zenith");
		bst.add("Jesus");
		bst.add("Image");
		bst.add("Ztek");
		bst.add("Isek");

		bst.rebuild();
		bst.printTree();
		bstVisualizer.drawTree(bst);
	}
    
	/**
	 * Constructs an empty binary search tree.
	 */
	public BinarySearchTree() {
		this.root = null;
		this.size = 0;
		comparator = null;
	}
	
	/**
	 * Constructs an empty binary search tree, sorted according to the specified comparator.
	 */
	public BinarySearchTree(Comparator<E> comparator) {
		this.root = null;
		this.size = 0;
		this.comparator = comparator;
	}

	/**
	 * Inserts the specified element in the tree if no duplicate exists.
	 * @param x element to be inserted
	 * @return true if the the element was inserted
	 */
	public boolean add(E x) {
		if (x == null) {
			throw new IllegalArgumentException();
		} else if (root == null) {
			root = new BinaryNode<E>(x);
			size++;
			return true;
		} else {
			return addRecursive(root, x);
		}
	}


	/**
	 * Recursively adds an object to the tree.
	 * @param current root node in the binary tree
	 * @param x	object to be added
	 * @return if object was added
	 */
	private boolean addRecursive(BinaryNode<E> current, E x) {
		if (compare(x, current.element) == 0) {
			return false;
		} else if (compare(x, current.element) < 0) {
			if (current.left == null) {
				current.left = new BinaryNode<E>(x);
				size++;
				return true;
			}
			return addRecursive(current.left, x);
		} else if (compare(x, current.element) > 0) {
			if (current.right == null) {
				current.right = new BinaryNode<E>(x);
				size++;
				return true;
			}
			return addRecursive(current.right, x);
		} else { return false; }
	}

	/**
	 * Compares two node elements using the comparison method chosen
	 * @param k1 object 1
	 * @param k2 object 2
	 * @return comparison value as integer
	 */
	// Compares two node elements using the comparison method chosen
	@SuppressWarnings("unchecked")
	private int compare(Object k1, Object k2) {
		if (comparator == null) {
			return ((Comparable<E>)k1).compareTo((E) k2);
		} else {
			return comparator.compare((E) k1, (E) k2);
		}
	}
	
	/**
	 * Computes the height of tree.
	 * @return the height of the tree
	 */
	public int height() {
		return getTreeHeight(root);
	}

	/**
	 * Gets height of a tree with root root
	 * @param root Root of the tree
	 * @return height of the tree
	 */
	private int getTreeHeight(BinaryNode<E> root) {
		if (root == null)  {
			return 0;
		} else {
			int leftHeight = 1 + getTreeHeight(root.left);
			int rightHeight = 1 + getTreeHeight(root.right);
			return Math.max(leftHeight, rightHeight);
		}
	}
	
	/**
	 * Returns the number of elements in this tree.
	 * @return the number of elements in this tree
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Removes all of the elements from this list.
	 */
	public void clear() {
		root = null;
		size = 0;
	}
	
	/**
	 * Print tree contents in inorder.
	 */
	public void printTree() {
		recInorder(root);
	}

	/**
	 * Print tree with root root contents in inorder
	 * @param root root of the tree
	 */
	private void recInorder(BinaryNode<E> root) {
		if (root != null) {
			recInorder(root.left);
			System.out.println(root.element);
			recInorder(root.right);
		}
	}

	/** 
	 * Builds a complete tree from the elements in the tree.
	 */
	public void rebuild() {
		ArrayList<E> treeList = new ArrayList<E>();
		toArray(root, treeList);
		root = buildTree(treeList, 0, treeList.size()-1);
	}
	
	/*
	 * Adds all elements from the tree rooted at n in inorder to the list sorted.
	 */
	private void toArray(BinaryNode<E> n, ArrayList<E> sorted) {
		if (n == null) {
			return;
		} else {
			toArray(n.left, sorted);
			sorted.add(n.element);
			toArray(n.right, sorted);
		}
	}
	
	/*
	 * Builds a complete tree from the elements from position first to 
	 * last in the list sorted.
	 * Elements in the list a are assumed to be in ascending order.
	 * Returns the root of tree.
	 */
	private BinaryNode<E> buildTree(ArrayList<E> sorted, int first, int last) {
		if (first > last) {
			return null;
		}
		int mid = first + ((last - first) / 2);
		BinaryNode<E> root = new BinaryNode<E>(sorted.get(mid));
		root.left = buildTree(sorted, first, mid-1);
		root.right = buildTree(sorted, mid+1, last);
		return root;
	}
	


	static class BinaryNode<E> {
		E element;
		BinaryNode<E> left;
		BinaryNode<E> right;

		private BinaryNode(E element) {
			this.element = element;
		}
	}
	
}
