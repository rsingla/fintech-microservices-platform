
package com.target.api;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "request_attributes",
    "items"
})
public class ProductCompositeResponse {

    @JsonProperty("request_attributes")
    private List<RequestAttribute> requestAttributes = new ArrayList<RequestAttribute>();
    @JsonProperty("items")
    private List<Item> items = new ArrayList<Item>();

    /**
     * 
     * @return
     *     The requestAttributes
     */
    @JsonProperty("request_attributes")
    public List<RequestAttribute> getRequestAttributes() {
        return requestAttributes;
    }

    /**
     * 
     * @param requestAttributes
     *     The request_attributes
     */
    @JsonProperty("request_attributes")
    public void setRequestAttributes(List<RequestAttribute> requestAttributes) {
        this.requestAttributes = requestAttributes;
    }

    /**
     * 
     * @return
     *     The items
     */
    @JsonProperty("items")
    public List<Item> getItems() {
        return items;
    }

    /**
     * 
     * @param items
     *     The items
     */
    @JsonProperty("items")
    public void setItems(List<Item> items) {
        this.items = items;
    }

}
