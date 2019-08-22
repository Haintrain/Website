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
