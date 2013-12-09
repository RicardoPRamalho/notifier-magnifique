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
package io.uriel.nm.server.business;

import java.util.Locale;

import io.uriel.nm.server.business.model.Device;
import io.uriel.nm.server.business.repository.IDeviceRepository;
import io.uriel.nm.server.exception.DeviceAlreadyRegisteredException;
import io.uriel.nm.server.exception.DeviceNotSubscribedException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Service;

/**
 * Services related to the subscription process.
 * 
 * @author Thiago Uriel M. Garcia
 */
@Service
public class SubscriptionServices 
{
    /** Spring object that provides I18N messages. */
    @Autowired
    private ResourceBundleMessageSource messages;
    
    /** Repository interface used to access persistence layer. */
    @Autowired
    private IDeviceRepository deviceRepository;
    
    /**
     * Synchronizes current entity state with persistence layer.
     * <p>
     * Synchronization will only occur if internal hashcode, stored since last
     * DB operation is present and different from current state's hash code.
     * <p>
     * Provided entity will be updated with any new information.
     */
    public void synchronize(Device device)
    {
        if (device.getSynchedHashCode() != -1 && device.hashCode() != device.getSynchedHashCode()) 
        {
            device = deviceRepository.save(device);
            device.updateSynchedHashCode();
        }
    }
    
    /**
     * Subscribes a device, to allow the server to send notifications.
     * 
     * @param subscription  Subscription code from specific Notification Service.
     * @param osName        Operational system that is running in the device.
     * @param osVersion     Operational system version running on the device.
     * 
     * @return
     *      The subscribed {@code Device} entity, with updated information.
     *      
     * @throws DeviceAlreadyRegisteredException
     *      If the provided information relates to an already registered device, then
     *      this exception will be thrown, so this problematic scenario may be handled.
     */
    public Device subscribe(String subscription, String osName, String osVersion)
    {
        Device device = deviceRepository.findBySubscription(subscription);
        if (device == null)
        {
            device = new Device();
            device.setSubscription(subscription);
            device.setOsName(osName);
            device.setOsVersion(osVersion);
            this.synchronize(device);
            return device;
        }
        else
        {
            final String message = getMessage("subscription.alreadySubscribed");
            throw new DeviceAlreadyRegisteredException(message);
        }
    }
    
    /**
     * Unsubscribes a device, impossibilitating further notifications.
     * 
     * @param subscription  Subscription code that must be cancelled.
     * 
     * @throws DeviceNotSubscribedException
     *      If a given subscription code does not exists in the persistence layer,
     *      this exception will be throw, to allow a friendly handling for the issue.
     */
    public void unsubscribe(String deviceId)
    {
        Device device = deviceRepository.findOne(deviceId);
        if (device != null)
        {
            deviceRepository.delete(device);
        }
        else
        {
            final String message = getMessage("subscription.subscriptionNotFound");
            throw new DeviceNotSubscribedException(message);
        }
    }
    
    /**
     * Helper method.
     * <p>
     * Retrieves a message from internal bundle and formats/localizes it.
     * 
     * @param name  Message name, as registered in bundles.
     * @param args  Optional arguments for wildcard replacements.
     * @return      The localized message with wildcards replaced.
     */
    private String getMessage(String name, String... args)
    {
        final String message = messages.getMessage("subscription.alreadySubscribed", args, Locale.getDefault());
        return message;
    }
    
}
