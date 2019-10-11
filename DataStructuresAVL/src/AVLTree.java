import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * AVL Tree that can insert nodes, remove nodes, and print out
 * the level order.
 * @author robin
 *
 */
public class AVLTree 
{
 
	/**
	 * The root node where traversal begins.
	 */
    private BinaryNode root;
 
    /**
     * Nodes in the tree that are distinguished by there key.
     * They also have a balance and height attribute.
     * In they AVL tree 
     * @author robin
     *
     */
    private static class BinaryNode 
    {
        private int balance;
        private int height;
        private int key;
        
        private BinaryNode parent;
        private BinaryNode left;
        private BinaryNode right;
 
        BinaryNode(int key, BinaryNode parent) 
        {
            this.key = key;
            this.parent = parent;
        }
    }
 
    /**
     * Insert a new node based on key.
     * @param key
     * @return
     */
    public boolean insert(int key) 
    {
        if (root == null) 
        {
            root = new BinaryNode(key, null);
            return true;
        }
 
        BinaryNode n = root;
        while (true) 
        {
            if (n.key == key)
            {
            	return false;
            }
 
            BinaryNode parent = n;
 
            //Determines whether to go left or right.
            boolean goLeft = n.key > key;
            n = goLeft ? n.left : n.right;
 
            if (n == null) 
            {
                if (goLeft) 
                {
                    parent.left = new BinaryNode(key, parent);
                } else 
                {
                    parent.right = new BinaryNode(key, parent);
                }
                balance(parent);
                break;
            }
        }
        return true;
    }
 
    /**
     * Removes the actual node
     * @param node
     */
    private void removeNode(BinaryNode node) 
    {
        if (node.left == null && node.right == null) 
        {
            if (node.parent == null) 
            {
                root = null;
            } else 
            {
            	BinaryNode parent = node.parent;
                if (parent.left == node) 
                {
                    parent.left = null;
                } else 
                {
                    parent.right = null;
                }
                balance(parent);
            }
            return;
        }
 
        if (node.left != null) 
        {
        	BinaryNode child = node.left;
            while (child.right != null)
            {
            	child = child.right;
            }
            node.key = child.key;
            removeNode(child);
        } 
        else 
        {
        	BinaryNode child = node.right;
            while (child.left != null) 
            	{
            		child = child.left;
            	}
            node.key = child.key;
            removeNode(child);
        }
    }
 
    /**
     * Removes the node depending on the key.
     * @param delKey The node to remove
     */
    public void remove(int removeKey) 
    {
        if (root == null)
        {
        	return;
        }
 
        BinaryNode child = root;
        while (child != null) 
        {
        	BinaryNode node = child;
            child = removeKey >= node.key ? node.right : node.left;
            if (removeKey == node.key) 
            {
                removeNode(node);
                return;
            }
        }
    }
 
    /**
     * Balances AVL Tree after a node has been inserted or removed.
     * @param node
     */
    private void balance(BinaryNode node) 
    {
        setBalance(node);
 
        if (node.balance == -2) 
        {
            if (height(node.left.left) >= height(node.left.right))
            {
            	node = rotateRight(node);
            }
            else
            {
            	node = rotateLeftThenRight(node);
            }
 
        } 
        else if (node.balance == 2) 
        {
            if (height(node.right.right) >= height(node.right.left))
            {
            	node = rotateLeft(node);
            }
            else
            {
            	node = rotateRightThenLeft(node);
            }
        }
 
        if (node.parent != null) 
        {
            balance(node.parent);
        } else 
        {
            root = node;
        }
    }
 
    /**
     * Rotates the nodes to the left, single rotation.
     * @param k1
     * @return the new node.
     */
    private BinaryNode rotateLeft(BinaryNode k1) 
    {
    	BinaryNode k2 = k1.right;
    	
        k2.parent = k1.parent;
        k1.right = k2.left;
 
        if (k1.right != null)
        {
        	k1.right.parent = k1;
        }
 
        k2.left = k1;
        k1.parent = k2;
 
        if (k2.parent != null) 
        {
            if (k2.parent.right == k1) 
            {
                k2.parent.right = k2;
            } 
            else 
            {
                k2.parent.left = k2;
            }
        }
 
        setBalance(k1, k2);
        return k2;
    }
 
    /**
     * Rotates the nodes to the right, single rotation.
     * @param k1
     * @return the new node
     */
    private BinaryNode rotateRight(BinaryNode k1) {
 
    	BinaryNode k2 = k1.left;
    	
        k2.parent = k1.parent;
        k1.left = k2.right;
 
        if (k1.left != null)
        {
        	k1.left.parent = k1;
        }
 
        k2.right = k1;
        k1.parent = k2;
 
        if (k2.parent != null) 
        {
            if (k2.parent.right == k1) 
            {
                k2.parent.right = k2;
            } 
            else 
            {
                k2.parent.left = k2;
            }
        }
 
        setBalance(k1, k2);
        return k2;
    }
 
