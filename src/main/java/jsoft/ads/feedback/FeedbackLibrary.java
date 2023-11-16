package jsoft.ads.feedback;

import java.util.ArrayList;

import org.javatuples.Quartet;
import org.javatuples.Triplet;

import jsoft.library.Utilities;
import jsoft.library.Utilities_date;
import jsoft.objects.FeedbackObject;
import jsoft.objects.UserObject;

public class FeedbackLibrary {

	public static ArrayList<String> viewFeedback(Triplet<ArrayList<FeedbackObject>, Integer, UserObject> datas,
			Quartet<FeedbackObject, Short, Byte, Boolean> infors) {
		//
		ArrayList<String> view = new ArrayList<>();
		//
		ArrayList<FeedbackObject> items = datas.getValue0();
		int total = datas.getValue1();
		UserObject user = datas.getValue2();
		//
		short page = infors.getValue1();
		byte totalperpage = infors.getValue2();
		String url = "/adv/feedback/list?";
		//
		StringBuilder tmp = new StringBuilder();
		tmp.append("<table class=\"table table-striped table-sm\">");
		tmp.append("<thead>");
		tmp.append("<tr>");
		tmp.append("<th scope=\"col\">ID</th>");
		tmp.append("<th scope=\"col\">Họ tên</th>");
		tmp.append("<th scope=\"col\">Email</th>");
		tmp.append("<th scope=\"col\">Tiêu đề</th>");
		tmp.append("<th scope=\"col\">Trạng thái</th>");
		tmp.append("<th scope=\"col\" colspan=\"2\">Thực hiện</th>");
		tmp.append("</tr>");
		tmp.append("</thead>");
		tmp.append("<tbody>\n");
		
		items.forEach(item -> {
			//class="align-middle"
			tmp.append("<tr>");
			tmp.append("<th scope=\"row\" class=\"align-middle\">").append(item.getFeedback_id()).append("</th>");
			tmp.append("<td class=\"align-middle\">").append(item.getFeedback_fullname()).append("</td>");
			tmp.append("<td class=\"align-middle\">").append(item.getFeedback_email()).append("</td>");
			tmp.append("<td class=\"align-middle\">").append(item.getFeedback_title()).append("</td>");
			tmp.append("<td class=\"align-middle\">").append(FeedbackLibrary.viewStatic(item.isFeedback_view())).append("</td>");
			tmp.append("<td class=\"align-middle\"><button class=\"btn btn-primary btn-sm\" data-bs-toggle=\"modal\" data-bs-target=\"#viewFeedback"+item.getFeedback_id()+"\"><i class=\"bi bi-eye\"></i></button></td>");
			tmp.append(FeedbackLibrary.viewDetail(item));
			tmp.append("<td class=\"align-middle\"><button class=\"btn btn-danger btn-sm\" data-bs-toggle=\"modal\" data-bs-target=\"#delFeedback"+item.getFeedback_id()+"\"><i class=\"bi bi-trash\"></i></button></td>");
			tmp.append(FeedbackLibrary.viewDel(item));
			tmp.append("</tr>\n");
		});
		
		tmp.append("</tbody>");
		tmp.append("</table>");
		//Danh sách feedback
		view.add(tmp.toString());
		//Phân trang
		view.add(FeedbackLibrary.getPaging(url, total, page, totalperpage));
		//Them mowi
		view.add(FeedbackLibrary.viewAdd().toString());
		return view;
	}
	
	private static StringBuilder viewStatic(boolean feedback_view) {
		// TODO Auto-generated method stub
		StringBuilder tmp = new StringBuilder();
		tmp.append(feedback_view ? "<span class=\"text-success\">Đã xem</span>" : "<span class=\"text-danger\">Chưa xem</span>");
		return tmp;
	}
	
