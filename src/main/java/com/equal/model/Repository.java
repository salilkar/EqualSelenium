package com.equal.model;

//import src.com.equal.est.model.String;

//import src.com.equal.est.model.String;

/*
* Variable Name = pageName_subSection_typeOfElement_Identifire_elementName
* Note: Identifier = xpath, id, link etc...
*/
/*
* Page Name
* 1. Home Page = hm
* 2. CMV = cmv
* 3. Interaction management = im
* 4. 
*/
public class Repository {
	
	
	//Common
	public static final String Members = "Members";
	public static final String Programs = "Programs";
	public static final String Requests = "Requests";
		public static final String memberId = "T112";
		public static final String programMemberName = "ALI, AHMED ";
		public static final String requestMemberName = "Baker, Robert";
		public static final String topicCount = "TOPICS (1)";
		public static final String dob = "10/16/1988";
		public static final String effectiveDate = "10/16/2015";
		public static final String terminationDate = "10/16/2017";
		public static final String firstName = "sp";
		public static final String lastName = "dp";
		public static final String newMemberId = "PAT432";
		public static final String subscriberId = "SUB1245";
		public static final String cmv_note="Note Added";
		public static final String prg_note="Program Nore Added";
		public static final String MemberID="BH110415";
		public static final String invalid_MemberID="ACMA01286828KCuiyi";
		public static final String Program_id="PROG-20151104-000007";
		public static final String Invalid_programID="PROG-20151019-00000";		
		public static final String request_id="20151104-000027"; // 20151002-000014
		public static final String Provider_Lastname="R";
		public static final String Provider_Firstname="Rahul";
		
	//Login page
	public static final String et_username = "loginForm:userName";
	public static final String et_password = "loginForm:password";
	public static final String btn_login = "loginForm:loginButton";
	public static final String txt_login="//span[text()='Log In']";
	public static final String txt_xp_logout="//span[text()='Log Out']";
	public static final String txt_xp_help = "//span[text()='Help']";

	//Home page
	public static final String btn_signout = "commandBarForm:masterCommandMenuButton_button";
	public static final String btn_search = "commandBarForm:searchMenu_button";
	public static final String txt_logout="//span[text()='Log Out']";
	public static final String txt_taskcount="tasksCount";
	public static final String txt_taskcommandlink_id="tasksCommandLink";
	public static final String txt_id_programscommandlink="programsCommandLink";
	public static final String txt_id_programscount="programsCount";
	public static final String txt_id_requestscommandlink="requestsCommandLink";
	public static final String txt_id_requestscount="requestsCount";
	public static final String txt_id_topicscommandlink="topicsCommandLink";
	public static final String lnl_taskTab_id_firstRow_GoToCmv = "tasksForm:taskTable:0:dynamicColumns:1:cmvLink";
	public static final String txt_id_topicscount="topicsCount";
	public static final String hm_taskTab_txt_xp_membername = "//tr[1]/td[5]";
	public static final String hm_taskTab_txt_xp_cmvImg= "//a[@id='tasksForm:taskTable:0:dynamicColumns:1:cmvLink']";
	public static final String lnl_requestTab_id_firstRow_GoToCmv = "requestsForm:requestTable:0:j_idt342:1:cmvLink";
    public static final String btn_welcome = "commandBarForm:masterCommandMenuButton_button";
    public static final String hm_taskTab_et_id_memberName = "tasksForm:taskTable:dynamicColumns:4:filter";
    public static final String hm_requestTab_et_id_btn_memberName = "requestsForm:requestTable:j_idt342:2:filter";
    public static final String hm_taskTab_txt_xp_memberName = "//tr[1]/td[5]";
    public static final String hm_requestTab_txt_xp_memberName = "//tr[1]/td[2]";
	public static final String hm_xp_search_member="//span[text()='Members']";
	public static final String btn_welcomeMenu = "commandBarForm:masterCommandMenuButton_button";
	public static final String hm_btn_id_search = "commandBarForm:searchMenu_button";
	
