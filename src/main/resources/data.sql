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


INSERT INTO guardians (parentid, firstname, lastname) VALUES (1, 'Rory', 'Murray');
INSERT INTO guardians (parentid, firstname, lastname) VALUES (2, 'Abbey', 'Murray');
INSERT INTO guardians (parentid, firstname, lastname) VALUES (3, 'Tate', 'Murray');

INSERT INTO doctors (doctorid, doctorname) VALUES (1, 'Mr. Magoo');
INSERT INTO doctors (doctorid, doctorname) VALUES (2, 'Jiminy Cricket');
INSERT INTO doctors (doctorid, doctorname) VALUES (3, 'Guy Guyson');

INSERT INTO wards (wardid, firstname, lastname) VALUES (1, 'Drew', 'Vandeberghe');
INSERT INTO wards (wardid, firstname, lastname) VALUES (2, 'Justin', 'Vandeberghe');
INSERT INTO wards (wardid, firstname, lastname) VALUES (3, 'Dawn', 'Kleser');

INSERT INTO immunizations (immunizationid, date, immunizationname, clinic) VALUES (1, CURRENT_DATE, 'Polio', 'Holy Cross');
INSERT INTO immunizations (immunizationid, date, immunizationname, clinic) VALUES (2, CURRENT_DATE, 'Tetanus', 'St. Thomas');
INSERT INTO immunizations (immunizationid, date, immunizationname, clinic) VALUES (3, CURRENT_DATE, 'Malaria', 'Sloan Memorial');





alter sequence hibernate_sequence restart with 25;