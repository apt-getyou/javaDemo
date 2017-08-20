/**
 * @author 刘博文
 * @date 2017/6/4 0004 17:35
 */
public enum Ensemble {
	SOLO(2), DUET(1);

	private final int numberOfMusicians;

	Ensemble(int size) {
		this.numberOfMusicians = size;
	}

	public int getNumberOfMusicians() {
		return numberOfMusicians;
	}

	public int numberOfmusicians(){
		return numberOfMusicians;
	}


	@Override
	public String toString() {
		return super.toString();
	}

	public static void main(String[] args) {
		Ensemble[] values = Ensemble.values();
		for (Ensemble ensemble : values) {
			System.out.println(ensemble);
		}
	}


}
