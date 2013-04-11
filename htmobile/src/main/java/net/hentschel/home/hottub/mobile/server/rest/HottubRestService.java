/*
 * Class: HottubRestService
 * 
 * Created on Apr 9, 2013
 * 
 */
package net.hentschel.home.hottub.mobile.server.rest;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import net.hentschel.home.hottub.mobile.server.HottubServiceImp;
import net.hentschel.hottub.IHottub;


@Path("/hottub")
public class HottubRestService
{
    public static String ON = "ON";
    public static String OFF = "OFF";

   /**
   @Path("/{order}")
   @PUT
   @Produces("text/html")
   public String create(@PathParam("order") String order, 
                                    @QueryParam("customer_name") String customerName)
   {
      orders.put(order, customerName);
      return "Added order #" + order;
   }

   @Path("/{order}")
   @GET
   @Produces("text/html")
   public String find(@PathParam("order") String order)
   {
      if (orders.containsKey(order))
         return "<h2>Details on Order #" + order + 
                    "</h2><p>Customer name: " + orders.get(order);

      throw new WebApplicationException(Response.Status.NOT_FOUND);
   }
*/
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public Hottub getHottub()
    {
        Hottub result = new Hottub(this.getPump(), this.getHeater(), this.getBlower(), this.getTemperature(), this.getSetpoint());
        return result;
    }

    @Path("/pump")
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public Pump getPump()
    {
        IHottub hottub = HottubServiceImp.getHottub();
        Pump result = new Pump();
        result.setState(hottub.isPumpOn() ? ON : OFF);
        return result;
    }

    @Path("/pump/{state}")
    @PUT
    @Produces(MediaType.APPLICATION_XML)
    public RestResponse setPump(@PathParam("state") String state)
    {
        IHottub hottub = HottubServiceImp.getHottub();
        if(ON.equalsIgnoreCase(state))
        {
            hottub.turnPumpOn();
            return new RestResponse.OK();
        }
        else if(OFF.equalsIgnoreCase(state))
        {
            hottub.turnPumpOff();
            return new RestResponse.OK();
        }
        return new RestResponse.FAIL(new IllegalArgumentException(state));
    }

   @Path("/blower")
   @GET
   @Produces(MediaType.APPLICATION_XML)
   public Blower getBlower()
   {
       IHottub hottub = HottubServiceImp.getHottub();
       Blower result = new Blower();
       result.setState(hottub.isBlowerOn() ? ON : OFF);
       return result;
   }

   @Path("/blower/{state}")
   @PUT
   @Produces(MediaType.APPLICATION_XML)
   public RestResponse setBlower(@PathParam("state") String state)
   {
       IHottub hottub = HottubServiceImp.getHottub();
       if(ON.equalsIgnoreCase(state))
       {
           hottub.turnBlowerOn();
           return new RestResponse.OK();
       }
       else if(OFF.equalsIgnoreCase(state))
       {
           hottub.turnBlowerOff();
           return new RestResponse.OK();
       }
       return new RestResponse.FAIL(new IllegalArgumentException(state));
   }

   @Path("/heater")
   @GET
   @Produces(MediaType.APPLICATION_XML)
   public Heater getHeater()
   {
       IHottub hottub = HottubServiceImp.getHottub();
       Heater result = new Heater();
       result.setState(hottub.isHeaterOn() ? ON : OFF);
       return result;
   }

   @Path("/temperature")
   @GET
   @Produces(MediaType.APPLICATION_XML)
   public Temperature getTemperature()
   {
       IHottub hottub = HottubServiceImp.getHottub();
       Temperature result = new Temperature();
       net.hentschel.hottub.Temperature temperature = hottub.getCurrentTemperature();
       result.setValue(Double.toString(temperature.getIn(net.hentschel.hottub.Temperature.Unit.Fahrenheit)));
       result.setUnit(net.hentschel.hottub.Temperature.Unit.Fahrenheit.display);
       return result;
   }

   @Path("/setpoint")
   @GET
   @Produces(MediaType.APPLICATION_XML)
   public Temperature getSetpoint()
   {
       IHottub hottub = HottubServiceImp.getHottub();
       Temperature result = new Temperature();
       net.hentschel.hottub.Temperature temperature = hottub.getSetpointTemperature();
       result.setValue(Double.toString(temperature.getIn(net.hentschel.hottub.Temperature.Unit.Fahrenheit)));
       result.setUnit(net.hentschel.hottub.Temperature.Unit.Fahrenheit.display);
       return result;
   }

   @Path("/setpoint/{value}")
   @PUT
   @Produces(MediaType.APPLICATION_XML)
   public RestResponse setSetpoint(@PathParam("value") String value)
   {
       return this.setSetpoint(value, net.hentschel.hottub.Temperature.Unit.Fahrenheit.display);
   }

   @Path("/setpoint/{value}/{unit}")
   @PUT
   @Produces(MediaType.APPLICATION_XML)
   public RestResponse setSetpoint(@PathParam("value") String value, @PathParam("unit") String unit)
   {
       try
    {
        IHottub hottub = HottubServiceImp.getHottub();
           double d = Double.parseDouble(value);
           
           net.hentschel.hottub.Temperature.Unit u = net.hentschel.hottub.Temperature.Unit.Fahrenheit;
           if(net.hentschel.hottub.Temperature.Unit.Celcius.display.equalsIgnoreCase(unit))
           {
               u = net.hentschel.hottub.Temperature.Unit.Celcius;
           }
           net.hentschel.hottub.Temperature temperature = new net.hentschel.hottub.Temperature(d, u);
           hottub.setTempSetpoint(temperature);
           return new RestResponse.OK();
    }
    catch (Exception e)
    {
        return new RestResponse.FAIL(e);
    }
   }
}
