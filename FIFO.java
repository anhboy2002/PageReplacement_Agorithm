package hedieuhanh;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

public class FIFO {
	static int[] head = new int[50];// nhap so phan tu
	static int frame;// so khung trang
	static int n;// do dai cua chuoi tham chieu
	static int[] frames = new int[7]; // mang cua trang
	static int[] time = new int[7]; // mang kiem tra
	static int count = 0;// diem trang bi loi

	public static void main(String[] args) {
		
		//setFile();
		//print();
		//LRU();
		//FIFO();
		//OPT();
		PageReplacement();
	}
	public static void PageReplacement() {
		int i=0;
		do {
		Scanner sc= new Scanner(System.in);
		System.out.println("Page Replacement Algorithm");
		System.out.println("1.Take data from file");
		System.out.println("2.Made data by yourself");
		int choice= sc.nextInt();
		if(choice==1) 
			{
			setFile();
			al();
			}
		else if(choice ==2) 
		   {
			set();
			al();
		   }
		else {
			System.out.println("Nhập lại:");
			i=1;
		}
		}while (i==1);
	}

	private static void al() {
		int i=0;
		do {
		Scanner sc= new Scanner(System.in);
		System.out.println("Choose Page Replacement Algorithm");
		System.out.println("1.FIFO");
		System.out.println("2.LRU");
		System.out.println("3.OPT");
		int choice= sc.nextInt();
		if(choice==1) 
			{
			print();
			FIFO();
			}
		else if(choice ==2) 
		   {
			print();
			LRU();
		   }
		else if (choice==3){
			print();
			OPT();
		   }
		else {
			System.out.println("Nhập lại:");
			i=1;
		}
		}while (i==1);
	}
//	private static void LRU() {
//		j = 0;
//		int h = 0;
//		int t = frame;
//		while (h < n) {
//			System.out.print("  " + head[h] + "    ");
//			check = 0;
//			for (int i = 0; i < frame; i++)
//				if (head[h] == frames[i]) // ktra trang co san hay ko
//					check = 1;// trang co san
//			if (check == 0) {
//				if (t > 0) {
//					frames[j] = head[h];
//					j = (j + 1) % frame;
//					count++;
//					System.out.print("|");
//					for (int i = 0; i < frame; i++)
//						System.out.print("   " + frames[i] + " ");
//					System.out.println("|  F");
//					t--;
//
//				} else {
//					int loop = 0;
//					for (int k = 1; k <= frame; k++) // ktra lap head ko
//						for (int i = k + 1; i <= frame; i++)
//							if (head[h - k] == head[h - i])
//								loop++;
//					for (int i = 0; i < frame; i++) {
//						if (head[h - frame - loop] == frames[i])
//							frames[i] = head[h];
//					}
//					System.out.print("|");
//					for (int i = 0; i < frame; i++)
//						System.out.print("   " + frames[i] + " ");
//					System.out.println("|  F");
//					f++;
//				}
//
//			} else {
//				System.out.print("|");
//				for (int i = 0; i < frame; i++)
//					System.out.print("    " + frames[i] + " ");
//				System.out.println("|");
//
//			}
//			h++;
//		}
//		System.out.println("Trang lỗi :"+f);
//	}

