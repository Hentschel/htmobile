/*
 */
package net.hentschel.home.hottub.mobile.client.activities;

import net.hentschel.home.hottub.mobile.client.HottubUpdate;
import net.hentschel.home.hottub.mobile.client.activities.HottubViewGWTImpl.TemperatureSelectionWidget;

import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.IsWidget;
import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;


/**
 */
public interface HottubView extends IsWidget {

	public void setTitle(String text);

	public void setRightButtonText(String text);

	public HasTapHandlers getAboutButton();

	public HasText getFirstHeader();

    public HasValueChangeHandlers<Boolean> getPumpButton();

    public HasValueChangeHandlers<Boolean> getBlowerButton();

    public TemperatureSelectionWidget getSetTemperatureWidget();

    public void update(HottubUpdate update);

}
