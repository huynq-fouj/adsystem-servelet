package jsoft.ads.basic;

import java.sql.*;
import java.util.*;

import jsoft.*;

public interface Basic extends ShareControl {

	//PreparedStatement pre đã được biên dịch và đã truyền giá trị
	public boolean add(PreparedStatement pre);
	public boolean edit(PreparedStatement pre);
	public boolean del(PreparedStatement pre);
	
	public ResultSet get(String sql, int id);
	public ResultSet get(ArrayList<String> sql, String name, String pass);
	public ResultSet gets(String sql);
	public ArrayList<ResultSet> getReList(String multiSelect);
	
}
