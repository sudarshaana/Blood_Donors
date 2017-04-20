package ml.sudarshan.bloodheros;

/**
 * Created by Sudarshan on 4/18/2017.
 */

public class Person {
    private String ID;
    private String name;
    private String bloodgroup;
    private String phoneNo;

    public Person() {
    }
    public Person(String name, String bloodgroup, String phoneNo) {

        this.name = name;
        this.bloodgroup = bloodgroup;
        this.phoneNo = phoneNo;
    }

    public Person(String ID, String name, String bloodgroup, String phoneNo) {
        this.ID = ID;
        this.name = name;
        this.bloodgroup = bloodgroup;
        this.phoneNo = phoneNo;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBloodgroup() {
        return bloodgroup;
    }

    public void setBloodgroup(String bloodgroup) {
        this.bloodgroup = bloodgroup;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
}
