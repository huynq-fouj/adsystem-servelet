package jsoft.ads.category;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import org.javatuples.Pair;
import org.javatuples.Quartet;
import org.javatuples.Quintet;
import org.javatuples.Sextet;
import org.javatuples.Triplet;

import jsoft.*;
import jsoft.objects.*;

public class CategoryModel {

	private Category c;
	
	public CategoryModel(ConnectionPool cp) {
		
		this.c = new CategoryImpl(cp);
		
	}
	
	public ConnectionPool getCP() {
		return this.c.getCP();
	}
	
	public void releaseConnection() {
		this.c.releaseConnection();
	}
	
	public boolean addCategory(CategoryObject item) {
		return this.c.addCategory(item);
	}
	public boolean editCategory(CategoryObject item) {
		return this.c.editCategory(item);
	}
	public boolean delCategory(CategoryObject item) {
		return this.c.delCategory(item);
	}
	
	public CategoryObject getCategory(short id) {
		CategoryObject item = new CategoryObject();
		ResultSet rs = this.c.getCategory(id);
		if(rs != null) {
			try {
				if(rs.next()) {
					item.setCategory_id(rs.getShort("category_id"));
					item.setCategory_name(rs.getString("category_name"));
					item.setCategory_section_id(rs.getShort("category_section_id"));
					item.setCategory_notes(rs.getString("category_notes"));
					item.setCategory_created_date(rs.getString("category_created_date"));
					item.setCategory_created_author_id(rs.getInt("category_created_author_id"));
					item.setCategory_last_modified(rs.getString("category_last_modified"));
					item.setCategory_manager_id(rs.getInt("category_manager_id"));
					item.setCategory_enable(rs.getBoolean("category_enable"));
					item.setCategory_delete(rs.getBoolean("category_delete"));
					item.setCategory_image(rs.getString("category_image"));
					item.setCategory_name_en(rs.getString("category_name_en"));
					item.setCategory_language(rs.getByte("category_language"));
					item.setSection_name(rs.getString("section_name"));
					item.setCategory_section_id(rs.getShort("section_id"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return item;
	}
	
	public Quintet<ArrayList<CategoryObject>,
		ArrayList<UserObject>, 
		ArrayList<SectionObject>, 
		Integer, 
		HashMap<Integer, String>> getCategories(Quartet<CategoryObject, Short, Byte, UserObject> infors){
		ArrayList<CategoryObject> items = new ArrayList<>();
		ArrayList<ResultSet> res = this.c.getCategories(infors);
		CategoryObject item = null;
		HashMap<Integer, String> managerName = new HashMap<>();
		ResultSet rs = res.get(0);
		if(rs != null) {
			try {
				while(rs.next()) {
					item = new CategoryObject();
					item.setCategory_id(rs.getShort("category_id"));
					item.setCategory_name(rs.getString("category_name"));
					item.setCategory_notes(rs.getString("category_notes"));
					item.setCategory_created_date(rs.getString("category_created_date"));
					item.setCategory_created_author_id(rs.getInt("category_created_author_id"));
					item.setCategory_last_modified(rs.getString("category_last_modified"));
					item.setCategory_manager_id(rs.getInt("category_manager_id"));
					item.setCategory_enable(rs.getBoolean("category_enable"));
					item.setCategory_delete(rs.getBoolean("category_delete"));
					item.setCategory_image(rs.getString("category_image"));
					item.setCategory_name_en(rs.getString("category_name_en"));
					item.setCategory_language(rs.getByte("category_language"));
					
					item.setCategory_section_id(rs.getShort("section_id"));
					item.setSection_name(rs.getString("section_name"));
					
					managerName.put(rs.getInt("user_id"), rs.getString("user_fullname"));
					
					items.add(item);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		int all = 0;
		rs = res.get(1);
		if(rs != null) {
			try {
				if(rs.next()) {
					all = rs.getInt("total");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		ArrayList<UserObject> users = new ArrayList<>();
		UserObject user = null;
		rs = res.get(2);
		if(rs != null) {
			try {
				while(rs.next()) {
					user = new UserObject();
					user.setUser_id(rs.getInt("user_id"));
					user.setUser_name(rs.getString("user_name"));
					user.setUser_fullname(rs.getString("user_fullname"));
					users.add(user);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		ArrayList<SectionObject> sections = new ArrayList<>();
		SectionObject section = null;
		rs = res.get(3);
		if(rs != null) {
			try {
				while(rs.next()) {
					section = new SectionObject();
					section.setSection_id(rs.getInt("section_id"));
					section.setSection_name(rs.getString("section_name"));
					sections.add(section);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return new Quintet<>(items, users, sections, all, managerName);
	}
}
