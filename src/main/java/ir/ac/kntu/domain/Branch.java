package ir.ac.kntu.domain;

import ir.ac.kntu.Storage;

import java.util.Objects;

public class Branch {

    private int countWorkers;

    private String branchCode;

    private City branchCity;

    public Branch(int countWorkers, String branchCode, City branchCity) {
        this.countWorkers = countWorkers;
        this.branchCode = branchCode;
        this.branchCity = branchCity;
    }

    public Branch(int countWorkers, String branchCode,int id) {
        this.countWorkers = countWorkers;
        this.branchCode = branchCode;
        this.branchCity = Storage.getINSTANCE().getCity(id);
    }

    public int getCountWorkers() {
        return countWorkers;
    }

    public void setCountWorkers(int countWorkers) {
        this.countWorkers = countWorkers;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public City getBranchCity() {
        return branchCity;
    }

    public void setBranchCity(City branchCity) {
        this.branchCity = branchCity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Branch branch = (Branch) o;
        return countWorkers == branch.countWorkers &&
                Objects.equals(branchCode, branch.branchCode) &&
                Objects.equals(branchCity, branch.branchCity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(countWorkers, branchCode, branchCity);
    }
}
