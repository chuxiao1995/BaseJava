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
	// ��������
	Robot robot;
	// �������Σ�ȷ������Ļ����
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
	 * ����UDPSocket
	 */
	public void startServer() {
		try {
			DatagramSocket socket = new DatagramSocket(8888);
			// Ԫ���� + 60kͼ���и��
			byte[] buf = new byte[1024 * 60 + 14];
			// ���ݱ���
			DatagramPacket pack = new DatagramPacket(buf, buf.length);
			// �㲥��ַ
			pack.setAddress(InetAddress.getByName("192.168.2.178"));
			// ���ܷ��˿�
			pack.setPort(9999);
			//ÿ��ѭ���ǣ�����һ��(���==��UDP��)
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
	 * ץһ��Ļ�Ļ���
	 */
	private BufferedImage captureScreen() {
		// ����ͼ�����
		BufferedImage image = robot.createScreenCapture(rect);
		return image;

	}

	/**
	 * ��һ�������и�
	 */

	private List<byte[]> popBlocks(BufferedImage screen) {

		List<byte[]> list = new ArrayList<byte[]>();
		// ����ʱ���
		long ts = System.currentTimeMillis();
		byte[] tsArr = Util.long2Bytes(ts);

		// ÿ��������(�������һ��)
		byte[] blockBuf;
		// ÿ���С
		int blockSize = 50 * 1024;
		// �ܿ���
		int blockCount = 0;

		try {
			// 1�Ƚ���ѹ��
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ZipOutputStream zos = new ZipOutputStream(baos);
			zos.putNextEntry(new ZipEntry("xxx"));
			ImageIO.write(screen, "jpg", zos);
			// 2�и�
			byte[] fullScreenZipArr = baos.toByteArray();
			int mod = fullScreenZipArr.length % blockSize;
			if (mod == 0) {
				blockCount = fullScreenZipArr.length / blockSize;
			} else {
				blockCount = fullScreenZipArr.length / blockSize + 1;
			}
			// ��ǰ���С
			int currBlockSize = blockSize;
			// 3.���ɼ���
			for (int i = 0; i < blockCount; i++) {
				// �������һ��
				if (mod != 0 && (i == (blockCount - 1))) {
					currBlockSize = mod;
				}
				// ��ʼ����
				blockBuf = new byte[currBlockSize + 14];
				// 1����ʱ���
				System.arraycopy(tsArr, 0, blockBuf, 0, 8);
				// 2���ÿ���
				blockBuf[8] = (byte) blockCount;
				// 3���ÿ���
				blockBuf[9] = (byte) i;
				// 4���ÿ鳤��
				System.arraycopy(Util.int2Bytes(currBlockSize), 0, blockBuf,
						10, 4);
				// 5��������
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
