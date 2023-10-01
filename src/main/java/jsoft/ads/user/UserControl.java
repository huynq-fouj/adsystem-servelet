package jsoft.ads.user;

import java.util.ArrayList;

import org.javatuples.Pair;
import org.javatuples.Quartet;
import org.javatuples.Quintet;

import jsoft.ConnectionPool;
import jsoft.objects.UserObject;

public class UserControl {

	private UserModel um;
	
	//Chuyển điều khiển
	public UserControl(ConnectionPool cp) {
		this.um = new UserModel(cp);
	}
	
	public ConnectionPool getCP() {
		return this.um.getCP();
	}
	
	public void releaseConnection() {
		this.um.releaseConnection();
	}
	
	public boolean addUser(UserObject u) {
		return this.um.addUser(u);
	}
	public boolean delUser(UserObject u) {
		return this.um.delUser(u);
	}
	public boolean editUser(UserObject u, USER_EDIT_TYPE et) {
		return this.um.editUser(u, et);
	}
	
	public UserObject getUserObject(int id) {
		return this.um.getUserObject(id);
	}
	
	public UserObject getUserObject(String username, String userpass) {
		// TODO Auto-generated method stub
		UserObject user = this.um.getUserObject(username, userpass);
		return user;
	}
	
	public ArrayList<String> viewUsers(Quintet<UserObject, Short, Byte, USER_SORT_TYPE, String> infors){
		//Lấy dữ liệu
		UserObject user = infors.getValue0();
		short at = infors.getValue1();
		byte total = infors.getValue2();
		USER_SORT_TYPE type = infors.getValue3();
		Pair<ArrayList<UserObject>, Integer> datas = this.um.getUserObjects(user, at, total, type);
		return UserLibrary.viewUser(datas, infors);
	}
	
	
}
