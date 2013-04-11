package net.hentschel.home.hottub.mobile.client;

import net.hentschel.home.hottub.mobile.client.activities.HomePlace;
import net.hentschel.home.hottub.mobile.client.activities.HottubActivity;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;

public class TabletMainActivityMapper implements ActivityMapper {

	private final ClientFactory clientFactory;

	public TabletMainActivityMapper(ClientFactory clientFactory) {
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
