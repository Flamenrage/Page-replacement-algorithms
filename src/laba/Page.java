package laba;

public class Page {
    private boolean existance; //наличие страницы в физической памяти
    private int numberFrame; // номер физической страницы

    public Page(boolean existance){
        this.existance = existance;
    }

    public boolean doesExist() {
        return existance;
    }

    public int getPageFrameNumber() {
        return numberFrame;
    }

    public void setPageFrameNumber(int numberFrame) {
        this.numberFrame = numberFrame;
    }

    public void setExistance(boolean existance) {
        this.existance = existance;
    }
}