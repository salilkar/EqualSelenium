package com.equal.model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.Zip;

public class Reports {

	String htmlheader;
	String bodyHeader ;
	String htmlfooter;
	BufferedWriter fileObj;
	FileWriter fileHTML;
	public int stepNo = 1;
	
	public String pass = "&#10004";
	
	public String fail = "&#10008";

	//According to number of @test methods inside java file
	public static int failedTests = 0;

	public static int passedTests = 0;

	public static int skippedTests = 0;

	public static boolean testStatus = true;

	public  String scriptStartTime=null;

	private static int imagesCount = 0;

	private static int videosCount = 0;

	private static final char NEWLINE = '\n';

	private static final long MAX_BUFFER_SIZE = 1024;

	private static StringBuilder resultBuffer = new StringBuilder();

	private static List<String[]> list = new ArrayList<String[]>();

	public void createFile(String testCaseName) throws Exception {

		File detailResultDir = new File(ConfigurationLibrary.detailResultPath);

		if(!detailResultDir.isDirectory()){
			detailResultDir.mkdirs();
		}

		String path = detailResultDir + "/" + testCaseName + ".html";

		File testCaseFile = new File(path);

		if(testCaseFile.exists())
			testCaseFile.delete();

		testCaseFile.createNewFile();

		FileWriter fileHTML = new FileWriter(testCaseFile, true);

		fileObj = new BufferedWriter(fileHTML);

		htmlheader = "<html><head>";
		htmlheader += "<title>Test Execution Report</title>";
		htmlheader += "</head><body>";
		htmlheader += "<style>div.header-fixed {top: 5px;background-color:white;position: absolute;right: 8px;position: fixed;}";
		htmlheader += "table.header {top: 0px;position: fixed;background-color:white;}";
		htmlheader += "font.style1 {font-family: 'calibri';font-size: 1em;text-align: justify;}</style>";

		fileObj.write(htmlheader);

		String table = "<table class=\"header\" width = 92.2% align = left border='1' bordercolordark='#C0C0C0' cellspacing='0' cellpadding='0' bordercolorlight='#C0C0C0' bordercolor='#C0C0C0'>";
		bodyHeader = table;

		bodyHeader = bodyHeader + "<tr><th colspan='4' align = center style=\"background-color:#168DDB;\"><font color = #ffffff face='calibri' size='4'> Test Script Name: " + testCaseName + "</font></th></tr>";
		bodyHeader = bodyHeader + "<tr bgcolor = #168DDB><th width = 35% ><font color = #ffffff size='4' face='calibri'>Step Description</font></th>";
		bodyHeader = bodyHeader + "<th width = 35%><font color = #ffffff size='4' face='calibri'>Actual Result</font></th>";
		bodyHeader = bodyHeader + "<th width = 10%><font color = #ffffff size='4' face='calibri'>Status</font></th>";
		bodyHeader = bodyHeader + "<th width = 20%><font color = #ffffff size='4' face='calibri'>Remarks</font></th></tr></table>";
		bodyHeader = bodyHeader + "<div style=\"margin-top:50px\"></div>";
		bodyHeader = bodyHeader + "<table width = 93.5% align = left border='1' bordercolordark='#C0C0C0' cellspacing='0' cellpadding='0' bordercolorlight='#C0C0C0' bordercolor='#C0C0C0'>";

		fileObj.write(bodyHeader);
	}

