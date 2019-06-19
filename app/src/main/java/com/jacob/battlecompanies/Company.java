package com.jacob.battlecompanies;

public class Company {
    private long compId;
    private String compNom;
    private long compCost;
    private String compDesc;
    private String tipus;
    private long infl;
    private String logo;
    private String date;

    public Company() { this.compId = -1; }

    public long getCompId() {
        return compId;
    }

    public void setCompId(long compId) {
        this.compId = compId;
    }

    public String getCompNom() {
        return compNom;
    }

    public void setCompNom(String compNom) {
        this.compNom = compNom;
    }

    public long getCompCost() {
        return compCost;
    }

    public void setCompCost(long compCost) {
        this.compCost = compCost;
    }

    public String getCompDesc() {
        return compDesc;
    }

    public void setCompDesc(String compDesc) {
        this.compDesc = compDesc;
    }

    public String getTipus() {
        return tipus;
    }

    public void setTipus(String tipus) {
        this.tipus = tipus;
    }

    public long getInfl() {
        return infl;
    }

    public void setInfl(long infl) {
        this.infl = infl;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
