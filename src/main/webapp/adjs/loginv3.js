function checkValidLogin() {
     //get value
     let name = document.getElementById("name").value;
     let pass = document.getElementById("pass").value;
     //tham chieeus ddoois tuowngj hieenj thj looix
     let viewErrName = document.getElementById("errName");
     let viewErrPass = document.getElementById("errPass");
     //khai báo xác nhận hợp lệ
     var valName = true;
     var valPass = true;
     // biến hiển thị lỗi
     var message = "";
     //kt name
     name = name.trim();
     if (name == "") {
          valName = false;
          message = "Thiếu tên/ hoặc hộp thư đăng nhập cho tài khoản.";
     } else {
          if (name.length < 5 || name.length > 50) {
               valName = false;
               message = "Tên đăng nhập/ Email quá ngắn hoặc quá dài.";
          } else {
               if (name.indexOf(" ") != -1) {
                    valName = false;
                    message = "Tên đăng nhập/ Email không được có dấu cách. ";
               } else {
                    if (name.indexOf("@") != -1) {
                         var pn = /([\w\_\.])+@\w+[.]\w/;
                         if (!pn.test(name)) {
                              valName = false;
                              message = "Email không đúng cấu trúc . ";
                         }
                    }
               }
          }
     }
     //thông báo lỗi
     viewErrName.style.marginTop = "5px";
     viewErrName.style.padding = "8px";

     if (valName) {
          viewErrName.innerHTML = '<i class="fa-solid fa-check"></i>';
          viewErrName.style.color = "#fff";
          viewErrName.style.backgroundColor = "blue";
     } else {
          viewErrName.style.backgroundColor = "transparent";
          viewErrName.style.color = "red";
          viewErrName.innerHTML = message;
     }
     pass = pass.trim();
     if (pass == "") {
          valPass = false;
          message = "Chưa nhập pass";
     } else {
          if (pass.length < 6) {
               valPass = false;
               message = "Mật khẩu không hợp lệ.";
          } else {
               if (pass.indexOf(" ") != -1) {
                    valPass = false;
                    message = "Mật khẩu không được chứa dấu cách.";
               } else {
                    var pt = /[a-z]/;
                    var pt1 = /[A-Z]/;
                    var pt2 = /[0-9]/;
                    var pt3 =
                         /[\.\`\~\!\@\#\$\%\^\&\*\(\)\_\-\=\+\{\}\\\[\]\:\;\"\'\<\,\>\.\/\?]/;
                    if (!pt.test(pass)) {
                         valPass = false;
                         message = "Mật khẩu phải có ký tự chữ thường.";
                    }
                    if (!pt1.test(pass)) {
                         valPass = false;
                         message = "Mật khẩu phải có ký tự chữ viết hoa.";
                    }
                    if (!pt2.test(pass)) {
                         valPass = false;
                         message = "Mật khẩu phải có chữ số.";
                    }
                    if (!pt3.test(pass)) {
                         valPass = false;
                         message = "Mật khẩu phải có ký tự đặc biệt.";
                    }
               }
          }
     }
     viewErrPass.style.marginTop = "5px";
     viewErrPass.style.padding = "8px";
     if (valPass) {
          viewErrPass.innerHTML = '<i class="fa-solid fa-check"></i>';
          viewErrPass.style.color = "#fff";
          viewErrPass.style.backgroundColor = "blue";
     } else {
          viewErrPass.style.backgroundColor = "transparent";
          viewErrPass.style.color = "red";
          viewErrPass.innerHTML = message;
     }
}
