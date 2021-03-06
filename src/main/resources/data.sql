-- -- -- INSERT INTO section (sectionid, name) VALUES (1, 'Fiction');
-- -- -- INSERT INTO section (sectionid, name) VALUES (2, 'Technology');
-- -- -- INSERT INTO section (sectionid, name) VALUES (3, 'Travel');
-- -- -- INSERT INTO section (sectionid, name) VALUES (4, 'Business');
-- -- -- INSERT INTO section (sectionid, name) VALUES (5, 'Religion');
-- --
-- INSERT INTO authors (authorid, firstname, lastname) VALUES (1, 'John', 'Mitchell');
-- INSERT INTO authors (authorid, firstname, lastname) VALUES (2, 'Dan', 'Brown');
-- INSERT INTO authors (authorid, firstname, lastname) VALUES (3, 'Jerry', 'Poe');
-- INSERT INTO authors (authorid, firstname, lastname) VALUES (4, 'Wells', 'Teague');
-- INSERT INTO authors (authorid, firstname, lastname) VALUES (5, 'George', 'Gallinger');
-- INSERT INTO authors (authorid, firstname, lastname) VALUES (6, 'Ian', 'Stewart');
--
-- INSERT INTO books (bookid, booktitle, isbn, copy) VALUES (1, 'Flatterland', '9780738206752', 2001);
-- INSERT INTO books (bookid, booktitle, isbn, copy) VALUES (2, 'Digital Fortess', '9788489367012', 2007);
-- INSERT INTO books (bookid, booktitle, isbn, copy) VALUES (3, 'The Da Vinci Code', '9780307474278', 2009);
-- INSERT INTO books (bookid, booktitle, isbn, copy) VALUES (4, 'Essentials of Finance', '1314241651234', 0000);
-- INSERT INTO books (bookid, booktitle, isbn, copy) VALUES (5, 'Calling Texas Home', '1885171382134', 2000);
--
-- INSERT INTO wrote (bookid, authorid) VALUES (1, 6);
-- INSERT INTO wrote (bookid, authorid) VALUES (2, 2);
-- INSERT INTO wrote (bookid, authorid) VALUES (3, 2);

-- INSERT INTO wrote (bookid, authorid) VALUES (4, 5);
-- INSERT INTO wrote (bookid, authorid) VALUES (4, 3);
-- INSERT INTO wrote (bookid, authorid) VALUES (5, 4);


-- INSERT INTO guardians (guardianid, firstname, lastname, userid) VALUES (1, 'Rory', 'Murray', 31);
-- INSERT INTO guardians (guardianid, firstname, lastname, userid) VALUES (2, 'Abbey', 'Murray', 32);
-- INSERT INTO guardians (guardianid, firstname, lastname, userid) VALUES (3, 'Tate', 'Murray', 33);

INSERT INTO guardians (guardianid, firstname, lastname) VALUES (1, 'Rory', 'Murray');
INSERT INTO guardians (guardianid, firstname, lastname) VALUES (2, 'Abbey', 'Murray');
INSERT INTO guardians (guardianid, firstname, lastname) VALUES (3, 'Tate', 'Murray');

INSERT INTO doctors (doctorid, doctorname) VALUES (1, 'Mr. Magoo');
INSERT INTO doctors (doctorid, doctorname) VALUES (2, 'Jiminy Cricket');
INSERT INTO doctors (doctorid, doctorname) VALUES (3, 'Guy Guyson');

-- INSERT INTO wards (guardianid, wardid, firstname, lastname) VALUES (1, 1, 'Drew', 'Vandeberghe');
-- INSERT INTO wards (guardianid, wardid, firstname, lastname) VALUES (1, 2, 'Justin', 'Vandeberghe');
-- INSERT INTO wards (guardianid, wardid, firstname, lastname) VALUES (2, 3, 'Dawn', 'Kleser');

INSERT INTO wards (guardianid, wardid, firstname, lastname) VALUES (1, 1, 'Drew', 'Vandeberghe');
INSERT INTO wards (guardianid, wardid, firstname, lastname) VALUES (1, 2, 'Justin', 'Vandeberghe');
INSERT INTO wards (guardianid, wardid, firstname, lastname) VALUES (2, 3, 'Dawn', 'Kleser');

INSERT INTO immunizations (wardid, immunizationid, date, immunizationname, clinic) VALUES (1, 1, CURRENT_DATE, 'Polio', 'Holy Cross');
INSERT INTO immunizations (wardid, immunizationid, date, immunizationname, clinic) VALUES (1, 2, CURRENT_DATE, 'Tetanus', 'St. Thomas');
INSERT INTO immunizations (wardid, immunizationid, date, immunizationname, clinic) VALUES (2, 3, CURRENT_DATE, 'Malaria', 'Sloan Memorial');

INSERT INTO permissions (guardianid, doctorid) VALUES (1, 1);
INSERT INTO permissions (guardianid, doctorid) VALUES (1, 2);
INSERT INTO permissions (guardianid, doctorid) VALUES (2, 3);
INSERT INTO permissions (guardianid, doctorid) VALUES (3, 1);

-- INSERT INTO userGuardians (guardianid, firstname, lastname) VALUES (1, 'Rory', 'Murray', 31);
-- INSERT INTO userGuardians (guardianid, firstname, lastname) VALUES (2, 'Abbey', 'Murray', 32);
-- INSERT INTO usdGuardians (guardianid, firstname, lastname) VALUES (3, 'Tate', 'Murray', 33);

-- INSERT INTO wards (guardianid, wardid) VALUES (1, 1);
-- INSERT INTO wards (guardianid, wardid) VALUES (1, 2);
-- INSERT INTO wards (guardianid, wardid) VALUES (2, 3);






alter sequence hibernate_sequence restart with 25;