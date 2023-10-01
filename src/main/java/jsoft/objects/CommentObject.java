package jsoft.objects;

public class CommentObject {
	private short comment_id;
	private String comment_created_date;
	private String comment_title;
	private String comment_content;
	private String comment_enabled;
	private short comment_type_id;
	private String comment_user_phone;
	private String comment_user_fullname;
	private String comment_user_email;
	private String comment_content_intro;
	private String comment_notes;
	private String comment_for_name;
	private short comment_for_id;
	public short getComment_id() {
		return comment_id;
	}
	public String getComment_created_date() {
		return comment_created_date;
	}
	public String getComment_title() {
		return comment_title;
	}
	public String getComment_content() {
		return comment_content;
	}
	public String getComment_enabled() {
		return comment_enabled;
	}
	public short getComment_type_id() {
		return comment_type_id;
	}
	public String getComment_user_phone() {
		return comment_user_phone;
	}
	public String getComment_user_fullname() {
		return comment_user_fullname;
	}
	public String getComment_user_email() {
		return comment_user_email;
	}
	public String getComment_content_intro() {
		return comment_content_intro;
	}
	public String getComment_notes() {
		return comment_notes;
	}
	public String getComment_for_name() {
		return comment_for_name;
	}
	public short getComment_for_id() {
		return comment_for_id;
	}
	public void setComment_id(short comment_id) {
		this.comment_id = comment_id;
	}
	public void setComment_created_date(String comment_created_date) {
		this.comment_created_date = comment_created_date;
	}
	public void setComment_title(String comment_title) {
		this.comment_title = comment_title;
	}
	public void setComment_content(String comment_content) {
		this.comment_content = comment_content;
	}
	public void setComment_enabled(String comment_enabled) {
		this.comment_enabled = comment_enabled;
	}
	public void setComment_type_id(short comment_type_id) {
		this.comment_type_id = comment_type_id;
	}
	public void setComment_user_phone(String comment_user_phone) {
		this.comment_user_phone = comment_user_phone;
	}
	public void setComment_user_fullname(String comment_user_fullname) {
		this.comment_user_fullname = comment_user_fullname;
	}
	public void setComment_user_email(String comment_user_email) {
		this.comment_user_email = comment_user_email;
	}
	public void setComment_content_intro(String comment_content_intro) {
		this.comment_content_intro = comment_content_intro;
	}
	public void setComment_notes(String comment_notes) {
		this.comment_notes = comment_notes;
	}
	public void setComment_for_name(String comment_for_name) {
		this.comment_for_name = comment_for_name;
	}
	public void setComment_for_id(short comment_for_id) {
		this.comment_for_id = comment_for_id;
	}
}
