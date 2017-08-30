package proxy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author 刘博文
 * @date 2017/8/30 0030 21:43
 */
public class BankImpl implements Bank {
	private List<Account> accounts;
	@Override
	public Collection<Account> getAccounts() {
		return accounts;
	}

	@Override
	public void setAccounts(Collection<Account> accounts) {
		this.accounts = new ArrayList<>();
		this.accounts.addAll(accounts);
	}
}