	//Interaction management page
	public static final String btn_interactionSearchMember = "interactionManagementForm:interactionsAccordionPanel:searchMembersCommandButton";
	public static final String btn_cancel = "creatMemberForm:cancel";
	//public static final String img_addContactToInteraction = "memberSearchResultsForm:memberSearchResultsTableIM:searchResultsTable:0:addExistingMemberToIMIcon";		
	public static final String img_addContactToInteraction = "memberSearchResultsForm:memberSearchResultsTableIM:searchResultsTable:0:addMemberToIM";
	//public static final String img_addContactToInteraction = "memberSearchResultsForm:memberSearchResultsTable:searchResultsTable:0:inspectIco";		
	public static final String fld_memberName = "interactionManagementForm:interactionsAccordionPanel:memberProviderName";
	public static final String lbl_interactionManagement = "ui-layout-unit-header-title";
	public static final String btn_removeInteraction = "interactionManagementForm:removeInteractionCommandButton";
	public static final String btn_interactionSearchProvider = "interactionManagementForm:interactionsAccordionPanel:searchProvidersCommandButton";
	public static final String btn_interactionReadDisclaimer = "interactionManagementForm:readDisclaimerCommandButton";
	public static final String tb_interactionTopicsTab = "interactionManagementForm:interactionsAccordionPanel:topicsTab";
	public static final String btn_ContinueInArial = "interactionManagementForm:interactionsAccordionPanel:continueInAerialCommandButton";
	public static final String txt_xp_resultMemberId = "//tr[@class='ui-widget-content ui-datatable-even']/td[2]";
	public static final String lst_id_description = "interactionManagementFortopicCountm:interactionsAccordionPanel:topicsAccordionPanel:0:topicDescription_label";
	//public static final String lst_xp_listItem_reviewMemberRequest = "//*[@id='interactionManagementForm:interactionsAccordionPanel:topicsAccordionPanel:0:topicDescription_panel']/div/ul/li[4]";
	public static final String lst_xp_listItem_reviewMemberRequest = "//li[text()='Review Member Request']";
	public static final String im_topicFrame_btn_id_saveAndExit = "saveAndExitInteractionForm:saveAndExitCommandButton";
	public static final String btn_xp_radioBtn_inbound = "//*[@id='interactionManagementForm:interactionsAccordionPanel:interactionRoute']/tbody/tr/td[1]/div/div[2]";
	public static final String lst_id_disposition = "interactionManagementForm:interactionsAccordionPanel:interactionDisposition_label";
	public static final String im_tb_id_topicTabEmpty = "interactionManagementForm:interactionsAccordionPanel:topicsTab";
	public static final String im_tb_id_topicTabOneTopic = "interactionManagementForm:interactionsAccordionPanel:topicsAccordionPanel:0:topicsTab";
	public static final String im_tb_id_manuallyAddTopic = "interactionManagementForm:interactionsAccordionPanel:addTopicCommandButton";
	public static final String im_tb_id_removeAttempt = "interactionManagementForm:interactionsAccordionPanel:topicsAccordionPanel:0:removeAttemptCommandButton";
	public static final String im_tb_id_topic_memberId = "interactionManagementForm:interactionsAccordionPanel:topicsAccordionPanel:0:topicMemberID";
	public static final String im_xp_topicCount = "//a[contains(text(), 'TOPICS')]";
	public static final String im_txt_xp_resultID = "//tbody[@id='memberSearchResultsForm:memberSearchResultsTableIM:searchResultsTable_data']/tr/td[2]";
	
	//Member Detail (New And Update Member Data)
	public static final String md_demographics_et_id_firstName = "memberDetailIdentificationForm:firstname";
	public static final String md_demographics_et_id_lastName = "memberDetailIdentificationForm:lastName";
	public static final String md_demographics_et_id_memberId = "memberDetailIdentificationForm:memberId";
	public static final String md_demographics_et_id_dob = "memberDetailIdentificationForm:birthDate_input";
	public static final String md_demographic_btn_xp_dobCal = "//[@id='memberDetailIdentificationForm:birthDate']/button";
	public static final String md_demographic_radiobtn_xp_dob = "//table[@id='memberDetailIdentificationForm:gender']/tbody/tr/td[1]/div/div[2]";
	public static final String md_demographic_btn_id_active = "memberDetailIdentificationForm:status:j_idt59";
	public static final String md_demographic_btn_id_cancel = "memberDetailIdentificationForm:cancel:j_idt60";
	public static final String md_demographic_btn_id_saveAanExit = "memberDetailIdentificationForm:saveAndExit:j_idt61";
	public static final String md_demographic_btn_id_viwAuditHistory = "memberDetailIdentificationForm:viewAuditHistoryCommandButton";		public static final String md_tab_xp_enrollment = "//span[contains(text(), 'Enrollment')]";
	public static final String md_tab_xp_demographics = "//span[contains(text(), 'Demographics')]";
	public static final String md_enroll_btn_id_add = "memberDetailEnrollmentForm:add";
	public static final String md_enroll_btn_id_saveAndExit = "memberDetailEnrollmentForm:saveAndExit";
	public static final String md_enroll_et_id_subscriberId = "memberDetailEnrollmentForm:newEnrolsubscriberId";
	public static final String md_enroll_list_xp_relationShip = "//span[@class='ui-icon ui-icon-triangle-1-s']";
	public static final String md_enroll_et_id_effectiveDate = "memberDetailEnrollmentForm:effDate_input";
	public static final String md_enroll_relLstItem_xp_dependant = "//li[contains(text(), 'Dependant')]";
	public static final String md_enroll_id_effectiveDateDisplay = "memberDetailEnrollmentForm:enrollmentsTableDataTable:0:text_effDate";
	public static final String md_enroll_id_terminationDateDisplay = "memberDetailEnrollmentForm:enrollmentsTableDataTable:0:text_effDate";
	public static final String md_enroll_id_subscriberIdDisplay = "memberDetailEnrollmentForm:enrollmentsTableDataTable:0:text_subScriber";
		
		
	//Home Page Task Tab
	 public static final String txt_id_due="tasksForm:taskTable:dynamicColumns:2:headerFacetPanelGroup";
	 public static final String txt_id_association="tasksForm:taskTable:dynamicColumns:7:headerFacetPanelGroup";
	 public static final String txt_id_priority="tasksForm:taskTable:dynamicColumns:3:headerFacetPanelGroup";
	 public static final String txt_id_membername="tasksForm:taskTable:dynamicColumns:4:headerFacetPanelGroup";
	 public static final String txt_id_type="tasksForm:taskTable:dynamicColumns:5:headerFacetPanelGroup";
	 public static final String txt_id_reason="tasksForm:taskTable:dynamicColumns:6:headerFacetPanelGroup";
	 public static final String txt_id_timezone="tasksForm:taskTable:dynamicColumns:8:headerFacetPanelGroup";
	 public static final String hm_TaskTab_btn_xp_DueToday="//span[text()='Due Today']";
	 public static final String hm_TaskTab_btn_xp_Overdue="//span[text()='Overdue']";
	 public static final String hm_TaskTab_btn_xp_Range="//span[text()='Range']";
	 public static final String hm_TaskTab_btn_id_PrintSummary="taskFilterForm:printSummaryCommandButton";
	 public static final String hm_TaskTab_btn_id_Reset="taskFilterForm:resetTasks";	
	 public static final String hm_TaskTab_img_id_CMV ="tasksForm:taskTable:0:dynamicColumns:1:cmvLink";
	 public static final String hm_TaskTab_img_id_AddTask = "tasksForm:taskTable:0:dynamicColumns:1:addTaskLink";
	 public static final String hm_TaskTab_img_id_InspectLink="tasksForm:taskTable:0:dynamicColumns:1:inspectIconLink";
	 public static final String hm_TaskTab_img_id_SpocLink = "tasksForm:taskTable:0:dynamicColumns:1:spocLink";
	 public static final String hm_TaskTab_img_id_ReadTask = "tasksForm:taskTable:0:dynamicColumns:1:readTaskStar";
	 public static final String hm_TaskTab_img_id_UnreadTask = "tasksForm:taskTable:7:dynamicColumns:1:unreadTaskStar";
	 public static final String hm_TaskTab_txt_xp_ListTask = "//label[@id='printSummaryForm:j_idt7']";
	 public static final String hm_TaskTab_txt_xp_TotalItems = "//label[@id='printSummaryForm:activityListSize']";
	 public static final String hm_TaskTab_btn_id_close = "printSummaryForm:j_idt5";
	 public static final String hm_TaskTab_btn_xp_Overdue_TableData="//tbody[@id='tasksForm:taskTable_data']/tr/td[3]";
	 public static final String hm_TaskTab_sel_id_filterType = "tasksForm:taskTable:dynamicColumns:5:filter";
	 
	  
	 
