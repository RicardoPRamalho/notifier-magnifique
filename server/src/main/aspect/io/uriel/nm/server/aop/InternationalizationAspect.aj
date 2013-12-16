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
package io.uriel.nm.server.aop;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

/**
 * Aspect that adds a message bundle and helper methods via ITD.
 * 
 * @author Thiago Uriel M. Garcia
 */
public privileged aspect InternationalizationAspect {

    declare parents: (@Component io.uriel.nm.server.view..*) implements IMessageBundleAware;
    declare parents: (@Controller io.uriel.nm.server.rest..*) implements IMessageBundleAware;
    declare parents: (@Service io.uriel.nm.server.business..*) implements IMessageBundleAware;
    
    /** Spring object that provides I18N messages. */
    @Autowired
    private ResourceBundleMessageSource IMessageBundleAware.messages;
    
    /**
     * Retrieves a given message from internal bundle.
     * 
     * @param name  Message name as defined in bundle.
     * @return      Localized message with argument substitution.
     */
    public String IMessageBundleAware.getMessage(String name)
    {
        return getMessage(name, new String[]{});
    }
    
    /**
     * Retrieves a given message from internal bundle.
     * 
     * @param name  Message name as defined in bundle.
     * @param args  Arguments for wildcard replacements.
     * @return      Localized message with argument substitution.
     */
    public String IMessageBundleAware.getMessage(String name, String[] args)
    {
        final String message = messages.getMessage(name, args, Locale.getDefault());
        return message;
    }
    
    /**
     * Marker interface to any object that's going to use I18N messages.
     * 
     * @author Thiago Uriel M. Garcia
     */
    private interface IMessageBundleAware { }
    
}
