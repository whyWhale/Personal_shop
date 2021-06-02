package jpa.jpa_shop.web.controller.API;

import jpa.jpa_shop.Query.OrderQueryRepository;
import jpa.jpa_shop.domain.orders.Order;
import jpa.jpa_shop.service.IFS.OrderServiceIFS;
import jpa.jpa_shop.web.dto.request.order.OrderSaveRequestDto;
import jpa.jpa_shop.web.dto.request.order.OrderSearchRequestDto;
import jpa.jpa_shop.web.dto.response.order.OrderDetailResponseDto;
import jpa.jpa_shop.web.dto.response.order.OrderQueryDetailDto;
import jpa.jpa_shop.web.dto.response.order.OrderQueryDto;
import jpa.jpa_shop.web.dto.response.order.OrderResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RequestMapping("/api/orders")
@RestController
public class OrderApiController {
    private final OrderServiceIFS orderService;
    private final OrderQueryRepository orderQueryRepository;
    // 주문 검색
    @GetMapping("")
    public List<OrderResponseDto> orderList() {
        return orderService.SearchMemberNameAndOrderStatus(new OrderSearchRequestDto()).stream().map(Order::toDto).collect(Collectors.toList());
    }

    // === 주문 조회 ===

    // 페이징 처리 불가. 모든 Domain fetch (result : 쿼리1)
    @GetMapping("/v1/notUsing")
    public List<OrderResponseDto> notUsing() {
        return orderService.findAllWithItem().stream().map(Order::toDto).collect(Collectors.toList());
    }
    // 페이징 처리 , 쿼리 최적화
    @GetMapping("/list")
    public List<OrderResponseDto> ordersAll() {
        return orderService.findWithMemberAndDelivery().stream().map(Order::toDto).collect(Collectors.toList());
    }

    // === 주문 상세 조회 ===
    @GetMapping("/v1/detail/notUsing")
    public List<OrderDetailResponseDto> detailData() {
        return orderService.findWithMemberAndDelivery().stream().map(OrderDetailResponseDto::new).collect(Collectors.toList());
    }

    // 페이징 처리 (result : 쿼리3)
    @GetMapping("/detail")
    public List<OrderDetailResponseDto> orderDetail(@RequestParam(value = "offset",defaultValue = "0" ,required = false) int offset,
                                     @RequestParam(value = "limit",defaultValue = "10", required = false) int limit) {
        return orderService.findWithMemberAndDelivery(offset,limit).stream().map(OrderDetailResponseDto::new).collect(Collectors.toList());
    }

    // queryDto 조회 전용
    @GetMapping("/list_dto")
    public List<OrderQueryDto> orderQueryDtos(@RequestParam(value = "offset",defaultValue = "0" ,required = false) int offset,
                                              @RequestParam(value = "limit",defaultValue = "20", required = false) int limit)
    {
        return orderQueryRepository.findOrders(offset,limit);
    }

    @GetMapping("/detail_dto")
    public List<OrderQueryDetailDto> orderQueryDetailDtos(@RequestParam(value = "offset",defaultValue = "0" ,required = false) int offset,
                                                          @RequestParam(value = "limit",defaultValue = "10", required = false) int limit)
    {
        return orderQueryRepository.findDetailList(offset,limit);
    }

    @PostMapping("")
    public void createOrder(@Valid @RequestBody OrderSaveRequestDto orderSaveRequestDto)
    {
        orderService.order(orderSaveRequestDto);
    }

    @DeleteMapping("/{orderId}")
    public void cancel(@PathVariable("orderId") Long orderId)
    {
        orderService.cancelOrder(orderId);
    }



}
