package com.project.lichtet.services;

import com.project.lichtet.models.Cart;
import com.project.lichtet.models.LichDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Service
public class CartService {

    @Autowired
    LichService lichService;

    public HashMap<String, Cart> addToCart(HashMap<String, Cart> cartItems, String id, int quantity) throws InterruptedException, ExecutionException {
        if(id != null && quantity != 0){

            LichDto lichDto = lichService.getLichById(id);

            if (lichDto != null) {
                if (cartItems.containsKey(id)) {
                    Cart cart = cartItems.get(id);
                    cart.setLich(lichDto);
                    cart.setQuantity(cart.getQuantity() + quantity);
                    cartItems.put(id, cart);
                } else {
                    Cart cart = new Cart();
                    cart.setLich(lichDto);
                    cart.setQuantity(quantity);
                    cartItems.put(id, cart);
                }
            }
        }

        return cartItems;
    }

    public HashMap<String, Cart> removeCart(HashMap<String, Cart> cartItems, String id) throws InterruptedException, ExecutionException {

        if(cartItems.containsKey(id)) {
            cartItems.remove(id);
        }

        return cartItems;
    }

    public int convertToNumber(String gia) {
        return Integer.parseInt(gia.replace(",", "").replace("đ", ""));
    }

    public String totalPrice(HashMap<String, Cart> cartItems) {

        int count = 0;

        for(Map.Entry<String, Cart> list : cartItems.entrySet()) {
            count += convertToNumber(list.getValue().getLich().getGia()) * list.getValue().getQuantity();
        }

        DecimalFormat formatter = new DecimalFormat("###,###,###");

        return formatter.format(count) + "đ";

    }

    public HashMap<String, Cart> updateCart(HashMap<String, Cart> cartItems, String id, int quantity) throws InterruptedException, ExecutionException {

        cartItems.get(id).setQuantity(quantity);

        return cartItems;
    }
}
