-- --------------------- 1.Forms ---------------------------------------------

DELETE FROM Form;

INSERT INTO Form 
(name) 
	VALUES
("Nioh is an awesome game to play."),
("Pencil is a great tool for writing."),
("Potatoes are a very nice vegetable to eat."),
("Tomatoes are red versions of potatoes."),
("This is gonna be fun to write up."),
("Garen is standing behind me at the moment."),
("I still need to type up a few more."),
("This will be the last."),
("The whiteboard is white."),
("The dog was barking at the cat on the other side of the fence."),

("Sex drew six easy four dear cold deny."),
("Sitting mistake towards his few country ask. You delighted two rapturous."),
("Shed may shy basket."),
("Off nay impossible dispatched partiality unaffect."),
("Ladies talked may shy basket narrow see. Him she distrusts questions sportsmen. Tolerably."), 
("Seen you eyes son show. Far two unaffected one alteration apartments."),
("Furniture on he discourse suspected perpetual."), 
("Power dried her taken place day ought the. Four and our ham west miss."),
("Did sentiments increasing particular."),
("Mr he recurred received prospect in. Wishing."),

("This line was almost the same."),
("The blackboard is black."),
("Mice are tiny animals chased by cats."),
("The bottle is red because it is filled with red water."),
("Janne is a vampire because he keeps repeating the word blood."),
("Potatoes are a touchy subject."),
("This text is a longer than the previous one."),
("No one knows where the pied piper disappeared, but we guess it was with the mice."),
("The ads on this site are a bit annoying and are giving me a headache."),
("Ikea is a nice store with nice stuff and nice people."),
("The chair in front of me has wheels and is white."),
("The desk is also white, but I guess we haven't stated that yet."),
("I am a bit tired from not sleeping enough."),
("Seven hours of sleep is nominal, but eight is even better."),
("I wonder what kind of food we are gonna eat today."),
("I can't believe we are about to eat chili sin carne, what is happening around here?"),
("This text is totally random."),
("This is short."),
("We are almost done, the finish line is close."),
("My level of motivation is increasing."),

("Rubber ducks are ocd-compulsive material objects."),
("Some people like dogs and some people like cats, but others don't like anything."),
("My friend to my right needs breakfast."),
("My friend to my left needs breakfast even more."),
("I gave him a cherry-tomato so he should be fine."),
("Actually, I gave him two."),
("He said cereal almost correctly, in Finnish."),
("He said he tries."),
("Finnish is not stupidly hard, what are you talking about?"),
("Okay, I guess it is for foreigners."),

("Today is the sixteenth of the third of two-thousand and seventeen."),
("The number ninety-six is a random number between 1 and 100."),
("These lines of random code are fun to type."),
("Butter."),
("Bread goes well with butter."),
("Mmmmm, I want lunch now, but I just had breakfast."),
("Superhero movies are overrated."),
("Guardians of the Galaxy 2 is coming soon to theaters near you."), 
("The amazing things are being posted to my address along with the other amazing thing."),
("Totally random."),

("No way! We are almost done!"),
("Smart phones are a thing of the past and future."),
("This project is fun."),
("I got 99 problems, but this project ain't one!");

-- --------------------- 2.Industry fields -----------------------------------

DELETE FROM IndustryField;

INSERT INTO IndustryField
(name, priority, iconFilePath)
	VALUES
("Industry Field One", 110, "fa fa-automobile"),
("Industry Field Two Industry Field Two", 230, "fa fa-bathtub"),
("Industry Field Three Industry Field Three Industry Field Three", 110, "fa fa-battery-3"),
("Industry Field Four Industry Field Four Industry Field Four Industry Field Four", 540, "fa fa-bicycle"),
("Industry Field Five Industry Field Five", 110, "fa fa-bomb"),
("Industry Field Six Industry Field Six Industry Field Six", 230, "fa fa-child"),
("Industry Field Seven Industry Field Seven Industry Field Seven", 110, "fa fa-coffee"),
("Industry Field Eight Industry Field Eight Industry Field Eight Industry Field Eight", 110, "fa fa-diamond"),
("Industry Field Nine Industry Field Nine Industry Field Nine", 540, "fa fa-female"),
("Industry Field Ten Industry Field Ten", 540, "fa fa-flag"),
("Industry Field Eleven Industry Field Eleven Industry Field Eleven", 230, "fa fa-futbol-o"),
("Industry Field Twelve Industry Field Twelve", 110, "fa fa-institution");

DELETE FROM IndustryFieldForm;

INSERT INTO IndustryFieldForm
(industryFieldId, formId, priority) 
	VALUES
