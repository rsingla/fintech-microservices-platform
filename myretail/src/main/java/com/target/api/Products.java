
package com.target.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "product_composite_response"
})
public class Products {

    @JsonProperty("product_composite_response")
    private ProductCompositeResponse productCompositeResponse;

    /**
     * 
     * @return
     *     The productCompositeResponse
     */
    @JsonProperty("product_composite_response")
    public ProductCompositeResponse getProductCompositeResponse() {
        return productCompositeResponse;
    }

    /**
     * 
     * @param productCompositeResponse
     *     The product_composite_response
     */
    @JsonProperty("product_composite_response")
    public void setProductCompositeResponse(ProductCompositeResponse productCompositeResponse) {
        this.productCompositeResponse = productCompositeResponse;
    }

}
