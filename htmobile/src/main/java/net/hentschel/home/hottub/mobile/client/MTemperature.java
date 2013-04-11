/*
 * Class: MTemperature
 * 
 * Created on Apr 8, 2013
 * 
 * (c) Copyright Novellus Systems, Inc., unpublished work, created 2003
 * All use, disclosure, and/or reproduction of this material is prohibited
 * unless authorized in writing.  All Rights Reserved.
 * Rights in this program belong to:
 * Novellus Systems, Inc.
 * 4000 N. First Street
 * San Jose, CA
 */
package net.hentschel.home.hottub.mobile.client;

import java.io.Serializable;

/**
 * <tt>MTemperature</tt> ...
 */
public class MTemperature implements Serializable
{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
         * <tt>Unit</tt> ...
         */
    public enum Unit implements Serializable{
        Fahrenheit("&#8457;", "\u2109"), Celcius("&#8451;", "\u2103");

        public final String html;
        public final String unicode; 

        Unit(String html, String unicode)
        {
            this.html = html;
            this.unicode = unicode;
        }
    }

    static double convertToCelcius(double value, Unit unit)
    {
        if (Unit.Fahrenheit.equals(unit))
        {
            return value;
        }
        else if (Unit.Celcius.equals(unit))
        {
            return (value - 32.000) / 1.800;
        }
        throw new RuntimeException("unsupported unit");
    }

    static double convertToFahrenheit(double value, Unit unit)
    {
        if (Unit.Fahrenheit.equals(unit))
        {
            return value;
        }
        else if (Unit.Celcius.equals(unit))
        {
            return (value * 1.800) + 32.000;
        }
        throw new RuntimeException("unsupported unit");
    }

    private double value;

    /**
     * Constructs a new <tt>MTemperature</tt>.
     */
    public MTemperature()
    {
    }

    public MTemperature(double t, MTemperature.Unit unit)
    {
        this.value = convertToFahrenheit(t, unit);
    }

    public double getIn(Unit unit)
    {
        if (Unit.Fahrenheit.equals(unit))
        {
            return convertToFahrenheit(this.value, unit);
        }
        else if (Unit.Celcius.equals(unit))
        {
            return convertToCelcius(this.value, unit);
        }
        throw new RuntimeException("unsupported unit");
    }
    
    public String toString()
    {
        return this.value + "F";
    }
}
