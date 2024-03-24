package com.oioioihi.stock.service;


import com.oioioihi.stock.domain.Stock;
import com.oioioihi.stock.repository.StockRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PessimisticStockService {

    private final StockRepository stockRepository;

    /**
     * [ Pessimistic Lock ]
     * 장점 :
     * 1. 충돌이 빈번하게 일어난다면 Optimistic Lock보다 성능이 좋을수 있다.
     * 2. Lock을 통해 업데이터를 제어하기 때문에 데이터 정합성이 보장된다.
     * <p>
     * 단점 : 별도의 락을 잡기 때문에 성능감소가 있을 수 있다.
     */

    @Transactional
    public synchronized void decrease(Long id, Long quantity) {
        // 장점 :

        Stock stock = stockRepository.findByIdWithPessimisticLock(id);
        stock.decrease(quantity);
        stockRepository.save(stock);
        //select s1_0.id,s1_0.product_id,s1_0.quantity from stock s1_0 where s1_0.id=? for update
    }

}
