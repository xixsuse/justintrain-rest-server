package com.albertogiunta.constants.JIT;

import java.time.format.DateTimeFormatter;

public class JVALUE {

    public static final int NA = -1;
    public static final String NAS = "";
    public static final Integer NAI = null;

    // FIRST CLASS ORIENTATION
    public static final int HEAD = 1;
    public static final int TAIL  = 0;

    // TRAIN STATUS
    public static final int REGULAR = 1;
    public static final int CANCELLED = 2;
    public static final int PARTIALLY_CANCELLED = 3;
    public static final int DEVIATED = 4;

    // PROGRESS
    public static final int FASTER = 1;
    public static final int SLOWER = 2;
    public static final int CONSTANT = 0;

    // CURRENT STOP TYPE CODE
    public static final int FIRST = 1;
    public static final int MIDDLE = 2;
    public static final int LAST = 3;

    // CURRENT STOP TYPE CODE - ACTUAL TRATTA TYPE
    public static final int NOT_VISITED = 0;
    public static final int VISITED = 1;
    public static final int EXTRAORDINARY = 2;
    public static final int DELETED = 3;

    // NEXT STOP STATUS CODE
    public static final int CVNV = 1;
    public static final int CVNU = 2;
    public static final int CUNU = 3;
    public static final int CVND = 4;
    public static final int CDND = 5;
    public static final int CDNE = 6;


    public static final String HHmm = "HH:mm";
    public static final String yyyyMMddTHHmmssZ = "yyyy-MM-dd'T'HH:mmZ";
    public static final String yyyyMMddTHHmm00 = "yyyy-MM-dd'T'HH:mm:00";
    public static final String TZROME = "GMT+1";
    public static final String TZGMT = "GMT";
    public static final DateTimeFormatter LONG_FORMATTER = DateTimeFormatter.ofPattern(yyyyMMddTHHmmssZ);
    public static final DateTimeFormatter SHORT_FORMATTER = DateTimeFormatter.ofPattern(HHmm);


    public static final int HOUR_PER_SLOT = 4;
    public static final int RIDES_PER_REQUEST = 6;
    public static final int RIDES_WITH_DELAY = 3;
    public static final int THREAD_POOL = 5;
    public static final int PREEMPTIVE_HOURS = 1;
}