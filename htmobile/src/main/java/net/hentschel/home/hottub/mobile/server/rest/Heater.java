/*
 * Class: Pump
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

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Heater
{
    private String state;

    @XmlAttribute
    public String getState()
    {
        return state;
    }

    public void setState(String state)
    {
        this.state = state;
    }
}
