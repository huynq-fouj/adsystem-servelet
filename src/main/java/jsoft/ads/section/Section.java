package jsoft.ads.section;

import java.sql.ResultSet;
import java.util.ArrayList;

import org.javatuples.Quartet;

import jsoft.ShareControl;
import jsoft.objects.*;

public interface Section extends ShareControl{
	public boolean addSection(SectionObject item);
	public boolean editSection(SectionObject item);
	public boolean delSection(SectionObject item);
	
	
	public ResultSet getSection(int id);
	public ArrayList<ResultSet> getSections(Quartet<SectionObject, Short, Byte, UserObject> infors);
}
