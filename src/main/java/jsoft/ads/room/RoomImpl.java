package jsoft.ads.room;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.javatuples.Quartet;

import jsoft.ConnectionPool;
import jsoft.ads.basic.BasicImpl;
import jsoft.objects.RoomObject;

public class RoomImpl extends BasicImpl implements Room {

	public RoomImpl(ConnectionPool cp) {
		super(cp, "Room");
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean addRoom(RoomObject item) {
		// TODO Auto-generated method stub
		/**
		 * room_id, room_type, room_services, room_title, room_detail, 
		 * room_width, room_length, room_number_people, room_number_bed, 
		 * room_price, room_static, room_quality, room_quantity, 
		 * room_address, room_image, room_enable, room_hotel_name
		 */
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO tblroom(");
		sql.append("room_type, room_services, room_title, room_detail,");
		sql.append("room_width, room_length, room_number_people, room_number_bed,");
		sql.append("room_price, room_static, room_quality, room_quantity,");
		sql.append("room_address, room_image, room_enable, room_hotel_name,");
		sql.append("room_built_at, room_upgraded_at");
		sql.append(") VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		try {
			PreparedStatement pre = this.con.prepareStatement(sql.toString());
			pre.setString(1, item.getRoom_type());
			pre.setString(2, item.getRoom_services());
			pre.setString(3, item.getRoom_title());
			pre.setString(4, item.getRoom_detail());
			pre.setFloat(5, item.getRoom_width());
			pre.setFloat(6, item.getRoom_length());
			pre.setInt(7, item.getRoom_number_people());
			pre.setInt(8, item.getRoom_number_bed());
			pre.setFloat(9, item.getRoom_price());
			pre.setInt(10, item.getRoom_static());
			pre.setFloat(11, item.getRoom_quality());
			pre.setInt(12, item.getRoom_quantity());
			pre.setString(13, item.getRoom_address());
			pre.setString(14, item.getRoom_image());
			pre.setBoolean(15, item.isRoom_enable());
			pre.setString(16, item.getRoom_hotel_name());
			pre.setString(17, item.getRoom_built_at());
			pre.setString(18, item.getRoom_upgraded_at());
			return this.add(pre);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				this.con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public boolean delRoom(RoomObject item) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM tblroom WHERE room_id=?";
		try {
			PreparedStatement pre = this.con.prepareStatement(sql);
			pre.setInt(1, item.getRoom_id());
			return this.del(pre);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				this.con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public boolean editRoom(RoomObject item) {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE tblroom SET ");
		sql.append("room_type=?, room_services=?, room_title=?, room_detail=?,");
		sql.append("room_width=?, room_length=?, room_number_people=?, room_number_bed=?,");
		sql.append("room_price=?, room_static=?, room_quality=?, room_quantity=?,");
		sql.append("room_address=?, room_image=?, room_enable=?, room_hotel_name=?,");
		sql.append("room_upgraded_at=? ");
		sql.append("WHERE room_id=?");
		try {
			PreparedStatement pre = this.con.prepareStatement(sql.toString());
			pre.setString(1, item.getRoom_type());
			pre.setString(2, item.getRoom_services());
			pre.setString(3, item.getRoom_title());
			pre.setString(4, item.getRoom_detail());
			pre.setFloat(5, item.getRoom_width());
			pre.setFloat(6, item.getRoom_length());
			pre.setInt(7, item.getRoom_number_people());
			pre.setInt(8, item.getRoom_number_bed());
			pre.setFloat(9, item.getRoom_price());
			pre.setInt(10, item.getRoom_static());
			pre.setFloat(11, item.getRoom_quality());
			pre.setInt(12, item.getRoom_quantity());
			pre.setString(13, item.getRoom_address());
			pre.setString(14, item.getRoom_image());
			pre.setBoolean(15, item.isRoom_enable());
			pre.setString(16, item.getRoom_hotel_name());
			pre.setString(17, item.getRoom_upgraded_at());
			pre.setInt(18, item.getRoom_id());
			return this.edit(pre);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				this.con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public ResultSet getRoom(int id) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM tblroom WHERE room_id=?";
		return this.get(sql, id);
	}

	@Override
	public ArrayList<ResultSet> getRooms(Quartet<RoomObject, Short, Byte, ROOM_SORT_TYPE> infors) {
		// TODO Auto-generated method stub
		RoomObject similar = infors.getValue0();
		byte total = infors.getValue2();
		int at = (infors.getValue1() - 1) * total;
		ROOM_SORT_TYPE sortType = infors.getValue3();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM tblroom ");
		sql.append("");
		switch(sortType) {
		case ID_DEC:
			sql.append("ORDER BY room_id DESC ");
			break;
		case TITLE:
			sql.append("ORDER BY room_title ASC ");
			break;
		case QUALITY:
			sql.append("ORDER BY room_quality DESC ");
			break;
		case BUILT_AT:
			sql.append("ORDER BY room_built_at DESC ");
			break;
		case UPGRADED_AT:
			sql.append("ORDER BY room_upgraded_at DESC ");
			break;
		default:
			sql.append("ORDER BY room_id ASC ");
		}
		sql.append("LIMIT ").append(at).append(",").append(total);
		sql.append(";");
		
		//Lay so luong
		sql.append("SELECT COUNT(*) AS total FROM tblroom ");
		sql.append("");
		sql.append(";");
		
		return this.getReList(sql.toString());
	}

}
