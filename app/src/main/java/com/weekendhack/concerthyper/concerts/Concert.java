package com.weekendhack.concerthyper.concerts;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by hedholm on 2017-09-30.
 */

public class Concert {

    private Date date;
    private String artist;
    private String location;

    public Concert() {
        this.date = new GregorianCalendar(Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH).getTime();
        this.artist = "";
        this.location = "";
    }

    public Concert(String artist, String location, String date) {
        this.artist = artist;
        this.location = location;
        //TODO: Figure out good date conversion
    }
}
