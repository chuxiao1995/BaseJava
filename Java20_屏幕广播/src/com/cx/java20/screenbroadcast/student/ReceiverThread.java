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
				// 接受到的包总长度
				int len = packet.getLength();
				// 解析包
				FrameUnit unit = prasePack(packet);
				// 处理帧单元
				ProcessUnit(unit);
				// 处理一帧
				processFrame();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * 
	 * 处理一帧：帧单元是否收集齐全
	 */
	private void processFrame() {
		//
		if (!allUnits.isEmpty()) {
			// 已经收集的个数
			int collectedUnits = allUnits.size();
			// 所有unit数量
			int allUnitCount = allUnits.values().iterator().next().getCount();
			// 收集全否
			if (collectedUnits == allUnitCount) {
				// 生成图片
				getImage();

			} 
		}

	}

	/**
	 * 生成图像
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
			//得到压缩的帧画面
			byte[] frameData = baos.toByteArray();
			//解压
			ZipInputStream zin = new ZipInputStream(new ByteArrayInputStream(frameData));
			
			
			
			
			try {
				zin.getNextEntry();
			} catch (IOException e) {
				//System.out.println("zininput");
				e.printStackTrace();
			}
			
			byte[] buf = new byte[512];
			int len = -1 ;
			
			//创建baos，容纳解压的帧画面数据
			 baos = new ByteArrayOutputStream();
			 int i = 0;
			 
			try {
				while ((len = zin.read(buf)) != -1) {
					i++;
					baos.write(buf,0,len);
				}
			} catch (Exception e) {
				System.out.println("len"+ i+"  :  "+len);
				//会抛出Unexpected end of ZLIB input stream
				//未写入所有的字节数据就会抛出异常
				//e.printStackTrace();
			}
			//得到解压的帧画面数据
			try {
				baos.flush();
				zin.close();
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("zin.close");
			}
			byte[] unzipFrameData = baos.toByteArray();
			//刷新UI的画面
			ui.updateIcon(unzipFrameData);
			//清除allUnits
			allUnits.clear();
			
			
		
	}

	/**
	 * 
	 * 处理帧单元
	 */
	private void ProcessUnit(FrameUnit unit) {
		if (allUnits.isEmpty()) {
			allUnits.put(unit.getTimestamp() + "-" + unit.getIndex(), unit);

		} else {
			//传入的帧单元的时间戳
			long ts = unit.getTimestamp();
			// 方法链，获得已有的时间戳
			long ts0 = allUnits.values().iterator().next().getTimestamp();
			// 属于同一帧
			if (ts == ts0) {
				allUnits.put(unit.getTimestamp() + "-" + unit.getIndex(), unit);
				// 新帧
			} else if (ts > ts0) {
				allUnits.clear();
				allUnits.put(unit.getTimestamp() + "-" + unit.getIndex(), unit);
			}
			// 旧帧
			else {
				return;
			}
		}

	}

	/**
	 * 解析包,返回帧单元对象
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
