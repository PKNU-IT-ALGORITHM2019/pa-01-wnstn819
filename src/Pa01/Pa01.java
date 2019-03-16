package Pa01;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Pa01 {
    static int count =0;
    static ArrayList<String> arr = new ArrayList<String>();
    static String[][] max = null;
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);	
		

		
		for( ; ;) {
			
			
			System.out.print("$ ");
			String name = sc.next();
			
			if(name.equals("read")) {
				String emt = sc.nextLine().trim();
				 max =read(emt,arr);
				 
				
			}else if(name.contains("size")) {
				System.out.println(size(arr));
			}else if(name.equals("find")) {
				
				String emt = sc.nextLine().trim();
				find(0,size(arr),emt);
				
								
			}else if(name.contains("exit")) {
				return;
			}
			
		}
		
		
		
	}



	static String[][] read(String data,ArrayList<String> arr) throws IOException {
		try {
		BufferedReader br = new BufferedReader(new FileReader(data));
		arr.add(new String(br.readLine()));
	    	while( br.ready()) {
				if(br.readLine() == " "){
	
				} 
				arr.add(new String(br.readLine()));
		     }
			arr.add(null);
			
			br.close();
			 String[][] mvp = new String[size(arr)][1000];
				for(int i=0; i<size(arr);i++) {
					String[] arrplus = arr.get(i).split("\\(");
				
				for(int j=0; j<arrplus.length;j++) {
				
					mvp[i][j] = arrplus[j];
					
				}
				
				}
				return mvp;
				
				
			
		} catch (FileNotFoundException e1) {
			System.out.println("파일을 찾을 수 없습니다.");
			e1.printStackTrace();
			return null;
		}
		
		
	}
	

	static int size(ArrayList<String> arr) {
		
	    while(arr.get(count)!=null) {
		  count++;
	    }
		return count;
	}
	
	
	
	public static int find(int begin, int end,String findme) {
		    
		if(end<begin) {
			
			int idx = arr.get(end).indexOf(")");
			int idx2 = arr.get(begin).indexOf(")");
			System.out.println("Not found.");
			System.out.println(arr.get(end).substring(0,idx+1));
			System.out.println("--------");
			System.out.println(arr.get(begin).substring(0,idx2+1));
			return end;

		}
		else {
		
			int middle=(begin+end)/2;
			String changefm;
			String changemax;
			
				changefm = findme.replaceAll("-","").replaceAll("'","").replaceAll(" ", "").trim(); //
			     changemax = max[middle][0].replaceAll("-", "").replaceAll("'","").replaceAll(" ","").trim();
				         
			
		    if(findme.trim().compareToIgnoreCase(max[middle][0].trim())==0 ) {
			    
		    	
				findit(middle);
				return middle;
			}
			else if(changefm.compareToIgnoreCase(changemax)<=0) {
				
				return find(begin, middle-1, findme);
			}

			else {
				
				return	find(middle+1,end, findme);
		
			}
			
			
		}
			
		
	}
	
	public static void findit(int middle) {
		int CCount =1;
		int upcom =middle;
		int downcom =middle;
		for( ; ; ) {
			
			if(downcom>0 && max[middle][0].trim().compareToIgnoreCase(max[downcom-1][0].trim()) ==0){
					downcom--;
					CCount++;
				
				
				
			}else {
				break;
			}
					
		}
		
		
		for( ; ; ) {
			
			if(upcom<size(arr) &&  max[middle][0].trim().compareToIgnoreCase(max[upcom+1][0].trim())==0) {
				CCount++;
				upcom++;
				
			 }
			else {
				break;
			}
			
		
		}
		
		System.out.println("Foun " + CCount + " items." );
		
		for(int i= downcom; i<downcom+CCount;i++) {
			System.out.println(arr.get(i));
			
		}
		System.out.println("affair took place " + max[middle][0]);
		
	}
	
	

	

}
