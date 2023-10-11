package jsoft.ads.user;

import java.util.ArrayList;

import org.javatuples.Pair;
import org.javatuples.Quartet;
import org.javatuples.Quintet;

import jsoft.library.Utilities;
import jsoft.objects.UserObject;

public class UserLibrary {

	public static ArrayList<String> viewUser(Pair<ArrayList<UserObject>, Integer> datas, 
			Quintet<UserObject, Short, Byte, USER_SORT_TYPE, String> infors){
		//datas
		ArrayList<UserObject> items = datas.getValue0();
		int totalGlobal = datas.getValue1();
		//infors
		UserObject user = infors.getValue0();
		short page = infors.getValue1();
		byte total = infors.getValue2();
		String searchKey = infors.getValue4();
		
		StringBuilder tmp = new StringBuilder();
		tmp.append("<table class=\"table table-striped table-sm\">");
		tmp.append("<thead>");
		tmp.append("<tr>");
		tmp.append("<th scope=\"col\">ID</th>");
		tmp.append("<th scope=\"col\">Tên đăng nhập</th>");
		tmp.append("<th scope=\"col\">Tên đầy đủ</th>");
		
		// Trong thùng rác chỉ có thao tác phục hồi hoặc xóa tuyệt đối
		if(user.isUser_deleted()) {
			tmp.append("<th scope=\"col\">Ngày xóa</th>");
			tmp.append("<th scope=\"col\" colspan=\"2\">Thực hiện</th>");
		} else {
			tmp.append("<th scope=\"col\">Hộp thư</th>");
			tmp.append("<th scope=\"col\">Điện thoại</th>");
			tmp.append("<th scope=\"col\">Địa chỉ</th>");
			tmp.append("<th scope=\"col\" colspan=\"3\">Thực hiện</th>");
		}
		tmp.append("</tr>");
		tmp.append("</thead>");
		tmp.append("<tbody>\n");
		
		items.forEach(item -> {
			//class="align-middle"
			tmp.append("<tr>");
			tmp.append("<th scope=\"row\" class=\"align-middle\">").append(item.getUser_id()).append("</th>");
			tmp.append("<td class=\"align-middle\">").append(item.getUser_name()).append("</td>");
			tmp.append("<td class=\"align-middle\">").append(item.getUser_fullname()).append("</td>");
			if(user.isUser_deleted()) { // trong danh sách người dùng bị xóa
				tmp.append("<td class=\"align-middle\">").append(item.getUser_last_modified()).append("</td>");
				tmp.append("<td class=\"align-middle\"><button class=\"btn btn-danger btn-sm\" data-bs-toggle=\"modal\" data-bs-target=\"#delUser"+item.getUser_id()+"\"><i class=\"bi bi-trash\"></i></button></td>");
				tmp.append(UserLibrary.viewDelUser(item));
			} else { // Trạng thái bình thường
				tmp.append("<td class=\"align-middle\">").append(item.getUser_email()).append("</td>");
				tmp.append("<td class=\"align-middle\">").append(item.getUser_homephone()).append("</td>");
				tmp.append("<td class=\"align-middle\">").append(item.getUser_address()).append("</td>");
				tmp.append("<td class=\"align-middle\"><a href=\"/adv/user/profiles?id="+item.getUser_id()+"\" class=\"btn btn-primary btn-sm\"><i class=\"bi bi-eye\"></i></a></td>");
				// Kiểm tra tài khoản trong danh sách có phải là tài khoản đang đăng nhập hay không
				if(item.getUser_id() == user.getUser_id()) {
					tmp.append("<td class=\"align-middle\"><a href=\"/adv/user/profiles?id="+item.getUser_id()+"\" class=\"btn btn-success btn-sm\"><i class=\"bi bi-pencil-square\"></i></a></td>");
					tmp.append("<td class=\"align-middle\"><a href=\"#\" class=\"btn btn-danger btn-sm disabled\" disabled><i class=\"bi bi-trash\"></i></a></td>");
				}else{
					// Kiểm tra người dùng có phải người quản trị hay không. Nếu có thì người dùng có toàn quyền sửa danh sách người dùng
					if(user.getUser_permission() >=4) {
						tmp.append("<td class=\"align-middle\"><a href=\"/adv/user/profiles?id="+item.getUser_id()+"\" class=\"btn btn-success btn-sm\"><i class=\"bi bi-pencil-square\"></i></a></td>");
						tmp.append("<td class=\"align-middle\"><button class=\"btn btn-danger btn-sm\" data-bs-toggle=\"modal\" data-bs-target=\"#delUser"+item.getUser_id()+"\"><i class=\"bi bi-trash\"></i></button></td>");
						tmp.append(UserLibrary.viewDelUser(item));
					}else {
						// Nếu là cha của người dùng trong danh sách thì được phép sửa
						if(item.getUser_parent_id() == user.getUser_id()) {
							tmp.append("<td class=\"align-middle\"><a href=\"/adv/user/profile?id="+item.getUser_id()+"\" class=\"btn btn-primary btn-sm\"><i class=\"bi bi-pencil-square\"></i></a></td>");
						} else {
							tmp.append("<td class=\"align-middle\"><a href=\"#\" class=\"btn btn-primary btn-sm disabled\"><i class=\"bi bi-pencil-square\"></i></a></td>");
						}
						tmp.append("<td class=\"align-middle\"><button class=\"btn btn-danger btn-sm disabled\" data-bs-toggle=\"modal\" data-bs-target=\"#delUser"+item.getUser_id()+"\" ><i class=\"bi bi-trash\"></i></button></td>");
					}
				}
			}
			tmp.append("</tr>\n");
		});
		
		tmp.append("</tbody>");
		tmp.append("</table>");
		
		//Danh sách hiênt thị
		ArrayList<String> view = new ArrayList<>();
		view.add(tmp.toString());
		
		//Cấu trúc phân trang
		String url = "/adv/user/list"+(!searchKey.equals("") ? ("?key=" + searchKey + "&") : "?");
		String paging = UserLibrary.getPaging(url, page, totalGlobal, total);
		view.add(paging);
		
		// Cấu trúc biểu đồ
		String chart = UserLibrary.createChart(items).toString();
		view.add(chart);
		
		return view;
	}
	
