package com.axcelerate.assessment.bean;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class HomeHubBean {
    private final Map<Integer,String> productMap = new HashMap<>();
    private final Map<String,String> stateMap = new HashMap<>();
    private final Deque<Map<String,String>> undoList = new LinkedList<>();

    public HomeHubBean() {
        productMap.put(1, "GARAGE_DOOR");
        productMap.put(2, "DISHWASHER");
        productMap.put(3, "LIVING_ROOM_LIGHTS");
        productMap.values().forEach(value -> stateMap.put(value, "OFF"));
    }

    public Map<Integer, String> getProductMap() {
        return productMap;
    }

    public Map<String, String> getStateMap() {
        return stateMap;
    }

    public void addToUndoList(Map<String,String> lastState) {
        undoList.addFirst(lastState);
    }

    public Map<String,String> removeFromUndoList() {
        return undoList.remove();
    }

    public Deque<Map<String,String>> getUndoList() {
        return undoList;
    }
}
