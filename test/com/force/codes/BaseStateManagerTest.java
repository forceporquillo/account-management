package com.force.codes;

import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedHashMap;

public class BaseStateManagerTest extends BaseTestCase {

    private static final LinkedHashMap<String, Information> map = new LinkedHashMap<>();

    Information information = createBuilderInstance();

    @Test
    public void testContainsInfo() {
        String keyId = information.getStudentId();

        map.put(keyId, information);

        if (map.containsKey(keyId)) {
            final Information info = map.get(keyId);

            if (!information.equals(info)) {
                map.put(info.getStudentId(), information);
            }
        }
        Assert.assertTrue(map.containsValue(information));
        Assert.assertTrue(map.containsKey(keyId));
    }

    @Test
    public void testExist() {

        information.setStudentId("1234567890");

        map.put(information.getStudentId(), information);

        Assert.assertTrue(map.containsKey("1234567890"));
    }
}