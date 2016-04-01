/**
 * <br>项目名: csvo
 * <br>文件名: Tree.java
 * <br>Copyright 2016
 */
package com.id.tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/** 
 * <br>类 名: Tree 
 * <br>描 述: 二叉树的操作类
 * <br>作 者: yufenghe 
 * <br>创 建： 2016年3月31日 
 * <br>版 本：v1.0.0 
 * <br>
 * <br>历 史: (版本) 作者 时间 注释
 */
public class Tree {
	private Node root;
	
	public Tree() {
		root = null;
	}
	
	public Node find(int key) {
		Node current = root;
		
		while(current.iData != key) {
			if(key < current.iData) {
				current = current.leftChild;
			}
			else {
				current = current.rightChild;
			}
			
			if(current == null) {
				return null;
			}
		}
		
		return current;
	}
	
	public void insert(int id, double dd) {
		Node newNode = new Node();
		newNode.iData = id;
		newNode.dData = dd;
		if(root == null) {
			root = newNode;
		}
		else {
			Node current = root;
			Node parent = null;
			while(true) {
				parent = current;
				if(id < current.iData) {
					current = current.leftChild;
					if(current == null) {
						parent.leftChild = newNode;
						return;
					}
				}
				else {
					current = current.rightChild;
					if(current == null) {
						parent.rightChild = newNode;
						return;
					}
				}
			}
		}
	}
	
	public boolean delete(int key) {
		Node current = root;
		Node parent = root;
		boolean isLeftChild = true;
		
		while(current.iData != key) {
			parent = current;
			if(current.iData > key) {
				isLeftChild = true;
				current = current.leftChild;
			}
			else {
				isLeftChild = false;
				current = current.rightChild;
			}
			
			if(current == null) {
				return false;
			}
		}
			
		if(current.leftChild == null && current.rightChild == null) {
			if(current == root) {
				root = null;
			}
			else if(isLeftChild) {
				parent.leftChild = null;
			}
			else {
				parent.rightChild = null;
			}
		}
		else if(current.rightChild == null) {
			if(current == root) {
				root = current.leftChild;
			}
			else if(isLeftChild) {
				parent.leftChild = current.leftChild;
			}
			else {
				parent.rightChild = current.leftChild;
			}
		}
		else if(current.leftChild == null) {
			if(current == root) {
				root = current.rightChild;
			}
			else if(isLeftChild) {
				parent.leftChild = current.rightChild;
			}
			else {
				parent.rightChild = current.rightChild;
			}
		}
		else {
			Node successor = getSuccessor(current);
			if(current == root) {
				root = successor;
			}
			else if(isLeftChild) {
				parent.leftChild = successor;
			}
			else {
				parent.rightChild = successor;
			}
			
			successor.leftChild = current.leftChild;
		}
		
		return true;
	}
	
	private Node getSuccessor(Node delNode) {
		Node successParent = delNode;
		Node successor = delNode;
		Node current = delNode.rightChild;
		while(current != null) {
			successParent = current;
			successor = current;
			current = current.leftChild;
		}
		
		if(successor != delNode.rightChild) {
			successParent.leftChild = successor.rightChild;
			successor.rightChild = delNode.rightChild;
		}
		
		return successor;
	}
	
	public void traverse(int tranverseType) {
		switch(tranverseType) {
			case 1:
				System.out.println("Preorder tranversal:");
				preOrder(root);
				break;
			case 2:
				System.out.println("Preorder tranversal:");
				inOrder(root);
				break;
			case 3:
				System.out.println("Preorder tranversal:");
				postOrder(root);
				break;
		}
		
		System.out.println();
	}
	
	
	private void preOrder(Node localRoot) {
		if(localRoot != null) {
			System.out.print(localRoot.iData + " ");
			preOrder(localRoot.leftChild);
			preOrder(localRoot.rightChild);
		}
	}
	private void inOrder(Node localRoot) {
		if(localRoot != null) {
			inOrder(localRoot.leftChild);
			System.out.print(localRoot.iData + " ");
			inOrder(localRoot.rightChild);
		}
	}
	private void postOrder(Node localRoot) {
		if(localRoot != null) {
			postOrder(localRoot.leftChild);
			postOrder(localRoot.rightChild);
			System.out.print(localRoot.iData + " ");
		}
	}
	
	public void displayTree() {
		Stack globalStack = new Stack();
		
		globalStack.push(root);
		int nBlanks = 32;
		boolean isRowEmpty = false;
		System.out.println("................................");
		while(isRowEmpty == false) {
			Stack localStack = new Stack();
			isRowEmpty = true;
			
			for(int j=0; j<nBlanks; j++) {
				System.out.print(" ");
			}
			
			while(globalStack.isEmpty() == false) {
				Node temp = (Node)globalStack.pop();
				if(temp != null) {
					System.out.print(temp.iData);
					
					localStack.push(temp.leftChild);
					localStack.push(temp.rightChild);
					
					if(temp.leftChild != null || temp.rightChild != null) {
						isRowEmpty = false;
					}
				}
				else {
					System.out.print("--");
					localStack.push(null);
					localStack.push(null);
				}
				
				for(int j=0; j<nBlanks*2-2; j++) {
					System.out.print(" ");
				}
			}
			
			System.out.println();
			nBlanks /= 2;
			while(localStack.isEmpty() == false) {
				globalStack.push(localStack.pop());
			}
			
			System.out.println("................................");
		}
	}
	
	public static void main(String[] args) throws IOException {
		int value;
		Tree tree = new Tree();
		
		tree.insert(50, 1.5);
		tree.insert(25, 1.2);
		tree.insert(75, 1.7);
		tree.insert(12, 1.5);
		tree.insert(37, 1.2);
		tree.insert(43, 1.7);
		tree.insert(30, 1.5);
		tree.insert(33, 1.2);
		tree.insert(87, 1.7);
		tree.insert(93, 1.5);
		tree.insert(97, 1.5);
		
		while(true) {
			System.out.print("Enter first letter of Row,");
			System.out.print("insert, find, delete or tranverse:");
			int choice = getChar();
			
			switch(choice) {
				case 's':
					tree.displayTree();
					break;
				case 'i':
					System.out.print("Enter value to insert:");
					value = getInt();
					tree.insert(value, value + 0.9);
					break;
				case 'f':
					System.out.print("Enter value to find:");
					value = getInt();
					Node found = tree.find(value);
					if(found != null) {
						System.out.print("Found:");
						found.displayNode();
						System.out.print("\n");
					}
					else {
						System.out.print("Could not found ");
						System.out.print(value + "\n");
					}
					break;
				case 'd':
					System.out.print("Enter value to delete:");
					value = getInt();
					boolean didDelete = tree.delete(value);
					if(didDelete) {
						System.out.print("Deleted " + value);
					}
					else {
						System.out.print("Could not delete ");
						System.out.print(value + "\n");
					}
				case 't':
					System.out.print("Enter type 1,2 or 3:");
					value = getInt();
					tree.traverse(value);
					break;
				default:
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
	
	class Node {
		public int iData;
		public double dData;
		public Node leftChild;
		public Node rightChild;
		
		public void displayNode() {
			System.out.println("{");
			System.out.println(iData);
			System.out.println(",");
			System.out.println(dData);
			System.out.println("}");
		}
	}
}
