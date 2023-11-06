package jsoft.ads.room;

import java.sql.ResultSet;
import java.util.ArrayList;

import org.javatuples.Quartet;

import jsoft.objects.RoomObject;

public interface Room {

	public boolean addRoom(RoomObject item);
	public boolean delRoom(RoomObject item);
	public boolean editRoom(RoomObject item);
	
	public ResultSet getRoom(int id);
	public ArrayList<ResultSet> getRooms(Quartet<RoomObject, Short, Byte, ROOM_SORT_TYPE> infors);
	
}
