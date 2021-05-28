package jpa.jpa_shop.web.controller.API;

import jpa.jpa_shop.domain.orders.Order;
import jpa.jpa_shop.domain.orders.Repository.OrderRepository;
import jpa.jpa_shop.service.IFS.OrderServiceIFS;
import jpa.jpa_shop.web.controller.dto.request.order.OrderSearchRequestDto;
import jpa.jpa_shop.web.controller.dto.response.order.OrderResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RequestMapping("/api/orders")
@RestController
public class OrderApiController {
    private final OrderServiceIFS orderService;
    @GetMapping("")
    public List<OrderResponseDto> orderList()
    {
        return  orderService.SearchMemberNameAndOrderStatus(new OrderSearchRequestDto()).stream()
                .map(Order::toDto).collect(Collectors.toList());
    }


    @GetMapping("/list")
    public List<OrderResponseDto> orderList_Enhancement()
    {
        return orderService.findWithMemberAndDelivery().stream().map(Order::toDto).collect(Collectors.toList());
    }
}
