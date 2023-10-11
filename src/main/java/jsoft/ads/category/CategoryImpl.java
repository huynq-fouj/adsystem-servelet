package jsoft.ads.category;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.javatuples.Quartet;
import org.javatuples.Triplet;

import jsoft.ConnectionPool;
import jsoft.ConnectionPoolImpl;
import jsoft.ads.basic.BasicImpl;
import jsoft.objects.CategoryObject;
import jsoft.objects.UserObject;

public class CategoryImpl extends BasicImpl implements Category {
	
	

	public CategoryImpl(ConnectionPool cp) {
		super(cp, "Category");
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean addCategory(CategoryObject item) {
		// TODO Auto-generated method stub
		if(!this.isExists(item)) {
			String sql = "INSERT INTO tblcategory (";
			sql += "category_name, category_section_id, category_notes, ";
			sql += "category_created_date, category_created_author_id, category_last_modified, ";
			sql += "category_manager_id, category_enable, category_delete, category_image, ";
			sql += "category_name_en, category_language) ";
			sql += "VALUE(?,?,?,?,?,?,?,?,?,?,?,?)";
			
			try {
				PreparedStatement pre = this.con.prepareStatement(sql);
				pre.setString(1, item.getCategory_name());
				pre.setShort(2, item.getCategory_section_id());
				pre.setString(3, item.getCategory_notes());
				pre.setString(4, item.getCategory_created_date());
				pre.setInt(5, item.getCategory_created_author_id());
				pre.setString(6, item.getCategory_last_modified());
				pre.setInt(7, item.getCategory_manager_id());
				pre.setBoolean(8, item.isCategory_enable());
				pre.setBoolean(9, item.isCategory_delete());
				pre.setString(10, item.getCategory_image());
				pre.setString(11, item.getCategory_name_en());
				pre.setByte(12, item.getCategory_language());
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
		}
		return false;
	}
	
	public boolean isExists(CategoryObject item) {
		boolean flag = false;
		String sql = "SELECT * FROM tblcategory WHERE category_name='"+ item.getCategory_name() + "'";
		ResultSet rs = this.gets(sql);
		if(rs!=null) {
			try {
				if(rs.next()) {
					flag = true;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return flag;
		
	}

	@Override
	public boolean editCategory(CategoryObject item) {
		// TODO Auto-generated method stub
		String sql = "UPDATE tblcategory SET ";
		sql += "category_name=?, category_section_id=?, category_notes=?, ";
		sql += "category_created_author_id=?, category_last_modified=?, ";
		sql += "category_manager_id=?, category_enable=?, category_delete=?, category_image=?, ";
		sql += "category_name_en=?, category_language=? ";
		sql += "WHERE category_id=?";
		try {
			PreparedStatement pre = this.con.prepareStatement(sql);
			pre.setString(1, item.getCategory_name());
			pre.setShort(2, item.getCategory_section_id());
			pre.setString(3, item.getCategory_notes());
			pre.setInt(4, item.getCategory_created_author_id());
			pre.setString(5, item.getCategory_last_modified());
			pre.setInt(6, item.getCategory_manager_id());
			pre.setBoolean(7, item.isCategory_enable());
			pre.setBoolean(8, item.isCategory_delete());
			pre.setString(9, item.getCategory_image());
			pre.setString(10, item.getCategory_name_en());
			pre.setByte(11, item.getCategory_language());
			pre.setShort(12, item.getCategory_id());
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
	public boolean delCategory(CategoryObject item) {
		// TODO Auto-generated method stub
		if(this.isEmpty(item)) {
			String sql = "DELETE FROM tblcategory WHERE category_id=" +  item.getCategory_id();
			try {
				PreparedStatement pre = this.con.prepareStatement(sql);
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
		}
		return false;
	}
	
	public boolean isEmpty(CategoryObject item) {
		boolean flag = true;
		String sql = "SELECT * FROM tblarticle WHERE article_category_id=?";
		ResultSet rs = this.get(sql, item.getCategory_id());
		if(rs != null) {
			try {
				if(rs.next()) {
					flag = false;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return flag;
	}

	@Override
	public ResultSet getCategory(Short id) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM tblcategory JOIN tblsection ON tblcategory.category_section_id=tblsection.section_id WHERE category_id=?";
		
		return this.get(sql, id);
	}

	@Override
	public ArrayList<ResultSet> getCategories(Quartet<CategoryObject, Short, Byte, UserObject> infors) {
		// TODO Auto-generated method stub
		UserObject user = infors.getValue3();
		byte total = infors.getValue2();
		int at = (infors.getValue1() - 1) * total;
		CategoryObject similar = infors.getValue0();
		String sql = "SELECT * FROM tblcategory JOIN tblsection ON tblcategory.category_section_id=tblsection.section_id ";
		sql += "LEFT JOIN tbluser ON tblcategory.category_manager_id = tbluser.user_id ";
		sql += "ORDER BY category_id ASC ";
		sql += "LIMIT " + at  + ", " + total + "; ";
		StringBuilder sql1 = new StringBuilder();
		sql1.append(sql);
		sql1.append("SELECT COUNT(*) AS total FROM tblcategory;");
		//Danh sách người sử dụng sẽ được cấp quyền quản lý
		sql1.append("SELECT * FROM tbluser WHERE ");
		sql1.append("((user_permission<"+user.getUser_permission()+") AND (");
		sql1.append("user_parent_id="+user.getUser_id()+")) OR (user_id="+user.getUser_id()+")");
		sql1.append("; ");
		sql1.append("SELECT * FROM tblsection;");
		return this.getReList(sql1.toString());
	}


}
