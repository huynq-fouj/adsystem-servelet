package jsoft.ads.product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.javatuples.Pair;
import org.javatuples.Triplet;

import jsoft.ConnectionPool;
import jsoft.ads.basic.BasicImpl;
import jsoft.objects.ProductObject;

public class ProductImpl extends BasicImpl implements Product {
	
	public ProductImpl(ConnectionPool cp) {
		super(cp, "Product");
	}

	@Override
	public boolean addProduct(ProductObject product) {
		// TODO Auto-generated method stub
		if(!this.isExisting(product)) {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO tblproduct (");
			sql.append("product_name, product_image, product_price, product_discount_price, ");
			sql.append("product_enable, product_delete, product_visited, product_total, product_manager_id, ");
			sql.append("product_intro, product_notes, product_code, product_created_date, product_modified_date, ");
			sql.append("product_pc_id, product_pg_id, product_ps_id, product_is_detail, product_deleted_date, ");
			sql.append("product_deleted_author, product_promotion_price, product_sold, product_best_seller, ");
			sql.append("product_promotion, product_price_calc_description, product_size, product_name_en, ");
			sql.append("product_customer_id, product_perspective_id");
			sql.append(") VALUES()");
			try {
				PreparedStatement pre = this.con.prepareStatement(sql.toString());
				pre.setString(1, product.getProduct_name());
				pre.setString(2, product.getProduct_image());
				pre.setInt(3, product.getProduct_price());
				pre.setInt(4, product.getProduct_discount_price());
				pre.setBoolean(5, product.isProduct_enable());
				pre.setBoolean(6, product.isProduct_delete());
				pre.setShort(7, product.getProduct_visited());
				pre.setShort(8, product.getProduct_total());
				pre.setInt(9, product.getProduct_manager_id());
				pre.setString(10, product.getProduct_intro());
				pre.setString(11, product.getProduct_notes());
				pre.setString(12, product.getProduct_code());
				pre.setString(13, product.getProduct_created_date());
				pre.setString(14, product.getProduct_modified_date());
				pre.setShort(15, product.getProduct_pc_id());
				pre.setShort(16, product.getProduct_pg_id());
				pre.setShort(17, product.getProduct_ps_id());
				pre.setBoolean(18, product.isProduct_is_detail());
				pre.setString(19, product.getProduct_deleted_date());
				pre.setString(20, product.getProduct_deleted_author());
				pre.setInt(21, product.getProduct_promotion_price());
				pre.setShort(22, product.getProduct_sold());
				pre.setBoolean(23, product.isProduct_best_seller());
				pre.setBoolean(24, product.isProduct_promotion());
				pre.setShort(25, product.getProduct_price_calc_description());
				pre.setString(26, product.getProduct_size());
				pre.setString(27, product.getProduct_name_en());
				pre.setInt(28, product.getProduct_customer_id());
				pre.setBoolean(29, product.isProduct_perspective_id());
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
	
	public boolean isExisting(ProductObject product) {
		boolean flag = false;
		String sql = "SELECT * FROM tblproduct WHERE product_code='" + product.getProduct_code() + "'";
		ResultSet rs = this.get(sql, 0);
		if(rs != null) {
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
	public boolean delProduct(ProductObject product) {
		// TODO Auto-generated method stub
		if(this.isEmpty(product)) {
			String sql = "DELETE FROM tblproduct WHERE product_id=" + product.getProduct_id();
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
	
	public boolean isEmpty(ProductObject product) {
		boolean flag = true;
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM tblcustomer WHERE customer_product_id=" + product.getProduct_id() + "; ");
		sql.append("SELECT * FROM tblfeedback WHERE feedback_product_id=" + product.getProduct_id() + "; ");
		ArrayList<ResultSet> res = this.getReList(sql.toString());
		for(ResultSet rs: res) {
			if(rs!=null) {
				try {
					if(rs.next()) {
						flag = false;
						break;
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return flag;
	}

	@Override
	public boolean editProduct(ProductObject product) {
		// TODO Auto-generated method stub
		/**
		 * product_id, product_name, product_image, product_price, product_discount_price, 
		 * product_enable, product_delete, product_visited, product_total, product_manager_id, 
		 * product_intro, product_notes, product_code, product_created_date, product_modified_date, 
		 * product_pc_id, product_pg_id, product_ps_id, product_is_detail, product_deleted_date, 
		 * product_deleted_author, product_promotion_price, product_sold, product_best_seller, 
		 * product_promotion, product_price_calc_description, product_size, product_name_en, 
		 * product_customer_id, product_perspective_id
		 */
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE tblproduct SET ");
		sql.append("product_name=?, product_image=?, product_price=?, product_discount_price=?, ");
		sql.append("product_enable=?, product_delete=?, product_visited=?, product_total=?, product_manager_id=?, ");
		sql.append("product_intro=?, product_notes=?, product_code=?, product_modified_date=?, ");
		sql.append("product_pc_id=?, product_pg_id=?, product_ps_id=?, product_is_detail=?, product_deleted_date=?, ");
		sql.append("product_deleted_author=?, product_promotion_price=?, product_sold=?, product_best_seller=?, ");
		sql.append("product_promotion=?, product_price_calc_description=?, product_size=?, product_name_en=?, ");
		sql.append("product_customer_id=?, product_perspective_id=? ");
		sql.append("WHERE product_id=?");
		try {
			PreparedStatement pre = this.con.prepareStatement(sql.toString());
			pre.setString(1, product.getProduct_name());
			pre.setString(2, product.getProduct_image());
			pre.setInt(3, product.getProduct_price());
			pre.setInt(4, product.getProduct_discount_price());
			pre.setBoolean(5, product.isProduct_enable());
			pre.setBoolean(6, product.isProduct_delete());
			pre.setShort(7, product.getProduct_visited());
			pre.setShort(8, product.getProduct_total());
			pre.setInt(9, product.getProduct_manager_id());
			pre.setString(10, product.getProduct_intro());
			pre.setString(11, product.getProduct_notes());
			pre.setString(12, product.getProduct_code());
			pre.setString(13, product.getProduct_modified_date());
			pre.setShort(14, product.getProduct_pc_id());
			pre.setShort(15, product.getProduct_pg_id());
			pre.setShort(16, product.getProduct_ps_id());
			pre.setBoolean(17, product.isProduct_is_detail());
			pre.setString(18, product.getProduct_deleted_date());
			pre.setString(19, product.getProduct_deleted_author());
			pre.setInt(20, product.getProduct_promotion_price());
			pre.setShort(21, product.getProduct_sold());
			pre.setBoolean(22, product.isProduct_best_seller());
			pre.setBoolean(23, product.isProduct_promotion());
			pre.setShort(24, product.getProduct_price_calc_description());
			pre.setString(25, product.getProduct_size());
			pre.setString(26, product.getProduct_name_en());
			pre.setInt(27, product.getProduct_customer_id());
			pre.setBoolean(28, product.isProduct_perspective_id());
			pre.setInt(29, product.getProduct_id());
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
	public ResultSet getProduct(int id) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM tblproduct WHERE product_id=" + id;
		return this.get(sql, id);
	}

	@Override
	public ArrayList<ResultSet> getProducts(Triplet<ProductObject, Short, Byte> infors) {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM tblproduct ");
		sql.append("ORDER BY product_id DESC ");
		sql.append("LIMIT " + infors.getValue1() + ", " + infors.getValue2() + "; ");
		sql.append("SELECT COUNT(*) AS total FROM tblproduct");
		return this.getReList(sql.toString());
	}

}
