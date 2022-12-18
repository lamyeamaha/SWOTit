package com.example.swotit;

public class DecisionModel {
    private int id;
    private String Strengths;
    private String Weakness;
    private String Opportunities;
    private String Threats;
    private String D2;
    private String D1;
    private String mainReason;

    public DecisionModel()
    {

    }


    public DecisionModel(String strengths, String weakness, String opportunities, String threats, String d2, String d1, String mainReason) {
        this.Strengths = strengths;
        this.Weakness = weakness;
        this.Opportunities = opportunities;
        this.Threats = threats;
        this.D2 = d2;
        this.D1 = d1;
        this.mainReason = mainReason;
    }

    public int getId() {
        return id;
    }



    public String getStrengths() {
        return Strengths;
    }

    public String getWeakness() {
        return Weakness;
    }

    public String getOpportunities() {
        return Opportunities;
    }

    public String getThreats() {
        return Threats;
    }

    public String getD2() {
        return D2;
    }

    public String getD1() {
        return D1;
    }

    public String getMainReason() {
        return mainReason;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStrengths(String strengths) {
        Strengths = strengths;
    }

    public void setWeakness(String weakness) {
        Weakness = weakness;
    }

    public void setOpportunities(String opportunities) {
        Opportunities = opportunities;
    }

    public void setThreats(String threats) {
        Threats = threats;
    }

    public void setD2(String d2) {
        D2 = d2;
    }

    public void setD1(String d1) {
        D1 = d1;
    }

    public void setMainReason(String mainReason) {
        this.mainReason = mainReason;
    }
}