	private static void LRU() {
		int h,i,j,check;
		for(i=0;i<frame;i++)
			time[i]=0;
		j=0;
		for(h=1;h<=n;h++) {
			System.out.print("  " + head[h] + "    ");
			check = 0;
			for (i = 0; i < frame; i++)
				if (head[h] == frames[i]) {// ktra trang co san hay ko
					check = 1;// trang co san
					time[i]=h;	
				}
			if(check==0) {
				frames[j]=head[h];
				time[j]=h;
				count++;
				print1();
				System.out.println("|  F");
			}
			else {
				print1();
				System.out.println("|");
			}
			for (i = 0; i < frame; i++)
                if (time[i] < time[j])
                    j = i;
					
		}
		System.out.println("Trang lỗi :"+count);
	}
	
//	private static void OPT() {
//		j = 0;
//		int h = 0;
//		int t = frame;
//		while (h < n) {
//			System.out.print("  " + head[h] + "    ");
//			check = 0;
//			for (int i = 0; i < frame; i++)
//				if (head[h] == frames[i]) // ktra trang co san hay ko
//					check = 1;// trang co san
//			if (check == 0) {
//				if (t > 0) {
//					frames[j] = head[h];
//					j = (j + 1) % frame;
//					count++;
//
//					System.out.print("|");
//					for (int i = 0; i < frame; i++)
//						System.out.print("   " + frames[i] + " ");
//					System.out.println("|  F");
//					t--;
//					f++;
//					
//
//				} else
//					checkOPT(h);
//
//			} else {
//				System.out.print("|");
//				for (int i = 0; i < frame; i++)
//					System.out.print("    " + frames[i] + " ");
//				System.out.println("|");
//
//			}
//
//			h++;
//		}
//		System.out.println("Trang lỗi :"+f);
//	}
//
//	private static void checkOPT(int h) {
//		int[] loop = new int[frame];
//		int max = 0;
//		for (int k = 0; k < frame; k++) {
//			int find = 0;
//			for (int i = h; i < n; i++) {
//				loop[k]++;
//				if (frames[k] == head[i]) {
//					find = 1;
//					break;
//				}
//			}
//			if (find == 0) {
//				loop[k] = 0;
//				System.out.print("x");
//			}
//			System.out.print(" " + loop[k]);
//			// System.out.println();
//		}
//		max = loop[0];
//		for (int i = 0; i < frame; i++) {
//			
//			if (max < loop[i])
//				max = loop[i];
//			System.out.print("max"+max);
//		}
//		int change = 0;
//		for (int i = 0; i < frame; i++) {
//			// System.out.print(" " + loop[i]+max);
//			if (loop[i] == 0) {
//				frames[i] = head[h];
//				change = 1;
//				System.out.print("change 1");
//				break;
//			}
//		}
//		if (change == 0) {
//			for (int i = 0; i < frame; i++) {
//				if (max == loop[i]) {
//					frames[i] = head[h];
//					System.out.print("change 2"+max);
//					break;
//				}
//			}
//		}
//		System.out.print("|");
//		for (int i = 0; i < frame; i++)
//			System.out.print("   " + frames[i] + " ");
//		System.out.println("|  F");
//		f++;
//
//	}
	private static void OPT() {
		int i,j=0,h,vt,temp,check;
		for(h=1;h<=n;h++) {
			System.out.print("  " + head[h] + "    ");
			check=0;
			for(i=0;i<frame;i++)
				if(head[h]==frames[i])
					check=1;
			if(check==0) {
				frames[j]=head[h];
				count++;
				print1();
				System.out.println("|  F");
			}
			else {
				print1();
			   System.out.println("|");
			}
			   temp=0;
			for(i=0;i<frame;i++) {
				vt=find(frames[i],h+1);
				if(vt==0) {
					j=i;
					break;
				}
				else {
					if(vt>temp) {
						j=i;
						temp=vt;
					}
				}
			}		
		}
		System.out.println("Trang lỗi :"+count);
	}
	private static int find(int x,int y) {
		int i;
		if(y==n) return 0;
		for(i=y;i<=n;i++) 
			if(head[i]==x)
				return i;

		return 0;
	}

	private static void FIFO() {
		int h = 1,i,j=0,check;
		while (h <= n) {
			System.out.print("  " + head[h] + "    ");
			check = 0;
			for (i = 0; i < frame; i++)
				if (head[h] == frames[i]) // ktra trang co san hay ko
					check = 1;// trang co san
			if (check == 0) {
				frames[j] = head[h];
				j = (j + 1) % frame;
				count++;
				print1();
				System.out.println("|  F");

			} else {
				print1();
			   System.out.println("|");
			}
			h++;	
		}
		System.out.println("Trang lỗi :"+count);
	}
	
	private static void print() {
		System.out.print("Số trang " + "| Khung Trang");
		for (int i = 0; i < frame; i++)
			System.out.print("     ");
		System.out.println("|");
	}
	
	private static void print1() {
		System.out.print("|");
		for (int i = 0; i < frame; i++)
			System.out.print("    " + frames[i] + " ");
	}
	
	private static void set() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Nhap so phan tu chuoi tham chieu:");
		n = sc.nextInt();
		System.out.println("Nhap so khung trang");
		frame = sc.nextInt();
		for (int i = 1; i <= n; i++) {
			System.out.println("Nhap chuoi ");
			head[i] = sc.nextInt();
		}
		for (int i = 0; i < frame; i++)
			frames[i] = -1; // danh trang trống
	}
	private static void setFile() {
		FileReader fr ;
        BufferedReader br;
		int c;
		int i=0;
		 String[] line1=new String[20];
		try {
			 fr = new FileReader("D:\\1.txt");
	         br = new BufferedReader(fr);
	         line1=br.readLine().split("\\s",0);
		} catch (FileNotFoundException e) {
			System.out.println("Loi file:"+e.getMessage());
		}
		 catch (IOException e) {
			 System.out.println("Loi doc file:"+e.getMessage());
		}
		n=Integer.parseInt(line1[0]);
		frame=Integer.parseInt(line1[1]);
		for(i=1;i<=n;i++)	
			head[i]=Integer.parseInt(line1[i+1]);
		for ( i = 0; i < frame; i++)
			frames[i] = -1; // danh trang trống

}
	}


