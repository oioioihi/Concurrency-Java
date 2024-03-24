package com.oioioihi.stock.service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TransactionStockService {

    private final StockService stockService;

    private static void startTransaction() {
        System.out.println("Start Transaction");
    }

    private static void commitTransaction() {
        System.out.println("Commit Transaction");
    }

    public void decrease(Long id, Long quantity) {

        startTransaction();

        stockService.decrease(id, quantity);
        // thread1이 비즈니스 로직을 실행 후 DB에 커밋하기 전에 thread2가 비즈니스 로직을 시작하면 재고값에 동시성이슈가 발생힌다.
        commitTransaction();
    }
}
