package jsoft.ads.user;

import java.sql.*;
import java.util.ArrayList;

import jsoft.ShareControl;
import jsoft.ads.basic.Basic;
import jsoft.objects.*;

public interface User extends ShareControl {
	//Các phương thức / Chức năng cập nhật thông tin người sử dung
	public boolean addUser(UserObject item);
	public boolean editUser(UserObject item, USER_EDIT_TYPE et);
	public boolean delUser(UserObject item);
	
	//Các phương thức / Chức năng lấy thông tin người sử dung
	public ResultSet getUser(int id);
	public ResultSet getUser(String username, String userpass);
	public ArrayList<ResultSet> getUsers(UserObject similar, int at, byte total, USER_SORT_TYPE type);
}
