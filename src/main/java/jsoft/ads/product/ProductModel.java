package jsoft.ads.product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.javatuples.Pair;
import org.javatuples.Triplet;

import jsoft.ConnectionPool;
import jsoft.ConnectionPoolImpl;
import jsoft.objects.ProductObject;


public class ProductModel {

private Product p;
	
	public ProductModel(ConnectionPool cp) {
		
		this.p = new ProductImpl(cp);
		
	}
	
	public ConnectionPool getCP() {
		return this.p.getCP();
	}
	
	public void releaseConnection() {
		this.p.releaseConnection();
	}
	
	public boolean addProduct(ProductObject item) {
		return this.p.addProduct(item);
	}
	public boolean editProduct(ProductObject item) {
		return this.p.editProduct(item);
	}
	public boolean delProduct(ProductObject item) {
		return this.p.delProduct(item);
	}
	
	public ProductObject getProduct(short id) {
		ProductObject item = new ProductObject();
		ResultSet rs = this.p.getProduct(id);
		if(rs != null) {
			try {
				if(rs.next()) {
					item.setProduct_id(rs.getShort("product_id"));
					item.setProduct_name(rs.getString("product_name"));
					item.setProduct_image(rs.getString("product_image"));
					item.setProduct_price(rs.getInt("product_price"));
					item.setProduct_discount_price(rs.getInt("product_discount_price"));
					item.setProduct_enable(rs.getBoolean("product_enable"));
					item.setProduct_delete(rs.getBoolean("product_delete"));
					item.setProduct_visited(rs.getShort("product_visited"));
					item.setProduct_total(rs.getShort("product_total"));
					item.setProduct_manager_id(rs.getInt("product_manager_id"));
					item.setProduct_intro(rs.getString("product_intro"));
					item.setProduct_notes(rs.getString("product_notes"));
					item.setProduct_code(rs.getString("product_code"));
					item.setProduct_created_date(rs.getString("product_created_date"));
					item.setProduct_modified_date(rs.getString("product_modified_date"));
					item.setProduct_discount_price(rs.getShort("product_pc_id"));
					item.setProduct_pg_id(rs.getShort("product_pg_id"));
					item.setProduct_ps_id(rs.getShort("product_ps_id"));
					item.setProduct_is_detail(rs.getBoolean("product_is_detail"));
					item.setProduct_deleted_date(rs.getString("product_deleted_date"));
					item.setProduct_deleted_author(rs.getString("product_deleted_author"));
					item.setProduct_discount_price(rs.getInt("product_promotion_price"));
					item.setProduct_sold(rs.getShort("product_sold"));
					item.setProduct_best_seller(rs.getBoolean("product_best_seller"));
					item.setProduct_promotion(rs.getBoolean("product_promotion"));
					item.setProduct_price_calc_description(rs.getShort("product_price_calc_description"));
					item.setProduct_size(rs.getString("product_size"));
					item.setProduct_name_en(rs.getString("product_name_en"));
					item.setProduct_customer_id(rs.getInt("product_customer_id"));
					item.setProduct_perspective_id(rs.getBoolean("product_perspective_id"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return item;
	}
	
	public Pair<ArrayList<ProductObject>, Integer> getProducts(ProductObject similar, short page, byte total){
		ArrayList<ProductObject> items = new ArrayList<>();
		int at = (page-1)*total;
		Triplet<ProductObject, Short, Byte> infors = new Triplet<>(similar, page, total);
		ArrayList<ResultSet> res = this.p.getProducts(infors);
		ProductObject item = null;
		ResultSet rs = res.get(0);
		if(rs != null) {
			try {
				while(rs.next()) {
					item = new ProductObject();
					item.setProduct_id(rs.getShort("product_id"));
					item.setProduct_name(rs.getString("product_name"));
					item.setProduct_image(rs.getString("product_image"));
					item.setProduct_price(rs.getInt("product_price"));
					item.setProduct_discount_price(rs.getInt("product_discount_price"));
					item.setProduct_enable(rs.getBoolean("product_enable"));
					item.setProduct_delete(rs.getBoolean("product_delete"));
					item.setProduct_visited(rs.getShort("product_visited"));
					item.setProduct_total(rs.getShort("product_total"));
					item.setProduct_manager_id(rs.getInt("product_manager_id"));
					item.setProduct_intro(rs.getString("product_intro"));
					item.setProduct_notes(rs.getString("product_notes"));
					item.setProduct_code(rs.getString("product_code"));
					item.setProduct_created_date(rs.getString("product_created_date"));
					item.setProduct_modified_date(rs.getString("product_modified_date"));
					item.setProduct_discount_price(rs.getShort("product_pc_id"));
					item.setProduct_pg_id(rs.getShort("product_pg_id"));
					item.setProduct_ps_id(rs.getShort("product_ps_id"));
					item.setProduct_is_detail(rs.getBoolean("product_is_detail"));
					item.setProduct_deleted_date(rs.getString("product_deleted_date"));
					item.setProduct_deleted_author(rs.getString("product_deleted_author"));
					item.setProduct_discount_price(rs.getInt("product_promotion_price"));
					item.setProduct_sold(rs.getShort("product_sold"));
					item.setProduct_best_seller(rs.getBoolean("product_best_seller"));
					item.setProduct_promotion(rs.getBoolean("product_promotion"));
					item.setProduct_price_calc_description(rs.getShort("product_price_calc_description"));
					item.setProduct_size(rs.getString("product_size"));
					item.setProduct_name_en(rs.getString("product_name_en"));
					item.setProduct_customer_id(rs.getInt("product_customer_id"));
					item.setProduct_perspective_id(rs.getBoolean("product_perspective_id"));
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
}
