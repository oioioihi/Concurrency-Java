package com.oioioihi.stock.service;


import com.oioioihi.stock.domain.Stock;
import com.oioioihi.stock.repository.StockRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StockService {

    private final StockRepository stockRepository;


    @Transactional // AOP방식으로 동작하는데, proxy객체에선 synchronized 보장이 되지 않음.
    public synchronized void decrease(Long id, Long quantity) {
        // Stock 조회
        // 재고를 감소한 뒤
        // 갱신된 값을 저장
        // monitor 사용

        Stock stock = stockRepository.findById(id).orElseThrow(() -> new RuntimeException("해당하는 stock이 존재하지 않습니다."));
        stock.decrease(quantity);
        stockRepository.saveAndFlush(stock);
    }

}
