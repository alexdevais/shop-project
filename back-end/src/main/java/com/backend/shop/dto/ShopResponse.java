package com.backend.shop.dto;

import com.backend.shop.enums.UserRole;

public record ShopResponse(
        String name,
        String city,
        String address,
        Integer postalCode,
        UserRole userRole
) {}
