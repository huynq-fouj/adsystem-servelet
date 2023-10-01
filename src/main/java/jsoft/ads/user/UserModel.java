package jsoft.ads.user;

import jsoft.*;
import jsoft.objects.*;
import java.util.*;

import org.javatuples.Pair;

import java.sql.*;

public class UserModel {
	private User u;

	public UserModel(ConnectionPool cp) {
		this.u = new UserImpl(cp);
	}

	protected void finallize() throws Throwable {
		this.u = null;
	}
	
	public ConnectionPool getCP() {
		return this.u.getCP();
	}
	
	public void releaseConnection() {
		this.u.releaseConnection();
	}

	// *********************************************************************************
	public boolean addUser(UserObject item) {
		return this.u.addUser(item);
	}

	public boolean editUser(UserObject item, USER_EDIT_TYPE et) {
		return this.u.editUser(item, et);
	}

	public boolean delUser(UserObject item) {
		return this.u.delUser(item);
	}

	// **********************************************************************************
	public UserObject getUserObject(int id) {
		UserObject item = null;

		// Lấy bản ghi
		ResultSet rs = this.u.getUser(id);
		if (rs != null) {
			try {
				if (rs.next()) {
					item = new UserObject();
					item.setUser_id(rs.getInt("user_id"));
					item.setUser_name(rs.getString("user_name"));
					item.setUser_fullname(rs.getString("user_fullname"));
					item.setUser_email(rs.getString("user_email"));
					item.setUser_address(rs.getString("user_address"));
					item.setUser_homephone(rs.getString("user_homephone"));
					item.setUser_officephone(rs.getString("user_officephone"));
					item.setUser_mobilephone(rs.getString("user_mobilephone"));
					item.setUser_permission(rs.getByte("user_permission"));
					item.setUser_parent_id(rs.getInt("user_parent_id"));
					item.setUser_job(rs.getString("user_job"));
					item.setUser_jobarea(rs.getString("user_jobarea"));
					item.setUser_logined(rs.getShort("user_logined"));
					item.setUser_birthday(rs.getString("user_birthday"));
					item.setUser_notes(rs.getString("user_notes"));
					item.setUser_deleted(rs.getBoolean("user_deleted"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return item;
	}

	// phuong thuc dang nhap
	public UserObject getUserObject(String username, String userpass) {
		UserObject item = null;

		// Lấy bản ghi
		ResultSet rs = this.u.getUser(username, userpass);
		if (rs != null) {
			try {
				if (rs.next()) {
					item = new UserObject();
					item.setUser_id(rs.getInt("user_id"));
					item.setUser_name(rs.getString("user_name"));
					item.setUser_fullname(rs.getString("user_fullname"));
					item.setUser_email(rs.getString("user_email"));
					item.setUser_address(rs.getString("user_address"));
					item.setUser_homephone(rs.getString("user_homephone"));
					item.setUser_officephone(rs.getString("user_officephone"));
					item.setUser_mobilephone(rs.getString("user_mobilephone"));
					item.setUser_permission(rs.getByte("user_permission"));
					item.setUser_parent_id(rs.getInt("user_parent_id"));
					item.setUser_logined(rs.getShort("user_logined"));
					item.setUser_deleted(rs.getBoolean("user_deleted"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return item;
	}

	public Pair<ArrayList<UserObject>, Integer> getUserObjects(UserObject similar, short page, byte total, USER_SORT_TYPE type) {
		ArrayList<UserObject> items = new ArrayList<>();

		UserObject item = null;

		// Lấy danh sách bản ghi
		int at = (page - 1) * total;
		ArrayList<ResultSet> res = this.u.getUsers(similar, at, total, type);
		
		ResultSet rs = res.get(0);
		if (rs != null) {
			try {
				while (rs.next()) {
					item = new UserObject();
					item.setUser_id(rs.getInt("user_id"));
					item.setUser_name(rs.getString("user_name"));
					item.setUser_fullname(rs.getString("user_fullname"));
					item.setUser_email(rs.getString("user_email"));
					item.setUser_address(rs.getString("user_address"));
					item.setUser_homephone(rs.getString("user_homephone"));
					item.setUser_officephone(rs.getString("user_officephone"));
					item.setUser_mobilephone(rs.getString("user_mobilephone"));
					item.setUser_permission(rs.getByte("user_permission"));
					item.setUser_parent_id(rs.getInt("user_parent_id"));
					item.setUser_last_modified(rs.getString("user_last_modified"));
					item.setUser_deleted(rs.getBoolean("user_deleted"));
					item.setUser_logined(rs.getShort("user_logined"));
					// 
					items.add(item);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		int all = 0;
		rs = res.get(1);
		if(rs!=null) {
			try {
				if(rs.next()) {
					all = rs.getInt("total");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return new Pair<>(items, all);
	}
}
