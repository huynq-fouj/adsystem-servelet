package jsoft.ads.feedback;

import java.util.ArrayList;

import org.javatuples.Quartet;
import org.javatuples.Triplet;

import jsoft.objects.FeedbackObject;
import jsoft.objects.UserObject;

public class FeedbackLibrary {

	public static ArrayList<String> viewFeedback(Triplet<ArrayList<FeedbackObject>, Integer, UserObject> datas,
			Quartet<FeedbackObject, Short, Byte, Boolean> infors) {
		//
		ArrayList<String> view = new ArrayList<>();
		//
		ArrayList<FeedbackObject> item = datas.getValue0();
		int total = datas.getValue1();
		UserObject user = datas.getValue2();
		//
		short page = infors.getValue1();
		byte totalperpage = infors.getValue2();
		String url = "/adv/feedback/list";
		//
		StringBuilder tmp = new StringBuilder();
		
		//Danh sách feedback
		view.add(tmp.toString());
		//Phân trang
		view.add(FeedbackLibrary.getPaging());
		return view;
	}
	
	private static String getPaging() {
		
		return null;
	}
	
}
