package com.szczepix.myinvest.services.futureService;

import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

@Service
public class FutureService {

    private static final Logger LOG = Logger.getAnonymousLogger();
    private final ExecutorService executor;

    public FutureService() {
        executor = Executors.newFixedThreadPool(4);
    }

    public Executor getExecutor() {
        return executor;
    }

    public void submit(final IFuture future) {
        CompletableFuture<IFuture> completableFuture = new CompletableFuture<>();
        executor.submit(() -> {
            try {
                completableFuture.complete(future.submit());
            } catch (Exception e) {
                completableFuture.completeExceptionally(e);
                LOG.info("Executor exception " + e.getMessage());
            }
        });
    }
}
