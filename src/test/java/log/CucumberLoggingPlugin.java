package log;

import io.cucumber.plugin.ConcurrentEventListener;
import io.cucumber.plugin.event.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CucumberLoggingPlugin implements ConcurrentEventListener {

    private static final Logger LOG = LoggerFactory.getLogger(CucumberLoggingPlugin.class);

    private final EventHandler<TestCaseStarted> caseStartedHandler = event -> {
        TestCase testCase = event.getTestCase();
        LOG.info("Scenario: {} ({}:{})", testCase.getName(), testCase.getUri(), testCase.getTags());
    };

    private final EventHandler<TestStepStarted> stepStartedHandler = event -> {
        TestStep step = event.getTestStep();
        if (step instanceof PickleStepTestStep) {
            Step pickle = ((PickleStepTestStep) step).getStep();
            LOG.info("Step: {}", pickle.getText(), pickle.getLine());
        }
    };

    private final EventHandler<TestCaseFinished> caseFinishedHandler =
            event -> {
                Result result = event.getResult();
                Throwable error = result.getError();
                if (error == null) {
                    LOG.info("[{}] Scenario \"{}\" finished after {} seconds\n", result.getStatus(),
                            event.getTestCase().getName(), result.getDuration().getSeconds());
                } else {
                    LOG.info("[{}] Scenario \"{}\" finished after {} seconds with error \"{}\"\n",
                            result.getStatus(), event.getTestCase().getName(), result.getDuration().getSeconds(),
                            error.getMessage().replaceAll("[\\t\\n\\r]+", " "));
                }
            };

    @Override
    public void setEventPublisher(EventPublisher publisher) {
        publisher.registerHandlerFor(TestCaseStarted.class, caseStartedHandler);
        publisher.registerHandlerFor(TestStepStarted.class, stepStartedHandler);
        publisher.registerHandlerFor(TestCaseFinished.class, caseFinishedHandler);
    }
}
