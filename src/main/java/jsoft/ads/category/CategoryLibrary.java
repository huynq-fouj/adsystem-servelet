package jsoft.ads.category;

import java.util.ArrayList;
import java.util.HashMap;

import org.javatuples.Pair;
import org.javatuples.Quintet;

import jsoft.ads.section.SectionLibrary;
import jsoft.objects.CategoryObject;
import jsoft.objects.SectionObject;
import jsoft.objects.UserObject;

public class CategoryLibrary {

	public static ArrayList<String> viewCategories(Quintet<ArrayList<CategoryObject>,
													ArrayList<UserObject>, 
													ArrayList<SectionObject>,
													Integer,
													HashMap<Integer, String>> datas, UserObject user){
		ArrayList<CategoryObject> items = datas.getValue0();
		ArrayList<UserObject> users = datas.getValue1();
		ArrayList<SectionObject> sections = datas.getValue2();
		HashMap<Integer, String> managerName = datas.getValue4();
		StringBuilder tmp = new StringBuilder();
		tmp.append("<table class=\"table table-striped table-sm\">");
		tmp.append("<thead>");
		tmp.append("<tr>");
		tmp.append("<th scope=\"col\">ID</th>");
		tmp.append("<th scope=\"col\">Tên loại</th>");
		tmp.append("<th scope=\"col\">Tên chuyên mục</th>");
		tmp.append("<th scope=\"col\">Người quản lý</th>");
		tmp.append("<th scope=\"col\">Mô tả chi tiết</th>");
		tmp.append("<th scope=\"col\" colspan=\"2\" class=\"text-center\">Thực hiện</th>");
		tmp.append("</tr>");
		tmp.append("</thead>");
		tmp.append("<tbody>\n");
		
		items.forEach(item -> {
			//class="align-middle"
			tmp.append("<tr>");
			tmp.append("<th scope=\"row\" class=\"align-middle\">").append(item.getCategory_id()).append("</th>");
			tmp.append("<td class=\"align-middle\">").append(item.getCategory_name()).append("</td>");
			tmp.append("<td class=\"align-middle\">").append(item.getSection_name()).append("</td>");
			tmp.append("<td class=\"align-middle\">").append(managerName.get(item.getCategory_manager_id())).append("</td>");
			tmp.append("<td class=\"align-middle\">").append(item.getCategory_notes()).append("</td>");
			if(user.getUser_permission() >= 4 
					|| item.getSection_created_author_id() == user.getUser_id()) {
				tmp.append("<td class=\"align-middle\"><button class=\"btn btn-success btn-sm\" data-bs-toggle=\"modal\" data-bs-target=\"#editCategory"+item.getCategory_id()+"\"><i class=\"bi bi-pencil-square\"></i></button></td>");
				tmp.append(CategoryLibrary.viewEditCategory(item, users, sections));
				tmp.append("<td class=\"align-middle\"><button class=\"btn btn-danger btn-sm\" data-bs-toggle=\"modal\" data-bs-target=\"#delCategory"+item.getCategory_id()+"\"><i class=\"bi bi-trash\"></i></button></td>");
				tmp.append(CategoryLibrary.viewDelCategory(item));
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
		//Them moi Category
		view.add(CategoryLibrary.viewAddCategory(users, sections));
		
		return view;
	}
	
	public static String viewAddCategory(ArrayList<UserObject> users, ArrayList<SectionObject> sections) {
		StringBuilder out = new StringBuilder();
		//start modal
				out.append("<button type=\"button\" class=\"btn btn-primary btn-sm mt-2\" data-bs-toggle=\"modal\" data-bs-target=\"#addCategory\">");
				out.append("Thêm mới");
				out.append("</button>");
				
				
				out.append("<div class=\"modal fade\" id=\"addCategory\" data-bs-backdrop=\"static\" data-bs-keyboard=\"false\" tabindex=\"-1\" aria-labelledby=\"staticBackdropLabel\" aria-hidden=\"true\">");
				out.append("<div class=\"modal-dialog modal-lg\">");
				//start from
				out.append("<form method=\"post\" action=\"\" class=\"needs-validation\" novalidate>");
				
				out.append("<div class=\"modal-content\">");
				out.append("<div class=\"modal-header\">");
				out.append("<h1 class=\"modal-title fs-5\" id=\"addCategoryLabel\">Thêm mới loại</h1>");
				out.append("<button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button>");
				out.append("</div>");
				out.append("<div class=\"modal-body\">");
				
					out.append("<div class=\"row mb-3\">");//row 1
						//
						out.append("<div class=\"col-lg-6\">");
							out.append("<label for=\"categoryname\" class=\"form-label\">Tên loại</label>");
							out.append("<input type=\"text\" class=\"form-control\" id=\"categoryname\" name=\"txtCategoryName\" required>");
							out.append("<div class=\"invalid-feedback\">Hãy nhập tên loại</div>");
						out.append("</div>");
						
					out.append("</div>");//end row 1
					
					out.append("<div class=\"row mb-3\">");//row 2
					//
					out.append("<div class=\"col-lg-6\">");
						out.append("<label for=\"section\" class=\"form-label\">Chọn chuyên mục</label>");
						out.append("<select class=\"form-select\" id=\"section\" name=\"slcSection\" required>");
							out.append(CategoryLibrary.viewSectionOptions(sections, 0));
						out.append("</select>");
						out.append("<div class=\"invalid-feedback\">Hãy chọn chuyên mục</div>");
					out.append("</div>");
					
					//
					out.append("<div class=\"col-lg-6\">");
						out.append("<label for=\"manager\" class=\"form-label\">Người quản lý</label>");
						out.append("<select class=\"form-select\" id=\"manager\" name=\"slcManager\" required>");
							out.append(CategoryLibrary.viewManagerOptions(users, 0));
						out.append("</select>");
						out.append("<div class=\"invalid-feedback\">Hãy chọn quản lý</div>");
					out.append("</div>");
					
				out.append("</div>");//end row 2
					
					out.append("<div class=\"row\">");//row 3
						//
						out.append("<div class=\"col-lg-12\">");
							out.append("<label for=\"sectionname\" class=\"form-label\">Ghi chú</label>");
							out.append("<textarea type=\"text\" class=\"form-control\" rows=\"10\" id=\"sectionnote\" name=\"txtCategoryNote\"></textarea>");
						out.append("</div>");
						
					out.append("</div>");//end row 3
					
				out.append("</div>");// end modal body
				
				out.append("<input type=\"hidden\" name=\"act\" value=\"add\">");
				
				out.append("<div class=\"modal-footer\">");
				out.append("<button type=\"submit\" class=\"btn btn-primary btn-sm\">Thêm mới</button>");
				out.append("<button type=\"button\" class=\"btn btn-secondary btn-sm\" data-bs-dismiss=\"modal\">Thoát</button>");
				out.append("</div>");
				
				out.append("</div>");
				
				out.append("</form>");
				//end from
				out.append("</div>");
				out.append("</div>");
				//end modal
		return out.toString();
	}
	
	public static StringBuilder viewManagerOptions(ArrayList<UserObject> users, int select_id) {
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
		return tmp;
	}
	
	public static StringBuilder viewSectionOptions(ArrayList<SectionObject> sections, int select_id) {
		StringBuilder tmp = new StringBuilder();
		sections.forEach(item -> {
			if(item.getSection_id() == select_id) {
				tmp.append("<option value=\""+item.getSection_id()+"\" selected>");
			}else {
				tmp.append("<option value=\""+item.getSection_id()+"\">");
			}
			tmp.append(item.getSection_name());
			tmp.append("</option>");
		});
		return tmp;
	}

	public static StringBuilder viewDelCategory(CategoryObject item) {
		StringBuilder out = new StringBuilder();
		out.append("<div class=\"modal fade\" id=\"delCategory"+item.getCategory_id()+"\" data-bs-backdrop=\"static\" data-bs-keyboard=\"false\" tabindex=\"-1\" aria-labelledby=\"staticBackdropLabel\" aria-hidden=\"true\">");
		out.append("<div class=\"modal-dialog modal-lg\">");
		//start from
		out.append("<form method=\"post\" action=\"\" class=\"needs-validation\" novalidate>");
		
		out.append("<div class=\"modal-content\">");
		out.append("<div class=\"modal-header\">");
		out.append("<h1 class=\"modal-title fs-5\" id=\"delCategoryLabel\">Xóa loại</h1>");
		out.append("<button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button>");
		out.append("</div>");
		out.append("<div class=\"modal-body\">");
		out.append("<p>Bạn chắc chắn muốn xóa loại <strong>"+item.getCategory_name()+"</strong></p>");
		out.append("</div>");//modal body
		
		out.append("<input type=\"hidden\" name=\"idForPost\" value=\""+item.getCategory_id()+"\">");
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

	public static StringBuilder viewEditCategory(CategoryObject item, ArrayList<UserObject> users, ArrayList<SectionObject> sections) {
		// TODO Auto-generated method stub
		StringBuilder out = new StringBuilder();
		out.append("<div class=\"modal fade\" id=\"editCategory"+item.getCategory_id()+"\" data-bs-backdrop=\"static\" data-bs-keyboard=\"false\" tabindex=\"-1\" aria-labelledby=\"staticBackdropLabel\" aria-hidden=\"true\">");
		out.append("<div class=\"modal-dialog modal-lg\">");
		//start from
		out.append("<form method=\"post\" action=\"\" class=\"needs-validation\" novalidate>");
		
		out.append("<div class=\"modal-content\">");
		out.append("<div class=\"modal-header\">");
		out.append("<h1 class=\"modal-title fs-5\" id=\"editCategoryLabel\">Chỉnh sửa loại</h1>");
		out.append("<button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button>");
		out.append("</div>");
		out.append("<div class=\"modal-body\">");
		
		out.append("<div class=\"row mb-3\">");
			//
			out.append("<div class=\"col-lg-6\">");
				out.append("<label for=\"\" class=\"form-label\">Ngày tạo</label>");
				out.append("<input type=\"text\" class=\"form-control\" value=\""+item.getCategory_created_date()+"\" disabled>");
			out.append("</div>");
			//
			out.append("<div class=\"col-lg-6\">");
				out.append("<label for=\"\" class=\"form-label\">Ngày chỉnh sửa cuối</label>");
				out.append("<input type=\"text\" class=\"form-control\" value=\""+item.getCategory_last_modified()+"\" disabled>");
			out.append("</div>");
			
		out.append("</div>");
		
		out.append("<div class=\"row mb-3\">");
		
			out.append("<div class=\"col-lg-6\">");
				out.append("<label for=\"categoryname\" class=\"form-label\">Tên loại</label>");
				out.append("<input type=\"text\" class=\"form-control\" id=\"categoryname\" name=\"txtCategoryName\" required value=\""+item.getSection_name()+"\">");
				out.append("<div class=\"invalid-feedback\">Hãy nhập tên loại</div>");
			out.append("</div>");
			
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
		
		out.append("</div>");
		out.append("<div class=\"row mb-3\">");
		
			out.append("<div class=\"col-lg-6\">");
				out.append("<label for=\"section\" class=\"form-label\">Chọn chuyên mục</label>");
				out.append("<select class=\"form-select\" id=\"section\" name=\"slcSection\" required>");
					out.append(CategoryLibrary.viewSectionOptions(sections, 0));
				out.append("</select>");
				out.append("<div class=\"invalid-feedback\">Hãy chọn chuyên mục</div>");
			out.append("</div>");
			
			out.append("<div class=\"col-lg-6\">");
				out.append("<label for=\"manager\" class=\"form-label\">Người quản lý</label>");
				out.append("<select class=\"form-select\" id=\"manager\" name=\"slcManager\" required>");
					out.append(CategoryLibrary.viewManagerOptions(users, item.getCategory_manager_id()));
				out.append("</select>");
				out.append("<div class=\"invalid-feedback\">Hãy chọn quản lý</div>");
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
