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
package io.uriel.nm.server.tests;

import static org.junit.Assert.assertNotNull;
import io.uriel.nm.server.business.model.Device;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

/**
 * Test class for subscription services.
 * 
 * @author Thiago Uriel M. Garcia
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/test-context.xml")
public class SubscriptionTests
{
    /** Base URL for the "Subscription" entity endpoint. */
    private static final String BASE_URL = "http://localhost:8080/api/subscriptions";
    
    /** Rest template used to communicate with the server. */
    @Autowired
    private RestTemplate rest;
    
    /** Tries to successfully subscribe a device. */
    @Test
    public void subscribeDeviceWithSuccess()
    {
        final String url = BASE_URL;
        final String[] subscriber = ApplicationData.subscriberInfo();
        final Device device = new Device();
        device.setOwnerName(subscriber[0]);
        device.setSubscription(ApplicationData.subscriptionCode());
        device.setOsName(subscriber[1]);
        device.setOsVersion(subscriber[2]);
        Device newDevice = rest.postForObject(url, device, Device.class);
        assertNotNull(newDevice);
    }
    
}
