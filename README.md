
## Table Of Contents
- --
* [Development process](#Development-process)
* [Description: When You Run The Program](#Database-structure)
* [Functions](#Functions)
* [Code structrue](#code-structure)
- --

### Development process
#####
* _The development process_: 

* _Architecture_: 
* * The development architecture have I been using `ER-Diagram` for create the tables and work out the __(PK)__ and __(FK)__, and for my own knowledge future on the road, keep things simple and don't do it complex.
* _My SQL Workbench_:
* * I have been using `(My-SQL-Workbench)` in order to create the connection to the database in `(DB-gate)` and implement the table and query for each table.
The configuration and setup have been worked out through the `(My-SQL-Workbench)` eviorment to make sure the `(CRUD)` query's is working for each table.
* _Dependency injection_:
* * Dependecy injection for `(JDBC = mysql-connector)`, `(junit = junit-jupiter-api)` , `(mockito = mockito-core)`.
* _JDBC_:
* * My next step was to set up the java program and implement the tables from the `(ER-diagrams)` and `(My-SQL-Workbench)` environment. To create the right classes and make sure the `(CRUD)` queries where working through java with the database.
* _Test JUNIT/MOCKING_: 
* * I did implement tests for `(CustomerMGR and MobileDevices)` for every `(CRUD)` methode.
* _Creation of the (AWS DB)_
* * The creation of the *AWS DB* in AWS cloud 
* _Implemented AWS DataBase/Connection with JDBC_
* * The implementation through `(JDBC)` simple, the change was to implement a duplication of the `(URL,USERNAME,PASS)` but with different input gained access to the AWS database, crud was completed with `My SQL Workbench  to make sure the connection was completed`.
- --
### Database structure

assignment_mobile_reparation consists of three tables: customers, mobile_devices, and reparations. Here’s a brief description of each:

**customers**: This table stores information about customers. It has the following fields:
- `customer_id`: A unique identifier for each customer. It’s the primary key.
- `customer_name`: The name of the customer. This field is required.
- `customer_phone_number`: The phone number of the customer.
- `customer_address`: The address of the customer.

**mobile_devices**: This table stores information about mobile devices. It has the following fields:
- `mobile_device_id`: A unique identifier for each mobile device. It’s the primary key.
- `imei_number`: The International Mobile Equipment Identity (IMEI) number of the device. This field is required.
- `phone_brand`: The brand of the mobile device. This field is required.
- `model_number`: The model number of the mobile device. This field is required.

**reparations**: This table stores information about reparations. It has the following fields:
- `reparation_id`: A unique identifier for each reparation. It’s the primary key.
- `customer_id`: The ID of the customer who requested the reparation. This field is required and references the `customer_id` in the `customers` table.
- `mobile_device_id`: The ID of the mobile device to be repaired. This field is required and references the `mobile_device_id` in the `mobile_devices` table.
- `employee_first_name`, `employee_last_name`, `employee_number`: Information about the employee who is handling the reparation. These fields are required.
- `reparation_start_date`, `reparation_end_date`: The start and end dates of the reparation. These fields are required.
- `reparation_status`: The status of the reparation. This field is required.
- `reparation_description`: A description of the reparation. This field is required.
- `images`: Images related to the reparation.

The `reparations` table has two foreign keys, `customer_id` and `mobile_device_id`, which create relationships with the `customers` and `mobile_devices` tables, respectively. This means that each reparation is linked to a specific customer and a specific mobile device.


- --
### Functions
#####
* __: 

- --
### Code structure
#####
* __: 

- --