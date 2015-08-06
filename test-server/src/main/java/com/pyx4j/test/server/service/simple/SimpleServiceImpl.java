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
 * @version $Id: SimpleServiceImpl.java 12150 2012-11-11 20:10:53Z vlads $
 */
package com.pyx4j.test.server.service.simple;

import java.io.Serializable;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import com.pyx4j.test.domain.simple.MapDTO;
import com.pyx4j.test.rpc.service.simple.SimpleService;

@SuppressWarnings("serial")
public class SimpleServiceImpl extends RemoteServiceServlet implements SimpleService {

    @Override
    public String echoString(String name) throws IllegalArgumentException {
        return name;
    }

    @Override
    public MapDTO echoMapDTO(MapDTO value) throws IllegalArgumentException {
        return value;
    }

    //This breaks GWT errors
    public Serializable echoSerializable(Serializable value) {
        return value;
    }

}
