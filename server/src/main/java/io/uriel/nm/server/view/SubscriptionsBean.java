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

import io.uriel.nm.server.business.SubscriptionServices;
import io.uriel.nm.server.business.model.Device;
import io.uriel.nm.server.view.datamodel.SubscriptionDataModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope("view")
public class SubscriptionsBean extends AbstractManagedBean 
{
    /** Serialization constant generated by JVM. */
    private static final long serialVersionUID = -8313831191353361482L;

    /** 
     * Tells the UI if multiple selection has been made.
     * <p>
     * When multiple selection occurs, then a message is broadcasted to all
     * selected subscribers. This field is maintained by internal listeners,
     * so long as UI goes, it is read-only. 
     */
    private boolean multipleSelection;
    
    /** Current device, being managed by the UI. */
    private Device currentDevice;

    /** Current devices, being managed by the UI. */
    private Device[] currentDevices;
    
    /** Message that's going to be pushed to selected device.*/
    private String messageToPush;
    
    /** Data model used to provided entities to UI table. */
    @Autowired
    private SubscriptionDataModel dataModel;
    
    /** Service used to manage subscriptions. */
    @Autowired
    private SubscriptionServices subscriptionService;
    
    /**
     * Provides a <b>live</b> version of the current list of subscriptions.
     * 
     * @return
     *      An {@code Iterable} with all devices currently subscribed.
     */
    public Iterable<Device> getSubscriptions()
    {
        logger.debug(message("log.getSubscriptions.begin"));
        return dataModel;
    }
    
    /**
     * Listener for the row selection event.
     * <p>
     * Whenever multiple rows are selected, this listener will "know" and then
     * toggle the {@code multipleSelection} flag accordingly. This will enable
     * or disable UI controles for multiple subscriptions.
     */
    public void checkMultipleSelection()
    {
        logger.debug(message("log.checkMultipleSelection.begin"));
        multipleSelection = (currentDevices.length > 1);
        logger.debug(message("log.checkMultipleSelection.end", String.valueOf(multipleSelection)));
    }
    
    /** 
     * Cancels a subscription for a given device indentifier.
     * 
     * @param deviceId
     *      Identifier of the device that is going to be unregistered. 
     */
    public void unsubscribe(String deviceId)
    {
        logger.debug(message("log.unsubscribe.begin", deviceId));
        subscriptionService.unsubscribe(deviceId);
    }
    
    public void pushNotification()
    {
        logger.debug(message("log.notify.begin", currentDevice.getDeviceId()));
    }

    
    
    //----[ Getters and Setters required by the UI ]-------------------------------------
    
    /**
     * Getter method for {@code multipleSelection}.
     * @return Current value for {@code multipleSelection}.
     */
    public boolean isMultipleSelection() 
    {
        return multipleSelection;
    }

    /**
     * Getter method for {@code currentDevice}.
     * @return Current value for {@code currentDevice}.
     */
    public Device getCurrentDevice() 
    {
        return currentDevice;
    }

    /**
     * Setter method for {@code currentDevice}.
     * @param currentDevice New value for {@code currentDevice}.
     */
    public void setCurrentDevice(Device currentDevice) 
    {
        this.currentDevice = currentDevice;
    }

    /**
     * Getter method for {@code currentDevices}.
     * @return Current value for {@code currentDevices}.
     */
    public Device[] getCurrentDevices() 
    {
        return currentDevices;
    }

    /**
     * Setter method for {@code currentDevices}.
     * @param currentDevice New value for {@code currentDevices}.
     */
    public void setCurrentDevices(Device[] currentDevices) 
    {
        this.currentDevices = currentDevices;
    }    
    
    /**
     * Getter method for {@code messageToPush}.
     * @return Current value for {@code messageToPush}.
     */
    public String getMessageToPush() 
    {
        return messageToPush;
    }

    /**
     * Setter method for {@code messageToPush}.
     * @param messageToPush New value for {@code messageToPush}.
     */
    public void setMessageToPush(String messageToPush) 
    {
        this.messageToPush = messageToPush;
    }
}
