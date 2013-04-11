
package net.hentschel.home.hottub.mobile.client;

import net.hentschel.home.hottub.mobile.client.activities.HomePlace;
import net.hentschel.home.hottub.mobile.client.activities.HottubActivity;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;


/**
 * 
 */
public class PhoneActivityMapper implements ActivityMapper {

	private final ClientFactory clientFactory;

	public PhoneActivityMapper(ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
	}

	@Override
	public Activity getActivity(Place place) {
	    if(place instanceof HomePlace)
	    {
	        return new HottubActivity((HomePlace)place, this.clientFactory);
	    }
		return null;
	}
}
