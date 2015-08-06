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
 * Created on 2012-11-08
 * @author vlads
 * @version $Id: MapDTO.java 12160 2012-11-18 22:59:56Z vlads $
 */
package com.pyx4j.test.domain.simple;

import java.io.Serializable;

import com.pyx4j.test.domain.simple.impl.SerialValueMap;

@SuppressWarnings("serial")
public class MapDTO implements Serializable {

    protected SMap map;

    public MapDTO() {
        init();
    }

    private void init() {
        //devmode broken 
        // map = new SerialExtValueMap();
        map = new SerialValueMap();
        // devmode broken
        //map = new SerialElementalValueMap();
    }

    public void setValue(String key, Serializable value) {
        map.put(key, value);
    }

    @Override
    public String toString() {
        return "MapDTO [map=" + map + "]";
    }

}
