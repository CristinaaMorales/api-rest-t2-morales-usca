package com.cibertec.edu.utils;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Utils {


	public static Date toDate(XMLGregorianCalendar calendar) 
    { 
        if (calendar == null) { 
            return null; 
        } 
        return calendar.toGregorianCalendar().getTime(); 
    } 
	
	public static XMLGregorianCalendar 
    toXMLGregorianCalendar(Date date) 
    { 
        GregorianCalendar gCalendar 
            = new GregorianCalendar(); 
        gCalendar.setTime(date); 
        XMLGregorianCalendar xmlCalendar = null; 
        try { 
            xmlCalendar 
                = DatatypeFactory.newInstance() 
                      .newXMLGregorianCalendar(gCalendar); 
        } 
        catch (DatatypeConfigurationException ex) { 
            System.out.println(ex); 
        } 
        return xmlCalendar; 
    } 
	
}
