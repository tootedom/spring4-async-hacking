package org.greencheek.example.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * Created by dominictootell on 24/04/2014.
 */
public class AsyncDeferredResultExecutor implements DeferredResultExecutor {

    private final java.util.concurrent.LinkedBlockingQueue<DeferredResult<String>> suspendedRequests = new java.util.concurrent.LinkedBlockingQueue<>();

    @Async("asyncTaskExecutor")
    public void executeDeferredResult(String result, DeferredResult<String> deferredResult) {
        suspendedRequests.add(deferredResult);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        DeferredResult<String> res = suspendedRequests.poll();
        res.setResult(result);
    }

}
