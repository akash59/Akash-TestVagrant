package testrunners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.IAlterSuiteListener;
import org.testng.xml.XmlSuite;

import java.util.List;

public class TestNGSuiteAlterer implements IAlterSuiteListener {

    private static final Logger LOG = LoggerFactory.getLogger(TestNGSuiteAlterer.class);

    @Override
    public void alter(List<XmlSuite> suites) {
        LOG.info("Setting data provider thread count");
        int count = Integer.parseInt(System.getProperty("ThreadCount", "2"));
        XmlSuite suite = suites.get(0);
        suite.setDataProviderThreadCount(count);
    }
}