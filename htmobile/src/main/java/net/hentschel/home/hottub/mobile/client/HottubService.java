/*
 * Class: HottubService
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
