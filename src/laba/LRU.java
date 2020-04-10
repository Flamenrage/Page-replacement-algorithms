package laba;

import java.util.ArrayList;

public class LRU {
	private PagesTable tablePages;
	private FramesTable tableFrames;
	private ArrayList<Integer> callSequence;
	public LRU(PagesTable tablePages, FramesTable tableFrames,
			ArrayList<Integer> callsList) {
		this.tablePages = tablePages;
		this.tableFrames = tableFrames;
		this.callSequence = callsList;
	}
    public ArrayList<Object> leastRecentlyUsedAlgorithm(Page page) {
    	int[] callsSequences = new int[tableFrames.size()];
    	for (int i = 0; i < callsSequences.length; i++) {
    		callsSequences[i] = -1;
    	}
    	ArrayList<Object>objArray = new ArrayList<Object>();
       	int index = 0;
    	loop:
    	for(int i = callSequence.size() - 1; i >= 0; i--) {
    		int tmp = callSequence.get(i);
    		for(int k = 0; k < tablePages.size(); k++) {
    			if((k == tmp) && (tablePages.get(k).doesExist())&& (callsSequences[tablePages.get(k).getPageFrameNumber()] == -1)) {
						callsSequences[tablePages.get(k).getPageFrameNumber()] = i;
					if (viewSequences(callsSequences)) {
						int least = callsSequences[0]; 
				    	for (int j = 0; j < callsSequences.length; j++) {
				    		if (callsSequences[j] < least) {
				    			least = callsSequences[j];
				    			index = j;
				    		}
				    	}
						printLruResult(k);
						break loop;
					}
				}
    		}
    	}
    	switchPages(index, page);
        objArray.add(0,tableFrames);
        objArray.add(1,tablePages);
        return objArray;
    }
    public void printLruResult(int k){
		System.out.println("Замещаем физ. страницу " + tablePages.get(k).getPageFrameNumber());
    }
	private boolean viewSequences(int[] a) {
    	for (int i = 0; i < a.length; i++) {
    		if (a[i] == -1) {
    			return false;
    		}    		
    	}
    	return true;
    }
    private void switchPages(int index, Page page){
    	tableFrames.get(index).setPageFrameNumber(-1);
    	tableFrames.get(index).setExistance(false);
    	tableFrames.remove(index);
        tableFrames.add(index, page);
        page.setExistance(true);
        page.setPageFrameNumber(index);
    }
}
