package dataStructureTrees;

public class RedBlackTree<T,E> extends BinaryTree<T,E> {
	private RBNode<T,E> root;
	
	public RedBlackTree() {
		
	}

	public RBNode<T,E> getRoot() {
		return root;
	}

	public void setRoot(RBNode<T,E> root) {
		this.root = root;
	}
	
	/*@SuppressWarnings({ "unchecked", "rawtypes" })
	void rotateLeft(RBNode node){
		RBNode aux = (RBNode) node.getRight();
		node.setRight(aux.getLeft());
		aux.getLeft().setParent(node);
		aux.setParent(node.getParent());
		if(node.getParent()==null) {
			root = aux;
		} else if(node.getParent().getLeft()==node) {
			node.getParent().setLeft(aux);
		} else {
			node.getParent().setRight(aux);
		}
		aux.setLeft(node);
		node.setParent(aux);
	}/*/
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public RBNode rotateLeft(RBNode node){
		RBNode aux = (RBNode) node.getRight();
		node.setRight(aux.getLeft());
		aux.setLeft(node);
		aux.setColor(node.getColor());
		node.setColor(Color.RED);
		return aux;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public RBNode rotateRight(RBNode node){
		RBNode aux = (RBNode) node.getLeft();
		node.setLeft(aux.getRight());
		aux.setRight(node);
		aux.setColor(node.getColor());
		node.setColor(Color.RED);
		return aux;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void delete(RBNode node) {
		RBNode aux = (RBNode) node.getRight();
		delete((T)node.getVal(), (E)node.getPlayer());
		while(node != root && node.getColor() == Color.BLACK) {
			if(node == node.getParent().getLeft()) {
				aux = (RBNode) node.getParent().getRight();
				if(aux.getColor()==Color.RED) {
					aux.setColor(Color.BLACK);
					((RBNode<T, E>) node.getParent()).setColor(Color.RED);
					rotateLeft((RBNode)node.getParent());
					aux = (RBNode) node.getParent().getRight();
				}
				if(aux.getColor()==Color.BLACK && ((RBNode<T, E>) aux.getRight()).getColor()==Color.BLACK) {
					aux.setColor(Color.RED);
					node = (RBNode) node.getParent();
				} else if(((RBNode<T, E>) aux.getRight()).getColor()==Color.BLACK) {
					((RBNode<T, E>) aux.getLeft()).setColor(Color.BLACK);
					aux.setColor(Color.RED);
					rotateRight(aux);
					aux = (RBNode) node.getParent().getRight();
				}
				//aux.setColor(node.getParent().getRight());
				((RBNode<T, E>) node.getParent()).setColor(Color.BLACK);
				((RBNode<T, E>) aux.getRight()).setColor(Color.BLACK);
				rotateLeft((RBNode) node.getParent());
				node= root;
			}
		}
	}
	
	
	public void insert(RBNode<T, E> node) {
		RBNode<T, E> aux = (RBNode<T, E>) node.getRight();
		node.setColor(Color.RED);
		while(node != root && ((RBNode<T, E>) node.getParent()).getColor() == Color.RED) {
			if (node.getParent()== node.getParent().getParent().getLeft()) {
				aux = (RBNode<T, E>) node.getParent().getParent().getRight();
				if(aux.getColor().equals(Color.RED)) {
					((RBNode<T, E>) node.getParent()).setColor(Color.BLACK);
					aux.setColor(Color.BLACK);
					((RBNode<T, E>) node.getParent().getParent()).setColor(Color.RED);
					node = (RBNode<T, E>) node.getParent().getParent();
				}else if(node == (RBNode<T, E>) node.getParent().getRight()) {
					node = (RBNode<T, E>) node.getParent();
					rotateLeft(node);
					((RBNode<T, E>) node.getParent()).setColor(Color.BLACK);
					((RBNode<T, E>) node.getParent().getParent()).setColor(Color.RED);
					rotateRight((RBNode<T, E>) node.getParent().getParent());
				}
			} else {
				aux = (RBNode<T, E>) node.getParent().getParent().getLeft();
				if(aux.getColor().equals(Color.RED)) {
					((RBNode<T, E>) node.getParent()).setColor(Color.BLACK);
					aux.setColor(Color.BLACK);
					((RBNode<T, E>) node.getParent().getParent()).setColor(Color.RED);
					node = (RBNode<T, E>) node.getParent().getParent();
				}else if(node == (RBNode<T, E>) node.getParent().getLeft()) {
					node = (RBNode<T, E>) node.getParent();
					rotateRight(node);
					((RBNode<T, E>) node.getParent()).setColor(Color.BLACK);
					((RBNode<T, E>) node.getParent().getParent()).setColor(Color.RED);
					rotateLeft((RBNode<T, E>) node.getParent().getParent());
				}
			}
		}
	}
}