	private static StringBuilder viewDelUser(UserObject item) {
		StringBuilder out = new StringBuilder();
		String title;
		if(item.isUser_deleted()) {
			title = "Xóa vĩnh viễn";
		} else {
			title = "Xóa tài khoản";
		}
		out.append("<div class=\"modal fade\" id=\"delUser"+item.getUser_id()+"\" data-bs-backdrop=\"static\" data-bs-keyboard=\"false\" tabindex=\"-1\" aria-labelledby=\"UserLabel"+item.getUser_id()+"\" aria-hidden=\"true\">");
		out.append("<div class=\"modal-dialog modal-lg\">");
		//start from
		out.append("<form method=\"post\" action=\"\" class=\"needs-validation\" novalidate>");
		
		out.append("<div class=\"modal-content\">");
		out.append("<div class=\"modal-header\">");
		out.append("<h1 class=\"modal-title fs-5\" id=\"UserLabel\""+item.getUser_id()+">"+title+"</h1>");		out.append("<button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button>");
		out.append("</div>");
		out.append("<div class=\"modal-body\">");
		if(item.isUser_deleted()) {
			out.append("<p>Bạn có chắc chắn muốn xóa vĩnh viễn tài khoản <b>").append(item.getUser_fullname()).append(" ("+item.getUser_name()+")</b></p>");
			out.append("<p class=\"txt-alert\">Tài khoản không thể phục hồi được nữa</p>");
		} else {
			out.append("Bạn có chắc chắn muốn xóa tài khoản <b>").append(item.getUser_fullname()).append(" ("+item.getUser_name()+")</b></br>");
			out.append("<p class=\"txt-alert\">Hệ thống tạm thời lưu vào thùng rác, tài khoản có thể phục hồi trong vòng 30 ngày</p>");	
		}		
		out.append("</div>");
		out.append("<div class=\"modal-footer\">");
		
		if(item.isUser_deleted()) {
			out.append("<a href=\"/adv/user/dr?id="+item.getUser_id()+"&pid="+item.getUser_parent_id()+"\" class=\"btn btn-danger\">Xóa vĩnh viễn</a>");
		} else {
			// dr: delete + restore
			out.append("<a href=\"/adv/user/dr?id="+item.getUser_id()+"&t&pid="+item.getUser_parent_id()+"\" class=\"btn btn-danger\">Xóa</a>");		
		}		out.append("<button type=\"button\" class=\"btn btn-secondary btn-sm\" data-bs-dismiss=\"modal\">Thoát</button>");
		out.append("</div>");
		out.append("</div>");
		
		out.append("</form>");
		//end from
		out.append("</div>");
		out.append("</div>");
		
		return out;
	}
	
