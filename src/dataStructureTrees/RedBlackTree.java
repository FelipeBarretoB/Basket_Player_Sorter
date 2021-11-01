package dataStructureTrees;

import java.util.ArrayList;

public class RedBlackTree<T,E> extends BinaryTree<T,E> {
	private RBNode<T,E> root;
	
	private int blackHeight;
	
	public RedBlackTree() {
		
	}

	public RBNode<T,E> getRoot() {
		return root;
	}

	public void setRoot(RBNode<T,E> root) {
		this.root = root;
	}
	
	public int getBlackHeight() {
		return blackHeight;
	}
	
	public RBNode<T, E> rotateLeft(RBNode<T, E> node){
		RBNode<T, E> aux = node.getRight();
		node.setRight(aux.getLeft());
		aux.setLeft(node);
		aux.setColor(node.getColor());
		node.setColor(Color.RED);
		return aux;
	}
	
	public RBNode<T,E> rotateRight(RBNode<T,E> node){
		RBNode<T,E> aux = node.getLeft();
		node.setLeft(aux.getRight());
		aux.setRight(node);
		aux.setColor(node.getColor());
		node.setColor(Color.RED);
		return aux;
	}
	
	public void leftRotate(RBNode<T,E> node) {
		RBNode<T,E> y = node.getRight();
		node.setRight(y.getLeft());
		if (y.getLeft() != null) {
			y.getLeft().setParent(node);
		}
		y.setParent(node.getParent());
		if (node.getParent() == null) {
			root = y;
		} else if (node == node.getParent().getLeft()){
			node.getParent().setLeft(y);
		} else {
			node.getParent().setRight(y);
		}
		y.setLeft(node);
		node.setParent(y);
		
	}
	
	public void rightRotate(RBNode<T,E> node) {
		RBNode<T,E> y = node.getLeft();
		node.setLeft(y.getRight());
		if (y.getRight() != null) {
			y.getRight().setParent(node);
		}
		y.setParent(node.getParent());
		if (node.getParent() == null) {
			root = y;
		} else if (node == node.getParent().getRight()){
			node.getParent().setRight(y);
		} else {
			node.getParent().setLeft(y);
		}
		y.setRight(node);
		node.setParent(y);
		
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
	
	public void rbTransplant(RBNode<T,E> u, RBNode<T,E> v) {
		if (u.getParent() == null) {
			root = v;
		} else if (u == u.getParent().getLeft()) {
			u.getParent().setLeft(v);
		} else {
			u.getParent().setRight(v);
		}
		if (u != root) {
			v.setParent(u.getParent());
		}
	}
	
	public void rbDelete(RBNode<T,E> node) {
		RBNode<T,E> y = node;
		RBNode<T,E> x = null;
		Color originalColor = y.getColor();
		if (node.getLeft() == null) {
			x = node.getRight();
			rbTransplant(node, node.getLeft());
		} else if(node.getRight() == null) {
			x = node.getLeft();
			rbTransplant(node, node.getLeft());
		} else {
			y = (RBNode<T, E>) treeMinimum(node.getRight());
			originalColor = y.getColor();
			x = y.getRight();
			if (y.getParent() == node) {
				x.setParent(y);
			} else {
				rbTransplant(y, y.getRight());
				y.setRight(node.getRight());
				y.getRight().setParent(y);
			}
			rbTransplant(node,y);
			y.setLeft(node.getLeft());
			y.getLeft().setParent(y);
			y.setColor(node.getColor());
		}
		if (originalColor == Color.BLACK) {
			rbDeleteFixup(x);
		}
	}

	public void rbDeleteFixup(RBNode<T,E> x) {
		RBNode<T,E> w = null;
		while (x != root && x.getColor() == Color.BLACK) {
			if (x == x.getParent().getLeft()) {
				w = x.getParent().getRight();
				if (w.getColor() == Color.RED) {
					w.setColor(Color.BLACK);
					x.getParent().setColor(Color.RED);
					leftRotate(x.getParent());
					w = x.getParent().getRight();
				}
				if (w.getLeft().getColor() == Color.BLACK && w.getRight().getColor() == Color.BLACK) {
					w.setColor(Color.RED);
					x = x.getParent();
				} else if (w.getRight().getColor() == Color.BLACK) {
					w.getLeft().setColor(Color.BLACK);
					w.setColor(Color.RED);
					rightRotate(w);
					w = x.getParent().getRight();
				}
				w.setColor(x.getParent().getColor());
				x.getParent().setColor(Color.BLACK);
				w.getRight().setColor(Color.BLACK);
				leftRotate(x.getParent());
				x = root;
			} else {
				w = x.getParent().getLeft();
				if (w.getColor() == Color.RED) {
					w.setColor(Color.BLACK);
					x.getParent().setColor(Color.RED);
					rightRotate(x.getParent());
					w = x.getParent().getLeft();
				}
				if (w.getRight().getColor() == Color.BLACK && w.getLeft().getColor() == Color.BLACK) {
					w.setColor(Color.RED);
					x = x.getParent();
				} else if (w.getLeft().getColor() == Color.BLACK) {
					w.getRight().setColor(Color.BLACK);
					w.setColor(Color.RED);
					leftRotate(w);
					w = x.getParent().getLeft();
				}
				w.setColor(x.getParent().getColor());
				x.getParent().setColor(Color.BLACK);
				w.getLeft().setColor(Color.BLACK);
				rightRotate(x.getParent());
				x = root;
			}
			x.setColor(Color.BLACK);
		}
	}
	
	public RBNode<T,E> searchRB(T el, E player) {
		RBNode<T,E> node = new RBNode<T,E>(el, player);
		RBNode<T,E> current = root;
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
		}
		return current;
	}
	