	//Programs Tab
	public static final String txt_id_membname = "programsForm:programTable:j_idt302:2:headerFacetPanelGroup";
	public static final  String txt_id_program = "programsForm:programTable:j_idt302:3:headerFacetPanelGroup";
	public static final String tx_id_primaryprovider = "programsForm:programTable:j_idt302:4:headerFacetPanelGroup";
	public static final String txt_id_severity = "programsForm:programTable:j_idt302:5:headerFacetPanelGroup";
	public static final String txt_id_startdate = "programsForm:programTable:j_idt302:6:headerFacetPanelGroup";
	public static final String txt_id_enrolled = "programsForm:programTable:j_idt302:7:headerFacetPanelGroup";
	public static final String txt_id_programId ="programsForm:programTable:j_idt302:8:headerFacetPanelGroup";
	public static final String txt_id_timeZone = "programsForm:programTable:j_idt302:9:headerFacetPanelGroup";
	public static final String hm_ProgramTab_txt_xp_today = "//span[text()='Today']";
	public static final String hm_ProgramTab_txt_xp_priortotoday="//span[text()='Prior to Today']";
	public static final String hm_ProgramTab_txt_xp_Range="//span[text()='Range']";
	public static final String hm_ProgramTab_txt_id_printSummary="programFilterForm:printSummaryCommandButton";
	public static final String hm_ProgramTab_txt_id_Reset="programFilterForm:resetPrograms";
	public static final String hm_ProgramTab_img_id_CMV="programsForm:programTable:0:j_idt302:1:cmvLink";
	public static final String hm_ProgramTab_img_id_InspectLink="programsForm:programTable:0:j_idt302:1:pgmInspectIconLink";
	public static final String hm_ProgramTab_img_id_SpocLink="programsForm:programTable:0:j_idt302:1:spocLink";
	public static final String hm_ProgramTab_txt_xp_membername = "//*[@id='programsForm:programTable_data']/tr[1]/td[3]";
	public static final String hm_ProgramTab_txt_xp_CMV="//*[@id='j_idt233']/div[1]/span[text()='Comprehensive Member View']";
	public static final String hm_ProgramTab_txt_xp_ListTask = "//label[@id='printSummaryForm:j_idt7']";
	public static final String hm_ProgramTab_txt_xp_TotalItems = "//label[@id='printSummaryForm:activityListSize']";
	public static final String hm_ProgramTab_btn_id_close = "printSummaryForm:j_idt5";
	
	
	
	
	//Requests Tab
	
