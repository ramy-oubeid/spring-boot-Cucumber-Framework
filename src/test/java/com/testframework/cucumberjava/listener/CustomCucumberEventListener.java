package com.testframework.cucumberjava.listener;

import io.cucumber.plugin.ConcurrentEventListener;
import io.cucumber.plugin.event.EventPublisher;
import io.cucumber.plugin.event.TestCaseStarted;
import io.cucumber.plugin.event.TestCaseFinished;
import org.springframework.stereotype.Component;

@Component
public class CustomCucumberEventListener implements ConcurrentEventListener {

    @Override
    public void setEventPublisher(EventPublisher publisher) {
        // Register event handlers
        publisher.registerHandlerFor(TestCaseStarted.class, this::onTestCaseStarted);
        publisher.registerHandlerFor(TestCaseFinished.class, this::onTestCaseFinished);
    }

    private void onTestCaseStarted(TestCaseStarted event) {
        System.out.println("Test Case Started: " + event.getTestCase().getName());
    }

    private void onTestCaseFinished(TestCaseFinished event) {
        System.out.println("Test Case Finished: " + event.getTestCase().getName() +
                ", Result: " + event.getResult().getStatus());
    }
}
