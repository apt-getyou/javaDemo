package enumMap;

import java.util.EnumMap;
import java.util.Map;
import java.util.Set;

/**
 * @author 刘博文
 * @date 2017/6/4 0004 17:48
 */
public class Herb {
	public enum Type { ANNUAL , PERENNIAL , BIENNIAL}

	private final String name;

	private final Type  type;

	Herb(String name , Type type ){
		this.name = name;
		this.type = type;
	}

	@Override
	public String toString() {
		return name;
	}

	public static void main(String[] args) {
		Map<Type,Set<Herb>> herbsByType = new EnumMap<>(Herb.Type.class);

	}
}
