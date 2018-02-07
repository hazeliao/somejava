-- ***********************************************************
-- QUERIES USED IN APP

-- ****** Form ****** 
-- Selects all data for Form '103'
SELECT f.id, f.name
	FROM Form f
WHERE f.id = 103;

-- Selects all terms with relevance '1' for Form '103'
SELECT t.name, t.termClassId, ft.relevance
	FROM FormTerm ft JOIN Term t ON (ft.termId = t.id)
WHERE ft.formId = 103 AND ft.relevance = 1
ORDER BY t.name;

-- Selects all terms with relevance '2' for Form '103'
SELECT t.name, t.termClassId, ft.relevance
	FROM FormTerm ft JOIN Term t ON (ft.termId = t.id)
WHERE ft.formId = 103 AND ft.relevance = 2
ORDER BY t.name;

-- EXTRA: All IndustryFields this form belongs into
SELECT ifd.id, ifd.name, ifd.priority, ifd.iconFilePath
	FROM IndustryField ifd
		JOIN IndustryFieldForm ifdf 
		ON ifd.id=ifdf.industryFieldId
WHERE ifdf.formId = 103
ORDER BY ifd.priority ASC, ifd.name ASC;

-- ****** ServiceLevels ******
SELECT id, name, price
	FROM ServiceLevel
ORDER BY price ASC; 

-- ******  FormSubmission ****** 
-- Selects all data for a FormSubmission '10003'
-- WITHOUT the selected terms ....

SELECT fs.id, fs.customerEmail, fs.textTrademark, 
       f.id AS 'formId', f.name AS 'formName', 
       sl.id AS 'serviceLevelId', 
       sl.name AS 'serviceLevelName', 
       sl.price AS 'serviceLevelPrice'
	FROM FormSubmission fs 
		JOIN Form f ON fs.formId = f.id
		JOIN ServiceLevel sl ON fs.serviceLevelId = sl.id
WHERE fs.id=10003;

-- ... And then separately fetches Selects all selected
-- terms for the same FormSubmission '10003'

SELECT t.id, t.name, t.termClassId 
	FROM FormSubmissionTerm fst 
		JOIN Term t ON (fst.termId = t.id)
WHERE fst.formSubmissionId=10003; 

-- ******  IndustryFieldPage ****** 
-- First all IndustryFields order by priority and name
-- REMARK: IF, If, if and iF in SQL mean all IF => reserved
-- word. Thus we need to use alias 'ifd' for IndustryField

SELECT ifd.id, ifd.name, ifd.priority, ifd.iconFilePath
	FROM IndustryField ifd
ORDER BY ifd.priority ASC, ifd.name ASC;

-- Then, using IndustryField id:s one-by-one, we will fetch
-- alls Forms for that IndustryField id, e.g. '202'. 
-- Ordered by Form's priority in that Field 
-- and then by Form name

SELECT f.id, f.name, 
	ifdf.industryFieldId AS 'industryFieldId',
        ifdf.priority
	FROM Form f JOIN IndustryFieldForm ifdf 
		ON (f.id = ifdf.formId)
WHERE ifdf.industryFieldId = 202
ORDER BY ifdf.priority ASC, f.name ASC;

-- ***** EventType ***** --

SELECT et.id, et.name, et.contacted AS "Was customer contacted?"
	FROM EventType et;

-- ***** FormSubmissionHistory ***** --

SELECT fsh.id, fsh.dateAndTime, fsh.empId, fs.id AS "formSubmissionId", fsh.description
	FROM FormSubmissionHistory fsh
		JOIN FormSubmission fs ON fsh.formSubmissionId = fs.Id
	WHERE fsh.formSubmissionId= "10001";

SELECT fsh.empId, fsh.dateAndTime, fs.id
	FROM FormSubmissionHistory fsh
		JOIN FormSubmission fs ON fsh.formSubmissionId = fs.Id
	WHERE fsh.empId= "40001";

