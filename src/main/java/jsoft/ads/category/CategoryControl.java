package jsoft.ads.category;

import java.util.ArrayList;

import org.javatuples.Pair;
import org.javatuples.Triplet;

import jsoft.ConnectionPool;
import jsoft.objects.CategoryObject;

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
	
	public ArrayList<String> viewCategory(Triplet<CategoryObject, Short, Byte> infors){
		Pair<ArrayList<CategoryObject>, Integer> datas = this.cm.getCategorys(infors.getValue0(), infors.getValue1(), infors.getValue2());
		return CategoryLibrary.viewCategories(datas);
	}
	public static void main(String[] args) {
//		CategoryControl c = new CategoryControl(null);
//		ArrayList<String> view = c.viewCategory(new Triplet<>(null, (short) 1, (byte) 100));
//		c.releaseConnection();
//		System.out.println(view);
	}
	
}
