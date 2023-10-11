package jsoft.ads.section;

import java.util.ArrayList;
import java.util.HashMap;

import org.javatuples.Pair;
import org.javatuples.Quartet;
import org.javatuples.Triplet;

import jsoft.objects.SectionObject;
import jsoft.objects.UserObject;

public class SectionLibrary {

	public static ArrayList<String> viewSections(Quartet<ArrayList<SectionObject>, Integer, HashMap<Integer, String>, ArrayList<UserObject>> datas, UserObject user){
		StringBuilder tmp = new StringBuilder();
		ArrayList<SectionObject> items = datas.getValue0();
		HashMap<Integer, String> manager_name = datas.getValue2();
		//Danh sach user cap quyen quan ly
		ArrayList<UserObject> users = datas.getValue3();
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
			if(user.getUser_permission() >= 4 
					|| item.getSection_created_author_id() == user.getUser_id()) {
				tmp.append("<td class=\"align-middle\"><button class=\"btn btn-success btn-sm\" data-bs-toggle=\"modal\" data-bs-target=\"#editSection"+item.getSection_id()+"\"><i class=\"bi bi-pencil-square\"></i></button></td>");
				tmp.append(SectionLibrary.viewEditSection(item, users));
				tmp.append("<td class=\"align-middle\"><button class=\"btn btn-danger btn-sm\" data-bs-toggle=\"modal\" data-bs-target=\"#delSection"+item.getSection_id()+"\"><i class=\"bi bi-trash\"></i></button></td>");
				tmp.append(SectionLibrary.viewDelSection(item));
			}else {
				tmp.append("<td class=\"align-middle\"><a href=\"#\" class=\"btn btn-success btn-sm disabled\"><i class=\"bi bi-pencil-square\"></i></a></td>");
				tmp.append("<td class=\"align-middle\"><a href=\"#\" class=\"btn btn-danger btn-sm disabled\"><i class=\"bi bi-trash\"></i></a></td>");
			}
			
			tmp.append("</tr>\n");
		});
		
		tmp.append("</tbody>");
		tmp.append("</table>");	
		ArrayList<String> view = new ArrayList<>();
		view.add(tmp.toString());
		
		//Phần trình bày option người quản lý
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
	
	public static StringBuilder viewDelSection(SectionObject item) {
		StringBuilder out = new StringBuilder();
		out.append("<div class=\"modal fade\" id=\"delSection"+item.getSection_id()+"\" data-bs-backdrop=\"static\" data-bs-keyboard=\"false\" tabindex=\"-1\" aria-labelledby=\"staticBackdropLabel\" aria-hidden=\"true\">");
		out.append("<div class=\"modal-dialog modal-lg\">");
		//start from
		out.append("<form method=\"post\" action=\"\" class=\"needs-validation\" novalidate>");
		
		out.append("<div class=\"modal-content\">");
		out.append("<div class=\"modal-header\">");
		out.append("<h1 class=\"modal-title fs-5\" id=\"addSectionLabel\">Xóa chuyên mục</h1>");
		out.append("<button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button>");
		out.append("</div>");
		out.append("<div class=\"modal-body\">");
		out.append("<p>Bạn chắc chắn muốn xóa chuyên mục <strong>"+item.getSection_name()+"</strong></p>");
		out.append("</div>");//modal body
		
		out.append("<input type=\"hidden\" name=\"idForPost\" value=\""+item.getSection_id()+"\">");
		out.append("<input type=\"hidden\" name=\"act\" value=\"del\">");
		
		out.append("<div class=\"modal-footer\">");
		out.append("<button type=\"submit\" class=\"btn btn-danger btn-sm\">Xóa</button>");
		out.append("<button type=\"button\" class=\"btn btn-secondary btn-sm\" data-bs-dismiss=\"modal\">Thoát</button>");
		out.append("</div>");
		
		out.append("</div>");
		
		out.append("</form>");
		//end from
		out.append("</div>");
		out.append("</div>");
		//end modal
		return out;
	}
	
	public static StringBuilder viewEditSection(SectionObject item, ArrayList<UserObject> users) {
		StringBuilder out = new StringBuilder();
		out.append("<div class=\"modal fade\" id=\"editSection"+item.getSection_id()+"\" data-bs-backdrop=\"static\" data-bs-keyboard=\"false\" tabindex=\"-1\" aria-labelledby=\"staticBackdropLabel\" aria-hidden=\"true\">");
		out.append("<div class=\"modal-dialog modal-lg\">");
		//start from
		out.append("<form method=\"post\" action=\"\" class=\"needs-validation\" novalidate>");
		
		out.append("<div class=\"modal-content\">");
		out.append("<div class=\"modal-header\">");
		out.append("<h1 class=\"modal-title fs-5\" id=\"addSectionLabel\">Chỉnh sửa chuyên mục</h1>");
		out.append("<button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button>");
		out.append("</div>");
		out.append("<div class=\"modal-body\">");
		
		out.append("<div class=\"row mb-3\">");
			out.append("<div class=\"col-lg-6\">");
				out.append("<label for=\"sectionname\" class=\"form-label\">Tên chuyên mục</label>");
				out.append("<input type=\"text\" class=\"form-control\" id=\"sectionname\" name=\"txtSectionName\" required value=\""+item.getSection_name()+"\">");
				out.append("<div class=\"invalid-feedback\">Hãy nhập tên chuyên mục</div>");
			out.append("</div>");
			out.append("<div class=\"col-lg-6\">");
				out.append("<label for=\"manager\" class=\"form-label\">Người quản lý</label>");
				out.append("<select class=\"form-select\" id=\"manager\" name=\"slcManager\" required>");
					out.append(SectionLibrary.viewManagerOptions(users, item.getSection_manager_id()));
				out.append("</select>");
				out.append("<div class=\"invalid-feedback\">Hãy chọn quản lý</div>");
			out.append("</div>");
		out.append("</div>");
		out.append("<div class=\"row mb-3\">");
			out.append("<div class=\"col-lg-6\">");
				out.append("<label for=\"sectionenable\" class=\"form-label\">Hiển thị trên site</label>");
				out.append("<select class=\"form-select\" id=\"sectionenable\" name=\"slcEnable\">");
					out.append("<option value=\"1\">Có</option>");
					if(!item.isSection_enable()) {
						out.append("<option value=\"0\" selected>Không</option>");
					}else {
						out.append("<option value=\"0\">Không</option>");
					}
				out.append("</select>");
			out.append("</div>");
			out.append("<div class=\"col-lg-6\">");
				out.append("<label for=\"\" class=\"form-label\">Ngày chỉnh sửa cuối</label>");
				out.append("<input type=\"text\" class=\"form-control\" value=\""+item.getSection_last_modified()+"\" disabled>");
			out.append("</div>");
		out.append("</div>");
		out.append("<div class=\"row\">");
			out.append("<div class=\"col-lg-12\">");
				out.append("<label for=\"sectionname\" class=\"form-label\">Ghi chú</label>");
				out.append("<textarea type=\"text\" class=\"form-control\" rows=\"7\" id=\"sectionnote\" name=\"txtSectionNote\">"+item.getSection_notes()+"</textarea>");
			out.append("</div>");
		out.append("</div>");
		
		out.append("</div>");//modal body
		
		out.append("<input type=\"hidden\" name=\"idForPost\" value=\""+item.getSection_id()+"\">");
		out.append("<input type=\"hidden\" name=\"act\" value=\"edit\">");
		
		out.append("<div class=\"modal-footer\">");
		out.append("<button type=\"submit\" class=\"btn btn-primary btn-sm\">Lưu thay đổi</button>");
		out.append("<button type=\"button\" class=\"btn btn-secondary btn-sm\" data-bs-dismiss=\"modal\">Thoát</button>");
		out.append("</div>");
		
		out.append("</div>");
		
		out.append("</form>");
		//end from
		out.append("</div>");
		out.append("</div>");
		//end modal
		return out;
	}
}
