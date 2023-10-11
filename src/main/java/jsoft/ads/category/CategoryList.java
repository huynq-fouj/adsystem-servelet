package jsoft.ads.category;

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
import org.javatuples.Triplet;

import jsoft.ConnectionPool;
import jsoft.ads.section.SectionControl;
import jsoft.library.Utilities;
import jsoft.objects.CategoryObject;
import jsoft.objects.SectionObject;
import jsoft.objects.UserObject;

/**
 * Servlet implementation class CategoryList
 */
@WebServlet("/category/list")
public class CategoryList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String CONTENT_TYPE = "text/html; charset=utf-8";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CategoryList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserObject user = (UserObject) request.getSession().getAttribute("userLogined");
		if(user != null) {
			view(request, response, user);
		}else {
			response.sendRedirect("/adv/user/login");
		}
	}
	protected void view(HttpServletRequest request, HttpServletResponse response, UserObject user) throws ServletException, IOException {
		response.setContentType(CONTENT_TYPE);
		PrintWriter out = response.getWriter();
		
		// Thiết lập tập ký tự cần lấy. Việc thiết lập này cần xác định từ đầu
		request.setCharacterEncoding("utf-8");
		
		ConnectionPool cp = (ConnectionPool) getServletContext().getAttribute("CPool");
		CategoryControl sc = new CategoryControl(cp);
		if(cp != null) {
			getServletContext().setAttribute("CPool", sc.getCP());
		}
		
		//Lấy từ khóa tìm kiếm
		String key = request.getParameter("key");
		String saveKey = (key != null && !key.equals("")) ? key.trim() : "";
		
		
		ArrayList<String> viewList = sc.viewCategory(new Quartet<>(null, (short) 1, (byte)10, user));
		sc.releaseConnection();
		RequestDispatcher header = request.getRequestDispatcher("/header?pos=arcalist");		
		if(header != null) {
			header.include(request, response);
		}
		

		out.append("<main id=\"main\" class=\"main\">");

		RequestDispatcher error = request.getRequestDispatcher("/error");
		if(error != null) {
			error.include(request, response);
		}
		
		out.append("<div class=\"pagetitle d-flex\">");
		out.append("<h1>Danh sách loại</h1>");
		out.append("<nav class=\"ms-auto\">");
		out.append("<ol class=\"breadcrumb\">");
		out.append("<li class=\"breadcrumb-item d-flex\"><a href=\"index.html\"><i class=\"bi bi-house\"></i></a></li>");
		out.append("<li class=\"breadcrumb-item\">Bài viết & Tin tức</li>");
		out.append("<li class=\"breadcrumb-item active\">Loại</li>");
		out.append("</ol>");
		out.append("</nav>");
		out.append("</div><!-- End Page Title -->");

		out.append("<section class=\"section\">");
		out.append("<div class=\"row\">");
		out.append("<div class=\"col-lg-12\">");
		out.append("<div class=\"card\">");
		out.append("<div class=\"card-body\">");
		
//		out.append("<p>This is an examle page with no contrnt. You can use it as a starter for your custom pages.</p>");
		
		//Modal
		out.append(viewList.get(1));
		
		out.append(viewList.get(0));
		
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
		UserObject user = (UserObject) request.getSession().getAttribute("userLogined");
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("act");
		if(action != null) {
			if(action.equalsIgnoreCase("add")) {
				
			}else if(action.equalsIgnoreCase("edit")) {
				
			}else if(action.equalsIgnoreCase("del")) {
				short id = Utilities.getShortParam(request, "idForPost");
				if(user.getUser_permission() > 4) {
					if(id > 0) {
						CategoryObject dCategory = new CategoryObject();
						dCategory.setCategory_id(id);
						ConnectionPool cp = (ConnectionPool) getServletContext().getAttribute("CPool");
						CategoryControl cc = new CategoryControl(cp);
						if(cp != null) {
							getServletContext().setAttribute("CPool", cc.getCP());
						}
						boolean result = cc.delCategory(dCategory);
						cc.releaseConnection();
						if(result) {
							response.sendRedirect("/adv/category/list");
						}else {
							response.sendRedirect("/adv/category/list?err=notok");
						}
					}else {
						response.sendRedirect("/adv/category/list?err=value");
					}
				}else {
					response.sendRedirect("/adv/category/list?err=nopermis");
				}
			}else {
				response.sendRedirect("/adv/category/list?err=notok");
			}
		}else {
			response.sendRedirect("/adv/category/list?err=notok");
		}
	}

}