	public static final String txt_id_followup="requestsForm:requestTable:j_idt342:3:headerFacetPanelGroup";
	public static final String txt_id_providerfacility="requestsForm:requestTable:j_idt342:4:headerFacetPanelGroup";
	public static final String txt_id_settings="requestsForm:requestTable:j_idt342:5:headerFacetPanelGroup";
	public static final String txt_id_admitstart="requestsForm:requestTable:j_idt342:6:headerFacetPanelGroup";
	public static final String txt_id_duedate="requestsForm:requestTable:j_idt342:7:headerFacetPanelGroup";
	public static final String txt_id_timeZ="requestsForm:requestTable:j_idt342:3:headerFacetPanelGroup";
	public static final String hm_RequestTab_txt_xp_FollowUpDate="//label[@id='requestFilterForm:j_idt76_label']";
	public static final String hm_RequestTab_txt_xp_today="//span[text()='Today']";
	public static final String hm_RequestTab_txt_xp_Priortotoday="//span[text()='Prior to Today']";
	public static final String hm_RequestTab_txt_xp_Range="//span[text()='Range']";
	public static final String hm_RequestTab_txt_id_PrintSummary="requestFilterForm:printSummaryCommandButton";
	public static final String hm_RequestTab_txt_id_Reset="requestFilterForm:resetRequests";
	public static final String hm_RequestTab_img_id_CMV="requestsForm:requestTable:0:j_idt342:1:cmvLink";
	public static final String hm_RequestTab_img_id_requestInspectIcon="requestsForm:requestTable:0:j_idt342:1:requestInspectIcon";
	public static final String hm_RequestTab_img_id_IconForReferral="requestsForm:requestTable:0:j_idt342:1:requestTypeCodeIconForReferral";	
	public static final String hm_RequestTab_img_id_IconForOutpatient="requestsForm:requestTable:1:j_idt342:1:requestTypeCodeIconForOutpatient";
	public static final String hm_RequestTab_img_id_IconForInpatient="requestsForm:requestTable:25:j_idt342:1:requestTypeCodeIconForInpatient";
	public static final String hm_RequestTab_txt_xp_membername = "//tbody[@id='requestsForm:requestTable_data']/tr[1]/td[3]";
	public static final String hm_RequestTab_txt_xp_CMV="//div[@id='j_idt233']/div[1]/span[text()='Comprehensive Member View']";
	public static final String hm_RequestTab_txt_xp_ListTask = "//label[@id='printSummaryForm:j_idt7']";
	public static final String hm_RequestTab_txt_xp_TotalItems = "//label[@id='printSummaryForm:activityListSize']";
	public static final String hm_RequestTab_btn_id_close = "printSummaryForm:j_idt5";
	public static final String dialog_popup_id = "requestDetailDialog";
	public static final String hm_RequestTab_txt_xp_requestId = "//tbody[@id='cmvCenterForm:requestsAndProgramsAccordionPanel:requestTable_data']/tr[1]/td";
	public static final String hm_RequestTab_xp_calendarbtn ="//span[text()='ui-button']";
	public static final String hm_RequestTab_id_dischargeDate ="dischargeRequestForm:dischargeDate_input";
	public static final String hm_RequestTab_disposition_dd ="dischargeRequestForm:disposition";
	public static final String hm_RequestTab_disposition_selectList ="//li[text()='Home']"; 
	public static final String hm_RequestTab_dischargeRequestBtn ="dischargeRequestForm:dischargeRequestCommandButton";
	public static final String hm_RequestTab_id_calendar_txt = "dischargeRequestForm:dischargeDate_input";
	public static final String hm_ReopenRequest = "cmvCenterForm:cmvItemsTabView:0:cmvReopenRequest";
	
	// Topics Tab
	
	public static final String txt_id_folloupdate="topicsForm:topicTable:j_idt461:1:headerFacetPanelGroup";
	public static final String txt_id_description="topicsForm:topicTable:j_idt461:3:headerFacetPanelGroup";
	public static final String txt_id_ReqProgid="topicsForm:topicTable:j_idt461:4:headerFacetPanelGroup";
	public static final String txt_id_attempts="topicsForm:topicTable:j_idt461:5:headerFacetPanelGroup";
	public static final String hm_TopicTab_img_id_CMV="topicsForm:topicTable:0:j_idt461:0:newInteractionTopicLink";
	public static final String hm_TopicTab_txt_xp_today="//span[text()='Today']";
	public static final String hm_TopicTab_txt_xp_Priortotoday="//span[text()='Prior to Today']";
	public static final String hm_TopicTab_txt_xp_Range="//span[text()='Range']";
	public static final String hm_TopicTab_txt_id_Reset="topicFilterForm:resetTopics";

	
	//CVM Detail screen

	public static final String btn_smartPlanCare = "cmvSpocButtonForm:navigateToSpoc";
	public static final String txt_xp_Memberview="//span[contains(text(), 'Member ID')]";
	public static final String txt_xp_membername="//span[@class='memberName']";
	public static final String txt_xp_memeberdeatils="//span[text()='Member Details']";
	public static final String cmv_txt_xp_MemberId = "//span[contains(text(), 'Member ID')]";
	public static final String cmv_topic_txt_xp_topicCount =  "//a[contains(text(), 'Interaction Topics')]";
	public static final String cmv_task_symbol_xp_goToCmv =  "//a[@aria-label='Go to CMV']";
	public static final String cmv_txt_xp_CombMembView="//span[text()='Comprehensive Member View']";
	public static final String cmv_txt_xp_phonenumber="//ul[@id='memberOverviewForm:memberOverviewAccordionPanel:memberPhoneDataList_list']";
	public static final String cmv_txt_xp_email = "//a[@id='memberOverviewForm:memberOverviewAccordionPanel:memberEmailDataList:0:memberEmailAddressLink']";
	public static final String cmv_btn_id_edit = "memberOverviewForm:memberOverviewAccordionPanel:editMemberDetails";
	public static final String cmv_et_id_email = "memberDetailIdentificationForm:input_personal_email";
	public static final String cmv_et_id_phonenumber = "memberDetailIdentificationForm:input_telHome_mask";
	public static final String cmv_btn_id_save = "memberDetailIdentificationForm:saveAndExit:j_idt61";
	public static final String cmv_btn_interaction = "commandBarForm:interactionButton";
	public static final String cmv_btn_xp_Returninteraction = "//span[contains(text(), 'Return to Interaction')]";
	public static final String cmv_btn_id_Discharge = "cmvCenterForm:cmvItemsTabView:0:cmvDischargeRequest";
	//public static final String cmv_xp_cmvLabel = "//span[contains(Text(),'Comprehensive Member View')]";
	//public static final String cmv_xp_cmvLabel = "//div[@id='j_idt233']/div[1]/span[contains(Text(),'Comprehensive Member View')]";
	//public static final String cmv_xp_cmvLabel = "//*[@id='j_idt233']/div[1]/span[Text()='Comprehensive Member View']";
	public static final String cmv_xp_cmvLabel = "//div[@id='j_idt233']/div[1]/span[text()='Comprehensive Member View']";	
			
