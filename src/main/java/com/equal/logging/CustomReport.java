package com.equal.logging;

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
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.Zip;

import com.equal.common.Driver;
import com.equal.common.Logger;


public class CustomReport {

    String htmlheader;
    String bodyHeader;
    String htmlfooter;
    BufferedWriter fileObj;
    FileWriter fileHTML;
    public int stepNo = 1;

    public static String pass = "&#10004";

    public static String fail = "&#10008";
    public static long executionStartTime = System.currentTimeMillis();
    public static long executionEndTime = System.currentTimeMillis();
    public static int passCount = 0;
    public static int failCount = 0;
    public static int skipCount = 0;


    //According to number of @test methods inside java file
    public static int failedTests = 0;

    public static int passedTests = 0;

    public static int skippedTests = 0;

    public static boolean testStatus = true;

    public long scriptStartTime = 0l;

    private static int imagesCount = 0;

    private static int videosCount = 0;

    private static final char NEWLINE = '\n';

    private static final long MAX_BUFFER_SIZE = 1024;

    private static StringBuilder resultBuffer = new StringBuilder();

    private static List<String[]> list = new ArrayList<String[]>();

    public CustomReport() {
        Logger.logDebug("Custom report is created");
    }

    public void createTestReport(String testCaseName) throws Exception {

        File detailResultDir = new File("LoggerScreenshots");

        if (!detailResultDir.isDirectory()) {
            detailResultDir.mkdirs();
        }

        String path = detailResultDir + "/" + testCaseName + ".html";

        File testCaseFile = new File(path);

        if (testCaseFile.exists())
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
            e.printStackTrace();
        }
    }

    public void writeIntoFile(WebDriver driver, String testCaseName, String stepSummary, String stepDecription, String ActualResult, String stepStatus, String string, String timeStamp) {

        try {
            String htmlBody = "<tr><td border = none colspan = 4><font color = #168DDB class=\"style1\"><b>" + "Step " + (stepNo++) + ": " + stepSummary + "</b></font></td></tr>";
            htmlBody = htmlBody + "<tr><td width = 35% border = none><font class=\"style1\"> " + stepDecription + "</font></td>";
            htmlBody = htmlBody + "<td align=center width=35% Border = 0><font class=\"style1\"> " + ActualResult + "</font></td>";
            if (stepStatus.equals(fail)) {
                testStatus = false;
                timeStamp = timeStamp.replaceAll(" ", "_").replaceAll(":", "_");
                captureScreenshot(driver, testCaseName + "_" + timeStamp);
                String imgLink = "<a style ='text-decoration: none; color: #C0292A; text-align: center;' href=\"" + testCaseName + "_" + timeStamp + ".jpg\"><div width=100%>";
                htmlBody = htmlBody + "<td align = center width = 10%><font color = #C0292A class=\"style1\">" + imgLink + stepStatus + "</div></a></font></td>";
                htmlBody = htmlBody + "<td align = center width = 20%><font class=\"style1\"> " + string + "</font></td>";
            } else {

                htmlBody = htmlBody + "<td align = center width = 10%><font color = #3FCE30 class=\"style1\">" + stepStatus + "</font></td>";
                htmlBody = htmlBody + "<td align = center width = 20%><font class=\"style1\"> " + string + "</font></td>";
            }
            fileObj.write(htmlBody);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeFile() throws Exception {
        htmlfooter = "</table></body></html>";
        fileObj.write(htmlfooter);
        fileObj.close();
    }

    public void writtingSummaryReport() {

        if (resultBuffer.length() > 0) {
            System.out.println("Into Writing");
//			try {
//				pieChartView();
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
            writeResultBufferToFile();
            getImagesReport();
//			getVideosReport();
            getExecutionHealthReport();
            getDashBoardReport();
        }
    }

    /*
     * Get Dash-board html.
     */
    public void getDashBoardReport() {
        executionEndTime = System.currentTimeMillis();
        try {
            File dashboardDir = new File("LoggerScreenshots");
            int total = passCount + failCount + skipCount;
            int passPercentage = ((passCount) * 100) / total;
            int failPercentage = ((failCount) * 100) / total;
            int skipPercentage = ((skipCount) * 100) / total;

            if (!dashboardDir.isDirectory()) {
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
            dashBoard = dashBoard + "<td id=\"rowheight1\" align=center width=17%><a href=\"https://www.medecision.com/\"><img width=90% height=60% src=\"data:image/png;base64,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\"/></a></td>";
            dashBoard = dashBoard + "<td width=83%><div class=\"center\" ><font size=5 face='calibri'><b>" + "Test Automation" + "Testing Dashbaord" + "</b></font></div>";
            dashBoard = dashBoard + "<div style=\"float:right;\"><font size=3  face='calibri'>" + "Executed on:" + getExecutionTime(0l, executionStartTime) + "</font></div></td></tr>";
            dashBoard = dashBoard + "<tr><td rowspan=\"4\" bgcolor=\"#FFFFFF\" valign=top width=17%><div style=\"margin-top: 12px;\"></div>";
//			try {
//				
//		
//			String[] prevReports = getPrevReports();
//			dashBoard = dashBoard + "<a href=\"#hide1\" class=\"hide animate\" id=\"hide1\"><div class=\"space btn\"><table width=100%><tr><td align='left'><font class=\"style1\" style=\"padding-left: 10px;\">Previous Reports</font></td><td align='right'><span class=\"badge\">" + prevReports.length + "</span></td></tr></table></div></a>";
//			dashBoard = dashBoard + "<a href=\"#show1\" class=\"show animate\" id=\"show1\"><div class=\"space btn\"><table width=100%><tr><td align='left'><font class=\"style1\" style=\"padding-left: 10px;\">Previous Reports</font></td><td align='right'><span class=\"badge\">" + prevReports.length + "</span></td></tr></table></div></a>";
//			if(prevReports.length>0) {
//				dashBoard = dashBoard + "<div class=\"list\"><ul><li><a href=\"../../../" + executionStartTime + "/result/DashBoard.html\" style=\"text-decoration: none;\"><font style=\"color: #168DDB;\" class=\"style1\">" + executionStartTime + "</font></a></li></ul></div>";
//				for(int i=0;i<prevReports.length;i++) {
//					dashBoard = dashBoard + "<div class=\"list\"><ul><li><a href=\"../../../" + prevReports[i] + "/result/DashBoard.html\" style=\"text-decoration: none;\"><font style=\"color: #168DDB;\" class=\"style1\">" + prevReports[i] + "</font></a></li></ul></div>";
//				}
//			}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
            dashBoard = dashBoard + "<a class=\"animate\" href=\"images.html\" target=\"frame1\"><div class=\"space btn\"><table width=100%><tr><td align='left'><font class=\"style1\" style=\"padding-left: 10px;\">Images</font></td><td align='right'><span class=\"badge\">" + imagesCount + "</span></td></tr></table></div></a>";
            dashBoard = dashBoard + "<a class=\"animate\" href=\"../result/videos/videos.html\" target=\"frame1\"><div class=\"space btn\"><table width=100%><tr><td align='left'><font class=\"style1\" style=\"padding-left: 10px;\">Videos</font></td><td align='right'><span class=\"badge\">" + videosCount + "</span></td></tr></table></div></a>";
            dashBoard = dashBoard + "<a class=\"animate\" href=\"executionHealth.html\" target=\"frame1\"><div class=\"space btn\"><table width=100%><tr><td align='left'><font class=\"style1\" style=\"padding-left: 10px;\">Execution Health</font></td></tr></table></div></a>";
            //dashBoard = dashBoard + "<a class=\"animate\" href=\"../result/detailResult/totalRuns.html\" target=\"frame1\"><div class=\"space btn\"><table width=100%><tr><td align='left'><font class=\"style1\" style=\"padding-left: 10px;\">Total Runs</font></td></tr></table></div></a>";
            dashBoard = dashBoard + "</td><td id=\"rowheight2\" width=80%>";
            dashBoard = dashBoard + "<table align=center width = 91% cellspacing=10><tr><td align='center' width=24%>";
            dashBoard = dashBoard + "<div align = 'center' style=\"border:2px; background:#649421; padding:0.5em;\"><font class=\"style1\"><b class=\"space\">Execution Start Time</b><br/>" + getExecutionTime(0l, executionStartTime) + "</font></div></td>";
            dashBoard = dashBoard + "<td align='center' width=24%><div align = 'center' style=\"border:2px; background:#6AC66A; padding:0.5em;\"><font class=\"style1\"><b class=\"space\">Execution End Time</b><br/>" + getExecutionTime(0l, executionEndTime) + "</font></div></td>";
            dashBoard = dashBoard + "<td align='center' width=24%><div align = 'center' style=\"border:2px; background:#2EB1CE; padding:0.5em;\"><font class=\"style1\"><b class=\"space\">Total Time</b><br/>" + getExecutionTime(executionStartTime, executionEndTime) + "</font></div></td>";
            dashBoard = dashBoard + "<td align='center' width=28% rowspan=\"2\"><canvas id=\"myCanv\" width = 300% height = 140%></canvas>";
            dashBoard = dashBoard + "<script>var data=[{name: \"Pass: " + passPercentage + "%\", grade:" + passCount + ", c:\"#649421\"} ,{name: \"Fail: " + failPercentage + "%\", grade:" + failCount + ", c:\"#C0292A\"}, {name: \"Skip: " + skipPercentage + "%\", grade:" + skipCount + ", c:\"#F5D103\"}];";
            dashBoard = dashBoard + "function getSum() {var mySum = 0;for (var i = 0; i < data.length; i++) {mySum += data[i].grade ;}return mySum;}";
            dashBoard = dashBoard + "function plotPie() {var x=[(myCanv.width)]-[((myCanv.width)*42)/100], y=[(myCanv.height)/2], y0=10, x0=[(myCanv.width)]-[((myCanv.width)*70)/100], r=y, angle1=0;";
            dashBoard = dashBoard + "canv = document.getElementById(\"myCanv\");ctx = canv.getContext(\"2d\");var mySum = getSum();for (var i = 0; i < data.length; i++) {ctx.strokeStyle = data[i].c ;";
            dashBoard = dashBoard + "ctx.fillStyle = data[i].c ;angle2 = (Math.PI * 2 * data[i].grade) / mySum ;ctx.beginPath();ctx.moveTo(x0,y) ;ctx.arc(x0,y,r, angle1, angle1 + angle2-0.00, false) ;";
            dashBoard = dashBoard + "ctx.fill ();angle1 += angle2 ;ctx.font=\"12px Arial\";ctx.fillRect(x,y0,5,5);ctx.fillText(data[i].name,x+10,y0+8);y0+=18;}}plotPie() ;";
            dashBoard = dashBoard + "</script></td></tr>";
            dashBoard = dashBoard + "<tr><td align='center' width=24%><div align = 'center' style=\"border:2px; background:#8FD32D; padding:0.5em;\"><font class=\"style1\"><b class=\"space\">Testscripts Executed</b><br/>" + total + "</font></div></td>";
            dashBoard = dashBoard + "<td align='center' width=24%><div align = 'center' style=\"border:2px; background:#649421; padding:0.5em;\"><font class=\"style1\"><b class=\"space\">Test Pass</b><br/>" + passCount + "</font></div></td>";
            dashBoard = dashBoard + "<td align='center' width=24%><div align = 'center' style=\"border:2px; background:#C0292A; padding:0.5em;\"><font class=\"style1\"><b class=\"space\">Test Fail</b><br/>" + failCount + "</font></div></td></tr></table>";
            dashBoard = dashBoard + "</td></tr>";
            dashBoard = dashBoard + "<tr><td id=\"rowheight3\" onload=\"value()\" align=left><iframe id=\"frame1\" name=\"frame1\" scrolling=\"yes\" style=\"border:none; margin-top:15px; margin-left:20px;\" height=100% width=95% src=\"Summary.html\"></iframe></td></tr></table>";
            dashBoard = dashBoard + "<footer id=\"rowheight4\"><font color = #1E90FF face='calibri' size= '2'>Copyright � 2015 <a href=\"https://www.medecision.com/\">Medecision</a>. All Rights Reserved.</font></footer>";
            dashBoard = dashBoard + "<a class=\"animate\" href=\"DashBoard.html\"><div left' class=\"home\"><img width=28px height=25px alt=\"Home\" src=\"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAeAAAAGMCAYAAAAC4nG0AAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAyJpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuMC1jMDYxIDY0LjE0MDk0OSwgMjAxMC8xMi8wNy0xMDo1NzowMSAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvIiB4bWxuczp4bXBNTT0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL21tLyIgeG1sbnM6c3RSZWY9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9zVHlwZS9SZXNvdXJjZVJlZiMiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENTNS4xIFdpbmRvd3MiIHhtcE1NOkluc3RhbmNlSUQ9InhtcC5paWQ6NEI2QUZBMTQ4MURFMTFFMkFEOTRENzY3MTRBREYzQTgiIHhtcE1NOkRvY3VtZW50SUQ9InhtcC5kaWQ6NEI2QUZBMTU4MURFMTFFMkFEOTRENzY3MTRBREYzQTgiPiA8eG1wTU06RGVyaXZlZEZyb20gc3RSZWY6aW5zdGFuY2VJRD0ieG1wLmlpZDo0QjZBRkExMjgxREUxMUUyQUQ5NEQ3NjcxNEFERjNBOCIgc3RSZWY6ZG9jdW1lbnRJRD0ieG1wLmRpZDo0QjZBRkExMzgxREUxMUUyQUQ5NEQ3NjcxNEFERjNBOCIvPiA8L3JkZjpEZXNjcmlwdGlvbj4gPC9yZGY6UkRGPiA8L3g6eG1wbWV0YT4gPD94cGFja2V0IGVuZD0iciI/PkhYClwAAD+8SURBVHja7Z13uF1F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\"/></div></a></body></html>";


            File f = new File(path);
            BufferedWriter bw = new BufferedWriter(new FileWriter(f));
            bw.write(dashBoard);
            bw.close();
            System.out.println("Dashboard report created");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /*
     * Add contents to buffer for summary report.
     */
    public void summaryReport(final String module, final String tcName, final String scriptExecutionTime, final String status) {
        try {

            if (resultBuffer.length() > 0) {
                resultBuffer.append(NEWLINE);
            }

            resultBuffer.append(" " + module + " ~ " + tcName + " ~ " + scriptExecutionTime + " ~ " + status + "`");

            if (resultBuffer.length() < MAX_BUFFER_SIZE) {
                return;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     * Get summary report
     */
    private synchronized void writeResultBufferToFile() {
        System.out.println("*******" + executionStartTime + "*******" + executionEndTime + "*******");

        try {

            File summaryResultDir = new File("LoggerScreenshots");

            if (!summaryResultDir.isDirectory()) {
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
                        if (data[i].trim().equals(pass)) {
                            String fileLink = "<a style ='text-decoration : none ; color: #3FCE30; text-align: center;' href= \"" + data[1].trim() + ".html\"  title='View detail result' ><div width=100%>";
                            bodyHeader = bodyHeader + "<td width = 10% align = center><font class=\"style1\"> " + fileLink + data[i].trim() + "</div></a></font></td>";
                        } else {
                            String fileLink = "<a style ='text-decoration : none ; color: #C0292A; text-align: center;' href= \"" + data[1].trim() + ".html\"  title='View detail result' ><div width=100%>";
                            bodyHeader = bodyHeader + "<td width = 10% align = center><font class=\"style1\"> " + fileLink + data[i].trim() + "</div></a></font></td>";
                        }
                    } else if (i == data.length - 2)
                        bodyHeader = bodyHeader + "<td width = 10% align=center><pre><font class=\"style1\"> " + data[i].trim() + "</font></pre></td>";
                    else if (i == data.length - 3)
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
            e.printStackTrace();
        }
    }

    /*
     * Get images report
     */
    public void getImagesReport() {
        try {
            File imageDir = new File("LoggerScreenshots");

            if (!imageDir.isDirectory()) {
                imageDir.mkdirs();
            }

            String path = imageDir + "/" + "images.html";

//			FileUtils.copyFile(new File("src//..//images//home.png"), new File("LoggerScreenshots"+"/home.png"));
//			FileUtils.copyFile(new File("src//..//images//Endeavour.png"), new File("LoggerScreenshots"+"/Endeavour.png"));

            String imageReport = "<!DOCTYPE html><html><body>";
            List<String> images = getFileNames("LoggerScreenshots", ".jpg");
            imagesCount = images.size();
            if (imagesCount > 0) {
                imageReport = imageReport + "<style>.btn{display:inline-block;padding:6px 12px;font-size:14px;font-weight:400;line-height:309px;text-align:center;white-space:nowrap;vertical-align:middle;";
                imageReport = imageReport + "-ms-touch-action:manipulation;touch-action:manipulation;-webkit-user-select:none;-moz-user-select:none;-ms-user-select:none;user-select:none;background:#ffffff;";
                imageReport = imageReport + "border:0px solid transparent;border-radius:2px}</style>";
                imageReport = imageReport + "<script type=\"text/javascript\">var i = 0;var image = new Array();";


                for (int i = 0; i < imagesCount; i++) {
                    imageReport = imageReport + "image[" + i + "] = \"" + images.get(i) + "\";";
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     * Get videos report
     */
//	public void getVideosReport() {
//		try {
//			File videoDir = new File(ConfigurationLibrary.videoPath);
//
//			if(!videoDir.isDirectory()){
//				videoDir.mkdirs();
//			}
//
//			String path = videoDir + "/" + "videos.html";
//
//
//			String videoReport = "<!DOCTYPE html><html><body>";
//			List<String> videos = getFileNames(ConfigurationLibrary.videoPath, ".mov");
//			videosCount = videos.size();
//			if(videosCount>0) {
//				videoReport = videoReport +	"<style>";
//				videoReport = videoReport + ".btn{display:inline-block;padding:6px 12px;font-size:14px;font-weight:400;line-height:309px;text-align:center;white-space:nowrap;vertical-align:middle;-ms-touch-action:manipulation;";
//				videoReport = videoReport + "touch-action:manipulation;-webkit-user-select:none;-moz-user-select:none;-ms-user-select:none;user-select:none;background:#ffffff;border:0px solid transparent;border-radius:2px}</style>";
//
//				videoReport = videoReport + "<script type=\"text/javascript\"> var i = 0;var video = new Array();";
//
//				for(int i = 0 ; i < videosCount; i++) {
//					videoReport = videoReport + "video[" + i + "] = \""+ videos.get(i) + "\";"; 
//				}
//
//				videoReport = videoReport + "var k = video.length-1;";
//				videoReport = videoReport + "function increment() {if(i<k) {i++;}else {i=0;}swapVideo();}";
//				videoReport = videoReport + "function decrement() {if(i>0) {i--;}else {i=k;}swapVideo();}";
//				videoReport = videoReport + "function swapVideo(){var film = document.getElementById(\"videoId\");var el = document.getElementById(\"mydiv\");el.innerHTML=video[i];var source= document.getElementById(\"slide\");"; 
//				videoReport = videoReport + "film.appendChild(source);film.pause();source.src= video[i];film.load();}";
//				videoReport = videoReport + "function addLoadEvent(func) {var oldonload = window.onload;if (typeof window.onload != 'function') {window.onload = func;}else {window.onload = function() {";
//				videoReport = videoReport + "if (oldonload) {oldonload();}func();}}}addLoadEvent(function() {swapVideo(); });</script><table border=0 width=85% height=100%><tr>";
//				videoReport = videoReport + "<td width=25%%><button style=\"float: right;\" class=\"btn\" onclick=\"decrement()\"><font size='4'>&#9668</font></button></td>";
//				videoReport = videoReport + "<td width=50% align=\"center\"><div align=\"center\"><video id=\"videoId\" width=\"542\" height=\"364\" controls>";
//				videoReport = videoReport + "<source id=\"slide\" alt=\"No video found\" src=\"" + videos.get(0) + "\" type=\"video/mp4\">";
//				videoReport = videoReport + "Your browser does not support the video tag. Please <a href=\"v.mov\">Click here</a> to download.";
//				videoReport = videoReport + "</video></div></td><td width=25%><button style=\"float: left;\" class=\"btn\" onclick=\"increment()\"><font size='4'>&#9658</font></button></td></tr>";
//				videoReport = videoReport + "<tr><td colspan='3' align=\"center\" style=\"font:small-caps bold 15px georgia; color:#168DDB;\"><div id =\"mydiv\"></div></td></tr></table>";
//			} else {
//				videoReport = videoReport + "<div align=center style=\"background:#168DDB; color:#FFFFFF; padding: 5px 7px; border-radius: 6px;\">Videos</div>";
//				videoReport = videoReport + "<div width=100% style=\"margin-top:120px;\" align=center valign=center>";
//				videoReport = videoReport + "<font style=\"font-family:'calibri';\" size='6'>Hurray..!!!<br/>TestScript(s) got passed.</font></div>";
//			}
//			videoReport = videoReport + "</body></html>";
//
//			File f = new File(path);
//			BufferedWriter bw = new BufferedWriter(new FileWriter(f));
//			bw.write(videoReport);
//			bw.close();
//			System.out.println("Videos report created");
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
//	}
//
    /*
	 * Add contents to buffer for execution health report.
	 */
    public void executionHealthReport(final String module) {
        try {
            String[] str = new String[]{module, "" + passedTests, "" + failedTests, "" + skippedTests};
            list.add(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     * Get execution health report
     */
    public void getExecutionHealthReport() {
        try {
            File executionHealthDir = new File("LoggerScreenshots");

            if (!executionHealthDir.isDirectory()) {
                executionHealthDir.mkdirs();
            }

            String path = executionHealthDir + "/" + "executionHealth.html";


            String executionHealth = "<!DOCTYPE html><html lang=\"en\"><head></head><body bgcolor=\"#FFFFFF\">";
            executionHealth = executionHealth + "<div style=\"margin-top:20px;\"align=\"left\"><canvas id=\"myCanv\" width = 950% height=220%></canvas></div>";
            executionHealth = executionHealth + "<script>var data=[";

            int listSize = list.size();

            if (listSize == 1) {
                executionHealth = executionHealth + "{text:\"" + list.get(0)[0] + "\", grade1:" + list.get(0)[1] + ", grade2:" + list.get(0)[2] + ", grade3:" + list.get(0)[3] + "}";
            } else {
                for (int i = 0; i < listSize - 1; ) {
                    boolean status = false;
                    while (i < list.size() - 1 && list.get(i)[0].equals(list.get(i + 1)[0])) {
                        String[] string = new String[4];
                        string[0] = list.get(i)[0];
                        string[1] = String.valueOf((Integer.parseInt(list.get(i + 1)[1]) + Integer.parseInt(list.get(i)[1])));
                        string[2] = String.valueOf((Integer.parseInt(list.get(i + 1)[2]) + Integer.parseInt(list.get(i)[2])));
                        string[3] = String.valueOf((Integer.parseInt(list.get(i + 1)[3]) + Integer.parseInt(list.get(i)[3])));
                        list.set(++i, string);
                        status = true;
                    }
                    executionHealth = executionHealth + "{text:\"" + list.get(i)[0] + "\", grade1:" + list.get(i)[1] + ", grade2:" + list.get(i)[2] + ", grade3:" + list.get(i)[3] + "}";
                    if (i < listSize - 1) {
                        executionHealth = executionHealth + ", ";
                    }
                    if (!status) {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     * Get total runs report
     */
    public void getTotalRunsReport() {
        try {
            File totalRunDir = new File("LoggerScreenshots");

            if (!totalRunDir.isDirectory()) {
                totalRunDir.mkdirs();
            }

            String path = "LoggerScreenshots" + "/totalRuns.html";


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


            for (int i = 0; i < 7; i++) {
                totalRuns = totalRuns + "{grade1:60, grade2:20, grade3:20}";
                if (i != 6) {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     * Mailing reports
     */
    public void sendEmail() throws Exception {
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

        try {

            MimeMessage message = new MimeMessage(session);

            message.setFrom(new InternetAddress(from));

            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(cc));
            message.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(bcc));

            message.setSubject("Automation - mede" + " Project Automation Report");

            BodyPart messageBodyPart = new MimeBodyPart();

            messageBodyPart.setText("Hi Team,"
                    + '\n' + '\n'
                    + "PFA" + "Automation - Equal" + " Automation Report along with this email"
                    + '\n' + '\n'
                    + "Thanks and Regards"
                    + '\n' + '\n'
                    + "Salil Kar");

            Multipart multipart = new MimeMultipart();

            multipart.addBodyPart(messageBodyPart);

            messageBodyPart = new MimeBodyPart();

            Zip zip = new Zip();

            String filePath1 = "LoggerScreenshots";
            String filePath2 = filePath1 + ".zip";

            File file = new File(filePath2);
            if (file.exists())
                file.delete();

            zip.zip(new File(filePath1), new File(filePath2));

            String filename = filePath2;

            DataSource source = new FileDataSource(filename);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName("summary html report");
            multipart.addBodyPart(messageBodyPart);

            message.setContent(multipart);

            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
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
                if (myFiles[i].toString().endsWith(extension)) {
                    fileNames.add(myFiles[i].toString());
                }
            }
        }
        return fileNames;
    }


    /*
     * Get previous report names. Update
     */
    public String[] getPrevReports() throws Exception {
        DateFormat dateFormatter1 = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        DateFormat dateFormatter = new SimpleDateFormat("dd-MMM-yyyy HH-mm-ss");
        File file = new File("LoggerScreenshots");
        String[] myFiles = null;
        if (!file.isDirectory()) {
            file.mkdirs();
        }

        myFiles = file.list();

        Arrays.sort(myFiles, Collections.reverseOrder());


        for (int i = 10; i < myFiles.length; i++) {
            deleteFiles("LoggerScreenshots" + "//" + myFiles[i] + "//result//detailResult");
            deleteFiles("LoggerScreenshots" + "//" + myFiles[i] + "//result//videos");
            deleteFiles("LoggerScreenshots" + "//" + myFiles[i] + "//result//images");
            deleteFiles("LoggerScreenshots" + "//" + myFiles[i] + "//result");
            deleteFiles("LoggerScreenshots" + "//" + myFiles[i]);
        }
        myFiles = file.list();
        Arrays.sort(myFiles, Collections.reverseOrder());

        for (int i = 0; i < myFiles.length; i++) {
            Date date = dateFormatter1.parse(myFiles[i]);
            myFiles[i] = dateFormatter.format(date.getTime());
        }

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
    public static void deleteLatestReportFolder() throws Exception {
        deleteFiles("LoggerScreenshots" + "//detailResult");
        deleteFiles("LoggerScreenshots" + "//videos");
        deleteFiles("LoggerScreenshots" + "//images");
        deleteFiles("LoggerScreenshots" + "//result");
    }

	/*
	 * Delete report folder.
	 */

//	public static void deleteReportFolder() throws Exception{
//		deleteFiles("LoggerScreenshots");
//		deleteFiles(ConfigurationLibrary.imagePath);
//		deleteFiles(ConfigurationLibrary.videoPath);
//		deleteFiles(ConfigurationLibrary.summaryResultPath);
//	}

    /*
     * Copy reports to another folder.
     */
    public void copyReports() throws Exception {
        DateFormat dateFormatter1 = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");//
        DateFormat dateFormatter = new SimpleDateFormat("dd-MMM-yyyy HH-mm-ss");
        Date date = dateFormatter.parse("LoggerScreenshots");
        File srcDir = new File("LoggerScreenshots");
        if (srcDir.isDirectory()) {
            File destDir = new File("LoggerScreenshots" + "/" + dateFormatter1.format(date.getTime()) + "/result");
            destDir.mkdirs();
            FileUtils.copyDirectory(srcDir, destDir);
        }
    }

    /*
     * Copy reports to another folder.
     */
    public void copyReportsToLatestFolder() throws Exception {
        DateFormat dateFormatter1 = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");//
        DateFormat dateFormatter = new SimpleDateFormat("dd-MMM-yyyy HH-mm-ss");
        Date date = new Date(executionStartTime);
        File srcDir = new File("LoggerScreenshots");
        if (srcDir.isDirectory()) {
            File destDir = new File("LoggerScreenshots" + "/result");
            destDir.mkdirs();
            FileUtils.copyDirectory(srcDir, destDir);
        }
    }

    /*
     * Create pie chart
     */
    public String pieChartView() throws Exception {
        String filePath = "LoggerScreenshots" + "/PieChart.html";
        int total = passCount + failCount;
        int passPercentage = (passCount * 100) / total;
        int failPercentage = (failCount * 100) / total;

        String htmlCode = "<!DOCTYPE html><html lang=\"en\"><head></head><body bgcolor=\"#FFFFF0\">"
                + "<canvas id=\"myCanv\" width = 210 height=150></canvas><script>"
                + "var data=[ {name: \"Pass: " + passPercentage + "%\", grade:" + passCount + ", c:\"#649421\"} ,"
                + "{name: \"Fail: " + failPercentage + "%\", grade:" + failCount + ", c:\"#C0292A\"} ];"

                + "function getSum() {"
                + "var mySum = 0;"
                + "for (var i = 0; i < data.length; i++) {mySum += data[i].grade ;}"
                + "return mySum;}"

                + "function plotPie() {"
                + "var x=20, x0=180, y=y0=90, r=90, angle1=0;"
                + "canv = document.getElementById(\"myCanv\");"
                + "ctx = canv.getContext(\"2d\");"
                + "var mySum = getSum();"
                + "for (var i = 0; i < data.length; i++) {"
                + "ctx.strokeStyle = data[i].c ;"
                + "ctx.fillStyle = data[i].c ;"
                + "angle2 = Math.PI * 2 * data[i].grade / mySum ;"
                + "ctx.beginPath();"
                + "ctx.moveTo(x0,y0) ;"
                + "ctx.arc(x0,y0,r, angle1, angle1 + angle2-0.00, false) ;"
                + "ctx.fill ();"
                + "angle1 += angle2 ;"
                + "ctx.font=\"12px Arial\";"
                + "ctx.fillRect(x,y+40,5,5);"
                + "ctx.fillText(data[i].name,x,y+48);"
                + "y += 15;}}"

                + "plotPie() ;"
                + "</script></body></html>";

        File f = new File(filePath);
        BufferedWriter bw = new BufferedWriter(new FileWriter(f));
        bw.write(htmlCode);
        bw.close();
        if (System.getProperty("os.name").replaceAll("[^A-Za-z]+", "").equalsIgnoreCase("Windows")) {
            Process p = Runtime.getRuntime().exec("attrib +h " + filePath);
            p.waitFor();
        }

        return htmlCode;
    }

    public static String getExecutionTime(long executionStartTime, long executionEndTime) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
        SimpleDateFormat timeFor = new SimpleDateFormat("HH:mm:ss");

        Date endTime = new Date(executionEndTime);
        Date startTime = new Date(executionStartTime);
        endTime.setHours(endTime.getHours() - startTime.getHours());
        endTime.setMinutes(endTime.getMinutes() - startTime.getMinutes());
        endTime.setSeconds(endTime.getSeconds() - startTime.getSeconds());

        String date = timeFor.format(endTime);
        return date;
    }

    public static void captureScreenshot(WebDriver driver, String imageName) throws Exception {
        Logger.makeScreenshot(imageName);
//		File scrFile = ((TakesScreenshot) Driver.getCurrentDriver()).getScreenshotAs(OutputType.FILE);
//		FileUtils.copyFile(scrFile, new File("LoggerScreenshots" + "//" + imageName + ".jpg"));
    }
}

