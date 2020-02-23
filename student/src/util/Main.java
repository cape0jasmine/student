package util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import dao.StudentD;

public class Main {

    public static void main(String[] args) {
    	Date date = new Date();
    	SimpleDateFormat formatter = new SimpleDateFormat("ddHHmmssSSS");  
    	System.out.println(formatter.format(date));
	}
}
