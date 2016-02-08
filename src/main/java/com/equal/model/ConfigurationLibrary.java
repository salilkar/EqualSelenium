package com.equal.model;

public class ConfigurationLibrary {

	static public final String projectName = "Equal";
	static public final String browser= "ie";

	static public String URL = "http://726197-qlmdlj08.rsp.medecision.com:8240/AMDOQ9-AerialPrime/login.faces";

	public static final String summaryResultPath = "src//main//java//com//equal//result";
	public static final String detailResultPath =  "src//main//java//com//equal//result//detailResult";
	public static final String videoPath = "src//main//java//com//equal//result//videos";
	public static final String imagePath = "src//main//java//com//equal//result//images";
	public static final String resultDirectory = "src//..//..//..//..//results";
	public static final String latestDirectory = "src//..//..//..//..//latest";
//	public static final String summaryHTMLPath = "src//com//equal//est//result//Summary.html";

	public static int failCount = 0;
	public static int passCount = 0;
	public static int skipCount = 0;

	public static int failPercentage = 0;
	public static int passPercentage = 0;

	public static String executionStartTime = null;
	public static String executionEndTime = null;

	static public String userName = "qa@southcoast";
	static public String password = "Password1";

	public static String xlpath_controller = "src//main//java//com//equal//input//Controller.xls";
	public static String xlsheet_controller = "ControllerSheet";
	public static String xlDataTable_controller = "ControllerData";
	
	public static String test_attachment = "src//test//resources//testAttachment.exe";

	public static String xlpath_login = "src//com//equal//input//data//Login.xls";
	public static String xlsheet_login = "ValidData";
	public static String xlsheet_login02 = "ValidData1";
	public static String xlDataTable_login = "DataTable";

	public static int testPass = 0;
	public static int testFail = 0;

}

