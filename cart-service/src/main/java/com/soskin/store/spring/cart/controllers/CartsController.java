package com.soskin.store.spring.cart.controllers;


import com.soskin.store.spring.cart.converters.CartConverter;
import com.soskin.store.spring.cart.services.CartService;
import com.soskin.store.springstore.api.carts.CartDto;
import com.soskin.store.springstore.api.dto.StringResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
@Tag(name = "Carts", description = "methods for work with carts")
public class CartsController {
    private final CartService cartService;
    private final CartConverter cartConverter;

    @GetMapping("/{uuid}")
    @Operation(
            summary = "Request to get a cart by uuid",
            responses = {
                    @ApiResponse(
                            description = "Success response", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = CartDto.class))
                    )
            }
    )
    public CartDto getCart(@RequestHeader(required = false) @Parameter(description = "Username", required = true) String username, @PathVariable @Parameter(description = "uuid", required = true) String uuid) {
        return cartConverter.modelToDto(cartService.getCurrentCart(getCurrentCartUuid(username, uuid)));
    }


    @GetMapping("/generate")
    @Operation(
            summary = "Request to generate uuid ",
            responses = {
                    @ApiResponse(
                            description = "Success response", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = StringResponse.class))
                    )
            }
    )
    public StringResponse getCart() {
        return new StringResponse(cartService.generateCartUuid());
    }


    @GetMapping("/{uuid}/add/{productId}")
    @Operation(
            summary = "Request to add a product to the c cart",
            responses = {
                    @ApiResponse(
                            description = "Success response", responseCode = "200"
                    )
            }
    )
    public void add(@RequestHeader(required = false) @Parameter(description = "Username", required = true) String username, @PathVariable @Parameter(description = "uuid", required = true) String uuid, @PathVariable @Parameter(description = "Product ID", required = true) Long productId) {
        cartService.addToCart(getCurrentCartUuid(username, uuid), productId);
    }

    @GetMapping("/{uuid}/decrement/{productId}")
    @Operation(
            summary = "Request to decrement quantity of orderItemDtos in order",
            responses = {
                    @ApiResponse(
                            description = "Success response", responseCode = "200"
                    )
            }
    )
    public void decrement(@RequestHeader(required = false) String username, @PathVariable String uuid, @PathVariable @Parameter(description = "Product ID", required = true) Long productId) {
        cartService.decrementItem(getCurrentCartUuid(username, uuid), productId);
    }

    @GetMapping("/{uuid}/remove/{productId}")
    @Operation(
            summary = "Request to remove orderItemDto from cart",
            responses = {
                    @ApiResponse(
                            description = "Success response", responseCode = "200"
                    )
            }
    )
    public void remove(@RequestHeader(required = false) String username, @PathVariable String uuid, @PathVariable @Parameter(description = "Product ID", required = true) Long productId) {
        cartService.removeItemFromCart(getCurrentCartUuid(username, uuid), productId);
    }

    @GetMapping("/{uuid}/clear")
    @Operation(
            summary = "Request to clear the cart",
            responses = {
                    @ApiResponse(
                            description = "Success response", responseCode = "200"
                    )
            }
    )
    public void clear(@RequestHeader(required = false) String username, @PathVariable @Parameter(description = "Cart's uuid", required = true) String uuid) {
        cartService.clearCart(getCurrentCartUuid(username, uuid));
    }

    @GetMapping("/{uuid}/merge")
    @Operation(
            summary = "Request to merge the carts",
            responses = {
                    @ApiResponse(
                            description = "Success response", responseCode = "200"
                    )
            }
    )
    public void merge(@RequestHeader(required = false) String username, @PathVariable @Parameter(description = "Cart's uuid", required = true) String uuid) {
        cartService.merge(
                getCurrentCartUuid(username, null),
                getCurrentCartUuid(null, uuid)
        );
    }

    private String getCurrentCartUuid(String username, String uuid) {
        if (username != null) {
            return cartService.getCartUuidFromSuffix(username);
        }
        return cartService.getCartUuidFromSuffix(uuid);
    }
}