	private static StringBuilder viewAdd() {
		StringBuilder tmp = new StringBuilder();
		//start modal
		tmp.append("<button type=\"button\" class=\"btn btn-primary btn-sm mt-2\" data-bs-toggle=\"modal\" data-bs-target=\"#addFeedback\">");
		tmp.append("Thêm mới");
		tmp.append("</button>");
		
		tmp.append("<div class=\"modal fade\" id=\"addFeedback\" data-bs-backdrop=\"static\" data-bs-keyboard=\"false\" tabindex=\"-1\" aria-labelledby=\"staticBackdropLabel\" aria-hidden=\"true\">");
		tmp.append("<div class=\"modal-dialog modal-lg\">");
		//start from
		tmp.append("<form method=\"post\" action=\"\" class=\"needs-validation\" novalidate>");
		
		tmp.append("<div class=\"modal-content\">");
		tmp.append("<div class=\"modal-header\">");
		tmp.append("<h1 class=\"modal-title fs-5\" id=\"addFeedbackLabel\">Tạo phản hồi</h1>");
		tmp.append("<button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button>");
		tmp.append("</div>");
		tmp.append("<div class=\"modal-body\">");
		
		tmp.append("<div class=\"row\">");
			tmp.append("<div class=\"col-lg-6\">");
				tmp.append("<label for=\"fullname\" class=\"form-label\">Họ tên</label>");
				tmp.append("<input type=\"text\" class=\"form-control\" id=\"fullname\" placeholder=\"Tên đầy đủ\" name=\"txtFullname\" required>");
				tmp.append("<div class=\"invalid-feedback\">Hãy nhập họ tên</div>");
			tmp.append("</div>");
			tmp.append("<div class=\"col-lg-6\">");
				tmp.append("<label for=\"email\" class=\"form-label\">Email</label>");
				tmp.append("<input type=\"text\" class=\"form-control\" id=\"email\" placeholder=\"Email\" name=\"txtEmail\" required>");
				tmp.append("<div class=\"invalid-feedback\">Hãy nhập email</div>");
			tmp.append("</div>");
		tmp.append("</div>");
		
		tmp.append("<div class=\"row mt-2\">");
			tmp.append("<div class=\"col-lg-6\">");
				tmp.append("<label for=\"title\" class=\"form-label\">Tiêu đề</label>");
				tmp.append("<input type=\"text\" class=\"form-control\" id=\"title\" placeholder=\"Tiêu đề\" name=\"txtTitle\" required>");
				tmp.append("<div class=\"invalid-feedback\">Hãy nhập tiêu đề</div>");
			tmp.append("</div>");
		tmp.append("</div>");
		
		tmp.append("<div class=\"row mt-2\">");
			tmp.append("<div class=\"col-lg-12\">");
				tmp.append("<label for=\"detail\" class=\"form-label\">Chi tiết</label>");
				tmp.append("<textarea class=\"form-control\" id=\"detail\" rows=\"10\" placeholder=\"Nội dung\" name=\"txtContent\"></textarea>");
			tmp.append("</div>");
		tmp.append("</div>");
		
		tmp.append("<input type=\"hidden\" name=\"idForPost\" value=\"\">");
		tmp.append("<input type=\"hidden\" name=\"act\" value=\"add\">");
		
		tmp.append("</div>");
		tmp.append("<div class=\"modal-footer\">");
		tmp.append("<button type=\"submit\" class=\"btn btn-primary btn-sm\">Gửi phản hồi</button>");
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

	private static StringBuilder viewDel(FeedbackObject item) {
		// TODO Auto-generated method stub
		StringBuilder tmp = new StringBuilder();
		//start modal
		
		tmp.append("<div class=\"modal fade\" id=\"delFeedback"+item.getFeedback_id()+"\" data-bs-backdrop=\"static\" data-bs-keyboard=\"false\" tabindex=\"-1\" aria-labelledby=\"staticBackdropLabel\" aria-hidden=\"true\">");
		tmp.append("<div class=\"modal-dialog modal-lg\">");
		//start from
		tmp.append("<form method=\"post\" action=\"\" class=\"needs-validation\" novalidate>");
		
		tmp.append("<div class=\"modal-content\">");
		tmp.append("<div class=\"modal-header\">");
		tmp.append("<h1 class=\"modal-title fs-5\" id=\"delRoomLabel\">Xóa phản hồi</h1>");
		tmp.append("<button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button>");
		tmp.append("</div>");
		tmp.append("<div class=\"modal-body\">");
		
		tmp.append("<div class=\"row\">");
			tmp.append("<div class=\"col-lg-12\">");
				tmp.append("<p>Bạn chắc chắn muốn xóa phản hồi: "+item.getFeedback_title()+"?</p>");
			tmp.append("</div>");
		tmp.append("</div>");
		
		tmp.append("<input type=\"hidden\" name=\"idForPost\" value=\""+item.getFeedback_id()+"\">");
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

	private static StringBuilder viewDetail(FeedbackObject item) {
		// TODO Auto-generated method stub
		StringBuilder tmp = new StringBuilder();
		//start modal
		tmp.append("<div class=\"modal fade\" id=\"viewFeedback"+item.getFeedback_id()+"\" data-bs-backdrop=\"static\" data-bs-keyboard=\"false\" tabindex=\"-1\" aria-labelledby=\"staticBackdropLabel\" aria-hidden=\"true\">");
		tmp.append("<div class=\"modal-dialog modal-lg\">");
		//start from
		tmp.append("<form method=\"post\" action=\"\" class=\"needs-validation\" novalidate>");
		
		tmp.append("<div class=\"modal-content\">");
		tmp.append("<div class=\"modal-header\">");
		tmp.append("<h1 class=\"modal-title fs-5\" id=\"addRoomLabel\">Phản hồi</h1>");
		tmp.append("<button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button>");
		tmp.append("</div>");
		tmp.append("<div class=\"modal-body\">");
		
		tmp.append("<div class=\"row\">");
			tmp.append("<div class=\"col-lg-6\">");
				tmp.append("<label for=\"fullname\" class=\"form-label\">Họ tên</label>");
				tmp.append("<input type=\"text\" class=\"form-control\" id=\"fullname\" value=\""+item.getFeedback_fullname()+"\"  disabled>");
			tmp.append("</div>");
			tmp.append("<div class=\"col-lg-6\">");
				tmp.append("<label for=\"email\" class=\"form-label\">Email</label>");
				tmp.append("<input type=\"text\" class=\"form-control\" id=\"email\" value=\""+item.getFeedback_email()+"\"  disabled>");
			tmp.append("</div>");
		tmp.append("</div>");
		
		tmp.append("<div class=\"row mt-2\">");
			tmp.append("<div class=\"col-lg-6\">");
				tmp.append("<label for=\"title\" class=\"form-label\">Tiêu đề</label>");
				tmp.append("<input type=\"text\" class=\"form-control\" id=\"title\" value=\""+item.getFeedback_title()+"\" disabled>");
			tmp.append("</div>");
			tmp.append("<div class=\"col-lg-6\">");
				tmp.append("<label for=\"createdate\" class=\"form-label\">Ngày tạo</label>");
				tmp.append("<input type=\"text\" class=\"form-control\" id=\"createdate\" value=\""+item.getFeedback_created_date()+"\"  disabled>");
			tmp.append("</div>");
		tmp.append("</div>");
		
		tmp.append("<div class=\"row mt-2\">");
			tmp.append("<div class=\"col-lg-12\">");
				tmp.append("<label for=\"detail\" class=\"form-label\">Chi tiết</label>");
				tmp.append("<textarea class=\"form-control\" id=\"detail\" rows=\"10\" disabled>"+item.getFeedback_content()+"</textarea>");
			tmp.append("</div>");
		tmp.append("</div>");
		
		tmp.append("<input type=\"hidden\" name=\"idForPost\" value=\""+item.getFeedback_id()+"\">");
		tmp.append("<input type=\"hidden\" name=\"act\" value=\"edit\">");
		
		tmp.append("</div>");
		tmp.append("<div class=\"modal-footer\">");
		tmp.append("<button type=\"submit\" class=\"btn btn-primary btn-sm\">Đánh dấu đã xem</button>");
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
	
}
