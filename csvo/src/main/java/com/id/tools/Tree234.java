/**
 * <br>项目名: csvo
 * <br>文件名: Tree234.java
 * <br>Copyright 2016
 */
package com.id.tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <br>
 * 类 名: Tree234 <br>
 * 描 述: 2-3-4树 <br>
 * 作 者: yufenghe <br>
 * 创 建： 2016年4月1日 <br>
 * 版 本：v1.0.0 <br>
 * <br>
 * 历 史: (版本) 作者 时间 注释
 */
public class Tree234 {

	private Node root = new Node();

	public int find(long key) {
		Node curNode = root;
		int childNum;
		while (true) {
			if ((childNum = curNode.findItem(key)) != -1) {
				return childNum;
			} else if (curNode.isLeaf()) {
				return -1;
			} else {
				curNode = getNextChild(curNode, key);
			}
		}
	}

	public void insert(long dValue) {
		Node curNode = root;
		DataItem tempItem = new DataItem(dValue);
		
		while(true) {
			if(curNode.isFull()) {
				split(curNode);
				curNode = curNode.parent;
				
				curNode = getNextChild(curNode, dValue);
			}
			else if(curNode.isLeaf()) {
				break;
			}
			else {
				curNode = getNextChild(curNode, dValue);	
			}
		}
		
		curNode.insertItem(tempItem);
	}
	public void split(Node thisNode) {
		DataItem itemB, itemC;
		Node parent, child2, child3;
		int itemIndex;

		itemC = thisNode.removeItem();
		itemB = thisNode.removeItem();
		child2 = thisNode.disConnectChild(2);
		child3 = thisNode.disConnectChild(3);
		Node newRight = new Node();

		if (thisNode == root) {
			root = new Node();
			parent = root;
			root.connectChild(0, thisNode);
		} else {
			parent = thisNode.getParent();
		}

		itemIndex = parent.insertItem(itemB);
		int n = parent.getNumItems();

		for (int j = n - 1; j > itemIndex; j--) {
			Node temp = parent.disConnectChild(j);
			parent.connectChild(j + 1, temp);
		}

		parent.connectChild(itemIndex + 1, newRight);

		newRight.insertItem(itemC);
		newRight.connectChild(0, child2);
		newRight.connectChild(1, child3);

	}

	public Node getNextChild(Node theNode, long theValue) {
		int j;
		int numItems = theNode.getNumItems();
		for (j = 0; j < numItems; j++) {
			if (theValue < theNode.getItem(j).dData) {
				return theNode.getChild(j);
			}
		}

		return theNode.getChild(j);
	}

	public void displayTree() {
		recDisplayTree(root, 0, 0);
	}

	private void recDisplayTree(Node thisNode, int level, int childNum) {
		System.out.println("level=" + level + " child=" + childNum);

		thisNode.displayNode();

		int numItems = thisNode.getNumItems();
		for (int j = 0; j < numItems + 1; j++) {
			Node nextNode = thisNode.getChild(j);
			if (nextNode != null) {
				recDisplayTree(nextNode, level + 1, j);
			} else {
				return;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		long value;
		Tree234 theTree = new Tree234();

		theTree.insert(50);
		theTree.insert(40);
		theTree.insert(60);
		theTree.insert(30);
		theTree.insert(70);

		while (true) {
			System.out.print("Enter first letter of Row,");
			System.out.print("insert, find, delete or tranverse:");
			int choice = getChar();

			switch (choice) {
				case 's' :
					theTree.displayTree();
					break;
				case 'i' :
					System.out.print("Enter value to insert:");
					value = getInt();
					theTree.insert(value);
					break;
				case 'f' :
					System.out.print("Enter value to find:");
					value = getInt();
					int found = theTree.find(value);
					if (found != -1) {
						System.out.println("Found " + value);
					} else {
						System.out.println("Could not found " + value);
					}
					break;
				default :
					System.out.print("Invalid Entry\n");

			}
		}
	}

	public static String getString() throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String s = br.readLine();
		return s;
	}

	public static char getChar() throws IOException {
		String s = getString();
		return s.charAt(0);
	}

	public static int getInt() throws IOException {
		String s = getString();
		return Integer.parseInt(s);
	}

	class DataItem {
		public long dData;

		public DataItem(long dd) {
			this.dData = dd;
		}

		public void displayItem() {
			System.out.print("/" + dData);
		}
	}

	class Node {
		private static final int ORDER = 4;
		private int numItems;
		private Node parent;
		private Node[] childArr = new Node[ORDER];
		private DataItem[] itemArr = new DataItem[ORDER - 1];

		public void connectChild(int childNum, Node child) {
			childArr[childNum] = child;
			if (child != null) {
				child.parent = this;
			}
		}

		public Node disConnectChild(int childNum) {
			Node tempNode = childArr[childNum];
			childArr[childNum] = null;
			return tempNode;
		}

		public Node getChild(int childNum) {
			return childArr[childNum];
		}

		public Node getParent() {
			return this.parent;
		}

		public boolean isLeaf() {
			return childArr[0] == null ? true : false;
		}

		public int getNumItems() {
			return numItems;
		}

		public DataItem getItem(int index) {
			return itemArr[index];
		}

		public boolean isFull() {
			return numItems == ORDER - 1 ? true : false;
		}

		public int findItem(long key) {
			for (int j = 0; j < ORDER - 1; j++) {
				if (itemArr[j] == null) {
					break;
				} else if (itemArr[j].dData == key) {
					return j;
				}
			}

			return -1;
		}

		public int insertItem(DataItem newItem) {
			numItems++;
			long newKey = newItem.dData;
			for (int j = ORDER - 2; j >= 0; j--) {
				if (itemArr[j] == null) {
					continue;
				} else {
					long itKey = itemArr[j].dData;
					if (newKey < itKey) {
						itemArr[j + 1] = itemArr[j];
					} else {
						itemArr[j + 1] = newItem;
						return j + 1;
					}
				}
			}

			itemArr[0] = newItem;
			return 0;
		}

		public DataItem removeItem() {
			DataItem temp = itemArr[numItems - 1];
			itemArr[numItems - 1] = null;
			numItems--;
			return temp;
		}

		public void displayNode() {
			for (int j = 0; j < numItems; j++) {
				itemArr[j].displayItem();
			}

			System.out.print("/");
		}
	}
}
