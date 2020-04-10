package laba;

import java.util.ArrayList;

public class LRU {
	private PagesTable tableVirtualPages;
	private FramesTable tableFrames;
	private ArrayList<Integer> callsList;
	public LRU(PagesTable tableVirtualPages, FramesTable tableFrames,
			ArrayList<Integer> callsList) {
		this.tableVirtualPages = tableVirtualPages;
		this.tableFrames = tableFrames;
		this.callsList = callsList;
	}
	private boolean viewSequences(int[] a) {
    	for (int i = 0; i < a.length; i++) {
    		if (a[i] == -1) {
    			return false;
    		}    		
    	}
    	return true;
    }
    public Object[] leastRecentlyUsedAlgorithm(Page page) {
    	Object[] objArray = new Object[3];
    	int index = 0;
    	int[] callsSequences = new int[tableFrames.size()];
    	for (int i = 0; i < callsSequences.length; i++) {
    		callsSequences[i] = -1;
    	}
    	point:
    	for(int i = callsList.size() - 1; i >= 0; i--) {
    		int temp = callsList.get(i);
    		for(int j = 0; j < tableVirtualPages.size(); j++) {
    			if((tableVirtualPages.get(j).doesExist()) && (j == temp) && 
					(callsSequences[tableVirtualPages.get(j).getPageFrameNumber()] == -1)) {
						callsSequences[tableVirtualPages.get(j).getPageFrameNumber()] = i;
					if (viewSequences(callsSequences)) {
						index = leastRecentlyUsedIndex(callsSequences);
						System.out.println("Замещаем физ. страницу " + tableVirtualPages.get(j).getPageFrameNumber());
						break point;
					}
				}
    		}
    	}
    	tableFrames.get(index).setExistance(false);
    	tableFrames.get(index).setPageFrameNumber(-1);
    	tableFrames.remove(index);
        tableFrames.add(index, page);
        page.setPageFrameNumber(index);
        page.setExistance(true);
        objArray[0] = tableFrames;
        objArray[1] = tableVirtualPages.getPagesNotes();
        objArray[2] = callsList;
        return objArray;
    }
    
    private int leastRecentlyUsedIndex(int[] a) {
    	int least = a[0];
    	int number = 0;
    	for (int i = 0; i < a.length; i++) {
    		if (a[i] < least) {
    			least = a[i];
    			number = i;
    		}
    	}
    	return number;
    }
}
