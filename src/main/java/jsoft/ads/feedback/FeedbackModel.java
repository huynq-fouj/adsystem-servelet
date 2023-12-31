package jsoft.ads.feedback;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.javatuples.Pair;
import org.javatuples.Quartet;

import jsoft.ConnectionPool;
import jsoft.library.Utilities;
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
					feedback.setFeedback_view(rs.getBoolean("feedback_view"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return feedback;
	}
	
	//
	public Pair<ArrayList<FeedbackObject>, Integer> getFeedbackObjects(Quartet<FeedbackObject, Short, Byte, Boolean> infors) {
		ArrayList<FeedbackObject> feedbacks = new ArrayList<>();
		ArrayList<ResultSet> res = this.fi.getFeedbacks(infors);
		FeedbackObject feedback = null;
		ResultSet rs = res.get(0);
		if(rs != null) {
			try {
				while(rs.next()) {
					feedback = new FeedbackObject();
					feedback.setFeedback_id(rs.getInt("feedback_id"));
					feedback.setFeedback_fullname(Utilities.decode(rs.getString("feedback_fullname")));
					feedback.setFeedback_email(rs.getString("feedback_email"));
					feedback.setFeedback_created_date(rs.getString("feedback_created_date"));
					feedback.setFeedback_title(Utilities.decode(rs.getString("feedback_title")));
					feedback.setFeedback_content(Utilities.decode(rs.getString("feedback_content")));
					feedback.setFeedback_view(rs.getBoolean("feedback_view"));
					feedbacks.add(feedback);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		int total = 0;
		rs = res.get(1);
		if(rs != null) {
			try {
				if(rs.next()) {
					total = rs.getInt("total");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return new Pair<>(feedbacks, total);
	}
	
}
