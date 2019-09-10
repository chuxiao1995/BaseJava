
public class firstTask {
public static void main(String[] args) {
	int n = 100;
	for (int i = 1; i <=n; i++) {
//		
//		 switch (i) {
//		case 1:
//			System.out.print("    *    "); 
//			break;
//		case 5:
//			System.out.print("*********");
//			break;
//		default:
//			for (int j = 1;j <= (5-i);j++) {
//				System.out.print(" ");
//			}
//			System.out.print("*");
//			for (int j = 1;j <= (2*i-3);j++) {
//				System.out.print(" ");
//			}
//			System.out.print("*");
//			break;
//		}
//		 	System.out.println();
	if ( i == 1) {
		for (int j = 1;j <= (n-i);j++) {
			System.out.print(" ");
		}
		System.out.print("*");
	} else if (i == n) {
		for (int j = 1; j <= 2*n-1; j++) {
			System.out.print("*");
		}
	} else {
		for (int j = 1;j <= (n-i);j++) {
			System.out.print(" ");
		}
		System.out.print("*");
		for (int j = 1;j <= (2*i-3);j++) {
			System.out.print(" ");
		}
		System.out.print("*");
	}
	System.out.println();
	}
}
}

