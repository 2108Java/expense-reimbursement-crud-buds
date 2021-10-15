# expense-reimbursement-crud-buds
# Project 1: Expense Reimbursement System

## Executive Summary

The Expense Reimbursement System (ERS) will manage the process of reimbursing employees for expenses incurred while on company time. All employees in the company can log in and submit requests for reimbursement and view their past tickets and pending requests. Finance managers can log in and view all reimbursement requests and past history for all employees in the company. Finance managers are authorized to approve and deny requests for expense reimbursement.

## Reuirements
1. Use three tier design architecture.
2. Data is stored in a database using amazon web services.
3. All tables need to be in 3rd Normal Form.
4. Must use servlet technology on the middle tier.
5. Must use JUnit test on the service class(s) and Postman to test endpoints.
6. Front end will use JavaScript and AJAX to call for server side components.
7. Must use JDBC to talk to database.
8. Logging must be implemented with Log4j and not by java.

Stretch Goal (Optional requirements)

* Deploy project entirely on AWS services, S3 Buckets, EC2, and RDS.

* Passwords shall be encrypted/hashed in Java and securely stored in the database.

* Users can upload a document or image of their receipt when submitting reimbursements. 

* The application will send an email to employees letting them know that they have been registered as a new user, giving them their temporary password 


## User Stories

Employee

* I can log in to view past tickets (pending, approved rejected)
 
* I can log in to add a reimbursement request.

* Employees must select the type of reimbursement as LODGING, TRAVEL, FOOD, or OTHER.

* Reimbursement ticket must specify the amount to be reimbursed and a description that the employee may add (in addition to the type) and a time stamp associated with it.


Finance Manager

* As a finance manager, I can log in to view all the reimbursements for all employees.

* I can filter the requests by the status of the tickets (approved, rejected, pending) 

