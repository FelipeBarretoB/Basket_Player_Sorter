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
	
	public void setupScenario2() {
		binaryTree=new BinaryTree<Integer, Player>();
		binaryTree.setRoot(new Node<Integer,Player>(30,null));
		binaryTree.insert(20, null);
		binaryTree.insert(40, null);
	}
	
	public void setupScenario3() {
		binaryTree=new BinaryTree<Integer, Player>();
		binaryTree.setRoot(new Node<Integer,Player>(30,null));
		binaryTree.insert(20, null);
		binaryTree.insert(40, null);
		binaryTree.insert(10, null);
		binaryTree.insert(5, null);
	}
	
	@Test
	public void testInsert() {
		setupScenario();
		binaryTree.insert(40, null);
		assertNotEquals(binaryTree.getRoot().getRight(),null);
	}
	
	@Test
	public void testInsert1() {
		setupScenario();
		binaryTree.insert(20, null);
		assertNotEquals(binaryTree.getRoot().getLeft(),null);
	}
	
	@Test
	public void testDelete() {
		setupScenario2();
		binaryTree.delete(20, null);
		assertEquals(binaryTree.getRoot().getLeft(),null);
	}
	
	@Test
	public void testSearch() {
		setupScenario2();
		assertTrue(binaryTree.search(20, null).getPlayer()==null && binaryTree.search(20, null).getVal() == 20);
	}
	
	
	@Test
	public void testTreeMinimum() {
		setupScenario3();
		assertEquals(binaryTree.treeMinimum(binaryTree.getRoot()).getVal(),5);
	}
	
	@Test
	public void testTreeSucesor() {
		setupScenario3();
		assertEquals(binaryTree.treeMinimum(binaryTree.getRoot()).getVal(),5);
	}
}
