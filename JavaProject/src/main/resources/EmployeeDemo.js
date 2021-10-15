console.log("Hello");

function getAllReports(){ //getting all the reimbursement tickets
	
	let reportsUrl = "http://localhost:8000/reports";

	fetch(reportsUrl).then(function(response) {
  		response.json().then(function(users){	
			console.log(users);
			console.log(users.reportList[1].reportId);
			addAllPlanets(users);
  		});
	}).catch(err => console.error(err));
	
}

window.onload = function(){

	getAllReports();
	
//This functions get invoked when the page is loaded in!
}

function addRow(user, report){
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
    nameColumn.innerText = user.username;
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

function addAllPlanets(user){
	
	for(let report of user.reportList){
		addRow(user, report);
	}
	
	
}