	//Member search page
	public static final String et_memberId = "memberSearchTabView:memberIdSearchForm:memberId";
	public static final String btn_memberIdSearch = "memberSearchTabView:memberIdSearchForm:memberIdSearchLocalButton";
	public static final String ms_btn_goToCmv ="memberSearchResultsForm:memberSearchResultsTable:searchResultsTable:0:inspectIcon";
	public static final String ms_tv_memberIdSearchResult = "memberSearchResultsForm:memberSearchResultsTable:searchResultsTable:0:memberDemographics";
	public static final String tb_name_Link  = "#memberSearchTabView:nameSearchTab";
	public static final String tb_id_Link  = "#memberSearchTabView:idSearchTab";
	public static final String txt_xp_memberid="//span[text()='Members']";
	
	public static final String et_lastName = "memberSearchTabView:memberNameSearchForm:lastName";
	public static final String et_firstName = "memberSearchTabView:memberNameSearchForm:firstName";
	public static final String ms_lbl_xp_searchResult = "//span[contains(text(), 'Results (0)')]";
	public static final String ms_btn_id_newMember = "creatMemberForm:creatMember";
	public static final String ms_txt_xp_memberid="//span[text()='Members']";
	public static final String tv_memberIdSearchResult = "memberSearchResultsForm:memberSearchResultsTable:searchResultsTable:0:memberDemographics";
	public static final String img_id_CMV="memberSearchResultsForm:memberSearchResultsTable:searchResultsTable:0:inspectIcon";
	public static final String txt_xp_result="//span[@class='ui-paginator-current']";
	public static final String txt_xp_noresults="//span[@class='ui-messages-info-detail']";
			
	/*Add Request Note page*/
	public static final String reqNote_txt_xp_request="//span[text()='Requests']";
	public static final String reqNote_txtbx_id_reqId="requestIdSearchForm:requestId";
	public static final String regNote_img_id_goArrow="requestSearchResultsForm:requestSearchResultsTable:inspectIcon";
	public static final String reqNote_btn_id_searchAerial="requestIdSearchForm:requestIdSearchButton";
	public static final String reqNote_txt_xp_note="//span[contains(text(),'Note')]";
	public static final String reqNote_lbl_xp_noteType="//label[@id='notesForm:j_idt236']";
	public static final String reqNote_txtare_xp_inputArea="//textarea[@id='notesForm:cmvNotesTextArea:notesTextArea']";
	public static final String reqNote_drpdwn_xp_noteType="//div[@id='notesForm:noteType']//span[@class='ui-icon ui-icon-triangle-1-s']";
	public static final String reqNote_txt_xp_noteType="//li[text()='Member Notes']";
	public static final String reqNote_btn_xp_save="//button[@id='notesForm:cmvNotesTextArea:submitButton']";
	public static final String reqNote_link_xp_memberName="//a[@class='ui-menuitem-link ui-corner-all']/span";
	public static final String reqNote_dd_xp_notes="//a[contains(text(),'Note')]";
	public static final String reqNote_btn_id_view="cmvCenterForm:cmvItemsTabView:1:addedNoteListTable:1:navigateToViewAllForNoteTypePlusEditNote";
	public static final String reqNote_btn_id_addNewItem="cmvCenterForm:contextSensitiveDefaultCMVItemMenuButton_button";
	public static final String reqNote_btn_xp_requestDetail="//a[contains(text(),'Request Detail')]";
	public static final String reqNote_msg_xp_noRecord="//td[contains(text(),'No records found')]";
	/*Add Request Task page*/
	public static final String reqTask_txt_xp_request="//span[text()='Requests']";
	public static final String reqTask_txtbx_id_reqId="requestIdSearchForm:requestId";
	public static final String regTask_img_id_goArrow="requestSearchResultsForm:requestSearchResultsTable:inspectIcon";
	public static final String reqTask_btn_id_searchAerial="requestIdSearchForm:requestIdSearchButton";
	public static final String reqTask_btn_id_addNewItem="cmvCenterForm:contextSensitiveDefaultCMVItemMenuButton_button";

