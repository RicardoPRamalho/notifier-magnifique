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
package io.uriel.nm.server.view;

import io.uriel.nm.server.business.model.Device;
import io.uriel.nm.server.view.SubscriptionsBean;

public privileged aspect SubscriptionBeanAspects 
{
    /**
     * Getter method for {@code multipleSelection}.
     * @return Current value for {@code multipleSelection}.
     */
    public boolean SubscriptionsBean.isMultipleSelection() 
    {
        return multipleSelection;
    }

    /**
     * Getter method for {@code currentDevice}.
     * @return Current value for {@code currentDevice}.
     */
    public Device SubscriptionsBean.getCurrentDevice() 
    {
        return currentDevice;
    }

    /**
     * Setter method for {@code currentDevice}.
     * @param currentDevice New value for {@code currentDevice}.
     */
    public void SubscriptionsBean.setCurrentDevice(Device currentDevice) 
    {
        this.currentDevice = currentDevice;
    }

    /**
     * Getter method for {@code currentDevices}.
     * @return Current value for {@code currentDevices}.
     */
    public Device[] SubscriptionsBean.getCurrentDevices() 
    {
        return currentDevices;
    }

    /**
     * Setter method for {@code currentDevices}.
     * @param currentDevice New value for {@code currentDevices}.
     */
    public void SubscriptionsBean.setCurrentDevices(Device[] currentDevices) 
    {
        this.currentDevices = currentDevices;
    }    
    
    /**
     * Getter method for {@code messageToPush}.
     * @return Current value for {@code messageToPush}.
     */
    public String SubscriptionsBean.getMessageToPush() 
    {
        return messageToPush;
    }

    /**
     * Setter method for {@code messageToPush}.
     * @param messageToPush New value for {@code messageToPush}.
     */
    public void SubscriptionsBean.setMessageToPush(String messageToPush) 
    {
        this.messageToPush = messageToPush;
    }    
}
