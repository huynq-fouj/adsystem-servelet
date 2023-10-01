package jsoft.ads.article;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.javatuples.Triplet;

import jsoft.ConnectionPool;
import jsoft.ads.basic.BasicImpl;
import jsoft.objects.ArticleObject;

public class ArticleImpl extends BasicImpl implements Article {
	
	public ArticleImpl(ConnectionPool cp) {
		super(cp, "Article");
	}

	@Override
	public boolean addArticle(ArticleObject item) {
		// TODO Auto-generated method stub
		if(!this.isExisting(item)) {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO tblarticle (");
			sql.append("article_title, article_summary, article_content, article_created_date,");
			sql.append("article_last_modified, article_image, article_category_id, article_section_id,");
			sql.append("article_visited, article_author_name, article_enable, article_url_link, ");
			sql.append("article_tag, article_title_en, article_summary_en, article_content_en, article_tag_en, ");
			sql.append("article_fee, article_isfee, article_delete, article_deleted_date, article_restored_date, ");
			sql.append("article_modified_author_name, article_author_permission, article_source, ");
			sql.append("article_language, article_focus, article_type, article_forhome) ");
			sql.append("VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			try {
				PreparedStatement pre = this.con.prepareStatement(sql.toString());
				pre.setString(1, item.getArticle_title());
				pre.setString(2, item.getArticle_summary());
				pre.setString(3, item.getArticle_content());
				pre.setString(4, item.getArticle_created_date());
				pre.setString(5, item.getArticle_last_modified());
				pre.setString(6, item.getArticle_image());
				pre.setShort(7, item.getArticle_category_id());
				pre.setShort(8, item.getArticle_section_id());
				pre.setShort(9, item.getArticle_visited());
				pre.setString(10, item.getArticle_author_name());
				pre.setBoolean(11, item.isArticle_enable());
				pre.setString(12, item.getArticle_url_link());
				pre.setString(13, item.getArticle_tag());
				pre.setString(14, item.getArticle_title_en());
				pre.setString(15, item.getArticle_summary_en());
				pre.setString(16, item.getArticle_content_en());
				pre.setString(17, item.getArticle_tag_en());
				pre.setInt(18, item.getArticle_fee());
				pre.setBoolean(19, item.isArticle_isfee());
				pre.setBoolean(20, item.isArticle_delete());
				pre.setString(21, item.getArticle_deleted_date());
				pre.setString(22, item.getArticle_restored_date());
				pre.setString(23, item.getArticle_modified_author_name());
				pre.setByte(24, item.getArticle_author_permission());
				pre.setString(25, item.getArticle_source());
				pre.setByte(26, item.getArticle_language());
				pre.setBoolean(27, item.isArticle_focus());
				pre.setByte(28, item.getArticle_type());
				pre.setBoolean(29, item.isArticle_forhome());
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
	
	public boolean isExisting(ArticleObject item) {
		boolean flag = false;
		
		return flag;
	}

	@Override
	public boolean editArticle(ArticleObject item) {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE tblarticle SET ");
		sql.append("article_title=?, article_summary=?, article_content=?, ");
		sql.append("article_last_modified=?, article_image=?, article_category_id=?, article_section_id=?, ");
		sql.append("article_visited=?, article_author_name=?, article_enable=?, article_url_link=?, ");
		sql.append("article_tag=?, article_title_en=?, article_summary_en=?, article_content_en=?, article_tag_en=?, ");
		sql.append("article_fee=?, article_isfee=?, article_delete=?, article_deleted_date=?, article_restored_date=?, ");
		sql.append("article_modified_author_name=?, article_author_permission=?, article_source=?, ");
		sql.append("article_language=?, article_focus=?, article_type=?, article_forhome=? ");
		sql.append("WHERE article_id=?");
		try {
			PreparedStatement pre = this.con.prepareStatement(sql.toString());
			pre.setString(1, item.getArticle_summary());
			pre.setString(2, item.getArticle_content());
			pre.setString(3, item.getArticle_last_modified());
			pre.setString(4, item.getArticle_image());
			pre.setShort(5, item.getArticle_category_id());
			pre.setShort(6, item.getArticle_section_id());
			pre.setShort(7, item.getArticle_visited());
			pre.setString(8, item.getArticle_author_name());
			pre.setBoolean(9, item.isArticle_enable());
			pre.setString(10, item.getArticle_url_link());
			pre.setString(11, item.getArticle_tag());
			pre.setString(12, item.getArticle_title_en());
			pre.setString(13, item.getArticle_summary_en());
			pre.setString(14, item.getArticle_content_en());
			pre.setString(15, item.getArticle_tag_en());
			pre.setInt(16, item.getArticle_fee());
			pre.setBoolean(17, item.isArticle_isfee());
			pre.setBoolean(18, item.isArticle_delete());
			pre.setString(19, item.getArticle_deleted_date());
			pre.setString(20, item.getArticle_restored_date());
			pre.setString(21, item.getArticle_modified_author_name());
			pre.setByte(22, item.getArticle_author_permission());
			pre.setString(23, item.getArticle_source());
			pre.setByte(24, item.getArticle_language());
			pre.setBoolean(25, item.isArticle_focus());
			pre.setByte(26, item.getArticle_type());
			pre.setBoolean(27, item.isArticle_forhome());
			pre.setString(28, item.getArticle_title());
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
	public boolean delArticle(ArticleObject item) {
		// TODO Auto-generated method stub
		if(this.isEmpty(item)) {
			String sql = "DELETE FROM tblarticle WHERE article_id=" + item.getArticle_id();
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
	
	public boolean isEmpty(ArticleObject item) {
		boolean flag = true;
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM tblarticle_extends WHERE ae_article_id=" + item.getArticle_id() + "; ");
		sql.append("SELECT * FROM tblorder WHERE order_article_id=" + item.getArticle_id() + "; ");
		ArrayList<ResultSet> res = this.getReList(sql.toString());
		for(ResultSet rs: res) {
			if(rs != null) {
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
	public ResultSet getArticle(int id) {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT a.*, c.category_id, c.category_name, s.section_id, s.section_name FROM tblarticle a ");
		sql.append("LEFT JOIN tblcategory c ON a.article_category_id=c.category_id ");
		sql.append("LEFT JOIN tblsection s ON c.category_section_id=s.section_id ");
		sql.append("WHERE a.artcile_id=?");
		return this.get(sql.toString(), id);
	}

	@Override
	public ArrayList<ResultSet> getArticles(Triplet<ArticleObject, Integer, Byte> infors) {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT a.*, c.category_id, c.category_name, s.section_id, s.section_name FROM tblarticle AS a ");
		sql.append("LEFT JOIN tblcategory AS c ON a.article_category_id=c.category_id ");
		sql.append("LEFT JOIN tblsection AS s ON c.category_section_id=s.section_id ");
		sql.append("ORDER BY a.article_id ASC ");
		sql.append("LIMIT " + infors.getValue1() + ", " + infors.getValue2() + "; ");
		sql.append("select count(*) as total from tblarticle");
		return this.getReList(sql.toString());
	}

}
