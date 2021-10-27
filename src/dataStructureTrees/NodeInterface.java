package dataStructureTrees;

public interface NodeInterface<T,E> {
	public T getVal();
	
	public void setVal(T val);
	
	public Node<T,E> getLeft();
	
	public void setLeft(Node<T,E> left);
	
	public Node<T,E> getRight();
	
	public void setRight(Node<T,E> right);
	
	public Node<T,E> getParent();
	
	public void setParent(Node<T,E> parent);
	
	public E getPlayer();
	
	public void setPlayer(E player);
	
	public int compareTo(T n);
}
