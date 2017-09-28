package proxy;

import java.util.Collection;

/**
 * @author banhujiu
 * @date 2017/8/30 0030 21:40
 */
public interface Bank {
	Collection<Account> getAccounts();
	void setAccounts(Collection<Account> accounts);
}
