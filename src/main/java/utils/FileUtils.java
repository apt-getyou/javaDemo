package utils;

import java.net.URL;

/**
 * @author banhujiu
 * @date 2017/9/11 0011 14:09
 */
public class FileUtils {

	public static String getClassPatchFileLocation(String configFile) {
		if (configFile == null) {
			configFile = "";
		}
		configFile = configFile.trim().replace("/", java.io.File.separator);
		URL resource = Thread.currentThread().getContextClassLoader().getResource("");
		assert resource != null;
		String proFilePath = resource.toString();
		//移除开头的file:/六个字符
		proFilePath = proFilePath.substring(6);

		//如果为window系统下,则把路径中的路径分隔符替换为window系统的文件路径分隔符
		proFilePath = proFilePath.replace("/", java.io.File.separator);

		//兼容处理最后一个字符是否为 window系统的文件路径分隔符,同时建立 properties 文件路径
		//例如返回: D:\workspace\myproject01\WEB-INF\classes\config.properties
		if (!proFilePath.endsWith(java.io.File.separator)) {
			proFilePath = proFilePath + java.io.File.separator + configFile;
		} else {
			proFilePath = proFilePath + configFile;
		}
		return proFilePath;
	}
}
