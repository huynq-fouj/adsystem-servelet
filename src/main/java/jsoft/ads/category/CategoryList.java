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
		
		
		short page = Utilities.getShortParam(request, "page");
		if(page < 1) {
			page = 1;
		}
		ArrayList<String> viewList = sc.viewCategory(new Triplet<>(null, page, (byte)10));
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
		
		//start modal
		out.append("<button type=\"button\" class=\"btn btn-primary btn-sm mt-2\" data-bs-toggle=\"modal\" data-bs-target=\"#addSection\">");
		out.append("<i class=\"bi bi-person-plus\"></i> Thêm mới</i>");
		out.append("</button>");
		
		
		out.append("<div class=\"modal fade\" id=\"addSection\" data-bs-backdrop=\"static\" data-bs-keyboard=\"false\" tabindex=\"-1\" aria-labelledby=\"staticBackdropLabel\" aria-hidden=\"true\">");
		out.append("<div class=\"modal-dialog modal-lg\">");
		//start from
		out.append("<form method=\"post\" action=\"\" class=\"needs-validation\" novalidate>");
		
		out.append("<div class=\"modal-content\">");
		out.append("<div class=\"modal-header\">");
		out.append("<h1 class=\"modal-title fs-5\" id=\"addSectionLabel\">Thêm mới chuyên mục</h1>");
		out.append("<button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button>");
		out.append("</div>");
		out.append("<div class=\"modal-body\">");
			out.append("<div class=\"row mb-3\">");
				out.append("<div class=\"col-lg-6\">");
					out.append("<label for=\"sectionname\" class=\"form-label\">Tên chuyên mục</label>");
					out.append("<input type=\"text\" class=\"form-control\" id=\"sectionname\" name=\"txtSectionname\" required>");
					out.append("<div class=\"invalid-feedback\">Hãy nhập tên của tài khoản</div>");
				out.append("</div>");
				out.append("<div class=\"col-lg-6\">");
					out.append("<label for=\"manager\" class=\"form-label\">Quản lý</label>");
					out.append("<select class=\"form-select\" id=\"manager\" name=\"slcManaher\" required>");
						out.append(viewList.get(1));
					out.append("</select>");
					out.append("<div class=\"invalid-feedback\">Hãy chọn quản lý</div>");
				out.append("</div>");
			out.append("</div>");
			out.append("<div class=\"row\">");
				out.append("<div class=\"col-lg-12\">");
					out.append("<label for=\"sectionname\" class=\"form-label\">Ghi chú</label>");
					out.append("<textarea type=\"text\" class=\"form-control\" rows=\"10\" id=\"sectionnote\" name=\"txtSectionnote\"></textarea>");
				out.append("</div>");
			out.append("</div>");
		out.append("</div>");//modal body
		out.append("<div class=\"modal-footer\">");
		out.append("<button type=\"submit\" class=\"btn btn-primary btn-sm\"><i class=\"bi bi-person-plus\"></i>Thêm mới</button>");
		out.append("<button type=\"button\" class=\"btn btn-secondary btn-sm\" data-bs-dismiss=\"modal\">Thoát</button>");
		out.append("</div>");
		out.append("</div>");
		
		out.append("</form>");
		//end from
		out.append("</div>");
		out.append("</div>");
		//end modal
		
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
		doGet(request, response);
	}

}
