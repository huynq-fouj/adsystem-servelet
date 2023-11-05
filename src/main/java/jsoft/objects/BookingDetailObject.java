package jsoft.objects;

public class BookingDetailObject {

	private int bkd_id;
	private int bkd_room_id;
	private String bkd_customer_name;
	private String bkd_customer_email;
	private String bkd_customer_phone;
	private String bkd_start_date;
	private String bkd_end_date;
	private byte bkd_number_adult;
	private byte bkd_number_children;
	private short bkd_number_room;
	private int bkd_static;
	private String bkd_notes;

	public int getBkd_id() {
		return bkd_id;
	}

	public int getBkd_room_id() {
		return bkd_room_id;
	}

	public String getBkd_customer_name() {
		return bkd_customer_name;
	}

	public String getBkd_customer_email() {
		return bkd_customer_email;
	}

	public String getBkd_customer_phone() {
		return bkd_customer_phone;
	}

	public String getBkd_start_date() {
		return bkd_start_date;
	}

	public String getBkd_end_date() {
		return bkd_end_date;
	}

	public byte getBkd_number_adult() {
		return bkd_number_adult;
	}

	public byte getBkd_number_children() {
		return bkd_number_children;
	}

	public short getBkd_number_room() {
		return bkd_number_room;
	}

	public int getBkd_static() {
		return bkd_static;
	}

	public String getBkd_notes() {
		return bkd_notes;
	}

	public void setBkd_id(int bkd_id) {
		this.bkd_id = bkd_id;
	}

	public void setBkd_room_id(int bkd_room_id) {
		this.bkd_room_id = bkd_room_id;
	}

	public void setBkd_customer_name(String bkd_customer_name) {
		this.bkd_customer_name = bkd_customer_name;
	}

	public void setBkd_customer_email(String bkd_customer_email) {
		this.bkd_customer_email = bkd_customer_email;
	}

	public void setBkd_customer_phone(String bkd_customer_phone) {
		this.bkd_customer_phone = bkd_customer_phone;
	}

	public void setBkd_start_date(String bkd_start_date) {
		this.bkd_start_date = bkd_start_date;
	}

	public void setBkd_end_date(String bkd_end_date) {
		this.bkd_end_date = bkd_end_date;
	}

	public void setBkd_number_adult(byte bkd_number_adult) {
		this.bkd_number_adult = bkd_number_adult;
	}

	public void setBkd_number_children(byte bkd_number_children) {
		this.bkd_number_children = bkd_number_children;
	}

	public void setBkd_number_room(short bkd_number_room) {
		this.bkd_number_room = bkd_number_room;
	}

	public void setBkd_static(int bkd_static) {
		this.bkd_static = bkd_static;
	}

	public void setBkd_notes(String bkd_notes) {
		this.bkd_notes = bkd_notes;
	}

}
