package Newpackage.Test;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {

    int count = 0;
    int maxRetry = 1;   // retry once

    @Override
    public boolean retry(ITestResult result) {

        if (count < maxRetry) {
            count++;
            return true;   // retry test
        }
        return false;      // stop retrying
    }
}