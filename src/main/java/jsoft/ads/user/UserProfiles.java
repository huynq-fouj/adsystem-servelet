package jsoft.ads.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import jsoft.ConnectionPool;
import jsoft.library.Utilities;
import jsoft.library.Utilities_mailer;
import jsoft.library.Utilities_text;
import jsoft.objects.UserObject;

/**
 * Servlet implementation class UserProfiles
 */
@WebServlet("/user/profiles")
public class UserProfiles extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private final String CONTENT_TYPE = "text/html; charset=utf-8";   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserProfiles() {
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
		
		//Tim id nguowi su dung de cap nhat
		int id = Utilities.getIntParam(request, "id");
		
		UserObject eUser = null;
		
		if(id > 0) {
			ConnectionPool cp = (ConnectionPool) getServletContext().getAttribute("CPool");
			UserControl uc = new UserControl(cp);
			if(cp == null) {
				getServletContext().setAttribute("CPool", uc);
			}
			eUser = uc.getUserObject(id);
			uc.releaseConnection();
		}
		
		RequestDispatcher header = request.getRequestDispatcher("/header?pos=urlist");
		if(header != null) {
			header.include(request, response);
		}
		

		out.append("<main id=\"main\" class=\"main\">");

		RequestDispatcher error = request.getRequestDispatcher("/error");
		if(error != null) {
			error.include(request, response);
		}
		
		out.append("<div class=\"pagetitle d-flex\">");
		out.append("<h1>Danh sách người sử dụng</h1>");
		out.append("<nav class=\"ms-auto\">");
		out.append("<ol class=\"breadcrumb\">");
		out.append("<li class=\"breadcrumb-item d-flex\"><a href=\"index.html\"><i class=\"bi bi-house\"></i></a></li>");
		out.append("<li class=\"breadcrumb-item\">Người sử dụng</li>");
		out.append("<li class=\"breadcrumb-item active\">Cập nhật chi tiết</li>");
		out.append("</ol>");
		out.append("</nav>");
		out.append("</div><!-- End Page Title -->");

		out.append("<section class=\"section profile\">");
		out.append("<div class=\"row\">");
		//
		out.append("<div class=\"col-xl-4\">");
		out.append("<div class=\"card\">");
		out.append("<div class=\"card-body profile-card pt-4 d-flex flex-column align-items-center\">");
		
		if(eUser != null) {
		
			out.append("<img src=\"/adv/adimgs/profile-img.jpg\" alt=\"Profile\" class=\"rounded-circle\">");
			out.append("<h2>"+eUser.getUser_fullname()+"</h2>");
			out.append("<h3>"+eUser.getUser_job()+"</h3>");
			out.append("<div class=\"social-links mt-2\">");
			out.append("<a href=\"#\" class=\"twitter\"><i class=\"bi bi-twitter\"></i></a>");
			out.append("<a href=\"#\" class=\"facebook\"><i class=\"bi bi-facebook\"></i></a>");
			out.append("<a href=\"#\" class=\"instagram\"><i class=\"bi bi-instagram\"></i></a>");
			out.append("<a href=\"#\" class=\"linkedin\"><i class=\"bi bi-linkedin\"></i></a>");
			out.append("</div>");
		
		}
		
		out.append("</div>");
		out.append("</div>");
		out.append("</div>");
		out.append("<div class=\"col-xl-8\">");
		out.append("<div class=\"card\">");
		out.append("<div class=\"card-body pt-3\">");
		out.append("<!-- Bordered Tabs -->");
		out.append("<ul class=\"nav nav-tabs nav-tabs-bordered\">");
		out.append("<li class=\"nav-item\">");
		out.append("<button class=\"nav-link active\" data-bs-toggle=\"tab\" data-bs-target=\"#overview\"><i class=\"bi bi-info-square\"></i> Tổng quát</button>");
		out.append("</li>");
		out.append("<li class=\"nav-item\">");
		out.append("<button class=\"nav-link\" data-bs-toggle=\"tab\" data-bs-target=\"#edit\"><i class=\"bi bi-pencil-square\"></i> Chỉnh sửa</button>");
		out.append("</li>");
		out.append("<li class=\"nav-item\">");
		out.append("<button class=\"nav-link\" data-bs-toggle=\"tab\" data-bs-target=\"#settings\"><i class=\"bi bi-gear\"></i> Cài đặt</button>");
		out.append("</li>");
		out.append("<li class=\"nav-item\">");
		out.append("<button class=\"nav-link\" data-bs-toggle=\"tab\" data-bs-target=\"#change-password\"><i class=\"bi bi-pass\"></i> Đổi mật khẩu</button>");
		out.append("</li>");
		out.append("</ul>");
		out.append("<div class=\"tab-content pt-2\">");
		
