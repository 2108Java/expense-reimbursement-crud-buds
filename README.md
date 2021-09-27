# expense-reimbursement-crud-buds
Project 1: Expense Reimbursement System

Executive Summary
The Expense Reimbursement System (ERS) will manage the process of reimbursing employees for expenses incurred while on company time. All employees in the company can log in and submit requests for reimbursement and view their past tickets and pending requests. Finance managers can log in and view all reimbursement requests and past history for all employees in the company. Finance managers are authorized to approve and deny requests for expense reimbursement.

Minimum Technical Requirements
Project code and package as to be appropriately structured (no spaghetti code) and utilize appropriate design patterns (e.g. DAO and Front-Controller Design pattern)
All tables must be in at least 3rd Normal Form.
The back-end system shall use JDBC to connect to a PostgreSQL database, hosted on AWS RDS.
Use at least one PreparedStatement or CallableStatement
The application shall deploy onto a Server.
The middle tier shall use Servlet technology for dynamic Web application development.
The front-end view will use JavaScript and AJAX to call server-side components. The web pages should look presentable (utilizing a combination of CSS and Bootstrap)
Logging has to be implemented, utilizing an external logging framework (e.g. Log4J)
Unit test coverage, utilizing Mockito, JUnit, and Postman. 

Stretch Goal (Optional requirements)
Deploy project entirely on AWS services, S3 Buckets, EC2, and RDS.
Passwords shall be encrypted/hashed in Java and securely stored in the database.
Users can upload a document or image of their receipt when submitting reimbursements. 
The application will send an email to employees letting them know that they have been registered as a new user, giving them their temporary password 



Minimum Application Functionality
	Employee
I can log in to view past tickets (pending, approved rejected) 
I can log in to add a reimbursement request.
Employees must select the type of reimbursement as LODGING, TRAVEL, FOOD, or OTHER.
Reimbursement ticket must specify the amount to be reimbursed and a description that the employee may add (in addition to the type) and a time stamp associated with it.
	Finance Manager
As a finance manager, I can log in to view all the reimbursements for all employees.
I can filter the requests by the status of the tickets (approved, rejected, pending) 
