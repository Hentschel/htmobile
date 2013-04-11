/*
 * Class: HottubSeriveImp
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
package net.hentschel.home.hottub.mobile.server;

import net.hentschel.home.hottub.mobile.client.HottubService;
import net.hentschel.home.hottub.mobile.client.HottubUpdate;
import net.hentschel.home.hottub.mobile.client.MTemperature;
import net.hentschel.hottub.Application;
import net.hentschel.hottub.IHottub;
import net.hentschel.hottub.Temperature;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * <tt>HottubSeriveImp</tt> ...
 */
public class HottubServiceImp extends RemoteServiceServlet implements HottubService
{
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new <tt>HottubSeriveImp</tt>.
     */
    public HottubServiceImp()
    {
    }

    /**
     * Constructs a new <tt>HottubSeriveImp</tt>.
     */
    public HottubServiceImp(Object delegate)
    {
        super(delegate);
    }

    /* (non-Javadoc)
     * @see net.hentschel.home.hottub.mobile.client.HottubService#getUpdate()
     */
    public HottubUpdate getUpdate()
    {
        IHottub hottub = getHottub();
        Temperature current = hottub.getCurrentTemperature();
        Temperature setpoint = hottub.getSetpointTemperature();
        boolean pumpOn = hottub.isPumpOn();
        boolean heaterOn = hottub.isHeaterOn();
        boolean blowerOn = hottub.isBlowerOn();
        HottubUpdate update = new HottubUpdate(
            new MTemperature(current.getIn(Temperature.Unit.Fahrenheit), MTemperature.Unit.Fahrenheit), 
            new MTemperature(setpoint.getIn(Temperature.Unit.Fahrenheit), MTemperature.Unit.Fahrenheit), 
            pumpOn, heaterOn, blowerOn);
        return update;
    }

    /* (non-Javadoc)
     * @see net.hentschel.home.hottub.mobile.client.HottubService#turnPumpOn()
     */
    public void turnPumpOn()
    {
        getHottub().turnPumpOn();
    }

    /* (non-Javadoc)
     * @see net.hentschel.home.hottub.mobile.client.HottubService#turnPumpOff()
     */
    public void turnPumpOff()
    {
        getHottub().turnPumpOff();
    }

    /* (non-Javadoc)
     * @see net.hentschel.home.hottub.mobile.client.HottubService#turnBlowerOn()
     */
    public void turnBlowerOn()
    {
        getHottub().turnBlowerOn();
    }

    /* (non-Javadoc)
     * @see net.hentschel.home.hottub.mobile.client.HottubService#turnBlowerOff()
     */
    public void turnBlowerOff()
    {
        getHottub().turnBlowerOff();
    }

    /* (non-Javadoc)
     * @see net.hentschel.home.hottub.mobile.client.HottubService#setTemperature(net.hentschel.hottub.Temperature)
     */
    public void setTemperature(MTemperature temp)
    {
        double t = temp.getIn(MTemperature.Unit.Fahrenheit);
        Temperature tt = new Temperature(t, Temperature.Unit.Fahrenheit);
        getHottub().setTempSetpoint(tt);
    }
    
    public static IHottub getHottub()
    {
        return Application.APP.getHottub();
    }
}
