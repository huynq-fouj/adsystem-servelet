package jsoft.ads.product;

import java.util.ArrayList;

import org.javatuples.Pair;
import org.javatuples.Triplet;

import jsoft.ConnectionPool;
import jsoft.objects.ProductObject;

public class ProductControl {
	
	private ProductModel pm;
	
	public ProductControl(ConnectionPool cp) {
		this.pm = new ProductModel(cp);
	}
	
	public ConnectionPool getCP() {
		return this.pm.getCP();
	}
	
	public void releaseConnection() {
		this.pm.releaseConnection();
	}
	
	public boolean addProduct(ProductObject c) {
		return this.pm.addProduct(c);
	}
	
	public boolean delProduct(ProductObject c) {
		return this.pm.delProduct(c);
	}
	
	public boolean editProduct(ProductObject c) {
		return this.pm.editProduct(c);
	}
	
	public ArrayList<String> viewProduct(Triplet<ProductObject, Short, Byte> infors){
		Pair<ArrayList<ProductObject>, Integer> datas = this.pm.getProducts(infors.getValue0(), infors.getValue1(), infors.getValue2());
		return ProductLibrary.viewProducts(datas);
	}
	public static void main(String[] args) {
		ProductControl pc = new ProductControl(null);
		ArrayList<String> view = pc.viewProduct(new Triplet<>(null, (short) 1, (byte) 100));
		pc.releaseConnection();
		System.out.println(view);
	}
}
