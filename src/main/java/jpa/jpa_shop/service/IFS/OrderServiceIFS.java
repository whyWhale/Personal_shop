package jpa.jpa_shop.service.IFS;

public interface OrderServiceIFS {

    public Long order(Long memberId,Long itemId,int count);

    public void cancelOrder(Long orderId);
}
