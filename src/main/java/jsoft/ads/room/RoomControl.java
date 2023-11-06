package jsoft.ads.room;

import java.util.ArrayList;

import org.javatuples.Pair;
import org.javatuples.Quartet;

import jsoft.ConnectionPool;
import jsoft.objects.RoomObject;

public class RoomControl {

	private RoomModel rm;
	
	public RoomControl(ConnectionPool cp) {
		this.rm = new RoomModel(cp);
	}
	
	public boolean addRoom(RoomObject item) {
		return this.rm.addRoom(item);
	}
	
	public boolean delRoom(RoomObject item) {
		return this.rm.delRoom(item);
	}
	
	public boolean editRoom(RoomObject item) {
		return this.rm.editRoom(item);
	}
	
	public ConnectionPool getCP() {
		return this.rm.getCP();
	}
	
	public void releaseConnection() {
		this.rm.releaseConnection();
	}
	
	public RoomObject getRoom(int id) {
		return this.rm.getRoom(id);
	}
	
	public ArrayList<String> viewRoom(Quartet<RoomObject, Short, Byte, ROOM_SORT_TYPE> infors) {
		Pair<ArrayList<RoomObject>, Integer> datas = this.rm.getRooms(infors);
		return RoomLibrary.viewRooms(datas,infors);
	}
	
}
