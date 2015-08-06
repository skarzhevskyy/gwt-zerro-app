/*
 * Pyx4j framework
 * Copyright (C) 2008-2011 pyx4j.com.
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
 * Created on Jan 31, 2012
 * @author vlads
 * @version $Id: TestJettyLaunch.java 12144 2012-11-09 19:58:39Z vlads $
 */
package com.pyx4j.test.server;

import com.pyx4j.jetty.JettyLaunch;

public class TestJettyLaunch extends JettyLaunch {

    public static void main(String[] args) throws Exception {
        JettyLaunch.launch(new TestJettyLaunch());
    }

    @Override
    public int getServerPort() {
        return 8881;
    }

    @Override
    public String getContextPath() {
        return "/test";
    }

    @Override
    public String getHashLoginServiceConfig() {
        return null;
    }
}
