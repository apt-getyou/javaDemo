package calc24;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author banhujiu
 * @date 2017/10/18 0018 14:33
 */
public class CalcConf {
	private int total = 24;

	private int max = 1;

	private int min = 13;

	private int num = 4;

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}


	public static void main(String[] args) {
		int sum = 24;
		int min = 1;
		int max = 13;
		int count = 0;
		for (int i = min; i <= max; i++) {
			for (int j = min; j < max; j++) {
				for (int k = min; k < max; k++) {
					for (int l = min; l < max; l++) {
						if (canCalc(sum, i, j, k, l)) {
							count++;
						}
					}
				}
			}
		}
		System.out.println(count);
	}

	private static boolean canCalc(int sum, int i, int j, int k, int l) {
		final Logger logger = LogManager.getLogger();
		if (i + j + k + l == sum) {
			logger.info("{}+{}+{}+{}", i, j, k, l);
			return true;
		}
		return false;
	}


}