	public static final String reqTask_txt_xp_task="//span[contains(text(),'Task')]";
	public static final String reqTask_lbl_xp_type="//label[@id='taskForm:j_idt243']";
	public static final String reqTask_lbl_xp_association="//label[@id='taskForm:j_idt292']";
	public static final String reqTask_drpdwn_xp_type="//div[@id='taskForm:taskType']//span[@class='ui-icon ui-icon-triangle-1-s']";
	public static final String reqTask_txt_xp_addNote="//li[contains(text(), 'Add Note')]";
	public static final String reqTask_drpdwn_xp_reason="//div[@id='taskForm:taskReason']//span[@class='ui-icon ui-icon-triangle-1-s']";
	public static final String reqTask_txt_xp_reason="//li[contains(text(),'Note added through iExchange')]";
	public static final String reqTask_drpdwn_xp_priority="//div[@id='taskForm:priority']//span[@class='ui-icon ui-icon-triangle-1-s']";
	public static final String reqTask_txt_xp_priority="//li[contains(text(),'Low')]";
	public static final String reqTask_btn_xp_saveTask="//button[@id='taskForm:saveButton']";
	public static final String reqTask_linktxt_xp_open="//a[contains(text(),'Open')]";
	public static final String reqTask_txt_xp_savedTask="//span[contains(text(),'Note added through iExchange')]";
	
	
	
	//CMV view
	public static final String cmv_btn_xp_addNewItem="//button[@id='cmvCenterForm:contextSensitiveDefaultCMVItemMenuButton_button']";
	public static final String cmv_txt_xp_note="//span[contains(text(),'Note')]";
	public static final String cmv_lbl_xp_noteType="//label[@id='notesForm:j_idt236']";
	public static final String cmv_drpdwn_xp_noteType="//div[@id='notesForm:noteType']//span[@class='ui-icon ui-icon-triangle-1-s']";
	public static final String cmv_txt_xp_noteType="//li[text()='Member Notes']";
	public static final String cmv_txtare_xp_inputArea="//textarea[@id='notesForm:cmvNotesTextArea:notesTextArea']";
	public static final String cmv_btn_xp_save="//button[@id='notesForm:cmvNotesTextArea:submitButton']";
	public static final String cmv_link_xp_memberName="//a[@class='ui-menuitem-link ui-corner-all']/span";
	public static final String cmv_dd_xp_notes="//a[contains(text(),'Note')]";
	public static final String cmv_btn_id_view="cmvCenterForm:cmvItemsTabView:1:addedNoteListTable:0:navigateToViewAllForNoteTypePlusEditNote";
	
	public static final String task_txt_xp_task="//span[contains(text(),'Task')]";
	public static final String task_lbl_xp_type="//label[@id='taskForm:j_idt243']";
	public static final String task_lbl_xp_association="//label[@id='taskForm:j_idt292']";
	public static final String task_drpdwn_xp_type="//div[@id='taskForm:taskType']//span[@class='ui-icon ui-icon-triangle-1-s']";
	public static final String task_txt_xp_addNote="//li[contains(text(), 'Add Note')]";
	public static final String task_drpdwn_xp_reason="//div[@id='taskForm:taskReason']//span[@class='ui-icon ui-icon-triangle-1-s']";
	public static final String task_txt_xp_reason="//li[contains(text(),'Note added through iExchange')]";
	public static final String task_drpdwn_xp_priority="//div[@id='taskForm:priority']//span[@class='ui-icon ui-icon-triangle-1-s']";
	public static final String task_txt_xp_priority="//li[contains(text(),'Low')]";
	public static final String task_btn_xp_saveTask="//button[@id='taskForm:saveButton']";
	public static final String task_linktxt_xp_open="//a[contains(text(),'Open')]";
	public static final String task_txt_xp_savedTask="//span[contains(text(),'Note added through iExchange')]";
	
/*Program Note page*/
	
	public static final String prgNote_btn_id_addNewItem="cmvCenterForm:contextSensitiveDefaultCMVItemMenuButton_button";
	public static final String prgNote_txt_xp_note="//span[contains(text(),'Note')]";
	public static final String prgNote_lstitm_xp_noteType="//li[text()='Member Notes']";
	public static final String prgNote_lbl_xp_noteType="//label[@id='notesForm:j_idt236']";
	public static final String prgNote_txtare_xp_inputArea="//textarea[@id='notesForm:cmvNotesTextArea:notesTextArea']";
	public static final String prgNote_drpdwn_xp_noteType="//div[@id='notesForm:noteType']//span[@class='ui-icon ui-icon-triangle-1-s']";
	public static final String prgNote_txt_xp_noteType="//li[text()='Enrollment Notes']";
	public static final String prgNote_btn_xp_save="//button[@id='notesForm:cmvNotesTextArea:submitButton']";
	public static final String prgNote_link_xp_memberName="//a[@class='ui-menuitem-link ui-corner-all']/span";
	public static final String prgNote_msg_xp_lockedMsg="//span[contains(text(),'This record is locked')]";
	public static final String prgNote_btn_xp_allItems="//td[contains(text(),'All Items')]";
	public static final String prgNote_lstitm_xp_recordLocks="//span[contains(text(),'Record Locks')]";
	public static final String prgNote_txtbx_id_recordId="entityResultsForm:j_idt29:j_idt40:filter";
	public static final String prgNote_img_xp_delete = "//tbody[@id='entityResultsForm:j_idt29_data']//a[@id='entityResultsForm:j_idt29:0:deleteLink']";
	public static final String prgNote_drpdwn_xp_programDetail="//h3[@id='cmvCenterForm:cmvItemsTabView:0:itemTab_h3']";
	public static final String prgNote_dd_xp_notes = "//a[contains(text(),'Note')]";
	public static final String prgNote_btn_xp_view="//tr[td[contains(text(),'Member Notes')]]//button[@id='cmvCenterForm:cmvItemsTabView:1:addedNoteListTable:1:navigateToViewAllForNoteTypePlusEditNote']";
	public static final String prgNote_btn_id_refresh="exitForm:refresh";
	
