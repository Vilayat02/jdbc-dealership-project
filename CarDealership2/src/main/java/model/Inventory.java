package model;

public class Inventory {
    private int id;
    private String vin;

    public Inventory(int id, String vin) {
        this.id = id;
        this.vin = vin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }
}
