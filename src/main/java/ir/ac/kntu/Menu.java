package ir.ac.kntu;

import ir.ac.kntu.domain.*;
import ir.ac.kntu.domain.Package;
import ir.ac.kntu.domain.enumeration.MenuOptions;
import ir.ac.kntu.domain.enumeration.ShipmentKinds;
import ir.ac.kntu.domain.enumeration.ShipmentStatus;
import ir.ac.kntu.domain.enumeration.ShipmentWays;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Menu {
    public static void main(String[] args) {
        Menu menu = new Menu();
    }

    public Menu() {
        Scanner scanner = new Scanner(System.in);
        createMenu();
        MenuOptions option = getOption(scanner);
        while (!option.equals(MenuOptions.EXIT)) {
            routeMenu(option, scanner);
            createMenu();
            option = getOption(scanner);
        }
    }

    private void routeMenu(MenuOptions option,Scanner scanner) {

            switch (option) {
                case CREATE_BRANCH:
                    createBranch(scanner);
                    break;
                case CREATE_CUSTOMER:
                    createCustomer(scanner);
                    break;
                case CREATE_CITY:
                    createCity(scanner);
                    break;
                case CREATE_PACKAGE:
                    createPackage(scanner);
                    break;
                case FILTER_PACKAGE_BY_STATUS:
                    findPackageByStatus(scanner);
                    break;
                case GET_PACKAGE_STATUS:
                    getPackageStatus(scanner);
                    break;
                case FIND_CUSTOMER:
                    findCustomer(scanner);
                    break;
                case FIND_PACKAGE_BY_DESTINATION_PLACE:
                    findPackageByDestination(scanner);
                    break;
                case FIND_PACKAGE_BY_SOURCE_PLACE:
                    findPackageBySource(scanner);
                    break;
                case SET_PACKAGE_DATE:
                    setPackageDate(scanner);
                    break;
                case SEND_OR_RECEIVE_PACKAGE:
                    sendPackages(scanner);
                    break;
                case IMPORT_TEST_DATA:
                    createTestData();
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + option);
        }
    }

    private void sendPackages(Scanner scanner) {
        System.out.println("input today Date:");
        Date date = getDate(scanner);
        Storage.getINSTANCE().getPackages().stream()
                .filter(aPackage -> aPackage.getReceivingDate()!=null)
                .filter(aPackage -> aPackage.getSendingDate()!=null)
                .forEach(aPackage -> {
            if (date.isGreaterDateThan(aPackage.getSendingDate()))
                aPackage.setShipmentStatus(ShipmentStatus.SENT);
            if (date.isGreaterDateThan(aPackage.getReceivingDate()))
                aPackage.setShipmentStatus(ShipmentStatus.DELIVERED);
        });
    }

    private void setPackageDate(Scanner scanner) {
        printPackages();
        System.out.print("Enter package Id: ");
        int packageId = scanner.nextInt();
        Package aPackage = Storage.getINSTANCE().getPackage(packageId);
        aPackage.setSendingDate(getDate(scanner));
        aPackage.setReceivingDate(getDate(scanner));
    }

    private Date getDate(Scanner scanner) {
        System.out.println("Create new Date:");
        Date date;
        do {
            System.out.print("Enter Year: ");
            int year = scanner.nextInt();
            System.out.print("Enter month: ");
            int month = scanner.nextInt();
            System.out.print("Enter day: ");
            int day = scanner.nextInt();
            date = new Date(year, month, day);
        }while (date.equals(new Date(0,1,1)));
        return date;
    }

    private void createTestData() {
        City c1 = Storage.getINSTANCE().addCity(new City("C1", 10.25, 12.34));
        City c2 = Storage.getINSTANCE().addCity(new City("C2", 15.68, 25.42));
        Branch b1 = Storage.getINSTANCE().addBranch(new Branch(10, "B1", c1));
        Branch b2 = Storage.getINSTANCE().addBranch(new Branch(25, "B2", c2));
        Customer ali = Storage.getINSTANCE().addCustomer(new Customer("Ali", "1254794892"));
        Storage.getINSTANCE().addCustomer(new Customer("Mohammad","1578948325"));
        Storage.getINSTANCE().addPackage(new Package("p1",ali,b1,c1,c2,12.54,ShipmentKinds.NORMAL,ShipmentWays.GROUND));
        Storage.getINSTANCE().addPackage(new Package("p2",ali,b2,c1,c2,12,ShipmentKinds.SPECIAL,ShipmentWays.GROUND));
    }

    private void getPackageStatus(Scanner scanner) {
        printPackages();
        System.out.print("Enter package Id: ");
        int packageId = scanner.nextInt();
        System.out.println(Storage.getINSTANCE().getPackage(packageId).getShipmentStatus());
    }

    private void findPackageByDestination(Scanner scanner) {
        printCities();
        System.out.print("Select Destination City: ");
        int cityId = scanner.nextInt();
        City city = Storage.getINSTANCE().getCity(cityId);
        Storage.getINSTANCE().getPackages()
                .stream()
                .filter(aPackage -> aPackage.getDestination().equals(city))
                .forEach(System.out::println);
    }

    private void findPackageBySource(Scanner scanner) {
        printCities();
        System.out.print("Select Source City: ");
        int cityId = scanner.nextInt();
        City city = Storage.getINSTANCE().getCity(cityId);
        Storage.getINSTANCE().getPackages()
                .stream()
                .filter(aPackage -> aPackage.getSource().equals(city))
                .forEach(System.out::println);
    }


    private void findCustomer(Scanner scanner) {
        System.out.println("Search Customer by:");
        System.out.println("1- national code");
        System.out.println("2- name");
        System.out.print("Enter the option number: ");
        int i = scanner.nextInt();
        if (i==1){
            System.out.print("Enter Desired national code: ");
            String nationalCode = scanner.next();
            System.out.println(Storage.getINSTANCE().getCustomer(nationalCode));
        }else if (i==2){
            System.out.print("Enter Desired name: ");
            String name = scanner.next();
            System.out.println(Storage.getINSTANCE().getCustomerByName(name));
        }
    }

    private void findPackageByStatus(Scanner scanner) {
        ShipmentStatus.printShipmentStatus();
        System.out.print("Enter Desired Shipment Status Id: ");
        int id = scanner.nextInt();
        ShipmentStatus status = ShipmentStatus.getShipmentStatusById(id);
        Storage.getINSTANCE()
                .getPackages()
                .stream()
                .filter(aPackage -> aPackage.getShipmentStatus().equals(status))
                .map(Package::getName)
                .forEach(System.out::println);
    }

    private void createBranch(Scanner scanner) {
        if (Storage.getINSTANCE().getCities().size()>0) {
            System.out.println("Creating Branch:");
            System.out.print("Enter name:");
            String name = scanner.next();
            System.out.print("Enter Number of Workers:");
            int workersCount = scanner.nextInt();
            printCities();
            System.out.print("Enter the City Number:");
            int cityId = scanner.nextInt();
            Storage.getINSTANCE().addBranch(new Branch(workersCount, name, cityId));
        } else {
            System.out.println("Can't make Branch when there is no City Defined");
        }
    }

    private void createCustomer(Scanner scanner){
        System.out.println("Creating Customer:");
        System.out.print("Enter name:");
        String name = scanner.next();
        System.out.print("Enter national code:");
        String nationalCode = scanner.next();
        Storage.getINSTANCE().addCustomer(new Customer(name,nationalCode));
    }

    private void createCity(Scanner scanner){
        System.out.println("Creating City:");
        System.out.print("Enter name:");
        String name = scanner.next();
        System.out.print("Enter city latitude:");
        double latitude = scanner.nextDouble();
        System.out.print("Enter city longitude:");
        double longitude = scanner.nextDouble();
        Storage.getINSTANCE().addCity(new City(name,latitude,longitude));
    }

    public void createMenu(){
        System.out.println("Branch Management System:");
        for (MenuOptions m: MenuOptions.values())
            System.out.println(m.getMenuId()+"- "+m.getMenuText());
        System.out.print("Enter Menu Number: ");
    }

    public MenuOptions getOption(Scanner scanner){
        MenuOptions menuById = MenuOptions.getMenuById(scanner.nextInt());
        return menuById;
    }

    public void printCities(){
        System.out.println("List Of cities");
        List<City> cities = Storage.getINSTANCE().getCities();
        for (int i = 0; i < cities.size(); i++)
            System.out.println(i+"- "+cities.get(i).toString());
    }

    public void printCustomer(){
        System.out.println("List Of customers:");
        Storage.getINSTANCE().getCustomers().forEach((s, customer) -> System.out.println(s + " - "+customer.toString()));
    }

    public void printBranches(){
        System.out.println("List Of Branches");
        List<Branch> branches = Storage.getINSTANCE().getBranches();
        for (int i = 0; i < branches.size(); i++)
            System.out.println(i+"- "+branches.get(i).toString());
    }

    public void printPackages(){
        System.out.println("List Of Packages");
        List<Package> packages = Storage.getINSTANCE().getPackages();
        for (int i = 0; i < packages.size(); i++)
            System.out.println(i+"- "+packages.get(i).getName());
    }


    public void createPackage(Scanner scanner){
        if (Storage.getINSTANCE().getCities().size()>1 &&
            Storage.getINSTANCE().getBranches().size()>0 &&
            Storage.getINSTANCE().getCustomers().size()>1) {
            System.out.println("Creating Package:");
            System.out.print("Enter name: ");
            String name = scanner.next();
            printCustomer();
            System.out.print("Enter customer national code: ");
            String customerNationalCode = scanner.next();
            printCities();
            System.out.print("Enter the Source City Number: ");
            int sourceCityId = scanner.nextInt();
            printCities();
            System.out.print("Enter the Destination City Number: ");
            int destinationCityId = scanner.nextInt();
            printBranches();
            System.out.print("Enter the Branch: ");
            int branchId = scanner.nextInt();
            System.out.print("Enter Wight: ");
            double wight = scanner.nextDouble();
            ShipmentKinds.printShipmentKinds();
            System.out.print("Enter Shipment Kind Id: ");
            int kindId = scanner.nextInt();
            ShipmentWays.printShipmentWays();
            System.out.print("Enter Shipment way Id: ");
            int wayId = scanner.nextInt();
            Storage.getINSTANCE().addPackage(
                    new Package(name
                            , customerNationalCode
                            , branchId
                            , sourceCityId
                            , destinationCityId
                            , wight
                            , ShipmentKinds.getShipmentKindsById(kindId),
                            ShipmentWays.getShipmentWaysById(wayId)));
        } else{
            System.out.println("Can't Create Package with no Branch or less that two City Created");
        }
    }

}