-- ***** FormSubmission WITH FormSubmissionHistory ***** --

-- Selecting certain FormSubmission with ALL of its FormSubmissionHistory markings
SELECT fsh.id, fsh.dateAndTime, fsh.empId, fs.id AS "formSubmissionId", fsh.description, et.name AS "Event type", et.contacted
	FROM FormSubmission fs
		JOIN FormSubmissionHistory fsh ON fs.id = fsh.formSubmissionId
		JOIN EventType et ON fsh.eventTypeId = et.id 
	WHERE fs.id= "10004";

-- Selecting certain FormSubmission with just its latest FormSubmissionHistory marking
SELECT fsh.id, fsh.dateAndTime, fsh.empId, fs.id AS "formSubmissionId", fsh.description, et.name AS "Event type", et.contacted
	FROM FormSubmission fs
		JOIN FormSubmissionHistory fsh ON fs.Id = fsh.formSubmissionId
		JOIN EventType et ON fsh.eventTypeId = et.id 
	WHERE fs.id= "10004" AND fsh.dateAndTime = (
					SELECT MAX(fsh2.dateAndTime) 
						FROM FormSubmissionHistory fsh2
						WHERE fsh2.formSubmissionId = fs.id);

-- Selecting certain FormSubmission with just its latest FormSubmissionHistory marking
-- that involved customer contact = When did we last contact the customer
SELECT fsh.id AS "formSubmissionHistoryId", fsh.dateAndTime, fsh.empId, fs.id AS "formSubmissionId", fsh.description, et.name AS "Event type", et.contacted
	FROM FormSubmission fs
		JOIN FormSubmissionHistory fsh ON fs.Id = fsh.formSubmissionId
		JOIN EventType et ON fsh.eventTypeId = et.id 
	WHERE fs.id= "10004" AND (
				fsh.dateAndTime = (
					SELECT MAX(fsh2.dateAndTime) 
						FROM FormSubmissionHistory fsh2
							JOIN EventType et2 ON fsh2.eventTypeId = et2.id 
						WHERE (et2.contacted = 1) AND 
							(fsh2.formSubmissionId = fs.id)
								  ) 
							);


-- Selecting ALL FormSubmissions with just their latest FormSubmissionHistory markings
SELECT fsh.id AS "formSubmissionHistoryId", fsh.dateAndTime, fsh.empId, 
fs.id AS "formSubmissionId", fsh.description, 
et.name AS "Event type", et.contacted
	FROM FormSubmission fs
		LEFT JOIN FormSubmissionHistory fsh ON (fs.Id = fsh.formSubmissionId)
		LEFT JOIN EventType et ON fsh.eventTypeId = et.id 
	WHERE (
				fsh.dateAndTime = NULL 
				OR 
				fsh.dateAndTime = 
					(
						SELECT MAX(fsh2.dateAndTime) 
							FROM FormSubmissionHistory fsh2
							WHERE
								(fsh2.formSubmissionId = fs.id)
					) 
           );

-- Selecting ALL FormSubmissions with LOT OF INFO e.g. their latest FormSubmissionHistory markings
-- TODO!!! In code: For every form submission, history item with event type 20000 should be added.
SELECT f.id AS "fId", f.name AS "formName",
	fs.id AS "fsId", fs.serviceLevelId AS "slId", fs.customerEmail,  
	fsh.id AS "fshId", fsh.dateAndTime, fsh.empId, fsh.description,
	et.name AS "Event type", et.contacted
	FROM Form f
		JOIN FormSubmission fs ON f.id = fs.formId
		LEFT JOIN FormSubmissionHistory fsh ON (fs.Id = fsh.formSubmissionId)
		JOIN EventType et ON fsh.eventTypeId = et.id 
	WHERE (
				fsh.dateAndTime = NULL 
				OR 
				fsh.dateAndTime = 
					(
						SELECT MAX(fsh2.dateAndTime) 
							FROM FormSubmissionHistory fsh2
							WHERE
								(fsh2.formSubmissionId = fs.id)
					) 
           );



