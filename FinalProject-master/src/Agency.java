/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hp
 */
class Agency {

    private String name;
    private String operationalarea;
    private String rate;
    private String phoneno;

    public Agency(String name, String operationalarea, String rate,String phoneno) {
        this.name = name;
        this.operationalarea = operationalarea;
        this.rate = rate;
        this.phoneno=phoneno;
    }

    public String getname() {
        return name;
    }

    public String getplace() {
        return operationalarea;
    }

    public String getrate() {
        return rate;
    }
    
    public String getphone()
    {
        return phoneno;
    }
}
