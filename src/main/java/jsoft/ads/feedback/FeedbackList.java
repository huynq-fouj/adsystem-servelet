package jsoft.ads.feedback;

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
import jsoft.ConnectionPoolImpl;
import jsoft.ads.user.UserControl;
import jsoft.library.Utilities;
import jsoft.library.Utilities_date;
import jsoft.objects.FeedbackObject;
import jsoft.objects.UserObject;

/**
 * Servlet implementation class FeedbackList
 */
@WebServlet("/feedback/list")
public class FeedbackList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String CONTENT_TYPE = "text/html; charset=utf-8";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FeedbackList() {
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
		FeedbackControl fc = new FeedbackControl(cp);
		if(cp != null) {
			getServletContext().setAttribute("CPool", fc.getCP());
		}
		
		String title = "Danh sách phản hồi";
		String pos = "fblist";
		
		short page = Utilities.getShortParam(request, "page");
		if(page < 1) {
			page = 1;
		}
		
		FeedbackObject similar = new FeedbackObject();
		boolean getState = false;
		short state_param = Utilities.getShortParam(request, "state");
		if(state_param > 0) {
			getState = true;
			if(state_param == 1) {
				similar.setFeedback_view(true);
			}
			if(state_param == 2) {
				similar.setFeedback_view(false);
			}
		}
		Quartet<FeedbackObject, Short, Byte, Boolean> infors = new Quartet<>(similar, page, (byte) 10, getState);
		ArrayList<String> viewList = fc.viewFeedbacks(infors, user);
		
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
		out.append("<li class=\"breadcrumb-item\">Phản hồi</li>");
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
		request.setCharacterEncoding("utf-8");
		int id = Utilities.getIntParam(request, "idForPost");
		String action = request.getParameter("act");
		if(action != null && action.equalsIgnoreCase("add")) {
			String fullname = request.getParameter("txtFullname");
			String email = request.getParameter("txtEmail");
			String title = request.getParameter("txtTitle");
			if(fullname != null && !fullname.equalsIgnoreCase("")
					&& email != null && !email.equalsIgnoreCase("")
					&& title != null && !title.equalsIgnoreCase("")) {
				String content = request.getParameter("txtContent");
				FeedbackObject fb = new FeedbackObject();
				fb.setFeedback_content(Utilities.encode(content));
				fb.setFeedback_fullname(Utilities.encode(fullname));
				fb.setFeedback_title(Utilities.encode(title));
				fb.setFeedback_email(email);
				fb.setFeedback_created_date(Utilities_date.getDate());
				ConnectionPool cp = (ConnectionPool) getServletContext().getAttribute("CPool");
				FeedbackControl uc = new FeedbackControl(cp);
				if(cp == null) {
					getServletContext().setAttribute("CPool", uc.getCP());
				}
				boolean result = uc.addFeedback(fb);
				uc.releaseConnection();
				if(result) {
					response.sendRedirect("/adv/feedback/list");
				} else {
					response.sendRedirect("/adv/feedback/list?err=add");
				}
			} else {
				response.sendRedirect("/adv/feedback/list?err=value");
			}
		}else {
			if(id > 0) {
				if(action != null && action.equalsIgnoreCase("edit")) {
					ConnectionPool cp = (ConnectionPool) getServletContext().getAttribute("CPool");
					FeedbackControl uc = new FeedbackControl(cp);
					if(cp == null) {
						getServletContext().setAttribute("CPool", uc.getCP());
					}
					FeedbackObject fb = uc.getFeedback(id);
					if(fb != null) {
						fb.setFeedback_view(true);
						boolean result = uc.editFeedback(fb);
						uc.releaseConnection();
						if(result) {
							response.sendRedirect("/adv/feedback/list");
						} else {
							response.sendRedirect("/adv/feedback/list?err=edit");
						}
					} else {
						response.sendRedirect("/adv/feedback/list?err=notok");
					}
				} else if(action != null && action.equalsIgnoreCase("del")) {
					FeedbackObject fb = new FeedbackObject();
					fb.setFeedback_id(id);
					ConnectionPool cp = (ConnectionPool) getServletContext().getAttribute("CPool");
					FeedbackControl uc = new FeedbackControl(cp);
					if(cp == null) {
						getServletContext().setAttribute("CPool", uc.getCP());
					}
					boolean result = uc.delFeedback(fb);
					uc.releaseConnection();
					if(result) {
						response.sendRedirect("/adv/feedback/list");
					}else {
						response.sendRedirect("/adv/feedback/list?err=del");
					}
				} else {
					response.sendRedirect("/adv/feedback/list?err=notok");
				}
			} else {
				response.sendRedirect("/adv/feedback/list?err=e");
			}
		}
	}

}
