package com.benclaus.koperasi.model;

public enum BookEnum {
	PROFIT_LOSS(1), BALANCE_SHEET(2), CASH_FLOW(3);
	
	private int code;
	private BookEnum(int code) {
		this.code = code;
	}
	public int getCode() {
		return code;
	}

}
