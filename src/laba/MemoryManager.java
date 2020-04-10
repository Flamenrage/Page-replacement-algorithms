package laba;

import java.util.ArrayList;

public class MemoryManager {
	private PagesTable tablePages;
	private FramesTable physicalMemory;
    private int countPageFrames;
	ArrayList<Integer> callsSequence = new ArrayList<>(); //последовательность недавно загруженных страниц

    public void viewInfo(){
        System.out.println();
        System.out.println("Физическая память\n" + " i бит-присутствия");
        int counter = 0;
        for (Page frame : physicalMemory.getFrameNotes()) {
            System.out.println(" " + counter + "\t" + frame.doesExist());
            counter++;
        }
        System.out.println("Таблица страниц\n" + " i бит-присутс. номер фрейма ");
    	int count = 0;
        for (Page page : tablePages.getPagesNotes()) {
        		System.out.println(" " + count + "\t " + page.doesExist() + "\t " + page.getPageFrameNumber());
        		counter++;
        }
        System.out.println();
    }
    public MemoryManager(int memoryRAM, int pageSize){
    	tablePages = new PagesTable();
        physicalMemory = new FramesTable();
        countPageFrames = memoryRAM/pageSize;
        int countPagesTable = (memoryRAM * 2)/pageSize;
        for(int i = 0; i < countPagesTable; i++){
            Page page = new Page(false);
            tablePages.add(page);
            page.setPageFrameNumber(-1);
        }
    }
	public void implementLRU(Page page, LRU alg, ArrayList<Object> arrayObjects,int pageNumber){
        callsSequence.add(pageNumber);
    	arrayObjects = alg.leastRecentlyUsedAlgorithm(page);
        physicalMemory.setFrameNotes((FramesTable)arrayObjects.get(0));
        tablePages.setPagesNotes((PagesTable)arrayObjects.get(1));
    }
	public void RequestForNewPage(int pageNumber){
        Page page = tablePages.get(pageNumber);
    	ArrayList<Object> arrayObjects = null;
    	LRU alg = new LRU(tablePages, physicalMemory, callsSequence);
        if(!page.doesExist()){
            if(physicalMemory.size() < countPageFrames){
                physicalMemory.add(page);
                page.setExistance(true);
                page.setPageFrameNumber(physicalMemory.getIndexOf(page));
                callsSequence.add(pageNumber);
            }else if(physicalMemory.size() == countPageFrames){
            	implementLRU(page, alg, arrayObjects, pageNumber);
            }
        }
        else {
            callsSequence.add(pageNumber);
        }
       viewInfo();
    }
    
    public int getTablePagesSize(){
        return tablePages.size();
    }
}
