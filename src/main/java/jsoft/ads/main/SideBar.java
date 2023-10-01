package jsoft.ads.main;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SideBar
 */
@WebServlet("/sidebar")
public class SideBar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SideBar() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		
		HashMap<String, String> collapsed = new HashMap<>();
		HashMap<String, String> show = new HashMap<>();
		HashMap<String, String> actives = new HashMap<>();
		
		//Tim tham so vi tri
		String pos = request.getParameter("pos");
		if(pos != null) {
			String menu = pos.substring(0, 2);
			String act = pos.substring(2);
			switch(menu) {
				case "ur":
					collapsed.put("user", "");
					show.put("user", "show");
					
					switch(act) {
						case "list":
							actives.put("ulist", "class=\"active\"");
							break;
						case "upd":
							actives.put("upd", "class=\"classpath\"");
							break;
						case "trash":
							actives.put("utrash", "class=\"active\"");
							break;
						case "log":
							break;
					}
					
					break;
				case "ar":
					collapsed.put("article", "");
					show.put("article", "show");
					switch(act) {
						case "list":
							actives.put("arlist", "class=\"active\"");
							break;
						case "upd":
							actives.put("arupd", "class=\"active\"");
							break;
						case "selist":
							actives.put("selist", "class=\"active\"");
							break;
						case "calist":
							actives.put("calist", "class=\"active\"");
							break;
					}
					break;
			}
		}
		
		out.append("<!-- ======= Sidebar ======= -->");
		out.append("<aside id=\"sidebar\" class=\"sidebar\">");

		out.append("<ul class=\"sidebar-nav\" id=\"sidebar-nav\">");

		out.append("<li class=\"nav-item\">");
		out.append("<a class=\"nav-link "+collapsed.getOrDefault("Dashboarh", "collapsed")+"\" href=\"/adv/view\">");
		out.append("<i class=\"bi bi-house\"></i>");
		out.append("<span>Dashboard</span>");
		out.append("</a>");
		out.append("</li><!-- End Dashboard Nav -->");

		
		//
		out.append("<li class=\"nav-item\">");
		out.append(
				"<a class=\"nav-link "+collapsed.getOrDefault("user", "collapsed")+"\" data-bs-target=\"#user-nav\" data-bs-toggle=\"collapse\" href=\"#\">");
		out.append(
				"<i class=\"bi bi-people\"></i><span>Người sử dụng</span><i class=\"bi bi-chevron-down ms-auto\"></i>");
		out.append("</a>");
		out.append("<ul id=\"user-nav\" class=\"nav-content collapse "+show.getOrDefault("user", "")+"\" data-bs-parent=\"#sidebar-nav\">");
		//li
		out.append("<li>");
		out.append("<a href=\"/adv/user/list\" "+actives.getOrDefault("ulist", "")+">");
		out.append("<i class=\"bi bi-list\"></i><span>Danh sách</span>");
		out.append("</a>");
		out.append("</li>");
		//li
		out.append("<li>");
		out.append("<a href=\"/adv/user/list?trash\" "+actives.getOrDefault("utrash", "")+">");
		out.append("<i class=\"bi bi-trash3\"></i><span>Thùng rác</span>");
		out.append("</a>");
		out.append("</li>");
		
		out.append("</ul>");
		out.append("</li><!-- End Components Nav -->");

		
		//
		out.append("<li class=\"nav-item\">");
		out.append(
				"<a class=\"nav-link "+ collapsed.getOrDefault("article", "collapsed")+"\" data-bs-target=\"#article-nav\" data-bs-toggle=\"collapse\" href=\"#\">");
		out.append(
				"<i class=\"bi bi-journal-text\"></i><span>Bài viết & tin tức</span><i class=\"bi bi-chevron-down ms-auto\"></i>");
		out.append("</a>");
		out.append("<ul id=\"article-nav\" class=\"nav-content collapse "+ show.getOrDefault("article", "") +"\" data-bs-parent=\"#sidebar-nav\">");
		//li
		out.append("<li>");
		out.append("<a href=\"/adv/article/list\" "+actives.getOrDefault("arlist", "")+">");
		out.append("<i class=\"bi bi-circle\"></i><span>Danh sách</span>");
		out.append("</a>");
		out.append("</li>");
		//li
		out.append("<li>");
		out.append("<a href=\"/adv/article/upd\" "+actives.getOrDefault("arupd", "")+">");
		out.append("<i class=\"bi bi-circle\"></i><span>Thêm mới</span>");
		out.append("</a>");
		out.append("</li>");
		//li
		out.append("<li>");
		out.append("<a href=\"/adv/section/list\" "+actives.getOrDefault("selist", "")+">");
		out.append("<i class=\"bi bi-circle\"></i><span>Chuyên mục</span>");
		out.append("</a>");
		out.append("</li>");
		//li
		out.append("<li>");
		out.append("<a href=\"/adv/category/list\" "+actives.getOrDefault("calist", "")+">");
		out.append("<i class=\"bi bi-circle\"></i><span>Loại</span>");
		out.append("</a>");
		out.append("</li>");
		
		out.append("</ul>");
		out.append("</li><!-- End Forms Nav -->");

		
		//
		out.append("<li class=\"nav-item\">");
		out.append(
				"<a class=\"nav-link collapsed\" data-bs-target=\"#tables-nav\" data-bs-toggle=\"collapse\" href=\"#\">");
		out.append(
				"<i class=\"bi bi-layout-text-window-reverse\"></i><span>Tables</span><i class=\"bi bi-chevron-down ms-auto\"></i>");
		out.append("</a>");
		out.append("<ul id=\"tables-nav\" class=\"nav-content collapse \" data-bs-parent=\"#sidebar-nav\">");
		out.append("<li>");
		out.append("<a href=\"tables-general.html\">");
		out.append("<i class=\"bi bi-circle\"></i><span>General Tables</span>");
		out.append("</a>");
		out.append("</li>");
		out.append("<li>");
		out.append("<a href=\"tables-data.html\">");
		out.append("<i class=\"bi bi-circle\"></i><span>Data Tables</span>");
		out.append("</a>");
		out.append("</li>");
		out.append("</ul>");
		out.append("</li><!-- End Tables Nav -->");

		out.append("<li class=\"nav-item\">");
		out.append(
				"<a class=\"nav-link collapsed\" data-bs-target=\"#charts-nav\" data-bs-toggle=\"collapse\" href=\"#\">");
		out.append("<i class=\"bi bi-bar-chart\"></i><span>Charts</span><i class=\"bi bi-chevron-down ms-auto\"></i>");
		out.append("</a>");
		out.append("<ul id=\"charts-nav\" class=\"nav-content collapse \" data-bs-parent=\"#sidebar-nav\">");
		out.append("<li>");
		out.append("<a href=\"charts-chartjs.html\">");
		out.append("<i class=\"bi bi-circle\"></i><span>Chart.js</span>");
		out.append("</a>");
		out.append("</li>");
		out.append("<li>");
		out.append("<a href=\"charts-apexcharts.html\">");
		out.append("<i class=\"bi bi-circle\"></i><span>ApexCharts</span>");
		out.append("</a>");
		out.append("</li>");
		out.append("<li>");
		out.append("<a href=\"charts-echarts.html\">");
		out.append("<i class=\"bi bi-circle\"></i><span>ECharts</span>");
		out.append("</a>");
		out.append("</li>");
		out.append("</ul>");
		out.append("</li><!-- End Charts Nav -->");

		out.append("<li class=\"nav-item\">");
		out.append(
				"<a class=\"nav-link collapsed\" data-bs-target=\"#icons-nav\" data-bs-toggle=\"collapse\" href=\"#\">");
		out.append("<i class=\"bi bi-gem\"></i><span>Icons</span><i class=\"bi bi-chevron-down ms-auto\"></i>");
		out.append("</a>");
		out.append("<ul id=\"icons-nav\" class=\"nav-content collapse \" data-bs-parent=\"#sidebar-nav\">");
		out.append("<li>");
		out.append("<a href=\"icons-bootstrap.html\">");
		out.append("<i class=\"bi bi-circle\"></i><span>Bootstrap Icons</span>");
		out.append("</a>");
		out.append("</li>");
		out.append("<li>");
		out.append("<a href=\"icons-remix.html\">");
		out.append("<i class=\"bi bi-circle\"></i><span>Remix Icons</span>");
		out.append("</a>");
		out.append("</li>");
		out.append("<li>");
		out.append("<a href=\"icons-boxicons.html\">");
		out.append("<i class=\"bi bi-circle\"></i><span>Boxicons</span>");
		out.append("</a>");
		out.append("</li>");
		out.append("</ul>");
		out.append("</li><!-- End Icons Nav -->");

		out.append("<li class=\"nav-heading\">Pages</li>");

		out.append("<li class=\"nav-item\">");
		out.append("<a class=\"nav-link collapsed\" href=\"users-profile.html\">");
		out.append("<i class=\"bi bi-person\"></i>");
		out.append("<span>Profile</span>");
		out.append("</a>");
		out.append("</li><!-- End Profile Page Nav -->");

		out.append("<li class=\"nav-item\">");
		out.append("<a class=\"nav-link collapsed\" href=\"pages-faq.html\">");
		out.append("<i class=\"bi bi-question-circle\"></i>");
		out.append("<span>F.A.Q</span>");
		out.append("</a>");
		out.append("</li><!-- End F.A.Q Page Nav -->");

		out.append("<li class=\"nav-item\">");
		out.append("<a class=\"nav-link collapsed\" href=\"pages-contact.html\">");
		out.append("<i class=\"bi bi-envelope\"></i>");
		out.append("<span>Contact</span>");
		out.append("</a>");
		out.append("</li><!-- End Contact Page Nav -->");

		out.append("<li class=\"nav-item\">");
		out.append("<a class=\"nav-link collapsed\" href=\"pages-register.html\">");
		out.append("<i class=\"bi bi-card-list\"></i>");
		out.append("<span>Register</span>");
		out.append("</a>");
		out.append("</li><!-- End Register Page Nav -->");

		out.append("<li class=\"nav-item\">");
		out.append("<a class=\"nav-link collapsed\" href=\"pages-login.html\">");
		out.append("<i class=\"bi bi-box-arrow-in-right\"></i>");
		out.append("<span>Login</span>");
		out.append("</a>");
		out.append("</li><!-- End Login Page Nav -->");

		out.append("<li class=\"nav-item\">");
		out.append("<a class=\"nav-link collapsed\" href=\"pages-error-404.html\">");
		out.append("<i class=\"bi bi-dash-circle\"></i>");
		out.append("<span>Error 404</span>");
		out.append("</a>");
		out.append("</li><!-- End Error 404 Page Nav -->");

		out.append("</ul>");

		out.append("</aside><!-- End Sidebar-->");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}