package com.lesson2.less;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private int[] orderIds = new int[5];

    @Autowired
    private OrderDAO orderDAO;

    public Order save(Order order) {
        return null;
    }

    public void test(int index, int id) {
        orderIds[index] = id;
    }
}
