/*
 * Class: HottubService
 * 
 * Created on Apr 8, 2013
 */
package net.hentschel.home.hottub.mobile.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * <tt>HottubService</tt> ...
 */

@RemoteServiceRelativePath("htservice")
public interface HottubService extends RemoteService
{
    public final int RPC_TIMEOUT = 20000;
    
    public HottubUpdate getUpdate();
    
    public void turnPumpOn();
    
    public void turnPumpOff();
    
    public void turnBlowerOn();
    
    public void turnBlowerOff();
    
    public void setTemperature(MTemperature temp);

}
