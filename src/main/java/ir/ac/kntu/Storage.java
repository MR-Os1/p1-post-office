package ir.ac.kntu;

import ir.ac.kntu.domain.Branch;
import ir.ac.kntu.domain.City;
import ir.ac.kntu.domain.Customer;
import ir.ac.kntu.domain.Package;

import java.util.*;

public class Storage {

    private static Storage INSTANCE;

    private final Map<String,Customer> customerMap = new HashMap<>();

    private final List<City> cities = new ArrayList<>();

    private final List<Branch> branches = new ArrayList<>();

    private final List<Package> packages = new ArrayList<>();

    private Storage(){
        INSTANCE = new Storage();
    }

    public static Storage getINSTANCE(){
        if (INSTANCE==null)
            INSTANCE = new Storage();
        return INSTANCE;
    }

    public Map<String, Customer> getCustomers(){
        return customerMap;
    }

    public Customer getCustomer(String nationalCode){
        return customerMap.get(nationalCode);
    }

    public Customer getCustomerByName(String name){
        return customerMap.values()
                .stream()
                .filter(customer -> customer.getName().equals(name))
                .findFirst().get();
    }

    public List<City> getCities(){
        return cities;
    }

    public Branch getBranch(int branchId){
        return branches.get(branchId);
    }

    public List<Branch> getBranches(){
        return branches;
    }

    public Customer addCustomer(Customer customer){
        return customerMap.put(customer.getNationalCode(),customer);
    }

    public City addCity(City city){
        cities.add(city);
        return city;
    }

    public Branch addBranch(Branch branch){
        branches.add(branch);
        return branch;
    }

    public City getCity(int cityId){
        return cities.get(cityId);
    }

    public Package getPackage(int packageId){
        return packages.get(packageId);
    }

    public Package addPackage(Package pack){
        packages.add(pack);
        return pack;
    }

    public List<Package> getPackages(){
        return packages;
    }


}
