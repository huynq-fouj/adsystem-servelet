package jsoft.ads.article;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.javatuples.Pair;
import org.javatuples.Triplet;

import jsoft.ConnectionPool;
import jsoft.ConnectionPoolImpl;
import jsoft.ads.category.CategoryModel;
import jsoft.objects.ArticleObject;
import jsoft.objects.CategoryObject;

public class ArticleModel {

	private Article a;
	
	public ArticleModel(ConnectionPool cp) {
		this.a = new ArticleImpl(cp);
	}
	
	public ConnectionPool getCP() {
		return this.a.getCP();
	}
	
	public void releaseConnection() {
		this.a.releaseConnection();
	}
	
	public boolean addArticle(ArticleObject item) {
		return this.a.addArticle(item);
	}
	public boolean editArticle(ArticleObject item) {
		return this.a.editArticle(item);
	}
	public boolean delArticle(ArticleObject item) {
		return this.a.delArticle(item);
	}
	
	public ArticleObject getArticle(short id) {
		ArticleObject item = new ArticleObject();
		ResultSet rs = this.a.getArticle(id);
		if(rs != null) {
			try {
				if(rs.next()) {
					item.setArticle_id(rs.getShort("article_id"));
					item.setArticle_title(rs.getString("article_title"));
					item.setCategory_id(rs.getShort("category_id"));
					item.setCategory_name(rs.getString("category_name"));
					item.setSection_name(rs.getString("section_name"));
					item.setSection_id(rs.getShort("section_id"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return item;
	}
	
	public Pair<ArrayList<ArticleObject>, Integer> getArticles(ArticleObject similar, short page, byte total){
		ArrayList<ArticleObject> items = new ArrayList<>();
		int at = (page-1)*total;
		ArrayList<ResultSet> res = this.a.getArticles(new Triplet<>(similar, at, total));
		ArticleObject item = null;
		ResultSet rs = res.get(0);
		if(rs != null) {
			try {
				while(rs.next()) {
					item = new ArticleObject();
					item.setArticle_id(rs.getShort("article_id"));
					item.setArticle_title(rs.getString("article_title"));
					item.setCategory_id(rs.getShort("category_id"));
					item.setCategory_name(rs.getString("category_name"));
					item.setSection_name(rs.getString("section_name"));
					item.setSection_id(rs.getShort("section_id"));
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
		ArticleModel a = new ArticleModel(cp);
		Pair<ArrayList<ArticleObject>, Integer> data = a.getArticles(null, (short) 1, (byte)100);
		ArrayList<ArticleObject> list = data.getValue0();
		list.forEach(item -> {
			System.out.print(list.indexOf(item) + " - ");
			System.out.println(item.getArticle_id() + " - " + item.getArticle_title() + " - Category: " + item.getCategory_id() + ", " + item.getCategory_name() + " - Section: " + item.getSection_id() + ", " + item.getSection_name());
		});
		System.out.println("Tổng số Article: total = " + data.getValue1());
	}
}
