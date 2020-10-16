package testrunners;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.IAlterSuiteListener;
import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.xml.XmlSuite;

import java.util.List;

@CucumberOptions(
        features = "src/test/resources",
        glue = { "stepdefs", "testbase" },
        monochrome = true,
        plugin = {
                "pretty",
                "json:target/cucumber.json",
                "log.CucumberLoggingPlugin"
        },
        tags = "@Regression"
)

public class SanityTestRunner extends AbstractTestNGCucumberTests {

    private static final Logger LOG = LoggerFactory.getLogger(SanityTestRunner.class);

    @BeforeSuite
    public void beforeSuite(ITestContext context)
    {
        LOG.info(context.getCurrentXmlTest().getSuite().getName() +" : suite execution started");
    }

    @AfterSuite
    public void afterSuite(ITestContext context)
    {
        LOG.info(context.getCurrentXmlTest().getSuite().getName() +" : suite execution finished");
    }


    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }

}

