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
 * @version $Id: SimpleServiceTestPanel.java 12160 2012-11-18 22:59:56Z vlads $
 */
package com.pyx4j.test.client.simple;

import java.util.EnumSet;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import com.pyx4j.test.domain.simple.MapDTO;
import com.pyx4j.test.domain.simple.MapSPrimitiveDTO;
import com.pyx4j.test.rpc.service.simple.SimpleService;
import com.pyx4j.test.rpc.service.simple.SimpleServiceAsync;

public class SimpleServiceTestPanel extends VerticalPanel {

    private final SimpleServiceAsync srv = GWT.create(SimpleService.class);

    private enum Option {

        String,

        MapOfString,

        MapOfStringAndBoolean,

        MapSPrimitive
    }

    final TextBox nameField;

    final Label inputLabel;

    final Label resultLabel;

    public SimpleServiceTestPanel() {

        final ListBox dropBox = new ListBox(false);
        this.add(dropBox);
        for (Option option : EnumSet.allOf(Option.class)) {
            dropBox.addItem(option.name());
        }

        this.add(nameField = new TextBox());
        nameField.setText("GWT User");

        final Button sendButton = new Button("Send");
        this.add(sendButton);

        this.add(inputLabel = new Label());
        this.add(resultLabel = new Label());

        sendButton.addClickHandler(new ClickHandler() {

            @SuppressWarnings("unchecked")
            @Override
            public void onClick(ClickEvent event) {
                inputLabel.setText("Sending ...");
                resultLabel.setText("...");

                Option option = Option.valueOf(dropBox.getItemText(dropBox.getSelectedIndex()));

                switch (option) {
                case String:
                    inputLabel.setText(nameField.getValue());
                    srv.echoString(nameField.getValue(), new AnyAsyncCallback());
                    break;
                case MapOfString: {
                    MapDTO map = new MapDTO();
                    map.setValue("s", nameField.getValue());

                    inputLabel.setText(map.toString());

                    srv.echoMapDTO(map, new AnyAsyncCallback());
                }
                    break;
                case MapOfStringAndBoolean: {
                    MapDTO map = new MapDTO();
                    map.setValue("s", nameField.getValue());
                    map.setValue("b", Boolean.TRUE);

                    inputLabel.setText(map.toString());

                    srv.echoMapDTO(map, new AnyAsyncCallback());
                }
                case MapSPrimitive: {
                    MapSPrimitiveDTO map = new MapSPrimitiveDTO();
                    map.name().setValue(nameField.getValue());
                    map.flag().setValue(Boolean.TRUE);

                    inputLabel.setText(map.toString());

                    srv.echoMapDTO(map, new AnyAsyncCallback());
                }
                    break;
                }

            }
        });
    }

    @SuppressWarnings("rawtypes")
    private class AnyAsyncCallback implements AsyncCallback {

        @Override
        public void onFailure(Throwable caught) {
            resultLabel.setText(caught.getMessage());
        }

        @Override
        public void onSuccess(Object result) {
            resultLabel.setText(result.toString());
        }

    }
}
