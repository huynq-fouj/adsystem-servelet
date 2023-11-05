package jsoft.ads.room;

import java.sql.ResultSet;
import java.util.ArrayList;

import org.javatuples.Triplet;

import jsoft.objects.RoomObject;

public interface Room {

	public boolean addRoom(RoomObject item);
	public boolean delRoom(RoomObject item);
	public boolean editRoom(RoomObject item);
	
	public ResultSet getRoom(int id);
	public ArrayList<ResultSet> getRooms(Triplet<RoomObject, Short, Byte> infors);
	
}