		String summary = "", name = "", fullname = "", birthday = "";
		String address = "", email = "", phone = "", ophone = "", mphone = "", job = "", jobarea = "";
		short logined = 0;
		boolean isEdit = false;
		
		if(eUser != null) {
			summary = eUser.getUser_notes();
			name = eUser.getUser_name();
			fullname = eUser.getUser_fullname();
			address = eUser.getUser_address();
			email = eUser.getUser_email();
			phone = eUser.getUser_homephone();
			job = eUser.getUser_job();
			jobarea = eUser.getUser_jobarea();
			logined = eUser.getUser_logined();
			birthday = eUser.getUser_birthday();
			ophone = eUser.getUser_officephone();
			mphone = eUser.getUser_mobilephone();
			isEdit = true;
		}
		
		out.append("<div class=\"tab-pane fade show active profile-overview\" id=\"overview\">");
		out.append("<h5 class=\"card-title\">Tóm tắt</h5>");
		out.append("<p class=\"small fst-italic\">"+summary+"</p>");
		out.append("<h5 class=\"card-title\">Profile Details</h5>");
		out.append("<div class=\"row\">");
		out.append("<div class=\"col-lg-3 col-md-4 label \">Họ và tên</div>");
		out.append("<div class=\"col-lg-6 col-md-5\">"+fullname+"</div>");
		out.append("<div class=\"col-lg-3 col-md-3\">("+name+")</div>");
		out.append("</div>");
		out.append("<div class=\"row\">");
		out.append("<div class=\"col-lg-3 col-md-4 label\">Nghề nghiệp</div>");
		out.append("<div class=\"col-lg-9 col-md-8\">"+job+"</div>");
		out.append("</div>");
		out.append("<div class=\"row\">");
		out.append("<div class=\"col-lg-3 col-md-4 label\">Lĩnh vực</div>");
		out.append("<div class=\"col-lg-9 col-md-8\">"+jobarea+"</div>");
		out.append("</div>");
		out.append("<div class=\"row\">");
		out.append("<div class=\"col-lg-3 col-md-4 label\">Địa chỉ</div>");
		out.append("<div class=\"col-lg-9 col-md-8\">"+address+"</div>");
		out.append("</div>");
		out.append("<div class=\"row\">");
		out.append("<div class=\"col-lg-3 col-md-4 label\">Điện thoại</div>");
		out.append("<div class=\"col-lg-9 col-md-8\">"+phone+"</div>");
		out.append("</div>");
		out.append("<div class=\"row\">");
		out.append("<div class=\"col-lg-3 col-md-4 label\">Hộp thư</div>");
		out.append("<div class=\"col-lg-9 col-md-8\">"+email+"</div>");
		out.append("</div>");
		out.append("<div class=\"row\">");
		out.append("<div class=\"col-lg-3 col-md-4 label\">Lần đăng nhập</div>");
		out.append("<div class=\"col-lg-9 col-md-8\">"+logined+"</div>");
		out.append("</div>");
		out.append("</div>");
		out.append("<div class=\"tab-pane fade profile-edit pt-3\" id=\"edit\">");
		
		
		//FORM EDIT
		out.append("<!-- Profile Edit Form -->");
		out.append("<form method=\"post\" action=\"/adv/user/profiles\" >");
		out.append("<div class=\"row mb-3 align-items-center\">");
		out.append("<label for=\"profileImage\" class=\"col-md-3 col-lg-2 text-end\">Avatar</label>");
		out.append("<div class=\"col-md-9 col-lg-10\">");
		out.append("<img src=\"assets/img/profile-img.jpg\" alt=\"Profile\">");
		out.append("<div class=\"pt-2\">");
		out.append("<a href=\"#\" class=\"btn btn-primary btn-sm\" title=\"Upload new profile image\"><i class=\"bi bi-upload\"></i></a>");
		out.append("<a href=\"#\" class=\"btn btn-danger btn-sm\" title=\"Remove my profile image\"><i class=\"bi bi-trash\"></i></a>");
		out.append("</div>");
		out.append("</div>");
		out.append("</div>");
		out.append("<div class=\"row mb-3 align-items-center\">");
		out.append("<label for=\"fullName\" class=\"col-md-3 col-lg-2 text-end\">Họ và tên</label>");
		out.append("<div class=\"col-md-6 col-lg-7\">");
		out.append("<div class=\"input-group\">");
		out.append("<input name=\"txtFullname\" type=\"text\" class=\"form-control\" id=\"fullName\" value=\""+fullname+"\">");
		out.append("<input name=\"txtAlias\" type=\"text\" class=\"form-control\" id=\"alias\" readonly>");
		out.append("</div>");
		out.append("</div>");
		out.append("<div class=\"col-md-3 col-lg-3\">");
		out.append("<input name=\"name\" type=\"text\" class=\"form-control\" id=\"name\" value=\""+name+"\" disabled>");
		out.append("</div>");
		out.append("</div>");
		out.append("<div class=\"row mb-3 align-items-center\">");
		out.append("<label for=\"notes\" class=\"col-md-3 col-lg-2 text-end\">Tóm tắt</label>");
		out.append("<div class=\"col-md-9 col-lg-10\">");
		out.append("<textarea name=\"txtNotes\" class=\"form-control\" id=\"notes\" style=\"height: 100px\">"+summary+"</textarea>");
		out.append("</div>");
		out.append("</div>");
		out.append("<div class=\"row mb-3 align-items-center\">");
		out.append("<label for=\"date\" class=\"col-md-3 col-lg-2 text-end\">Ngày sinh</label>");
		out.append("<div class=\"col-md-3 col-lg-4\">");
		out.append("<input name=\"txtBirthday\" type=\"date\" class=\"form-control\" id=\"date\" value=\""+birthday+"\">");
		out.append("</div>");
		out.append("<label for=\"gt\" class=\"col-md-3 col-lg-2 text-end\">Giới tính</label>");
		out.append("<div class=\"col-md-3 col-lg-4\">");
		out.append("<select class=\"form-control\" id=\"gt\" name=\"slcGender\">");
		out.append("<option value=\"\">----</option>");
		out.append("<option value=\"0\">Nam</option>");
		out.append("<option value=\"1\">Nữ</option>");
		out.append("</select>");
		out.append("</div>");
		out.append("</div>");
		out.append("<div class=\"row mb-3 align-items-center\">");
		out.append("<label for=\"Job\" class=\"col-md-3 col-lg-2 text-end\">Nghề nghiệp</label>");
		out.append("<div class=\"col-md-3 col-lg-4\">");
		out.append("<input name=\"txtJob\" type=\"text\" class=\"form-control\" id=\"Job\" value=\""+job+"\">");
		out.append("</div>");
		out.append("<label for=\"jobarea\" class=\"col-md-3 col-lg-2 text-end\">Lĩnh vực</label>");
		out.append("<div class=\"col-md-3 col-lg-4\">");
		out.append("<input name=\"txtJobarea\" type=\"text\" class=\"form-control\" id=\"jobarea\" value=\""+jobarea+"\">");
		out.append("</div>");
		out.append("</div>");
		out.append("<div class=\"row mb-3 align-items-center\">");
		out.append("<label for=\"Address\" class=\"col-md-3 col-lg-2 text-end\">Địa chỉ</label>");
		out.append("<div class=\"col-md-9 col-lg-10\">");
		out.append("<input name=\"txtAddress\" type=\"text\" class=\"form-control\" id=\"Address\" value=\""+address+"\">");
		out.append("</div>");
		out.append("</div>");
		out.append("<div class=\"row mb-3 align-items-center\">");
		out.append("<label for=\"Phone\" class=\"col-md-3 col-lg-2 text-end\">Điện thoại</label>");
		out.append("<div class=\"col-md-9 col-lg-10\">");
		out.append("<div class=\"input-group\">");
		out.append("<input name=\"txtHPhone\" type=\"text\" class=\"form-control\" id=\"Phone\" value=\""+phone+"\" placeholder=\"Home phone\">");
		out.append("<input name=\"txtOPhone\" type=\"text\" class=\"form-control\" id=\"Phone\" value=\""+ophone+"\" placeholder=\"Office phone\">");
		out.append("<input name=\"txtMPhone\" type=\"text\" class=\"form-control\" id=\"Phone\" value=\""+mphone+"\" placeholder=\"Mobile phone\">");
		out.append("</div>");
		out.append("</div>");
		out.append("</div>");
		out.append("<div class=\"row mb-3 align-items-center\">");
		out.append("<label for=\"Email\" class=\"col-md-3 col-lg-2 text-end\">Hộp thư</label>");
		out.append("<div class=\"col-md-3 col-lg-4\">");
		out.append("<input name=\"txtEmail\" type=\"email\" class=\"form-control\" id=\"Email\" value=\""+email+"\">");
		out.append("</div>");
		out.append("<label for=\"Facebook\" class=\"col-md-3 col-lg-2 text-end\">Facebook</label>");
		out.append("<div class=\"col-md-3 col-lg-4\">");
		out.append("<input name=\"facebook\" type=\"text\" class=\"form-control\" id=\"Facebook\" value=\"https://facebook.com/#\">");
		out.append("</div>");
		out.append("</div>");
		//Truyen id theo co che bien form an de thuc hien edit neu id > 0
		if(isEdit) {
			out.append("<input type=\"hidden\" name=\"idForPost\" value=\""+id+"\">");
			out.append("<input type=\"hidden\" name=\"act\" value=\"edit\">");
		}
		out.append("<div class=\"text-center\">");
		out.append("<button type=\"submit\" class=\"btn btn-primary\"><i class=\"bi bi-save\"></i> Lưu thay đổi</button>");
		out.append("</div>");
		out.append("</form><!-- End Profile Edit Form -->");
		
