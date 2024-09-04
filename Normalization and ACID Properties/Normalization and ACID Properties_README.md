# Table of Contents

## 1. Different Kinds of Keys
- 1.1 [Super Key](#11-super-key)
- 1.2 [Candidate Key](#12-candidate-key)
- 1.3 [Primary Key](#13-primary-key)
- 1.4 [Composite Key](#14-composite-key)
- 1.5 [Foreign Key](#15-foreign-key)

## 2. Functional Dependencies
- 2.1 [Functional Dependency](#21-functional-dependency)
- 2.2 [Closure of Attributes](#22-closure-of-attributes)

## 3. Normalization
- 3.1 [First Normal Form (1NF)](#31-first-normal-form-1nf)
- 3.2 [Second Normal Form (2NF)](#32-second-normal-form-2nf)
- 3.3 [Third Normal Form (3NF)](#33-third-normal-form-3nf)

## 4. Transaction
- 4.1 [What does a Transaction mean in DBMS?](#41-what-does-a-transaction-mean-in-dbms)
- 4.2 [Operations of Transaction](#42-operations-of-transaction)
- 4.3 [Transaction Schedules](#43-transaction-schedules)
- 4.4 [Serializable](#44-serializable)
- 4.5 [Transaction States](#45-transaction-states)
- 4.6 [Properties of Transaction (ACID)](#46-properties-of-transaction-acid)
    - 4.6.1 [Atomicity](#461-atomicity)
    - 4.6.2 [Consistency](#462-consistency)
    - 4.6.3 [Isolation](#463-isolation)
    - 4.6.4 [Durability](#464-durability)


# 1. Different Kinds of Keys
In relational databases, keys are crucial for identifying and establishing relationships between tables. Here’s a breakdown of the different types of keys you'll encounter:

## 1.1 Super Key
🔼 [Back to Top](#table-of-contents)

A `super key` is a fundamental concept used to uniquely identify a record within a table. A `super key` is a set of one or more columns (attributes) that can uniquely identify a row in a table. This means that no two rows can have the same combination of values in the columns that make up the super key.

### Properties of Super Key

- **Uniqueness**: Every super key must ensure that the combination of values within it uniquely identifies a single row in the table.
- **Includes Primary Key**: Every table's primary key is a super key, but not every super key is a primary key.
- **Multiple Super Keys**: A table can have multiple super keys, each capable of uniquely identifying records.
- **Redundancy**: Super keys can contain extra attributes that aren't necessary for uniqueness. Removing these redundant attributes can result in a candidate key.

**Example**

Consider a table `Employees`:

| ID | Name      | Department | PhoneNumber  | Email              |
|----|-----------|------------|--------------|--------------------|
| 1  | Alice     | SD         | 555-1234     | alice@example.com   |
| 2  | Bob       | IT         | 555-5678     | bob@example.com     |
| 3  | Charlie   | VLSI       | 555-9012     | charlie@example.com |
| 4  | Alice     | HR         | 555-3456     | alice.hr@example.com|

### Super Keys in This Table:

- `(ID)`, `(Email)`, `(PhoneNumber)`: Uniquely identifies each row.
- `(Name, Department)`: Individually, `Name` and `Department` do not uniquely identify rows, but together they do.

**Superset Property**

If `X` is a super key in a relational table, then any superset of `X` is also a super key. This means that if a set of columns `X` uniquely identifies each row in the table, then adding one or more additional columns to `X` will still result in a set of columns that uniquely identifies each row.
- Let `X` be a super key in a table `T`.
- If `Y` is a set of columns where `Y ⊇ X` (i.e., `Y` is a superset of `X`), then `Y` is also a super key.

**Example**

- **Super Key**: `(Email)`
- **Superset**: `(Email, Department)`
  - Adding the `Department` column to the `Email` column creates a superset, which still uniquely identifies each row.
  - Thus, `(Email, Department)` is also a super key.

## 1.2 Candidate Key
🔼 [Back to Top](#table-of-contents)

A `candidate key` is a minimal super key. It is a set of one or more columns that can uniquely identify each record in a table, and none of its proper subsets can be a super key. In other words, a candidate key is a super key without any extraneous attributes.
- Let `R` be a relation (table) with attributes `{A1, A2, ..., An}`. A subset `X` of `{A1, A2, ..., An}` is a **super key** if for any two distinct tuples (rows) `t1` and `t2` in `R`, `t1[X] ≠ t2[X]`.
- A subset `X` of `{A1, A2, ..., An}` is a candidate key if:
  1. `X` is a super key.
  2. No proper subset of `X` is a super key.

### Properties of Candidate Key

- **Uniqueness**: A candidate key uniquely identifies each record in a table.
- **Minimality**: No proper subset of a candidate key can uniquely identify records, meaning there are no redundant attributes.

**Example**

Consider the `Employees` table:

| ID | Name      | Department | PhoneNumber  | Email              |
|----|-----------|------------|--------------|--------------------|
| 1  | Alice     | SD         | 555-1234     | alice@example.com   |
| 2  | Bob       | IT         | 555-5678     | bob@example.com     |
| 3  | Charlie   | VLSI       | 555-9012     | charlie@example.com |
| 4  | Alice     | HR         | 555-3456     | alice.hr@example.com|

- **`{Name, Department}`**
    - `{Name, Department}` is a super key because the combination of `Name` and `Department` uniquely identifies each row.
    - **Proper Subsets:**
        - `{Name}` is not a super key.
        - `{Department}` is not a super key.
    - So, `{Name, Department}` is also a candidate key.

- **`{Email, Name}`**
    - `{Email, Name}` is a super key because the combination of `Email` and `Name` uniquely identifies each row.
    - **Proper Subsets:**
        - `{Email}` is a super key.
        - `{Name}` is not a super key.
    - So, `{Email, Name}` is not a candidate key.

## 1.3 Primary Key
🔼 [Back to Top](#table-of-contents)

A `primary key` is a candidate key chosen to uniquely identify each record in a table.
- It satisfies the conditions of a `candidate key` and is selected as the primary method for record identification.

#### Properties of Primary Key

- **Uniqueness**: Ensures that each row is uniquely identified.
- **Non-null**: All values in the primary key columns must be non-null.
- **Single Primary Key**: Each table can have only one primary key.

**Example**

In the `Employees` table, `ID` could be selected as the primary key:

| ID | Name      | Department | PhoneNumber  | Email              |
|----|-----------|------------|--------------|--------------------|
| 1  | Alice     | SD         | 555-1234     | alice@example.com   |
| 2  | Bob       | IT         | 555-5678     | bob@example.com     |
| 3  | Charlie   | VLSI       | 555-9012     | charlie@example.com |
| 4  | Alice     | HR         | 555-3456     | alice.hr@example.com|

- **Primary Key**: `{ID}` is a primary key as it uniquely identifies each row and is not null.

## 1.4 Composite Key
🔼 [Back to Top](#table-of-contents)

A `composite key` is a super key formed by combining multiple columns to ensure uniqueness.
- None of the individual columns in the composite key alone can uniquely identify the records.

### Properties of Composite Key

- **Uniqueness**: The combination of columns in the composite key must uniquely identify each record.
- **Multi-column**: Involves more than one column.

**Example**

In the `Employees` table, `{Name, Department}` could be a composite key if neither `Name` nor `Department` alone can uniquely identify each row:

| ID | Name      | Department | PhoneNumber  | Email              |
|----|-----------|------------|--------------|--------------------|
| 1  | Alice     | SD         | 555-1234     | alice@example.com   |
| 2  | Bob       | IT         | 555-5678     | bob@example.com     |
| 3  | Charlie   | VLSI       | 555-9012     | charlie@example.com |
| 4  | Alice     | HR         | 555-3456     | alice.hr@example.com|

- **Composite Key**: `{Name, Department}` together uniquely identifies each record.

## 1.5 Foreign Key
🔼 [Back to Top](#table-of-contents)

A `foreign key` is a column or set of columns in one table that references the primary key of another table. It is used to establish and enforce a link between the data in the two tables, ensuring referential integrity.
- It must either match an existing value in the referenced table or be null.

### Properties of Foreign Key

- **Referential Integrity**: Ensures that the value in the foreign key column matches a value in the primary key column of another table or is null.
- **Relationship**: Creates a relationship between tables, enabling data to be related.

**Example**

Assume there is another table `Departments`:

| Department ID | Department Name |
|--------|----------|
| 1      | SD       |
| 2      | IT       |
| 3      | VLSI     |
| 4      | HR       |

In the `Employees` table:

| ID | Name      | Department| PhoneNumber  | Email              |
|----|-----------|------------|--------------|--------------------|
| 1  | Alice     | 1          | 555-1234     | alice@example.com   |
| 2  | Bob       | 2          | 555-5678     | bob@example.com     |
| 3  | Charlie   | 3          | 555-9012     | charlie@example.com |
| 4  | Alice     | 4          | 555-3456     | alice.hr@example.com|

- `{Department}` in the `Employees` table references `Department ID` in the `Departments` table. It ensures that each department in the `Employees` table matches an existing department in the `Departments` table.


# 2. Functional Dependencies
## 2.1 Functional Dependency
🔼 [Back to Top](#table-of-contents)

A **Functional Dependency (FD)** is a fundamental concept in relational database design that describes the relationship between attributes in a relational table. It specifies how one attribute or a group of attributes (the determinant) uniquely determines another attribute or set of attributes.

- Given a relation (table) `R` with attributes `{A_1, A_2, ..., A_n}`, a functional dependency `X → Y` is defined as follows:
  - **X** and **Y** are subsets of the attributes of `R`.
  - **X** is called the determinant, and **Y** is the dependent.
  - **X → Y** means that if two tuples (rows) of `R` agree on the attributes in **X**, then they must also agree on the attributes in **Y**.

**Key Points**

i. **Determinants**: The attributes on the left side of the arrow in a functional dependency.

ii. **Dependents**: The attributes on the right side of the arrow in a functional dependency.

iii. **Closure**: The set of all attributes functionally determined by a given set of attributes.

In other words, if you know the value of **X**, you can uniquely determine the value of **Y**.

### Properties of Functional Dependencies

- **Reflexivity**: If `Y ⊆ X`, then `X → Y`.
- **Augmentation**: If `X → Y`, then `XZ → YZ` for any attribute set `Z`.
- **Transitivity**: If `X → Y` and `Y → Z`, then `X → Z`.

**Examples**

Consider the following table `Students`:

| StudentID | Name    | Major | Advisor  |
|------------|---------|-------|----------|
| 1          | Alice   | CS    | Dr. Smith|
| 2          | Bob     | Math  | Dr. Jones|
| 3          | Charlie | CS    | Dr. Smith|
| 4          | Dave    | Math  | Dr. Jones|

### Functional Dependencies in This Table

- **StudentID → Name, Major, Advisor**: Knowing the `StudentID` uniquely determines `Name`, `Major`, and `Advisor`.
- **Name → Major, Advisor**: Assuming each name is unique, knowing the `Name` will determine the `Major` and `Advisor`.


## 2.2 Closure of Attributes
🔼 [Back to Top](#table-of-contents)

The closure of a set of attributes `X`, denoted `X`<sup>+</sup>, is the set of attributes that can be functionally determined by `X`. It is computed using the given functional dependencies.

### Steps to Compute Closure

i. Initialize the closure `X`<sup>+</sup> to `X`.

ii. For each functional dependency `X → Y` where `X ⊆ X`<sup>+</sup>, add `Y` to `X`<sup>+</sup>.

iii. Repeat until no more attributes can be added.

**Example**

Given the functional dependencies:
- `StudentID → Name, Major, Advisor`
- `Name → Major, Advisor`

To find `{StudentID}`<sup>+</sup>:
i. Start with `{StudentID}`<sup>+</sup> = `{StudentID}`.

ii. Apply `StudentID → Name, Major, Advisor`: Add `Name`, `Major`, `Advisor`.

iii. Final closure: `{StudentID}`<sup>+</sup> = `{StudentID, Name, Major, Advisor}`.

### Simple Dependency
- **Functional Dependency**: `{EmployeeID} → {Name, Department}`
  - Knowing `EmployeeID` determines `Name` and `Department`.

### Composite Key
- **Functional Dependency**: `{CourseID, StudentID} → {Grade}`
  - Knowing the combination of `CourseID` and `StudentID` determines the `Grade`.


## Normalization
Normalization is a process in database design that organizes data to minimize redundancy and improve data integrity. The primary goal is to decompose tables into smaller tables and define relationships between them to ensure that the database remains consistent and efficient.


# 3. Normalization
🔼 [Back to Top](#table-of-contents)

Normalization is a process in database design that organizes data to minimize redundancy and improve data integrity. The primary goal is to decompose tables into smaller tables and define relationships between them to ensure that the database remains consistent and efficient.

## Key Normal Forms

i. **First Normal Form (1NF)**
   - Ensures that the table has a primary key and that all attributes contain only atomic (indivisible) values.
   - Each column should hold a single value, and each row should be unique.

ii. **Second Normal Form (2NF)**
   - Builds on 1NF by ensuring that all non-key attributes are fully functionally dependent on the entire primary key.
   - This applies to tables with composite keys.

iii. **Third Normal Form (3NF)**
   - Ensures that all non-key attributes are not only fully functionally dependent on the primary key but also are independent of each other.
   - This eliminates transitive dependencies.

iv. **Boyce-Codd Normal Form (BCNF)**
   - A stricter version of 3NF that handles certain anomalies not addressed by 3NF.
   - Specifically focuses on removing redundancy in cases where a table’s primary key is not unique.

v. **Fourth Normal Form (4NF)**
   - Addresses multi-valued dependencies, ensuring that no table contains two or more independent multi-valued facts about an entity.

vi. **Fifth Normal Form (5NF)**
   - Deals with cases where information can be reconstructed from smaller pieces of data without loss of information.
   - Ensures that no redundancy exists.


## 3.1 First Normal Form (1NF)
🔼 [Back to Top](#table-of-contents)

The First Normal Form (1NF) is the foundational step in the normalization process. It focuses on ensuring that a table is structured in such a way that it adheres to the basic principles of relational databases. A table is said to be in First Normal Form (1NF) if:

i. **Atomic Values**: Each column contains only atomic (indivisible) values. This means that every cell in the table holds a single value, not a set or list of values.

ii. **Unique Rows**: Each row in the table is unique. There must be a primary key or a unique identifier for each row to ensure no duplicate records.

iii. **Consistent Data Type**: Each column must contain values of a consistent data type. For example, a column meant to store dates should not have any text or numeric data.

### Key Points

- **Atomicity**: This means breaking down columns that contain multiple values into separate columns or rows. For example, instead of having a single column for "PhoneNumbers" that stores multiple phone numbers separated by commas, you would create separate rows or columns for each phone number.

- **Primary Key**: There must be a primary key that uniquely identifies each record in the table. This ensures that each row is distinct and can be uniquely referenced.

**Example**

Consider a table `Employees` with the following structure:

| ID | Name      | PhoneNumbers          |
|------------|-----------|-------------------------|
| 1          | Alice     | 555-1234, 555-5678      |
| 2          | Bob       | 555-9012                |
| 3          | Charlie   | 555-3456, 555-7890      |

In this table, the `PhoneNumbers` column contains multiple values, which violates the atomicity requirement of 1NF. To bring this table into 1NF, you would need to separate the phone numbers into individual rows:

| ID | Name    | PhoneNumber |
|------------|---------|-------------|
| 1          | Alice   | 555-1234    |
| 1          | Alice   | 555-5678    |
| 2          | Bob     | 555-9012    |
| 3          | Charlie | 555-3456    |
| 3          | Charlie | 555-7890    |

In this revised table:

- Each `PhoneNumber` value is atomic (single value per cell).
- Each row is unique, with `EmployeeID` and `PhoneNumber` forming a composite primary key.

### Benefits of 1NF

- **Reduces Redundancy**: By ensuring that each column contains only atomic values, 1NF helps reduce duplication of data and maintains consistency.

- **Simplifies Data Management**: A table in 1NF is easier to query, update, and maintain, as each piece of data is stored in its most basic form.

## 3.2 Second Normal Form (2NF)
🔼 [Back to Top](#table-of-contents)

The Second Normal Form (2NF) builds upon the First Normal Form (1NF) by addressing partial dependencies. It ensures that all non-key attributes are fully functionally dependent on the entire primary key, which is especially important for tables with composite keys. A table is in Second Normal Form (2NF) if:

i. **It is in First Normal Form (1NF)**: The table must first meet the requirements of 1NF, which means it has atomic values and unique rows.

ii. **Full Functional Dependency**: All non-key attributes are fully functionally dependent on the entire primary key. This means that each non-key attribute must be dependent on the whole composite key and not just a part of it.

### Key Points

- **Prime Attributes**: Attributes that are part of any candidate key. In a table with a composite key, these are the attributes that form the key itself.

- **Non-Prime Attributes**: Attributes that are not part of any candidate key. These are the attributes that are dependent on the prime attributes.

### Partial Dependency

Partial dependency occurs when a non-prime attribute is dependent on only a part of a composite key, rather than on the whole composite key. To achieve 2NF, such partial dependencies must be removed.
- Let `X` be a candidate key and `Y` a non-prime attribute.
- A partial dependency exists if `Y` is dependent on a proper subset of `X`.

**Example**

Consider a table `CourseRegistrations`:

| StudentID | CourseID | StudentName | CourseName    |
|-----------|----------|-------------|---------------|
| 1         | 101      | Alice       | Database      |
| 1         | 102      | Alice       | Algorithms    |
| 2         | 101      | Bob         | Database      |
| 3         | 103      | Charlie     | Operating Systems |

In this table:
- **Composite Primary Key**: `(StudentID, CourseID)`
- **Prime Attributes**: `StudentID`, `CourseID`
- **Non-Prime Attributes**: `StudentName`, `CourseName`

**Partial Dependencies**:
- `StudentName` is dependent only on `StudentID` (part of the composite key).
- `CourseName` is dependent only on `CourseID` (part of the composite key).

### Decomposition to Achieve 2NF
To eliminate partial dependencies, decompose the table into smaller tables:

**Table 1: StudentDetails**

| StudentID | StudentName |
|-----------|-------------|
| 1         | Alice       |
| 2         | Bob         |
| 3         | Charlie     |

**Table 2: CourseDetails**

| CourseID | CourseName          |
|----------|----------------------|
| 101      | Database             |
| 102      | Algorithms           |
| 103      | Operating Systems    |

**Table 3: CourseRegistrations**

| StudentID | CourseID |
|-----------|----------|
| 1         | 101      |
| 1         | 102      |
| 2         | 101      |
| 3         | 103      |


### Benefits of 2NF
- **Eliminates Redundancy**: By removing partial dependencies, 2NF helps avoid redundant data and ensures that non-key attributes are correctly related to the entire primary key.
- **Enhances Data Integrity**: Ensures that non-prime attributes are fully dependent on the complete primary key, which improves the consistency and accuracy of the data.

### 3.3 Third Normal Form (3NF)
🔼 [Back to Top](#table-of-contents)

The Third Normal Form (3NF) builds on the Second Normal Form (2NF) by addressing transitive dependencies. It ensures that all non-key attributes are not only fully functionally dependent on the primary key but also that they are independent of each other. This further refines the structure of a database to ensure that it is free from unnecessary redundancy and maintains data integrity. A table is in Third Normal Form (3NF) if:

i. **It is in Second Normal Form (2NF)**: The table must first meet the requirements of 2NF, meaning it already eliminates partial dependencies.

ii. **No Transitive Dependency**: All non-key attributes must be directly dependent on the primary key and not on other non-key attributes. In other words, non-key attributes should not be dependent on other non-key attributes.

### Key Points
- **Transitive Dependency**: A transitive dependency occurs when a non-key attribute depends on another non-key attribute rather than depending directly on the primary key.

- **Non-Transitive Dependency**: To be in 3NF, non-key attributes must be fully functionally dependent only on the primary key and not on other non-key attributes.

**Example**

Consider the following table `EmployeeDetails`:

| EmployeeID | Name      | Department | DepartmentHead       |
|------------|-----------|------------|----------------|
| 1          | Alice     | HR         | John Doe       |
| 2          | Bob       | IT         | Jane Smith     |
| 3          | Charlie   | HR         | John Doe       |
| 4          | David     | IT         | Jane Smith     |

In this table:
- **Primary Key**: `EmployeeID`
- **Non-Key Attributes**: `Name`, `Department`, `DepartmentHead`

**Transitive Dependency**:
- `DepartmentHead` is dependent on `Department`, which is a non-key attribute.
- `Department` is dependent on `EmployeeID` (the primary key), but `DepartmentHead` depends indirectly on `EmployeeID` through `Department`.

### Decomposition to Achieve 3NF
To eliminate transitive dependencies, decompose the table into smaller tables:

**Table 1: Employees**

| EmployeeID | Name      | Department |
|------------|-----------|------------|
| 1          | Alice     | HR         |
| 2          | Bob       | IT         |
| 3          | Charlie   | HR         |
| 4          | David     | IT         |

**Table 2: Departments**

| Department | DepartmentHead       |
|------------|----------------|
| HR         | John Doe       |
| IT         | Jane Smith     |

### Benefits of 3NF

- **Eliminates Transitive Dependencies**: By ensuring that non-key attributes are dependent only on the primary key, 3NF removes transitive dependencies that could lead to redundant data.
- **Reduces Redundancy**: Ensures that data is not duplicated across different parts of the database, improving overall efficiency.
- **Improves Data Integrity**: Ensures that all non-key attributes are directly related to the primary key, leading to a more accurate and consistent dataset.


# 4. Transaction
A transaction is a fundamental concept representing a set of logically related operations executed as a single unit. Transactions are essential for handling user requests to access and modify database contents, ensuring the database remains consistent and reliable despite various operations and potential interruptions.

## 4.1 What does a Transaction mean in DBMS?
🔼 [Back to Top](#table-of-contents)
- Transaction can be defined as a set of logically related operations.
-   It is the result of a request made by the user to access the contents of the databaseand perform operations on it.
-   It consists of various operations and has various states in its completion journey.
-   It also has some specific properties that must be followed to keep the database consistent.

## 4.2 Operations of Transaction
🔼 [Back to Top](#table-of-contents)
A user can make different types of requests to access and modify the contents of a database. So, we have different types of operations relating to a transaction. They are discussed as follows:

i. **Read(X)**

- A read operation is used to read the value of **X** from the database and store it in a buffer in the main memory for further actions such as displaying that value.
- Such an operation is performed when a user wishes just to see any content of the database and not make any changes to it. For example, when a user wants to check his/her account’s balance, a read operation would be performed on user’s account balance from the database.

ii. **Write(X)**

- A write operation is used to write the value to the database from the buffer in the main memory. For a write operation to be performed, first a read operation is performed to bring its value in buffer, and then some changes are made to it, **e.g.** some set of arithmetic operations are performed on it according to the user’s request, then to store the modified value back in the database, a write operation is performed.
- **For example**, when a user requests to withdraw some money from his account, his account balance is fetched from the database using a read operation, then the amount to be deducted from the account is subtracted from this value, and then the obtained value is stored back in the database using a write operation.

iii. **Commit**

- This operation in transactions is used to maintain integrity in the database. Due to some failure of power, hardware, or software, etc., a transaction might get interrupted before all its operations are completed. This may cause ambiguity in the database, i.e. it might get inconsistent before and after the transaction.
- To ensure that further operations of any other transaction are performed only after work of the current transaction is done, a commit operation is performed to the changes made by a transaction permanently to the database.

iv. **Rollback**

- This operation is performed to bring the database to the last saved state when any transaction is interrupted in between due to any power, hardware, or software failure.
- In simple words, it can be said that a rollback operation does undo the operations of transactions that were performed before its interruption to achieve a safe state of the database and avoid any kind of ambiguity or inconsistency.

## 4.3 Transaction Schedules
🔼 [Back to Top](#table-of-contents)

When multiple transaction requests are made at the same time, we need to decide their order of execution. Thus, a transaction schedule can be defined as a chronological order of execution of multiple transactions.

There are broadly two types of transaction schedules discussed as follows:

i. **Serial Schedule**

-   In this kind of schedule, when multiple transactions are to be executed, they are executed serially, i.e. at one time only one transaction is executed while others wait for the execution of the current transaction to be completed. This ensures consistency in the database as transactions do not execute simultaneously.
-   But, it increases the waiting time of the transactions in the queue, which in turn lowers the throughput of the system, i.e. number of transactions executed per time.
-   To improve the throughput of the system, another kind of schedule are used which has some more strict rules which help the database to remain consistent even when transactions execute simultaneously.

ii. **Non-Serial Schedule**

-   To reduce the waiting time of transactions in the waiting queue and improve the system efficiency, we use nonserial schedules which allow multiple transactions to start before a transaction is completely executed. This may sometimes result in inconsistency and errors in database operation.
-   So, these errors are handled with specific algorithms to maintain the consistency of the database and improve CPU throughput as well.
-   Non-serial schedules are also sometimes referred to as parallel schedules, as transactions execute in parallel in these kinds of schedules.

## 4.4 Serializable
🔼 [Back to Top](#table-of-contents)

-   Serializability in DBMS is the property of a nonserial schedule that determines whether it would maintain the database consistency or not.
-   The nonserial schedule which ensures that the database would be consistent after the transactions are executed in the order determined by that schedule is said to be Serializable Schedules.
-   The serial schedules always maintain database consistency as a transaction starts only when the execution of the other transaction has been completed under it.
-   Thus, serial schedules are always serializable.

## 4.5 Transaction States
🔼 [Back to Top](#table-of-contents)

A transaction is a series of operations, so various states occur in its completion journey. These are the states which tell about the current state of the transaction and also tell how we will further do the processing in the transactions. These states govern the rules which decide the fate of the transaction whether it will be committed or aborted. 

![Transaction-in-dbms](https://media.geeksforgeeks.org/wp-content/uploads/20230915172115/Transaction-in-dbms.png)

i. **Active**

- It is the first stage of any transaction when it has begun to execute. The execution of the transaction takes place in this state.
- Operations such as insertion, deletion, or updation are performed during this state.
- During this state, the data records are under manipulation and they are not saved to the database, rather they remain somewhere in a buffer in the main memory.

ii. **Partially Committed**

- This state of transaction is achieved when it has completed most of the operations and is executing its final operation.
- It can be a signal to the commit operation, as after the final operation of the transaction completes its execution, the data has to be saved to the database through the commit operation.  
    
- If some kind of error occurs during this state, the transaction goes into a failed state, else it goes into the Committed state.

iii. **Committed**

- This state of transaction is achieved when all the transaction-related operations have been executed successfully along with the Commit operation, i.e. data is saved into the database after the required manipulations in this state. This marks the successful completion of a transaction.

iv. **Failed**

- If any of the transaction-related operations cause an error during the active or partially committed state, further execution of the transaction is stopped and it is brought into a failed state. Here, the database recovery system makes sure that the database is in a consistent state.

v. **Aborted**

- If the error is not resolved in the failed state, then the transaction is aborted and a rollback operation is performed to bring database to the the last saved consistent state. When the transaction is aborted, the database recovery module either restarts the transaction or kills it.


## 4.6 Properties of Transaction (ACID)
🔼 [Back to Top](#table-of-contents)

As transactions deal with accessing and modifying the contents of the database, they must have some basic properties which help maintain the consistency and integrity of the database before and after the transaction. Transactions follow 4 properties, namely, **Atomicity, Consistency, Isolation, and Durability**. Generally, these are referred to as **ACID** properties of transactions in DBMS.

![](https://media.geeksforgeeks.org/wp-content/cdn-uploads/20191121102921/ACID-Properties.jpg)

### 4.6.1 Atomicity
🔼 [Back to Top](#table-of-contents)

The term `atomicity` defines that the data remains atomic. It means if any operation is performed on the data, either it should be performed or executed completely or should not be executed at all. It further means that the operation should not break in between or execute partially. In the case of executing operations on the transaction, the operation should be completely executed and not partially. Each transaction is considered as one unit and either runs to completion or is not executed at all. It involves the following two operations.
- **Abort:** If a transaction aborts, changes made to the database are not visible.  
- **Commit:** If a transaction commits, changes made are visible.

Atomicity is also known as the `All or nothing rule`.

**Example**
Consider the following transaction **T** consisting of **T1** and **T2**: Transfer of 100 from account **X** to account **Y**.

![](https://media.geeksforgeeks.org/wp-content/uploads/11-6.jpg)

If the transaction fails after completion of **T1** but before completion of **T2** ( say, after **write(X)** but before **write(Y)** ), then the amount has been deducted from **X** but not added to **Y**. This results in an inconsistent database state. Therefore, the transaction must be executed in its entirety in order to ensure the correctness of the database state.

### 4.6.2 Consistency
🔼 [Back to Top](#table-of-contents)

The word `consistency` means that the value should remain preserved always. In DBMS, the integrity of the data should be maintained, which means if a change in the database is made, it should remain preserved always. In the case of transactions, the integrity of the data is very essential so that the database remains consistent before and after the transaction. The data should always be correct. It refers to the correctness of a database.

**Example**

Referring to the example above, The total amount before and after the transaction must be maintained.  
Total = 500 + 200 = 700 (**before T occurs**)  
Total = 400 + 300 = 700 (**after T occurs**)  
Therefore, the database is **consistent**. Inconsistency occurs in case **T1** completes but **T2** fails. As a result, T is incomplete.

### 4.6.3 Isolation
🔼 [Back to Top](#table-of-contents)

The term `isolation` means separation. In DBMS, Isolation is the property of a database where no data should affect the other one and may occur concurrently. It means if two operations are being performed on two different databases, they may not affect the value of one another. Changes occurring in a particular transaction will not be visible to any other transaction until that particular change in that transaction is written to memory or has been committed. This property ensures that the execution of transactions concurrently will result in a state that is equivalent to a state achieved these were executed serially in some order.

**Example**

Let **X** = 500, **Y** = 500.  
Consider two transactions **T** and **T".**

![](https://media.geeksforgeeks.org/wp-content/uploads/20210402015259/isolation-300x137.jpg)

Suppose **T** has been executed till **Read (Y)** and then **T"** starts. As a result, interleaving of operations takes place due to which **T"** reads the correct value of **X** but the incorrect value of **Y** and sum computed by  
**T": (X+Y = 50,000+500=50,500)** 
is thus not consistent with the sum at end of the transaction:  
**T: (X+Y = 50,000 + 450 = 50,450)**.  
This results in database inconsistency, due to a loss of 50 units. Hence, transactions must take place in isolation and changes should be visible only after they have been made to the main memory.

#### Read Phenomenas
Isolation levels define the degree to which a transaction must be isolated from the data modifications made by any other transaction in the database system. A transaction isolation level is defined by the following phenomena:

- **i. Dirty Read:** A Dirty read is a situation when a transaction reads data that has not yet been committed. For example, Let’s say transaction 1 updates a row and leaves it uncommitted, meanwhile, Transaction 2 reads the updated row. If transaction 1 rolls back the change, transaction 2 will have read data that is considered never to have existed.
- **ii. Non Repeatable read:** Non Repeatable read occurs when a transaction reads the same row twice and gets a different value each time. For example, suppose transaction T1 reads data. Due to concurrency, another transaction T2 updates the same data and commit, Now if transaction T1 rereads the same data, it will retrieve a different value.
- **iii. Phantom Read:** Phantom Read occurs when two same queries are executed, but the rows retrieved by the two, are different. For example, suppose transaction T1 retrieves a set of rows that satisfy some search criteria. Now, Transaction T2 generates some new rows that match the search criteria for transaction T1. If transaction T1 re-executes the statement that reads the rows, it gets a different set of rows this time.
- **iv. Lost Update:** Lost update occurs when two or more transactions read the same data and then update it, but the final value reflects only the last update, effectively "losing" the earlier changes. This happens because the transactions overwrite each other's updates without awareness of the other transactions' operations.

#### Isolation Levels
Based on these phenomena, The SQL standard defines four isolation levels:

- **i. Read Uncommitted:** Read Uncommitted is the lowest isolation level. In this level, one transaction may read not yet committed changes made by other transactions, thereby allowing dirty reads. At this level, transactions are not isolated from each other.
- **ii. Read Committed:** This isolation level guarantees that any data read is committed at the moment it is read. Thus it does not allow dirty read. The transaction holds a read or write lock on the current row, and thus prevents other transactions from reading, updating, or deleting it.
- **iii. Repeatable Read:** This is the most restrictive isolation level. The transaction holds read locks on all rows it references and writes locks on referenced rows for update and delete actions. Since other transactions cannot read, update or delete these rows, consequently it avoids non-repeatable read.
- **iv. Serializable:** This is the highest isolation level. A serializable execution is guaranteed to be serializable. Serializable execution is defined to be an execution of operations in which concurrently executing transactions appears to be serially executing.

The Table given below clearly depicts the relationship between isolation levels, read phenomena, and locks:

![](https://media.geeksforgeeks.org/wp-content/cdn-uploads/transactnLevel.png)

Anomaly Serializable is not the same as Serializable. That is, it is necessary, but not sufficient that a Serializable schedule should be free of all three phenomena types.

Transaction isolation levels are used in database management systems (DBMS) to control the level of interaction between concurrent transactions.

### 4.6.4 Durability
🔼 [Back to Top](#table-of-contents)

`Durability` ensures the permanency of something. In DBMS, the term `durability` ensures that the data after the successful execution of the operation becomes permanent in the database. These updates now become permanent and are stored in non-volatile memory. The effects of the transaction, thus, are never lost. The durability of the data should be so perfect that even if the system fails or leads to a crash, the database still survives.  For committing the values, the COMMIT command must be used every time we make changes.

**Some important points:**

| **Property**   | **Responsibility for Maintaining Properties** |
|----------------|-----------------------------------------------|
| Atomicity      | Transaction Manager                           |
| Consistency    | Application Programmer                        |
| Isolation      | Concurrency Control Manager                   |
| Durability     | Recovery Manager                              |

Overall, ACID properties provide a framework for ensuring data consistency, integrity, and reliability in DBMS. They ensure that transactions are executed in a reliable and consistent manner, even in the presence of system failures, network issues, or other problems. These properties make DBMS a reliable and efficient tool for managing data in modern organizations.

### 4.6.5 Advantages of ACID Properties

i. **Data Consistency:** ACID properties ensure that the data remains consistent and accurate after any transaction execution.

ii. **Data Integrity:** ACID properties maintain the integrity of the data by ensuring that any changes to the database are permanent and cannot be lost.

iii. **Concurrency Control:** ACID properties help to manage multiple transactions occurring concurrently by preventing interference between them.

iv. **Recovery:** ACID properties ensure that in case of any failure or crash, the system can recover the data up to the point of failure or crash.

### 4.6.6 Disadvantages of ACID Properties

i. **Performance:** The ACID properties can cause a performance overhead in the system, as they require additional processing to ensure data consistency and integrity.

ii. **Scalability:** The ACID properties may cause scalability issues in large distributed systems where multiple transactions occur concurrently.

iii. **Complexity:** Implementing the ACID properties can increase the complexity of the system and require significant expertise and resources.

Overall, the advantages of ACID properties in DBMS outweigh the disadvantages. They provide a reliable and consistent approach to data management, ensuring data integrity, accuracy, and reliability. However, in some cases, the overhead of implementing ACID properties can cause performance and scalability issues. Therefore, it’s important to balance the benefits of ACID properties against the specific needs and requirements of the system.