    /**
     * Rotates the node Left then Right, double rotation.
     * @param node
     * @return the new node.
     */
    private BinaryNode rotateLeftThenRight(BinaryNode node) 
    {
        node.left = rotateLeft(node.left);
        return rotateRight(node);
    }
 
    /**
     * Rotates the node Right then left, double rotation.
     * @param node 
     * @return The new node.
     */
    private BinaryNode rotateRightThenLeft(BinaryNode node) 
    {
        node.right = rotateRight(node.right);
        return rotateLeft(node);
    }
 
    /**
     * Returns the height of the node.
     * @param node The node to get the height
     * @return height of the node.
     */
    private int height(BinaryNode node) 
    {
        if (node == null)
        {
        	return -1;
        }
        return node.height;
    }
 
    /**
     * Done every time a node is inserted or removed.
     * @param nodes
     */
    private void setBalance(BinaryNode... nodes) 
    {
    	//For loop going through all nodes reheighting and balancing them.
        for (BinaryNode n : nodes) 
        {
            reheight(n);
            n.balance = height(n.right) - height(n.left);
        }
    }

    /**
     * Will set a new height to the node after insertion or removal.
     * @param node
     */
    private void reheight(BinaryNode node) 
    {
        if (node != null) {
            node.height = 1 + Math.max(height(node.left), height(node.right));
        }
    }
    
    /**
	 * Prints out the level order of the tree.
	 * For example if the nodes for this order:
	 * 
	 * 		4
	 * 	2		6
	 * 1 3	   5 7
	 * 
	 * The output would be: 4 2 6 1 3 5 7
	 */
    public ArrayList<Integer> levelOrderTraversal()
	{
		//Queue is used to go in order according to levelOrder rules.
		Queue<BinaryNode> queue = new LinkedList<BinaryNode>();
		ArrayList<Integer> array = new ArrayList<Integer>();
		queue.add(root);
		while (!queue.isEmpty())
		{
			BinaryNode tempNode = queue.poll();
			array.add(tempNode.key);
			if (tempNode.left != null)
			{
				queue.add(tempNode.left);
			}
			if (tempNode.right != null)
			{
				queue.add(tempNode.right);
			}
		}
		return array;
	}
    
	/**
	 * The main program that takes the users input
	 * to create a tree. It can also find the amount 
	 * of leaves, half nodes, and full nodes in a tree.
	 * 
	 * @param arg
	 */
	public static void main(String arg[])
	{
		Scanner in = new Scanner(System.in);
		int input = 7;
		AVLTree tree = new AVLTree();
		//Repeats until 7 is chosen.
		do
		{
			//Repeats until valid input is chosen.
			do
			{
				int choice = 0;
				System.out.print(">> Enter choice [1-4] from menu below:");
				System.out.println("");
				System.out.println("\t1) Insert node");
				System.out.println("\t2) Remove");
				System.out.println("\t3) Level Order");
				System.out.println("\t4) Exit Program");
				System.out.println("Enter choice: ");
				//Makes sure user chooses a valid number.
				try
				{
					choice = in.nextInt();
				}
				catch (InputMismatchException e)
				{
					choice = 10;
					in.nextLine();
				}
				input = choice;
			}while (input > 7 || input < 1);
			if (input == 1)
			{
				ArrayList<Integer> arraylist = new ArrayList<Integer>();
				System.out.println("Enter a node value: ");
				String numbers = in.next();
				System.out.println(numbers);
				Scanner record = new Scanner(numbers);
		        record.useDelimiter(",");
		        while (record.hasNextInt()) 
		        {
		        	arraylist.add(record.nextInt());
		        }
		        record.close();
		        for (int i = 0; i < arraylist.size(); i++)
		        {
		        	tree.insert(arraylist.get(i));
		        }
		        arraylist.clear();
		        }
			//}
			
			//Prints out the tree in order.
			else if (input == 2)
			{
				int value = 0;
				boolean flag = true;
				//Makes sure user chooses a number for the node.
				do
				{
					flag = true;
					try
					{
						System.out.println("Enter a node value: ");
						value = in.nextInt();
					}
					catch (InputMismatchException e)
					{
						flag = false;
						in.nextLine();
					}
				}while (flag == false);
				tree.remove(value);
			}
			
			//Prints the amount of leaves in the tree.
			else if (input == 3)
			{
				ArrayList<Integer> level = new ArrayList<Integer>();
				level = tree.levelOrderTraversal();
				for (int i = 0; i < level.size(); i++)
				{
					System.out.print(level.get(i) + " ");
				}
				System.out.print("\n");
			}	
		}while (input != 4);
		//closes the scanner for input.
		in.close();
	}

}