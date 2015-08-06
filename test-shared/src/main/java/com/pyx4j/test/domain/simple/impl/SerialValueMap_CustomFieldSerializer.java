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
 * @version $Id: SerialValueMap_CustomFieldSerializer.java 12160 2012-11-18 22:59:56Z vlads $
 */
package com.pyx4j.test.domain.simple.impl;

import com.google.gwt.user.client.rpc.CustomFieldSerializer;
import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.client.rpc.SerializationStreamReader;
import com.google.gwt.user.client.rpc.SerializationStreamWriter;
import com.google.gwt.user.client.rpc.core.java.util.Map_CustomFieldSerializerBase;

public class SerialValueMap_CustomFieldSerializer extends CustomFieldSerializer<SerialValueMap> {

    @Override
    public void deserializeInstance(SerializationStreamReader streamReader, SerialValueMap instance) throws SerializationException {
        deserialize(streamReader, instance);
    }

    @Override
    public void serializeInstance(SerializationStreamWriter streamWriter, SerialValueMap instance) throws SerializationException {
        serialize(streamWriter, instance);
    }

    public static void deserialize(SerializationStreamReader streamReader, SerialValueMap instance) throws SerializationException {
        Map_CustomFieldSerializerBase.deserialize(streamReader, instance.map);
    }

    public static void serialize(SerializationStreamWriter streamWriter, SerialValueMap instance) throws SerializationException {
        Map_CustomFieldSerializerBase.serialize(streamWriter, instance.map);
    }
}
