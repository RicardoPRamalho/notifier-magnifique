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
import java.util.TimeZone;

import org.springframework.stereotype.Component;

/**
 * Aspects that provides Locale and TimeZone informations via ITD.
 * <p>
 * Targets are Managed Beans that must provide I18N information to their managed
 * interfaces, whenever a message or date must be localized.
 * 
 * @author Thiago Uriel M. Garcia
 */
public privileged aspect LocaleAndTimezoneAspect {

    declare parents: (@Component io.uriel.nm.server.view..*) implements ILocaleAndTimeZoneAware;

    /** 
     * Provides the current default {@link TimeZone}, that may be used for
     * all formatting needs in the application.
     * 
     * @return
     *      Current {@link TimeZone} used for the whole application.
     */
    public TimeZone ILocaleAndTimeZoneAware.getTimeZone()
    {
        return TimeZone.getDefault();
    }
    
    /** 
     * Provides the current default {@link Locale}, that may be used for
     * all formatting needs in the application.
     * 
     * @return
     *      Current {@link Locale} used for the whole application.
     */
    public Locale ILocaleAndTimeZoneAware.getLocale()
    {
        return Locale.getDefault();
    }
    
    /**
     * Interface marcadora para serviços que precisem de mensagens de I18N.
     * 
     * @author Thiago Uriel M. Garcia
     */
    private interface ILocaleAndTimeZoneAware { }
    
    }
