/*
 * Class: Pump
 * 
 * Created on Apr 9, 2013
 * 
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
