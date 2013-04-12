/*
 * Class: HottubServiceAsync
 * 
 * Created on Apr 8, 2013
 */
package net.hentschel.home.hottub.mobile.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * <tt>HottubServiceAsync</tt> ...
 */
public interface HottubServiceAsync
{

    /**
     * 
     * @see net.hentschel.home.hottub.mobile.client.HottubService#getUpdate()
     */
    void getUpdate(AsyncCallback<HottubUpdate> callback);

    void setTemperature(MTemperature temp, AsyncCallback<Void> callback);

    /**
     * 
     * @see net.hentschel.home.hottub.mobile.client.HottubService#turnBlowerOff()
     */
    void turnBlowerOff(AsyncCallback<Void> callback);

    /**
     * 
     * @see net.hentschel.home.hottub.mobile.client.HottubService#turnBlowerOn()
     */
    void turnBlowerOn(AsyncCallback<Void> callback);

    /**
     * 
     * @see net.hentschel.home.hottub.mobile.client.HottubService#turnPumpOff()
     */
    void turnPumpOff(AsyncCallback<Void> callback);

    /**
     * 
     * @see net.hentschel.home.hottub.mobile.client.HottubService#turnPumpOn()
     */
    void turnPumpOn(AsyncCallback<Void> callback);

}
