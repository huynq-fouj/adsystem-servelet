package jsoft.ads.category;

import java.sql.ResultSet;
import java.util.ArrayList;

import org.javatuples.Quartet;

import jsoft.ShareControl;
import jsoft.objects.*;

public interface Category extends ShareControl{

	public boolean addCategory(CategoryObject item);
	public boolean editCategory(CategoryObject item);
	public boolean delCategory(CategoryObject item);
	
	public ResultSet getCategory(Short id);
	public ArrayList<ResultSet> getCategories(Quartet<CategoryObject, Short, Byte, UserObject> infors);	
}
