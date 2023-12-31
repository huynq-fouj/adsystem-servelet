package jsoft.ads.room;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.javatuples.Quartet;

import jsoft.ConnectionPool;
import jsoft.library.Utilities;
import jsoft.objects.RoomObject;
import jsoft.objects.UserObject;

/**
 * Servlet implementation class RoomList
 */
@WebServlet("/room/list")
public class RoomList extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final String CONTENT_TYPE = "text/html; charset=utf-8";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RoomList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		UserObject user = (UserObject) request.getSession().getAttribute("userLogined");
		if(user != null) {
			view(request, response, user);
		}else {
			response.sendRedirect("/adv/user/login");
		}
	}

	private void view(HttpServletRequest request, HttpServletResponse response, UserObject user) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType(CONTENT_TYPE);
		PrintWriter out = response.getWriter();
		
		ConnectionPool cp = (ConnectionPool) getServletContext().getAttribute("CPool");
		RoomControl rc = new RoomControl(cp);
		if(cp != null) {
			getServletContext().setAttribute("CPool", rc.getCP());
		}
		
		String title = "Danh sách phòng";
		String pos = "rolist";
		
		short page = Utilities.getShortParam(request, "page");
		if(page < 1) {
			page = 1;
		}
		
		RoomObject similar = new RoomObject();		
		Quartet<RoomObject, Short, Byte, ROOM_SORT_TYPE> infors = new Quartet<>(similar, page, (byte) 10, ROOM_SORT_TYPE.ID_INC);
		ArrayList<String> viewList = rc.viewRoom(infors);
		
		RequestDispatcher header = request.getRequestDispatcher("/header?pos="+pos);		
		if(header != null) {
			header.include(request, response);
		}
		

		out.append("<main id=\"main\" class=\"main\">");

		RequestDispatcher error = request.getRequestDispatcher("/error");
		if(error != null) {
			error.include(request, response);
		}
		
		out.append("<div class=\"pagetitle d-flex\">");
		out.append("<h1>"+title+"</h1>");
		out.append("<nav class=\"ms-auto\">");
		out.append("<ol class=\"breadcrumb\">");
		out.append("<li class=\"breadcrumb-item d-flex\"><a href=\"index.html\"><i class=\"bi bi-house\"></i></a></li>");
		out.append("<li class=\"breadcrumb-item\">Phòng</li>");
		out.append("<li class=\"breadcrumb-item active\">Danh sách</li>");
		out.append("</ol>");
		out.append("</nav>");
		out.append("</div><!-- End Page Title -->");

		out.append("<section class=\"section\">");
		out.append("<div class=\"row\">");
		out.append("<div class=\"col-lg-12\">");
		out.append("<div class=\"card\">");
		out.append("<div class=\"card-body\">");
		
		//Start Modal
		out.append(viewList.get(2));
		//End Modal
		
		out.append(viewList.get(0));
		
		out.append(viewList.get(1)); // Phần trình bày phân trang
		out.append("</div>"); // card-body
		out.append("</div>"); // card
		out.append("</div>"); // col-lg-12
		out.append("</div>"); // row
		
		out.append("</section>");
		
		out.append("</main><!-- End #main -->");

		RequestDispatcher footer = request.getRequestDispatcher("/footer");
		if(footer != null) {
			footer.include(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
