//package ��ʮһ��;
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
//		String s = "abcdefgһ������";
//		
//		byte[] arr = s.getBytes("gbk");
//		byte[] arr1 = reverseTwo(arr);
//		//�������
//		
//		String str2 = new String(arr,"gbk");
//		
//	}
//	
//	
//	public Byte[] reverse(Byte[] arr) {
//
//        //��ʼ�����鲢���峤��
//		Byte[] arrResult = new Byte[arr.length];
//        int j = 0;//��������±�
//        //ͨ��ѭ���滻����
//        for (byte i=(byte) arr.length - 1;i>=0; i--,j++) {
//            arrResult[j] = arr[i];
//        }
//        return  arrResult;
//    }
//
//    /**
//     * @description:����i��j�໥�滻
//      */
////    public Byte[] reverseTwo(Byte[] arr) {
////        byte i=0;
////        byte j = arr.length-1;
////        Byte temp;
////        while (i<j) {
////            //�滻
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