(201, 101, 110),
(201, 102, 370),
(202, 103, 230),
(203, 103, 990),
(203, 104, 110),
(201, 106, 990),
(202, 105, 110),
(204, 107, 230),
(203, 108, 370),
(202, 106, 230),
(205, 110, 990),
(207, 111, 990),
(209, 109, 110),
(206, 106, 370),
(208, 112, 990),
(210, 111, 110),
(212, 113, 370), 
(205, 114, 230),
(206, 114, 110),
(206, 107, 110),
(207, 105, 230),
(208, 107, 230),
(209, 111, 110);

-- --------------------- 3.Form terms ----------------------------------------

DELETE FROM TermClass;

INSERT INTO TermClass
(id) 
	VALUES
(57),
(67),
(77),
(87),
(97),
(58),
(68),
(78),
(88),
(98);

DELETE FROM Term;

INSERT INTO Term
(name, termClassId) 
	VALUES
("Term One", 57),
("Term Two", 77),
("Term Three", 87),
("Term Four", 67),
("Term Four Term Four", 57),
("Term Five", 87),
("Term Six", 77),
("Term Four Term Four Term Four", 67),
("Term Three Term Three", 77),
("Term Four Term Four Term Four Term Four", 67),
("Term Nine Term Nine Term Nine Term Nine Term Nine Term Nine Term Nine Term Nine Term Nine", 87),
("Term Two Term Two", 57),
("Term One Term One", 77),
("Term Six Term Six", 77),
("Term Four Term Four Term Four Term Four Term Four", 57),
("Term Four Term Four Term Four Term Four Term Four Term Four", 67),
("Term Seven Term Seven Term Seven Term Seven Term Seven Term Seven Term Seven", 87),
("Term Four Term Four Term Four Term Four", 67),
("Term Three Term Three Term Three", 57),
("Term Eight Term Eight Term Eight Term Eight Term Eight Term Eight Term Eight Term Eight", 87),
("Term Seven Term Seven Term Seven Term Seven Term Seven Term Seven", 57),
("Term Seven Term Seven Term Seven Term Seven Term Seven", 77),
("Term Seven Term Seven Term Seven Term Seven", 67),
("Term Seven Term Seven ", 87),
("Term Seven ", 57),
("Term Seven Term Seven Term Seven", 67),
("Term Seven", 77);

DELETE FROM Relevance;

INSERT INTO Relevance
(relevance) 
	VALUES
(1),
(2);

DELETE FROM FormTerm;

INSERT INTO FormTerm
(formId, termId, relevance) 
	VALUES
(101, 1001, 1),
(101, 1007, 2),
(102, 1002, 2),
(102, 1008, 1),
(103, 1003, 1),
(103, 1002, 1),
(103, 1009, 1),
(103, 1010, 1),
(103, 1012, 1),
(103, 1013, 1),
(103, 1015, 1),
(103, 1011, 1),
(103, 1014, 1),
(103, 1016, 1),
(103, 1017, 2),
(103, 1018, 2),
(103, 1019, 2),
(104, 1004, 1),
(104, 1005, 1),
(104, 1006, 1),
(104, 1007, 1),
(104, 1008, 1),
(104, 1009, 1),
(104, 1010, 1),
(104, 1011, 1),
(104, 1012, 1),
(104, 1013, 1),
(104, 1014, 1),
(104, 1015, 1),
(104, 1016, 1),
(104, 1017, 1),
(104, 1018, 1),
(104, 1001, 2),
(104, 1002, 2),
(104, 1003, 2),
(104, 1019, 2),
(104, 1020, 2),
(104, 1021, 2),
(104, 1022, 2),
(105, 1001, 1),
(106, 1002, 2),
(107, 1002, 1),
(108, 1003, 1),
(109, 1004, 2),
(110, 1005, 2),
(111, 1006, 1),
(112, 1005, 2),
(113, 1007, 2),
(114, 1008, 1);

-- --------------------- 4.Form's other options ------------------------------

DELETE FROM ServiceLevel;

INSERT INTO ServiceLevel
(id, name, price) 
	VALUES
(33, "Complete", 200.00),
(11, "Standard", 49.99),
(22, "Professional", 100.00);

DELETE FROM TradeMarkType;

INSERT INTO TradeMarkType
(id, name)
    VALUES
(301, "Word"),
(302, "Figurative"),
(303, "Other");

-- --------------------- 5.Actual Form Submissions ---------------------------

DELETE FROM FormSubmission;

INSERT INTO FormSubmission
(id, formId, serviceLevelId, customerEmail, textTrademark) 
	VALUES
