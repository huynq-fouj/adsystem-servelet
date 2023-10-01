package jsoft.ads.user;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import jsoft.objects.*;
import jsoft.*;
import jsoft.ads.basic.*;
import jsoft.library.Utilities;

public class UserImpl extends BasicImpl implements User {

	public UserImpl(ConnectionPool cp) {
		super(cp, "User");
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean addUser(UserObject item) {
		// TODO Auto-generated method stub
		if(this.isExisting(item)) {
			return false;
		}
		/**
		 * user_id, user_name, user_pass, user_fullname, user_birthday, 
		 * user_mobilephone, user_homephone, user_officephone, user_email, user_address, 
		 * user_jobarea, user_job, user_position, user_applyyear, user_permission, 
		 * user_notes, user_roles, user_logined, user_created_date, user_last_modified, 
		 * user_last_logined, user_parent_id, user_actions
		 */
		String sql = "INSERT INTO tbluser(";
		sql += "user_name, user_pass, user_fullname, user_birthday, ";
		sql += "user_mobilephone, user_homephone, user_officephone, user_email, user_address, ";
		sql += "user_jobarea, user_job, user_position, user_applyyear, user_permission, ";
		sql += "user_notes, user_roles, user_logined, user_created_date, user_last_modified, ";
		sql += "user_last_logined, user_parent_id, user_actions ";
		sql += ") ";
		sql += "VALUE(?,md5(?),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		//Biên dịch
		try {
			PreparedStatement pre = this.con.prepareStatement(sql);
			pre.setString(1, item.getUser_name());
			pre.setString(2, item.getUser_pass());
			pre.setString(3, item.getUser_fullname());
			pre.setString(4, item.getUser_birthday());
			pre.setString(5, item.getUser_mobilephone());
			pre.setString(6, item.getUser_homephone());
			pre.setString(7, item.getUser_officephone());
			pre.setString(8, item.getUser_email());
			pre.setString(9, item.getUser_address());
			pre.setString(10, item.getUser_jobarea());
			pre.setString(11, item.getUser_job());
			pre.setString(12, item.getUser_position());
			pre.setShort(13, item.getUser_applyyear());
			pre.setByte(14, item.getUser_permission());
			pre.setString(15, item.getUser_notes());
			pre.setString(16, item.getUser_roles());
			pre.setShort(17, item.getUser_logined());
			pre.setString(18, item.getUser_created_date());
			pre.setString(19, item.getUser_last_modified());
			pre.setString(20, item.getUser_last_logined());
			pre.setInt(21, item.getUser_parent_id());
			pre.setByte(22, item.getUser_actions());
			return this.add(pre);	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				this.con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		return false;
	}
	
	private boolean isExisting(UserObject item) {
		boolean flag = false;
		String sql = "SELECT user_id FROM tbluser WHERE user_name='" + item.getUser_name() + "'";
		ResultSet rs = this.gets(sql);
		if(rs!=null) {
			try {
				if(rs.next()) {
					flag = true;
				}
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return flag;
	}

	@Override
	public boolean editUser(UserObject item, USER_EDIT_TYPE et) {
		// TODO Auto-generated method stub
		String sql = "UPDATE tbluser SET ";
		switch(et) {
		case GENERAL:
			sql += "user_fullname=?, user_birthday=?, ";
			sql += "user_mobilephone=?, user_homephone=?, user_officephone=?, user_email=?, user_address=?, ";
			sql += "user_jobarea=?, user_job=?, user_position=?, user_applyyear=?, ";
			sql += "user_notes=?, ";
			sql += "user_last_modified=?, user_actions=? ";
			break;
		case SETTINGS:
			sql += "user_permission=?, user_roles=? ";
			break;
		case PASS:
			sql += "user_pass=md5(?) ";
			break;
		case TRASH:
			sql+="user_deleted=1, user_last_modified=? ";
			break;
		}
		sql += "WHERE user_id=? ";
		
		try {
			PreparedStatement pre = this.con.prepareStatement(sql);
			switch(et) {
			case GENERAL:
				pre.setString(1, item.getUser_fullname());
				pre.setString(2, item.getUser_birthday());
				pre.setString(3, item.getUser_mobilephone());
				pre.setString(4, item.getUser_homephone());
				pre.setString(5, item.getUser_officephone());
				pre.setString(6, item.getUser_email());
				pre.setString(7, item.getUser_address());
				pre.setString(8, item.getUser_jobarea());
				pre.setString(9, item.getUser_job());
				pre.setString(10, item.getUser_position());
				pre.setShort(11, item.getUser_applyyear());
				pre.setString(12, item.getUser_notes());
				pre.setString(13, item.getUser_last_modified());
				pre.setByte(14, item.getUser_actions());
				pre.setInt(15, item.getUser_id());
				break;
			case SETTINGS:
				pre.setByte(1, item.getUser_permission());
				pre.setString(2, item.getUser_roles());
				pre.setInt(3, item.getUser_id());
				break;
			case PASS:
				pre.setString(1, item.getUser_pass());
				pre.setInt(2, item.getUser_id());
				break;
			case TRASH:
				pre.setString(1, item.getUser_last_modified());
				pre.setInt(2, item.getUser_id());
				break;
			}
			
			return this.edit(pre);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				this.con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return false;
	}
	
	@Override
	public boolean delUser(UserObject item) {
		// TODO Auto-generated method stub
		if(!this.isEmpty(item)) {
			return false;
		}
		String sql = "DELETE FROM tbluser WHERE user_id=? ";
		
		try {
			PreparedStatement pre = this.con.prepareStatement(sql);
			pre.setInt(1, item.getUser_id());
			
			return this.del(pre);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				this.con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		return false;
	}
	
	private boolean isEmpty(UserObject item) {
		boolean flag = true;
		
//		String sql1 = "SELECT * FROM tblarticle WHERE article_author_name='"+item.getUser_name()+"' ";
//		String sql2 = "SELECT * FROM tblproduct WHERE product_manager_id=?";
//		String sql3 = "SELECT * FROM tbluser WHERE user_parent_id=?";
//		
//		ResultSet rs1 = this.gets(sql1);
//		ResultSet rs2 = this.get(sql2, item.getUser_id());
//		ResultSet rs3 = this.get(sql3, item.getUser_id());
//		
//		if(rs1!=null || rs2!=null || rs3!=null) {
//			try {
//				if(rs1.next() || rs2.next() || rs3.next()) {
//					flag = false;
//				}
//					rs1.close();
//					rs2.close();
//					rs3.close();
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM tblarticle WHERE article_author_name='"+item.getUser_name()+"'; ");
		sql.append("SELECT * FROM tblproduct WHERE product_manager_id=" + item.getUser_id() + "; ");
		sql.append("SELECT * FROM tbluser WHERE user_parent_id=" + item.getUser_id() + ";");
		
		ArrayList<ResultSet> res = this.getReList(sql.toString());
		for(ResultSet rs:res) {
			try {
				if(rs!=null && rs.next()) {
					flag=false;
					break;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return flag;
	}

	@Override
	public ResultSet getUser(int id) {
		// TODO Auto-generated method stub
		
		String sql = "SELECT * FROM tbluser WHERE (user_id=?) AND (user_deleted=0)";
		
		return this.get(sql, id);
	}

	@Override
	public ResultSet getUser(String username, String userpass) {
		// TODO Auto-generated method stub
		ArrayList<String> sql = new ArrayList<>();
		String sqlSelect = "SELECT * FROM tbluser WHERE (user_name = ?) AND (user_pass = md5(?)) AND (user_deleted=0) ";
		String sqlUpdate = "UPDATE tbluser SET user_logined = user_logined + 1 WHERE (user_name=?) AND (user_pass = md5(?)) AND (user_deleted=0) ";
		sql.add(sqlSelect);
		sql.add(sqlUpdate);
		return this.get(sql, username, userpass);
	}
	
	private String createConditions(UserObject similar) {
		StringBuilder conds = new StringBuilder();
		
		if(similar != null) {
			byte permis = similar.getUser_permission();//Tài khoản đăng nhập truyền cho
			conds.append("(user_permission<=").append(permis).append(")");
			
			if(permis < 4) {
				int id = similar.getUser_id();
				
				if(id > 0) {
					conds.append(" AND ( (user_parent_id=").append(id).append(") OR (user_id=").append(id).append(") ) ");
				}
			}
			//Từ khóa tìm kiếm
			String key = similar.getUser_name();
			if(key != null && !key.equalsIgnoreCase("")) {
				key = Utilities.encode(key);
				conds.append(" AND (");
				conds.append(" (user_name LIKE '%"+key+"%') OR ");
				conds.append(" (user_fullname LIKE '%"+key+"%') OR ");
				conds.append(" (user_address LIKE '%"+key+"%') OR ");
				conds.append(" (user_email LIKE '%"+key+"%') ");
				conds.append(") ");
			}
			
			if(similar.isUser_deleted()) {
				conds.append(" AND (user_deleted=1) ");
			} else {
				conds.append(" AND (user_deleted=0) ");
			}
		}
		
		if(!conds.toString().equalsIgnoreCase("")) {
			conds.insert(0, " WHERE ");
		}
		
		return conds.toString();
	}

	@Override
	public ArrayList<ResultSet> getUsers(UserObject similar, int at, byte total, USER_SORT_TYPE type) {
		// TODO Auto-generated method stub
		
		String sql = "SELECT * FROM tbluser ";
		sql += this.createConditions(similar);
		switch(type) {
		case NAME:
			sql += "ORDER BY user_name ASC ";
			break;
		case FULLNAME:
			sql += "ORDER BY user_fullname ASC ";
			break;
		case ADDRESS:
			sql += "ORDER BY user_address ASC ";
			break;
		case CREATED:
			sql += "ORDER BY user_created_date DESC ";
			break;
		case MODIFIED:
		case UPDATE:
			sql += "ORDER BY user_last_modified DESC ";
			break;
		default:
			sql += "ORDER BY usr_id DESC ";
			break;
		}
		
		sql += "LIMIT " + at + ", " + total + "; ";
		
		StringBuilder multiSelect = new StringBuilder();
		multiSelect.append(sql);
		multiSelect.append("SELECT COUNT(user_id) AS total FROM tbluser ");
		multiSelect.append(this.createConditions(similar));
		multiSelect.append("; ");
		
		return this.getReList(multiSelect.toString());
	}
}
