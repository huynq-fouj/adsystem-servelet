package jsoft.ads.feedback;

import java.util.ArrayList;

import org.javatuples.Quartet;
import org.javatuples.Triplet;

import jsoft.ConnectionPool;
import jsoft.objects.FeedbackObject;
import jsoft.objects.UserObject;

public class FeedbackControl {

	private FeedbackModel fm;
	
	public FeedbackControl(ConnectionPool cp) {
		this.fm = new FeedbackModel(cp);
	}
	
	public ConnectionPool getCP() {
		return this.fm.getCP();
	}
	
	public void releaseConnection() {
		this.fm.releaseConnection();
	}
	
	public boolean addFeedback(FeedbackObject item) {
		return this.fm.addFeedback(item);
	}
	
	public boolean editFeedback(FeedbackObject item) {
		return this.fm.editFeedback(item);
	}
	
	public boolean delFeedback(FeedbackObject item) {
		return this.fm.delFeedback(item);
	}
	
	public FeedbackObject getFeedback(int id) {
		return this.fm.getFeedback(id);
	}
	
	public ArrayList<String> viewFeedbacks(Quartet<FeedbackObject, Short, Byte, Boolean> infors, UserObject user) {
		Triplet<ArrayList<FeedbackObject>, Integer, UserObject> datas = this.fm.getFeedbackObjects(infors).add(user);
		return FeedbackLibrary.viewFeedback(datas, infors);
	}
}
