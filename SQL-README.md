##SQL Basics

CRUD: Create, Read, Update, Delete :: Select, Insert, Update, Delete

Types of Databases:
Hierarchical databases
Network databases
Relational databases
Object-oriented databases
Graph databases
ER model databases
Document databases

SQL is a Relational Database with Tables with rows and columns
Row is like a record in a table
Column is a field in the table
Every table must have a unique key. Tables keys are used to create relationships
between different tables.  

Semicolons and Caps are optional. Better to keep them.
Single quotes for strings only. For everything else double quotes

SELECT: Retrieves data from data source
AS: Keyword to identify column
ORDER BY {key} (Only after WHERE)

Entire database of SQL is stored in dynamic memory and is fast because of 
in-memory processing

##SQL Queries

-- Select statement examples (World.db)
SELECT Name, Region 'Reg', Continent AS 'Cont' 
    FROM Country
    WHERE Continent = 'Europe'
    ORDER BY Name
    LIMIT 5 OFFSET 5
    
SELECT Name, Continent, Population 
    FROM Country 
    WHERE Population < 100000 AND Continent = 'Oceania' 
    ORDER BY Population DESC;

-- Contains island or has second letter a
SELECT Name, Continent, Population 
    FROM Country 
    WHERE Name LIKE '%island%' OR '_a%' 
    ORDER BY Name DESC, Continent;

SELECT Name, Continent, Population 
    FROM Country 
    WHERE Continent IN ( 'Europe', 'Asia' ) 
    ORDER BY Name;

SELECT DISTINCT Continent 
    FROM Country;
    
SELECT COUNT(DISTINCT Continent)
    FROM Country;
    
SELECT COUNT(*) 
    FROM Country
    WHERE Population > 1000000
    AND Continent = 'North America'
    
-- Count is an aggregate function
SELECT COUNT(LifeExpectancy)
    FROM Country
    
SELECT Region, Count(*)
    FROM Country
    GROUP BY Region
    

-- In places where data is not specified, NULL will be defaulted (test.db)
INSERT INTO customer (name, address, city, state, zip) 
    VALUES ('Fred Flintstone', '123 Cobblestone Way', 'Bedrock', 'CA', '91234');

INSERT INTO test 
    VALUES ( 1, 'a' );
    
INSERT INTO test 
    DEFAULT VALUES;
    
-- Will add all values from item into test table
INSERT INTO test ( a, b, c ) 
    SELECT id, name, description from item; 
     
    
UPDATE customer
    SET address = '123 Music Avenue', zip = '98056'
    WHERE id = 5;
    
    
DELETE FROM customer 
    WHERE id = 4;


-- Column name to type mapping
-- Unique accepts 2 NULL values as same
CREATE TABLE test (
  a INTEGER NOT NULL PRIMARY KEY,
  b TEXT UNIQUE NOT NULL,
  c TEXT DEFAULT 'Pingu'
);


DROP TABLE IF EXISTS test
ALTER TABLE test 
    ADD d TEXT DEFAULT 'Notzerovalue'


-- Null refers to empty value or no value present
-- Can be checked only through IS or IS NOT
-- Different from '' which represents empty string
SELECT * 
    FROM test 
    WHERE a = NULL;

SELECT * 
    FROM test 
    WHERE a IS NULL;  
    
    
-- Case Expressions example:
CREATE TABLE booltest (a INTEGER, b INTEGER);
INSERT INTO booltest VALUES (1, 0);
SELECT * FROM booltest;

-- When non zero, true, else false
SELECT
    CASE WHEN a THEN 'true' ELSE 'false' END as boolA,
    CASE WHEN b THEN 'true' ELSE 'false' END as boolB
    FROM booltest;

-- When 1 then true otherwise false
SELECT
    CASE a WHEN 1 THEN 'true' ELSE 'false' END AS boolA,
    CASE b WHEN 1 THEN 'true' ELSE 'false' END AS boolB 
    FROM booltest;
    

