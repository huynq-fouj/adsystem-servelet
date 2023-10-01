package jsoft.ads.product;

import java.sql.ResultSet;
import java.util.ArrayList;

import org.javatuples.Triplet;

import jsoft.ShareControl;
import jsoft.objects.ProductObject;

public interface Product extends ShareControl{

	public boolean addProduct(ProductObject product);
	public boolean delProduct(ProductObject product);
	public boolean editProduct(ProductObject product);
	
	public ResultSet getProduct(int id);
	public ArrayList<ResultSet> getProducts(Triplet<ProductObject, Short, Byte> infors);
}
