package com.zinkwork.Atm.service;

import com.zinkwork.Atm.model.Account;

public class Overdraft {
	public static boolean checkOverdraft(Account account, Double newValue) {
		if (account.getOverdraft() >= newValue) {
			return true;
		}
		return false;
	}
}
