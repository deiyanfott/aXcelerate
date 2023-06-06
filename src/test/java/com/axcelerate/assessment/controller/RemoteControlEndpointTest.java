package com.axcelerate.assessment.controller;

import com.axcelerate.assessment.bean.HomeHubBean;
import jakarta.servlet.ServletException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = RemoteControlEndpoint.class)
class RemoteControlEndpointTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HomeHubBean homeHubBean;

    @Test
    void whenTurnOnRemoteControlWithValidProduct_thenReturn200() throws Exception {
        given(homeHubBean.getProductMap()).willReturn(new HashMap<>(Map.of(1, "GARAGE_DOOR", 2, "DISHWASHER", 3, "LIVING_ROOM_LIGHTS")));
        given(homeHubBean.getStateMap()).willReturn(new HashMap<>(Map.of("GARAGE_DOOR", "OFF", "DISHWASHER", "ON")));

        mockMvc.perform(put("/api/v1/remote-control/turn-on/{productId}", 1))
                .andExpect(status().isOk());
        assertEquals("ON", homeHubBean.getStateMap().get("GARAGE_DOOR"));
    }

    @Test
    void whenTurnOffRemoteControlWithValidProduct_thenReturn200() throws Exception {
        given(homeHubBean.getProductMap()).willReturn(new HashMap<>(Map.of(1, "GARAGE_DOOR", 2, "DISHWASHER", 3, "LIVING_ROOM_LIGHTS")));
        given(homeHubBean.getStateMap()).willReturn(new HashMap<>(Map.of("GARAGE_DOOR", "OFF", "DISHWASHER", "ON")));

        mockMvc.perform(put("/api/v1/remote-control/turn-off/{productId}", 2))
                .andExpect(status().isOk());
        assertEquals("OFF", homeHubBean.getStateMap().get("DISHWASHER"));
    }

    @Test
    void whenUndoListHasActions_thenReturn200AndListIsEmptied() throws Exception {
        given(homeHubBean.getUndoList()).willReturn(new LinkedList<>());
        mockMvc.perform(delete("/api/v1/remote-control/undo"));
        assertEquals(0, homeHubBean.getUndoList().size());
    }

    @Test()
    void whenTurnOnRemoteControlWithInvalidProduct_thenThrowException() {
        assertThrows(ServletException.class, () ->
                mockMvc.perform(put("/api/v1/remote-control/turn-on/{productId}", 0)));
    }
}
