package com.soskin.store.springstore.core;

import com.soskin.store.springstore.core.entities.Product;
import com.soskin.store.springstore.core.repositories.ProductsRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class ControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ProductsRepository productsRepository;

    @Test
    public void test() throws Exception {

        Product product = new Product(1L, "Milk", BigDecimal.valueOf(200));
        List<Product> products = new ArrayList<>(List.of(product));

        given(productsRepository.findAll()).willReturn(products);

        mvc.perform(get("http://localhost:8189/web-market-core/api/v1/products")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].title", is(products.get(0).getTitle())));
    }
}
