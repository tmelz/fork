/*
 * Copyright 2014 Shazam Entertainment Limited
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License.
 *
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.shazam.fork.listeners;

import com.android.ddmlib.testrunner.ITestRunListener;
import com.android.ddmlib.testrunner.TestIdentifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

import static java.lang.String.format;

class LoggingTestRunListener implements ITestRunListener {
    private static final Logger logger = LoggerFactory.getLogger(LoggingTestRunListener.class);
	private final String serial;
	private final SwimlaneConsoleLogger runningStatusListener;

	public LoggingTestRunListener(String serial, SwimlaneConsoleLogger runningStatusListener) {
		this.serial = serial;
		this.runningStatusListener = runningStatusListener;
	}

	@Override
	public void testRunStarted(String runName, int testCount) {
	}

	@Override
	public void testStarted(TestIdentifier test) {
		runningStatusListener.testStarted(serial, test);
        System.out.println(format("%s [%s] Starting %s", runningStatusListener.getStatus(serial), serial, test));
//        logger.debug("{} [{}] Starting {}", runningStatusListener.getStatus(serial), serial, test);
	}

	@Override
	public void testEnded(TestIdentifier test, Map<String, String> testMetrics) {
		runningStatusListener.testFinished(serial);
        System.out.println(format("%s [%s] Finished %s", runningStatusListener.getStatus(serial), serial, test));
//        logger.debug("{} [{}] Finished {}", runningStatusListener.getStatus(serial), serial, test);
	}

	@Override
	public void testRunStopped(long elapsedTime) {
        System.out.println(format("%s [%s] Test stopped after %s ms", runningStatusListener.getStatus(serial), serial, elapsedTime));
//        logger.debug("{} [{}] Test stopped after {} ms", runningStatusListener.getStatus(serial), serial, elapsedTime);
	}

	@Override
	public void testFailed(TestIdentifier test, String trace) {
		runningStatusListener.testFailed(serial);
        System.out.println(format("%s [%s] Test %s\n %s", runningStatusListener.getStatus(serial), serial, test, trace));
//        logger.debug("{} [{}] Test {} {}\n {}", runningStatusListener.getStatus(serial), serial, test, status.name(), trace);
	}

    @Override
    public void testAssumptionFailure(TestIdentifier test, String trace) {
        logger.debug("test={}", test);
        logger.debug("assumption failure {}", trace);
    }

    @Override
    public void testIgnored(TestIdentifier test) {
        logger.debug("ignored test {}", test);
    }

    @Override
	public void testRunFailed(String errorMessage) {
        System.out.println(format("[%s] Test run failed: %s", serial, errorMessage));
//        logger.debug("[{}] Test run failed: {}", serial, errorMessage);
	}

	@Override
	public void testRunEnded(long elapsedTime, Map<String, String> runMetrics) {
	}

}
