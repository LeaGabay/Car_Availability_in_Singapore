package sg.edu.rp.c346.id21025553.caravailabilityinsingapore;

public class CarAvailability {

    private String carpark_number;
    private int total_lots;
    private String lot_type;
    private int lots_available;

    public CarAvailability(String carpark_number, int total_lots, String lot_type, int lots_available){
        this.carpark_number = carpark_number;
        this.lots_available = lots_available;
        this.total_lots = total_lots;
        this.lot_type = lot_type;
    }

    public String getCarpark_number() {
        return carpark_number;
    }

    public void setCarPark_number(String carPark_number) {
        this.carpark_number = carPark_number;
    }

    public int getTotal_lots() {
        return total_lots;
    }

    public void setTotal_lots(int total_lots) {
        this.total_lots = total_lots;
    }

    public String getLot_type() {
        return lot_type;
    }

    public void setLot_type(String lot_type) {
        this.lot_type = lot_type;
    }

    public int getLots_available() {
        return lots_available;
    }

    public void setLots_available(int lots_available) {
        this.lots_available = lots_available;
    }

    @Override
    public String toString() {
        return "\nCar park Number: " + carpark_number + '\n' +
                "Total Lots: " + total_lots + '\n' +
                "Lot Type: " + lot_type + '\n' +
                "Available Lots: " + lots_available + '\n';
    }
}
