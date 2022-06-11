package com.zinkwork.Atm.service;

import com.zinkwork.Atm.model.Account;

public class Withdraw {
	public static void updateValue(Account account, double value) {
		if (Saving.checkSaving(account, value)) {
			if (value <= account.getBalance()) {
				account.updateBalance((-1)*value);
			}
			else {
				account.updateOverdraft((-1)*(value - account.getBalance()));
			}
		}
	}
}
