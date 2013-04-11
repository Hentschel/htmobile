package net.hentschel.home.hottub.mobile.client.activities;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class HomePlace extends Place {

    private String token;
	public HomePlace(String token)
    {
	    this.token = token;
    }
	
	public String getHomeName()
	{
	    return this.token;
	}

    public static class HomePlaceTokenizer implements PlaceTokenizer<HomePlace> {

		@Override
		public HomePlace getPlace(String token) {
			return new HomePlace(token);
		}

		@Override
		public String getToken(HomePlace place) {
			return place.getHomeName();
		}

	}

}
