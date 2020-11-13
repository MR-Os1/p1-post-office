package ir.ac.kntu.domain.enumeration;

import java.util.ArrayList;
import java.util.List;

public enum ShipmentKinds {
    NORMAL(0),
    SPECIAL(1);

    private int id;

    private static final List<ShipmentKinds> SHIPMENT_KINDS = new ArrayList<>();

    static {
        for (ShipmentKinds shipmentKinds: ShipmentKinds.values())
            SHIPMENT_KINDS.add(shipmentKinds.getId(),shipmentKinds);
    }

    ShipmentKinds(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static ShipmentKinds getShipmentKindsById(int id){
        return SHIPMENT_KINDS.get(id);
    }


    public static void printShipmentKinds(){
        System.out.println("List of Kinds:");
        for (int i = 0;i<SHIPMENT_KINDS.size();i++)
            System.out.println(i+"- "+ SHIPMENT_KINDS.get(i).name());
    }

}
