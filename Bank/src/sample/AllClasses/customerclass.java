package sample.AllClasses;

public class customerclass {
    private Integer customer_id;
    private String fname;
    private String lname;
    private String city;
    private String occupation;
    private String dob;
    private Long mobileno;

    public customerclass(Integer customer_id, String fname, String lname, String city, String occupation, String dob, Long mobileno) {
        this.customer_id = customer_id;
        this.fname = fname;
        this.lname = lname;
        this.city = city;
        this.occupation = occupation;
        this.dob = dob;
        this.mobileno = mobileno;
    }

    public Integer getCustomer_id() {
        return customer_id;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getCity() {
        return city;
    }

    public String getOccupation() {
        return occupation;
    }

    public String getDob() {
        return dob;
    }

    public Long getMobileno() {
        return mobileno;
    }
}
