

import java.util.Scanner;
import java.io.*;

public class bucketSort{
	
	private int min, max, lastIndex;
	private int[] BucketAry;
	
	public bucketSort(int min, int max){
		this.min = min;
		this.max = max;
		lastIndex = (max-min);
		BucketAry = new int[lastIndex+1];
		for(int i=0; i<BucketAry.length; i++) { BucketAry[i] = 0; }
	}
	
	public void getIndex(int data){
		BucketAry[data-min]++;
	}
	
	public void printSortData(PrintWriter x){
		for(int i=0; i<BucketAry.length; i++){
			for(int j = BucketAry[i]; j!=0; j--){
				x.println(min+i);
				System.out.println(min+i);
			}
		}
		
		x.close();
	}
	
	public static int[] findMinMax(Scanner file){
		
		/* We initialize myArray with MAX_VALUE and MIN_VALUE 
		 * so we can do an initial test for the first input value from the txt file.
		 */
		
		int[] myArray = new int[2];
		myArray[0] = Integer.MAX_VALUE;			
		myArray[1] = Integer.MIN_VALUE;			
		int num;
		
		while(file.hasNext()){
			if(file.hasNextInt()){
				num = file.nextInt();
					if(num < 0){
						System.out.println("A negative value was read. Program will exit now.");
						System.exit(0);
					}
					if(num < myArray[0]){ myArray[0] = num; }
					if(num > myArray[1]){ myArray[1] = num; }
			}
		}
		return myArray;
	}
	
	public static void main(String[] args) throws FileNotFoundException{
		
		int number;
		int[] retArr; 
		Scanner readFile;
		File inFile;
		
		if(args.length > 0){
			inFile = new File(args[0]);
			readFile = new Scanner(inFile);
		}else{
			inFile = new File("Bucket_Sort_Data.txt");
			readFile = new Scanner(inFile);
		}
		
		
		retArr = bucketSort.findMinMax(readFile);
		readFile.close();
		System.out.println(retArr[0] + " " + retArr[1]);
		bucketSort sort = new bucketSort(retArr[0], retArr[1]);
		
		readFile = new Scanner(inFile);
		while(readFile.hasNext()){
			if(readFile.hasNextInt()){
				number = readFile.nextInt();
				sort.getIndex(number);
			}
		}
		
		readFile.close();
		
		if(args.length > 0){
			PrintWriter writer = new PrintWriter(args[1]);
			sort.printSortData(writer);
			writer.close();
		}else{
			PrintWriter writer = new PrintWriter("Sorted_Data.txt");
			sort.printSortData(writer);
			writer.close();
		}
	}
}
	
	
	
