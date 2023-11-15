package jsoft.ads.feedback;

import java.util.ArrayList;

import org.javatuples.Quartet;
import org.javatuples.Triplet;

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
		String url = "/adv/feedback/list";
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
		tmp.append("<th scope=\"col\" colspan=\"3\">Thực hiện</th>");
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
			tmp.append("");//View modal detail
			tmp.append(FeedbackLibrary.viewDetail(item));
			tmp.append("<td class=\"align-middle\"><button class=\"btn btn-success btn-sm\" data-bs-toggle=\"modal\" data-bs-target=\"#editFeedback"+item.getFeedback_id()+"\"><i class=\"bi bi-pencil-square\"></i></button></td>");
			tmp.append("");//View modal edit
			tmp.append(FeedbackLibrary.viewEdit(item));
			tmp.append("<td class=\"align-middle\"><button class=\"btn btn-danger btn-sm\" data-bs-toggle=\"modal\" data-bs-target=\"#delFeedback"+item.getFeedback_id()+"\"><i class=\"bi bi-trash\"></i></button></td>");
			tmp.append("");//View modal del
			tmp.append(FeedbackLibrary.viewDel(item));
			tmp.append("</tr>\n");
		});
		
		tmp.append("</tbody>");
		tmp.append("</table>");
		//Danh sách feedback
		view.add(tmp.toString());
		//Phân trang
		view.add(FeedbackLibrary.getPaging(url, total, page, totalperpage));
		return view;
	}
	
	private static StringBuilder viewStatic(boolean feedback_view) {
		// TODO Auto-generated method stub
		StringBuilder tmp = new StringBuilder();
		tmp.append(feedback_view ? "<span class=\"text-body-success\">Đã xem</span>" : "<span class=\"text-body-danger\">Chưa xem</span>");
		return tmp;
	}
	
	private static StringBuilder viewAdd() {
		
		return null;
	}

	private static StringBuilder viewDel(FeedbackObject item) {
		// TODO Auto-generated method stub
		return null;
	}

	private static StringBuilder viewEdit(FeedbackObject item) {
		// TODO Auto-generated method stub
		return null;
	}

	private static StringBuilder viewDetail(FeedbackObject item) {
		// TODO Auto-generated method stub
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
	
}
