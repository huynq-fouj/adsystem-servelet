package jsoft.ads.section;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.javatuples.Pair;
import org.javatuples.Quartet;
import org.javatuples.Triplet;

import jsoft.*;
import jsoft.objects.*;

public class SectionModel {

	private Section s;

	public SectionModel(ConnectionPool cp) {
		this.s = new SectionImpl(cp);
	}

	protected void finallize() throws Throwable {
		this.s = null;
	}
	
	public ConnectionPool getCP() {
		return this.s.getCP();
	}
	
	public void releaseConnection() {
		this.s.releaseConnection();
	}

	public boolean addSection(SectionObject item) {
		return this.s.addSection(item);
	}

	public boolean editSection(SectionObject item) {
		return this.s.editSection(item);
	}

	public boolean delSection(SectionObject item) {
		return this.s.delSection(item);
	}

	public SectionObject getSection(int id) {
		SectionObject item = new SectionObject();

		ResultSet rs = this.s.getSection(id);
		if (rs != null) {
			try {
				if (rs.next()) {
					item.setSection_id(rs.getInt("section_id"));
					item.setSection_name(rs.getString("section_name"));
					item.setSection_notes(rs.getString("section_notes"));
					item.setSection_created_date(rs.getString("section_created_date"));
					item.setSection_manager_id(rs.getInt("section_manager_id"));
					item.setSection_enable(rs.getBoolean("section_enable"));
					item.setSection_delete(rs.getBoolean("section_delete"));
					item.setSection_last_modified(rs.getString("section_last_modified"));
					item.setSection_created_author_id(rs.getInt("section_created_author_id"));
					item.setSection_name_en(rs.getString("section_name_en"));
					item.setSection_language(rs.getByte("section_language"));
				}
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return item;
	}

	public Quartet<ArrayList<SectionObject>, Integer, HashMap<Integer, String>, ArrayList<UserObject>> getSections(Quartet<SectionObject, Short, Byte, UserObject> infors) {
		ArrayList<SectionObject> items = new ArrayList<>();
		
		ArrayList<ResultSet> res = this.s.getSections(infors);
		SectionObject item;
		HashMap<Integer, String> managerName = new HashMap<>();
		ResultSet rs = res.get(0);
		if (rs != null) {
			try {
				while (rs.next()) {
					item = new SectionObject();
					item.setSection_id(rs.getInt("section_id"));
					item.setSection_name(rs.getString("section_name"));
					item.setSection_notes(rs.getString("section_notes"));
					item.setSection_created_date(rs.getString("section_created_date"));
					item.setSection_manager_id(rs.getInt("section_manager_id"));
					managerName.put(rs.getInt("user_id"), rs.getString("user_fullname") + " (" + rs.getString("user_name") + ")");
					item.setSection_enable(rs.getBoolean("section_enable"));
					item.setSection_delete(rs.getBoolean("section_delete"));
					item.setSection_last_modified(rs.getString("section_last_modified"));
					item.setSection_created_author_id(rs.getInt("section_created_author_id"));
					item.setSection_name_en(rs.getString("section_name_en"));
					item.setSection_language(rs.getByte("section_language"));
					items.add(item);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		int all = 0;
		
		rs = res.get(1);
		if (rs != null) {
			try {
				if (rs.next()) {
					all = rs.getInt("total");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		rs = res.get(2);
		ArrayList<UserObject> users = new ArrayList<>();
		UserObject user = null;
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
	
		return new Quartet<>(items, all, managerName, users);
	}

	public static void main(String[] args) {
		

	}
}