		out.append("</div>");
		out.append("<div class=\"tab-pane fade pt-3\" id=\"settings\">");
		
		//Setting form
		out.append("<form>");
		out.append("<div class=\"row mb-3\">");
		out.append("<label for=\"fullName\" class=\"col-md-4 col-lg-3 col-form-label\">Email Notifications</label>");
		out.append("<div class=\"col-md-8 col-lg-9\">");
		out.append("<div class=\"form-check\">");
		out.append("<input class=\"form-check-input\" type=\"checkbox\" id=\"changesMade\" checked>");
		out.append("<label class=\"form-check-label\" for=\"changesMade\">");
		out.append("Changes made to your account");
		out.append("</label>");
		out.append("</div>");
		out.append("<div class=\"form-check\">");
		out.append("<input class=\"form-check-input\" type=\"checkbox\" id=\"newProducts\" checked>");
		out.append("<label class=\"form-check-label\" for=\"newProducts\">");
		out.append("Information on new products and services");
		out.append("</label>");
		out.append("</div>");
		out.append("<div class=\"form-check\">");
		out.append("<input class=\"form-check-input\" type=\"checkbox\" id=\"proOffers\">");
		out.append("<label class=\"form-check-label\" for=\"proOffers\">");
		out.append("Marketing and promo offers");
		out.append("</label>");
		out.append("</div>");
		out.append("<div class=\"form-check\">");
		out.append("<input class=\"form-check-input\" type=\"checkbox\" id=\"securityNotify\" checked disabled>");
		out.append("<label class=\"form-check-label\" for=\"securityNotify\">");
		out.append("Security alerts");
		out.append("</label>");
		out.append("</div>");
		out.append("</div>");
		out.append("</div>");
		out.append("<div class=\"text-center\">");
		out.append("<button type=\"submit\" class=\"btn btn-primary\">Save Changes</button>");
		out.append("</div>");
		out.append("</form><!-- End settings Form -->");
		
