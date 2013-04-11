/*
 */
package net.hentschel.home.hottub.mobile.client.activities;

import java.util.ArrayList;
import java.util.List;

import net.hentschel.home.hottub.mobile.client.HottubUpdate;
import net.hentschel.home.hottub.mobile.client.MTemperature;

import com.google.gwt.dom.client.Style.BorderStyle;
import com.google.gwt.dom.client.Style.FontWeight;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;
import com.googlecode.mgwt.ui.client.MGWT;
import com.googlecode.mgwt.ui.client.MGWTStyle;
import com.googlecode.mgwt.ui.client.widget.FormListEntry;
import com.googlecode.mgwt.ui.client.widget.HeaderButton;
import com.googlecode.mgwt.ui.client.widget.HeaderPanel;
import com.googlecode.mgwt.ui.client.widget.LayoutPanel;
import com.googlecode.mgwt.ui.client.widget.MCheckBox;
import com.googlecode.mgwt.ui.client.widget.MListBox;
import com.googlecode.mgwt.ui.client.widget.RoundPanel;
import com.googlecode.mgwt.ui.client.widget.ScrollPanel;
import com.googlecode.mgwt.ui.client.widget.WidgetList;

/**
 */
public class HottubViewGWTImpl implements HottubView
{
    static public class TemperatureSelectionWidget extends MListBox
    {
        private static MTemperature.Unit unit = MTemperature.Unit.Fahrenheit;
        private List<MTemperature> list;
        static final int MIN = 75;
        static final int MAX = 104;
                
        private TemperatureSelectionWidget()
        {
            this.list = new ArrayList<MTemperature>();
            for (int i = MIN; i <= MAX; i++)
            {
                this.list.add(new MTemperature(i, unit));
                this.addItem(Long.toString(i) + unit.unicode);
            }
        }
        public void setSelected(MTemperature temperature)
        {
            if(temperature == null)
            {
                return;
            }
            int temp = (int)Math.round(temperature.getIn(unit));
            this.setSelectedIndex(temp - MIN);
        }
        
        public MTemperature getSelected()
        {
            int index = this.getSelectedIndex();
            if(index < 0)
            {
                return null;
            }
            return this.list.get(index);
        }
    }

    private LayoutPanel main;

    private HeaderButton forwardButton;

    private HeaderPanel headerPanel;

    private HTML heatOn;

    private HTML temperature;

    private TemperatureSelectionWidget temperatureSetpointWidget;

    private MCheckBox pumpSwitch;

    private MCheckBox blowerSwitch;

    private HTML debug;

    private int counter = 0;

