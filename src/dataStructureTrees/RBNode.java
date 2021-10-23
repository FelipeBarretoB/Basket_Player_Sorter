package dataStructureTrees;

public class RBNode<T,E> extends Node {
	private Color color;
	
	@SuppressWarnings("unchecked")
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
