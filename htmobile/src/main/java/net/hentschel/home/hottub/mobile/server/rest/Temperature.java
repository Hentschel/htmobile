/*
 * Class: Temperature
 * 
 * Created on Apr 9, 2013
 * 
 */
package net.hentschel.home.hottub.mobile.server.rest;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Temperature
{
    private String value;
    private String unit;

    @XmlAttribute
    public String getValue()
    {
        return value;
    }
    public void setValue(String value)
    {
        this.value = value;
    }

    @XmlAttribute
    public String getUnit()
    {
        return unit;
    }
    public void setUnit(String unit)
    {
        this.unit = unit;
    }

}
