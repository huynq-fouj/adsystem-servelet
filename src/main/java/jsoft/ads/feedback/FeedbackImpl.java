package jsoft.ads.feedback;

import java.sql.ResultSet;
import java.util.ArrayList;

import org.javatuples.Triplet;

import jsoft.ConnectionPool;
import jsoft.ads.basic.BasicImpl;
import jsoft.objects.FeedbackObject;

public class FeedbackImpl extends BasicImpl implements Feedback {

	public FeedbackImpl(ConnectionPool cp) {
		super(cp, "Feedback");
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean addFeedback(FeedbackObject item) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delFeedback(FeedbackObject item) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean editFeedback(FeedbackObject item) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ResultSet getFeedback(int id) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM tblfeedback WHERE id=?";
		return this.get(sql, id);
	}

	@Override
	public ArrayList<ResultSet> getFeedbacks(Triplet<FeedbackObject, Short, Byte> infors) {
		// TODO Auto-generated method stub
		FeedbackObject similar = infors.getValue0();
		byte page = infors.getValue2();
		int at = (infors.getValue1() - 1) * page;
		StringBuilder sql = new StringBuilder();
		
		return this.getReList(sql.toString());
	}

}
