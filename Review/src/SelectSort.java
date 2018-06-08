
public class SelectSort {
	public static void main(String[] args) {
		int[] arr = {1,3,5,2,6,8,9};
		SelectSort ss = new SelectSort();
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i]);
		}
		System.out.println();
		ss.doit(arr);	
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i]);
		}
	}
public void doit(int[] arr) {
	int index;
	for (int i = 1; i < arr.length; i++) {
		index = 0 ;
		for (int j = 1; j <= arr.length-i; j++) {
			if (arr[index] < arr[j]) {
				index = j;
			
			}
		}
		int temp = arr[arr.length-i];
		arr[arr.length-i] = arr[index];
		arr[index] = temp; 
		
	}
	
}
}
