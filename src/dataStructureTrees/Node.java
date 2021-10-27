package dataStructureTrees;



public class Node<T,E> implements Comparable<T>, NodeInterface<T,E>{
	
	private E player;
	private T val;
	private Node<T,E> left;
	private Node<T,E> right;
	private Node<T,E> parent;
	
	public Node(T el, E player) {
		val = el;
		this.player=player;
	}

	public T getVal() {
		return val;
	}

	public void setVal(T val) {
		this.val = val;
	}

	public Node<T,E> getLeft() {
		return left;
	}

	public void setLeft(Node<T,E> left) {
		this.left = left;
	}

	public Node<T,E> getRight() {
		return right;
	}

	public void setRight(Node<T,E> right) {
		this.right = right;
	}

	public Node<T,E> getParent() {
		return parent;
	}

	public void setParent(Node<T,E> parent) {
		this.parent = parent;
	}

	public E getPlayer() {
		return player;
	}

	public void setPlayer(E player) {
		this.player = player;
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

