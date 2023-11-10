package jsoft.ads.room;

import java.util.ArrayList;

import org.javatuples.Pair;
import org.javatuples.Quartet;

import jsoft.ads.user.UserLibrary;
import jsoft.objects.RoomObject;

public class RoomLibrary {

	public static ArrayList<String> viewRooms(
			Pair<ArrayList<RoomObject>, Integer> datas,
			Quartet<RoomObject, Short, Byte, ROOM_SORT_TYPE> infors) {
		ArrayList<RoomObject> items = datas.getValue0();
		int total = datas.getValue1();
		
		short page = infors.getValue1();
		byte totalperpage = infors.getValue2();
		
		String url = "";
		
		StringBuilder tmp = new StringBuilder();
		tmp.append("<table class=\"table table-striped table-sm\">");
		tmp.append("<thead>");
		tmp.append("<tr>");
		tmp.append("<th scope=\"col\">ID</th>");
		tmp.append("<th scope=\"col\">Tiêu đề</th>");
		tmp.append("<th scope=\"col\">Loại phòng</th>");
		tmp.append("<th scope=\"col\">Tên khách sạn</th>");
		tmp.append("<th scope=\"col\">Trạng thái</th>");
		tmp.append("<th scope=\"col\" colspan=\"3\">Thực hiện</th>");
		tmp.append("</tr>");
		tmp.append("</thead>");
		tmp.append("<tbody>\n");
		
		items.forEach(item -> {
			//class="align-middle"
			tmp.append("<tr>");
			tmp.append("<th scope=\"row\" class=\"align-middle\">").append(item.getRoom_id()).append("</th>");
			tmp.append("<td class=\"align-middle\">").append(item.getRoom_title()).append("</td>");
			tmp.append("<td class=\"align-middle\">").append(item.getRoom_type()).append("</td>");
			tmp.append("<td class=\"align-middle\">").append(item.getRoom_hotel_name()).append("</td>");
			tmp.append("<td class=\"align-middle\">").append(RoomLibrary.viewStatic(item.getRoom_static())).append("</td>");
			tmp.append("<td class=\"align-middle\"><button class=\"btn btn-primary btn-sm\" data-bs-toggle=\"modal\" data-bs-target=\"#viewRoom"+item.getRoom_id()+"\"><i class=\"bi bi-eye\"></i></button></td>");
			tmp.append("");//View modal detail
			tmp.append(RoomLibrary.viewDetail(item));
			tmp.append("<td class=\"align-middle\"><button class=\"btn btn-success btn-sm\" data-bs-toggle=\"modal\" data-bs-target=\"#editRoom"+item.getRoom_id()+"\"><i class=\"bi bi-pencil-square\"></i></button></td>");
			tmp.append("");//View modal edit
			tmp.append(RoomLibrary.viewEdit(item));
			tmp.append("<td class=\"align-middle\"><button class=\"btn btn-danger btn-sm\" data-bs-toggle=\"modal\" data-bs-target=\"#delRoom"+item.getRoom_id()+"\"><i class=\"bi bi-trash\"></i></button></td>");
			tmp.append("");//View modal del
			tmp.append(RoomLibrary.viewDel(item));
			tmp.append("</tr>\n");
		});
		
		tmp.append("</tbody>");
		tmp.append("</table>");
		
		ArrayList<String> view = new ArrayList<>();
		
		//Danh sach index = 0;
		view.add(tmp.toString());
		//Phan trang index = 1
		view.add(RoomLibrary.getPaging(url, total, page, totalperpage));
		//Them moi index = 2
		view.add(RoomLibrary.viewAdd());
		return view;
	}
	
	private static StringBuilder viewStatic(int room_static) {
		StringBuilder tmp = new StringBuilder();
		if(room_static == 0) {
			tmp.append("<span class=\"text-body-success\">Còn phòng trống</span>");
		}
		if(room_static == 1) {
			tmp.append("<span class=\"text-body-danger\">Hết phòng</span>");
		}
		return null;
	}

