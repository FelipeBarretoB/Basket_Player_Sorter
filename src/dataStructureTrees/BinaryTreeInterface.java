package dataStructureTrees;

import java.util.ArrayList;

public interface BinaryTreeInterface<T,E> {

	public Node<T,E> getRoot();
	
	public void insert(T el, E player);
	
	public void setRoot(Node<T,E> root);
	
	public Node<T,E> search(T el, E player);
	
	public ArrayList<Node<T,E>> getSameValueNodes(T el, E player);
	
	public Node<T,E> treeMinimum(Node<T,E> node);
	
	public Node<T,E> treeSuccessor(Node<T,E> node);
	
	public Node<T,E> delete(T el, E Player);
	
	public Node<T,E> deleteSpecificPlayer(T el, E Player);
	
	public Node<T,E> searchSpecificPlayer(T el, E player);
	
	public void printTree(Node<T,E> root);
}
