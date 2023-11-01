package jsoft.ads.feedback;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.javatuples.Quartet;
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
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO tblfeedback(feedback_title, feedback_content, feedback_email, feedback_fullname, feedback_created_date, feedback_view) ");
		sql.append("VALUES(?,?,?,?,?,?);");
		try {
			PreparedStatement pre = this.con.prepareStatement(sql.toString());
			pre.setString(1, item.getFeedback_title());
			pre.setString(2, item.getFeedback_content());
			pre.setString(3, item.getFeedback_email());
			pre.setString(4, item.getFeedback_fullname());
			pre.setString(5, item.getFeedback_created_date());
			pre.setBoolean(6, false);			
			return this.add(pre);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				this.con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public boolean delFeedback(FeedbackObject item) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM tblfeedback WHERE feedback_id=?";
		try {
			PreparedStatement pre = this.con.prepareStatement(sql);
			pre.setInt(1, item.getFeedback_id());
			return this.del(pre);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean editFeedback(FeedbackObject item) {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE tblfeedback SET ");
		sql.append("feedback_fullname=?,feedback_content=?,feedback_title=?,feedback_email=?,feedback_view=? ");
		sql.append("WHERE feedback_id=?");
		try {
			PreparedStatement pre = this.con.prepareStatement(sql.toString());
			pre.setString(1, item.getFeedback_fullname());
			pre.setString(2, item.getFeedback_content());
			pre.setString(3, item.getFeedback_title());
			pre.setString(4, item.getFeedback_email());
			pre.setBoolean(5, item.isFeedback_view());
			pre.setInt(6, item.getFeedback_id());			
			return this.add(pre);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				this.con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public ResultSet getFeedback(int id) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM tblfeedback WHERE id=?";
		return this.get(sql, id);
	}

	@Override
	public ArrayList<ResultSet> getFeedbacks(Quartet<FeedbackObject, Short, Byte, Boolean> infors) {
		// TODO Auto-generated method stub
		FeedbackObject similar = infors.getValue0();
		byte total = infors.getValue2();
		int at = (infors.getValue1() - 1) * total;
		boolean getState = infors.getValue3();
		StringBuilder sql = new StringBuilder();
		//Lấy danh sách feedback
		sql.append("SELECT * FROM tblfeedback ");
		sql.append(this.createConditions(similar, getState));
		sql.append("LIMIT " + at + ", " + total + ";");
		//Lấy số lượng bản ghi
		sql.append("SELECT COUNT(*) FROM tblfeedback ");
		sql.append(this.createConditions(similar, getState));
		sql.append(";");
		return this.getReList(sql.toString());
	}
	
	public StringBuilder createConditions(FeedbackObject similar, boolean getState) {
		StringBuilder tmp = new StringBuilder();
		if(getState) {
			tmp.append("WHERE feedback_view=");
			if(similar.isFeedback_view()) {
				tmp.append("1");
			}else {
				tmp.append("0");
			}
		}
		return tmp;
	}

}
