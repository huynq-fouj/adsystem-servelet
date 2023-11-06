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
			tmp.append("<td class=\"align-middle\"><button class=\"btn btn-success btn-sm\" data-bs-toggle=\"modal\" data-bs-target=\"#editRoom"+item.getRoom_id()+"\"><i class=\"bi bi-pencil-square\"></i></button></td>");
			tmp.append("");//View modal edit
			tmp.append("<td class=\"align-middle\"><button class=\"btn btn-danger btn-sm\" data-bs-toggle=\"modal\" data-bs-target=\"#delRoom"+item.getRoom_id()+"\"><i class=\"bi bi-trash\"></i></button></td>");
			tmp.append("");//View modal del
			
			tmp.append("</tr>\n");
		});
		
		tmp.append("</tbody>");
		tmp.append("</table>");
		
		ArrayList<String> view = new ArrayList<>();
		
		//Danh sach
		view.add(tmp.toString());
		//Phan trang
		view.add(RoomLibrary.getPaging(url, total, page, totalperpage));
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

	public static String getPaging(String url, int total, short page, byte totalperpage) {
		StringBuilder tmp = new StringBuilder();
		int countPage = total/totalperpage;
		if(total%totalperpage != 0) {
			countPage++;
		}
		
		
		
		return tmp.toString();
	}
	
}
