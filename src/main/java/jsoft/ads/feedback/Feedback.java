package jsoft.ads.feedback;

import java.sql.ResultSet;
import java.util.ArrayList;

import org.javatuples.Triplet;

import jsoft.ShareControl;
import jsoft.objects.FeedbackObject;

public interface Feedback extends ShareControl {

	public boolean addFeedback(FeedbackObject item);
	public boolean delFeedback(FeedbackObject item);
	public boolean editFeedback(FeedbackObject item);
	
	public ResultSet getFeedback(int id);
	public ArrayList<ResultSet> getFeedbacks(Triplet<FeedbackObject, Short, Byte> infors);
	
}
