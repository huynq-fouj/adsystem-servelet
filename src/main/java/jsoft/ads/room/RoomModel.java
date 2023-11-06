package jsoft.ads.room;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.javatuples.Pair;
import org.javatuples.Quartet;
import org.javatuples.Triplet;

import jsoft.ConnectionPool;
import jsoft.objects.RoomObject;

public class RoomModel {

	private RoomImpl ri;
	
	public RoomModel(ConnectionPool cp) {
		this.ri = new RoomImpl(cp);
	}
	
	public boolean addRoom(RoomObject item) {
		return this.ri.addRoom(item);
	}
	
	public boolean delRoom(RoomObject item) {
		return this.ri.delRoom(item);
	}
	
	public boolean editRoom(RoomObject item) {
		return this.ri.editRoom(item);
	}
	
	public ConnectionPool getCP() {
		return this.ri.getCP();
	}
	
	public void releaseConnection() {
		this.ri.releaseConnection();
	}
	
	public RoomObject getRoom(int id) {
		RoomObject item = null;
		ResultSet rs = this.ri.getRoom(id);
		if(rs != null) {
			try {
				if(rs.next()) {
					item = new RoomObject();
					item.setRoom_id(rs.getInt("room_id"));
					item.setRoom_title(rs.getString("room_title"));
					item.setRoom_services(rs.getString("room_services"));
					item.setRoom_type(rs.getString("room_type"));
					item.setRoom_detail(rs.getString("room_detail"));
					item.setRoom_width(rs.getFloat("room_width"));
					item.setRoom_length(rs.getFloat("room_length"));
					item.setRoom_number_people(rs.getInt("room_number_people"));
					item.setRoom_number_bed(rs.getInt("room_number_bed"));
					item.setRoom_price(rs.getFloat("room_price"));
					item.setRoom_static(rs.getInt("room_static"));
					item.setRoom_quality(rs.getFloat("room_quality"));
					item.setRoom_quantity(rs.getInt("room_quantity"));
					item.setRoom_address(rs.getString("room_address"));
					item.setRoom_image(rs.getString("room_image"));
					item.setRoom_enable(rs.getBoolean("room_enable"));
					item.setRoom_hotel_name(rs.getString("room_hotel_name"));
					item.setRoom_built_at(rs.getString("room_built_at"));
					item.setRoom_upgraded_at(rs.getString("room_upgraded_at"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return item;
	}
	
	public Pair<ArrayList<RoomObject>, Integer> getRooms(Quartet<RoomObject, Short, Byte, ROOM_SORT_TYPE> infors) {
		ArrayList<RoomObject> items = new ArrayList<>();
		ArrayList<ResultSet> res = this.ri.getRooms(infors);
		RoomObject item = null;
		ResultSet rs = res.get(0);
		if(rs != null) {
			try {
				while(rs.next()) {
					item = new RoomObject();
					item.setRoom_id(rs.getInt("room_id"));
					item.setRoom_title(rs.getString("room_title"));
					item.setRoom_services(rs.getString("room_services"));
					item.setRoom_type(rs.getString("room_type"));
					item.setRoom_detail(rs.getString("room_detail"));
					item.setRoom_width(rs.getFloat("room_width"));
					item.setRoom_length(rs.getFloat("room_length"));
					item.setRoom_number_people(rs.getInt("room_number_people"));
					item.setRoom_number_bed(rs.getInt("room_number_bed"));
					item.setRoom_price(rs.getFloat("room_price"));
					item.setRoom_static(rs.getInt("room_static"));
					item.setRoom_quality(rs.getFloat("room_quality"));
					item.setRoom_quantity(rs.getInt("room_quantity"));
					item.setRoom_address(rs.getString("room_address"));
					item.setRoom_image(rs.getString("room_image"));
					item.setRoom_enable(rs.getBoolean("room_enable"));
					item.setRoom_hotel_name(rs.getString("room_hotel_name"));
					item.setRoom_built_at(rs.getString("room_built_at"));
					item.setRoom_upgraded_at(rs.getString("room_upgraded_at"));
					items.add(item);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//Lay so luong
		int total = 0;
		rs = res.get(1);
		if(rs != null) {
			try {
				if(rs.next()) {
					total = rs.getInt("total");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return new Pair<>(items, total);
	}
	
}
