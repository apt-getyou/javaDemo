package proxy;

import java.util.Collection;

/**
 * @author 刘博文
 * @date 2017/8/30 0030 21:40
 */
public interface Bank {
	Collection<Account> getAccounts();
	void setAccounts(Collection<Account> accounts);
}
