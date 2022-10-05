package com.project.controller.API_TestInvit;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InvitationsResponse {
    private String message;
    private HashMap<String, Long> response;
}
