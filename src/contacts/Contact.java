package contacts;

public class Contact {
    private final String name;
    private final long phoneNumber;
    private final long workNumber;
    private final String address;

    //constructors
    public Contact(String name, long phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.workNumber = 0;
        this.address = "N/A";
    }

    public Contact(String name, long phoneNumber,long workNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.workNumber = workNumber;
        this.address = "N/A";
    }

    public Contact(String name, long phoneNumber, String address) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.workNumber = 0;
        this.address = address;
    }

    public Contact(String name, long phoneNumber, String address, long workNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.workNumber = workNumber;
    }

    public String toCSV() {
        return name + "," + phoneNumber + "," + workNumber + "," + address;
    }

    //getters and setters
    public String getName() {
        return name;
    }

    public long getWorkNumber() {
        return workNumber;
    }

    public String getAddress() {
        return address;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }


}
