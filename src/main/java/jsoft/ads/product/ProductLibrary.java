package jsoft.ads.product;

import java.util.ArrayList;

import org.javatuples.Pair;

import jsoft.objects.CategoryObject;
import jsoft.objects.ProductObject;

public class ProductLibrary {

	public static ArrayList<String> viewProducts(Pair<ArrayList<ProductObject>, Integer> datas){
		ArrayList<ProductObject> items = datas.getValue0();
		int total = datas.getValue1();
		StringBuilder tmp = new StringBuilder();
		tmp.append("<h3>Tổng số lượng sản phẩm: ").append(total).append("</h3>\n");
		tmp.append("<table class=\"table table-striped\">");
		tmp.append("<thead>");
		tmp.append("<tr>");
		tmp.append("<th scope=\"col\">ID</th>");
		tmp.append("<th scope=\"col\">Tên sản phẩm</th>");
		tmp.append("<th scope=\"col\">Giá sản phẩm</th>");
		tmp.append("<th scope=\"col\">Giá chiết khấu</th>");
		tmp.append("<th scope=\"col\">Số lượng trong kho</th>");
		tmp.append("<th scope=\"col\" colspan=\"3\">Thực hiện</th>");
		tmp.append("</tr>");
		tmp.append("</thead>");
		tmp.append("<tbody>\n");
		
		items.forEach(item -> {
			tmp.append("<tr>");
			tmp.append("<th scope=\"row\">").append(item.getProduct_id()).append("</th>");
			tmp.append("<td>").append(item.getProduct_name()).append("</td>");
			tmp.append("<td>").append(item.getProduct_price()).append("</td>");
			tmp.append("<td>").append(item.getProduct_discount_price()).append("</td>");
			tmp.append("<td>").append(item.getProduct_total()).append("</td>");
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