	/*Program Task page*/
	
	public static final String prgTask_txt_xp_task="//span[contains(text(),'Task')]";
	public static final String prgTask_lbl_xp_type="//label[@id='taskForm:j_idt243']";
	public static final String prgTask_lbl_xp_association="//label[@id='taskForm:j_idt292']";
	public static final String prgTask_drpdwn_xp_type="//div[@id='taskForm:taskType']//span[@class='ui-icon ui-icon-triangle-1-s']";
	public static final String prgTask_txt_xp_addNote="//li[contains(text(), 'Add Note')]";
	public static final String prgTask_drpdwn_xp_reason="//div[@id='taskForm:taskReason']//span[@class='ui-icon ui-icon-triangle-1-s']";
	public static final String prgTask_txt_xp_reason="//li[contains(text(),'Note added through iExchange')]";
	public static final String prgTask_drpdwn_xp_priority="//div[@id='taskForm:priority']//span[@class='ui-icon ui-icon-triangle-1-s']";
	public static final String prgTask_txt_xp_priority="//li[contains(text(),'Low')]";
	public static final String prgTask_btn_xp_saveTask="//button[@id='taskForm:saveButton']";
	public static final String prgTask_linktxt_xp_open="//a[contains(text(),'Open')]";
	public static final String prgTask_txt_xp_savedTask="//span[contains(text(),'Note added through iExchange')]";
	public static final String prgTask_btn_id_addNewItem="cmvCenterForm:contextSensitiveDefaultCMVItemMenuButton_button";

	
	
	
	
	
			
	//Edit Profile details
	public static final String txt_xp_phonenumber="//ul[@id='memberOverviewForm:memberOverviewAccordionPanel:memberPhoneDataList_list']";
	public static final String txt_xp_email="//a[@id='memberOverviewForm:memberOverviewAccordionPanel:memberEmailDataList:0:memberEmailAddressLink']";
	public static final String btn_id_edit="memberOverviewForm:memberOverviewAccordionPanel:editMemberDetails";
	public static final String et_id_email="memberDetailIdentificationForm:input_personal_email";
	public static final String et_id_phonenumber="memberDetailIdentificationForm:input_telHome_mask";
	public static final String btn_id_save="memberDetailIdentificationForm:saveAndExit:j_idt61";
	
		
	//smart plan of care page
	public static final String btn_allTopics = "allTopics";
	
	// Program Search Page
	
	public static final String txt_xp_program="//span[text()='Programs']";	
	public static final String et_id_programid="programIdSearchForm:programId";
	public static final String btn_id_searchaerial="programIdSearchForm:programIdSearchButton";
	public static final String txt_xp_ProgramSearch="//span[text()='Program Search']";
	public static final String txt_xp_programid="//tr[@class='ui-widget-content ui-datatable-even']/td[2]";
	public static final String img_id_cmv="programSearchResultsForm:programSearchResultsTable:inspectIcon";
	
	//common iDs
	 public static final String txt_xp_norecords="//tr[@class='ui-widget-content ui-datatable-empty-message']/td[text()='No records found.']";
	 public static final String hm_Top_btn_xp_SeekFirst="//div[@class='ui-paginator ui-paginator-top ui-widget-header ui-corner-top']//span[@class='ui-icon ui-icon-seek-first']";
	 public static final String hm_Top_btn_xp_SeekPrev="//div[@class='ui-paginator ui-paginator-top ui-widget-header ui-corner-top']//span[@class='ui-icon ui-icon-seek-prev']";
	 public static final String hm_Top_btn_xp_SeekNext="//div[@class='ui-paginator ui-paginator-top ui-widget-header ui-corner-top']//span[@class='ui-icon ui-icon-seek-next']";
	 public static final String hm_Top_btn_xp_SeekEnd="//div[@class='ui-paginator ui-paginator-top ui-widget-header ui-corner-top']//span[@class='ui-icon ui-icon-seek-end']";
	 public static final String hm_Bottom_btn_xp_SeekFirst="//div[@class='ui-paginator ui-paginator-bottom ui-widget-header ui-corner-bottom']//span[@class='ui-icon ui-icon-seek-first']";
	 public static final String hm_Bottom__btn_xp_SeekPrev="//div[@class='ui-paginator ui-paginator-bottom ui-widget-header ui-corner-bottom']//span[@class='ui-icon ui-icon-seek-prev']";
	 public static final String hm_Bottom__btn_xp_SeekNext="//div[@class='ui-paginator ui-paginator-bottom ui-widget-header ui-corner-bottom']//span[@class='ui-icon ui-icon-seek-next']";
	 public static final String hm_Bottom__btn_xp_SeekEnd="//div[@class='ui-paginator ui-paginator-bottom ui-widget-header ui-corner-bottom']//span[@class='ui-icon ui-icon-seek-end']";
	
	 
	 // Request Search Page
	public static final String txt_xp_Request="//span[text()='Requests']";	
	public static final String Invalid_requestId="20000929-001";
	public static final String et_id_requestid="requestIdSearchForm:requestId";
	public static final String btn_id_searchrequest="requestIdSearchForm:requestIdSearchButton";
	public static final String txt_xp_requestsearch="//span[text()='Request Search']";
	public static final String img_id_cmvRq="requestSearchResultsForm:requestSearchResultsTable:inspectIcon";
	public static final String txt_xp_requestid="//tr[@class='ui-widget-content ui-datatable-even']/td[2]";
	
