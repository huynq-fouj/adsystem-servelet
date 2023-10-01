package jsoft.ads.user;

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
import org.javatuples.Quintet;

import jsoft.ConnectionPool;
import jsoft.library.Utilities;
import jsoft.library.Utilities_date;
import jsoft.library.Utilities_text;
import jsoft.objects.UserObject;

/**
 * Servlet implementation class UserList
 */

@WebServlet("/user/list")
public class UserList extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private final String CONTENT_TYPE = "text/html; charset=utf-8";   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserList() {
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
	
	

	protected void view(HttpServletRequest request, HttpServletResponse response, UserObject user) throws ServletException, IOException{
		// TODO Auto-generated method stub
		response.setContentType(CONTENT_TYPE);
		PrintWriter out = response.getWriter();
		
		// Thiết lập tập ký tự cần lấy. Việc thiết lập này cần xác định từ đầu
		request.setCharacterEncoding("utf-8");
		
		ConnectionPool cp = (ConnectionPool) getServletContext().getAttribute("CPool");
		UserControl uc = new UserControl(cp);
		if(cp != null) {
			getServletContext().setAttribute("CPool", uc.getCP());
		}
		
		//Lấy từ khóa tìm kiếm
		String key = request.getParameter("key");
		String saveKey = (key != null && !key.equals("")) ? key.trim() : "";
		
		UserObject similar = new UserObject();
		similar.setUser_id(user.getUser_id());
		similar.setUser_permission(user.getUser_permission());
		similar.setUser_name(saveKey);
		// Tìm tham số xác định loại danh sách
		String trash = request.getParameter("trash");
		String title, pos;
		if(trash == null) {
			similar.setUser_deleted(false);
			pos = "urlist";
			title = "Danh sách người sử dụng";
		} else {
			similar.setUser_deleted(true);
			pos = "urtrash";
			title = "Danh sách người bị xóa";
		}
		short page = Utilities.getShortParam(request, "page");
		if(page < 1) {
			page = 1;
		}
		ArrayList<String> viewList = uc.viewUsers(new Quintet<>(similar, page, (byte)5, USER_SORT_TYPE.NAME, saveKey));
		uc.releaseConnection();
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
		out.append("<li class=\"breadcrumb-item\">Người sử dụng</li>");
		out.append("<li class=\"breadcrumb-item active\">Danh sách</li>");
		out.append("</ol>");
		out.append("</nav>");
		out.append("</div><!-- End Page Title -->");

		out.append("<section class=\"section\">");
		out.append("<div class=\"row\">");
		out.append("<div class=\"col-lg-12\">");
		out.append("<div class=\"card\">");
		out.append("<div class=\"card-body\">");
		if(trash==null) {
			//start modal
			out.append("<button type=\"button\" class=\"btn btn-primary btn-sm mt-2\" data-bs-toggle=\"modal\" data-bs-target=\"#addUser\">");
			out.append("<i class=\"bi bi-person-plus\"></i> Thêm mới</i>");
			out.append("</button>");
			
			
			out.append("<div class=\"modal fade\" id=\"addUser\" data-bs-backdrop=\"static\" data-bs-keyboard=\"false\" tabindex=\"-1\" aria-labelledby=\"staticBackdropLabel\" aria-hidden=\"true\">");
			out.append("<div class=\"modal-dialog modal-lg\">");
			//start from
			out.append("<form method=\"post\" action=\"\" class=\"needs-validation\" novalidate>");
			
			out.append("<div class=\"modal-content\">");
			out.append("<div class=\"modal-header\">");
			out.append("<h1 class=\"modal-title fs-5\" id=\"addUserLabel\">Thêm mới người sử dụng</h1>");
			out.append("<button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button>");
			out.append("</div>");
			out.append("<div class=\"modal-body\">");
			
			out.append("<div class=\"row\">");
			out.append("<div class=\"col-lg-6\">");
			out.append("<label for=\"username\" class=\"form-label\">Tên tài khoản</label>");
			out.append("<input type=\"text\" class=\"form-control\" id=\"username\" name=\"txtUsername\" required>");
			out.append("<div class=\"invalid-feedback\">Hãy nhập tên của tài khoản</div>");
			out.append("</div>");
			out.append("<div class=\"col-lg-6\">");
			out.append("<label for=\"userfullname\" class=\"form-label\">Tên đầy đủ</label>");
			out.append("<input type=\"text\" class=\"form-control\" id=\"userfullname\" name=\"txtUserfullname\">");
			out.append("</div>");
			out.append("</div>");
			out.append("<div class=\"row\">");
			out.append("<div class=\"col-lg-6\">");
			out.append("<label for=\"userpass1\" class=\"form-label\">Mật khẩu</label>");
			out.append("<input type=\"password\" class=\"form-control\" id=\"userpass1\" name=\"txtUserpass1\" required>");
			out.append("<div class=\"invalid-feedback\">Hãy nhập mật khẩu</div>");
			out.append("</div>");
			out.append("<div class=\"col-lg-6\">");
			out.append("<label for=\"userpass2\" class=\"form-label\">Nhập lại mật khẩu</label>");
			out.append("<input type=\"password\" class=\"form-control\" id=\"userpass2\" name=\"txtUserpass2\" required>");
			out.append("<div class=\"invalid-feedback\">Nhập mật khẩu xác nhận cho tài khoản</div>");
			out.append("</div>");
			out.append("</div>");
			out.append("<div class=\"row\">");
			out.append("<div class=\"col-lg-6\">");
			out.append("<label for=\"useremail\" class=\"form-label\">Hộp thư</label>");
			out.append("<input type=\"email\" class=\"form-control\" id=\"useremail\" name=\"txtUseremail\" required>");
			out.append("<div class=\"invalid-feedback\">Hãy nhập hộp thư của tài khoản</div>");
			out.append("</div>");
			out.append("<div class=\"col-lg-6\">");
			out.append("<label for=\"userphone\" class=\"form-label\">Điện thoại</label>");
			out.append("<input type=\"text\" class=\"form-control\" id=\"userphone\" name=\"txtUserphone\" required>");
			out.append("<div class=\"invalid-feedback\">Hãy nhập điện thoại</div>");
			out.append("</div>");
			out.append("</div>");
			out.append("<div class=\"row mb-3\">");
			out.append("<div class=\"col-lg-6\">");
			out.append("<label for=\"userpermis\" class=\"form-label\">Quyền thực thi</label>");
			
			out.append("<select class=\"form-select\" id=\"userpermis\" name=\"slcPermis\" required>");
			out.append("<option value=\"\">--Chọn--</option>");
			out.append("<option value=\"1\">Thành viên</option>");
			out.append("<option value=\"2\">Tác giả</option>");
			out.append("<option value=\"3\">Quản lý</option>");
			out.append("<option value=\"4\">Quản trị</option>");
			out.append("<option value=\"5\">Quản trị cấp cao</option>");
			out.append("</select>");
			
			out.append("<div class=\"invalid-feedback\">Hãy chọn quyền thực thi</div>");
			out.append("</div>");
			out.append("<div class=\"col-lg-6\">");
			
			out.append("</div>");
			out.append("</div>");
			
			out.append("</div>");
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
		}
//		out.append("<p>This is an examle page with no contrnt. You can use it as a starter for your custom pages.</p>");
		
		out.append(viewList.get(0));
		
		out.append(viewList.get(1)); // Phần trình bày phân trang
		out.append("</div>"); // card-body
		out.append("</div>"); // card
		out.append("</div>"); // col-lg-12
		out.append("</div>"); // row
		
		out.append("<div class=\"row\">");
		out.append("<div class=\"col-lg-12\">");
		out.append(viewList.get(2)); // Phần trình bày biểu đồ
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
		
		request.setCharacterEncoding("utf-8");//thiết lập tập ký tự cần lấy
		String name = request.getParameter("txtUsername");
		String pass1 = request.getParameter("txtUserpass1");
		String pass2 = request.getParameter("txtUserpass2");
		String email = request.getParameter("txtUseremail");
		String phone = request.getParameter("txtUserphone");
		byte permis = Utilities.getByteParam(request, "slcPermis");
		if(name != null && !name.equalsIgnoreCase("") 
				&& Utilities_text.checkValidPass(pass1, pass2) 
				&& email != null && !email.equalsIgnoreCase("") 
				&& phone != null && !phone.equalsIgnoreCase("") 
				&& permis > 0) {
			String fullname = request.getParameter("txtUserfullname");
			
			UserObject nUser = new UserObject();
			nUser.setUser_name(name);
			nUser.setUser_email(email);
			nUser.setUser_pass(pass1);
			nUser.setUser_homephone(phone);
			nUser.setUser_fullname(Utilities.encode(fullname));
			nUser.setUser_parent_id(user.getUser_id());
			nUser.setUser_created_date(Utilities_date.getDate());
			nUser.setUser_permission(permis);
			ConnectionPool cp = (ConnectionPool) getServletContext().getAttribute("CPool");
			UserControl uc = new UserControl(cp);
			if(cp != null) {
				getServletContext().setAttribute("CPool", uc.getCP());
			}
			boolean result = uc.addUser(nUser);
			uc.releaseConnection();
			if(result) {
				response.sendRedirect("/adv/user/list");
			}else {
				response.sendRedirect("/adv/user/list?err=add");
			}
		}else {
			String key = request.getParameter("keyword");
			response.sendRedirect("/adv/user/list?key="+key);
		}
	}

}
