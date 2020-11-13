package ir.ac.kntu.domain.enumeration;

import java.util.ArrayList;
import java.util.List;

public enum ShipmentStatus {
    IN_STORAGE(1),
    DELIVERED(2),
    SENT(3);

    private int id;

    ShipmentStatus(int id) {
        this.id = id;
    }
    private static final List<ShipmentStatus> shipmentStatuses = new ArrayList<>();

    static {
        for (ShipmentStatus status: ShipmentStatus.values())
            shipmentStatuses.set(status.getId(),status);
    }

    public int getId() {
        return id;
    }

    public static ShipmentStatus getShipmentStatusById(int id){
        return shipmentStatuses.get(id);
    }

    public static void printShipmentStatus(){
        System.out.println("List of Status:");
        for (int i = 0;i<shipmentStatuses.size();i++)
            System.out.println(i+"- "+ shipmentStatuses.get(i).name());
    }
}
