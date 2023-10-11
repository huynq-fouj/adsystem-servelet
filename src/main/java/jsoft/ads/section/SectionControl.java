package jsoft.ads.section;

import java.util.ArrayList;
import java.util.HashMap;

import org.javatuples.Pair;
import org.javatuples.Quartet;
import org.javatuples.Triplet;

import jsoft.ConnectionPool;
import jsoft.objects.SectionObject;
import jsoft.objects.UserObject;

public class SectionControl {

	private SectionModel sm;
	
	public SectionControl(ConnectionPool cp) {
		this.sm = new SectionModel(cp);
	}
	
	public ConnectionPool getCP() {
		return this.sm.getCP();
	}
	
	public void releaseConnection() {
		this.sm.releaseConnection();
	}
	
	public boolean addSection(SectionObject s) {
		return this.sm.addSection(s);
	}
	
	public boolean delSection(SectionObject s) {
		return this.sm.delSection(s);
	}
	
	public boolean editSection(SectionObject s) {
		return this.sm.editSection(s);
	}
	
	public ArrayList<String> viewSection(Quartet<SectionObject, Short, Byte, UserObject> infors){
		Quartet<ArrayList<SectionObject>, Integer, HashMap<Integer, String>, ArrayList<UserObject>> datas = this.sm.getSections(infors);
		
		return SectionLibrary.viewSections(datas, infors.getValue3());
	}
	public static void main(String[] args) {
		
	}
	
}
