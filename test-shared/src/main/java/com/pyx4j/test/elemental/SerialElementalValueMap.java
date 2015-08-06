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
 * Created on 2012-11-18
 * @author vlads
 * @version $Id: SerialElementalValueMap.java 12160 2012-11-18 22:59:56Z vlads $
 */
package com.pyx4j.test.elemental;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.GwtTransient;

import com.pyx4j.test.domain.simple.SMap;

import elemental.util.ArrayOfString;
import elemental.util.Collections;
import elemental.util.MapFromStringTo;

@SuppressWarnings("serial")
public class SerialElementalValueMap implements SMap, Serializable {

    @GwtTransient
    protected MapFromStringTo<Serializable> map = Collections.mapFromStringTo();

    @Override
    public Serializable put(String key, Serializable value) {
        map.put(key, value);
        return null;
    }

    @Override
    public Serializable get(String key) {
        return map.get(key);
    }

    @Override
    public String toString() {
        ArrayOfString keys = map.keys();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= keys.length(); i++) {
            sb.append(keys.get(i));
            sb.append('=');
            sb.append(get(keys.get(i)));
            sb.append(',');
        }
        return sb.toString();
    }

}
