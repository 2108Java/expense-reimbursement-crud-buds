create table employee (
	employee_id serial primary key,
	username varchar(20) not null unique,
	password varchar(20) not null,
	is_finance_manager boolean not null
	--email varchar(50) not null unique, only used for strech goal--

);

create table expense_reports (
	report_id serial primary key,
	amount numeric not null,
	expense_type varchar(10) not null,--4 states are lodging, travel, food, or other-- 
	creation_time timestamp not null, --date format example '2004-10-19 10:23:54'--
	approval_status varchar(10) default 'Pending', --3 states are pending, approved, or denied--
	employee_id int REFERENCES employee(employee_id)
	
);