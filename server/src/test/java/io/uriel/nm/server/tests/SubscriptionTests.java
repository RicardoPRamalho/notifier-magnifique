package io.uriel.nm.server.tests;

import static org.junit.Assert.assertNotNull;
import io.uriel.nm.server.business.model.Device;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/test-context.xml")
public class SubscriptionTests
{
    @Autowired
    private RestTemplate rest;
    
    @Test
    public void subscribeDeviceWithSuccess()
    {
        final String url = "http://localhost:8080/api/subscriptions";
        
        final Device device = new Device();
        device.setOwnerName("Thiago Uriel");
        device.setSubscription("Abc123DCC");
        device.setOsName("Android");
        device.setOsVersion("Jelly Bean");
        
        Device newDevice = rest.postForObject(url, device, Device.class);
        assertNotNull(newDevice);
    }
}
