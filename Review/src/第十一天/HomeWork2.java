//package 第十一天;
//
//import java.io.UnsupportedEncodingException;
//
//public class HomeWork2{
//	
//	
//	
//	public void doit() throws Exception {
//		
//		
//		String s = "abcdefg一二三四";
//		
//		byte[] arr = s.getBytes("gbk");
//		byte[] arr1 = reverseTwo(arr);
//		//解码过程
//		
//		String str2 = new String(arr,"gbk");
//		
//	}
//	
//	
//	public Byte[] reverse(Byte[] arr) {
//
//        //初始化数组并定义长度
//		Byte[] arrResult = new Byte[arr.length];
//        int j = 0;//新数组的下标
//        //通过循环替换数据
//        for (byte i=(byte) arr.length - 1;i>=0; i--,j++) {
//            arrResult[j] = arr[i];
//        }
//        return  arrResult;
//    }
//
//    /**
//     * @description:定义i，j相互替换
//      */
////    public Byte[] reverseTwo(Byte[] arr) {
////        byte i=0;
////        byte j = arr.length-1;
////        Byte temp;
////        while (i<j) {
////            //替换
////            temp = arr[i];
////            arr[i] = arr[j];
////            arr[j] = temp;
////            i++;
////            j--;
////        }
////        return arr;
////    }
//
//}
