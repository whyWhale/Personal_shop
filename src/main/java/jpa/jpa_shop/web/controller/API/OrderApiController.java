package jpa.jpa_shop.web.controller.API;

import jpa.jpa_shop.domain.orders.Order;
import jpa.jpa_shop.domain.orders.Repository.OrderRepository;
import jpa.jpa_shop.service.IFS.OrderServiceIFS;
import jpa.jpa_shop.web.controller.dto.request.order.OrderSearchRequestDto;
import jpa.jpa_shop.web.controller.dto.response.order.OrderDetailResponseDto;
import jpa.jpa_shop.web.controller.dto.response.order.OrderResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RequestMapping("/api/orders")
@RestController
public class OrderApiController {
    private final OrderServiceIFS orderService;
    private final OrderRepository orderRepository;

    // 주문 검색
    @GetMapping("")
    public List<OrderResponseDto> orderList() {
        return orderService.SearchMemberNameAndOrderStatus(new OrderSearchRequestDto()).stream().map(Order::toDto).collect(Collectors.toList());
    }

    // === 주문 조회 ===

        // 페이징 처리 불가. 모든 Domain fetch (result : 쿼리1)
    @GetMapping("/v1/notUsing")
    public List<OrderResponseDto> notUsing() {
        return orderRepository.findAllWithItem().stream().map(Order::toDto).collect(Collectors.toList());
    }
        // 페이징 처리 , 쿼리 최적화
    @GetMapping("/list")
    public List<OrderResponseDto> orderList_Enhancement() {
        return orderService.findWithMemberAndDelivery().stream().map(Order::toDto).collect(Collectors.toList());
    }

    // === 주문 상세 조회 ===
    @GetMapping("/v1/detail/notUsing")
    public List<OrderDetailResponseDto> detailData() {
        return orderService.findWithMemberAndDelivery().stream().map(OrderDetailResponseDto::new).collect(Collectors.toList());
    }

    // 페이징 처리 (result : 쿼리3)
    @GetMapping("/detail")
    public List<OrderDetailResponseDto> v2(@RequestParam(value = "offset",defaultValue = "0" ,required = false) int offset,
                                     @RequestParam(value = "limit",defaultValue = "100", required = false) int limit) {
        return orderService.findWithMemberAndDelivery(offset,limit).stream().map(OrderDetailResponseDto::new).collect(Collectors.toList());
    }




}
