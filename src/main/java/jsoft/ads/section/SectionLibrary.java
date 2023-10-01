package jsoft.ads.section;

import java.util.ArrayList;
import java.util.HashMap;

import org.javatuples.Pair;
import org.javatuples.Quartet;
import org.javatuples.Triplet;

import jsoft.objects.SectionObject;
import jsoft.objects.UserObject;

public class SectionLibrary {

	public static ArrayList<String> viewSections(Quartet<ArrayList<SectionObject>, Integer, HashMap<Integer, String>, ArrayList<UserObject>> datas){
		StringBuilder tmp = new StringBuilder();
		ArrayList<SectionObject> items = datas.getValue0();
		HashMap<Integer, String> manager_name = datas.getValue2();
		tmp.append("<table class=\"table table-striped table-sm\">");
		tmp.append("<thead>");
		tmp.append("<tr>");
		tmp.append("<th scope=\"col\">ID</th>");
		tmp.append("<th scope=\"col\">Tên chuyên mục</th>");
		tmp.append("<th scope=\"col\">Ngày tạo</th>");
		tmp.append("<th scope=\"col\">Người quản lý</th>");
		tmp.append("<th scope=\"col\">Mô tả chi tiết</th>");
		tmp.append("<th scope=\"col\" colspan=\"2\" class=\"text-center\">Thực hiện</th>");
		tmp.append("</tr>");
		tmp.append("</thead>");
		tmp.append("<tbody>\n");
		
		items.forEach(item -> {
			//class="align-middle"
			tmp.append("<tr>");
			tmp.append("<th scope=\"row\" class=\"align-middle\">").append(item.getSection_id()).append("</th>");
			tmp.append("<td class=\"align-middle\">").append(item.getSection_name()).append("</td>");
			tmp.append("<td class=\"align-middle\">").append(item.getSection_created_date()).append("</td>");
			tmp.append("<td class=\"align-middle\">").append(manager_name.get(item.getSection_manager_id())).append("</td>");
			tmp.append("<td class=\"align-middle\">").append(item.getSection_notes()).append("</td>");
			
			tmp.append("<td class=\"align-middle\"><a href=\"#\" class=\"btn btn-success btn-sm\"><i class=\"bi bi-pencil-square\"></i></a></td>");
			tmp.append("<td class=\"align-middle\"><a href=\"#\" class=\"btn btn-danger btn-sm\"><i class=\"bi bi-trash\"></i></a></td>");
			tmp.append("</tr>\n");
		});
		
		tmp.append("</tbody>");
		tmp.append("</table>");	
		ArrayList<String> view = new ArrayList<>();
		view.add(tmp.toString());
		//Danh sach user cap quyen quan ly
		ArrayList<UserObject> users = datas.getValue3();
		view.add(SectionLibrary.viewManagerOptions(users, 0));
		return view;
	}
	
	public static String viewManagerOptions(ArrayList<UserObject> users, int select_id) {
		StringBuilder tmp = new StringBuilder();
		users.forEach(item -> {
			if(item.getUser_id() == select_id) {
				tmp.append("<option value=\""+item.getUser_id()+"\" selected>");
			}else {
				tmp.append("<option value=\""+item.getUser_id()+"\">");
			}
			tmp.append(item.getUser_fullname() + " (" + item.getUser_name() + ")");
			tmp.append("</option>");
		});
		return tmp.toString();
	}
}
