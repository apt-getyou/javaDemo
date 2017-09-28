package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author banhujiu
 * @date 2017/8/30 0030 21:45
 */
public class BankProxyHandler implements InvocationHandler {
	private Bank bank;

	public BankProxyHandler(Bank bank) {
		this.bank = bank;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		String methodName = method.getName();
		if (methodName.equals("getAccounts")){
			System.out.println("getAccounts method");
			bank.setAccounts(getAccountsFromDatabase());
		} else if (methodName.equals("setAccounts")){
			bank.setAccounts((Collection<Account>) args[0]);
			setAccountsToDatabase(bank.getAccounts());
			return null;
		}
		return null;
	}

	public static void main(String[] args) {
		Bank bank = (Bank) Proxy.newProxyInstance(
				Bank.class.getClassLoader(),
				new Class[]{Bank.class},
				new BankProxyHandler(new BankImpl())
		);
		bank.getAccounts();
	}

	protected Collection<Account> getAccountsFromDatabase(){
		List<Account> accounts = new ArrayList<>();
		return accounts;
	}

	protected void setAccountsToDatabase(Collection<Account> accounts){

	}
}
