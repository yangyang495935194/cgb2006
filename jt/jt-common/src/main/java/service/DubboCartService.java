package service;

import com.jt.pojo.Cart;

import java.util.List;

public interface DubboCartService {
    List<Cart> findCartListByUserId(Long userId);

    int updateCartNum(Cart cart);

    void saveCart(Cart cart);


    void deleteCart(Long userId, Long itemId);
}
