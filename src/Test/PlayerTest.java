package Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.Player;

public class PlayerTest {
	private Player player;
	
	public void setupScenario1() {
		player=new Player("Steve", "Cadia", 20, 30, 80, 50, 95, 40);	
	}
	
	@Test
	public void testGet() {
		setupScenario1();
		assertEquals(player.get("name"),player.getName());
		assertEquals(player.get("age"),player.getAge()+"");
		assertEquals(player.get("team"),player.getTeam());
		assertEquals(player.get("points"),player.getPoints()+"");
		assertEquals(player.get("reBounds"),player.getReBounds()+"");
		assertEquals(player.get("blocks"),player.getBlocks()+"");
		assertEquals(player.get("steals"),player.getSteals()+"");
	}

}
