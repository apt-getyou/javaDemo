package com.banhujiu.test;

import model.User;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;

import org.junit.Test;

/**
 * @author 刘博文
 * @date 2017/9/11 0011 11:15
 * transient 关键词
 * 1）一旦变量被transient修饰，变量将不再是对象持久化的一部分，该变量内容在序列化后无法获得访问。
 * 2）transient关键字只能修饰变量，而不能修饰方法和类。注意，本地变量是不能被transient关键字修饰的。变量如果是用户自定义类变量，则该类需要实现Serializable接口。
 * 3）被transient关键字修饰的变量不再能被序列化，一个静态变量不管是否被transient修饰，均不能被序列化
 */
public class TransientTest {
	/**
	 * 使用transient关键字不序列化某个变量
	 * 注意读取的时候，读取数据的顺序一定要和存放数据的顺序保持一致
	 */
	@Test
	public void test_transient_for_serializable() {
		User user = new User();
		user.setUsername("Alexia");
		user.setPassword("123456");
		user.setTestStatic("序列1");

		System.out.println("read before Serializable: ");
		System.out.println("username: " + user.getUsername());
		System.out.println("password: " + user.getPassword());
		System.out.println("testStatic: " + user.getTestStatic());
		ObjectOutputStream os = null;

		Thread.currentThread().getContextClassLoader().getResource("");
		String fileLocation = getFileLocation();
		try {
			os = new ObjectOutputStream(new FileOutputStream(fileLocation));
			os.writeObject(user); // 将User对象写进文件
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (os != null) {
				try {
					os.flush();
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println("\n");
		ObjectInputStream is = null;
		try {
			user.setTestStatic("序列2");
			is = new ObjectInputStream(new FileInputStream(fileLocation));
			user = (User) is.readObject(); // 从流中读取User的数据
			System.out.println("read after Serializable: ");
			System.out.println("username: " + user.getUsername());
			System.out.println("password: " + user.getPassword());
			/*
			反序列化后类中static型变量testStatic的值为当前JVM中对应static变量的值，
			这个值是JVM中的不是反序列化得出的
			所以关于 transient 的第三点描述是正确的
			 */
			System.out.println("testStatic: " + user.getTestStatic());
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Test
	public void testLocation(){
		URL resource = Thread.currentThread().getContextClassLoader().getResource("");
		assert resource != null;
		System.out.println(resource.toString());
	}

	@Test
	public void test_getFileLocation(){
		System.out.println(getFileLocation());
	}


	private String getFileLocation(){
		URL resource = Thread.currentThread().getContextClassLoader().getResource("");
		assert resource != null;
		String proFilePath = resource.toString();
		String configFile = "user.txt";
		//移除开通的file:/六个字符
		proFilePath = proFilePath.substring(6);

		//如果为window系统下,则把路径中的路径分隔符替换为window系统的文件路径分隔符
		proFilePath = proFilePath.replace("/", java.io.File.separator);

		//兼容处理最后一个字符是否为 window系统的文件路径分隔符,同时建立 properties 文件路径
		//例如返回: D:\workspace\myproject01\WEB-INF\classes\config.properties
		if(!proFilePath.endsWith(java.io.File.separator)){
			proFilePath = proFilePath + java.io.File.separator + configFile;
		} else {
			proFilePath = proFilePath + configFile;
		}
		return proFilePath;
	}
}


