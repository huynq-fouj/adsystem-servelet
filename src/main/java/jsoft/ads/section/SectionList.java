package jsoft.ads.section;

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
import jsoft.ads.user.USER_SORT_TYPE;
import jsoft.ads.user.UserControl;
import jsoft.library.Utilities;
import jsoft.library.Utilities_date;
import jsoft.objects.SectionObject;
import jsoft.objects.UserObject;

/**
 * Servlet implementation class SectionList
 */
@WebServlet("/section/list")
public class SectionList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String CONTENT_TYPE = "text/html; charset=utf-8";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SectionList() {
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
	
	protected void view(HttpServletRequest request, HttpServletResponse response, UserObject user) throws ServletException, IOException {
		response.setContentType(CONTENT_TYPE);
		PrintWriter out = response.getWriter();
		
		// Thiết lập tập ký tự cần lấy. Việc thiết lập này cần xác định từ đầu
		request.setCharacterEncoding("utf-8");
		
		ConnectionPool cp = (ConnectionPool) getServletContext().getAttribute("CPool");
		SectionControl sc = new SectionControl(cp);
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
		ArrayList<String> viewList = sc.viewSection(new Quartet<>(null, page, (byte)100, user));
		sc.releaseConnection();
		RequestDispatcher header = request.getRequestDispatcher("/header?pos=arselist");		
		if(header != null) {
			header.include(request, response);
		}
		

		out.append("<main id=\"main\" class=\"main\">");

		RequestDispatcher error = request.getRequestDispatcher("/error");
		if(error != null) {
			error.include(request, response);
		}
		
		out.append("<div class=\"pagetitle d-flex\">");
		out.append("<h1>Danh sách chuyên mục</h1>");
		out.append("<nav class=\"ms-auto\">");
		out.append("<ol class=\"breadcrumb\">");
		out.append("<li class=\"breadcrumb-item d-flex\"><a href=\"index.html\"><i class=\"bi bi-house\"></i></a></li>");
		out.append("<li class=\"breadcrumb-item\">Bài viết & Tin tức</li>");
		out.append("<li class=\"breadcrumb-item active\">Chuyên mục</li>");
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
		out.append("Thêm mới");
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
					out.append("<input type=\"text\" class=\"form-control\" id=\"sectionname\" name=\"txtSectionName\" required>");
					out.append("<div class=\"invalid-feedback\">Hãy nhập tên chuyên mục</div>");
				out.append("</div>");
				out.append("<div class=\"col-lg-6\">");
					out.append("<label for=\"manager\" class=\"form-label\">Người quản lý</label>");
					out.append("<select class=\"form-select\" id=\"manager\" name=\"slcManager\" required>");
						out.append(viewList.get(1));
					out.append("</select>");
					out.append("<div class=\"invalid-feedback\">Hãy chọn quản lý</div>");
				out.append("</div>");
			out.append("</div>");
			out.append("<div class=\"row\">");
				out.append("<div class=\"col-lg-12\">");
					out.append("<label for=\"sectionname\" class=\"form-label\">Ghi chú</label>");
					out.append("<textarea type=\"text\" class=\"form-control\" rows=\"10\" id=\"sectionnote\" name=\"txtSectionNote\"></textarea>");
				out.append("</div>");
			out.append("</div>");
		out.append("</div>");//modal body
		
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
				String sectionName = request.getParameter("txtSectionName");
				int sectionManagerId = Utilities.getIntParam(request, "slcManager");
				if(sectionName != null && !sectionName.equalsIgnoreCase("")
						&& sectionManagerId > 0) {
					String sectionNote = request.getParameter("txtSectionNote");
					String date = Utilities_date.getDate();
					SectionObject nSection = new SectionObject();
					nSection.setSection_created_author_id(user.getUser_id());
					nSection.setSection_manager_id(sectionManagerId);
					nSection.setSection_created_date(date);
					nSection.setSection_name(Utilities.encode(sectionName));
					nSection.setSection_notes(Utilities.encode(sectionNote));
					ConnectionPool cp = (ConnectionPool) getServletContext().getAttribute("CPool");
					SectionControl sc = new SectionControl(cp);
					if(cp != null) {
						getServletContext().setAttribute("CPool", sc.getCP());
					}
					boolean result = sc.addSection(nSection);
					sc.releaseConnection();
					if(result) {
						response.sendRedirect("/adv/section/list");
					}else {
						response.sendRedirect("/adv/section/list?err=notok");
					}
				}else {
					response.sendRedirect("/adv/section/list?err=value");
				}
			}else if(action.equalsIgnoreCase("edit")) {
				int id = Utilities.getIntParam(request, "idForPost");
				String sectionName = request.getParameter("txtSectionName");
				int sectionManagerId = Utilities.getIntParam(request, "slcManager");
				if(sectionName != null && !sectionName.equalsIgnoreCase("")
						&& sectionManagerId > 0 && id > 0) {
					String sectionNote = request.getParameter("txtSectionNote");
					String date = Utilities_date.getDate();
					boolean isEnable = Utilities.getBoolParam(request, "slcEnable");
					SectionObject eSection = new SectionObject();
					eSection.setSection_id(id);
					eSection.setSection_manager_id(sectionManagerId);
					eSection.setSection_last_modified(date);
					eSection.setSection_name(Utilities.encode(sectionName));
					eSection.setSection_notes(Utilities.encode(sectionNote));
					eSection.setSection_enable(isEnable);
					ConnectionPool cp = (ConnectionPool) getServletContext().getAttribute("CPool");
					SectionControl sc = new SectionControl(cp);
					if(cp != null) {
						getServletContext().setAttribute("CPool", sc.getCP());
					}
					boolean result = sc.editSection(eSection);
					sc.releaseConnection();
					if(result) {
						response.sendRedirect("/adv/section/list");
					}else {
						response.sendRedirect("/adv/section/list?err=notok");
					}
				}else {
					response.sendRedirect("/adv/section/list?err=value");
				}
			}else if(action.equalsIgnoreCase("del")) {
				int id = Utilities.getIntParam(request, "idForPost");
				if(user.getUser_permission() > 4) {
					if(id > 0) {
						SectionObject dSection = new SectionObject();
						dSection.setSection_id(id);
						ConnectionPool cp = (ConnectionPool) getServletContext().getAttribute("CPool");
						SectionControl sc = new SectionControl(cp);
						if(cp != null) {
							getServletContext().setAttribute("CPool", sc.getCP());
						}
						boolean result = sc.delSection(dSection);
						sc.releaseConnection();
						if(result) {
							response.sendRedirect("/adv/section/list");
						}else {
							response.sendRedirect("/adv/section/list?err=notok");
						}
					}else {
						response.sendRedirect("/adv/section/list?err=value");
					}
				}else {
					response.sendRedirect("/adv/section/list?err=nopermis");
				}
			}else {
				response.sendRedirect("/adv/section/list?err=notok");
			}
		}else {
			response.sendRedirect("/adv/section/list?err=notok");
		}
		
	}

}
