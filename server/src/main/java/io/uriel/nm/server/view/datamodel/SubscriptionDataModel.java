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
package io.uriel.nm.server.view.datamodel;

import io.uriel.nm.server.business.SubscriptionServices;
import io.uriel.nm.server.business.model.Device;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Data model used to allow extended functionality to subscription table UI.
 * <p>
 * Since this object is provided by SpringFramework, it's wrapped data won't
 * be defined via constructor, which is a common way.
 * <p>
 * To define the wrapped data, receive an instance for this object, by Spring's
 * regular Dependency Injection system, and call the public method from base
 * class - {@code setWrappedData}.
 * 
 * @author Thiago Uriel M. Garcia
 */
@Component
public class SubscriptionDataModel extends ListDataModel<Device>
implements SelectableDataModel<Device>
{
    @Autowired
    private SubscriptionServices service;

    /** {@inheritDoc} */
    @Override
    public Device getRowData(String rowKey) 
    {
        return service.findDevice(rowKey);
    }

    /** {@inheritDoc} */
    @Override
    public Object getRowKey(Device device) 
    {
        return device.getDeviceId();
    }
}
