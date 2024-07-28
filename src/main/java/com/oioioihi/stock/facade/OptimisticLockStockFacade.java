package com.oioioihi.stock.facade;

import com.oioioihi.stock.service.OptimisticStockService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OptimisticLockStockFacade {

    private final OptimisticStockService optimisticStockService;

    public void decrease(Long id, Long quantity) throws InterruptedException {
        while (true) {
            try {
                optimisticStockService.decrease(id, quantity);

                break; // 정상적으로 수량을 감소했다면 while문 종료
            } catch (Exception e) {
                //정상적으로 수행되지 않았다면 일정시간 대기 후 다시 시도
                Thread.sleep(50);
            }
        }
    }
}
