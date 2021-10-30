package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import dataStructureTrees.BinaryTree;
import dataStructureTrees.Node;
import model.Player;

public class BinaryTreeTest {
	
	private BinaryTree<Integer, Player> binaryTree;
	
	public void setupScenario() {
		binaryTree=new BinaryTree<Integer, Player>();
		binaryTree.setRoot(new Node<Integer,Player>(30,null));
	}
	
	@Test
	public void testInsert() {
		setupScenario();
		binaryTree.insert(40, null);
		assertTrue(binaryTree.getRoot().getRight()!=null);
	}
	
	@Test
	public void testInsert1() {
		setupScenario();
		binaryTree.insert(20, null);
		assertTrue(binaryTree.getRoot().getLeft()!=null);
	}

}
