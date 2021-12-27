package sample.AllClasses;

public class queryone {
    private Integer customer_idcol1;
    private String fnamecol1;
    private String lnamecol1;
    private String dobcol1;

    public queryone(Integer customer_idcol1, String fnamecol1, String lnamecol1, String dobcol1) {
        this.customer_idcol1 = customer_idcol1;
        this.fnamecol1 = fnamecol1;
        this.lnamecol1 = lnamecol1;
        this.dobcol1 = dobcol1;
    }

    public Integer getCustomer_idcol1() {
        return customer_idcol1;
    }

    public String getFnamecol1() {
        return fnamecol1;
    }

    public String getLnamecol1() {
        return lnamecol1;
    }

    public String getDobcol1() {
        return dobcol1;
    }
}
