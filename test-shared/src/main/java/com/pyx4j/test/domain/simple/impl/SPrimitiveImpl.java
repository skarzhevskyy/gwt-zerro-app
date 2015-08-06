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
 * @version $Id: SPrimitiveImpl.java 12160 2012-11-18 22:59:56Z vlads $
 */
package com.pyx4j.test.domain.simple.impl;

import java.io.Serializable;

import com.pyx4j.test.domain.simple.SMap;
import com.pyx4j.test.domain.simple.SPrimitive;

public class SPrimitiveImpl<E extends Serializable> implements SPrimitive<E> {

    private final SMap map;

    private final String key;

    public SPrimitiveImpl(SMap map, String fieldName) {
        this.map = map;
        this.key = fieldName;
    }

    @Override
    public void setValue(E value) {
        map.put(key, value);

    }

    @SuppressWarnings("unchecked")
    @Override
    public E getValue() {
        return (E) map.get(key);
    }

}
