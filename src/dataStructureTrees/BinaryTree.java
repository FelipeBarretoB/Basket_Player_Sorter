package dataStructureTrees;

import java.util.ArrayList;


public class BinaryTree<T,E> {
	private Node<T,E> root;
	
	public BinaryTree() {
		
	}

	public Node<T,E> getRoot() {
		return root;
	}

	public void setRoot(Node<T,E> root) {
		this.root = root;
	}
	
	// -------------------------------------- //
	
	public void insert(T el, E player) {
		Node<T,E> node = new Node<>(el, player);
		Node<T,E> temp = null;
		Node<T,E> current = root;
		while (current != null) {
			temp = current;
			if (node.getVal().hashCode() < current.getVal().hashCode()/*node.compareTo(current.getVal()) < 0*/) {
				current = current.getLeft();
			} else {
				current = current.getRight();
			}
		}
		node.setParent(temp);
		if (temp == null) {
			root = node;
		} else if (node.getVal().hashCode() < temp.getVal().hashCode()/*node.compareTo(temp.getVal()) < 0*/) {
			temp.setLeft(node);
		} else {
			temp.setRight(node);
		}
		
		//System.out.println(node.getVal());
		/*if (temp != null) {
			System.out.println("-----PARENT " + temp.getVal() + " -----");
			System.out.println("Added child: " + node.getVal());
			if (temp.getLeft() != null) {
				System.out.println("Node left: " + temp.getLeft().getVal());
			}
			if (temp.getRight() != null ) {
				System.out.println("Node right: " + temp.getRight().getVal());
			}			
		}*/
	}
	
	public Node<T,E> search(T el, E player) {
		Node<T,E> node = new Node<>(el, player);
		Node<T,E> current = root;
		boolean notFound = false;
		if (root != null) {
		
			while (current != null && !notFound) {
				// If it's the number
				if (node.compareTo(current.getVal()) == 0) {
					notFound = true;
				// If is greater
				} else if (node.compareTo(current.getVal()) > 0) {
					current = current.getRight();
				// If is smaller
				} else {
					current = current.getLeft();
				}
			}
		} else {
			return null;
		}
		return current;
	}
	
	public ArrayList<Node<T,E>> getSameValueNodes(T el, E player){
		//TODO Creo que no está del todo bien el método
		Node<T,E> current = search(el,player);
		ArrayList<Node<T,E>> nodes = new ArrayList<Node<T,E>>();
		nodes.add(current);
		
		while (current.getRight() != null && current.getVal().hashCode() == current.getRight().getVal().hashCode()) {
			current = current.getRight();
			nodes.add(current);
		}
		return nodes;
	}
	
	public Node<T,E> treeMinimum(Node<T,E> node) {
		while (node.getLeft() != null) {
			node = node.getLeft();
		}
		return node;
	}
	
	public Node<T,E> treeSuccessor(Node<T,E> node) {
		Node<T,E> temp = null;
		if (node.getRight() != null) {
			return treeMinimum(node.getRight());
		}
		temp = node.getParent();
		while (temp != null && node == temp.getRight()) {
			node = temp;
			temp = temp.getParent();
		}
		return temp;
	}
	
	/*
	public Node<T,E> delete(T el) {
		Node<T,E> node = search(el);
		Node<T,E> temp = null;
		Node<T,E> current = null;
		if (node.getLeft() == null || node.getRight() == null) {
			temp = node;
		} else {
			temp = treeSuccessor(node);
		}
		
		if (temp.getLeft() != null) {
			current = temp.getLeft();
		} else {
			current = temp.getRight();
		}
		
		if (current != null) {
			current.setParent(temp.getParent());
		}
		
		if (temp.getParent() == null) {
			root = current;
		} else if (temp == temp.getParent().getLeft()) {
			temp.getParent().setLeft(current);
		} else {
			temp.getParent().setRight(current);
		}
		
		if (temp != node) {
			node.setVal(temp.getVal());
		}
		
		return temp;
	}
	*/
	// InOrder
	public void printTree(Node<T,E> root) {
		if (root == null) {
			return;
		}
		printTree(root.getLeft());
		System.out.println(root.getVal());
		printTree(root.getRight());
		
		return;
	}
	
}

