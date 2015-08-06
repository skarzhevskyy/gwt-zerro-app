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
 * @version $Id: TestClient.java 12140 2012-11-09 05:02:56Z vlads $
 */
package com.pyx4j.test.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.UIObject;

import com.pyx4j.test.client.simple.SimpleServiceTestPanel;

public class TestClient implements EntryPoint {

    @Override
    public void onModuleLoad() {
        hideLoadingIndicator();

        final SimplePanel holder = new SimplePanel();

        // Create a menu bar
        MenuBar menu = new MenuBar();
        menu.setAutoOpen(true);
        menu.setWidth("500px");
        menu.setAnimationEnabled(true);

        RootPanel.get().add(menu);
        RootPanel.get().add(holder);

        // Create the Tests menu
        MenuBar testsMenu = new MenuBar(true);
        testsMenu.setAnimationEnabled(true);
        menu.addItem(new MenuItem("Tests", testsMenu));
        testsMenu.addItem("Simple Service", new Command() {

            @Override
            public void execute() {
                holder.setWidget(new SimpleServiceTestPanel());
            }
        });

    }

    public void hideLoadingIndicator() {
        // Remove the loading icon
        RootPanel loading = RootPanel.get("loading");
        if (loading != null) {
            com.google.gwt.user.client.Element elem = loading.getElement();
            UIObject.setVisible(elem, false);
            DOM.setInnerHTML(elem, "");
            loading.removeFromParent();
            elem.getParentElement().removeChild(elem);
        }
    }

}
