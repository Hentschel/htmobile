/*
 */
package net.hentschel.home.hottub.mobile.client.activities;

import net.hentschel.home.hottub.mobile.client.ClientFactory;
import net.hentschel.home.hottub.mobile.client.HottubService;
import net.hentschel.home.hottub.mobile.client.HottubServiceAsync;
import net.hentschel.home.hottub.mobile.client.HottubUpdate;
import net.hentschel.home.hottub.mobile.client.MTemperature;
import net.hentschel.home.hottub.mobile.client.activities.HottubViewGWTImpl.TemperatureSelectionWidget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RpcRequestBuilder;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.web.bindery.event.shared.EventBus;
// import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
// import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
// import com.googlecode.gwtphonegap.client.util.PhonegapUtil;
import com.googlecode.mgwt.mvp.client.MGWTAbstractActivity;

/**
 */
public class HottubActivity extends MGWTAbstractActivity
{
    class BusyManager
    {
        void setBusy(HottubView view)
        {
            
        }
        
        void unsetBusy(HottubView view)
        {
            
        }
        
        void displayError(HottubView view, Throwable error)
        {
            
        }
    }

    class NoCachingRPCTimeoutRequestBuilder extends RpcRequestBuilder 
    {
           protected RequestBuilder doCreate(String serviceEntryPoint) 
           {
                   RequestBuilder requestBuilder = super.doCreate(serviceEntryPoint);           
                   requestBuilder.setHeader("Cache-Control", "no-cache");
                   requestBuilder.setTimeoutMillis(HottubService.RPC_TIMEOUT);
                   return requestBuilder;
           }
    }

    private final ClientFactory clientFactory;

    private HottubServiceAsync service;

    private BusyManager busyManager;

    private Timer updater;

    public HottubActivity(HomePlace place, ClientFactory clientFactory)
    {
        this.clientFactory = clientFactory;
        this.busyManager = new BusyManager();
        this.service = GWT.create(HottubService.class);
        
        // hacks to work around IOS6 AJAX bug http://stackoverflow.com/questions/12506897/is-safari-on-ios-6-caching-ajax-results
        NoCachingRPCTimeoutRequestBuilder builder = new NoCachingRPCTimeoutRequestBuilder();
        ((ServiceDefTarget)service).setRpcRequestBuilder(builder);
    }

    @Override
    public void start(AcceptsOneWidget panel, EventBus eventBus)
    {
        final HottubView view = clientFactory.getHomeView();
        final AsyncCallback<Void> standardVoidHandler = new AsyncCallback<Void>(){

            public void onFailure(Throwable error)
            {
                busyManager.displayError(view, error);
            }

            public void onSuccess(Void result)
            {
                busyManager.unsetBusy(view);
            }};

        view.setTitle("Hottub @Home");
        view.setRightButtonText("about");
        
        HasValueChangeHandlers<Boolean> pump = view.getPumpButton();
        pump.addValueChangeHandler(new ValueChangeHandler<Boolean>(){

            public void onValueChange(ValueChangeEvent<Boolean> event)
            {
                if(Boolean.TRUE.equals(event.getValue()))
                {
                    service.turnPumpOn(standardVoidHandler);
                }
                if(Boolean.FALSE.equals(event.getValue()))
                {
                    service.turnPumpOff(standardVoidHandler);
                }
            }});
        
        HasValueChangeHandlers<Boolean> blower = view.getBlowerButton();
        blower.addValueChangeHandler(new ValueChangeHandler<Boolean>(){

            public void onValueChange(ValueChangeEvent<Boolean> event)
            {
                if(Boolean.TRUE.equals(event.getValue()))
                {
                    service.turnBlowerOn(standardVoidHandler);
                }
                if(Boolean.FALSE.equals(event.getValue()))
                {
                    service.turnBlowerOff(standardVoidHandler);
                }
            }});
        
        final TemperatureSelectionWidget setTemperature = view.getSetTemperatureWidget();
        setTemperature.addChangeHandler(new ChangeHandler(){

            public void onChange(ChangeEvent event)
            {
                MTemperature setp = setTemperature.getSelected();
                service.setTemperature(setp, standardVoidHandler);
            }});
        

        // get initial update
        service.getUpdate(new AsyncCallback<HottubUpdate>(){

            public void onFailure(Throwable error)
            {
                busyManager.displayError(view, error);
            }

            public void onSuccess(HottubUpdate update)
            {
                view.update(update);
            }});
            
        // schedule periodic update
        this.updater = new Timer() {

            public void run()
            {
                service.getUpdate(new AsyncCallback<HottubUpdate>(){

                    public void onFailure(Throwable error)
                    {
                        busyManager.displayError(view, error);
                    }

                    public void onSuccess(HottubUpdate update)
                    {
                        view.update(update);
                    }});
            }
        };
        
        // update display every 15 seconds
        this.updater.scheduleRepeating(10000);
        
        /** add later
        addHandlerRegistration(view.getAboutButton().addTapHandler(new TapHandler() {

          @Override
          public void onTap(TapEvent event) {
            clientFactory.getPlaceController().goTo(new AboutPlace());

          }
        }));
        */

        panel.setWidget(view.asWidget());
    }

    /* (non-Javadoc)
     * @see com.googlecode.mgwt.mvp.client.MGWTAbstractActivity#onStop()
     */
    public void onStop()
    {
        super.onStop();
        this.updater.cancel();
    }
    
    public void onCancel()
    {
        super.onCancel();
        this.updater.cancel();
    }
}
