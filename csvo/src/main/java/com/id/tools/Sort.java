/**
 * <br>项目名: csvo
 * <br>文件名: Test.java
 * <br>Copyright 2016
 */
package com.id.tools;

import java.io.IOException;

/**
 * <br>
 * 类 名: Sort <br>
 * 描 述: 排序 <br>
 * 作 者: yufenghe <br>
 * 创 建： 2016年3月22日 <br>
 * 版 本：v1.0.0 <br>
 * <br>
 * 历 史: (版本) 作者 时间 注释
 */
public class Sort {
	private static int[] GENERATE_ARR; 
	
	public Sort() {
		
	}
	
	public static int operate() throws IOException {
		System.out.println("请输入序号进行操作:");
		byte[] command = new byte[4];
		System.in.read(command);
		int operate = 0;
		try {
			String commandString = new String(command);
			commandString = commandString.replaceAll("\r\n", "").trim();
			operate = Integer.parseInt(commandString);
		} catch (Exception e) {
			System.out.println("命令输入错误！！！");
		}
		
		return operate;
	}
	
	public static void main(String[] args) throws IOException {
		startMenu();
		while(true) {
			int operate = operate();
			switch (operate) {
				case 1:
					int diskNum = operate(); 
					System.out.println("################汉诺塔################");
					doTowers(diskNum, 'A', 'B', 'C');
					break;
				case 2:
					bubbleSort();
					break;
				case 3:
					selectSort();
					break;
				case 4:
					insertSort();
					break;
				case 5:
					oddEvenSort();
					break;
				case 6:
					GENERATE_ARR = generateArr();
					mergeSort();
					break;
				case 7:
					shellSort();
					break;
				case 8:
					System.exit(0);
				default:
					System.out.println("没有该命令 " + operate);
					break;
				}
		}
	}
	
	public static void startMenu() {
		int i = 1;
		System.out.println(i++ + "、汉诺塔.");
		System.out.println(i++ + "、冒泡排序.");
		System.out.println(i++ + "、选择排序");
		System.out.println(i++ + "、插入排序");
		System.out.println(i++ + "、奇偶排序");
		System.out.println(i++ + "、归并排序");
		System.out.println(i++ + "、希尔排序");
		System.out.println(i + "、退出");
	}
	
	public static int[] generateArr() {
		System.out.println("------------排序前-------------");
		int[] arr = new int[55];
		for(int i=0; i<55; i++) {
			arr[i] = (int) (Math.random()*100 + 1);
			System.out.print(arr[i] + " ");
		}
		
		System.out.println();
		
		return arr;
	}
	
	public static void print(int[] arr) {
		System.out.println("************排序后************");
		for(int i : arr) {
			System.out.print(i + " ");
		}
		
		System.out.println();
	}
	
	/**
	 * 
	 * <br>描 述：冒泡排序
	 * <br>作 者：yufenghe 
	 * <br>历 史: (版本) 作者 时间 注释
	 */
	public static void bubbleSort() {
		System.out.println("################冒泡排序################");
		int[] arr = generateArr();
		for(int i=0; i<arr.length; i++) {
			for(int j=0; j<arr.length-i-1; j++) {
				if(arr[j] > arr[j+1]) {
					int temp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = temp;
				}
			}
		}
		
		print(arr);
	}
	
	/**
	 * 
	 * <br>描 述：冒泡排序
	 * <br>作 者：yufenghe 
	 * <br>历 史: (版本) 作者 时间 注释
	 */
	public static void bubbleSort1() {
		System.out.println("################冒泡排序################");
		int[] arr = generateArr();
		for(int i=0; i<arr.length; i++) {
			for(int j=arr.length-1; j>i; j--) {
				if(arr[j] < arr[j-1]) {
					int temp = arr[j];
					arr[j] = arr[j-1];
					arr[j-1] = temp;
				}
			}
		}
		
		print(arr);
	}
	
	/**
	 * 
	 * <br>描 述：冒泡排序
	 * <br>作 者：yufenghe 
	 * <br>历 史: (版本) 作者 时间 注释
	 */
	public static void bubbleSort2() {
		System.out.println("################冒泡排序################");
		int[] arr = generateArr();
		for(int i=arr.length-1; i>0; i--) {
			for(int j=0; j<i; j++) {
				if(arr[j] > arr[j+1]) {
					int temp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = temp;
				}
			}
		}
		
		print(arr);
	}
	
	/**
	 * 
	 * <br>描 述：选择排序
	 * <br>作 者：yufenghe 
	 * <br>历 史: (版本) 作者 时间 注释
	 */
	public static void selectSort() {
		System.out.println("################选择排序################");
		int[] arr = generateArr(); 
		for(int i=0; i<arr.length-1; i++) {
			int index = i;
			for(int j=i+1; j<arr.length; j++) {
				if(arr[j] < arr[index]) {
					index = j;
				}
			}
			
			int temp = arr[i];
			arr[i] = arr[index];
			arr[index] = temp;
		}
		
		print(arr);
	}
	
