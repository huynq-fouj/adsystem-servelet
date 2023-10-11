package jsoft.ads.main;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class error
 */
@WebServlet("/error")
public class error extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public error() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		
		String err = request.getParameter("err");
		if(err != null) {
			out.append("<div class=\"toast-container position-fixed top-0 start-50 translate-middle-x p-3\">");
			out.append("<div id=\"liveToast\" class=\"toast\" role=\"alert\" aria-live=\"assertive\" aria-atomic=\"true\">");
			out.append("<div class=\"toast-header\">");
			out.append("<strong class=\"me-auto\">Có lỗi</strong>");
			out.append("<small>Người sử dụng</small>");
			out.append("<button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"toast\" aria-label=\"Close\"></button>");
			out.append("</div>");
			out.append("<div class=\"toast-body\">");
			switch(err) {
			case "add":
				out.append("Lỗi khi cập nhật thêm mới!");
				break;
			case "edit":
				out.append("Có lỗi khi chỉnh sửa thông tin!");
				break;
			case "upd":
				out.append("Lỗi khi lấy giá trị để cập nhật!");
				break;
			case "acclogin":
				out.append("Không thể xóa tài khoản đang đăng nhập!");
				break;
			case "del":
				out.append("Lỗi khi thực hiện xóa!");
				break;
			case "nopermis":
				out.append("Không đủ quyền hạn!");
				break;
			case "repass":
				out.append("Mật khẩu nhập lại không trùng khớp hoặc không có giá trị!");
				break;
			case "curpass":
				out.append("Mật khẩu hiện tại không chính xác!");
				break;
			case "curpassvalue":
				out.append("Lỗi khi lấy giá trị mật khẩu hiện tại!");
				break;
			case "eqlpass":
				out.append("Mật khẩu mới không được trùng với mật khẩu hiện tại!");
				break;
			case "value":
				out.append("Dữ liệu gửi đi không hợp lệ!");
				break;
//			case "notok":
//				out.append("Lỗi trong quá trình thực hiện!");
//				break;
			default:
				out.append("Có lỗi xin vui lòng kiểm tra lại!");
			}
			out.append("</div>");
			out.append("</div>");
			out.append("</div>");
			out.append("<script>");
			out.append("const viewToast = document.getElementById('liveToast');");
			out.append("const toast =  new bootstrap.Toast(viewToast);");
			out.append("toast.show();");
			out.append("</script>");
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
