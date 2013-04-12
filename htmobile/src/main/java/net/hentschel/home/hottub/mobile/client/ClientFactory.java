/*
 */
package net.hentschel.home.hottub.mobile.client;

import net.hentschel.home.hottub.mobile.client.activities.HottubView;

import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.event.shared.EventBus;


public interface ClientFactory {

	public EventBus getEventBus();

	public PlaceController getPlaceController();

    public HottubView getHomeView();

}
