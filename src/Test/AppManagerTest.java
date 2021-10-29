package Test;

import static org.junit.jupiter.api.Assertions.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import dataStructureTrees.BinaryTree;
import model.AppManager;
import model.Player;

public class AppManagerTest {
	private AppManager appManager;

	public void setupScenario1() {
		appManager=new AppManager();	
	}
	public void setupScenario2() throws IOException {
		appManager=new AppManager();
		BufferedReader br = new BufferedReader(new FileReader("Data/DataBase.csv"));
		String line= br.readLine();
		int x=0;
		while(x<5) {
			String[] parts= line.split(";");
			Player currentPlayer= new Player(parts[0], parts[2], Integer.parseInt(parts[1]), Integer.parseInt(parts[3]), Integer.parseInt(parts[4]), Integer.parseInt(parts[5]), Integer.parseInt(parts[6]), Integer.parseInt(parts[7]));
			appManager.getPlayers().add(currentPlayer);
			line=br.readLine();
			x++;
		}
		br.close();
		int i=0;
		String[] values= {"age","points","reBounds","blocks"};
		
		while(i<4) {
			appManager.getBinarySearchTrees().add(new BinaryTree<Integer, Player>());
			for(int c=0;c<5;c++) {
				appManager.getBinarySearchTrees().get(i).insert(Integer.parseInt(appManager.getPlayers().get(c).get(values[i])), appManager.getPlayers().get(c));
			}
			i++;
		}

	}

	@Test
	public void testImport() {
		setupScenario1();
		try {
			appManager.importPlayerDataBase();
			assertEquals(200000, appManager.getPlayers().size());
		} catch (IOException e) {
			fail("Error en el br");
		}
	}
	
	@Test
	public void testTreeCreation() {
		setupScenario1();
		try {
			appManager.importPlayerDataBase();
		} catch (IOException e) {
			fail("Error en el br");
		}
		appManager.creatBinarySearchTree();
		assertEquals(4,appManager.getBinarySearchTrees().size() );
	}
	 
	@Test
	public void testLinearSearchForFirstPlayerWithValue() {
		try {
			setupScenario2();
		} catch (IOException e) {
			fail("Error al importar datos del csv");
		}
		assertEquals(appManager.getPlayers().get(2),appManager.linearSearchForFirstPlayerWithValue("name", "Tori"));
	}

	@Test
	public void testlinearSearchWithRange1() {
		try {
			setupScenario2();
		} catch (IOException e) {
			fail("Error al importar datos del csv");
		}
		assertEquals(5,appManager.linearSearchWithRange(20, 39, "age").size());
	}

	@Test
	public void testlinearSearchWithRange2() {
		try {
			setupScenario2();
		} catch (IOException e) {
			fail("Error al importar datos del csv");
		}
		assertEquals(2,appManager.linearSearchWithRange(30, 39, "age").size());
	}

	@Test
	public void testlinearSearch() {
		try {
			setupScenario2();
		} catch (IOException e) {
			fail("Error al importar datos del csv");
		}
		assertEquals(3,appManager.linearSearch( "age", "29").size());
	}

	@Test
	public void testSearchWithTree() {
		try {
			setupScenario2();
		} catch (IOException e) {
			fail("Error al importar datos del csv");
		}
		assertEquals(3,appManager.searchWithTree( "age", "29").size());
	}

	@Test
	public void testSearchWithTree1() {
		try {
			setupScenario2();
		} catch (IOException e) {
			fail("Error al importar datos del csv");
		}
		assertEquals(appManager.getPlayers().get(1),appManager.searchWithTree( "points", "33").get(0));
	}
	
	@Test
	public void testSearchWithTree2() {
		try {
			setupScenario2();
		} catch (IOException e) {
			fail("Error al importar datos del csv");
		}
		assertEquals(2,appManager.searchWithTree( "reBounds", "2").size());
	}
	
	@Test
	public void testSearchWithTree3() {
		try {
			setupScenario2();
		} catch (IOException e) {
			fail("Error al importar datos del csv");
		}
		assertTrue(appManager.searchWithTree( "blocks", "2").isEmpty());
	}
}
