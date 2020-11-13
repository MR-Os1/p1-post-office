package ir.ac.kntu.domain;

import java.util.Objects;

public class Customer {

    private String name;

    private String nationalCode;

    public Customer(String name, String nationalCode) {
        this.name = name;
        this.nationalCode = nationalCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(name, customer.name) &&
                Objects.equals(nationalCode, customer.nationalCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, nationalCode);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", nationalCode='" + nationalCode + '\'' +
                '}';
    }
}
