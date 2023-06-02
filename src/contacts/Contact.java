package contacts;

public class Contact {
    private String name;
    private long phoneNumber;

    private long workNumber;
     private String address;

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



    public void displayContacts() {
        //TODO format this
        System.out.println(name + " | " + phoneNumber);
    }

    public String toCSV() {
        //TODO does this need a new line?
        return name + "," + phoneNumber + "," + workNumber + "," + address;
    }

    //getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getWorkNumber() {
        return workNumber;
    }

    public void setWorkNumber(long workNumber) {
        this.workNumber = workNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "name='" + name + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", workNumber=" + workNumber +
                ", address='" + address + '\'' +
                '}';
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}
