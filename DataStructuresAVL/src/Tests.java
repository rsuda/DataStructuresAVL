import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class Tests 
{
	@Test
	public void InsertNode() 
	{
		//Correct level order for 1,2,3
		ArrayList<Integer> test = new ArrayList<Integer>(); 
		test.add(2);
		
		//Inserting node to get left rotation.
		AVLTree target = new AVLTree();
		target.insert(2);
		assertEquals(true, test.equals(target.levelOrderTraversal()));
	}
	
	@Test
	public void LeftRightRotation() 
	{
		//Correct level order for 1,2,3
		ArrayList<Integer> test = new ArrayList<Integer>(); 
		test.add(2);
		test.add(1);
		test.add(3);
		
		//Inserting node to get left rotation.
		AVLTree target = new AVLTree();
		target.insert(3);
		target.insert(1);
		target.insert(2);
		assertEquals(true, test.equals(target.levelOrderTraversal()));
	}
	
	@Test
	public void RightLeftRotation() 
	{
		//Correct level order for 1,2,3
		ArrayList<Integer> test = new ArrayList<Integer>(); 
		test.add(2);
		test.add(1);
		test.add(3);
		
		//Inserting node to get left rotation.
		AVLTree target = new AVLTree();
		target.insert(1);
		target.insert(3);
		target.insert(2);
		assertEquals(true, test.equals(target.levelOrderTraversal()));
	}
	
	@Test
	public void LeftRotation() 
	{
		//Correct level order for 1,2,3
		ArrayList<Integer> test = new ArrayList<Integer>(); 
		test.add(2);
		test.add(1);
		test.add(3);
		
		//Inserting node to get left rotation.
		AVLTree target = new AVLTree();
		target.insert(1);
		target.insert(2);
		target.insert(3);
		assertEquals(true, test.equals(target.levelOrderTraversal()));
	}
	
	@Test
	public void RightRotation() 
	{
		//Correct level order for 1,2,3
		ArrayList<Integer> test = new ArrayList<Integer>(); 
		test.add(2);
		test.add(1);
		test.add(3);
		
		//Inserting node to get left rotation.
		AVLTree target = new AVLTree();
		target.insert(3);
		target.insert(2);
		target.insert(1);
		assertEquals(true, test.equals(target.levelOrderTraversal()));
	}

	@Test
	public void testLevelOrder() 
	{
		//Correct level order for 25,23,28,22,24,26,29
		ArrayList<Integer> test = new ArrayList<Integer>(); 
		test.add(25);
		test.add(23);
		test.add(28);
		test.add(22);
		test.add(24);
		test.add(26);
		test.add(29);
		
		//Inserting node in different order.
		AVLTree target = new AVLTree();
		target.insert(28);
		target.insert(23);
		target.insert(22);
		target.insert(29);
		target.insert(25);
		target.insert(24);
		target.insert(26);
		assertEquals(true, test.equals(target.levelOrderTraversal()));
	}
	
	@Test
	public void testRemoveRoot() 
	{
		//Correct level order for 25,23,28,22,24,26,29
		ArrayList<Integer> test = new ArrayList<Integer>(); 
		test.add(24);
		test.add(23);
		test.add(28);
		test.add(22);
		test.add(26);
		test.add(29);
		
		//Inserting node in different order.
		AVLTree target = new AVLTree();
		target.insert(28);
		target.insert(23);
		target.insert(22);
		target.insert(29);
		target.insert(25);
		target.insert(24);
		target.insert(26);
		
		//Removing root.
		target.remove(25);
		assertEquals(true, test.equals(target.levelOrderTraversal()));
	}
	
	@Test
	public void testRemoveLeaf() 
	{
		//Correct level order for 25,23,28,22,24,26,29
		ArrayList<Integer> test = new ArrayList<Integer>(); 
		test.add(25);
		test.add(23);
		test.add(28);
		test.add(22);
		test.add(24);
		test.add(26);
		
		//Inserting node in different order.
		AVLTree target = new AVLTree();
		target.insert(28);
		target.insert(23);
		target.insert(22);
		target.insert(29);
		target.insert(25);
		target.insert(24);
		target.insert(26);
		
		//Removing root.
		target.remove(29);
		assertEquals(true, test.equals(target.levelOrderTraversal()));
	}
	
	@Test
	public void testRemoveParent() 
	{
		//Correct level order for 25,23,28,22,24,26,29
		ArrayList<Integer> test = new ArrayList<Integer>(); 
		test.add(25);
		test.add(23);		
		test.add(26);
		test.add(22);
		test.add(24);
		test.add(29);
		
		//Inserting node in different order.
		AVLTree target = new AVLTree();
		target.insert(28);
		target.insert(23);
		target.insert(22);
		target.insert(29);
		target.insert(25);
		target.insert(24);
		target.insert(26);
		
		//Removing root.
		target.remove(28);
		assertEquals(true, test.equals(target.levelOrderTraversal()));
	}

}
