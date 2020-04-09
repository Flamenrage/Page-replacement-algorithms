package laba;

import java.util.ArrayList;

public class MemoryManager {
    private ArrayList<Page> pagesTable;
    private ArrayList<Page> physicalMemory;
    private int countPageFrames;
	ArrayList<Integer> callsSequence = new ArrayList<>(); //последовательность недавно загруженных страниц


    public MemoryManager(int memoryRAM, int pageSize){
        pagesTable = new ArrayList<Page>();
        physicalMemory = new ArrayList<Page>();
        countPageFrames = memoryRAM/pageSize;

        for(int i = 0; i <(memoryRAM * 2)/pageSize; i++){
            Page page = new Page(false);
            page.setPageFrameNumber(-1);
            pagesTable.add(page);
        }
    }

    public void inputNumberOfPage(int pageIndex){
        Page page = pagesTable.get(pageIndex);
        if(!page.doesExist()){
            if(physicalMemory.size() < countPageFrames){
                page.setExistance(true);
                physicalMemory.add(page);
                int indexOfPageFrames = physicalMemory.indexOf(page);
                page.setPageFrameNumber(indexOfPageFrames);
                callsSequence.add(pageIndex);
            }else if(physicalMemory.size() == countPageFrames){
                LRU(page);
            }
        }
        printPages();
        printPageFrames();
    }


    private void LRU(Page page){
    	int index = 0;
    	loop:
    	// проходим по всем недавно загруженным страницам
    	for (int i = 0; i < callsSequence.size(); i++) {
			int temp = callsSequence.get(i);
			// проходим по всем страницам виртуальной памяти
			for (int j = 0; j < pagesTable.size(); j++) {
				// 
				if((pagesTable.get(j).doesExist()) && (j == temp)) {
					Page result = pagesTable.get(j);
					System.out.println("Выгрузка из памяти страницы с номером " + j);
					index = result.getPageFrameNumber();
					// выход из вложенного цикла
					break loop;
				}
			}
		}
    	physicalMemory.get(index).setExistance(false);
    	physicalMemory.get(index).setPageFrameNumber(-1);
    	physicalMemory.remove(index);
        physicalMemory.add(index, page);
        page.setPageFrameNumber(index);
        page.setExistance(true);
     
    }

    public int getSizeTablePages(){
        return pagesTable.size();
    }

    public void printPageFrames(){
        int i = 0;
        System.out.println("Физическая память\n" + " i бит-присутствия");
        for (Page pageThis: physicalMemory) {
            System.out.println(" " + i + "\t" + pageThis.doesExist());
            i++;
        }
        System.out.println();
    }

    private void printPages(){
        System.out.println("Таблица страниц\n" + " i бит-присутс. номер фрейма ");
        for(int i = 0; i < pagesTable.size(); i++){
            System.out.println(" " + i + "\t "
                    + pagesTable.get(i).doesExist() + "\t " + pagesTable.get(i).getPageFrameNumber());
        }
        System.out.println();
    }
}
