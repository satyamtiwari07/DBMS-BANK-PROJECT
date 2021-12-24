package sample.AllClasses;

public class branchclass {
    private Integer branch_id;
    private String branch_name;
    private String branch_city;

    public branchclass(Integer branch_id, String branch_name, String branch_city) {
        this.branch_id = branch_id;
        this.branch_name = branch_name;
        this.branch_city = branch_city;
    }

    public Integer getBranch_id() {
        return branch_id;
    }

    public String getBranch_name() {
        return branch_name;
    }

    public String getBranch_city() {
        return branch_city;
    }
}
