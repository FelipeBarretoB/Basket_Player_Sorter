package dataStructureTrees;

public class RBNode<T,E> extends Node<T, E> {
	private Color color;
	
	
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
}
