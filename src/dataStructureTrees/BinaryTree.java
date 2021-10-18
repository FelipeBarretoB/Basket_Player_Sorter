package dataStructureTrees;

public class BinaryTree<T> {
	private Node<T> root;
	
	public BinaryTree() {
		
	}

	public Node<T> getRoot() {
		return root;
	}

	public void setRoot(Node<T> root) {
		this.root = root;
	}
	
	// -------------------------------------- //
	
	public void insert(T el) {
		Node<T> node = new Node<>(el);
		Node<T> temp = null;
		Node<T> current = root;
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
	
	public Node<T> search(T el) {
		Node<T> node = new Node<>(el);
		Node<T> current = root;
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
	
	public Node<T> treeMinimum(Node<T> node) {
		while (node.getLeft() != null) {
			node = node.getLeft();
		}
		return node;
	}
	
	public Node<T> treeSuccessor(Node<T> node) {
		Node<T> temp = null;
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
	
	public Node<T> delete(T el) {
		Node<T> node = search(el);
		Node<T> temp = null;
		Node<T> current = null;
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
	// InOrder
	public void printTree(Node<T> root) {
		if (root == null) {
			return;
		}
		printTree(root.getLeft());
		System.out.println(root.getVal());
		printTree(root.getRight());
		
		return;
	}
	
}

