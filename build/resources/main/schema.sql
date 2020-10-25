DROP TABLE IF EXISTS GROUP_MEMBER;
DROP TABLE IF EXISTS Allergens;
DROP TABLE IF EXISTS cartItems;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS cart;
DROP TABLE IF EXISTS menuItems;
DROP TABLE IF EXISTS itemAllergens;
DROP TABLE IF EXISTS staffIDs;
DROP TABLE IF EXISTS wakeUpCallRequests;
DROP TABLE IF EXISTS maintenance_requests;
DROP TABLE IF EXISTS general_requests;
DROP TABLE IF EXISTS request;

create table cart (
    cart_id int primary key,
    completed boolean not null,
    room int not null
);

create table orders (
    order_id int primary key,
    cart_id int not null,
    foreign key (cart_id) references cart,
    status varchar(10) not null,
    timeOfRequest DATETIME DEFAULT CURRENT_TIMESTAMP
);

create table menuItems (
    item_id int primary key,
    name varchar(50) not null,
    price decimal(4,2) not null,
    description varchar(300) not null,
    image varchar(100)
);

create table cartItems (
    id int primary key,
    item_id int,
    foreign key (item_id) references menuItems,
    quantity int DEFAULT 1,
    notes varchar(100),
    cart_id int not null,
    foreign key (cart_id) references cart
);

create table Allergens (
    allergen varchar(25) primary key
);

create table itemAllergens (
    item_id int,
    foreign key (item_id) references menuItems,
    allergen varchar(25),
    foreign key (allergen) references Allergens,
    primary key (item_id, allergen)
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