(10001, 101, 33, "john.johnson@gmail.com", "OfficialGames"),
(10002, 101, 11, "lisa.jones@gmail.com", "FunSchool"),
(10003, 101, 22, "mark.marky@yahoo.com", "RedBarrel"),
(10004, 104, 22, "todd.livingston@hotmail.com", "AfroGym"),
(10005, 104, 11, "aku@vankka.com", "Orange monster"),
(10006, 104, 33, "paul.krugman@crash.biz", "Too Big To Fail");

DELETE FROM FormSubmissionTerm;

INSERT INTO FormSubmissionTerm
(formSubmissionId, termId) 
	VALUES
(10001, 1001),
(10002, 1002),
(10002, 1003),
(10003, 1004),
(10003, 1005),
(10003, 1006),
(10003, 1007),
(10003, 1008),
(10003, 1009),
(10003, 1010),
(10005, 1001),
(10005, 1003),
(10005, 1004),
(10006, 1005);

-- TODO: Rename FormSubmissionTradeMarkType TO FormSubmissionTradeMark
-- _everywhere_
DELETE FROM FormSubmissionTradeMarkType;

INSERT INTO FormSubmissionTradeMarkType
(formSubmissionId, trademarkId, trademarkValue)
    VALUES
(10001, 301, "OfficialGames"),
(10002, 301, "FunSchool"),
(10002, 302, "https://drive.google.com/file/d/0B9TGc1PGCgSsWkMySWJLdk9TY1E/view?usp=sharing"),
(10003, 301, "RedBarrel"),
(10004, 301, "AfroGym"),
(10005, 301, "Orange monster"),
(10006, 301, "Too Big To Fail");

-- --------------------- 6.FormSubmission history ----------------------------


DELETE FROM EventType;
    
INSERT INTO EventType
(id, name, contacted) 
	VALUES
(20000, "***new form submission", false),
(20001, "edited", false),
(20002, "contact with customer", true),
(20003, "edited after customer contact", true),
(20004, "billed", true),
(20005, "payment received", false),
(20006, "submitted to authorities", false);

DELETE FROM Users;

INSERT INTO Users
(id, username, password, firstName, lastName, email, phone, enabled) 
	VALUES
(0, 'N/A', 'N/A', 'N/A', 'N/A', 'N/A', 'N/A', false),
(40001, 'priya', 'pass1234','Aapeli', 'Persson', 'aapeli@beepeli.com', '+3584012345678', true),
(40002, 'naveen', 'pass1234','Bertta', 'Larsson', 'bebe@tamakin.fi', '040-4444444', true),
(40003, 'proof', 'pass1234','Cecilia', 'Svensson', 'sissi@gmail.org', '04545678901', true);


DELETE FROM UserRoles;

INSERT INTO UserRoles (username, role)
VALUES 
('priya', 'ROLE_USER'),
('priya', 'ROLE_ADMIN'),
('naveen', 'ROLE_USER'),
('proof', 'ROLE_USER');



DELETE FROM FormSubmissionHistory;

INSERT INTO FormSubmissionHistory
(dateAndTime, empName, formSubmissionId, description, eventTypeId) 
	VALUES
("2017-08-29 11:23:22", 'N/A', 10001, "***new submission",20000),
("2017-08-29 14:56:59", 'priya', 10001, "started working on it",20001),
("2017-09-02 09:56:03", 'priya', 10001, "checked info",20003),
("2017-09-29 13:56:18", 'priya', 10001, "calculated",20003),
("2017-10-01 09:23:35", 'N/A', 10002, "***new submission",20000),
("2017-10-01 14:03:44", 'naveen', 10002, "began working on it",20001),
("2017-10-02 14:45:07", 'priya', 10002, "changed info",20001),
("2017-10-15 14:47:59", 'proof', 10002, "called Jack London, CEO",20002),
("2017-10-17 18:00:22", 'N/A', 10003, "***new submission",20000),
("2017-10-17 18:58:11", 'naveen', 10003, "started working on it3",20001),
("2017-10-20 14:58:11", 'N/A', 10005, "***new submission",20000),
("2017-10-20 16:26:58", 'priya', 10005, "started working on it3",20001),
("2017-10-14 14:23:21",	'N/A', 10004, "***new submission",20000),
("2017-10-16 16:03:04", 'proof', 10004, "started working on it2",20001),
("2017-10-29 15:36:15", 'naveen', 10004, "called Joe Pesci, CIO, to get clarification",20003),
("2017-10-30 11:32:15", 'proof', 10004, "with 15% discount",20004),
("2017-11-21 15:36:34", 'proof', 10004, "finally they paid",20005),
("2017-12-01 11:47:00", 'N/A', 10006, "***new submission", 20000);

--  -------------------- *** END *** -----------------------------------------

	