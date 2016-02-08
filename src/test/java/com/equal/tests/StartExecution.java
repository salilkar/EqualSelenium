package com.equal.tests;

import org.testng.annotations.Test;

import com.equal.model.CommonLibrary;
import com.equal.model.ConfigurationLibrary;
import com.equal.model.Reports;

public class StartExecution {

	@Test
	public void startTime() throws Exception { 
		CommonLibrary commonLibrary = new CommonLibrary();
		ConfigurationLibrary.executionStartTime = commonLibrary.getCurrentTime();
		Reports.deleteReportFolder();
		Reports.deleteLatestReportFolder();
	}
} 
