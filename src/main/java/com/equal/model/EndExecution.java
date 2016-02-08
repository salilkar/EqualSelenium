package com.equal.model;


import org.testng.annotations.Test;


public class EndExecution {

	@Test
	public void endTime() throws Exception {
		CommonLibrary commonLibrary = new CommonLibrary();
		Reports reports = new Reports();
		ConfigurationLibrary.executionEndTime = commonLibrary.getCurrentTime();
		System.out.println("End Time "+ ConfigurationLibrary.executionEndTime);

		reports.writingSummaryReport();
	//	reports.copyReports();
	//	reports.copyReportsToLatestFolder();
		//reports.sendEmail();

	}
}
