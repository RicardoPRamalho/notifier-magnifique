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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope("view")
public class SubscriptionsBean extends AbstractManagedBean 
{
    /** Serialization constant generated by JVM. */
    private static final long serialVersionUID = -8313831191353361482L;
    
    /** Service used to manage subscriptions. */
    @Autowired
    private SubscriptionServices subscriptionService;
    
    /**
     * Provides a live version of the current list of subscriptions.
     * 
     * @return
     *      An {@code Iterable} with all devices currently subscribed.
     */
    public Iterable<Device> getSubscriptions()
    {
        return subscriptionService.listDevices();
    }
}
