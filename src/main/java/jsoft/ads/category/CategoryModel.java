package jsoft.ads.category;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.javatuples.Pair;
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
	
	public Pair<ArrayList<CategoryObject>, Integer> getCategorys(CategoryObject similar, short page, byte total){
		ArrayList<CategoryObject> items = new ArrayList<>();
		int at = (page-1)*total;
		ArrayList<ResultSet> res = this.c.getCategorys(new Triplet<>(similar, at, total));
		CategoryObject item = null;
		ResultSet rs = res.get(0);
		if(rs != null) {
			try {
				while(rs.next()) {
					item = new CategoryObject();
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
		
		return new Pair<>(items, all);
	}
	
	public static void main(String[] args) {
		ConnectionPool cp = new ConnectionPoolImpl();
		CategoryModel cate = new CategoryModel(cp);
		Pair<ArrayList<CategoryObject>, Integer> data = cate.getCategorys(null, (short) 1, (byte)100);
		ArrayList<CategoryObject> list = data.getValue0();
		list.forEach(item -> {
			System.out.print(list.indexOf(item) + " - ");
			System.out.println(item);
		});
		System.out.println("Tổng số Category: total = " + data.getValue1());
	}
}
