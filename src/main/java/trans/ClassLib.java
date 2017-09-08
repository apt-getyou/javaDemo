package trans;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

/**
 * @author 刘博文
 * @date 2017/9/7 0007 17:46
 */
public class ClassLib implements Serializable {
	private static final long serialVersionUID = 1456840468287000899L;

	private transient InputStream is;

	private int majorVer;
	private int minorVer;

	public ClassLib(InputStream is) throws IOException {
		System.out.println("trans.ClassLib(InputStream) called");
		this.is = is;
		DataInputStream dis;
		if (is instanceof DataInputStream)
			dis = (DataInputStream) is;
		else
			dis = new DataInputStream(is);
		if (dis.readInt() != 0xcafebabe)
			throw new IOException("not a .class file");
		minorVer = dis.readShort();
		majorVer = dis.readShort();
	}

	int getMajorVer() {
		return majorVer;
	}

	int getMinorVer() {
		return minorVer;
	}

	void showIS() {
		System.out.println(is);
	}

}
