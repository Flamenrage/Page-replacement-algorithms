package laba;

import java.util.ArrayList;

public class PagesTable {
	private ArrayList<Page> pagesNotes;
	public ArrayList<Page> getPagesNotes() {
		return  pagesNotes;
	}
	public void setPagesNotes(PagesTable pagesTable) {
		this.pagesNotes = pagesTable.getPagesNotes();
	}
	public PagesTable() {
		pagesNotes = new ArrayList<Page>();
	}
	public Page get(int index) {
		return pagesNotes.get(index);
	}
	public int size() {
		return pagesNotes.size();
	}
	public void add(Page page) {
		pagesNotes.add(page);
	}
}
