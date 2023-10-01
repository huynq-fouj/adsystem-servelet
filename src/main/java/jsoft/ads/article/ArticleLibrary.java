package jsoft.ads.article;

import java.util.ArrayList;

import org.javatuples.Pair;

import jsoft.objects.ArticleObject;

public class ArticleLibrary {

	public static ArrayList<String> viewArticles(Pair<ArrayList<ArticleObject>, Integer> datas){
		ArrayList<ArticleObject> items = datas.getValue0();
		int total = datas.getValue1();
		StringBuilder tmp = new StringBuilder();
		tmp.append("<h3>Tổng số lượng Article: ").append(total).append("</h3>\n");
		tmp.append("<table class=\"table table-striped\">");
		tmp.append("<thead>");
		tmp.append("<tr>");
		tmp.append("<th scope=\"col\">Article id</th>");
		tmp.append("<th scope=\"col\">Article title</th>");
		tmp.append("<th scope=\"col\">Category id</th>");
		tmp.append("<th scope=\"col\">Category name</th>");
		tmp.append("<th scope=\"col\">Section id</th>");
		tmp.append("<th scope=\"col\">Section name</th>");
		tmp.append("<th scope=\"col\" colspan=\"3\">Thực hiện</th>");
		tmp.append("</tr>");
		tmp.append("</thead>");
		tmp.append("<tbody>\n");
		
		items.forEach(item -> {
			tmp.append("<tr>");
			tmp.append("<th scope=\"row\">").append(item.getArticle_id()).append("</th>");
			tmp.append("<td>").append(item.getArticle_title()).append("</td>");
			tmp.append("<td>").append(item.getCategory_id()).append("</td>");
			tmp.append("<td>").append(item.getCategory_name()).append("</td>");
			tmp.append("<td>").append(item.getSection_id()).append("</td>");
			tmp.append("<td>").append(item.getSection_name()).append("</td>");
			tmp.append("<td>Chi tiết</td>");
			tmp.append("<td>Sửa</td>");
			tmp.append("<td>Xóa</td>");
			tmp.append("</tr>\n");
		});
		
		tmp.append("</tbody>");
		tmp.append("</table>");
		ArrayList<String> view = new ArrayList<>();
		view.add(tmp.toString());
		return view;
	}
	
}
