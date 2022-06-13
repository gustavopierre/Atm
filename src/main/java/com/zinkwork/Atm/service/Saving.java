package com.zinkwork.Atm.service;

import com.zinkwork.Atm.model.Account;

public class Saving {
	public static boolean checkSaving(Account account, double value) {
		if (account.getBalance() >= value) {
			return true;
		}
		value -= account.getBalance();
		return Overdraft.checkOverdraft(account, value);
	}
}
