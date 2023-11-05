package jsoft.ads.bookingdetail;

import java.sql.ResultSet;
import java.util.ArrayList;

import org.javatuples.Triplet;

import jsoft.objects.BookingDetailObject;

public interface BookingDetail {

	public boolean addBookingDetail(BookingDetailObject item);
	public boolean delBookingDetail(BookingDetailObject item);
	public boolean editBookingDetail(BookingDetailObject item);
	
	public ResultSet getBookingDetai(int id);
	public ArrayList<ResultSet> getBookingDetails(Triplet<BookingDetailObject, Short, Byte> infors);
	
}
