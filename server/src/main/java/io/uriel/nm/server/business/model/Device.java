/*
 * Copyright 2013 Thiago Uriel M. Garcia
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.uriel.nm.server.business.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * This entity represents a mobile device (smartphone, tablet, and so on), that
 * is going to communicate with the server, by subscribing for notifications and
 * receiving messages.
 * 
 * @author Thiago Uriel M. Garcia
 */
@Document(collection="Devices")
public class Device implements Serializable 
{
    /** Default serialization constant. */
    private static final long serialVersionUID = -3666808196997540388L;

    /** Device's internal identifier. */
    @Id
    private String deviceId;
    
    /** Code used with device's provider to send notifications. */
    private String subscription;
    
    /** Operational system running on the device. */
    private String osName;
    
    /** Operation system's version running on the device. */
    private String osVersion;
    
    /** Date in which the device was registered. */
    @CreatedDate
    private Date registryDate;
    
    /** Hash code stored since last DB operation. */
    @Transient
    private int synchedHashCode;

    /**
     * Constructor.
     * <p>
     * Generates a new instance of {@code Device} and initializes it's fields.
     */
    public Device() 
    {
        super();
        this.registryDate = new Date();
    }
    
    /** Updates internal sychronization code. */
    public void updateSynchedHashCode()
    {
        this.synchedHashCode = hashCode();
    }
    
    /**
     * Getter method for deviceId.
     * @return the current value for deviceId.
     */
    public String getDeviceId()
    {
        return deviceId;
    }

    /**
     * Defines a new value for deviceId.
     * @param deviceId the deviceId to set
     */
    public void setDeviceId(String deviceId)
    {
        this.deviceId = deviceId;
    }

    /**
     * Getter method for subscription.
     * @return the current value for subscription.
     */
    public String getSubscription()
    {
        return subscription;
    }

    /**
     * Defines a new value for subscription.
     * @param subscription the subscription to set
     */
    public void setSubscription(String subscription)
    {
        this.subscription = subscription;
    }

    /**
     * Getter method for osName.
     * @return the current value for osName.
     */
    public String getOsName()
    {
        return osName;
    }

    /**
     * Defines a new value for osName.
     * @param osName the osName to set
     */
    public void setOsName(String osName)
    {
        this.osName = osName;
    }

    /**
     * Getter method for osVersion.
     * @return the current value for osVersion.
     */
    public String getOsVersion()
    {
        return osVersion;
    }

    /**
     * Defines a new value for osVersion.
     * @param osVersion the osVersion to set
     */
    public void setOsVersion(String osVersion)
    {
        this.osVersion = osVersion;
    }

    /**
     * Getter method for registryDate.
     * @return the current value for registryDate.
     */
    public Date getRegistryDate()
    {
        return registryDate;
    }

    /**
     * Getter method for synchedHashCode.
     * @return the current value for synchedHashCode.
     */
    public int getSynchedHashCode()
    {
        return synchedHashCode;
    }
}