		out.append("</div>");
		out.append("<div class=\"tab-pane fade pt-3\" id=\"change-password\">");
		
		//Change pass form
		out.append("<form method=\"post\" action=\"/adv/user/profiles\" >");
		if(id == user.getUser_id()) {
			out.append("<div class=\"row mb-3\">");
			out.append("<label for=\"currentPassword\" class=\"col-md-5 col-lg-4 col-form-label\">Mật khẩu hiện tại</label>");
			out.append("<div class=\"col-md-7 col-lg-8\">");
			out.append("<input name=\"txtCurrentPassword\" type=\"password\" class=\"form-control\" id=\"currentPassword\">");
			out.append("</div>");
			out.append("</div>");
		}else {
			out.append("<div class=\"row mb-3\">");
			out.append("<div class=\"col-md-5 col-lg-4\">");
			out.append("<button type=\"button\" class=\"btn btn-primary\" id=\"createRandomPass\">Tạo mật khẩu</button>");
			out.append("</div>");
			out.append("<div class=\"col-md-7 col-lg-8\">");
			out.append("<input type=\"text\" class=\"form-control\" id=\"preViewRanPass\" disabled>");
			out.append("</div>");
			out.append("</div>");
		}
		out.append("<div class=\"row mb-3\">");
		out.append("<label for=\"txtPass1\" class=\"col-md-5 col-lg-4 col-form-label\">Mật khẩu mới</label>");
		out.append("<div class=\"col-md-7 col-lg-8\">");
		out.append("<input name=\"txtNewPass1\" type=\"password\" class=\"form-control\" id=\"txtPass1\">");
		out.append("</div>");
		out.append("</div>");
		
