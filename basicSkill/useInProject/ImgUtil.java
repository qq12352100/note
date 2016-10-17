package com.success.jdlk.common.util;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Java实现图片内容无损任意角度旋转
 * 
 * @author bkk
 * @version 2016年8月9日上午10:12:49
 */
public class ImgUtil {

	/**
	 * 旋转文件
	 * 
	 * @param file
	 *            图片
	 * @param overImg（1，2，3）
	 *            右转90*1,反转180=90*2,左转90=90*3
	 * @param desDir
	 *            输出路径
	 */
	public static void overImg(File file, int overImg, String desDir) {
		try {
			BufferedImage src = ImageIO.read(file);
			BufferedImage des = ImgUtil.Rotate(src, 90 * overImg);// 右转90*1,反转180=90*2,左转90=90*3
			ImageIO.write(des, "png", new File(desDir));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 判断是否存在文件
	 * 
	 * <pre>
	 * 例：String[] fileName = ImgUtil.hasFile(imgDir, base.getExpand1());
	 * </pre>
	 * 
	 * @param dir
	 *            文件夹
	 * @param name
	 *            文件名
	 * @return 文件名数组
	 */
	public static String[] hasFile(File dir, String name) {
		String[] fileName = dir.list(new FilenameFilter() {
			public boolean accept(File f, String name) {
				return name.contains(name);
			}
		});
		return fileName;
	}

	public static BufferedImage Rotate(Image src, int angel) {
		int src_width = src.getWidth(null);
		int src_height = src.getHeight(null);
		// calculate the new image size
		Rectangle rect_des = CalcRotatedSize(new Rectangle(new Dimension(src_width, src_height)), angel);

		BufferedImage res = null;
		res = new BufferedImage(rect_des.width, rect_des.height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = res.createGraphics();
		// transform
		g2.translate((rect_des.width - src_width) / 2, (rect_des.height - src_height) / 2);
		g2.rotate(Math.toRadians(angel), src_width / 2, src_height / 2);

		g2.drawImage(src, null, null);
		return res;
	}

	public static Rectangle CalcRotatedSize(Rectangle src, int angel) {
		// if angel is greater than 90 degree, we need to do some conversion
		if (angel >= 90) {
			if (angel / 90 % 2 == 1) {
				int temp = src.height;
				src.height = src.width;
				src.width = temp;
			}
			angel = angel % 90;
		}

		double r = Math.sqrt(src.height * src.height + src.width * src.width) / 2;
		double len = 2 * Math.sin(Math.toRadians(angel) / 2) * r;
		double angel_alpha = (Math.PI - Math.toRadians(angel)) / 2;
		double angel_dalta_width = Math.atan((double) src.height / src.width);
		double angel_dalta_height = Math.atan((double) src.width / src.height);

		int len_dalta_width = (int) (len * Math.cos(Math.PI - angel_alpha - angel_dalta_width));
		int len_dalta_height = (int) (len * Math.cos(Math.PI - angel_alpha - angel_dalta_height));
		int des_width = src.width + len_dalta_width * 2;
		int des_height = src.height + len_dalta_height * 2;
		return new java.awt.Rectangle(new Dimension(des_width, des_height));
	}
}
