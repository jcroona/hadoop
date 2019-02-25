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
import org.apache.hadoop.yarn.server.nodemanager.containermanager.AuxServicesContainerEvent;
import org.apache.hadoop.yarn.server.nodemanager.containermanager.AuxServicesEventType;

import org.apache.hadoop.yarn.server.nodemanager.containermanager.container.*;

import org.apache.hadoop.yarn.api.records.ApplicationAttemptId;
import org.apache.hadoop.yarn.api.records.ApplicationId;
import org.apache.hadoop.yarn.api.records.ContainerId;
import org.apache.hadoop.yarn.api.records.Priority;
import org.apache.hadoop.yarn.api.records.Resource;
import org.apache.hadoop.yarn.conf.YarnConfiguration;
import org.apache.hadoop.yarn.server.nodemanager.Context;
import org.apache.hadoop.yarn.security.ContainerTokenIdentifier;
import static org.mockito.Mockito.mock;


public class TestAuxServicesContainerEvent {
  @Test
  public void testGetContainer() {
    ApplicationId appId1 = ApplicationId.newInstance(0, 65);
    ApplicationAttemptId attemptId = ApplicationAttemptId.newInstance(appId1, 1);
    ContainerTokenIdentifier cti = new ContainerTokenIdentifier(
        ContainerId.newContainerId(attemptId, 1), "", "",
        Resource.newInstance(1, 1), 0,0,0, Priority.newInstance(0), 0);
    Context context = mock(Context.class);
    Container container = new ContainerImpl(new YarnConfiguration(), null, null, null, null, cti, context);
    AuxServicesContainerEvent evt = 
      new AuxServicesContainerEvent(AuxServicesEventType.CONTAINER_INIT, container);
    assertEquals(evt.getContainer(), container);
  }

  @Test
  public void testGetAppId() {
    ApplicationId appId1 = ApplicationId.newInstance(0, 65);
    ApplicationAttemptId attemptId = ApplicationAttemptId.newInstance(appId1, 1);
    ContainerTokenIdentifier cti = new ContainerTokenIdentifier(
        ContainerId.newContainerId(attemptId, 1), "", "",
        Resource.newInstance(1, 1), 0,0,0, Priority.newInstance(0), 0);
        Context context = mock(Context.class);
    Container container = new ContainerImpl(new YarnConfiguration(), null, null, null, null, cti, context);
    AuxServicesContainerEvent evt = 
      new AuxServicesContainerEvent(AuxServicesEventType.CONTAINER_INIT, container);
    assertEquals(evt.getApplicationID().getId(), container.getContainerId().getApplicationAttemptId().getApplicationId().getId());
  }

  

}