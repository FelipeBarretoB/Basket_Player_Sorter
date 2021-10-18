package dataStructureTrees;

public class Node<T> implements Comparable<T>{
	
	private T val;
	private Node<T> left;
	private Node<T> right;
	private Node<T> parent;
	
	public Node(T el) {
		val = el;
	}

	public T getVal() {
		return val;
	}

	public void setVal(T val) {
		this.val = val;
	}

	public Node<T> getLeft() {
		return left;
	}

	public void setLeft(Node<T> left) {
		this.left = left;
	}

	public Node<T> getRight() {
		return right;
	}

	public void setRight(Node<T> right) {
		this.right = right;
	}

	public Node<T> getParent() {
		return parent;
	}

	public void setParent(Node<T> parent) {
		this.parent = parent;
	}

	@Override
	public int compareTo(T n) {
		if (val.toString().hashCode() == n.toString().hashCode()) {
			return 0;
		} else if (val.toString().hashCode() > n.toString().hashCode()) {
			return 1;
		} else {
			return -1;
		}
	}
	
}

