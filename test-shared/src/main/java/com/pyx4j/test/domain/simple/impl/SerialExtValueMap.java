/*
 * Pyx4j framework
 * Copyright (C) 2008-2012 pyx4j.com.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 *
 * Created on 2012-11-09
 * @author vlads
 * @version $Id: SerialExtValueMap.java 12160 2012-11-18 22:59:56Z vlads $
 */
package com.pyx4j.test.domain.simple.impl;

import java.io.Serializable;
import java.util.HashMap;

import com.pyx4j.test.domain.simple.SMap;

@SuppressWarnings("serial")
public class SerialExtValueMap extends HashMap<String, Serializable> implements SMap {

    @Override
    public Serializable get(String key) {
        return super.get(key);
    }

}
