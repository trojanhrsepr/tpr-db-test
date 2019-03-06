package basics.beans;

import java.io.Serializable;

public class TourBean implements Serializable {
    private int tourId;
    private int packageId;
    private String tourName;
    private double price;

    public TourBean() {
        this.packageId = 12;
    }

    public int getTourId() {
        return tourId;
    }

    public void setTourId(int tourId) {
        this.tourId = tourId;
    }

    public String getTourName() {
        return tourName;
    }

    public void setTourName(String tourName) {
        this.tourName = tourName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getpackageId() {
        return this.packageId;
    }
}
