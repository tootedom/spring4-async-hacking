package org.greencheek.example.controller;

import org.greencheek.example.service.DeferredResultExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.Callable;

@Controller
class IndexController {

    @Autowired
    DeferredResultExecutor deferredResultExecutor;

    @RequestMapping(value = "/sync", method = RequestMethod.GET)
    @ResponseBody
    public String showIndex() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Sync Hello world";
    }

    @RequestMapping("/async")
    @ResponseBody
    public Callable<String> asyncCall() {
        return new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(5000);
                return "Async Hello World";
            }
        };
    }

    @RequestMapping("/deferredRes")
    @ResponseBody
    public DeferredResult<String> deferredCall() {
        DeferredResult<String> result = new DeferredResult<>();
        deferredResultExecutor.executeDeferredResult("Deferred Hello World",result);
        return result;
    }


}
