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
package io.uriel.nm.server.exception;

/**
 * Exception that represents the problem of a device not being subscribed, when
 * the business layer requests some operation for it.
 * 
 * @author Thiago Uriel M. Garcia
 */
public class DeviceNotSubscribedException extends NotifierException
{
    /** Default serialization constant. */
    private static final long serialVersionUID = 4789388562150511090L;

    /**
     * Constructor.
     * 
     * @param message   Provides further information on the problem.
     */
    public DeviceNotSubscribedException(String message) {
        super(message);
    }
}