    public HottubViewGWTImpl()
    {
        main = new LayoutPanel();

        headerPanel = new HeaderPanel();

        forwardButton = new HeaderButton();
        forwardButton.setForwardButton(true);
        if (MGWT.getOsDetection().isPhone())
        {
            headerPanel.setRightWidget(forwardButton);
        }
        main.add(headerPanel);


        ScrollPanel scroll = new ScrollPanel();
        scroll.setWidth("100%");
        scroll.setScrollingEnabledX(false);
        scroll.setScrollingEnabledY(true);
        main.add(scroll);

        RoundPanel rounded = new RoundPanel();
        scroll.add(rounded);

        rounded.setWidth("88%");
        rounded.getElement().getStyle().setBorderColor("black");
        rounded.getElement().getStyle().setBorderWidth(1.0, Unit.PX);
        rounded.getElement().getStyle().setBorderStyle(BorderStyle.SOLID);
        rounded.getElement().getStyle().setProperty("backgroundColor", "darkblue");

        VerticalPanel flow = new VerticalPanel();
        rounded.add(flow);
        
        flow.setWidth("100%");
        
        flow.addStyleName(MGWTStyle.getTheme().getMGWTClientBundle().getLayoutCss().fillPanelExpandChild());
//        vert.addStyleName(MGWTStyle.getTheme().getMGWTClientBundle().getLayoutCss().fillPanelExpandChild());
//        flow.addStyleName(MGWTStyle.getTheme().getMGWTClientBundle().getLayoutCss().fillPanel());
//        vert.addStyleName(MGWTStyle.getTheme().getMGWTClientBundle().getLayoutCss().fillPanel());
//        main.addStyleName(MGWTStyle.getTheme().getMGWTClientBundle().getLayoutCss().fillPanelHorizontal());
        
        this.temperature = new HTML("");
        this.temperature.addStyleName(MGWTStyle.getTheme().getMGWTClientBundle().getListCss().listHeader());

        // set temperature via handler
        this.temperature.setHTML("");
        this.temperature.getElement().getStyle().setFontSize(4.0, Unit.EM);
        this.temperature.getElement().getStyle().setFontWeight(FontWeight.BOLD);
        this.temperature.getElement().getStyle().setColor("white");
        this.temperature.addStyleName(MGWTStyle.getTheme().getMGWTClientBundle().getLayoutCss().fillPanel());
        this.temperature.addStyleName(MGWTStyle.getTheme().getMGWTClientBundle().getLayoutCss().fillPanelExpandChild());
        this.temperature.setWidth("99%");

        this.heatOn = new HTML("&nbsp;");
        this.heatOn.getElement().getStyle().setFontWeight(FontWeight.BOLD);
        this.heatOn.getElement().getStyle().setColor("white");
        this.heatOn.addStyleName(MGWTStyle.getTheme().getMGWTClientBundle().getLayoutCss().fillPanel());
        this.heatOn.addStyleName(MGWTStyle.getTheme().getMGWTClientBundle().getLayoutCss().fillPanelExpandChild());
        this.heatOn.setWidth("99%");

        flow.add(this.temperature);
        flow.add(this.heatOn);

        // align in the middle
        flow.setCellHorizontalAlignment(this.temperature, HasHorizontalAlignment.ALIGN_CENTER) ;
        flow.setCellVerticalAlignment(this.temperature, HasVerticalAlignment.ALIGN_MIDDLE) ;
        flow.setCellHorizontalAlignment(this.heatOn, HasHorizontalAlignment.ALIGN_CENTER) ;
        flow.setCellVerticalAlignment(this.heatOn, HasVerticalAlignment.ALIGN_MIDDLE) ;
        
        FlowPanel container = new FlowPanel();
        WidgetList widgetList = new WidgetList();
        widgetList.setRound(true);
        widgetList.setWidth("96%");
        container.add(widgetList);

        this.temperatureSetpointWidget = new TemperatureSelectionWidget();
        widgetList.add(new FormListEntry("Setpoint:", this.temperatureSetpointWidget));

        this.pumpSwitch = new MCheckBox();
        widgetList.add(new FormListEntry("Pump:", this.pumpSwitch));

        this.blowerSwitch = new MCheckBox();
        widgetList.add(new FormListEntry("Blower:", this.blowerSwitch));

        this.debug = new HTML("");
//        container.add(this.debug);
        flow.add(container);
    }

    @Override
    public Widget asWidget()
    {
        return main;
    }

    @Override
    public void setTitle(String text)
    {
        headerPanel.setCenter(text);
    }

    @Override
    public void setRightButtonText(String text)
    {
        forwardButton.setText(text);
    }

    @Override
    public HasTapHandlers getAboutButton()
    {
        return forwardButton;
    }

    @Override
    public HasText getFirstHeader()
    {
        return this.heatOn;
    }

    public HasValueChangeHandlers<Boolean> getPumpButton()
    {
        return this.pumpSwitch;
    }

    public HasValueChangeHandlers<Boolean> getBlowerButton()
    {
        return this.blowerSwitch;
    }

    public TemperatureSelectionWidget getSetTemperatureWidget()
    {
        return this.temperatureSetpointWidget;
    }

    public void update(HottubUpdate update)
    {
        this.debug.setText(this.counter++ + ": update is " + update);

        if (update == null)
        {
            return;
        }
        this.setPumpOn(update.isPumpOn());
        this.setBlowerOn(update.isBlowerOn());
        this.setHeaterOn(update.isHeaterOn());
        this.setCurrentValue(update.getCurrent());
        this.setSetpointValue(update.getSetPoint());
    }

    private void setPumpOn(Boolean pumpOn)
    {
        this.pumpSwitch.setValue(pumpOn, false);
    }

    private void setBlowerOn(Boolean blowerOn)
    {
        this.blowerSwitch.setValue(blowerOn, false);
    }

    private void setHeaterOn(Boolean heaterOn)
    {
        this.heatOn.setHTML(heaterOn ? "HEATING" : "&nbsp;");
    }

    private void setSetpointValue(MTemperature current)
    {
        this.temperatureSetpointWidget.setSelected(current);
    }

    private void setCurrentValue(MTemperature current)
    {
        int t = (int)Math.abs(current.getIn(TemperatureSelectionWidget.unit));
        this.temperature.setHTML(Integer.toString(t) + TemperatureSelectionWidget.unit.html);
    }
}