	/**
	 * 
	 * <br>描 述：插入排序
	 * <br>作 者：yufenghe 
	 * <br>历 史: (版本) 作者 时间 注释
	 */
	public static void insertSort() {
		System.out.println("################插入排序################");
		
		int[] arr = generateArr(); 
		
		for(int i=1; i<arr.length; i++) {
			int flag = arr[i];
			int j = i;
//			while(j>0 && arr[j-1] >= flag) {
//				if(arr[j-1] == flag) {
//					flag = -1;
//				}
//				arr[j] = arr[j-1];
//				
//				j--;
//			}
			while(j>0 && arr[j-1] > flag) {
				arr[j] = arr[j-1];
				j--;
			}
			
			arr[j] = flag;
		}
		
		print(arr);
	}
	
	/**
	 * 
	 * <br>描 述：奇偶排序
	 * <br>作 者：yufenghe 
	 * <br>历 史: (版本) 作者 时间 注释
	 */
	public static void oddEvenSort() {
		System.out.println("################奇偶排序################");
		int[] arr = generateArr(); 
		boolean flag = true;
		while(flag) {
			boolean odd = false, even = false;
			for(int i=0; i<arr.length;) {
				if(i+1 > arr.length-1) {
					odd = false;
					break;
				}
				if(arr[i] > arr[i+1]) {
					arr[i] = arr[i+1] + 0*(arr[i+1] = arr[i]);
					odd = true;
				}
				i+=2;
			}
			
			for(int i=1; i<arr.length;) {
				if(i+1 > arr.length-1) {
					even = false;
					break;
				}
				if(arr[i] > arr[i+1]) {
					arr[i] = arr[i+1] + 0*(arr[i+1] = arr[i]);
					even = true;
				}
				i+=2;
			}
			
			flag = odd || even;
		}
		
		print(arr);
	}
	
	/**
	 * 
	 * <br>描 述： 归并排序
	 * <br>作 者：yufenghe 
	 * <br>历 史: (版本) 作者 时间 注释
	 */
	public static void mergeSort() {
		System.out.println("################归并排序################");
		int[] arr = new int[GENERATE_ARR.length]; 
		recMerge(arr, 0, arr.length-1);
		
		print(arr);
		print(GENERATE_ARR);
	}
	
	
	public static void recMerge(int[] workspace, int low, int high) {
		if(low == high) {
			return;
		}
		
		int mid = (low + high)/2;
		recMerge(workspace, low, mid);
		recMerge(workspace, mid+1, high);
		
		merge(workspace, low, mid, high);
	}
	
	public static void merge(int[] workspace, int low, int mid, int high) {
		int j = 0;
		int lowBound = low;
		int midl = mid;
		int midh = mid + 1;
		int n = high - lowBound + 1;
		while(low <= midl && midh <= high) {
			if(GENERATE_ARR[low] < GENERATE_ARR[midh]) {
				workspace[j++] = GENERATE_ARR[low++];
			}
			else {
				workspace[j++] = GENERATE_ARR[midh++];
			}
		}
		
		while(low <= midl) {
			workspace[j++] = GENERATE_ARR[low++];
		}
		
		while(midh <= high) {
			workspace[j++] = GENERATE_ARR[midh++];
		}
		
		for(int i=0; i<n; i++) {
			GENERATE_ARR[lowBound+i] = workspace[i];
		}
	}
	
	/**
	 * 
	 * <br>描 述：汉诺塔
	 * <br>作 者：yufenghe 
	 * <br>历 史: (版本) 作者 时间 注释
	 * @param n
	 * @param from
	 * @param inter
	 * @param to
	 */
	public static void doTowers(int n, char from, char inter, char to) {
		if(n == 1) {
			System.out.println("Disk 1 from " +  from + " to " + to);
		}
		else {
			doTowers(n-1, from, to, inter);
			System.out.println("Disk " + n + " from " + from + " to " + to);
			doTowers(n-1, inter, from, to);
		}
	}
	
	public static void shellSort() {
		int[] arr = generateArr();
		int h = 1;
		int inner, outter;
		int temp;
		while(h <= arr.length/3) {
			h = h*3 + 1;
		}
		
		while(h > 0) {
			for(outter=h; outter<arr.length; outter++) {
				temp = arr[outter];
				inner = outter;
				
				while(inner > h-1 && arr[inner-h] >= temp) {
					arr[inner] = arr[inner - h];
					inner -= h;
				}
				
				arr[inner] = temp;
			}
			
			h = (h - 1)/3;
		}
		
		print(arr);
	}
}
