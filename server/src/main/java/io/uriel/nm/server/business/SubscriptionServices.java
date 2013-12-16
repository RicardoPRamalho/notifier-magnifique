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

import io.uriel.nm.server.business.model.Device;
import io.uriel.nm.server.business.repository.IDeviceRepository;
import io.uriel.nm.server.exception.DeviceAlreadyRegisteredException;
import io.uriel.nm.server.exception.DeviceNotSubscribedException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

/**
 * Services providing business implementation for all subscription tasks.
 * <p>
 * A subscription represents a device, registered for this application, in
 * one of the main Cloud Message Servers supported by the server.
 * 
 * @author Thiago Uriel M. Garcia
 */
@Service
public class SubscriptionServices 
{
    /** Default page size for device listing. */
    private static final int DEFAULT_PAGE_SIZE = 20;
    
    /** Repository interface used to access persistence layer. */
    @Autowired
    private IDeviceRepository deviceRepository;
    
    /**
     * Finds a specific device, by it's unique identifier.
     * 
     * @param deviceId  Device's unique identifier, used for the lookup.
     * @return          The device related to the identifier or {@code null}.
     * 
     * @throws DeviceNotSubscribedException
     *      If a given subscription code does not exists in the persistence layer,
     *      this exception will be throw, to allow a friendly handling for the issue.
     */
    public Device findDevice(String deviceId)
    {
        Device device = deviceRepository.findOne(deviceId);
        if (device != null)
        {
            return device;
        }
        else
        {
            final String message = getMessage("subscription.msg.subscriptionNotFound");
            throw new DeviceNotSubscribedException(message);
        }
    }
    
    /**
     * Lists devices following given directives of paging and sorting.
     * <p>
     * If no directives are provided, then lists a defined ammount of devices,
     * following {@code DEFAULT_PAGE_SIZE}, ordered by their registry date and
     * using a descending order.
     * 
     * @param pageAndSort
     *      Directives of paging and sorting to be following by the underlying
     *      repository. May be null, in that case, uses defaults above.
     * 
     * @return
     *      An Iterable containing all selected devices respecting directives
     *      provided in the {@code pageAndSort} parameter.
     */
    public Iterable<Device> listDevices(Pageable pageAndSort)
    {
        if (pageAndSort != null)
        {
            return deviceRepository.findAll(pageAndSort);
        }
        else
        {
            Sort sort = new Sort(Direction.DESC, "registryDate");
            final PageRequest defaultPageAndSort = new PageRequest(0, DEFAULT_PAGE_SIZE, sort);
            return deviceRepository.findAll(defaultPageAndSort);
        }
    }
    
    public long[] countDevicesByPlatform()
    {
        final long[] platforms = new long[3];
        platforms[0] = deviceRepository.countByOsName("ANDROID");
        platforms[1] = deviceRepository.countByOsName("IOS");
        platforms[2] = deviceRepository.countByOsName("WINDOWSPHONE");
        return platforms;
    }
    
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
     * @param owner         Device's owner's name.
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
    public void subscribe(Device newDevice)
    {
        Device existingDevice = deviceRepository.findBySubscription(newDevice.getSubscription());
        if (existingDevice == null)
        {
            this.synchronize(newDevice);
        }
        else
        {
            final String message = getMessage("subscription.msg.alreadySubscribed");
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
        Device device = findDevice(deviceId);
        deviceRepository.delete(device);
    }
}
