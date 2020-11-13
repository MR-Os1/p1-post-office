package ir.ac.kntu.domain.enumeration;

import java.util.ArrayList;
import java.util.List;

public enum ShipmentWays {
    GROUND(1),
    AIR(2),
    SEA(3);
    private int id;

    private static final List<ShipmentWays> shipmentWays = new ArrayList<>();

    static {
        for (ShipmentWays ways:ShipmentWays.values())
            shipmentWays.add(ways.getId(),ways);
    }

    ShipmentWays(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static ShipmentWays getShipmentWaysById(int id){
        return shipmentWays.get(id);
    }

    public static void printShipmentWays(){
        System.out.println("List of Ways:");
        for (int i = 0;i<shipmentWays.size();i++)
            System.out.println(i+"- "+ shipmentWays.get(i).name());
    }
}
