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

import java.util.Random;

/**
 * Provides randomized data for test cases.
 * 
 * @author Thiago Uriel M. Garcia
 */
class ApplicationData
{
    /** Random number generator. */
    private static final Random RAND = new Random();
    
    /** Valid chars (just HEX) for "fake" subscriptions. */
    private static final String[] VALID_CHARS = 
        {
            "0", "1", "2", "3", "4", "5", "6", "7",
            "8", "9", "a", "b", "c", "d", "e", "f"
        };
    
    /** Valid subscribers and current devices. */
    private static final String[][] SUBSCRIBERS = 
        {
            {"Dean Winchester", "ANDROID", "2.3.3"},
            {"Sam Winchester", "IOS", "6.0"},
            {"Jack Shephard", "ANDROID", "4.0.1"},
            {"John Locke", "ANDROID", "2.2"},
            {"James Ford", "ANDROID", "4.2"},
            {"Kate Austen", "IOS", "7.0"},
            {"Hugo Reyes", "IOS", "7.0"},
            {"Richard Alpert", "IOS", "7.0"},
            {"Peter Bishop", "ANDROID", "4.4"},
            {"Walter Bishop", "ANDROID", "4.2"},
            {"Olivia Dunham", "IOS", "7.0"},
            {"Phillip Broyles", "ANDROID", "3.0"},
            {"Astrid Farnsworth", "ANDROID", "4.4"},
            {"Michael Scoffield", "IOS", "5.0"},
            {"Lincoln Burrows", "ANDROID", "2.3.3"},
            {"Ted Mosby", "ANDROID", "4.1"},
            {"Barney Stinson", "IOS", "7.0"},
            {"Marshall Eriksen", "ANDROID", "4.1"},
            {"Lily Aldrin", "ANDROID", "2.3"},
            {"Robin Scherbatsky", "IOS", "7.0"},
            {"Chandler Bing", "IOS", "7.0"},
            {"Monica Geller-Bing", "ANDROID", "3.0"},
            {"Ross Geller", "IOS", "7.0"},
            {"Joey Tribbiani", "ANDROID", "2.3.3"},
            {"Phoebe Buffay", "ANDROID", "4.3"},
            {"Rachel Green", "IOS", "7.0"},
        };
    
    /**
     * Creates a subscription code, similar to what would be expected.
     * 
     * @return  A 64-Hexadecimal string, simulating a subscription token.
     */
    public static String subscriptionCode()
    {
        int index; 
        StringBuilder subscription = new StringBuilder();
        for (int i=0; i<64; i++)
        {
            index = RAND.nextInt(VALID_CHARS.length);
            subscription.append(VALID_CHARS[index]);
        }
        return subscription.toString();
    }
    
    /**
     * Provides a fake subscriber and it's device. Array positions are:
     * 0 = name; 1 = O/S; 2 = version.
     * 
     * @return  An array of subscriber information.
     */
    public static String[] subscriberInfo()
    {
        int index = RAND.nextInt(SUBSCRIBERS.length);
        return SUBSCRIBERS[index];
    }
    
}
