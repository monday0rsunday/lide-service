package com.broduce.lide.js;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;

import org.apache.log4j.Logger;

public class ReaderUtil {

	public static String read(String filePath) {
		try {
			FileInputStream fis = new FileInputStream(filePath);
			int buffSize = 1024;
			byte[] buffer = new byte[buffSize];
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			int rCount = 0;
			while ((rCount = fis.read(buffer)) > 0) {
				baos.write(buffer, 0, rCount);
			}
			byte[] data = baos.toByteArray();
			baos.close();
			fis.close();
			return new String(data);
		} catch (Exception e) {
			Logger.getLogger(ReaderUtil.class.getSimpleName()).error("", e);
		}
		return "";
	}
}