		out.append("<div class=\"row mb-3\">");
		out.append("<label for=\"txtPass2\" class=\"col-md-5 col-lg-4 col-form-label\">Nhập lại mật khẩu mới</label>");
		out.append("<div class=\"col-md-7 col-lg-8\">");
		out.append("<input name=\"txtNewPass2\" type=\"password\" class=\"form-control\" id=\"txtPass2\">");
		out.append("</div>");
		out.append("</div>");
		
		if(isEdit) {
			out.append("<input type=\"hidden\" name=\"idForPost\" value=\""+id+"\">");
			out.append("<input type=\"hidden\" name=\"act\" value=\"pass\">");
		}
		
		out.append("<div class=\"text-center\">");
		out.append("<button type=\"submit\" class=\"btn btn-primary\">Đổi mật khẩu</button>");
		out.append("</div>");
		out.append("</form>");
		//<!-- End Change Password Form -->
		out.append("</div>");
		
		out.append("</div><!-- End Bordered Tabs -->");
		
		out.append("</div>");
		out.append("</div>");
		
		out.append("</div>");
		
		//
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
		request.setCharacterEncoding("utf-8");//thiết lập tập ký tự cần lấy
		
		int id = Utilities.getIntParam(request, "idForPost");
		String action = request.getParameter("act");
		