	private static String getPaging(String url, int total, short page, byte totalperpage) {
		StringBuilder tmp = new StringBuilder();
		int countPage = total/totalperpage;
		if(total%totalperpage != 0) {
			countPage++;
		}
		
		if(page > countPage || page<=0) {
			page = 1;
		}
		
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
	
	private static StringBuilder viewDetail(RoomObject item) {
		StringBuilder tmp = new StringBuilder();
		
		return tmp;
	}
	
	private static StringBuilder viewDel(RoomObject item) {
		StringBuilder tmp = new StringBuilder();
		//start modal
		
		tmp.append("<div class=\"modal fade\" id=\"delRoom"+item.getRoom_id()+"\" data-bs-backdrop=\"static\" data-bs-keyboard=\"false\" tabindex=\"-1\" aria-labelledby=\"staticBackdropLabel\" aria-hidden=\"true\">");
		tmp.append("<div class=\"modal-dialog modal-lg\">");
		//start from
		tmp.append("<form method=\"post\" action=\"\" class=\"needs-validation\" novalidate>");
		
		tmp.append("<div class=\"modal-content\">");
		tmp.append("<div class=\"modal-header\">");
		tmp.append("<h1 class=\"modal-title fs-5\" id=\"delRoomLabel\">Xóa phòng</h1>");
		tmp.append("<button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button>");
		tmp.append("</div>");
		tmp.append("<div class=\"modal-body\">");
		
		tmp.append("<div class=\"row\">");
			tmp.append("<div class=\"col-lg-12\">");
				tmp.append("<p>Bạn chắc chắn muốn xóa phòng: "+item.getRoom_title()+"?</p>");
			tmp.append("</div>");
		tmp.append("</div>");
		
		tmp.append("<input type=\"hidden\" name=\"idForPost\" value=\""+item.getRoom_id()+"\">");
		tmp.append("<input type=\"hidden\" name=\"act\" value=\"del\">");
		
		tmp.append("</div>");
		tmp.append("<div class=\"modal-footer\">");
		tmp.append("<button type=\"submit\" class=\"btn btn-danger btn-sm\">Xóa</button>");
		tmp.append("<button type=\"button\" class=\"btn btn-secondary btn-sm\" data-bs-dismiss=\"modal\">Thoát</button>");
		tmp.append("</div>");
		tmp.append("</div>");
		
		tmp.append("</form>");
		//end from
		tmp.append("</div>");
		tmp.append("</div>");
		//end modal
		return tmp;
	}
	
	private static StringBuilder viewEdit(RoomObject item) {
		StringBuilder tmp = new StringBuilder();
		
		return tmp;
	}
	
	private static String viewAdd() {
		StringBuilder tmp = new StringBuilder();
		//start modal
		tmp.append("<button type=\"button\" class=\"btn btn-primary btn-sm mt-2\" data-bs-toggle=\"modal\" data-bs-target=\"#addRoom\">");
		tmp.append("Thêm mới");
		tmp.append("</button>");
		
		
		tmp.append("<div class=\"modal fade\" id=\"addRoom\" data-bs-backdrop=\"static\" data-bs-keyboard=\"false\" tabindex=\"-1\" aria-labelledby=\"staticBackdropLabel\" aria-hidden=\"true\">");
		tmp.append("<div class=\"modal-dialog modal-lg\">");
		//start from
		tmp.append("<form method=\"post\" action=\"\" class=\"needs-validation\" novalidate>");
		
		tmp.append("<div class=\"modal-content\">");
		tmp.append("<div class=\"modal-header\">");
		tmp.append("<h1 class=\"modal-title fs-5\" id=\"addRoomLabel\">Thêm mới phòng</h1>");
		tmp.append("<button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button>");
		tmp.append("</div>");
		tmp.append("<div class=\"modal-body\">");
		
		tmp.append("<div class=\"row\">");
			tmp.append("<div class=\"col-lg-6\">");
				tmp.append("<label for=\"roomtitle\" class=\"form-label\">Tiêu đề</label>");
				tmp.append("<input type=\"text\" class=\"form-control\" id=\"roomtitle\" name=\"txtRoomTitle\" required>");
				tmp.append("<div class=\"invalid-feedback\">Hãy nhập tiêu đề hiển thị của phòng</div>");
			tmp.append("</div>");
			tmp.append("<div class=\"col-lg-6\">");
				tmp.append("<label for=\"roomtype\" class=\"form-label\">Kiểu phòng</label>");
				tmp.append("<input type=\"text\" class=\"form-control\" id=\"roomtype\" name=\"txtRoomType\" required>");
				tmp.append("<div class=\"invalid-feedback\">Hãy nhập kiểu phòng</div>");
			tmp.append("</div>");
		tmp.append("</div>");
		
		tmp.append("<div class=\"row\">");
			tmp.append("<div class=\"col-lg-12\">");
				tmp.append("<label for=\"roomdetail\" class=\"form-label\">Chi tiết</label>");
				tmp.append("<textarea class=\"form-control\" id=\"roomdetail\" name=\"txtRoomDetail\" placeholder=\"Nhập nội dung\" rows=\"10\"></textarea>");
			tmp.append("</div>");
		tmp.append("</div>");
		
		tmp.append("<input type=\"hidden\" name=\"idForPost\" value=\"\">");
		tmp.append("<input type=\"hidden\" name=\"act\" value=\"add\">");
		
		tmp.append("</div>");
		tmp.append("<div class=\"modal-footer\">");
		tmp.append("<button type=\"submit\" class=\"btn btn-primary btn-sm\">Thêm mới</button>");
		tmp.append("<button type=\"button\" class=\"btn btn-secondary btn-sm\" data-bs-dismiss=\"modal\">Thoát</button>");
		tmp.append("</div>");
		tmp.append("</div>");
		
		tmp.append("</form>");
		//end from
		tmp.append("</div>");
		tmp.append("</div>");
		//end modal
		return tmp.toString();
	}
	
	
	
}