-- ****************For checking fast while coding****************

SHOW TABLES;

DESC Form;
SELECT * FROM Form;
SELECT * FROM Form WHERE id=101;

DESC IndustryField;
SELECT * FROM IndustryField;
SELECT * FROM IndustryField WHERE id=201;

DESC IndustryFieldForm;
SELECT * FROM IndustryFieldForm;
SELECT * FROM IndustryFieldForm WHERE industryFieldId=201;
SELECT * FROM IndustryFieldForm WHERE formId=101;


DESC TermClass;
SELECT * FROM TermClass;
SELECT * FROM TermClass WHERE id=57;

DESC Term;
SELECT * FROM Term;
SELECT * FROM Term WHERE id=1001;
SELECT * FROM Term WHERE termClassId=57;

DESC FormTerm;
SELECT * FROM FormTerm;
SELECT * FROM FormTerm WHERE formId=103;
SELECT * FROM FormTerm WHERE termId=1001;


DESC TermClass;
SELECT * FROM TermClass;
SELECT * FROM TermClass WHERE id=57;

DESC Relevance;
SELECT * FROM Relevance;

DESC Term;
SELECT * FROM Term;
SELECT * FROM Term WHERE id=1001;
SELECT * FROM Term WHERE termClassId=57;

DESC FormTerm;
SELECT * FROM FormTerm;
SELECT * FROM FormTerm WHERE formId=103;
SELECT * FROM FormTerm WHERE termId=1001;

DESC ServiceLevel;
SELECT * FROM ServiceLevel;

DESC TradeMarkType;
SELECT * FROM TradeMarkType;
SELECT * FROM TradeMarkType WHERE id=301;


DESC FormSubmission;
SELECT * FROM FormSubmission;
SELECT * FROM FormSubmission WHERE id=10001;

DESC FROM FormSubmissionTerm;
SELECT * FROM FormSubmissionTerm;
SELECT * FROM FormSubmissionTerm WHERE formSubmissionId=10001;
SELECT * FROM FormSubmissionTerm WHERE termId=1001;

DESC FormSubmissionTradeMarkType;
SELECT * FROM FormSubmissionTradeMarkType;


DESC EventType;
SELECT * FROM EventType;
SELECT * FROM EventType WHERE id=20001;

DESC FormSubmissionHistory;
SELECT * FROM FormSubmissionHistory;
SELECT * FROM FormSubmissionHistory WHERE id=30001;
SELECT * FROM FormSubmissionHistory WHERE formSubmissionId=10001;




 
-- *************************************************************
-- * RAW QUERIES FOR TECHNICAL TESTS
-- * JUMBOJOIN Selects certain data with all tables joined 
-- CREATES LARGE sheet of duplicared data, almost like Cartesian product

SELECT Form.id, price, customerEmail, IndustryFieldForm.industryFieldId, termClassId 
FROM ServiceLevel 
JOIN FormSubmission ON ServiceLevel.id = FormSubmission.serviceLevelId
JOIN FormSubmissionTerm ON FormSubmission.id = FormSubmissionTerm.formSubmissionId
JOIN Form ON Form.id = FormSubmission.formId 
JOIN FormTerm ON Form.id = FormTerm.formId
JOIN Relevance ON Relevance.relevance = FormTerm.relevance
JOIN Term ON Term.id = FormTerm.termId
JOIN TermClass ON TermClass.id = Term.termClassId
JOIN IndustryFieldForm ON Form.id = IndustryFieldForm.formId
JOIN IndustryField ON IndustryField.id = IndustryFieldForm.industryFieldId
ORDER BY Form.id;



 -- listing all form submissions, each form field on the page is a link to the full form with. 




