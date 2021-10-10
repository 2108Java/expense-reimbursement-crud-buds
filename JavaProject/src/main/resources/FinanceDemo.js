console.log("Hello");


function getReport(){ //getting a single report

    let planetsUrl = "http://localhost:8000/viewReport";

    

			// console.log(report[0].employeeName);

            let radios = document.getElementsByName("flexRadioDefault");

           radios = document.querySelector('input[name="flexRadioDefault"]:checked').value;

           console.log(radios);


           if(radios == 'All'){
            fetch(planetsUrl).then(function(response) {
                response.json().then(function(report){	
                console.log(report);
             
                deleteTableRows();
    
                addAllReports(report);
          
              });
          }).catch(err => console.error(err));

           }else if(radios == 'Rejected') {

            fetch(planetsUrl).then(function(response) {
                response.json().then(function(report){	
                console.log(report);
             
                deleteTableRows();
    
                addAllReports(report);
          
              });
          }).catch(err => console.error(err));



           }else if(radios == 'Pending'){
                fetch(planetsUrl).then(function(response) {
                                response.json().then(function(report){	
                                console.log(report);
                             
                                deleteTableRows();
                    
                                addAllPending(report);
                          
                              });
                          }).catch(err => console.error(err));
           }else{
               
            fetch(planetsUrl).then(function(response) {
                response.json().then(function(report){	
                console.log(report);
             
                deleteTableRows();
    
                addAllReports(report);
          
              });
          }).catch(err => console.error(err));
           }

            // for (var i = 0, length = radios.length; i < length; i++) {
            //     if (radios[i].checked) {
            //       // do whatever you want with the checked radio
            //     //   alert(radios[i].value);
                    
            //       // only one radio can be logically checked, don't check the rest

            //       console.log(radios[i].value);
            //         if(radios[i].checked == 'All'){
            //             console.log(radios[i].value);
            //         	fetch(planetsUrl).then(function(response) {
  		    //                 response.json().then(function(report){	
			//                 console.log(report);
            //                 deleteTableRows();
                        
            //                 addAllPlanets(report);
	        //                 });
	        //             }).catch(err => console.error(err));

                        
            //         }else if(radios[i].checked == 'Pending'){
            //             console.log(radios[i].value);
            //             fetch(planetsUrl).then(function(response) {
            //                 response.json().then(function(report){	
            //                 console.log(report);
                         
            //                 deleteTableRows();
                
            //                 addAllPending(report);
                      
            //               });
            //           }).catch(err => console.error(err));
                        
            //         }else if(radios[i].checked == 'Rejected'){
            //             console.log(radios[i].value);
            //             fetch(planetsUrl).then(function(response) {
            //                 response.json().then(function(report){	
            //               console.log(report);
            //               deleteTableRows();
                      
            //           addAllPlanets(report);
            //               });
            //           }).catch(err => console.error(err));
            //             deleteTableRows();
            //         }else{

            //             fetch(planetsUrl).then(function(response) {
            //                 response.json().then(function(report){	
            //               console.log(report);
            //               deleteTableRows();
                      
            //         //   addAllPlanets(report);
            //               });
            //           }).catch(err => console.error(err));
            //             deleteTableRows();
            //         }




            //       break;
            //     }
            //   }
            // let radioValues = Radio.;
            

			// addAllPlanets(report);
  	
	
}

function getAllReports(){ //getting all the reimbursement tickets
	
	let planetsUrl = "http://localhost:8000/viewReport";

    

	fetch(planetsUrl).then(function(response) {
  		response.json().then(function(report){	
			console.log(report);
			console.log(report[0].employeeName);
			addAllPlanets(report);
  		});
	}).catch(err => console.error(err));

}

let button = document.getElementById("viewSubmit");
button.addEventListener('click',getReport);

// window.onload = function(){

// 	getAllReports();
	
// //This functions get invoked when the page is loaded in!
// }

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

    //assign the button
    let rejectBtn = document.createElement("input");
    rejectBtn.type = "submit";
    rejectBtn.innerText = "submit";
    rejectBtnColumn.appendChild(rejectBtn);
    
    let approveBtn = document.createElement("input");
    approveBtn.type = "submit";
    approveBtn.innerText = "submit";
    approveBtnColumn.appendChild(approveBtn);
    // approveBtn.type = "button";



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
        console.log(report.approvalStatus);
		addRow( report);
	}
	

}

function addAllPending(user){
	
	for(let report of user){
        if(report.approvalStatus == "Pending"){
            console.log(report.approvalStatus);
            addRowPending( report);
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
	
    // let tr = document.getElementsByTagName('tr')[0];

    // tr.remove();
    // $("#table_of_items tr").remove(); 
}
