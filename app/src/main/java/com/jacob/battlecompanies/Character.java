package com.jacob.battlecompanies;

public class Character {
    private long idChar;
    private long compId;
    private String nomChar;
    private String rang;
    private String razaChar;
    private String descChar;
    private long mov;
    private long cost;
    private String img;
    private long c;
    private long cd;
    private long a;
    private long h;
    private long f;
    private long d;
    private long v;
    private boolean hero;
    private long pdr;
    private long vlt;
    private long dst;
    private long exp;
    private String rules;
    private String inj;
    private String eqp;
    private boolean rest;

    public Character(){}

    public Character(long idChar) {
        this.idChar = idChar;
    }

    public String getNomchar() {
        return nomChar;
    }

    public void setNomchar(String nomChar) {
        this.nomChar = nomChar;
    }

    public String getRang() {
        return rang;
    }

    public void setRang(String rang) {
        this.rang = rang;
    }

    public String getRazachar() {
        return razaChar;
    }

    public void setRazaChar(String razaChar) {
        this.razaChar = razaChar;
    }

    public long getIdChar() {
        return idChar;
    }

    public void setIdChar(long idChar) {
        this.idChar = idChar;
    }

    public long getCompId() {
        return compId;
    }

    public void setCompId(long compId) {
        this.compId = compId;
    }

    public String getDescChar() {
        return descChar;
    }

    public void setDescChar(String descChar) {
        this.descChar = descChar;
    }

    public long getMov() {
        return mov;
    }

    public void setMov(long mov) {
        this.mov = mov;
    }

    public long getCost() {
        return cost;
    }

    public void setCost(long cost) {
        this.cost = cost;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public long getC() {
        return c;
    }

    public void setC(long c) {
        this.c = c;
    }

    public long getCd() {
        return cd;
    }

    public void setCd(long cd) {
        this.cd = cd;
    }

    public long getA() {
        return a;
    }

    public void setA(long a) {
        this.a = a;
    }

    public long getH() {
        return h;
    }

    public void setH(long h) {
        this.h = h;
    }

    public long getF() {
        return f;
    }

    public void setF(long f) {
        this.f = f;
    }

    public long getD() {
        return d;
    }

    public void setD(long d) {
        this.d = d;
    }

    public long getV() {
        return v;
    }

    public void setV(long v) {
        this.v = v;
    }

    public boolean isHero() {
        return hero;
    }

    public void setHero(boolean hero) {
        this.hero = hero;
    }

    public long getPdr() {
        return pdr;
    }

    public void setPdr(long pdr) {
        this.pdr = pdr;
    }

    public long getVlt() {
        return vlt;
    }

    public void setVlt(long vlt) {
        this.vlt = vlt;
    }

    public long getDst() {
        return dst;
    }

    public void setDst(long dst) {
        this.dst = dst;
    }

    public long getExp() {
        return exp;
    }

    public void setExp(long exp) {
        this.exp = exp;
    }

    public String getRules() {
        return rules;
    }

    public void setRules(String rules) {
        this.rules = rules;
    }

    public String getInj() {
        return inj;
    }

    public void setInj(String inj) {
        this.inj = inj;
    }

    public String getEqp() {
        return eqp;
    }

    public void setEqp(String eqp) {
        this.eqp = eqp;
    }

    public boolean isRest() {return rest; }

    public void setRest(boolean rest) {this.rest = rest; }
}
