package com.soskin.store.springstore.recommendations.controllers;


import com.soskin.store.springstore.api.core.OrderItemDto;
import com.soskin.store.springstore.recommendations.integrations.CoreServiceIntegration;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/recommendations")
@RequiredArgsConstructor
@CrossOrigin("*")  //в какой-то момент перестало работать без этой аннотации, хотя некоторое время работало без нее...
public class RecommendationsController {

    private final CoreServiceIntegration coreServiceIntegration;


    @GetMapping
    public List<OrderItemDto> getRecommendation() {
        return coreServiceIntegration.getRecommendationItems();
    }

}
