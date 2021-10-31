package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import dataStructureTrees.Node;
import model.Player;

public class NodeTest {
	private Node<Integer,Player> node;
	
	public void setupScenario() {
		node=new Node<>(27,null);
		node.setLeft(new Node<Integer,Player>(10,null));
		node.setRight(new Node<Integer,Player>(30,null));
	}
	
	@Test
	public void testCompareTo() {
		setupScenario();
		assertEquals(1, node.compareTo(node.getLeft().getVal()));
	}
	@Test
	public void testCompareTo1() {
		setupScenario();
		assertEquals(-1, node.compareTo(node.getRight().getVal()));
	}
	@Test
	public void testCompareTo2() {
		setupScenario();
		assertEquals(0, node.compareTo(node.getVal()));
	}

}
