create database MyStore;
use MyStore;

create table accounts(
 Username varchar(20) NOT NULL PRIMARY KEY,
 Password varchar(120) not null,
 Fullname nvarchar(50) not null,
 Email varchar(50) not null
);

create table roles(
 Id varchar(10) NOT NULL PRIMARY KEY,
 Name varchar(20) not null
);

create table Authorities(
	Id int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
	Username varchar(20),
	Roleid varchar(10),
	Foreign key(Username) references accounts(Username),
	Foreign key(Roleid) references Roles(Id)
);

CREATE TABLE Categories(
	Id char(4) not null PRIMARY KEY,
	Name nvarchar(50) not null
);

CREATE TABLE Products(
	Id int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
	Name nvarchar(100) not null,
	Image nvarchar(100) not null,
	Price float not null,
	Active bit not null,
	Createdate date not null,
	CategoryId char(4) not null,
	Foreign key(CategoryId) references Categories(Id)
);

CREATE TABLE Orders(
	Id int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
	Username varchar(20) not null,
	Createdate date not null,
	Address nvarchar(200) not null,
	Foreign key(Username) references Accounts(Username)
);

CREATE TABLE OrderDetails(
	Id int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
	OrderId int(11) not null,
	ProductId int(11) not null,
	Price float not null,
	Quantity int not null,
	Foreign key(OrderId) references Orders(Id),
	Foreign key(ProductId) references Products(Id)
);


INSERT INTO Categories VALUES('COTTON', N'ÁO THUN COTTON NAM');
INSERT INTO Categories VALUES('GTT', N'GIÀY THỂ THAO NAM');
INSERT INTO Categories VALUES('SDL', N'SANDAL NAM');
INSERT INTO Categories VALUES('DPN', N'DÉP NAM');

INSERT INTO Products(Name,Image,Price,Active,Createdate,CategoryId) VALUES (N'Áo thun Cotton Nam - 1','COTTON-1.jpg', 149000, 1, '2021-07-09', 'COTTON');
INSERT INTO Products(Name,Image,Price,Active,Createdate,CategoryId) VALUES (N'Áo thun Cotton Nam - 2','COTTON-2.jpg', 149000, 1, '2021-07-09', 'COTTON');
INSERT INTO Products(Name,Image,Price,Active,Createdate,CategoryId) VALUES (N'Áo thun Cotton Nam - 3','COTTON-3.jpg', 149000, 1, '2021-07-09', 'COTTON');
INSERT INTO Products(Name,Image,Price,Active,Createdate,CategoryId) VALUES (N'Áo thun Cotton Nam - 4','COTTON-4.jpg', 149000, 1, '2021-07-09', 'COTTON');

INSERT INTO roles(id, name) VALUES('US', 'ROLE_USER');
INSERT INTO roles(id, name) VALUES('MOD', 'ROLE_MODERATOR');
INSERT INTO roles(id, name) VALUES('AD', 'ROLE_ADMIN');

INSERT INTO accounts VALUES('admin','123',N'Lê Văn Lý', 'dathqpc00844@fpt.edu.vn');
INSERT INTO accounts VALUES('moderator','123',N'Nguyễn Văn B', 'datlmhgtc1@gmail.com');
INSERT INTO accounts VALUES('QuocDat','123',N'Hồ Quốc Đạt', 'lolhunter1990@gmail.com');

INSERT INTO Authorities(username, Roleid) VALUES('admin', 'AD');
INSERT INTO Authorities(username, Roleid) VALUES('moderator', 'MOD');
INSERT INTO Authorities(username, Roleid) VALUES('QuocDat', 'US');