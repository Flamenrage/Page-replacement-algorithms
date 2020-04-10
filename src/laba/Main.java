package laba;

public class Main {
	  public static void main(String[] args) {
	        MemoryManager manager = new MemoryManager(2048, 512);
	        int pageNumbers[] = {7, 5, 3, 1, 0, 6, 2};
	        
	        for (int i = 0; i < pageNumbers.length; i++){
	        	 System.out.println("Добавление страницы в физическую память:" + pageNumbers[i]);
	             if ((pageNumbers[i] >= 0) && (pageNumbers[i] < manager.getTablePagesSize())){
	                 manager.inputNumberOfPage(pageNumbers[i]);
	             }
	        }
	  }
}