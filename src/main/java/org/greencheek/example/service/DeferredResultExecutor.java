package org.greencheek.example.service;

import org.springframework.web.context.request.async.DeferredResult;

/**
 * Created by dominictootell on 24/04/2014.
 */
public interface DeferredResultExecutor {
    void executeDeferredResult(String s ,DeferredResult<String> result);
}
