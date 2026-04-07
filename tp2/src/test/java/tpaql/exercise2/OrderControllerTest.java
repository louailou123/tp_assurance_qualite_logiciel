package tpaql.exercise2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class OrderControllerTest {

    private OrderDao orderDao;
    private OrderService orderService;
    private OrderController orderController;

    @BeforeEach
    void setUp() {
        orderDao = mock(OrderDao.class);
        orderService = spy(new OrderService(orderDao));
        orderController = new OrderController(orderService);
    }

    @Test
    void shouldCallOrderServiceAndOrderDaoWhenCreatingOrder() {
        Order order = new Order(101L, "Laptop", 2);

        orderController.createOrder(order);

        verify(orderService, times(1)).createOrder(order);
        verify(orderDao, times(1)).saveOrder(order);
    }
}