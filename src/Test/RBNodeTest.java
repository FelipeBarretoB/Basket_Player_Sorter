package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import dataStructureTrees.Color;
import dataStructureTrees.RBNode;
import model.Player;

class RBNodeTest {
	RBNode<Integer,Player> node;
	
	public void setupScenario() {
		node=new RBNode<Integer,Player>(27,null);
		node.setColor(Color.BLACK);
	}
	
	@Test
	public void testColor1() {
		setupScenario();
		assertEquals(node.getColor(),Color.BLACK);
	}
	
	@Test
	public void testColor2() {
		setupScenario();
		assertNotEquals(node.getColor(),Color.RED);
	}

}