	//Provider Search Page.String
	public static final String Pvd_txt_xp_provider = "//span[text()='Providers']";
	public static final String Pvd_et_id_lastName = "j_idt29:providerNameSearchForm:practitionerLastName";
	public static final String Pvd_btn_id_SearchAerial = "j_idt29:providerNameSearchForm:searchLocalButton";
	public static final String Pvd_et_id_firstName = "j_idt29:providerNameSearchForm:firstName";
	public static final String Pvd_tx_xp_Providername = "//tr[@class='ui-widget-content ui-datatable-even']/td[2]";
	public static final String Pvd_txt_xp_ProviderId = "providerSearchResultsForm:providerSearchResultsTable:searchResultsTable:0:providerId";
	public static final String Pvd_txt_xp_Identification = "//div[@id='providerDetailIdentificationForm:j_idt28']/div[1]/h3[text()='Identification']";
	public static final String Pvd_btn_id_Cancel = "providerDetailIdentificationForm:cancel";
	public static final String Pvd_btn_id_Edit = "providerDetailIdentificationForm:edit";
	
	
	
	// Help page
	public static final String help_txt_xp_careMgmt = "//h1[contains(text(),'Care Management Online Help')]";

	/*Add Contacts*/
	public static final String AddContacts_txt_xp_Contacts="//span[text()='Contacts']";
	public static final String AddContacts_txt_id_AddContact="memberDetailContactForm:addMemberContactTop";
	public static final String AddContacts_et_id_FirstName="memberDetailContactForm:addEditContactForm:firstName";
	public static final String AddContacts_et_id_LastName="memberDetailContactForm:addEditContactForm:lastName";
	public static final String AddContacts_btn_id_NewPhone="memberDetailContactForm:addEditContactForm:phoneList:newPhoneButton";
	public static final String AddContacts_et_id_NewPhoneNumber = "memberDetailContactForm:addEditContactForm:phoneList:0:j_idt497";
	public static final String AddContacts_btn_id_NewEmail="memberDetailContactForm:addEditContactForm:emailList:newEmailButton";
	public static final String AddContacts_et_id_NewEmailID = "memberDetailContactForm:addEditContactForm:emailList:0:j_idt518";
	public static final String AddContacts_btn_id_SaveAndExit= "memberDetailContactForm:saveAndExit";
	public static final String AddContacts_chk_xp_CheckNumber ="//div[@id='memberDetailContactForm:addEditContactForm:phoneList:0:j_idt507']/span[2]";
	public static final String AddContacts_chk_xp_CheckPreferedNumber="//div[@id='memberDetailContactForm:addEditContactForm:phoneList:0:j_idt493']/div[2]";
	
	public static final String AddContacts_chk_xp_CheckEmail ="//div[@id='memberDetailContactForm:addEditContactForm:emailList:0:j_idt524']/span[2]";
	public static final String AddContacts_chk_xp_CheckPreferedEmail="//div[@id='memberDetailContactForm:addEditContactForm:emailList:0:j_idt514']/div[2]";
	public static final String AddContacts_btn_id_Active ="memberDetailContactForm:status:j_idt389";
	
	/*Add a Provider contact*/
	public static final String AddContactsPVD_txt_xp_Contacts="//span[text()='Contacts']";
	public static final String AddContactsPVD_txt_id_AddContact="providerDetailContactForm:contactTabView:addContact";
	public static final String AddContactsPVD_et_id_FirstName="providerDetailContactForm:addEditContactForm:firstName";
	public static final String AddContactsPVD_et_id_LastName="providerDetailContactForm:addEditContactForm:lastName";
	public static final String AddContactsPVD_btn_id_NewPhone="providerDetailContactForm:addEditContactForm:phoneList:newPhoneButton";
	public static final String AddContactsPVD_et_id_NewPhoneNumber = "providerDetailContactForm:addEditContactForm:phoneList:0:j_idt369";
	public static final String AddContactsPVD_btn_id_NewEmail="providerDetailContactForm:addEditContactForm:emailList:newEmailButton";
	public static final String AddContactsPVD_et_id_NewEmailID = "providerDetailContactForm:addEditContactForm:emailList:0:j_idt385";
	public static final String AddContactsPVD_btn_id_SaveAndExit= "providerDetailContactForm:saveAndExit";
	public static final String AddContactsPVD_chk_xp_CheckNumber ="//div[@id='providerDetailContactForm:addEditContactForm:phoneList:0:j_idt375']/span[2]";
	public static final String AddContactsPVD_chk_xp_CheckPreferedNumber= "//div[@id='providerDetailContactForm:addEditContactForm:phoneList:0:j_idt365']/div[2]";	
	public static final String AddContactsPVD_chk_xp_CheckEmail = "//div[@id='providerDetailContactForm:addEditContactForm:emailList:0:j_idt391']/span[2]";
	public static final String AddContactsPVD_chk_xp_CheckPreferedEmail= "//div[@id='providerDetailContactForm:addEditContactForm:emailList:0:j_idt381']/div[2]";
	public static final String AddContactsPVD_btn_id_Active = "providerDetailContactForm:status";
	

	
}
