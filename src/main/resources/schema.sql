/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  hansp965
 * Created: 20/08/2019
 */

CREATE TABLE Product (
    ProductID varchar(50) not null,
    Name varchar(50) not null,
    Description varchar(50),
    Category varchar(50) not null, 
    ListPrice decimal not null,
    QuantityInStock decimal not null,
    constraint Product_PK primary key (ProductID)
);

CREATE TABLE Customer (
    CustomerID varchar(50) not null auto_increment,
    UserName varchar(50) not null,
    FirstName varchar(50) not null,
    LastName varchar(50) not null,
    Password varchar(50) not null,
    EmailAddress varchar(50),
    ShippingAddress varchar(50),
    constraint Customer_PK primary key (CustomerID)
);

CREATE TABLE Sale (
    SaleID varchar(50) not null auto_increment,
    SaleDate timestamp not null,
    Status varchar(50),
    CustomerID varchar(50) not null,
    constraint Sale_PK primary key (SaleID)
);

CREATE TABLE SaleItem (
    QuantityPurchased varchar(50) not null auto_increment,
    SalePrice varchar(50) not null,
    ProductID varchar(50),
    SaleID varchar(50) not null,
    FOREIGN KEY (SaleID) REFERENCES Sale(SaleID)
);
