package com.soskin.store.springstore.core.controllers;


import com.soskin.store.springstore.api.core.OrderDetailsDto;
import com.soskin.store.springstore.api.core.OrderDto;
import com.soskin.store.springstore.api.core.ProductDto;
import com.soskin.store.springstore.core.converters.OrderConverter;
import com.soskin.store.springstore.core.services.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
@Tag(name = "Orders", description = "methods for work with orders")
//@CrossOrigin("*")
public class OrdersController {
    private final OrderService orderService;
    private final OrderConverter orderConverter;


    @Operation(
            summary = "Request to create an order",
            responses = {
                    @ApiResponse(
                            description = "Success response", responseCode = "201"
                    ),

            }
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(@RequestHeader String username, @RequestBody OrderDetailsDto orderDetailsDto) {
        orderService.createOrder(username, orderDetailsDto);
    }

    @Operation(
            summary = "Request to get a current user's order",
            responses = {
                    @ApiResponse(
                            description = "Success response", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = OrderDto.class))

                    ),

            }
    )
    @GetMapping
    public List<OrderDto> getCurrentUserOrders(@RequestHeader @Parameter(description = "Username", required = true) String username) {
        return orderService.findOrdersByUsername(username).stream()
                .map(orderConverter::entityToDto).collect(Collectors.toList());
    }
}
