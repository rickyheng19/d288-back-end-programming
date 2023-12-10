package com.example.demo.services;

import com.example.demo.dao.CartRepository;
import com.example.demo.dao.CustomerRepository;
import com.example.demo.entities.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

import static com.example.demo.entities.StatusType.*;

@Service
public class CheckoutServiceImpl implements CheckoutService{

    private CustomerRepository customerRepository;
    private CartRepository cartRepository;


    public CheckoutServiceImpl(CustomerRepository customerRepository, CartRepository cartRepository) {
        this.customerRepository = customerRepository;
        this.cartRepository = cartRepository;

    }

    @Override
    @Transactional
    public PurchaseResponse placeOrder(Purchase purchase) {
        try {
            // Retrieve the order info
            Cart cart = purchase.getCart();

            // Generate tracking number
            String orderTrackingNumber = generateOrderTrackingNumber();
            cart.setOrderTrackingNumber(orderTrackingNumber);

            // Populate cart with cart items
            Set<CartItem> cartItems = purchase.getCartItems();
            cartItems.forEach(item -> item.setCart(cart));
            cart.setCartitem(cartItems);


            // Set the customer for the cart
            Customer customer = purchase.getCustomer();
            cart.setCustomer(customer);


            //Set status type
            cart.setStatus(ordered);

            // Handle null customer or empty cart items
            if (customer == null || cartItems.isEmpty()) {
                throw new IllegalArgumentException("Customer cannot be null and cart items cannot be empty.");
            }

            // Save customer and cart information to the database
            customerRepository.save(customer);
            cartRepository.save(cart);

            // Return a response
            return new PurchaseResponse(orderTrackingNumber);
         } catch (Exception e) {
            // Handle any exceptions and provide an error response
            return new PurchaseResponse("Error: " + e.getMessage());
        }
    }

    private String generateOrderTrackingNumber() {
        // Generates a universally unique identifier
        return UUID.randomUUID().toString();
    }
}