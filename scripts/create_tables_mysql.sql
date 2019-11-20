#sudo apt-get install mysql-server
#systemctl status mysql.service
#mysqladmin -p -u root version

CREATE DATABASE kafka;

connect kafka;

CREATE TABLE account_txn (
    id int(5) NOT NULL AUTO_INCREMENT,
    account varchar(100),
    type varchar(50) DEFAULT NULL,
    created_date TIMESTAMP DEFAULT NOW(),
    PRIMARY KEY(id)
);

CREATE TABLE account_login (
    id int(5) NOT NULL AUTO_INCREMENT,
    account varchar(100),
    channel varchar(50) DEFAULT 'WEB',
    created_date TIMESTAMP DEFAULT NOW(),
    PRIMARY KEY(id)
);

CREATE TABLE account_alerts (
    alert_id int(5) NOT NULL AUTO_INCREMENT,
    type varchar(50) DEFAULT NULL,
    created_date DATE DEFAULT NULL,
    account varchar(100) DEFAULT NULL,
    no_access int(3), 
    details varchar(250) DEFAULT NULL,
    status varchar(10) DEFAULT NULL,
    PRIMARY KEY(alert_id)
);

CREATE TABLE sales (
    id int(5) NOT NULL AUTO_INCREMENT,
    item  varchar(100),
    quantity int(5),
    amount FLOAT(10,7),
    PRIMARY KEY(id)
);


/*

delete from account_txn;
delete from account_login;
delete from account_alerts;

insert into account_txn (id, account, type) VALUES (1,'1001','ATM_WITHDRAWL');
insert into account_login (id, account, channel) VALUES (1,'1001','INTERNET');

CREATE TABLE accounts (
    id int(5),
    name varchar(250) DEFAULT NULL,
    PRIMARY KEY(id)
);
insert into accounts (id, name) VALUES (1,'Sibendu');

CREATE TABLE customers (
    id int(5),
    name varchar(250),
    age int(5),
    last_updated TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY(id)
);

insert into customers values (1, 'Sibendu Das',42, null);

insert into account_alerts (type,created_date,account,no_access,details,status) VALUES ('',CURDATE(),'ABC1',7,'Frequent Access','NEW');

*/
