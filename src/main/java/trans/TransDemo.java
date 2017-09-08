package trans;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author 刘博文
 * @date 2017/9/7 0007 17:55
 */
public class TransDemo {
	public static void main(String[] args) throws IOException {
		String arg = "";
		ClassLib cl = new ClassLib(new FileInputStream(arg));
		System.out.printf("Minor version number: %d%n", cl.getMinorVer());
		System.out.printf("Major version number: %d%n", cl.getMajorVer());
		cl.showIS();

		try (FileOutputStream fos = new FileOutputStream("x.ser");
			 ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			oos.writeObject(cl);
		}

		cl = null;

		try (FileInputStream fis = new FileInputStream("x.ser");
			 ObjectInputStream ois = new ObjectInputStream(fis)) {
			System.out.println();
			cl = (ClassLib) ois.readObject();
			System.out.printf("Minor version number: %d%n", cl.getMinorVer());
			System.out.printf("Major version number: %d%n", cl.getMajorVer());
			cl.showIS();
		} catch (ClassNotFoundException cnfe) {
			System.err.println(cnfe.getMessage());
		}
	}
}
