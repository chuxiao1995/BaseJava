public class bubbleDemo {
	public static void main(String[] args) {
		int[] arr = {1,4,5,3,7,8};
		bubble(arr);
		for (int i : arr) {
			System.out.print(i);
		}
	
}
	public static void bubble(int[] arr){
		int tmp = 0;
		for (int i = 0; i < arr.length-1; i++) {
			for (int j = 0; j < arr.length-1-i; j++) {
				tmp = arr[j];
				if (arr[j]>arr[j+1]) {
					arr[j] = arr[j + 1];
					arr[j + 1] = tmp ;
				}
			}
		}
	}

}
