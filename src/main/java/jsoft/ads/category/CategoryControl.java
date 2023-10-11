package jsoft.ads.category;

import java.util.ArrayList;
import java.util.HashMap;

import org.javatuples.Pair;
import org.javatuples.Quartet;
import org.javatuples.Quintet;
import org.javatuples.Triplet;

import jsoft.ConnectionPool;
import jsoft.objects.CategoryObject;
import jsoft.objects.SectionObject;
import jsoft.objects.UserObject;

public class CategoryControl {

	private CategoryModel cm;
	
	public CategoryControl(ConnectionPool cp) {
		this.cm = new CategoryModel(cp);
	}
	
	public ConnectionPool getCP() {
		return this.cm.getCP();
	}
	
	public void releaseConnection() {
		this.cm.releaseConnection();
	}
	
	public boolean addCategory(CategoryObject c) {
		return this.cm.addCategory(c);
	}
	
	public boolean delCategory(CategoryObject c) {
		return this.cm.delCategory(c);
	}
	
	public boolean editCategory(CategoryObject c) {
		return this.cm.editCategory(c);
	}
	
	public ArrayList<String> viewCategory(Quartet<CategoryObject, Short, Byte, UserObject> infors){
		Quintet<ArrayList<CategoryObject>, 
		ArrayList<UserObject>, 
		ArrayList<SectionObject>, 
		Integer, 
		HashMap<Integer, String>> datas = this.cm.getCategories(infors);
		UserObject user = infors.getValue3();
		return CategoryLibrary.viewCategories(datas, user);
	}
	public static void main(String[] args) {
//		CategoryControl c = new CategoryControl(null);
//		ArrayList<String> view = c.viewCategory(new Triplet<>(null, (short) 1, (byte) 100));
//		c.releaseConnection();
//		System.out.println(view);
	}
	
}