		if(id > 0) {
			if(action != null && action.equalsIgnoreCase("edit")) {
				String fullname = request.getParameter("txtFullname");
				String address = request.getParameter("txtAddress");
				String job = request.getParameter("txtJob");
				String email = request.getParameter("txtEmail");
				String phone = request.getParameter("txtHPhone");
				String jobarea = request.getParameter("txtJobarea");
				String notes = request.getParameter("txtNotes");
				String birthday = request.getParameter("txtBirthday");
				String alias = request.getParameter("txtAlias");
				if(fullname != null && !fullname.equalsIgnoreCase("") 
						&& email != null && !email.equalsIgnoreCase("") 
						&& phone != null && !phone.equalsIgnoreCase("") ) {
					
					UserObject nUser = new UserObject();
					nUser.setUser_id(id);
					nUser.setUser_email(email);
					nUser.setUser_address(Utilities.encode(address));
					nUser.setUser_homephone(phone);
					nUser.setUser_fullname(Utilities.encode(fullname));
					nUser.setUser_job(Utilities.encode(job));
					nUser.setUser_birthday(birthday);
					nUser.setUser_jobarea(Utilities.encode(jobarea));
					nUser.setUser_notes(Utilities.encode(notes));
					nUser.setUser_alias(alias);
					ConnectionPool cp = (ConnectionPool) getServletContext().getAttribute("CPool");
					UserControl uc = new UserControl(cp);
					if(cp == null) {
						getServletContext().setAttribute("CPool", uc.getCP());
					}
					//Cap nhat user
					boolean result = uc.editUser(nUser, USER_EDIT_TYPE.GENERAL);
					uc.releaseConnection();
					if(result) {
						response.sendRedirect("/adv/user/list");
					}else {
						response.sendRedirect("/adv/user/list?err=edit");
					}
				}else {
					response.sendRedirect("/adv/user/list?err=upd");
				}
			}else if(action != null && action.equalsIgnoreCase("pass")) {
				UserObject user = (UserObject) request.getSession().getAttribute("userLogined");
				String pass1 = request.getParameter("txtNewPass1");
				String pass2 = request.getParameter("txtNewPass2");
				if(Utilities_text.checkValidPass(pass1, pass2)) {
					//Kiểm tra có phải là user đang đăng nhập hay không
					if(user.getUser_id() == id) {
						String currentPass = request.getParameter("txtCurrentPassword");
						//Kiểm tra giá trị mật khẩu hiện tại được nhập vào
						if(currentPass != null && !currentPass.equalsIgnoreCase("")) {
							ConnectionPool cp = (ConnectionPool) getServletContext().getAttribute("CPool");
							UserControl uc = new UserControl(cp);
							if(cp == null) {
								getServletContext().setAttribute("CPool", uc.getCP());
							}
							//Kiểm tra có đúng mật khẩu hiện tại hay không
							if(uc.getUserObject(user.getUser_name(), currentPass) != null) {
								//Mật khẩu mới không được trùng với mật khẩu hiện tại
								if(!currentPass.equals(pass2)) {
									user.setUser_pass(pass2);
									//Cap nhat user
									boolean result = uc.editUser(user, USER_EDIT_TYPE.PASS);
									uc.releaseConnection();
									if(result) {
										response.sendRedirect("/adv/user/list");
									}else {
										response.sendRedirect("/adv/user/list?err=edit");
									}
								}else {
									response.sendRedirect("/adv/user/profiles?id="+id+"&err=eqlpass");
								}
							}else {
								response.sendRedirect("/adv/user/profiles?id="+id+"&err=curpass");
							}
						}else {
							response.sendRedirect("/adv/user/profiles?id="+id+"&err=curpassvalue");
						}
					}else {
						UserObject eUser = new UserObject();
						eUser.setUser_id(id);
						eUser.setUser_pass(pass2);
						ConnectionPool cp = (ConnectionPool) getServletContext().getAttribute("CPool");
						UserControl uc = new UserControl(cp);
						if(cp == null) {
							getServletContext().setAttribute("CPool", uc.getCP());
						}
						//Cap nhat user
						boolean result = uc.editUser(eUser, USER_EDIT_TYPE.PASS);
						eUser = uc.getUserObject(id);
						uc.releaseConnection();
						if(result) {
							if(Utilities_mailer.PasswordMailer(eUser.getUser_email(), eUser.getUser_name(), pass2)) {
								System.out.println("Success");
							}else {
								System.out.println("Failure");
							}
							response.sendRedirect("/adv/user/list");
						}else {
							response.sendRedirect("/adv/user/list?err=edit");
						}
					}
				}else {
					response.sendRedirect("/adv/user/profiles?id="+id+"&err=repass");
				}
			}else {
				//
			}
		}else {
			response.sendRedirect("/adv/user/list?err=profiles");
		}
	}

}
