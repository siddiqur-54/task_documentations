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
- 3.2 [Second Normal Form (1NF)](#32-second-normal-form-2nf)
- 3.3 [Third Normal Form (3NF)](#33-third-normal-form-3nf)

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

### Key Points
i. **Determinants**: The attributes on the left side of the arrow in a functional dependency.
ii. **Dependents**: The attributes on the right side of the arrow in a functional dependency.
iii. **Closure**: The set of all attributes functionally determined by a given set of attributes.

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
