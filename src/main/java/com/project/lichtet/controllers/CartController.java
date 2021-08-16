package com.project.lichtet.controllers;

import com.project.lichtet.models.Cart;
import com.project.lichtet.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

@Controller
@RequestMapping(value = "gio-hang")
public class CartController {

    @Autowired
    CartService cartService;

    @RequestMapping(value = "add")
    public String addToCart(HttpSession session, String id, int quantity, boolean muaNgay, String path) throws InterruptedException, ExecutionException {
        if (session.getAttribute("success") != null)
            session.removeAttribute("success");

        HashMap<String, Cart> cartItems = (HashMap<String, Cart>) session.getAttribute("cart");

        if (cartItems == null)
            cartItems = new HashMap<>();

        cartItems = cartService.addToCart(cartItems, id, quantity);
        session.setAttribute("cart", cartItems);
        session.setAttribute("total", cartService.totalPrice(cartItems));
        session.setAttribute("cartNum", cartItems.size());
        if (muaNgay)
            return "redirect:/gio-hang/thong-tin";
        else
            return "redirect:/" + path;
    }

    @RequestMapping(value = "thong-tin")
    public String showCart() {
        return "cart";
    }

    @RequestMapping(value = "xoa/{id}")
    public String removeCart(HttpSession session, @PathVariable String id) throws InterruptedException, ExecutionException {
        HashMap<String, Cart> cartItems = (HashMap<String, Cart>) session.getAttribute("cart");

        if (cartItems != null) {
            cartItems = cartService.removeCart(cartItems, id);
        }

        session.setAttribute("cart", cartItems);
        session.setAttribute("total", cartService.totalPrice(cartItems));
        session.setAttribute("cartNum", cartItems.size());

        return "redirect:/gio-hang/thong-tin";
    }

    @RequestMapping(value = "{lichId}/{update}")
    public String updateCart(HttpSession session, @PathVariable String lichId, @PathVariable String update, @RequestParam(required = false) String quantity) throws InterruptedException, ExecutionException {
        HashMap<String, Cart> cartItems = (HashMap<String, Cart>) session.getAttribute("cart");

        if (update.equals("minus") && cartItems.get(lichId).getQuantity() > 1) {
            cartItems = cartService.updateCart(cartItems, lichId, cartItems.get(lichId).getQuantity() - 1);
        } else if (update.equals("plus")) {
            cartItems = cartService.updateCart(cartItems, lichId, cartItems.get(lichId).getQuantity() + 1);
        } else {
            cartItems = cartService.updateCart(cartItems, lichId, Integer.parseInt(quantity));
        }

        if (cartItems.get(lichId).getQuantity() == 1) {
            session.setAttribute("disableBtnMinus", true);
        } else if (session.getAttribute("disableBtnMinus") != null) {
            session.removeAttribute("disableBtnMinus");
        }

        session.setAttribute("cart", cartItems);
        session.setAttribute("total", cartService.totalPrice(cartItems));

        return "redirect:/gio-hang/thong-tin";
    }
}
