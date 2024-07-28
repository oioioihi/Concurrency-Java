package com.oioioihi.stock.service;


import com.oioioihi.stock.domain.Stock;
import com.oioioihi.stock.repository.StockRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OptimisticStockService {

    private final StockRepository stockRepository;

    /**
     * [ Optimistic Lock ]
     * 장점 :
     * 1. 별도의 락을 잡지 않으므로 비관적락보다 성능상 이점이 있다.
     * 2. 동시성 이슈가 발생활 확률이 낮다고 생각되는 경우 사용한다.
     * <p>
     * 단점 : 실패시 재시도 로직을 개발자가 직접 핸들링 해야한다.
     */

    @Transactional
    public synchronized void decrease(Long id, Long quantity) {
        Stock stock = stockRepository.findByIdWithOptimisticLock(id);
        stock.decrease(quantity);
        stockRepository.save(stock);
    }

}
