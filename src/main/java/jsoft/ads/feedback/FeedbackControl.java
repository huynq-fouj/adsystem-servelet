package jsoft.ads.feedback;

import jsoft.ConnectionPool;
import jsoft.objects.FeedbackObject;

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
}
