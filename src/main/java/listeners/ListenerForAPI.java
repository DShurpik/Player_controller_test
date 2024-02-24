package listeners;

import lombok.extern.slf4j.Slf4j;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
@Slf4j
public class ListenerForAPI implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        ITestListener.super.onTestStart(result);
        log.debug(result.getName() + " has been started");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        log.debug("This test has been completed => " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        log.debug("This test has been failed => " + result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        log.debug("This test has been skipped => " + result.getName());
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
        ITestListener.super.onStart(context);
    }

    @Override
    public void onFinish(ITestContext context) {
        ITestListener.super.onFinish(context);
    }
}