	public void writeTestName(String testName) {

		try {
			String htmlTestName = "<tr><td colspan='4'><font class=\"style1\"><b>" + "@Test name : " + testName + "</b></font></td></tr>";
			fileObj.write(htmlTestName);

		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
	}

	public void writeIntoFile(WebDriver driver, String testCaseName, String stepSummary, String stepDecription, String ActualResult, String stepStatus, String string, String timeStamp) {

		try {
			String htmlBody = "<tr><td border = none colspan = 4><font color = #168DDB class=\"style1\"><b>" + "Step " + (stepNo++) + ": " +  stepSummary + "</b></font></td></tr>";
			htmlBody = htmlBody + "<tr><td width = 35% border = none><font class=\"style1\"> " + stepDecription + "</font></td>";
			htmlBody = htmlBody + "<td align=center width=35% Border = 0><font class=\"style1\"> " + ActualResult + "</font></td>";
			if(stepStatus.equals(fail)){
				testStatus = false;
				CommonLibrary.captureScreenshot(driver, testCaseName + "_" + timeStamp);
				String imgLink = "<a style ='text-decoration: none; color: #C0292A; text-align: center;' href=\"../images/" + testCaseName + "_" + timeStamp + ".jpg\"><div width=100%>";
				htmlBody = htmlBody + "<td align = center width = 10%><font color = #C0292A class=\"style1\">" + imgLink + stepStatus + "</div></a></font></td>";
				htmlBody = htmlBody + "<td align = center width = 20%><font class=\"style1\"> " + string + "</font></td>";
			}else{

				htmlBody = htmlBody + "<td align = center width = 10%><font color = #3FCE30 class=\"style1\">" + stepStatus + "</font></td>";
				htmlBody = htmlBody + "<td align = center width = 20%><font class=\"style1\"> " + string + "</font></td>";
			}
			fileObj.write(htmlBody);

		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
	}

	public void closeFile() throws Exception {
		htmlfooter = "</table></body></html>";
		fileObj.write(htmlfooter);
		fileObj.close();
	}

	public void writingSummaryReport() {

		if (resultBuffer.length() > 0) {
			System.out.println("Into Writing");

			writeResultBufferToFile();
			getImagesReport();
			getVideosReport();
			getExecutionHealthReport();
			getDashBoardReport();
		}
	}

	/*
	 * Get Dash-board html.
	 */
	public void getDashBoardReport() {
		try {
			File dashboardDir = new File(ConfigurationLibrary.summaryResultPath);
			int total = ConfigurationLibrary.passCount+ConfigurationLibrary.failCount+ConfigurationLibrary.skipCount;
			int passPercentage = ((ConfigurationLibrary.passCount)*100)/total;
			int failPercentage = ((ConfigurationLibrary.failCount)*100)/total;
			int skipPercentage = ((ConfigurationLibrary.skipCount)*100)/total;

			if(!dashboardDir.isDirectory()){
				dashboardDir.mkdirs();
			}

			String path = dashboardDir + "/" + "DashBoard.html";


			String dashBoard = "<!DOCTYPE html><html><script>";
			dashBoard = dashBoard + "function getsumheight(){";
			dashBoard = dashBoard + "document.getElementById('rowheight3').height=screen.height-((screen.height*18)/100)-document.getElementById('rowheight1').clientHeight-document.getElementById('rowheight2').clientHeight-document.getElementById('rowheight4').clientHeight;";
			dashBoard = dashBoard + "document.getElementById('tableWidth').width = screen.width;}";
			dashBoard = dashBoard + "</script><body onload=\"getsumheight()\"><style>";
			dashBoard = dashBoard + ".row { vertical-align: top; height:auto !important; }";
			dashBoard = dashBoard + ".list {display:none; }";
			dashBoard = dashBoard + ".show {display: none; }";
			dashBoard = dashBoard + ".hide:target + .show {display: inline; }";
			dashBoard = dashBoard + ".hide:target {display: none; }";
			dashBoard = dashBoard + ".hide:target ~ .list {display:inline; }";
			dashBoard = dashBoard + "@media print { .hide, .show { display: none; } }";
			dashBoard = dashBoard + ".btn {border-radius: 6px;padding:0.1em;border:2px;background:#168DDB;}";
			dashBoard = dashBoard + ".badge {display: inline-block;min-width: 10px;padding: 5px 7px;font-size: 12px;font-weight: bold;line-height: 1;";
			dashBoard = dashBoard + "color: #168DDB;text-align: center;white-space: nowrap;vertical-align: middle;background-color: #ffffff;border-radius: 10px;}";
			dashBoard = dashBoard + "a.animate {color:white;text-decoration: none;}";
			dashBoard = dashBoard + "div.space {margin-bottom: 6px;list-style: none;}";
			dashBoard = dashBoard + ".center {margin: auto;width: 50%;}";
			dashBoard = dashBoard + "font.style1 {font-family: 'calibri';font-size: 1em;}";
			dashBoard = dashBoard + "footer {bottom: 0;left: 0;position: fixed;right: 0;text-align: center;background-color: #ffffff;}";
			dashBoard = dashBoard + "div.home {text-align: center;top: 1px;color: #ffffff;right: 20px;position: fixed;min-width: 40px;min-height: 30px;border-radius: 2px;padding:0em;background:#fffff0;}";
			dashBoard = dashBoard + "li {margin: 0.5em 0;}";
			dashBoard = dashBoard + "b.space {line-height: 180%;}";
			dashBoard = dashBoard + "</style><table id='tableWidth'><tr>";
			dashBoard = dashBoard + "<td id=\"rowheight1\" align=center width=17%><a href=\"https://www.medecision.com/\"><img width=90% height=60% src=\"../result/Images/Endeavour.png\"/></a></td>";
			dashBoard = dashBoard + "<td width=83%><div class=\"center\" ><font size=5 face='calibri'><b>" + ConfigurationLibrary.projectName+ "Testing Dashbaord" + "</b></font></div>";
			dashBoard = dashBoard + "<div style=\"float:right;\"><font size=3  face='calibri'>" + "Executed on:" + ConfigurationLibrary.executionStartTime + "</font></div></td></tr>";
			dashBoard = dashBoard + "<tr><td rowspan=\"4\" bgcolor=\"#FFFFFF\" valign=top width=17%><div style=\"margin-top: 12px;\"></div>";

			String[] prevReports = getPrevReports();
			dashBoard = dashBoard + "<a href=\"#hide1\" class=\"hide animate\" id=\"hide1\"><div class=\"space btn\"><table width=100%><tr><td align='left'><font class=\"style1\" style=\"padding-left: 10px;\">Previous Reports</font></td><td align='right'><span class=\"badge\">" + prevReports.length + "</span></td></tr></table></div></a>";
			dashBoard = dashBoard + "<a href=\"#show1\" class=\"show animate\" id=\"show1\"><div class=\"space btn\"><table width=100%><tr><td align='left'><font class=\"style1\" style=\"padding-left: 10px;\">Previous Reports</font></td><td align='right'><span class=\"badge\">" + prevReports.length + "</span></td></tr></table></div></a>";
			if(prevReports.length>0) {
				dashBoard = dashBoard + "<div class=\"list\"><ul><li><a href=\"../../../" + ConfigurationLibrary.executionStartTime + "/result/DashBoard.html\" style=\"text-decoration: none;\"><font style=\"color: #168DDB;\" class=\"style1\">" + ConfigurationLibrary.executionStartTime + "</font></a></li></ul></div>";
				for(int i=0;i<prevReports.length;i++) {
					dashBoard = dashBoard + "<div class=\"list\"><ul><li><a href=\"../../../" + prevReports[i] + "/result/DashBoard.html\" style=\"text-decoration: none;\"><font style=\"color: #168DDB;\" class=\"style1\">" + prevReports[i] + "</font></a></li></ul></div>";
				}
			}
			dashBoard = dashBoard + "<a class=\"animate\" href=\"../result/images/images.html\" target=\"frame1\"><div class=\"space btn\"><table width=100%><tr><td align='left'><font class=\"style1\" style=\"padding-left: 10px;\">Images</font></td><td align='right'><span class=\"badge\">" + imagesCount + "</span></td></tr></table></div></a>";
			dashBoard = dashBoard + "<a class=\"animate\" href=\"../result/videos/videos.html\" target=\"frame1\"><div class=\"space btn\"><table width=100%><tr><td align='left'><font class=\"style1\" style=\"padding-left: 10px;\">Videos</font></td><td align='right'><span class=\"badge\">" + videosCount + "</span></td></tr></table></div></a>";
			dashBoard = dashBoard + "<a class=\"animate\" href=\"../result/detailResult/executionHealth.html\" target=\"frame1\"><div class=\"space btn\"><table width=100%><tr><td align='left'><font class=\"style1\" style=\"padding-left: 10px;\">Execution Health</font></td></tr></table></div></a>";
			//dashBoard = dashBoard + "<a class=\"animate\" href=\"../result/detailResult/totalRuns.html\" target=\"frame1\"><div class=\"space btn\"><table width=100%><tr><td align='left'><font class=\"style1\" style=\"padding-left: 10px;\">Total Runs</font></td></tr></table></div></a>";
			dashBoard = dashBoard + "</td><td id=\"rowheight2\" width=80%>";
			dashBoard = dashBoard + "<table align=center width = 91% cellspacing=10><tr><td align='center' width=24%>";
			dashBoard = dashBoard + "<div align = 'center' style=\"border:2px; background:#649421; padding:0.5em;\"><font class=\"style1\"><b class=\"space\">Execution Start Time</b><br/>" + ConfigurationLibrary.executionStartTime + "</font></div></td>";
			dashBoard = dashBoard + "<td align='center' width=24%><div align = 'center' style=\"border:2px; background:#6AC66A; padding:0.5em;\"><font class=\"style1\"><b class=\"space\">Execution End Time</b><br/>" + ConfigurationLibrary.executionEndTime + "</font></div></td>";
			dashBoard = dashBoard + "<td align='center' width=24%><div align = 'center' style=\"border:2px; background:#2EB1CE; padding:0.5em;\"><font class=\"style1\"><b class=\"space\">Total Time</b><br/>" + CommonLibrary.getExecutionTime(ConfigurationLibrary.executionStartTime, ConfigurationLibrary.executionEndTime) +"</font></div></td>";
			dashBoard = dashBoard + "<td align='center' width=28% rowspan=\"2\"><canvas id=\"myCanv\" width = 300% height = 140%></canvas>";
			dashBoard = dashBoard + "<script>var data=[{name: \"Pass: " + passPercentage + "%\", grade:" + ConfigurationLibrary.passCount + ", c:\"#649421\"} ,{name: \"Fail: " + failPercentage + "%\", grade:" + ConfigurationLibrary.failCount + ", c:\"#C0292A\"}, {name: \"Skip: " + skipPercentage + "%\", grade:" + ConfigurationLibrary.skipCount + ", c:\"#F5D103\"}];";
			dashBoard = dashBoard + "function getSum() {var mySum = 0;for (var i = 0; i < data.length; i++) {mySum += data[i].grade ;}return mySum;}";
			dashBoard = dashBoard + "function plotPie() {var x=[(myCanv.width)]-[((myCanv.width)*42)/100], y=[(myCanv.height)/2], y0=10, x0=[(myCanv.width)]-[((myCanv.width)*70)/100], r=y, angle1=0;";
			dashBoard = dashBoard + "canv = document.getElementById(\"myCanv\");ctx = canv.getContext(\"2d\");var mySum = getSum();for (var i = 0; i < data.length; i++) {ctx.strokeStyle = data[i].c ;";
			dashBoard = dashBoard + "ctx.fillStyle = data[i].c ;angle2 = (Math.PI * 2 * data[i].grade) / mySum ;ctx.beginPath();ctx.moveTo(x0,y) ;ctx.arc(x0,y,r, angle1, angle1 + angle2-0.00, false) ;";
			dashBoard = dashBoard + "ctx.fill ();angle1 += angle2 ;ctx.font=\"12px Arial\";ctx.fillRect(x,y0,5,5);ctx.fillText(data[i].name,x+10,y0+8);y0+=18;}}plotPie() ;";
			dashBoard = dashBoard + "</script></td></tr>";
			dashBoard = dashBoard + "<tr><td align='center' width=24%><div align = 'center' style=\"border:2px; background:#8FD32D; padding:0.5em;\"><font class=\"style1\"><b class=\"space\">Testscripts Executed</b><br/>" + total + "</font></div></td>";
			dashBoard = dashBoard + "<td align='center' width=24%><div align = 'center' style=\"border:2px; background:#649421; padding:0.5em;\"><font class=\"style1\"><b class=\"space\">Test Pass</b><br/>" + ConfigurationLibrary.passCount + "</font></div></td>";
			dashBoard = dashBoard + "<td align='center' width=24%><div align = 'center' style=\"border:2px; background:#C0292A; padding:0.5em;\"><font class=\"style1\"><b class=\"space\">Test Fail</b><br/>" + ConfigurationLibrary.failCount + "</font></div></td></tr></table>";
			dashBoard = dashBoard + "</td></tr>";
			dashBoard = dashBoard + "<tr><td id=\"rowheight3\" onload=\"value()\" align=left><iframe id=\"frame1\" name=\"frame1\" scrolling=\"yes\" style=\"border:none; margin-top:15px; margin-left:20px;\" height=100% width=95% src=\"../result/Summary.html\"></iframe></td></tr></table>";
			dashBoard = dashBoard + "<footer id=\"rowheight4\"><font color = #1E90FF face='calibri' size= '2'>Copyright � 2015 <a href=\"https://www.medecision.com/\">Medecision</a>. All Rights Reserved.</font></footer>";
			dashBoard = dashBoard + "<a class=\"animate\" href=\"../result/DashBoard.html\"><div left' class=\"home\"><img width=28px height=25px alt=\"Home\" src=\"../result/Images/home.png\"/></div></a></body></html>";


			File f = new File(path);
			BufferedWriter bw = new BufferedWriter(new FileWriter(f));
			bw.write(dashBoard);
			bw.close();
			System.out.println("Dashboard report created");

		} catch(Exception e) {
			e.getMessage();
		}

	}

	/*
	 * Add contents to buffer for summary report.
	 */
	public void summaryReport(final String module, final String tcName,final String scriptExecutionTime, final String status) {
		try {

			if (resultBuffer.length() > 0) {
				resultBuffer.append(NEWLINE);
			}

			resultBuffer.append(" "+ module + " ~ " + tcName + " ~ " + scriptExecutionTime + " ~ " + status + "`");

			if (resultBuffer.length() < MAX_BUFFER_SIZE) {
				return;
			}

		} catch (Exception e) {
		}
	}

	/*
	 * Get summary report
	 */
	private synchronized void writeResultBufferToFile() {
		System.out.println("*******"+ ConfigurationLibrary.executionStartTime + "*******" + ConfigurationLibrary.executionEndTime + "*******");

		try {

			File summaryResultDir = new File(ConfigurationLibrary.summaryResultPath);

			if(!summaryResultDir.isDirectory()){
				summaryResultDir.mkdirs();
			}

			String path = summaryResultDir + "/" + "Summary.html";

			OutputStream htmlfile = new FileOutputStream(new File(path));
			PrintStream printhtml = new PrintStream(htmlfile);

			String htmlheader = "<html><head>";
			htmlheader += "<title>Automation Report</title>";
			htmlheader += "</head><body>";
			String htmlfooter = "</body></html>";
			String bodyHeader = "<style>#header-fixed {position: fixed;top: 0px;background-color:white;}";
			bodyHeader = bodyHeader + "font.style1 {font-family: 'calibri';font-size: 1em;word-spacing: 12px;}</style>";

			bodyHeader = bodyHeader + "<table id=\"header-fixed\" width=92%><tr bgcolor = #168DDB>";
			bodyHeader = bodyHeader + "<th width = 25%><font color = #ffffff face='calibri' size= '4.5'>" + "Module Name" + "</font></th>";
			bodyHeader = bodyHeader + "<th width = 25%><font color = #ffffff face='calibri' size= '4.5'>" + "Test Script Name" + "</font></th>";
			bodyHeader = bodyHeader + "<th width = 10%><font color = #ffffff face='calibri' size= '4.5'>" + "Duration" + "</font></th>";
			bodyHeader = bodyHeader + "<th width = 10%><font color = #ffffff face='calibri' size= '4.5'>" + "Status" + "</font></th></table>";
			bodyHeader = bodyHeader + "<div style=\"margin-top:27px\"></div>";


			bodyHeader = bodyHeader + "<table width = 93% border='1' bordercolordark='#C0C0C0' cellspacing='0' cellpadding='0' bordercolorlight='#C0C0C0' bordercolor='#C0C0C0'>";

			String[] temp = resultBuffer.toString().split("`");

			for (String s : temp) {

				String[] data = s.trim().split("~");

				bodyHeader = bodyHeader + "<tr>";

				for (int i = 0; i < data.length; i++) {

					if (i == data.length - 1) {
						if(data[i].trim().equals(pass)){
							String fileLink = "<a style ='text-decoration : none ; color: #3FCE30; text-align: center;' href= \"../result/detailResult/" + data[1].trim() + ".html\"  title='View detail result' ><div width=100%>";
							bodyHeader = bodyHeader + "<td width = 10% align = center><font class=\"style1\"> " + fileLink	+ data[i].trim() + "</div></a></font></td>";
						}else{
							String fileLink = "<a style ='text-decoration : none ; color: #C0292A; text-align: center;' href= \"../result/detailResult/" + data[1].trim() + ".html\"  title='View detail result' ><div width=100%>";
							bodyHeader = bodyHeader + "<td width = 10% align = center><font class=\"style1\"> " + fileLink	+ data[i].trim() + "</div></a></font></td>";
						}
					}else if(i == data.length-2)
						bodyHeader = bodyHeader + "<td width = 10% align=center><pre><font class=\"style1\"> " + data[i].trim() + "</font></pre></td>";
					else if(i == data.length-3)
						bodyHeader = bodyHeader + "<td width = 25%><pre><font class=\"style1\"> " + data[i].trim() + "</font></pre></td>";
					else
						bodyHeader = bodyHeader + "<td width = 25%><pre><font class=\"style1\"> " + data[i].trim() + "</font></pre></td>";
				}
				bodyHeader = bodyHeader + "</tr>";
			}
			bodyHeader = bodyHeader + "</table>";

			printhtml.println(htmlheader + bodyHeader + htmlfooter);

			printhtml.close();
			htmlfile.close();
			System.out.println("Summary report created");

			resultBuffer.delete(0, resultBuffer.length());

		} catch (Exception e) {
			e.getMessage();
		}
	}

	/*
	 * Get images report
	 */
	public void getImagesReport() {
		try {
			File imageDir = new File(ConfigurationLibrary.imagePath);

			if(!imageDir.isDirectory()){
				imageDir.mkdirs();
			}

			String path = imageDir + "/" + "images.html";

			FileUtils.copyFile(new File("src//main//resources//images//home.png"), new File(ConfigurationLibrary.imagePath+"/home.png"));
			FileUtils.copyFile(new File("src//main//resources//images//Endeavour.png"), new File(ConfigurationLibrary.imagePath+"/Endeavour.png"));

			String imageReport = "<!DOCTYPE html><html><body>";
			List<String> images = getFileNames(ConfigurationLibrary.imagePath, ".jpg");
			imagesCount = images.size();
			if(imagesCount>0) {
				imageReport = imageReport + "<style>.btn{display:inline-block;padding:6px 12px;font-size:14px;font-weight:400;line-height:309px;text-align:center;white-space:nowrap;vertical-align:middle;";
				imageReport = imageReport + "-ms-touch-action:manipulation;touch-action:manipulation;-webkit-user-select:none;-moz-user-select:none;-ms-user-select:none;user-select:none;background:#ffffff;";
				imageReport = imageReport + "border:0px solid transparent;border-radius:2px}</style>";
				imageReport = imageReport + "<script type=\"text/javascript\">var i = 0;var image = new Array();";


				for(int i = 0 ; i < imagesCount; i++) {
					imageReport = imageReport + "image["+ i + "] = \"" + images.get(i) + "\";";
				}

				imageReport = imageReport + "var k = image.length-1;";
				imageReport = imageReport + "function increment() {if(i<k) {i++;}else {i=0;}swapImage();}";
				imageReport = imageReport + "function decrement() {if(i>0) {i--;}else {i=k;}swapImage();}";
				imageReport = imageReport + "function swapImage(){var el = document.getElementById(\"mydiv\");el.innerHTML=image[i];var img= document.getElementById(\"slide\");img.src= image[i];";
				imageReport = imageReport + "img.width=\"542\";img.height=\"364\";}";
				imageReport = imageReport + "function addLoadEvent(func) {var oldonload = window.onload;if (typeof window.onload != 'function') {window.onload = func;}else {window.onload = function() {"; 
				imageReport = imageReport + "if (oldonload) {oldonload();}func();}}}addLoadEvent(function() {swapImage(); });</script>";

				imageReport = imageReport + "<table border=0 width=85% height=100%><tr>";
				imageReport = imageReport + "<td width=25%%><button style=\"float: right;\" class=\"btn\" onclick=\"decrement()\"><font size='4'>&#9668</font></button></td>";
				imageReport = imageReport + "<td width=50% align=\"center\"><a><img name=\"slide\" id=\"slide\" alt =\"Screenshots\" src=\"" + images.get(0) + "\"/></a></td>";
				imageReport = imageReport + "<td width=25%><button style=\"float: left;\" class=\"btn\" onclick=\"increment()\"><font size='4'>&#9658</font></button></td>";
				imageReport = imageReport + "</tr><tr><td colspan='3' align=\"center\" style=\"font:small-caps bold 15px georgia; color:#168DDB;\"> <div id =\"mydiv\"></div></td></tr></table>";

			} else {
				imageReport = imageReport + "<div align=center style=\"background:#168DDB; color:#FFFFFF; padding: 5px 7px; border-radius: 6px;\">Images</div>";
				imageReport = imageReport + "<div width=100% style=\"margin-top:120px;\" align=center valign=center>";
				imageReport = imageReport + "<font style=\"font-family:'calibri';\" size='6'>Hurray..!!!<br/>TestScript(s) got passed.</font></div>";

			}

			imageReport = imageReport + "</body></html>";

			File f = new File(path);
			BufferedWriter bw = new BufferedWriter(new FileWriter(f));
			bw.write(imageReport);
			bw.close();
			System.out.println("Images report created");
		} catch(Exception e) {
			e.getMessage();
		}
	}

	/*
	 * Get videos report
	 */
	public void getVideosReport() {
		try {
			File videoDir = new File(ConfigurationLibrary.videoPath);

			if(!videoDir.isDirectory()){
				videoDir.mkdirs();
			}

			String path = videoDir + "/" + "videos.html";


			String videoReport = "<!DOCTYPE html><html><body>";
			List<String> videos = getFileNames(ConfigurationLibrary.videoPath, ".mov");
			videosCount = videos.size();
			if(videosCount>0) {
				videoReport = videoReport +	"<style>";
				videoReport = videoReport + ".btn{display:inline-block;padding:6px 12px;font-size:14px;font-weight:400;line-height:309px;text-align:center;white-space:nowrap;vertical-align:middle;-ms-touch-action:manipulation;";
				videoReport = videoReport + "touch-action:manipulation;-webkit-user-select:none;-moz-user-select:none;-ms-user-select:none;user-select:none;background:#ffffff;border:0px solid transparent;border-radius:2px}</style>";

				videoReport = videoReport + "<script type=\"text/javascript\"> var i = 0;var video = new Array();";

				for(int i = 0 ; i < videosCount; i++) {
					videoReport = videoReport + "video[" + i + "] = \""+ videos.get(i) + "\";"; 
				}

				videoReport = videoReport + "var k = video.length-1;";
				videoReport = videoReport + "function increment() {if(i<k) {i++;}else {i=0;}swapVideo();}";
				videoReport = videoReport + "function decrement() {if(i>0) {i--;}else {i=k;}swapVideo();}";
				videoReport = videoReport + "function swapVideo(){var film = document.getElementById(\"videoId\");var el = document.getElementById(\"mydiv\");el.innerHTML=video[i];var source= document.getElementById(\"slide\");"; 
				videoReport = videoReport + "film.appendChild(source);film.pause();source.src= video[i];film.load();}";
				videoReport = videoReport + "function addLoadEvent(func) {var oldonload = window.onload;if (typeof window.onload != 'function') {window.onload = func;}else {window.onload = function() {";
				videoReport = videoReport + "if (oldonload) {oldonload();}func();}}}addLoadEvent(function() {swapVideo(); });</script><table border=0 width=85% height=100%><tr>";
				videoReport = videoReport + "<td width=25%%><button style=\"float: right;\" class=\"btn\" onclick=\"decrement()\"><font size='4'>&#9668</font></button></td>";
				videoReport = videoReport + "<td width=50% align=\"center\"><div align=\"center\"><video id=\"videoId\" width=\"542\" height=\"364\" controls>";
				videoReport = videoReport + "<source id=\"slide\" alt=\"No video found\" src=\"" + videos.get(0) + "\" type=\"video/mp4\">";
				videoReport = videoReport + "Your browser does not support the video tag. Please <a href=\"v.mov\">Click here</a> to download.";
				videoReport = videoReport + "</video></div></td><td width=25%><button style=\"float: left;\" class=\"btn\" onclick=\"increment()\"><font size='4'>&#9658</font></button></td></tr>";
				videoReport = videoReport + "<tr><td colspan='3' align=\"center\" style=\"font:small-caps bold 15px georgia; color:#168DDB;\"><div id =\"mydiv\"></div></td></tr></table>";
			} else {
				videoReport = videoReport + "<div align=center style=\"background:#168DDB; color:#FFFFFF; padding: 5px 7px; border-radius: 6px;\">Videos</div>";
				videoReport = videoReport + "<div width=100% style=\"margin-top:120px;\" align=center valign=center>";
				videoReport = videoReport + "<font style=\"font-family:'calibri';\" size='6'>Hurray..!!!<br/>TestScript(s) got passed.</font></div>";
			}
			videoReport = videoReport + "</body></html>";

			File f = new File(path);
			BufferedWriter bw = new BufferedWriter(new FileWriter(f));
			bw.write(videoReport);
			bw.close();
			System.out.println("Videos report created");
		} catch(Exception e) {
			e.getMessage();
		}
	}

	/*
	 * Add contents to buffer for execution health report.
	 */
	public void executionHealthReport(final String module) {
		try {
			String[] str = new String[] {module, ""+passedTests, ""+failedTests, ""+skippedTests};
			list.add(str);
		} catch (Exception e) {
		}
	}

	/*
	 * Get execution health report
	 */
	public void getExecutionHealthReport() {
		try {
			File executionHealthDir = new File(ConfigurationLibrary.detailResultPath);

			if(!executionHealthDir.isDirectory()){
				executionHealthDir.mkdirs();
			}

			String path = executionHealthDir + "/" + "executionHealth.html";


			String executionHealth = "<!DOCTYPE html><html lang=\"en\"><head></head><body bgcolor=\"#FFFFFF\">";
			executionHealth = executionHealth + "<div style=\"margin-top:20px;\"align=\"left\"><canvas id=\"myCanv\" width = 950% height=220%></canvas></div>";
			executionHealth = executionHealth + "<script>var data=["; 

			int listSize = list.size();

			if(listSize == 1) {
				executionHealth = executionHealth + "{text:\"" + list.get(0)[0] + "\", grade1:" + list.get(0)[1] + ", grade2:" + list.get(0)[2] + ", grade3:" + list.get(0)[3] + "}";
			}
			else {
				for(int i=0;i<listSize-1;) {
					boolean status = false;
					while(i<list.size()-1 && list.get(i)[0].equals(list.get(i+1)[0])) {
						String[] string = new String[4];
						string[0] = list.get(i)[0];
						string[1] = String.valueOf((Integer.parseInt(list.get(i+1)[1]) + Integer.parseInt(list.get(i)[1])));
						string[2] = String.valueOf((Integer.parseInt(list.get(i+1)[2]) + Integer.parseInt(list.get(i)[2])));
						string[3] = String.valueOf((Integer.parseInt(list.get(i+1)[3]) + Integer.parseInt(list.get(i)[3])));
						list.set(++i, string);
						status = true;
					}
					executionHealth = executionHealth + "{text:\"" + list.get(i)[0] + "\", grade1:" + list.get(i)[1] + ", grade2:" + list.get(i)[2] + ", grade3:" + list.get(i)[3] + "}";
					if(i<listSize-1){
						executionHealth = executionHealth + ", ";
					}
					if(!status) {
						i++;
					}
				}
			}

			executionHealth = executionHealth + "];var x=0, y=0, i=0, lineWidth=0, length=0, limit=0;var text;";
			executionHealth = executionHealth + "function getSum() {var	mySum = data[i].grade1+data[i].grade2+data[i].grade3 ;return mySum;}";
			executionHealth = executionHealth + "function getDiv() {var div=0;for(i=0;i< data.length;i++) {if(div<getSum())div=getSum();limit=parseInt(((canv.width-190)/div), 10);}}";
			executionHealth = executionHealth + "function addText() {ctx.font=\"15px Arial\";ctx.fillStyle=\"#000000\";if(text!=0)ctx.fillText(text,[x-parseInt((length/2), 10)],y+18);}";
			executionHealth = executionHealth + "function addBar() {length = limit*text;ctx.fillRect(x,y,length,25);x += length;addText();}";
			executionHealth = executionHealth + "function getWidth() {if(lineWidth<x)lineWidth = x-115;}";
			executionHealth = executionHealth + "function plotBar() {canv = document.getElementById(\"myCanv\");ctx = canv.getContext(\"2d\");getDiv();for (i=0;i<data.length;i++) {";
			executionHealth = executionHealth + "x=5, length=0;text=data[i].text+\" --------------\";addText();x=120;ctx.beginPath();ctx.moveTo(x,y);ctx.fillStyle=\"#649421\";";
			executionHealth = executionHealth + "text = data[i].grade1;addBar();ctx.fillStyle=\"#C0292A\" ;text = data[i].grade2;addBar();ctx.fillStyle=\"#F7D10E\";";
			executionHealth = executionHealth + "text = data[i].grade3;addBar();getWidth();text=\" \"+getSum();x = canv.width, length=120;addText();y += 35, x= 120;}";
			executionHealth = executionHealth + "ctx.fillStyle=\"#000000\";ctx.fillRect(x-1,0,0.2,y-5);}plotBar();";
			executionHealth = executionHealth + "</script></body></html>";

			File f = new File(path);
			BufferedWriter bw = new BufferedWriter(new FileWriter(f));
			bw.write(executionHealth);
			bw.close();
			System.out.println("Execution health report created");
		} catch(Exception e) {
			e.getMessage();
		}
	}

	/*
	 * Get total runs report
	 */
	public void getTotalRunsReport() {
		try {
			File totalRunDir = new File(ConfigurationLibrary.detailResultPath);

			if(!totalRunDir.isDirectory()){
				totalRunDir.mkdirs();
			}

			String path = ConfigurationLibrary.imagePath + "/totalRuns.html";


			String totalRuns = "<!DOCTYPE html><html lang=\"en\"><head></head>";
			totalRuns = totalRuns + "<body bgcolor=\"#FFFFFF\"><Style>font.style1 {font-family: 'calibri';font-size: 1em;}";
			totalRuns = totalRuns + "#verticalText {transform: rotate(270deg);transform-origin: left top 0;margin-top: 250px;margin-left:5px;position: fixed;}";
			totalRuns = totalRuns + "</style><div style=\"margin-top:15px;\"></div>";
			totalRuns = totalRuns + "<div style=\"float: left;\" valign='center' id=\"verticalText\" ><font class=\"style1\"><b>Percentage</b></font></div>";
			totalRuns = totalRuns + "<div align=\"left\"><canvas id=\"myCanv\" width = 1000% height=320%></canvas></div>";
			totalRuns = totalRuns + "<table width='980'><tr>";
			totalRuns = totalRuns + "<td><div style=\"margin-left:70px;\"></div></td>";
			totalRuns = totalRuns + "<td width='15%'><font class=\"style1\">Run 1</font></td>";
			totalRuns = totalRuns + "<td width='15%'><font class=\"style1\">Run 2</font></td>";
			totalRuns = totalRuns + "<td width='15%'><font class=\"style1\">Run 3</font></td>";
			totalRuns = totalRuns + "<td width='15%'><font class=\"style1\">Run 4</font></td>";
			totalRuns = totalRuns + "<td width='15%'><font class=\"style1\">Run 5</font></td>";
			totalRuns = totalRuns + "<td width='15%'><font class=\"style1\">Run 6</font></td>";
			totalRuns = totalRuns + "<td width='15%'><font class=\"style1\">Run 7</font></td></tr>";
			totalRuns = totalRuns + "<tr><td align='center' colspan='8'><font class=\"style1\" ><b>Last 5 runs results</b></font></td></font></tr></table>";

			totalRuns = totalRuns + "<script>var data=[ ";



			for(int i=0;i<7;i++) {
				totalRuns = totalRuns + "{grade1:60, grade2:20, grade3:20}";
				if(i!=6){
					totalRuns = totalRuns + ", ";
				}
			}

			totalRuns = totalRuns + "];var x=5, y=0, i=0, lineWidth=0, length=0, limit=0;var text;";

			totalRuns = totalRuns + "function addText() {ctx.font=\"13px Arial\";ctx.fillStyle = \"#000000\";ctx.fillText(text+\"%\",x,length-3);}";
			totalRuns = totalRuns + "function addBar() {y=canv.height;length = y-(limit*text);ctx.fillRect(x,length,25,y);addText();x+=35;}";
			totalRuns = totalRuns + "function getWidth() {if(lineWidth<x)lineWidth = x-115;}";
			totalRuns = totalRuns + "function plotBar() {canv = document.getElementById(\"myCanv\");ctx = canv.getContext(\"2d\");limit=((canv.height-20)/100);";		
			totalRuns = totalRuns + "for (i=0;i<data.length;i++) {x+=32;ctx.beginPath();ctx.moveTo(x,y) ;y=canv.height-20;text = data[i].grade1;ctx.fillStyle = \"#649421\" ;";
			totalRuns = totalRuns + "addBar();text = data[i].grade2;ctx.fillStyle = \"#C0292A\" ;addBar();text = data[i].grade3;ctx.fillStyle = \"#F7D10E\" ;addBar();}";
			totalRuns = totalRuns + "ctx.fillRect(0,canv.height-1,canv.width,0.2);}plotBar() ;</script></body></html>";


			File f = new File(path);
			BufferedWriter bw = new BufferedWriter(new FileWriter(f));
			bw.write(totalRuns);
			bw.close();
			System.out.println("Images report created");
		} catch(Exception e) {
			e.getMessage();
		}
	}

	/*
	 * Mailing reports
	 */
	public void sendEmail() throws Exception 
	{
		final String username = "rahul.radhakrishnan@medecision.com";
		final String password = "Med20150929";
		String from = username;
		String to = "rahul.radhakrishnan@techendeavour.com";
		String cc = "";
		String bcc = "";

		Properties props = new Properties();
		props.put("mail.smtp.auth", true);
		props.put("mail.smtp.starttls.enable", true);
		props.put("mail.smtp.host", "outlook.office365.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.user", username); 
		props.put("mail.smtp.password", password); 

		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try{

			MimeMessage message = new MimeMessage(session);

			message.setFrom(new InternetAddress(from));

			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			message.setRecipients(Message.RecipientType.CC,InternetAddress.parse(cc));
			message.setRecipients(Message.RecipientType.BCC,InternetAddress.parse(bcc));

			message.setSubject(ConfigurationLibrary.projectName+" Project Automation Report");

			BodyPart messageBodyPart = new MimeBodyPart();

			messageBodyPart.setText("Hi Team,"
					+ '\n'+'\n'
					+ "PFA" + ConfigurationLibrary.projectName + " Automation Report along with this email"
					+'\n'+'\n'
					+"Thanks and Regards"
					+'\n'+'\n'
					+"Rahul R");

			Multipart multipart = new MimeMultipart();

			multipart.addBodyPart(messageBodyPart);

			messageBodyPart = new MimeBodyPart();

			Zip zip = new Zip();

			String filePath1 = ConfigurationLibrary.summaryResultPath;
			String filePath2 = filePath1+".zip";

			File file = new File(filePath2);
			if(file.exists())
				file.delete();

			zip.zip(new File(filePath1), new File(filePath2));

			String filename = filePath2;

			DataSource source = new FileDataSource(filename);
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName("summary html report");
			multipart.addBodyPart(messageBodyPart);

			message.setContent(multipart );

			Transport.send(message);
			System.out.println("Sent message successfully....");
		}catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}

	/*
	 * Get file names of specific extension.
	 */
	public List<String> getFileNames(String dirPath, String extension) throws IOException {
		File file = new File(dirPath);
		String[] myFiles;
		List<String> fileNames = new ArrayList<String>();
		if (file.isDirectory()) {
			myFiles = file.list();
			for (int i = 0; i < myFiles.length; i++) {
				if(myFiles[i].toString().endsWith(extension)) {
					fileNames.add(myFiles[i].toString());
				}
			}
		}
		return fileNames;
	}

	
	/*
	 * Get previous report names.//Updated
	 */
	public String[] getPrevReports() throws Exception {
		DateFormat dateFormatter1 = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		DateFormat dateFormatter = new SimpleDateFormat("dd-MMM-yyyy HH-mm-ss");
		File file = new File(ConfigurationLibrary.resultDirectory);
		String[] myFiles = null;
		if (!file.isDirectory()) {
			file.mkdirs();
		}

		myFiles = file.list();
		
		for(int i=0;i<myFiles.length;i++) {
			Date date = dateFormatter.parse(myFiles[i]);
			myFiles[i] = dateFormatter1.format(date.getTime());
		}
		
		Arrays.sort(myFiles, Collections.reverseOrder());
		
		for(int i=0;i<myFiles.length;i++) {
			Date date = dateFormatter1.parse(myFiles[i]);
			myFiles[i] = dateFormatter.format(date.getTime());
		}
		for(int i=10;i<myFiles.length;i++) {
			deleteFiles(ConfigurationLibrary.resultDirectory+ "//" +myFiles[i] + "//result//detailResult");
			deleteFiles(ConfigurationLibrary.resultDirectory+ "//" +myFiles[i] + "//result//videos");
			deleteFiles(ConfigurationLibrary.resultDirectory+ "//" +myFiles[i] + "//result//images");
			deleteFiles(ConfigurationLibrary.resultDirectory+ "//" +myFiles[i] + "//result");
			deleteFiles(ConfigurationLibrary.resultDirectory+ "//" +myFiles[i]);
		}
		
		myFiles = file.list();
		Arrays.sort(myFiles, Collections.reverseOrder());
		
		return myFiles;
	}
	/*
	 * Delete files.
	 */
	public static void deleteFiles(String dirPath) throws IOException {
		File file = new File(dirPath);
		String[] myFiles;
		if (file.isDirectory()) {
			myFiles = file.list();
			for (int i = 0; i < myFiles.length; i++) {
				myFiles[i].toString();
				File myFile = new File(file, myFiles[i]);
				myFile.delete();
			}
			file.delete();
		}
	}
	
	/*
	 * Delete report folder.
	 */
	public static void deleteLatestReportFolder() throws Exception{
		deleteFiles(ConfigurationLibrary.latestDirectory+"//detailResult");
		deleteFiles(ConfigurationLibrary.latestDirectory+"//videos");
		deleteFiles(ConfigurationLibrary.latestDirectory+"//images");
		deleteFiles(ConfigurationLibrary.latestDirectory+"//result");
	}

	/*
	 * Delete report folder.
	 */
	 
	public static void deleteReportFolder() throws Exception{
		deleteFiles(ConfigurationLibrary.detailResultPath);
		deleteFiles(ConfigurationLibrary.imagePath);
		deleteFiles(ConfigurationLibrary.videoPath);
		deleteFiles(ConfigurationLibrary.summaryResultPath);
	}

	
	/*
	 * Copy reports to another folder.//Updated
	 */
	public void copyReports() throws Exception{
		DateFormat dateFormatter1 = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");//
		DateFormat dateFormatter = new SimpleDateFormat("dd-MMM-yyyy HH-mm-ss");
		Date date = dateFormatter.parse(ConfigurationLibrary.executionStartTime);
		File srcDir = new File(ConfigurationLibrary.summaryResultPath);
		if(srcDir.isDirectory()){
			File destDir = new File(ConfigurationLibrary.resultDirectory + "/" + dateFormatter1.format(date.getTime()) + "/result");
			destDir.mkdirs();
			FileUtils.copyDirectory(srcDir, destDir);
		}
	}
	
	/*
	 * Copy reports to another folder.
	 */
	public void copyReportsToLatestFolder() throws Exception{
		DateFormat dateFormatter1 = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");//
		DateFormat dateFormatter = new SimpleDateFormat("dd-MMM-yyyy HH-mm-ss");
		Date date = dateFormatter.parse(ConfigurationLibrary.executionStartTime);
		File srcDir = new File(ConfigurationLibrary.summaryResultPath);
		if(srcDir.isDirectory()){
			File destDir = new File(ConfigurationLibrary.latestDirectory + "/result");
			destDir.mkdirs();
			FileUtils.copyDirectory(srcDir, destDir);
		}
	}

	/*
	 * Create pie chart
	 */
	public String pieChartView() throws Exception
	{
		String filePath = ConfigurationLibrary.detailResultPath +"/PieChart.html";
		int total = ConfigurationLibrary.passCount+ConfigurationLibrary.failCount;
		int passPercentage = (ConfigurationLibrary.passCount*100)/total;
		int failPercentage = (ConfigurationLibrary.failCount*100)/total;

		String htmlCode = "<!DOCTYPE html><html lang=\"en\"><head></head><body bgcolor=\"#FFFFF0\">"
				+"<canvas id=\"myCanv\" width = 210 height=150></canvas><script>"
				+"var data=[ {name: \"Pass: "+passPercentage+"%\", grade:"+ ConfigurationLibrary.passCount +", c:\"#649421\"} ,"
				+"{name: \"Fail: "+failPercentage+"%\", grade:"+ ConfigurationLibrary.failCount +", c:\"#C0292A\"} ];"

			    +"function getSum() {"
			    +"var mySum = 0;"
			    +"for (var i = 0; i < data.length; i++) {mySum += data[i].grade ;}"
			    +"return mySum;}"

				+"function plotPie() {"
				+"var x=20, x0=180, y=y0=90, r=90, angle1=0;"
				+"canv = document.getElementById(\"myCanv\");"
				+"ctx = canv.getContext(\"2d\");"
				+"var mySum = getSum();"
				+"for (var i = 0; i < data.length; i++) {"
				+"ctx.strokeStyle = data[i].c ;"
				+"ctx.fillStyle = data[i].c ;"
				+"angle2 = Math.PI * 2 * data[i].grade / mySum ;"
				+"ctx.beginPath();"
				+"ctx.moveTo(x0,y0) ;"
				+"ctx.arc(x0,y0,r, angle1, angle1 + angle2-0.00, false) ;"
				+"ctx.fill ();"
				+"angle1 += angle2 ;"
				+"ctx.font=\"12px Arial\";"
				+"ctx.fillRect(x,y+40,5,5);"
				+"ctx.fillText(data[i].name,x,y+48);"
				+"y += 15;}}"

				+"plotPie() ;"
				+"</script></body></html>";

		File f = new File(filePath);
		BufferedWriter bw = new BufferedWriter(new FileWriter(f));
		bw.write(htmlCode);
		bw.close();
		if(System.getProperty("os.name").replaceAll("[^A-Za-z]+", "").equalsIgnoreCase("Windows")){
			Process p = Runtime.getRuntime().exec("attrib +h " + filePath);
			p.waitFor();
		}

		return htmlCode;	
	}
}

