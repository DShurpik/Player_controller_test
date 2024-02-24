package listeners;

import lombok.extern.log4j.Log4j;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import propertyReader.PropertyReader;

import static propertyReader.PropertyReader.getIntProperty;
@Log4j
public class ListenerProperty implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        ITestListener.super.onTestStart(result);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ITestListener.super.onTestSuccess(result);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ITestListener.super.onTestFailure(result);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ITestListener.super.onTestSkipped(result);
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        ITestListener.super.onTestFailedWithTimeout(result);
    }

    @Override
    public void onStart(ITestContext context) {
        String propertyName = context
                .getSuite()
                .getParameter("config") == null ? System.getProperty("config") : context.getSuite().getParameter("config");
        new PropertyReader(propertyName);
        log.info("Property file is: " + propertyName + ".properties");
        context.getSuite().getXmlSuite().setThreadCount(getIntProperty("thread.count"));
        log.info("Suite name is: " + context.getSuite().getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        log.info("Test suite " + context.getSuite().getName() + " has been completed");
    }
}
