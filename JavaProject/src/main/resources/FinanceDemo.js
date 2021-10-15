function getReport(){ //getting a single report
    let reportUrl = "http://localhost:8000/viewReport";
			// console.log(report[0].employeeName);
           let radios = document.getElementsByName("flexRadioDefault");
           radios = document.querySelector('input[name="flexRadioDefault"]:checked').value;
           if(radios == 'All'){
            fetch(reportUrl).then(function(response) {
                response.json().then(function(report){	            
                deleteTableRows();   
                addAllReports(report);           
              });
          }).catch(err => console.error(err));
           }else if(radios == 'Rejected') {
            fetch(reportUrl).then(function(response) {
                response.json().then(function(report){	           
                deleteTableRows();    
                addAllRejected(report);          
              });
          }).catch(err => console.error(err));     
           }else if(radios == 'Pending'){
                fetch(reportUrl).then(function(response) {
                                response.json().then(function(report){	                            
                                deleteTableRows();                    
                                addAllPending(report);                         
                              });
                          }).catch(err => console.error(err));
           }else{              
            fetch(reportUrl).then(function(response) {
                response.json().then(function(report){	            
                deleteTableRows();  
                addAllApproved(report);         
              });
          }).catch(err => console.error(err));
           }	
}
function rejectGetSelected(event){ //getting all the reimbursement tickets
	let reportUrl = "http://localhost:8000/viewReport";
	fetch(reportUrl).then(function(response) {
  		response.json().then(function(report){	
			reject(report,event);
  		});
	}).catch(err => console.error(err));
}
function approveGetSelected(event){ //getting all the reimbursement tickets
	
	let reportUrl = "http://localhost:8000/viewReport";
	fetch(reportUrl).then(function(response) {
  		response.json().then(function(report){	
			approve(report,event);
  		});
	}).catch(err => console.error(err));
}
let button = document.getElementById("viewSubmit");
button.addEventListener('click',getReport);
window.onload = function(){
	getReport(); 	
//This functions get invoked when the page is loaded in!
}
function addRow(report){
    //Append this onto my table, 
    let tableBody = document.getElementById("employeeTableBody");
    //Creating a table row
    let tableRow = document.createElement("tr");
    //Create the columns 
    let idColumn = document.createElement("td");
    let nameColumn = document.createElement("td");
	let typeColumn = document.createElement("td");
    let descColumn = document.createElement("td");
    let amountColumn = document.createElement("td");
	let timeColumn = document.createElement("td");
	let statusColumn = document.createElement("td");
	//assigning the "text value" to our columns 
    idColumn.innerText = report.reportId;
    nameColumn.innerText = report.employeeName;
	typeColumn.innerText = report.reportType
    descColumn.innerText = report.description;
    amountColumn.innerText = report.amount;
	timeColumn.innerText = report.timestamp;
	statusColumn.innerText = report.approvalStatus;
    //attach the columns to our newly created row 
    tableRow.appendChild(idColumn);
    tableRow.appendChild(nameColumn);
    tableRow.appendChild(typeColumn);
    tableRow.appendChild(descColumn);
 	tableRow.appendChild(amountColumn);
 	tableRow.appendChild(timeColumn);
 	tableRow.appendChild(statusColumn);
    //attach the row itself to the table
    tableBody.appendChild(tableRow);	
}
function addRowPending(report){
    //Append this onto my table, 
    let tableBody = document.getElementById("employeeTableBody");
    //Creating a table row
    let tableRow = document.createElement("tr");
    //Create the columns
    let idColumn = document.createElement("td");
    let nameColumn = document.createElement("td");
	let typeColumn = document.createElement("td");
    let descColumn = document.createElement("td");
    let amountColumn = document.createElement("td");
	let timeColumn = document.createElement("td");
	let statusColumn = document.createElement("td");
    let rejectBtnColumn = document.createElement("td");
    let approveBtnColumn = document.createElement("td");
    //assign the reject button
    let rejectBtn = document.createElement("input");
    rejectBtn.type = "button";
    rejectBtn.value = "Reject";
    rejectBtn.setAttribute("id",report.reportId);
    rejectBtn.addEventListener('click', rejectGetSelected);
    rejectBtnColumn.appendChild(rejectBtn);
    //assign the approve button
    let approveBtn = document.createElement("input");
    approveBtn.type = "button";
    approveBtn.value = "Approve";
    approveBtn.setAttribute("id",report.reportId);
    approveBtn.addEventListener('click', approveGetSelected);
    approveBtnColumn.appendChild(approveBtn);
    //assigning the "text value" to our columns 
    idColumn.innerText = report.reportId;
    nameColumn.innerText = report.employeeName;
	typeColumn.innerText = report.reportType
    descColumn.innerText = report.description;
    amountColumn.innerText = report.amount;
	timeColumn.innerText = report.timestamp;
	statusColumn.innerText = report.approvalStatus;
    //attach the columns to our newly created row 
    tableRow.appendChild(idColumn);
    tableRow.appendChild(nameColumn);
    tableRow.appendChild(typeColumn);
    tableRow.appendChild(descColumn);
 	tableRow.appendChild(amountColumn);
 	tableRow.appendChild(timeColumn);
 	tableRow.appendChild(statusColumn);
    tableRow.appendChild(rejectBtnColumn);
    tableRow.appendChild(approveBtnColumn);
    //attach the row itself to the table
    tableBody.appendChild(tableRow);
	
}
function addAllReports(user){	
	for(let report of user){
		addRow( report);
	}	
}

function addAllPending(user){	
	for(let report of user){
        if(report.approvalStatus == "Pending"){
            addRowPending( report);
        }	
	}
}
function addAllRejected(user){	
	for(let report of user){
        if(report.approvalStatus == "Rejected"){          
            addRow( report);
        }	
	}
}
function addAllApproved(user){	
	for(let report of user){
        if(report.approvalStatus == "Approved"){
            addRow( report);
        }	
	}
}
function deleteTableRows(){
    let tableHeaderRowCount = 0;
    let table = document.getElementById('employeeTableBody');
    let rowCount = table.rows.length;
    for (let i = tableHeaderRowCount; i < rowCount; i++) {
        table.deleteRow(tableHeaderRowCount);
    }
}
function approve(report, event){ 
    let url = "http://localhost:8000/managerApproved/" + event.srcElement.id;
    let xhttp = new XMLHttpRequest();	
	xhttp.onreadystatechange = function (){ 		
		if(this.readyState == 4 && this.status == 200){
			 getReport();
		}
	}
	for(let reportId of report){
        if(reportId.reportId == event.srcElement.id){				
			 	xhttp.open("PUT",url);	
	            xhttp.send(JSON.stringify(reportId));
			 }
		 }

	}

function reject(report, event){
    let url = "http://localhost:8000/managerRejected/" + event.srcElement.id ;
    let xhttp = new XMLHttpRequest();	
	xhttp.onreadystatechange = function (){ 				
		if(this.readyState == 4 && this.status == 200){
			 getReport();			
		}
	}
	for(let reportId of report){
        if(reportId.reportId == event.srcElement.id){
                xhttp.open("PUT",url);
	            xhttp.send(JSON.stringify(reportId));
			}
		}
	}

   
