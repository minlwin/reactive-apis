create table division (
	id identity,
	name varchar(40) not null,
	region varchar(80) not null
);

create table township (
	id identity,
	name varchar(40) not null,
	division_id int not null,
	foreign key (division_id) references division(id)
);