//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.04.27 at 12:17:49 PM EDT 
//

	
package com.chong.site;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Hosts">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Host" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="Host_Name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="IP_address" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="OS" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="Load_avg_1min" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *                             &lt;element name="Load_avg_5min" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *                             &lt;element name="Load_avg_15min" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *                           &lt;/sequence>
 *                           &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}short" />
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}short" />
 *       &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="location" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "hosts"
})
@XmlRootElement(name = "Site")
public class Site {

    @XmlElement(name = "Hosts", required = true)
    protected Site.Hosts hosts;
    @XmlAttribute(name = "id")
    protected Short id;
    @XmlAttribute(name = "name")
    protected String name;
    @XmlAttribute(name = "location")
    protected String location;

    /**
     * Gets the value of the hosts property.
     * 
     * @return
     *     possible object is
     *     {@link Site.Hosts }
     *     
     */
    public Site.Hosts getHosts() {
        return hosts;
    }

    /**
     * Sets the value of the hosts property.
     * 
     * @param value
     *     allowed object is
     *     {@link Site.Hosts }
     *     
     */
    public void setHosts(Site.Hosts value) {
        this.hosts = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setId(Short value) {
        this.id = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the location property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets the value of the location property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocation(String value) {
        this.location = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="Host" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="Host_Name" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="IP_address" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="OS" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="Load_avg_1min" type="{http://www.w3.org/2001/XMLSchema}float"/>
     *                   &lt;element name="Load_avg_5min" type="{http://www.w3.org/2001/XMLSchema}float"/>
     *                   &lt;element name="Load_avg_15min" type="{http://www.w3.org/2001/XMLSchema}float"/>
     *                 &lt;/sequence>
     *                 &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}short" />
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "host"
    })
    public static class Hosts {

        @XmlElement(name = "Host")
        protected List<Site.Hosts.Host> host;

        /**
         * Gets the value of the host property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the host property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getHost().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Site.Hosts.Host }
         * 
         * 
         */
        public List<Site.Hosts.Host> getHost() {
            if (host == null) {
                host = new ArrayList<Site.Hosts.Host>();
            }
            return this.host;
        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="Host_Name" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="IP_address" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="OS" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="Load_avg_1min" type="{http://www.w3.org/2001/XMLSchema}float"/>
         *         &lt;element name="Load_avg_5min" type="{http://www.w3.org/2001/XMLSchema}float"/>
         *         &lt;element name="Load_avg_15min" type="{http://www.w3.org/2001/XMLSchema}float"/>
         *       &lt;/sequence>
         *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}short" />
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "hostName",
            "ipAddress",
            "os",
            "loadAvg1Min",
            "loadAvg5Min",
            "loadAvg15Min"
        })
        public static class Host {

            @XmlElement(name = "Host_Name", required = true)
            protected String hostName;
            @XmlElement(name = "IP_address", required = true)
            protected String ipAddress;
            @XmlElement(name = "OS", required = true)
            protected String os;
            @XmlElement(name = "Load_avg_1min")
            protected float loadAvg1Min;
            @XmlElement(name = "Load_avg_5min")
            protected float loadAvg5Min;
            @XmlElement(name = "Load_avg_15min")
            protected float loadAvg15Min;
            @XmlAttribute(name = "id")
            protected Short host_id;

            /**
             * Gets the value of the hostName property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getHostName() {
                return hostName;
            }

            /**
             * Sets the value of the hostName property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setHostName(String value) {
                this.hostName = value;
            }

            /**
             * Gets the value of the ipAddress property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getIPAddress() {
                return ipAddress;
            }

            /**
             * Sets the value of the ipAddress property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setIPAddress(String value) {
                this.ipAddress = value;
            }

            /**
             * Gets the value of the os property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getOS() {
                return os;
            }

            /**
             * Sets the value of the os property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setOS(String value) {
                this.os = value;
            }

            /**
             * Gets the value of the loadAvg1Min property.
             * 
             */
            public float getLoadAvg1Min() {
                return loadAvg1Min;
            }

            /**
             * Sets the value of the loadAvg1Min property.
             * 
             */
            public void setLoadAvg1Min(float value) {
                this.loadAvg1Min = value;
            }

            /**
             * Gets the value of the loadAvg5Min property.
             * 
             */
            public float getLoadAvg5Min() {
                return loadAvg5Min;
            }

            /**
             * Sets the value of the loadAvg5Min property.
             * 
             */
            public void setLoadAvg5Min(float value) {
                this.loadAvg5Min = value;
            }

            /**
             * Gets the value of the loadAvg15Min property.
             * 
             */
            public float getLoadAvg15Min() {
                return loadAvg15Min;
            }

            /**
             * Sets the value of the loadAvg15Min property.
             * 
             */
            public void setLoadAvg15Min(float value) {
                this.loadAvg15Min = value;
            }

            /**
             * Gets the value of the id property.
             * 
             * @return
             *     possible object is
             *     {@link Short }
             *     
             */
            public Short getId() {
                return host_id;
            }

            /**
             * Sets the value of the id property.
             * 
             * @param value
             *     allowed object is
             *     {@link Short }
             *     
             */
            public void setId(Short value) {
                this.host_id = value;
            }

        }

    }

}
