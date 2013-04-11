/*
 * Class: Hottub
 * 
 * Created on Apr 9, 2013
 * 
 * (c) Copyright Novellus Systems, Inc., unpublished work, created 2003
 * All use, disclosure, and/or reproduction of this material is prohibited
 * unless authorized in writing.  All Rights Reserved.
 * Rights in this program belong to:
 * Novellus Systems, Inc.
 * 4000 N. First Street
 * San Jose, CA
 */
package net.hentschel.home.hottub.mobile.server.rest;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Hottub
{
    
    private Heater heater;
    private Blower blower;
    private Pump pump;
    private Temperature temperature;
    private Temperature setPoint;

    Hottub()
    {
        
    }

    Hottub(Pump pump, Heater heater, Blower blower, Temperature current, Temperature setPoint)
    {
        this.pump = pump;
        this.blower = blower;
        this.heater = heater;
        this.temperature = current;
        this.setPoint = setPoint;
    }

    @XmlElement
    public Heater getHeater()
    {
        return heater;
    }

    @XmlElement
    public Blower getBlower()
    {
        return blower;
    }

    @XmlElement
    public Pump getPump()
    {
        return pump;
    }

    @XmlElement
    public Temperature getTemperature()
    {
        return temperature;
    }

    @XmlElement
    public Temperature getSetpoint()
    {
        return setPoint;
    }
}
