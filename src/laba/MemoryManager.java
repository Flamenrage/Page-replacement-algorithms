package laba;

import java.util.ArrayList;

public class MemoryManager {
	private PagesTable tablePages;
    //private ArrayList<Page> physicalMemory;
	private FramesTable physicalMemory;
    private int countPageFrames;
	ArrayList<Integer> callsSequence = new ArrayList<>(); //последовательность недавно загруженных страниц


    public MemoryManager(int memoryRAM, int pageSize){
    	tablePages = new PagesTable();
        physicalMemory = new FramesTable();
        countPageFrames = memoryRAM/pageSize;

        for(int i = 0; i <(memoryRAM * 2)/pageSize; i++){
            Page page = new Page(false);
            page.setPageFrameNumber(-1);
            tablePages.add(page);
        }
    }

    @SuppressWarnings("unchecked")
	public void inputNumberOfPage(int pageIndex){
    	Object [] arrayObjects;
    	LRU alg = new LRU(tablePages, physicalMemory, callsSequence);
        Page page = tablePages.get(pageIndex);
        if(!page.doesExist()){
            if(physicalMemory.size() < countPageFrames){
                page.setExistance(true);
                physicalMemory.add(page);
                int indexOfPageFrames = physicalMemory.getIndexOf(page);
                page.setPageFrameNumber(indexOfPageFrames);
                callsSequence.add(pageIndex);
            }else if(physicalMemory.size() == countPageFrames){
            	arrayObjects = alg.leastRecentlyUsedAlgorithm(page);
                callsSequence.add(pageIndex);
                callsSequence.add(pageIndex);
                physicalMemory.setFrameNotes((FramesTable)arrayObjects[0]);
                tablePages.setPagesRecords((ArrayList<Page>)arrayObjects[1]);
                callsSequence = (ArrayList<Integer>)arrayObjects[2];
            }
        }
        printPages();
        printPageFrames();
    }

    public int getTablePagesSize(){
        return tablePages.size();
    }

    public void printPageFrames(){
        int i = 0;
        System.out.println("Физическая память\n" + " i бит-присутствия");
        for (Page page : physicalMemory.getFrameNotes()) {
            System.out.println(" " + i + "\t" + page.doesExist());
            i++;
        }
        System.out.println();
    }

    private void printPages(){
    	int i = 0;
        System.out.println("Таблица страниц\n" + " i бит-присутс. номер фрейма ");
        for (Page page : tablePages.getPagesNotes()) {
        		System.out.println(" " + i + "\t "
                    + page.doesExist() + "\t " + page.getPageFrameNumber());
        }
        i++;
        System.out.println();
    }
}
