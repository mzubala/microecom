package pl.com.bottega.microecom.orders.ui;

import org.springframework.web.bind.annotation.*;
import pl.com.bottega.microecom.orders.api.OrderProcess;

import javax.validation.Valid;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private OrderProcess orderProcess;

    public OrderController(OrderProcess orderProcess) {
        this.orderProcess = orderProcess;
    }

    @PostMapping
    public CreateOrderResponse create() {
        Long orderId = orderProcess.create(currentUserId());
        return new CreateOrderResponse(orderId);
    }

    private Long currentUserId() {
        return 1L;
    }

    @PostMapping("/{orderId}/products")
    public void addProduct(@PathVariable Long orderId, @Valid @RequestBody AddProductRequest addProductRequest) {
        orderProcess.addProduct(
                orderId,
                addProductRequest.getProductId(),
                addProductRequest.getQuantity()
        );
    }

    @PutMapping("/{orderId}/placement")
    public void place(@PathVariable Long orderId) {
        orderProcess.place(orderId);
    }


}