	public static StringBuilder createChart(ArrayList<UserObject> items) {
		StringBuilder data = new StringBuilder();
		StringBuilder accounts = new StringBuilder();
		items.forEach(item -> {
			data.append(item.getUser_logined());
			accounts.append("'"+Utilities.decode(item.getUser_fullname())).append(" (").append(item.getUser_name()).append(")'");
			if(items.indexOf(item) < items.size()) {
				data.append(", ");
				accounts.append(", ");
			}
		});
//		System.out.println(data.toString());
		StringBuilder tmp = new StringBuilder();
		tmp.append("<div class=\"card\">");
		tmp.append("<div class=\"card-body\">");
		tmp.append("<h5 class=\"card-title\">Biểu đồ đăng nhập hệ thống</h5>");

		tmp.append("<!-- Bar Chart -->");
		tmp.append("<div id=\"barChart\"></div>");

		tmp.append("<script>");
		tmp.append("document.addEventListener(\"DOMContentLoaded\", () => {");
		tmp.append("new ApexCharts(document.querySelector(\"#barChart\"), {");
		tmp.append("series: [{");
		tmp.append("data: ["+data.toString()+"],");
		tmp.append("name: [\"logined\"],");
		tmp.append("}],");
		tmp.append("chart: {");
		tmp.append("type: 'bar',");
		tmp.append("height:"+(items.size() >= 5 ? items.size()*40 : items.size()*50));
		tmp.append("},");
		tmp.append("plotOptions: {");
		tmp.append("bar: {");
		tmp.append("borderRadius: 0,");
		tmp.append("horizontal: true,");
		tmp.append("}");
		tmp.append("},");
		tmp.append("dataLabels: {");
		tmp.append("enabled: false");
		tmp.append("},");
		
		tmp.append("xaxis: {");
			tmp.append("categories: ["+accounts.toString()+"],");
		tmp.append("},");
		
		tmp.append("yaxis:{");
			tmp.append("show: true,");
			tmp.append("labels:{");
				tmp.append("align: 'right',");
				tmp.append("minWidth: 0,");
				tmp.append("maxWidth: 300,");
				
				tmp.append("style:{");
					tmp.append("color: ['green', 'blue', 'orange'],");
					tmp.append("fontSize: '15px',");
					tmp.append("fontWeight: 400,");
					tmp.append("fontFamily: 'Helvetica, Arial, sans-serif',");
					tmp.append("cssClass: 'apexcharts-yaxis-label',");
				tmp.append("},");//style
				
			tmp.append("},");//labels
			
		tmp.append("},");//yaxis
		tmp.append("}).render();");
		tmp.append("});");
		tmp.append("</script>");
		tmp.append("<!-- End Bar Chart -->");

		tmp.append("</div>");
		tmp.append("</div>");

		return tmp;
	}
	
