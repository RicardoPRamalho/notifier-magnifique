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
package io.uriel.nm.server.rest;

import io.uriel.nm.server.business.SubscriptionServices;
import io.uriel.nm.server.exception.NotifierException;
import io.uriel.nm.server.rest.vo.PushResults;
import io.uriel.nm.server.rest.vo.RestError;

import org.primefaces.push.PushContext;
import org.primefaces.push.PushContextFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Controller containing RESTful definitions related to subscription management.
 * 
 * @author Thiago Uriel M. Garcia
 */
@Controller
@RequestMapping("/subscriptions")
public class SubscriptionController 
{
    /** Service object, exposed by this RESTful controller. */
    @Autowired
    private SubscriptionServices service;
    
    /**
     * REST service that receives a requisition with a Device and subscribes it.
     * 
     * @param body  Incoming request body, with a device in JSON format.
     * 
     * @return
     *      In case of success, it will return a code of <b>201 CREATED</b> and
     *      a JSON with the new subscription information.
     *      <p>
     *      If subscription is not possible (i.e. problems with provided device),
     *      then it will return a code of <b>409 BAD REQUEST</b> and a JSON with
     *      additional information about the problem.
     */
    @RequestMapping(method=RequestMethod.POST, consumes="application/json", produces="application/json")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ResponseEntity<String> subscribe(@RequestBody String body)
    {
        return null;
    }
    
    /**
     * REST service that receives a device ID and unsubscribes it.
     * 
     * @param deviceId  Device identifier that must be unregistered.
     * 
     * @return
     *      In case of success, it will return a code of <b>203 NO CONTENT</b>
     *      and no JSON body.
     *      <p>
     *      If unsubscription is not possible, (i.e. device is not registered),
     *      then it will return a code of <b>409 BAD REQUEST</b> and a JSON with
     *      additional information about the problem.
     */
    @RequestMapping(value="/{deviceId}", method=RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void unsubscribe(@RequestParam String deviceId) 
    {
        service.unsubscribe(deviceId);
        PushResults results = new PushResults(PushResults.ResultType.REMOVE_ITEM, deviceId);
        PushContext pushContext = PushContextFactory.getDefault().getPushContext();
        pushContext.push("/subscriptions", results.toJson());
    }
    
    /**
     * Handle errors thrown by REST services and delivers a proper HTTP response.
     * 
     * @param nExp  Exception that has ocurred.
     * @return      {@link RestError} with error details. 
     */
    @ExceptionHandler(NotifierException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public RestError handleErrors(NotifierException nExp)
    {
        RestError error = new RestError(nExp.getMessage());
        return error;
    }   
}
