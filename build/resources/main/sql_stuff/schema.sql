DROP TABLE IF EXISTS GROUP_MEMBER;
DROP TABLE IF EXISTS menuItems;
DROP TABLE IF EXISTS staffIDs;
DROP TABLE IF EXISTS request;
DROP TABLE IF EXISTS wakeUpCallRequests;
DROP TABLE IF EXISTS maintenance_requests;
DROP TABLE IF EXISTS general_requests;

create table menuItems (
    item_id int primary key,
    name varchar(50),
    price decimal(4,2) not null,
    description varchar(300) not null,
    allergens varchar(100),
    image blob -- should be not null, add pics later
);

create table staffIDs (
    id int primary key,
    username varchar(25) not null,
    password varchar(25) not null
);

create table request (
    requestID int primary key,
    roomNumber int not null,
    timeOfRequest DATETIME DEFAULT CURRENT_TIMESTAMP,
    requestType varchar(50) not null
);

create table wakeUpCallRequests (
    requestID int primary key,
    foreign key (requestID) references request,
    roomNumber int not null,
    foreign key (roomNumber) references request,
    timeOfRequest DATETIME,
    foreign key (timeOfRequest) references request,
    timeForCall DATETIME not null
);

create table maintenance_requests (
    requestID int primary key,
    foreign key (requestID) references request,
    roomNumber int not null,
    foreign key (roomNumber) references request,
    timeOfRequest DATETIME,
    foreign key (timeOfRequest) references request,
    notes varchar(500),
    commonRequestType varchar(50) not null
);

create table general_requests (
    requestID int primary key,
    foreign key (requestID) references request,
    roomNumber int not null,
    foreign key (roomNumber) references request,
    timeOfRequest DATETIME,
    foreign key (timeOfRequest) references request,
    notes varchar(500),
    commonRequestType varchar(50) not null
);