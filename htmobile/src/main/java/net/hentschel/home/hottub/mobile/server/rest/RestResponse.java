/*
 * Class: RestResponse
 * 
 * Created on Apr 9, 2013
 * 
 */
package net.hentschel.home.hottub.mobile.server.rest;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public abstract class RestResponse
{
    @XmlRootElement
    static class OK extends RestResponse
    {
        @XmlAttribute
        public String getSucceeded()
        {
            return "true";
        }

        @XmlElement
        public String getStatus()
        {
            return "200";
        }
    }

    @XmlRootElement
    static class FAIL extends RestResponse
    {
        @SuppressWarnings("unused")
        private String trace;
        private String msg;

        FAIL()
        {
            this.msg = "";
            this.trace = "";
        }

        FAIL(Throwable e)
        {
            this.msg = e.getClass().getSimpleName() + ": " + e.getLocalizedMessage();
            StringWriter s = new StringWriter();
            PrintWriter p = new PrintWriter(s);
            e.printStackTrace(p);
            this.trace = s.toString();
            
        }
        
        @XmlAttribute
        public String getSucceeded()
        {
            return "false";
        }

        @XmlElement
        public String getStatus()
        {
            return "404";
        }

//        @XmlElement
//        public String getTrace()
//        {
//            return trace;
//        }

        @XmlElement
        public String getMessage()
        {
            return msg;
        }
    }

    public abstract String getSucceeded();
    public abstract String getStatus();
}
