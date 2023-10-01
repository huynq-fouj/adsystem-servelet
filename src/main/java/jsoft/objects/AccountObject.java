package jsoft.objects;

public class AccountObject {
	
	private short account_id; 
	private short account_customer_id; 
	private int account_money; 
	private String account_actived_date; 
	private String account_notes; 
	private int account_current_money; 
	private byte account_type;
	public short getAccount_id() {
		return account_id;
	}
	public short getAccount_customer_id() {
		return account_customer_id;
	}
	public int getAccount_money() {
		return account_money;
	}
	public String getAccount_actived_date() {
		return account_actived_date;
	}
	public String getAccount_notes() {
		return account_notes;
	}
	public int getAccount_current_money() {
		return account_current_money;
	}
	public byte getAccount_type() {
		return account_type;
	}
	public void setAccount_id(short account_id) {
		this.account_id = account_id;
	}
	public void setAccount_customer_id(short account_customer_id) {
		this.account_customer_id = account_customer_id;
	}
	public void setAccount_money(int account_money) {
		this.account_money = account_money;
	}
	public void setAccount_actived_date(String account_actived_date) {
		this.account_actived_date = account_actived_date;
	}
	public void setAccount_notes(String account_notes) {
		this.account_notes = account_notes;
	}
	public void setAccount_current_money(int account_current_money) {
		this.account_current_money = account_current_money;
	}
	public void setAccount_type(byte account_type) {
		this.account_type = account_type;
	}
	
}
