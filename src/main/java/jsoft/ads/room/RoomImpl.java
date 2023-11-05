package jsoft.ads.room;

import java.sql.ResultSet;
import java.util.ArrayList;

import org.javatuples.Triplet;

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
		return false;
	}

	@Override
	public boolean delRoom(RoomObject item) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean editRoom(RoomObject item) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ResultSet getRoom(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<ResultSet> getRooms(Triplet<RoomObject, Short, Byte> infors) {
		// TODO Auto-generated method stub
		return null;
	}

}
