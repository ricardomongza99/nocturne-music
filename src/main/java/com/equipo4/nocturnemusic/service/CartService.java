package com.equipo4.nocturnemusic.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

import com.equipo4.nocturnemusic.model.*;

@Service
public class CartService {
	private ShoppingCart cart;
	
	public void newCart(User user) {
		this.cart = new ShoppingCart();
		this.cart.setUser(user);
	}
	
	public void addToCart(Product item) {
		this.cart.addItem(item);
	}
	
	public void removeFromCart(Product item) {
		int qty = this.cart.findItem(item);
		if (qty > 0) {
			this.cart.updateItem(item, qty - 1);
		}
	}
	
	public BigDecimal grandTotal() {
		return this.cart.getTotalCost();
	}
	
	public List<Element> ticket() {
		return this.transform(this.cart);
	}
	
	private List<Element> transform(ShoppingCart cart) {
		List<Element> ticket = new ArrayList<>();
		
		Iterator<Map.Entry<Product, Integer>> iterator = cart.getItems().entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<Product, Integer> entry = iterator.next();
            Product i = entry.getKey();
            Integer qty = entry.getValue();
            Element j = new Element(i.getName(), qty, i.getPrice());

            ticket.add(j);
        }
		
		return ticket;
	}
}

class Element {
	public final String name;
	public final int amount;
	public final BigDecimal price;
	public final BigDecimal uprice;
	
	public Element(String name, int amount, BigDecimal unitprice) {
		this.name = name;
		this.amount = amount;
		this.uprice = unitprice;
		this.price = this.uprice.multiply(BigDecimal.valueOf(amount));
	}
}