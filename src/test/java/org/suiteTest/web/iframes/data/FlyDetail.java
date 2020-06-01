package org.suiteTest.web.iframes.data;

public class FlyDetail {

    private String origin;
    private String destination;
    private String adults;
    private String childs;
    private String[] childsAge;

    public FlyDetail(String origin, String destination, String adults,
                     String childs, String[] childsAge){
        this.origin=origin;
        this.destination=destination;
        this.adults=adults;
        this.childs=childs;
        this.childsAge=childsAge;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public String getAdults() {
        return adults;
    }

    public String getChilds() {
        return childs;
    }

    public String[] getChildsAge() {
        return childsAge;
    }
}