-- Join table examples (test.db)
-- Plain Join displays A intersection B only
SELECT * 
    FROM sale;
SELECT * 
    FROM item;
INSERT INTO customer ( name ) 
    VALUES ( 'Jane Smith' );
SELECT * 
    FROM customer;

SELECT s.id, s.customer_id, s.date, i.name, i.description, s.quantity, s.price
    FROM sale AS s
    JOIN item AS i
    ON s.id=i.id

-- Left joins A and A intersection B (Right join is B and B intersection A)
-- Also shows how multiple joins can be implemented in same table
SELECT c.name AS Cust, c.zip, i.name, i.description, s.quantity, s.price
    FROM customer AS c
    LEFT JOIN sale AS s ON s.customer_id = c.id
    LEFT JOIN item AS i ON s.item_id = i.id
    ORDER BY Cust;
    
    
   
-- Having is the same as WHERE but is used for Aggregate functions
-- This is so that we can use WHERE as well whenever needed
-- WHERE usually comes in for String comparisons (album.db)
SELECT a.title AS Album, COUNT(t.trackNumber) as Tracks
    FROM track AS t
    JOIN album AS a
    ON a.id = t.album_id
    GROUP BY a.id
    HAVING Tracks >= 10
    ORDER BY Tracks DESC, Album; 
    
SELECT Region, AVG(Population) 
    FROM Country 
    GROUP BY Region;
    
SELECT Region, MIN(Population), MAX(Population) 
    FROM Country 
    GROUP BY Region;
    
SELECT Region, SUM(Population) 
    FROM Country 
    GROUP BY Region; 
    

-- Transactions to improve performance and maintain data integrity 
BEGIN TRANSACTION;
INSERT INTO widgetSales ( inv_id, quan, price ) 
    VALUES ( 1, 5, 500 );
UPDATE widgetInventory 
    SET onhand = ( onhand - 5 ) 
    WHERE id = 1;
END TRANSACTION;


-- Date and Time functions
SELECT DATETIME('now');
SELECT DATE('now');
SELECT TIME('now');
SELECT DATETIME('now', '+3 hours', '+27 minutes', '-1 day', '+3 years');


-- Triggers and their use for Timestamps
-- Raise denotes an event in SQL. NEW refers to the latest data that is being updated
CREATE TRIGGER updateWidgetSale BEFORE UPDATE ON widgetSale
    BEGIN
        SELECT RAISE(ROLLBACK, 'cannot update table "widgetSale"') FROM widgetSale
            WHERE id = NEW.id AND reconciled = 1;
    END
;


-- Views are a subset of a table and can be reused within DB
CREATE VIEW trackView AS
  SELECT id, albumId, title, track_number, 
    duration / 60 AS m, duration % 60 AS s FROM track;
SELECT * FROM trackView;
DROP VIEW IF EXISTS trackView;


-- Integers
-- First will be real. Sasdecond will be 0 (int), third will be 0.5
SELECT TYPEOF( 1 + 1.0 );
SELECT 1 / 2;
SELECT 1.0 / 2;
SELECT CAST(1 AS REAL) / 2;
-- Rounding to nearest integer, nearest float with precision
SELECT ROUND(2.55555);
SELECT ROUND(2.55555, 3);


-- String
-- First one will print string (Starting from pos 6)
SELECT SUBSTR('this string', 6);
-- Will keep three as parts of date. Start to end substring
SELECT released,
    SUBSTR(released, 1, 4) AS year,
    SUBSTR(released, 6, 2) AS month,
    SUBSTR(released, 9, 2) AS day
    FROM album
    ORDER BY released
;
SELECT UPPER(Name) 
    FROM City 
    ORDER BY Name;
SELECT LOWER(Name) 
    FROM City 
    ORDER BY Name;
-- Removing spaces
SELECT TRIM('   string   string   ');
SELECT LTRIM('   string   ');
SELECT RTRIM('   string   ');    
