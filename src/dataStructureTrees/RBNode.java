package dataStructureTrees;

public class RBNode<T,E> extends Node<T, E> {
	private Color color;
	
	RBNode<T,E> left;
	RBNode<T,E> right;
	RBNode<T,E> parent;
	
	
	public RBNode(T el, E player) {
		super(el,player);
		this.color = Color.RED;
	}
	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
	@Override
	public RBNode<T,E> getLeft() {
		return left;
	}
	
	public void setLeft(RBNode<T,E> l) {
		left = l;
	}
	
	@Override
	public RBNode<T,E> getRight() {
		return left;
	}
	
	public void setRight(RBNode<T,E> r) {
		right = r;
	}
	
	@Override
	public RBNode<T,E> getParent() {
		return left;
	}
	
	public void setParent(RBNode<T,E> p) {
		parent = p;
	}
}
