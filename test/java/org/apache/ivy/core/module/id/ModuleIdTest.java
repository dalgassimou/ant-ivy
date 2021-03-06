/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */
package org.apache.ivy.core.module.id;

import org.junit.Test;

import static org.junit.Assert.*;

public class ModuleIdTest {

    @Test
    public void testModuleId() {
        String org = "apache";
        String name = "some-new-module";
        ModuleId moduleId = new ModuleId(org, name);

        assertEquals(org, moduleId.getOrganisation());
        assertEquals(name, moduleId.getName());
    }

    @Test
    public void testModuleIdIllegalArgumentException1() {
        String name = "some-new-module";

        try {
            new ModuleId(null, name);
        } catch (IllegalArgumentException iae) {
            fail("A null should be allowed for argument 'org'.");
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testModuleIdIllegalArgumentException2() {
        String org = "apache";
        new ModuleId(org, null);
    }

    @Test
    public void testEqualsObjectTrue() {
        String org = "apache";
        String name = "some-new-module";
        ModuleId moduleId = new ModuleId(org, name);
        ModuleId moduleId2 = new ModuleId(org, name);

        assertTrue(moduleId.equals(moduleId));
        assertTrue(moduleId.equals(moduleId2));
        assertTrue(moduleId2.equals(moduleId));
    }

    @Test
    public void testEqualsObjectFalse() {
        String org = "apache";
        String name = "some-new-module";
        ModuleId moduleId = new ModuleId(org, name);
        ModuleId moduleId2 = new ModuleId(null, name);

        assertNotNull(moduleId);
        assertFalse(moduleId.equals(null));
        assertFalse(moduleId.equals(moduleId2));
        assertFalse(moduleId2.equals(moduleId));
    }

    @Test
    public void testEncodeToString() {
        String org = "apache";
        String name = "some-new-module";
        ModuleId moduleId = new ModuleId(org, name);

        assertEquals(org + ModuleId.ENCODE_SEPARATOR + name, moduleId.encodeToString());
    }

    @Test
    public void testDecode() {
        String org = "apache";
        String name = "some-new-module";
        ModuleId moduleId = new ModuleId(org, name);

        ModuleId moduleId2 = ModuleId.decode(moduleId.encodeToString());
        assertEquals(moduleId, moduleId2);
    }

    @Test(expected = NullPointerException.class)
    public void testCompareToNullObject() {
        String org = "apache";
        String name = "some-new-module";
        ModuleId moduleId = new ModuleId(org, name);
        moduleId.compareTo(null);
    }

    @Test
    public void testCompareToEqual() {
        String org = "apache";
        String name = "some-new-module";
        ModuleId moduleId = new ModuleId(org, name);

        assertTrue(moduleId.compareTo(new ModuleId(org, name)) == 0);
    }

    @Test
    public void testCompareToLessThan() {
        String org = "apache";
        String name = "some-new-module";
        ModuleId moduleId = new ModuleId(org, name);
        String name2 = "the-new-module";
        ModuleId moduleId2 = new ModuleId(org, name2);
        System.out.println(moduleId + "\n" + moduleId2);

        assertTrue(moduleId.compareTo(moduleId2) < 0);
    }

    @Test
    public void testCompareToGreaterThan() {
        String org = "apache";
        String name = "some-new-module";
        ModuleId moduleId = new ModuleId(org, name);
        String name2 = "the-new-module";
        ModuleId moduleId2 = new ModuleId(org, name2);
        System.out.println(moduleId + "\n" + moduleId2);

        assertTrue(moduleId2.compareTo(moduleId) > 0);
    }
}
