/*
 * Class: MockHottub
 * 
 * Created on Apr 8, 2013
 * 
 */
package net.hentschel.home.hottub.mobile.server;

import java.util.Timer;
import java.util.TimerTask;

import net.hentschel.hottub.IHottub;
import net.hentschel.hottub.Temperature;

class MockHottub implements IHottub
{

    private static IHottub INSTANCE = null;
    private Temperature setpoint;
    private boolean blower;
    private boolean pump;
    private Temperature current;
    private Timer updater;
    
    static synchronized IHottub getInstance()
    {
        if(INSTANCE == null)
        {
            INSTANCE = new MockHottub();
        }
        return INSTANCE;
    }
    
    MockHottub()
    {
        this.setpoint = new Temperature(95L, Temperature.Unit.Fahrenheit);
        this.current = new Temperature(94L, Temperature.Unit.Fahrenheit);
        this.updater = new Timer();
        this.updater.scheduleAtFixedRate(new TimerTask(){

            public void run()
            {
                long temp = 92L + (long)(Math.random() * 10);
                current = new Temperature(temp, Temperature.Unit.Fahrenheit);
            }}, 15000, 12000);
    }

    public Temperature getCurrentTemperature()
    {
        return this.current;
    }

    public Temperature getSetpointTemperature()
    {
        return setpoint;
    }

    public boolean isBlowerOn()
    {
        return this.blower;
    }

    public boolean isHeaterOn()
    {
        return this.current.getIn(Temperature.Unit.Fahrenheit) < this.setpoint.getIn(Temperature.Unit.Fahrenheit);
    }

    public boolean isPumpOn()
    {
        return this.pump;
    }

    public void setTempSetpoint(Temperature t)
    {
        System.out.println("setting temp to " + t);
        this.setpoint = t;
    }

    public void turnBlowerOff()
    {
        System.out.println("Turning blower off");
        this.blower = false;
    }

    public void turnBlowerOn()
    {
        System.out.println("Turning blower on");
        this.blower = true;
    }

    public void turnPumpOff()
    {
        System.out.println("Turning pump off");
        this.pump = false;
    }

    public void turnPumpOn()
    {
        System.out.println("Turning pump on");
        this.pump = true;
    }
}