package jsoft.ads.room;

import java.util.ArrayList;

import org.javatuples.Pair;
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
		return null;
	}
	
	public Pair<ArrayList<RoomObject>, Integer> getRooms(Triplet<RoomObject, Short, Byte> infors) {
		return null;
	}
	
}
