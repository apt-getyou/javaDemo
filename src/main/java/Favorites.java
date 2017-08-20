import java.util.HashMap;
import java.util.Map;

/**
 * @author 刘博文
 * @date 2017/6/4 0004 16:51
 */
public class Favorites {
	private Map<Class<?>, Object> favorites = new HashMap<>();

	public <T> void putFavorite(Class<T> type, T instance) {
		if (type == null) {
			throw new NullPointerException("type is null");
		}
		favorites.put(type, type.cast(instance));
	}

	public <T> T getFavorite(Class<T> type) {
		return type.cast(favorites.get(type));
	}
}
