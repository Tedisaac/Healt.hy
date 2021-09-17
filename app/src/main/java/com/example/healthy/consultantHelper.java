package com.example.healthy;

public class consultantHelper {
    int consultImage;
    String consultTitle;


    public consultantHelper(int consultImage, String consultTitle) {
        this.consultImage = consultImage;
        this.consultTitle = consultTitle;

    }

    public int getConsultImage() {
        return consultImage;
    }

    public void setConsultImage(int consultImage) {
        this.consultImage = consultImage;
    }

    public String getConsultTitle() {
        return consultTitle;
    }

    public void setConsultTitle(String consultTitle) {
        this.consultTitle = consultTitle;
    }

}
