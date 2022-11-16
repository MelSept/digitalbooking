package com.digitalbooking.apilodgings.entity;

import java.sql.Date;

public class Reservation {

    public Product product;
    public Date dateCheckIn;
    public Date dateCheckOut;
    public boolean covid;


    public Reservation(Product product, Date dateCheckIn, Date dateCheckOut, boolean covid) {
        this.product = product;
        this.dateCheckIn = dateCheckIn;
        this.dateCheckOut = dateCheckOut;
        this.covid = covid;
    }

    public Reservation() {

    }
}
