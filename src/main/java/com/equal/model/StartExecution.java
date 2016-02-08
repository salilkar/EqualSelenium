package com.equal.model;

import org.testng.annotations.Test;

public class StartExecution {

	@Test
	public void startTime() throws Exception { 
		CommonLibrary commonLibrary = new CommonLibrary();
		ConfigurationLibrary.executionStartTime = commonLibrary.getCurrentTime();
		Reports.deleteReportFolder();
		Reports.deleteLatestReportFolder();
	}
}
