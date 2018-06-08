package com.cx.java20.screenbroadcast.teacher;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import com.cx.java20.screenbroadcast.Util;

import javax.imageio.ImageIO;

public class Teacher {
	// 机器人类
	Robot robot;
	// 创建矩形，确定的屏幕区域
	private Rectangle rect;

	public Teacher() {
		try {
			robot = new Robot();
			rect = new Rectangle(0, 0, 1000,500);
		} catch (AWTException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 开启UDPSocket
	 */
	public void startServer() {
		try {
			DatagramSocket socket = new DatagramSocket(8888);
			// 元数据 + 60k图像切割块
			byte[] buf = new byte[1024 * 60 + 14];
			// 数据报包
			DatagramPacket pack = new DatagramPacket(buf, buf.length);
			// 广播地址
			pack.setAddress(InetAddress.getByName("192.168.2.178"));
			// 接受方端口
			pack.setPort(9999);
			//每次循环是，发送一屏(多块==多UDP包)
			while (true) {
				TimeUnit.MICROSECONDS.sleep(100);
				BufferedImage image = captureScreen();
				List<byte[]> alBlocks = popBlocks(image);
				for (byte[] arr : alBlocks) {
					pack.setData(arr);
					socket.send(pack);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 抓一屏幕的画面
	 */
	private BufferedImage captureScreen() {
		// 生成图像对象
		BufferedImage image = robot.createScreenCapture(rect);
		return image;

	}

	/**
	 * 将一屏进行切割
	 */

	private List<byte[]> popBlocks(BufferedImage screen) {

		List<byte[]> list = new ArrayList<byte[]>();
		// 结算时间戳
		long ts = System.currentTimeMillis();
		byte[] tsArr = Util.long2Bytes(ts);

		// 每个数据量(不含最后一块)
		byte[] blockBuf;
		// 每块大小
		int blockSize = 50 * 1024;
		// 总块数
		int blockCount = 0;

		try {
			// 1先进行压缩
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ZipOutputStream zos = new ZipOutputStream(baos);
			zos.putNextEntry(new ZipEntry("xxx"));
			ImageIO.write(screen, "jpg", zos);
			// 2切割
			byte[] fullScreenZipArr = baos.toByteArray();
			int mod = fullScreenZipArr.length % blockSize;
			if (mod == 0) {
				blockCount = fullScreenZipArr.length / blockSize;
			} else {
				blockCount = fullScreenZipArr.length / blockSize + 1;
			}
			// 当前块大小
			int currBlockSize = blockSize;
			// 3.生成集合
			for (int i = 0; i < blockCount; i++) {
				// 计算最后一块
				if (mod != 0 && (i == (blockCount - 1))) {
					currBlockSize = mod;
				}
				// 初始化块
				blockBuf = new byte[currBlockSize + 14];
				// 1设置时间戳
				System.arraycopy(tsArr, 0, blockBuf, 0, 8);
				// 2设置块数
				blockBuf[8] = (byte) blockCount;
				// 3设置块编号
				blockBuf[9] = (byte) i;
				// 4设置块长度
				System.arraycopy(Util.int2Bytes(currBlockSize), 0, blockBuf,
						10, 4);
				// 5设置数据
				System.arraycopy(fullScreenZipArr, (i * blockSize), blockBuf,
						14, currBlockSize);

				list.add(blockBuf);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
