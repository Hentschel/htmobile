/*
 */
package net.hentschel.home.hottub.mobile.client;

import net.hentschel.home.hottub.mobile.client.activities.HottubView;
import net.hentschel.home.hottub.mobile.client.activities.HottubViewGWTImpl;

import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;

/**
 * @author Daniel Kurka
 * 
 */
public class ClientFactoryImpl implements ClientFactory {

	private EventBus eventBus;
	private PlaceController placeController;
    private HottubView homeViewImpl;

	public ClientFactoryImpl() {
		eventBus = new SimpleEventBus();

		placeController = new PlaceController(eventBus);

	}

	@Override
	public EventBus getEventBus() {
		return eventBus;
	}

	@Override
	public PlaceController getPlaceController() {
		return placeController;
	}

    public HottubView getHomeView()
    {
        if (this.homeViewImpl == null) {
            this.homeViewImpl = new HottubViewGWTImpl();
        }
        return this.homeViewImpl;
   }

}
