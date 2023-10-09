package com.example.DoAn.server;

import com.example.DoAn.dto.CartItemDto;
import com.example.DoAn.dto.ProductDTO;
import com.example.DoAn.dto.ShoppingCartDto;
import com.example.DoAn.entity.CartItem;
import com.example.DoAn.entity.Product;
import com.example.DoAn.entity.ShoppingCart;
import com.example.DoAn.entity.User;
import com.example.DoAn.repository.CartItemRepository;
import com.example.DoAn.repository.ShoppingCartRepository;
import com.example.DoAn.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class ShoppingCartService {
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private UserRepository userRepository;
    public ShoppingCart addItemToCart(ProductDTO productDTO,int quantity,String username){
        User user=userRepository.findByEmail(username);
        ShoppingCart shoppingCart=user.getCart();
        if(shoppingCart==null){
            shoppingCart=new ShoppingCart();
        }
        Set<CartItem> cartItemList=shoppingCart.getCartItems();
        CartItem cartItem =find(cartItemList,productDTO.getId());
        Product product=transfer(productDTO);
        double unitPrice=productDTO.getSalePrice();
        int itemQuantity=0;
        if(cartItemList==null){
            cartItemList=new HashSet<>();
            if(cartItem==null){
                cartItem=new CartItem();
                cartItem.setProduct(product);
                cartItem.setQuantity(quantity);
                cartItem.setUnitPrice(quantity*product.getSalePrice());
                cartItem.setCart(shoppingCart);
                cartItemList.add(cartItem);
                cartItemRepository.save(cartItem);
            }else{
                itemQuantity=cartItem.getQuantity()+quantity;
                cartItem.setQuantity(itemQuantity);
                cartItemRepository.save(cartItem);
            }
        } else {
            if (cartItem == null) {
                cartItem = new CartItem();
                cartItem.setProduct(product);
                cartItem.setQuantity(quantity);
                cartItem.setUnitPrice(quantity*product.getSalePrice());
                cartItem.setCart(shoppingCart);
                cartItemList.add(cartItem);
                cartItemRepository.save(cartItem);
            } else {
                cartItem.setQuantity(cartItem.getQuantity()+quantity);
                cartItem.setUnitPrice(cartItem.getUnitPrice()+(quantity*product.getSalePrice()));
                cartItemRepository.save(cartItem);
            }
        }
        shoppingCart.setCartItems(cartItemList);
        double totalPrice=totalPrice(shoppingCart.getCartItems());
        int totalItem=totalItem(shoppingCart.getCartItems());

        shoppingCart.setTotalPrice(totalPrice);
        shoppingCart.setTotalItems(totalItem);
        shoppingCart.setUser(user);

        return shoppingCartRepository.save(shoppingCart);
    }
    public ShoppingCart updateItemCart(ProductDTO productDTO,int quantity,String username){
        User user= userRepository.getUserByEmail(username);
        ShoppingCart shoppingCart=user.getCart();
        Set<CartItem> cartItemList=shoppingCart.getCartItems();
        CartItem item=find(cartItemList, productDTO.getId());
        int itemQuantity=quantity;
        item.setQuantity(itemQuantity);
        cartItemRepository.save(item);
        shoppingCart.setCartItems(cartItemList);
        int totalItem=totalItem(cartItemList);
        double totalPrice=totalPrice(cartItemList);
        shoppingCart.setTotalItems(totalItem);
        shoppingCart.setTotalPrice(totalPrice);
        return shoppingCartRepository.save(shoppingCart);
    }
    public ShoppingCart deleteItemFromCart(Product product, User user){
        ShoppingCart cart=user.getCart();
        Set<CartItem> cartItems=cart.getCartItems();
        CartItem item =find(cartItems, product.getId());
        cartItems.remove(item);
        cartItemRepository.delete(item);
        double totalPrice=totalPrice(cartItems);
        int totalItems=totalItem(cartItems);
        cart.setCartItems(cartItems);
        cart.setTotalItems(totalItems);
        cart.setTotalPrice(totalPrice);
        return shoppingCartRepository.save(cart);
    }
    private CartItem find(Set<CartItem> cartItems, Long productId){
        if(cartItems==null){
            return null;
        }
        CartItem cartItem=null;
        for(CartItem item: cartItems){
            if(item.getProduct().getId()==productId){
                cartItem=item;
            }
        }
        return cartItem;
    }
    private CartItemDto findInDto(ShoppingCartDto shoppingCartDto,long productId){
        if(shoppingCartDto==null){
            return null;
        }
        CartItemDto cartItem=null;
        for(CartItemDto item: shoppingCartDto.getCartItem()){
            if(item.getProduct().getId()==productId){
                cartItem=item;
            }
        }
        return cartItem;
    }
    private int totalItem(Set<CartItem> cartItemList){
        int totalItem=0;
        for(CartItem item: cartItemList){
            totalItem+=item.getQuantity();
        }
        return totalItem;
    }
    private double totalPrice(Set<CartItem> cartItemList){
        double totalPrice=0;
        for(CartItem item:cartItemList){
            totalPrice+=item.getUnitPrice()*item.getQuantity();
        }
        return totalPrice;
    }
    private Product transfer(ProductDTO productDTO){
        Product product=new Product();
        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setSalePrice(productDTO.getSalePrice());
        product.setPrice(productDTO.getPrice());
        product.setImageName(productDTO.getImageName());
        return product;
    }


}
