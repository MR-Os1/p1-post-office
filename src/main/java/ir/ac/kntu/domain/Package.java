package ir.ac.kntu.domain;

import ir.ac.kntu.Storage;
import ir.ac.kntu.domain.enumeration.ShipmentKinds;
import ir.ac.kntu.domain.enumeration.ShipmentStatus;
import ir.ac.kntu.domain.enumeration.ShipmentWays;

import java.time.ZonedDateTime;
import java.util.Objects;

public class Package {

    private String name;

    private Customer receiver;

    private Branch branch;

    private City source;

    private City destination;

    private double wight;

    private Date sendingDate;

    private Date receivingDate;

    private ShipmentKinds shipmentKinds;

    private ShipmentStatus shipmentStatus = ShipmentStatus.IN_STORAGE;

    private ShipmentWays shipmentWays;

    public Package(String name, Customer receiver, Branch branch, City source,
                   City destination, double wight, ShipmentKinds shipmentKinds, ShipmentWays shipmentWays) {
        this.name = name;
        this.receiver = receiver;
        this.branch = branch;
        this.source = source;
        this.destination = destination;
        this.wight = wight;
        this.shipmentKinds = shipmentKinds;
        this.shipmentWays = shipmentWays;
    }

    public Package(String name, String receiverNationalCode, int branchId, int sourceCityId,
                   int destinationCityId, double wight, ShipmentKinds shipmentKinds, ShipmentWays shipmentWays) {
        this.name = name;
        this.receiver = Storage.getINSTANCE().getCustomer(receiverNationalCode);
        this.branch = Storage.getINSTANCE().getBranch(branchId);
        this.source = Storage.getINSTANCE().getCity(sourceCityId);
        this.destination = Storage.getINSTANCE().getCity(destinationCityId);
        this.wight = wight;
        this.shipmentKinds = shipmentKinds;
        this.shipmentWays = shipmentWays;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Customer getReceiver() {
        return receiver;
    }

    public void setReceiver(Customer receiver) {
        this.receiver = receiver;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public City getSource() {
        return source;
    }

    public void setSource(City source) {
        this.source = source;
    }

    public City getDestination() {
        return destination;
    }

    public void setDestination(City destination) {
        this.destination = destination;
    }

    public double getWight() {
        return wight;
    }

    public void setWight(double wight) {
        this.wight = wight;
    }

    public Date getSendingDate() {
        return sendingDate;
    }

    public void setSendingDate(Date sendingDate) {
        this.sendingDate = sendingDate;
    }

    public Date getReceivingDate() {
        return receivingDate;
    }

    public void setReceivingDate(Date receivingDate) {
        this.receivingDate = receivingDate;
    }

    public ShipmentKinds getShipmentKinds() {
        return shipmentKinds;
    }

    public void setShipmentKinds(ShipmentKinds shipmentKinds) {
        this.shipmentKinds = shipmentKinds;
    }

    public ShipmentStatus getShipmentStatus() {
        return shipmentStatus;
    }

    public void setShipmentStatus(ShipmentStatus shipmentStatus) {
        this.shipmentStatus = shipmentStatus;
    }

    public ShipmentWays getShipmentWays() {
        return shipmentWays;
    }

    public void setShipmentWays(ShipmentWays shipmentWays) {
        this.shipmentWays = shipmentWays;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Package aPackage = (Package) o;
        return Double.compare(aPackage.wight, wight) == 0 &&
                Objects.equals(name, aPackage.name) &&
                Objects.equals(receiver, aPackage.receiver) &&
                Objects.equals(branch, aPackage.branch) &&
                Objects.equals(source, aPackage.source) &&
                Objects.equals(destination, aPackage.destination) &&
                Objects.equals(sendingDate, aPackage.sendingDate) &&
                Objects.equals(receivingDate, aPackage.receivingDate) &&
                shipmentKinds == aPackage.shipmentKinds &&
                shipmentStatus == aPackage.shipmentStatus &&
                shipmentWays == aPackage.shipmentWays;
    }

    @Override
    public String toString() {
        return "Package{" +
                "name='" + name + '\'' +
                ", receiver=" + receiver +
                ", branch=" + branch +
                ", source=" + source +
                ", destination=" + destination +
                ", wight=" + wight +
                ", sendingDate=" + sendingDate +
                ", receivingDate=" + receivingDate +
                ", shipmentKinds=" + shipmentKinds +
                ", shipmentStatus=" + shipmentStatus +
                ", shipmentWays=" + shipmentWays +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, receiver, branch, source, destination, wight, sendingDate,
                receivingDate, shipmentKinds, shipmentStatus, shipmentWays);
    }
}
