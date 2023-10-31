package jsoft.ads.feedback;

import java.sql.ResultSet;
import java.sql.SQLException;

import jsoft.ConnectionPool;
import jsoft.objects.FeedbackObject;

public class FeedbackModel {

	private FeedbackImpl fi;
	
	public FeedbackModel(ConnectionPool cp) {
		this.fi = new FeedbackImpl(cp);
	}
	
	public ConnectionPool getCP() {
		return this.fi.getCP();
	}
	
	public void releaseConnection() {
		this.fi.releaseConnection();
	}
	
	public boolean addFeedback(FeedbackObject item) {
		return this.fi.addFeedback(item);
	}
	
	public boolean editFeedback(FeedbackObject item) {
		return this.fi.editFeedback(item);
	}
	
	public boolean delFeedback(FeedbackObject item) {
		return this.fi.delFeedback(item);
	}
	
	public FeedbackObject getFeedback(int id) {
		FeedbackObject feedback = null;
		ResultSet rs = this.fi.getFeedback(id);
		if(rs != null) {
			try {
				if(rs.next()) {
					feedback = new FeedbackObject();
					feedback.setFeedback_id(rs.getInt("feedback_id"));
					feedback.setFeedback_fullname(rs.getString("feedback_fullname"));
					feedback.setFeedback_email(rs.getString("feedback_email"));
					feedback.setFeedback_created_date(rs.getString("feedback_created_date"));
					feedback.setFeedback_title(rs.getString("feedback_title"));
					feedback.setFeedback_content(rs.getString("feedback_content"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return feedback;
	}
	
	//
	
}
