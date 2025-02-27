package com.backend.shop.service;

import com.backend.shop.dto.ShopRequest;
import com.backend.shop.dto.ShopResponse;
import com.backend.shop.entity.Shop;
import com.backend.shop.entity.User;
import com.backend.shop.enums.UserRole;
import com.backend.shop.repository.ShopRepository;
import com.backend.shop.repository.UserRepository;
import com.backend.shop.security.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ShopService {

    private final ShopRepository shopRepository;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @Autowired
    public ShopService(ShopRepository shopRepository, UserRepository userRepository, JwtUtil jwtUtil) {
        this.shopRepository = shopRepository;
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    public ShopResponse createShop(ShopRequest shopRequest, HttpServletRequest request) {
        try {
            String token = request.getHeader("Authorization").substring(7);
            String email = jwtUtil.extractUsername(token);

            User owner = userRepository.findByEmail(email)
                    .orElseThrow(() -> new RuntimeException("User not found"));
            owner.setUserRole(UserRole.OWNER);

            Shop shop = new Shop();
            shop.setName(shopRequest.name());
            shop.setCity(shopRequest.city());
            shop.setAddress(shopRequest.address());
            shop.setPostalCode(shopRequest.postalCode());
            shop.setOwner(owner);

            Shop savedShop = shopRepository.save(shop);

            return new ShopResponse(
                    savedShop.getName(),
                    savedShop.getCity(),
                    savedShop.getAddress(),
                    savedShop.getPostalCode(),
                    savedShop.getOwner().getUserRole()
            );
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while creating the shop: " + e.getMessage());
        }
    }
}
