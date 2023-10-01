package jsoft.objects;

public class LabelObject {
	private short label_id;
	private String label_title;
	private String label_infors;
	private String label_infors_en;
	private String label_notes;
	public short getLabel_id() {
		return label_id;
	}
	public String getLabel_title() {
		return label_title;
	}
	public String getLabel_infors() {
		return label_infors;
	}
	public String getLabel_infors_en() {
		return label_infors_en;
	}
	public String getLabel_notes() {
		return label_notes;
	}
	public void setLabel_id(short label_id) {
		this.label_id = label_id;
	}
	public void setLabel_title(String label_title) {
		this.label_title = label_title;
	}
	public void setLabel_infors(String label_infors) {
		this.label_infors = label_infors;
	}
	public void setLabel_infors_en(String label_infors_en) {
		this.label_infors_en = label_infors_en;
	}
	public void setLabel_notes(String label_notes) {
		this.label_notes = label_notes;
	}
}
