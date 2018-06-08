package com.cx.java20.screenbroadcast.student;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipInputStream;

import com.cx.java20.screenbroadcast.Util;

public class ReceiverThread extends Thread {
	private Map<String, FrameUnit> allUnits = new HashMap<String, FrameUnit>();
	private DatagramSocket sock;
	private StudentUI ui;

	public ReceiverThread(StudentUI ui) {
		try {
			this.ui = ui;
			sock = new DatagramSocket(9999);
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	@Override
	public void run() {
		//
		byte[] buf = new byte[64 * 1024];
		//
		DatagramPacket packet = new DatagramPacket(buf, buf.length);

		while (true) {
			try {
				sock.receive(packet);
				// ���ܵ��İ��ܳ���
				int len = packet.getLength();
				// ������
				FrameUnit unit = prasePack(packet);
				// ����֡��Ԫ
				ProcessUnit(unit);
				// ����һ֡
				processFrame();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * 
	 * ����һ֡��֡��Ԫ�Ƿ��ռ���ȫ
	 */
	private void processFrame() {
		//
		if (!allUnits.isEmpty()) {
			// �Ѿ��ռ��ĸ���
			int collectedUnits = allUnits.size();
			// ����unit����
			int allUnitCount = allUnits.values().iterator().next().getCount();
			// �ռ�ȫ��
			if (collectedUnits == allUnitCount) {
				// ����ͼƬ
				getImage();

			} 
		}

	}

	/**
	 * ����ͼ��
	 */
	private void getImage() {
		long ts = allUnits.values().iterator().next().getTimestamp();
		FrameUnit unit = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
			for (int i = 0; i < allUnits.size(); i++) {
				unit = allUnits.get(ts + "-" + i);
				try {
					baos.write(unit.getUnitData());
				} catch (IOException e) {
					
					e.printStackTrace();
				}
			}
			//�õ�ѹ����֡����
			byte[] frameData = baos.toByteArray();
			//��ѹ
			ZipInputStream zin = new ZipInputStream(new ByteArrayInputStream(frameData));
			
			
			
			
			try {
				zin.getNextEntry();
			} catch (IOException e) {
				//System.out.println("zininput");
				e.printStackTrace();
			}
			
			byte[] buf = new byte[512];
			int len = -1 ;
			
			//����baos�����ɽ�ѹ��֡��������
			 baos = new ByteArrayOutputStream();
			 int i = 0;
			 
			try {
				while ((len = zin.read(buf)) != -1) {
					i++;
					baos.write(buf,0,len);
				}
			} catch (Exception e) {
				System.out.println("len"+ i+"  :  "+len);
				//���׳�Unexpected end of ZLIB input stream
				//δд�����е��ֽ����ݾͻ��׳��쳣
				//e.printStackTrace();
			}
			//�õ���ѹ��֡��������
			try {
				baos.flush();
				zin.close();
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("zin.close");
			}
			byte[] unzipFrameData = baos.toByteArray();
			//ˢ��UI�Ļ���
			ui.updateIcon(unzipFrameData);
			//���allUnits
			allUnits.clear();
			
			
		
	}

	/**
	 * 
	 * ����֡��Ԫ
	 */
	private void ProcessUnit(FrameUnit unit) {
		if (allUnits.isEmpty()) {
			allUnits.put(unit.getTimestamp() + "-" + unit.getIndex(), unit);

		} else {
			//�����֡��Ԫ��ʱ���
			long ts = unit.getTimestamp();
			// ��������������е�ʱ���
			long ts0 = allUnits.values().iterator().next().getTimestamp();
			// ����ͬһ֡
			if (ts == ts0) {
				allUnits.put(unit.getTimestamp() + "-" + unit.getIndex(), unit);
				// ��֡
			} else if (ts > ts0) {
				allUnits.clear();
				allUnits.put(unit.getTimestamp() + "-" + unit.getIndex(), unit);
			}
			// ��֡
			else {
				return;
			}
		}

	}

	/**
	 * ������,����֡��Ԫ����
	 */
	private FrameUnit prasePack(DatagramPacket pack) {
		byte[] buf = pack.getData();
		//
		long timestamp = Util.bytes2long(buf);
		// 2.blockCount
		int blockCount = buf[8];
		// 3.blockIndex
		int blockIndex = buf[9];
		// blockLen
		int blockLen = Util.bytes2Int(buf, 10);

		//
		byte[] blockData = new byte[blockLen];
		System.arraycopy(buf, 14, blockData, 0, blockLen);

		return new FrameUnit(timestamp, blockCount, blockIndex, blockData);

	}

}
