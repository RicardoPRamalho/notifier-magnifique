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

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Persistent;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * This entity represents a mobile device (smartphone, tablet, and so on), that
 * is going to communicate with the server, by subscribing for notifications and
 * receiving messages.
 * 
 * @author Thiago Uriel M. Garcia
 */
@Document(collection="Devices")
public class Device implements Serializable 
{
    /** Default serialization constant. */
    private static final long serialVersionUID = -3666808196997540388L;

    /** Device's internal identifier. */
    @Id
    private String deviceId;
    
    /** Identification name for device's owner. */
    @NotNull
    @Size(min=3, max=50)
    @Persistent
    private String ownerName;
    
    /** Code used with device's provider to send notifications. */
    @NotNull
    @Persistent
    private String subscription;
    
    /** Operational system running on the device. */
    @NotNull
    @Persistent
    private String osName;
    
    /** Operation system's version running on the device. */
    @NotNull
    @Persistent
    private String osVersion;
    
    /** Date in which the device was registered. */
    @CreatedDate
    private Date registryDate;
    
    /** Hash code stored since last DB operation. */
    @Transient
    private int synchedHashCode;

    /**
     * Constructor.
     * <p>
     * Generates a new instance of {@code Device} and initializes it's fields.
     */
    public Device() 
    {
        super();
        this.registryDate = new Date();
    }
    
    /** Updates internal sychronization code. */
    public void updateSynchedHashCode()
    {
        this.synchedHashCode = hashCode();
    }
}
