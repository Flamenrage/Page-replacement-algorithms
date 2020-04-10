package laba;

import java.util.ArrayList;

public class FramesTable {
	private ArrayList<Page> frameNotes;
	public ArrayList<Page> getFrameNotes() {
		return frameNotes;
	}
	public void setFrameNotes(FramesTable frameNotes) {
		this.frameNotes = frameNotes.getFrameNotes();
	}
	public FramesTable() {
		frameNotes = new ArrayList<Page>();
	}
	public Page get(int index) {
		return frameNotes.get(index);
	}
	public int getIndexOf(Page page) {
		return frameNotes.indexOf(page);
	}
	public int size() {
		return frameNotes.size();
	}
	public void add(int index, Page page) {
		frameNotes.add(index, page);
	}
	public void add(Page page) {
		frameNotes.add(page);
	}
	public void remove(int ind) {
		frameNotes.remove(ind);
	}
}