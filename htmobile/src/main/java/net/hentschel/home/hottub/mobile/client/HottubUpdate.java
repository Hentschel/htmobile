/*
 * Class: HottubUpdate
 * 
 * Created on Apr 8, 2013
 * 
 */
package net.hentschel.home.hottub.mobile.client;

import java.io.Serializable;

/**
 * <tt>HottubUpdate</tt> ...
 */
public class HottubUpdate implements Serializable
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private MTemperature current;
    private MTemperature setPoint;
    private boolean pumpOn;
    private boolean blowerOn;
    private boolean heaterOn;

    /**
     * Constructs a new <tt>HottubUpdate</tt>.
     */
    public HottubUpdate()
    {
    }
    
    /**
     * Constructs a new <tt>HottubUpdate</tt>.
     */
    public HottubUpdate(MTemperature current, MTemperature set, boolean pumpOn, boolean heaterOn, boolean blowerOn)
    {
        this.current = current;
        this.setPoint = set;
        this.pumpOn = pumpOn;
        this.heaterOn = heaterOn;
        this.blowerOn = blowerOn;
    }

    /**
     * @return Returns the current.
     */
    public final MTemperature getCurrent()
    {
        return this.current;
    }

    /**
     * @return Returns the setPoint.
     */
    public final MTemperature getSetPoint()
    {
        return this.setPoint;
    }

    /**
     * @return Returns the heaterOn.
     */
    public final boolean isPumpOn()
    {
        return this.pumpOn;
    }

    /**
     * @return Returns the pumpOn.
     */
    public final boolean isHeaterOn()
    {
        return this.heaterOn;
    }

    /**
     * @return Returns the blowerOn.
     */
    public final boolean isBlowerOn()
    {
        return this.blowerOn;
    }
    
    public String toString()
    {
        String result = "";
        result += "P[" + (this.pumpOn ? "x]" : " ]");
        result += ", H[" + (this.heaterOn ? "x]" : " ]");
        result += ", B[" + (this.blowerOn ? "x]" : " ]");
        result += ", C[" + this.current + "]";
        result += ", S[" + this.setPoint + "]";
        return result;
    }
}
