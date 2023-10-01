package jsoft.objects;

public class LogObject {
	private short log_id;
	private String log_name;
	private String log_user_permission;
	private String log_date;
	private String log_user_name;
	private String log_action;
	private String log_position;
	public short getLog_id() {
		return log_id;
	}
	public String getLog_name() {
		return log_name;
	}
	public String getLog_user_permission() {
		return log_user_permission;
	}
	public String getLog_date() {
		return log_date;
	}
	public String getLog_user_name() {
		return log_user_name;
	}
	public String getLog_action() {
		return log_action;
	}
	public String getLog_position() {
		return log_position;
	}
	public void setLog_id(short log_id) {
		this.log_id = log_id;
	}
	public void setLog_name(String log_name) {
		this.log_name = log_name;
	}
	public void setLog_user_permission(String log_user_permission) {
		this.log_user_permission = log_user_permission;
	}
	public void setLog_date(String log_date) {
		this.log_date = log_date;
	}
	public void setLog_user_name(String log_user_name) {
		this.log_user_name = log_user_name;
	}
	public void setLog_action(String log_action) {
		this.log_action = log_action;
	}
	public void setLog_position(String log_position) {
		this.log_position = log_position;
	}
}
