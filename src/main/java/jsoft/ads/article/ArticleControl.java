package jsoft.ads.article;

import java.util.ArrayList;

import org.javatuples.Pair;
import org.javatuples.Triplet;

import jsoft.ConnectionPool;
import jsoft.objects.ArticleObject;

public class ArticleControl {
	
	private ArticleModel am;

	public ArticleControl(ConnectionPool cp) {
		this.am = new ArticleModel(cp);
	}
	
	public ConnectionPool getCP() {
		return this.am.getCP();
	}
	
	public void releaseConnection() {
		this.am.releaseConnection();
	}
	
	public boolean addArticle(ArticleObject a) {
		return am.addArticle(a);
	}
	
	public boolean delArticle(ArticleObject a) {
		return am.delArticle(a);
	}
	
	public boolean editArticle(ArticleObject a) {
		return am.editArticle(a);
	}
	
	public ArrayList<String> viewArticle(Triplet<ArticleObject, Short, Byte> infors){
		Pair<ArrayList<ArticleObject>, Integer> datas = am.getArticles(infors.getValue0(), infors.getValue1(), infors.getValue2());
		return ArticleLibrary.viewArticles(datas);
	}
	
	public static void main(String[] args) {
		ArticleControl ac = new ArticleControl(null);
		ArrayList<String> view = ac.viewArticle(new Triplet<>(null, (short)1, (byte)100));
		ac.releaseConnection();
		System.out.println(view);
	}
	
}
