package jsoft.ads.category;

import java.util.ArrayList;

import org.javatuples.Pair;

import jsoft.objects.CategoryObject;

public class CategoryLibrary {

	public static ArrayList<String> viewCategories(Pair<ArrayList<CategoryObject>, Integer> datas){
		ArrayList<CategoryObject> items = datas.getValue0();
		int total = datas.getValue1();
		StringBuilder tmp = new StringBuilder();
		tmp.append("<h3>Tổng số lượng Category: ").append(total).append("</h3>\n");
		tmp.append("<table class=\"table table-striped\">");
		tmp.append("<thead>");
		tmp.append("<tr>");
		tmp.append("<th scope=\"col\">ID</th>");
		tmp.append("<th scope=\"col\">Tên category</th>");
		tmp.append("<th scope=\"col\">Ngày tạo</th>");
		tmp.append("<th scope=\"col\">ID người quản lý</th>");
		tmp.append("<th scope=\"col\">ID người tạo</th>");
		tmp.append("<th scope=\"col\" colspan=\"2\">Thực hiện</th>");
		tmp.append("</tr>");
		tmp.append("</thead>");
		tmp.append("<tbody>\n");
		
		items.forEach(item -> {
			tmp.append("<tr>");
			tmp.append("<th scope=\"row\">").append(item.getCategory_id()).append("</th>");
			tmp.append("<td>").append(item.getCategory_name()).append("</td>");
			tmp.append("<td>").append(item.getCategory_created_date()).append("</td>");
			tmp.append("<td>").append(item.getCategory_manager_id()).append("</td>");
			tmp.append("<td>").append(item.getCategory_created_author_id()).append("</td>");
			tmp.append("<td>Sửa</td>");
			tmp.append("<td>Xóa</td>");
			tmp.append("</tr>\n");
		});
		
		tmp.append("</tbody>");
		tmp.append("</table>");
		ArrayList<String> view = new ArrayList<>();
		view.add(tmp.toString());
		
		tmp.setLength(0);
		tmp.append("<nav aria-label=\"...\">");
		tmp.append("<ul class=\"pagination\">");
		tmp.append("<li class=\"page-item disabled\">");
		tmp.append("<a class=\"page-link\" href=\"#\" tabindex=\"-1\" aria-disabled=\"true\">Previous</a>");
		tmp.append("</li>");
		tmp.append("<li class=\"page-item\"><a class=\"page-link\" href=\"#\">1</a></li>");
		tmp.append("<li class=\"page-item active\" aria-current=\"page\">");
		tmp.append("<a class=\"page-link\" href=\"#\">2</a>");
		tmp.append("</li>");
		tmp.append("<li class=\"page-item\"><a class=\"page-link\" href=\"#\">3</a></li>");
		tmp.append("<li class=\"page-item\">");
		tmp.append("<a class=\"page-link\" href=\"#\">Next</a>");
		tmp.append("</li>");
		tmp.append("</ul>");
		tmp.append("</nav>");
		
		view.add(tmp.toString());
		return view;
	}
	
}
