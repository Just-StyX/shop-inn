package com.jsl.shop_inn.common.util;

import java.util.List;

public record PurchaseRequest(
        String state,
        String city,
        String street,
        String zipcode,

        String shipping_state,
        String shipping_city,
        String shipping_street,
        String shipping_zipcode,

        List<String> furnitureIds
) {
}
