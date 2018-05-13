package bank;

import java.io.Serializable;

/**
 * Клиент
 * Denis
 * 09.03.2018
 */
public class Client implements Serializable{
//    private static final long serialVersionUID = -5052337507613828669L;
    private String name;
    private int passportSeries;
    private int passportNumber;
//    private int age; // java.io.InvalidClassException: bank.Client; local class incompatible: stream classdesc serialVersionUID = -5195975707637507965, local class serialVersionUID = 316421113953026076

    public Client(String name, int passportSeries, int passportNumber) {
        this.name = name;
        this.passportSeries = passportSeries;
        this.passportNumber = passportNumber;
    }

    @Override
    public String toString() {
        return name + " " + passportSeries + " " + passportNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Client client = (Client) o;

        if (passportSeries != client.passportSeries) return false;
        if (passportNumber != client.passportNumber) return false;
        return name != null ? name.equals(client.name) : client.name == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + passportSeries;
        result = 31 * result + passportNumber;
        return result;
    }
}
