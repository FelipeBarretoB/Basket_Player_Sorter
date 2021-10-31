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
	
	
	public RBNode<T, E> rotateLeft(RBNode<T, E> node){
		RBNode<T, E> aux = node.getRight();
		node.setRight(aux.getLeft());
		aux.setLeft(node);
		aux.setColor(node.getColor());
		node.setColor(Color.RED);
		return aux;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public RBNode rotateRight(RBNode node){
		RBNode aux = node.getLeft();
		node.setLeft(aux.getRight());
		aux.setRight(node);
		aux.setColor(node.getColor());
		node.setColor(Color.RED);
		return aux;
	}
		
	public void delete(RBNode<T, E> node) {
		RBNode<T, E> aux = node.getRight();
		delete(node.getVal(), node.getPlayer());
		while(node != root && node.getColor() == Color.BLACK) {
			if(node == node.getParent().getLeft()) {
				aux = node.getParent().getRight();
				if(aux.getColor()==Color.RED) {
					aux.setColor(Color.BLACK);
					node.getParent().setColor(Color.RED);
					rotateLeft(node.getParent());
					aux = node.getParent().getRight();
				}
				if(aux.getColor()==Color.BLACK && (aux.getRight()).getColor()==Color.BLACK) {
					aux.setColor(Color.RED);
					node = node.getParent();
				} else if((aux.getRight()).getColor()==Color.BLACK) {
					(aux.getLeft()).setColor(Color.BLACK);
					aux.setColor(Color.RED);
					rotateRight(aux);
					aux = node.getParent().getRight();
				}
				//aux.setColor(node.getParent().getRight());
				(node.getParent()).setColor(Color.BLACK);
				(aux.getRight()).setColor(Color.BLACK);
				rotateLeft(node.getParent());
				node= root;
			}
		}
	}
	
	
	/*public void insert(T el, E player) {
		//System.out.println("1");
		insertTwo(root, el, player);
		
	}*/
	
	
	public void insert(T el, E player) {
		//System.out.println("2");
		//System.out.println(a);
		RBNode<T,E> node = new RBNode<>(el, player);
		RBNode<T,E> temp = null;
		RBNode<T,E> current = getRoot();
		while (current != null) {
			//System.out.println(current.getPlayer());
			System.out.println("2 + " + el);
			temp = current;
			if (node.getVal().hashCode() < current.getVal().hashCode()/*node.compareTo(current.getVal()) < 0*/) {
				current = current.getLeft();
			} else {
				current = current.getRight();
			}
		}
		node.setParent(temp);
		/*if (temp != null) {
			System.out.println(node.getParent().getPlayer());
			System.out.println(node.getPlayer());
		}*/
		
		if (temp == null) {
			System.out.println("2.1 + " + el);
			root = node;
			root.setColor(Color.BLACK);
		} else if (/*node.getVal().hashCode() < temp.getVal().hashCode()*/node.compareTo(temp.getVal()) < 0) {
			temp.setLeft(node);
			
		} else {
			temp.setRight(node);
			
		}
		
		insertFix(root,node);
		System.out.println("2.2 + " + el);
	}
	
	public void insertFix(RBNode<T, E> rt, RBNode<T, E> node) {
		//System.out.println("3");
		RBNode<T, E> aux;
		//node.setColor(Color.RED);
		while(node.getParent() != null && (node.getParent()).getColor() == Color.RED) {
			
			if (node.getParent()== node.getParent().getParent().getLeft()) {
				aux = node.getParent().getParent().getRight();
				
				if((aux != null && aux.getColor().equals(Color.BLACK)) || aux == null) {
					
					if(node == node.getParent().getRight()) {
						node = node.getParent();
						rotateLeft(node);
					}
					
					(node.getParent()).setColor(Color.BLACK);
					//aux.setColor(Color.BLACK);
					( node.getParent().getParent()).setColor(Color.RED);
					//node = (RBNode<T, E>) node.getParent().getParent();
					rotateRight(node.getParent().getParent());
					
				}else if(node == (RBNode<T, E>) node.getParent().getLeft() && aux != null && aux.getColor() == Color.RED) {
					node.setColor(Color.BLACK);
					rotateRight(node.getParent().getParent());
					
					if(node.getParent().getParent() != null && (node.getParent().getParent()).getColor() == Color.RED) {
						node = (RBNode<T, E>) node.getParent();
					}
					
				}else if(node == node.getParent().getRight() && aux != null && aux.getColor() == Color.RED) {
					node.setColor(Color.BLACK);
					rotateLeft(node.getParent().getParent());
					rotateRight(node.getParent().getParent());
					
					/*if(node.getParent() != null && ((RBNode<T, E>) node.getParent()).getColor() == Color.RED) {
						node = (RBNode<T, E>) node.getParent();
					}*/
					
				}else if(aux != null && aux.getColor() == Color.RED) {
					node.getParent().setColor(Color.BLACK);
					node.getParent().getParent().setColor(Color.RED);
					aux.setColor(Color.BLACK);
					node = node.getParent().getParent();
				}
				node = node.getParent();
				rotateLeft(node);
				
				rotateRight(node.getParent().getParent());
			} else {
				aux = node.getParent().getParent().getLeft();
				
				if((aux != null && aux.getColor().equals(Color.BLACK)) || aux == null) {
					
					if( node == node.getParent().getLeft()) {
						node = node.getParent();
						rotateRight(node);
					}
					
					(node.getParent()).setColor(Color.BLACK);
					//aux.setColor(Color.BLACK);
					(node.getParent().getParent()).setColor(Color.RED);
					//node = (RBNode<T, E>) node.getParent().getParent();
					rotateLeft(node.getParent().getParent());
					
					
				}else if(node == node.getParent().getRight() && aux != null && aux.getColor() == Color.RED) {
					node.setColor(Color.BLACK);
					rotateLeft( node.getParent().getParent());
					
					if(node.getParent().getParent() != null && (node.getParent().getParent()).getColor() == Color.RED) {
						node = node.getParent();
					}
					
				}else if(node ==  node.getParent().getLeft() && aux != null && aux.getColor() == Color.RED) {
					node.setColor(Color.BLACK);
					rotateRight(node.getParent().getParent());
					rotateLeft(node.getParent().getParent());
					
					/*if(node.getParent() != null && ((RBNode<T, E>) node.getParent()).getColor() == Color.RED) {
						node = (RBNode<T, E>) node.getParent();
					}*/
					
				}else if(aux != null && aux.getColor() == Color.RED) {
					(node.getParent()).setColor(Color.BLACK);
					(node.getParent().getParent()).setColor(Color.RED);
					aux.setColor(Color.BLACK);
					node = node.getParent().getParent();
				}
			}
		}
		
		if(rt != null) {
			rt.setColor(Color.BLACK);
		}
		
	}
	
}
