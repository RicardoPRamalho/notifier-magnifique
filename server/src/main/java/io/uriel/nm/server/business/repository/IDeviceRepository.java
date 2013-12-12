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
package io.uriel.nm.server.business.repository;

import io.uriel.nm.server.business.model.Device;

import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Repository definition, containing methods that communicate with persistence,
 * for {@code Device} entity. Implemented by the Spring Data Engine.
 * 
 * @author Thiago Uriel M. Garcia
 */
public interface IDeviceRepository extends PagingAndSortingRepository<Device, String>
{
    /**
     * Finds a {@code Device} by it's subscription code.
     * 
     * @param subscription  Subscription code, target for the retrieval.
     * @return              {@code Device} entity, or null, if none exists.
     */
    Device findBySubscription(String subscription);
    
    /**
     * Counts all devices for a given platform.
     * 
     * @param osName    Target platform for subscription counting.
     * @return          {@code long} with subscription count for given platform.
     */
    long countByOsName(String osName);
    
}
