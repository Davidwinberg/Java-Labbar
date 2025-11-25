package bst;

import java.util.ArrayList;
import java.util.Comparator;


public class BinarySearchTree<E> {
  BinaryNode<E> root;  // Används också i BSTVisaulizer
  int size;            // Används också i BSTVisaulizer
  private Comparator<E> comparator;
    
	/**
	 * Constructs an empty binary search tree.
	 */
	public BinarySearchTree() {
		root = null;
		size = 0;
		comparator = (e1, e2) -> ((Comparable<E>) e1).compareTo(e2);
	}
	
	/**
	 * Constructs an empty binary search tree, sorted according to the specified comparator.
	 */
	public BinarySearchTree(Comparator<E> comparator) {
		root = null;
		size = 0;
		this.comparator = comparator;
	}

	/**
	 * Inserts the specified element in the tree if no duplicate exists.
	 * @param e element to be inserted
	 * @return true if the the element was inserted
	 */
	public boolean add(E e) {
		if (root == null){                        // gör detta om trädet är tomt
			root = new BinaryNode<>(e);
			size ++;
			return true;
		}
		return add(root, e);
	}

	private boolean add(BinaryNode<E> node, E e){
		int compare = comparator.compare(e, node.element);

		if (compare == 0){                            //dublett, lägg ej till
			return false;
		}
		else if (compare < 0){                           //e är mindre, gå till vänster
			if(node.left == null){                       // om nästa nod är tom, lägg till e
				node.left = new BinaryNode<>(e);
				size++;
				return true;
			}
			else return add(node.left, e);
		}
		else{                                            // e är större, gå höger
			if(node.right == null){
				node.right = new BinaryNode<>(e);
				size++;
				return true;
			}
			else return add(node.right, e);
		}
	}
	
	/**
	 * Computes the height of tree.
	 * @return the height of the tree
	 */
	public int height() {
		return height(root);
	}
	private int height(BinaryNode<E> node){
		if(node == null){
			return 0;
		}
		return 1 + Math.max(height(node.left), height(node.right)); // går hela vägen ner i trädet och adderar med 1 hela vägen upp
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
	 * Builds a complete tree from the elements in the tree.
	 */
	public void rebuild() {
		ArrayList<E> element = new ArrayList<>();                 //skapa lista
		toArray(root, element);                                   //lägg till noder i listan
		root = buildTree(element, 0, element.size()-1);    //bygg trädet
	}
	
	/*
	 * Adds all elements from the tree rooted at n in inorder to the list sorted.
	 */
	private void toArray(BinaryNode<E> n, ArrayList<E> sorted) {
		if(n == null) return;
		toArray(n.left, sorted);
		sorted.add(n.element);
		toArray(n.right, sorted);
		
	}

	@Override
	public String toString(){
		StringBuilder sträng = new StringBuilder();        // skapar en sträng att bygga
		toString(root, sträng);                            //Går igenom trädet
		return sträng.toString();                          // Tar stringbuilders egna toString metod och omvandlar till en sträng
	}

	private void toString(BinaryNode<E> node, StringBuilder sträng){
		if(node == null) return;
		toString(node.left, sträng);
		sträng.append(node.element).append("\n");
		toString(node.right, sträng);
	}
	
	/*
	 * Builds a complete tree from the elements from position first to 
	 * last in the list sorted.
	 * Elements in the list a are assumed to be in ascending order.
	 * Returns the root of tree.
	 */
	private BinaryNode<E> buildTree(ArrayList<E> sorted, int first, int last) {
		if (first > last) return null;                                          //Om det är sista elementet den ska lägga till så returnera null

		int mid = first + (last-first)/2;
		BinaryNode<E> node = new BinaryNode<E>(sorted.get(mid));                //skapar nod av mittelementet
		
		node.left = buildTree(sorted, first, mid-1);                            //skapar nya noder till vänster 
		node.right = buildTree(sorted, mid+1, last);
		
		return node;
	}
	


	static class BinaryNode<E> {
		E element;
		BinaryNode<E> left;
		BinaryNode<E> right;

		private BinaryNode(E element) {
			this.element = element;
		}	
	}
	
	public static void main(String[] args){
		BinarySearchTree<Integer> skevtTräd = new BinarySearchTree<>();
		skevtTräd.add(1);
		skevtTräd.add(3);
		skevtTräd.add(5);
		skevtTräd.add(7);
		skevtTräd.add(9);
		skevtTräd.add(11);
		skevtTräd.add(13);
		skevtTräd.rebuild();
		BSTVisualizer diagram = new BSTVisualizer("Skevt träd", 5, skevtTräd.height());
		diagram.drawTree(skevtTräd);

		BinarySearchTree<Integer> träd = new BinarySearchTree<>();
		träd.add(10);
		träd.add(5);
		träd.add(20);
		träd.add(2);
		träd.add(7);
		//BSTVisualizer diagram2 = new BSTVisualizer("Träd", 5, träd.height());
		//diagram2.drawTree(träd);

	}
}



//F2.  a) 10,20,47,30     b) 3    c) 4
//F3.  a) 0, 1 + max(höjd(vänster), höjd(höger))              b) Math.max(int a, int b)
//F4.  int mid = first + (last-first)/2