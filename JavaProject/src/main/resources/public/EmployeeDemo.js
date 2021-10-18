console.log("Hello");


function getReport(){ //getting a single report

	let planetId = document.getElementById("planetId").value;
	
	let baseUrl = "http://ec2-52-15-202-41.us-east-2.compute.amazonaws.com:8000/planet/";
	
	let fullPlanetUrl = baseUrl +planetId;
	
	let xhttp = new XMLHttpRequest();
	
	xhttp.onreadystatechange = function(){
		
		if(this.status == 200 && this.readyState == 4){
			//let planet = JSON.parse(this.responseText);
			//console.log(planet);
			
			addRow(planet);
		}
	}
	
	xhttp.open("GET",fullPlanetUrl);
	
	xhttp.send();
	
}

function getAllReports(){ //getting all the reimbursement tickets
	
	let planetsUrl = "http://ec2-52-15-202-41.us-east-2.compute.amazonaws.com:8000/reports";

	fetch(planetsUrl).then(function(response) {
  		response.json().then(function(users){	
			console.log(users);
			console.log(users.reportList[1].reportId);
			addAllPlanets(users);
  		});
	}).catch(err => console.error(err));
	
	// let xhttp = new XMLHttpRequest();
	
	// xhttp.onreadystatechange = function (){ 
	// 	//fat arrow notation does not support "this" keyword
		
	// 	// console.log(this.readyState);
		
	// 	if(this.readyState == 4 && this.status == 200){
			
			
    //         let user = JSON.parse(this.responseText);
			
    //         console.log(user);
    //         console.log(user.reportList[1].reportId);
	// 		 addAllPlanets(user);
	// 	}
	// }
	
	// xhttp.open("GET",planetsUrl);
	
	// xhttp.send();
	
}

// let button = document.getElementById("planetSubmit");
// button.addEventListener('click',getPlanet);

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
