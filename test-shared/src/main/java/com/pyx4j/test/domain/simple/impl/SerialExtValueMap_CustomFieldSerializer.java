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
 * @version $Id: SerialExtValueMap_CustomFieldSerializer.java 12160 2012-11-18 22:59:56Z vlads $
 */
package com.pyx4j.test.domain.simple.impl;

import com.google.gwt.user.client.rpc.CustomFieldSerializer;
import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.client.rpc.SerializationStreamReader;
import com.google.gwt.user.client.rpc.SerializationStreamWriter;
import com.google.gwt.user.client.rpc.core.java.util.Map_CustomFieldSerializerBase;

public class SerialExtValueMap_CustomFieldSerializer extends CustomFieldSerializer<SerialExtValueMap> {

    @Override
    public void deserializeInstance(SerializationStreamReader streamReader, SerialExtValueMap instance) throws SerializationException {
        deserialize(streamReader, instance);
    }

    @Override
    public void serializeInstance(SerializationStreamWriter streamWriter, SerialExtValueMap instance) throws SerializationException {
        serialize(streamWriter, instance);
    }

    public static void deserialize(SerializationStreamReader streamReader, SerialExtValueMap instance) throws SerializationException {
        Map_CustomFieldSerializerBase.deserialize(streamReader, instance);
    }

    public static void serialize(SerializationStreamWriter streamWriter, SerialExtValueMap instance) throws SerializationException {
        Map_CustomFieldSerializerBase.serialize(streamWriter, instance);
    }
}