	public ArrayList<RBNode<T, E>> getRBSameValueNodes(T el, E player){
		RBNode<T,E> node = new RBNode<>(el,player);
		RBNode<T,E> current = root;
		ArrayList<RBNode<T,E>> nodes = new ArrayList<RBNode<T,E>>();
		while (current != null ) {
			// If it's the number
			if (node.compareTo(current.getVal()) == 0) {
				nodes.add(current);
				current = current.getRight();
				// If is greater
			} else if (node.compareTo(current.getVal()) > 0) {
				current = current.getRight();
				// If is smaller
			} else {
				current = current.getLeft();
			}
		}
		
		return nodes;
	}
	
	public void calculateBlackHeight() {
		RBNode<T,E> x = root;
		int result = 0;
		while (x != null) {
			x = x.getRight();
			if (x != null && x.getColor() == Color.BLACK) {				
				result++;
			} else if (x == null) {
				result++;
			}
		}
		blackHeight = result;
	}

	public void insert(T el, E player) {
		RBNode<T,E> node = new RBNode<>(el, player);
		RBNode<T,E> temp = null;
		RBNode<T,E> current = getRoot();
		while (current != null) {
			temp = current;
			if (node.compareTo(current.getVal()) < 0) {
				current = current.getLeft();
			} else {
				current = current.getRight();
			}
		}
		node.setParent(temp);
		
		if (temp == null) {
			root = node;
			root.setColor(Color.BLACK);
		} else if (node.compareTo(temp.getVal()) < 0) {
			temp.setLeft(node);
			
		} else {
			temp.setRight(node);
			
		}
		calculateBlackHeight();
		boolean check = checkProperties(node);
		if (!check) {
			insertFixup(node);			
		}
		
	}
	
	public boolean checkProperties(RBNode<T,E> node) {
		boolean pass = false;
		// Check Property 2
		// Check Property 4
		// Check Property 5
		if (propertyTwo() && propertyFour(root) && propertyFive()) {
			pass = true;
		}
		return pass;
	}
	
	public boolean propertyTwo() {
		boolean pass = true;
		if (root.getColor() != Color.BLACK) {
			pass = false;
		}
		return pass;
	}
	
	public boolean propertyFour(RBNode<T,E> node) {
		boolean pass = true;
		pass = inOrder(node);
		return pass;
	}
	
	public boolean inOrder(RBNode<T,E> node) {
		if (node == null) {
			return true;
		}
		if (node != null && node.getColor() == Color.RED && ((node.getLeft() != null && node.getLeft().getColor() == Color.RED) || (node.getRight() != null && node.getRight().getColor() == Color.RED))) {
			return false;
		} else if (node != null && node.getColor() == Color.RED && (node.getLeft() != null && node.getLeft().getColor() == Color.BLACK && node.getRight() != null && node.getRight().getColor() == Color.BLACK))  {
			return true;
		} else {
			boolean partOne = inOrder(node.getLeft());
			boolean partTwo = inOrder(node.getRight());
			return (partOne && partTwo);
		}
	}
	
	public boolean propertyFive() {
		boolean pass = true;
		if (root.getColor() != Color.BLACK) {
			pass = false;
		}
		return pass;
	}
	
	public void insertFixup(RBNode<T,E> node) {
		RBNode<T,E> y = null;
			while (node.getParent() != null && node.getParent().getColor() == Color.RED) {
				if (node.getParent() == node.getParent().getParent().getLeft()) {
					y = node.getParent().getParent().getRight();
					if (y!= null && y.getColor() == Color.RED) {
						node.getParent().setColor(Color.BLACK);
						y.setColor(Color.BLACK);
						node.getParent().getParent().setColor(Color.RED);
						node = node.getParent().getParent();
					} else if (node == node.getParent().getRight()) {
						node = node.getParent();
						leftRotate(node);
					}
					if (node == root) {
						node.setColor(Color.BLACK);
					} else {						
						node.getParent().setColor(Color.BLACK);
						if (node.getParent() != root) {							
							node.getParent().getParent().setColor(Color.RED);
							rightRotate(node.getParent().getParent());
						}
					}
				} else {
					y = node.getParent().getParent().getLeft();
					if ( y!=null && y.getColor() == Color.RED) {
						node.getParent().setColor(Color.BLACK);
						y.setColor(Color.BLACK);
						node.getParent().getParent().setColor(Color.RED);
						//node = node.getParent().getParent();
					} else if (node == node.getParent().getLeft()) {
						node = node.getParent();
						rightRotate(node);
					}
					if (node == root) {
						node.setColor(Color.BLACK);
					} else {						
						node.getParent().setColor(Color.BLACK);
						node.getParent().getParent().setColor(Color.RED);
						leftRotate(node.getParent().getParent());
					}
				}
				root.setColor(Color.BLACK);
		}
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
