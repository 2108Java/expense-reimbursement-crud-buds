package com.revature.models;

public class Report {
	
	//Attributes
	private int reportId;
	private float amount;
	private String reportType;
	private String description;
	private String timestamp;
	private String approvalStatus;
	
	//Constructors
	
	public Report() {
		super();
	}

	
	
	//Getters and Setters
	
	public Report(int reportId, float amount, String reportType, String description, String timestamp,
			String approvalStatus) {
		super();
		this.reportId = reportId;
		this.amount = amount;
		this.reportType = reportType;
		this.description = description;
		this.timestamp = timestamp;
		this.approvalStatus = approvalStatus;
	}



	public int getReportId() {
		return reportId;
	}
	public void setReportId(int reportId) {
		this.reportId = reportId;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public String getReportType() {
		return reportType;
	}
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getApprovalStatus() {
		return approvalStatus;
	}
	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}



	@Override
	public String toString() {
		return "Report [reportId=" + reportId + ", amount=" + amount + ", reportType=" + reportType + ", description="
				+ description + ", timestamp=" + timestamp + ", approvalStatus=" + approvalStatus + "]";
	}

}
