package jsoft.ads.article;

import java.sql.ResultSet;
import java.util.ArrayList;

import org.javatuples.Triplet;

import jsoft.ShareControl;
import jsoft.objects.*;

public interface Article extends ShareControl{

	public boolean addArticle(ArticleObject item);
	public boolean editArticle(ArticleObject item);
	public boolean delArticle(ArticleObject item);
	
	public ResultSet getArticle(int id);
	public ArrayList<ResultSet> getArticles(Triplet<ArticleObject, Integer, Byte> infors);
	
}
