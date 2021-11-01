package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import dataStructureTrees.Color;
import dataStructureTrees.RBNode;
import dataStructureTrees.RedBlackTree;
import model.Player;

class RedBlackTreeTest {

	private RedBlackTree<Integer,Player> rbTree;
	
	public void setupScenario1() {
		rbTree = new RedBlackTree<Integer,Player>();
		
		rbTree.insert(47, null);
	}
	
	public void setupScenario2() {
		rbTree = new RedBlackTree<Integer,Player>();
		
		rbTree.insert(47, null);
		rbTree.insert(60, null);
		rbTree.insert(22, null);
		rbTree.insert(12, null);
		//System.out.println(rbTree.getRoot().getColor());
	}
	
	public void setupScenario3() {
		rbTree = new RedBlackTree<Integer,Player>();
		
		rbTree.insert(47, null);
		rbTree.insert(60, null);
		rbTree.insert(22, null);
		rbTree.insert(12, null);
		rbTree.insert(6, null);
		rbTree.insert(13, null);
		rbTree.insert(41, null);
	}
	
	@Test
	public void testInsert1() {
		setupScenario1();
		rbTree.insert(40, null);
		assertTrue(rbTree.getRoot().getColor()== Color.BLACK);
	}
	
	@Test
	public void testInsert2() {
		setupScenario2();
		rbTree.insert(6, null);
		assertTrue((rbTree.getRoot().getLeft().getLeft()).getColor() == Color.RED);
	}
	
	@Test
	public void testInsert3() {
		setupScenario3();
		rbTree.insert(20, null);
		System.out.println(rbTree.getRoot().getLeft().getRight().getRight());
		System.out.println(rbTree.searchRB(20, null).getParent().getParent().getVal());
		assertTrue((rbTree.getRoot().getLeft().getRight().getRight()).getColor() == Color.RED);
	}
	
	@Test
	public void testDelete() {
		setupScenario2();
		//rbTree.delete(22, null);
		rbTree.rbDelete(rbTree.searchRB(22, null));
		assertNotEquals(rbTree.getRoot().getLeft().getVal(),22);
	}

}
