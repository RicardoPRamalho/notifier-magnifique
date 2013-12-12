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

import io.uriel.nm.server.business.model.Device;

import java.util.Date;

public privileged aspect DeviceAspects 
{
    /**
     * Getter method for deviceId.
     * @return the current value for deviceId.
     */
    public String Device.getDeviceId()
    {
        return deviceId;
    }

    /**
     * Defines a new value for deviceId.
     * @param deviceId the deviceId to set
     */
    public void Device.setDeviceId(String deviceId)
    {
        this.deviceId = deviceId;
    }

    /**
     * Getter method for ownerName.
     * @return the current value for ownerName.
     */
    public String Device.getOwnerName() 
    {
        return ownerName;
    }

    /**
     * Defines a new value for ownerName.
     * @param ownerName the deviceId to set
     */
    public void Device.setOwnerName(String ownerName)
    {
        this.ownerName = ownerName;
    }

    /**
     * Getter method for subscription.
     * @return the current value for subscription.
     */
    public String Device.getSubscription()
    {
        return subscription;
    }

    /**
     * Defines a new value for subscription.
     * @param subscription the subscription to set
     */
    public void Device.setSubscription(String subscription)
    {
        this.subscription = subscription;
    }

    /**
     * Getter method for osName.
     * @return the current value for osName.
     */
    public String Device.getOsName()
    {
        return osName;
    }

    /**
     * Defines a new value for osName.
     * @param osName the osName to set
     */
    public void Device.setOsName(String osName)
    {
        this.osName = osName;
    }

    /**
     * Getter method for osVersion.
     * @return the current value for osVersion.
     */
    public String Device.getOsVersion()
    {
        return osVersion;
    }

    /**
     * Defines a new value for osVersion.
     * @param osVersion the osVersion to set
     */
    public void Device.setOsVersion(String osVersion)
    {
        this.osVersion = osVersion;
    }

    /**
     * Getter method for registryDate.
     * @return the current value for registryDate.
     */
    public Date Device.getRegistryDate()
    {
        return registryDate;
    }

    /**
     * Getter method for synchedHashCode.
     * @return the current value for synchedHashCode.
     */
    public int Device.getSynchedHashCode()
    {
        return synchedHashCode;
    }    
}
