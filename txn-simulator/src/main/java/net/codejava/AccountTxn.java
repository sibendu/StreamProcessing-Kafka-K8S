package net.codejava;

import java.sql.Timestamp;

public class AccountTxn {
	private int id;
	private String account;
	private String type;
	private Timestamp  createdDate;
	

	protected AccountTxn() {
	}

	protected AccountTxn(String account, String type, Timestamp  createdDate) {
		this.account = account;
		this.type = type;
		this.createdDate = createdDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Timestamp  getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp  createdDate) {
		this.createdDate = createdDate;
	}
	
}
