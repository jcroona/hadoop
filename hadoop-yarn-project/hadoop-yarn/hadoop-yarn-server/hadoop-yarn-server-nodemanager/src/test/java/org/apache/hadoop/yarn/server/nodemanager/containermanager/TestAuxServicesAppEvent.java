/**
* Licensed to the Apache Software Foundation (ASF) under one
* or more contributor license agreements.  See the NOTICE file
* distributed with this work for additional information
* regarding copyright ownership.  The ASF licenses this file
* to you under the Apache License, Version 2.0 (the
* "License"); you may not use this file except in compliance
* with the License.  You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package org.apache.hadoop.yarn.server.nodemanager.containermanager.container;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

import java.nio.ByteBuffer;
import org.apache.hadoop.yarn.api.records.ApplicationId;
import org.apache.hadoop.yarn.server.nodemanager.containermanager.AuxServicesEvent;
import org.apache.hadoop.yarn.server.nodemanager.containermanager.AuxServicesAppEvent;
import org.apache.hadoop.yarn.server.nodemanager.containermanager.AuxServicesEventType;

public class TestAuxServicesAppEvent {
  @Test
  public void testGetServiceId() {
    AuxServicesAppEvent evt = 
      new AuxServicesAppEvent(AuxServicesEventType.APPLICATION_INIT, "hadoop", null, "107", ByteBuffer.allocate(10));
    assertEquals(evt.getServiceID(), "107");
  }

  @Test
  public void testGetServiceData() {
    ByteBuffer buf = ByteBuffer.allocate(10);
    AuxServicesAppEvent evt = 
        new AuxServicesAppEvent(AuxServicesEventType.APPLICATION_INIT, "hadoop", null, "107", buf);
    assertEquals(evt.getServiceData(), buf);
  }

  @Test
  public void testGetUser() {
    AuxServicesAppEvent evt = 
      new AuxServicesAppEvent(AuxServicesEventType.APPLICATION_INIT, "hadoop", null, "107", ByteBuffer.allocate(10));
    assertEquals(evt.getUser(), "hadoop");
  }

  @Test
  public void testGetAppId() {
    ApplicationId appId = ApplicationId.newInstance(0, 66);
    AuxServicesAppEvent evt = 
      new AuxServicesAppEvent(AuxServicesEventType.APPLICATION_INIT, "hadoop", appId, "107", ByteBuffer.allocate(10));
    assertEquals(evt.getApplicationID().getId() , 66);
  }

}