	public static String getPaging(String url, short page, int total, byte totalperpage) {
		//Tính toán tổng số trang
		int countPage = total/totalperpage;
		if(total % totalperpage != 0) {
			countPage++;
		}
		
		if(page > countPage || page<=0) {
			page = 1;
		}
		
		StringBuilder tmp = new StringBuilder();
		tmp.append("<nav aria-label=\"...\">");
		tmp.append("<ul class=\"pagination justify-content-center\">");
		
		if(countPage > 1) {
			//Previous page
			if(page == 1) {
				tmp.append("<li class=\"page-item disabled\"><a class=\"page-link\" href=\""+url+"\"><span aria-hidden=\"true\">&laquo;</span></a></li>");
				tmp.append("<li class=\"page-item active\" aria-current=\"page\"><a class=\"page-link\" href=\"#\">"+page+"</a></li>");
			}else {
				tmp.append("<li class=\"page-item\"><a class=\"page-link\" href=\""+url+"page="+(page - 1)+"\"><span aria-hidden=\"true\">&laquo;</span></a></li>");
				tmp.append("<li class=\"page-item\" aria-current=\"page\"><a class=\"page-link\" href=\""+url+"page=1\">1</a></li>");
			}
			
			//Left current
			String leftCurrent = "";
			int count = 0;
			for(int i = page - 1; i > 1; i--) {
				leftCurrent = "<li class=\"page-item\"><a class=\"page-link\" href=\""+url+"page="+i+"\">" + i + "</a></li>" + leftCurrent;
				if(++count >= 2) {
					break;
				}
			}
			if(page > 4) {
				leftCurrent = "<li class=\"page-item disabled\"><a class=\"page-link\" href=\"#\">...</a></li>" + leftCurrent;
			}
			tmp.append(leftCurrent);
			//End left current
			//Current
			if(page > 1 && page < countPage) {
				tmp.append("<li class=\"page-item active\" aria-current=\"page\"><a class=\"page-link\" href=\"#\">"+page+"</a></li>");
			}
			//Right current
			String rightCurrent = "";
			count = 0;
			for(int i = page + 1; i < countPage; i++) {
				rightCurrent += "<li class=\"page-item\"><a class=\"page-link\" href=\""+url+"page="+i+"\">" + i + "</a></li>";
				if(++count >= 2) {
					break;
				}
			}
			if(page < countPage - 3) {
				rightCurrent += "<li class=\"page-item disabled\"><a class=\"page-link\" href=\"#\">...</a></li>";
			}
			tmp.append(rightCurrent);
			//End right current
			//Next page
			if(page == countPage) {	
				tmp.append("<li class=\"page-item active\" aria-current=\"page\"><a class=\"page-link\" href=\"#\">"+page+"</a></li>");
				tmp.append("<li class=\"page-item disabled\"><a class=\"page-link\" href=\""+url+"\" tabindex=\"-1\" aria-disabled=\"true\" ><span aria-hidden=\"true\">&raquo;</span></a></li>");
			}else {
				tmp.append("<li class=\"page-item\" aria-current=\"page\"><a class=\"page-link\" href=\""+url+"page="+countPage+"\">"+countPage+"</a></li>");
				tmp.append("<li class=\"page-item\"><a class=\"page-link\" href=\""+url+"page="+(page + 1)+"\" tabindex=\"-1\" aria-disabled=\"true\" ><span aria-hidden=\"true\">&raquo;</span></a></li>");
			}
		}else {
			tmp.append("<li class=\"page-item disabled\"><a class=\"page-link\" href=\"#\"><span aria-hidden=\"true\">&laquo;</span></a></li>");
			tmp.append("<li class=\"page-item active\" aria-current=\"page\"><a class=\"page-link\" href=\"#\">"+page+"</a></li>");
			tmp.append("<li class=\"page-item disabled\"><a class=\"page-link\" href=\"#\" tabindex=\"-1\" aria-disabled=\"true\" ><span aria-hidden=\"true\">&raquo;</span></a></li>");
		}
		
		tmp.append("</ul>");
		tmp.append("</nav>");
		return tmp.toString();
	}
}
