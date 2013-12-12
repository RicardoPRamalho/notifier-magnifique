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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Aspect that displays messages in console for services, view and rest layers.
 * <p>
 * The internal pointcuts defines which objects are targetted for logging. For
 * those targets, all of their public methods are going to be logged.
 * <p>
 * The pointcut syntax is the following:
 * <pre>
 *      expression([method scope] [return type] [fully qualified class name].[method](parametes))
 * </pre>
 * 
 * @author Thiago Uriel M. Garcia
 */
public privileged aspect LoggerAspect 
{
    /** 
     * Pointcut representing server's Rest layer.
     * <ul>
     *      <li>All classes in package {@code io.uriel.nm.server.rest};</li>
     *      <li>All public methods, with any number of arguments.</li>
     * </ul>
     */
    pointcut restLayer(): execution(public * io.uriel.nm.server.rest.*.*(..));
    
    /** 
     * Pointcut representing server's View layer.
     * <ul>
     *      <li>All classes in package {@code io.uriel.nm.server.view};</li>
     *      <li>All classes of type {@code AbstractManagedBean};</li>
     *      <li>All public methods, with any number of arguments.</li>
     * </ul>
     */
    pointcut viewLayer(): execution(public * io.uriel.nm.server.view.AbstractManagedBean.*(..));
    
    /** 
     * Pointcut representing server's Business layer.
     * <ul>
     *      <li>All classes in package {@code io.uriel.nm.server.business};</li>
     *      <li>All public methods, with any number of arguments.</li>
     * </ul>
     */    
    pointcut businessLayer(): execution(public * io.uriel.nm.server.business.*.*(..));

    /** Logger object, used to display messages in the console.*/
    private Logger logger;
    
    /** Before advice. Displays a log message before a pointcut execution. */
    before(): restLayer() || viewLayer() || businessLayer()
    {
        logger = LoggerFactory.getLogger(thisJoinPoint.getTarget().getClass());
        if (logger.isDebugEnabled()) 
        {
            logger.debug("{}: Begin.", thisJoinPoint.getSignature().getName());
            Object[] arguments = thisJoinPoint.getArgs();
            for (int i=0; i<arguments.length; i++)
            {
                Object argument = arguments[i];
                if (argument != null)
                {
                    logger.debug("  Argument '{}': '{}'.", argument.getClass().getSimpleName(), argument);
                }
            }
        }
    }

    /** After advice. Displays a log message after a pointcut execution. */
    after(): restLayer() || viewLayer() || businessLayer()
    {
        logger = LoggerFactory.getLogger(thisJoinPoint.getTarget().getClass());
        if (logger.isDebugEnabled()) 
        {
            logger.debug("{}: End.", thisJoinPoint.getSignature().getName());
        }
    }
}
