-- --------------------- 1.Forms ---------------------------------------------

CREATE TABLE Form 
(
    id              INTEGER         NOT NULL     AUTO_INCREMENT,
    name            VARCHAR(255)    NOT NULL,

	CONSTRAINT pk_form PRIMARY KEY(id)
) ENGINE=InnoDB;
ALTER TABLE Form AUTO_INCREMENT = 101;

-- --------------------- 2.Industry fields --------------------------------

CREATE TABLE IndustryField
(
    id              INTEGER         NOT NULL     AUTO_INCREMENT,
    name            VARCHAR(255)    NOT NULL,
    priority        INTEGER         NOT NULL,
    iconFilePath    VARCHAR(255)    NOT NULL,

    CONSTRAINT pk_industryfield PRIMARY KEY(id)
) ENGINE=InnoDB;
ALTER TABLE IndustryField AUTO_INCREMENT = 201;

CREATE TABLE IndustryFieldForm
(
    industryFieldId INTEGER         NOT NULL,
    formId          INTEGER         NOT NULL,
    priority        INTEGER         NOT NULL,

    CONSTRAINT pk_industryfieldform PRIMARY KEY(industryFieldId, formId),

    CONSTRAINT fk_industryfieldform_industryfield 
        FOREIGN KEY(industryFieldId) REFERENCES IndustryField(id)
	ON DELETE CASCADE,
    CONSTRAINT fk_industryfieldform_form 
        FOREIGN KEY(formId) REFERENCES Form(id)
	ON DELETE CASCADE
) ENGINE=InnoDB;

-- --------------------- 3.Form terms ----------------------------------------

CREATE TABLE TermClass
(
    id              INTEGER         NOT NULL,

    CONSTRAINT pk_termclass PRIMARY KEY(id)
) ENGINE=InnoDB;

CREATE TABLE Term
(
    id              INTEGER         NOT NULL     AUTO_INCREMENT,
    name            VARCHAR(255)    NOT NULL,
    termClassId     INTEGER         NOT NULL,

    CONSTRAINT pk_term PRIMARY KEY(id),

    CONSTRAINT fk_term_formterm 
        FOREIGN KEY(termClassId) REFERENCES TermClass(id)
	ON DELETE NO ACTION
) ENGINE=InnoDB;
ALTER TABLE Term AUTO_INCREMENT = 1001;

CREATE TABLE Relevance
(
	relevance       INTEGER         NOT NULL,

	CONSTRAINT pk_relevance PRIMARY KEY(relevance)
) ENGINE=InnoDB;

CREATE TABLE FormTerm
(
    formId          INTEGER         NOT NULL,
    termId          INTEGER         NOT NULL,
    relevance       INTEGER         NOT NULL,

    CONSTRAINT pk_formterm PRIMARY KEY(formId, termId),

    CONSTRAINT fk_formterm_form FOREIGN KEY(formId) REFERENCES Form(id)
	ON DELETE CASCADE,
    CONSTRAINT fk_formterm_term FOREIGN KEY(termId) REFERENCES Term(id)
	ON DELETE NO ACTION,
    CONSTRAINT fk_formterm_relevance 
        FOREIGN KEY(relevance) REFERENCES Relevance(relevance)
	ON DELETE NO ACTION
) ENGINE=InnoDB;

-- --------------------- 4.Form's other options ------------------------------

CREATE TABLE ServiceLevel
(
    id              INTEGER         NOT NULL,
	name            VARCHAR(255)    NOT NULL,
	price           DECIMAL(9,2)    NOT NULL,

    CONSTRAINT pk_servicelevel PRIMARY KEY(id)
) ENGINE=InnoDB;

CREATE TABLE TradeMarkType 
(
    id                     INTEGER         NOT NULL,
    name                   VARCHAR(255)    NOT NULL,
    
    CONSTRAINT pk_trademarktype PRIMARY KEY(id)
) ENGINE=InnoDB;

-- --------------------- 5.Actual Form Submissions ---------------------------

CREATE TABLE FormSubmission
(
    id              INTEGER         NOT NULL     AUTO_INCREMENT,
    formId          INTEGER         NOT NULL,
    serviceLevelId  INTEGER         NOT NULL,
    customerEmail   VARCHAR(255)    NOT NULL,
    textTrademark   VARCHAR(255)    NOT NULL,

    CONSTRAINT pk_formsubmission PRIMARY KEY(id, formId),

    CONSTRAINT fk_formsubmission_form 
        FOREIGN KEY(formId) REFERENCES Form(id)
	ON DELETE NO ACTION,
    CONSTRAINT fk_formsubmission_servicelevel 
        FOREIGN KEY(serviceLevelId) REFERENCES ServiceLevel(id)
	ON DELETE NO ACTION
) ENGINE=InnoDB;
ALTER TABLE FormSubmission  AUTO_INCREMENT = 10001;

CREATE TABLE FormSubmissionTerm
(
    formSubmissionId    INTEGER     NOT NULL,
    termId              INTEGER     NOT NULL,

    CONSTRAINT pk_formsubmissionterm PRIMARY KEY(formSubmissionId, termId),

    CONSTRAINT fk_formsubmissionterm_formsubmission 
        FOREIGN KEY(formSubmissionId) REFERENCES FormSubmission(id)
	ON DELETE CASCADE,
    CONSTRAINT fk_formsubmissionterm_term 
        FOREIGN KEY(termId) REFERENCES Term(id)
	ON DELETE NO ACTION
) ENGINE=InnoDB;

CREATE TABLE FormSubmissionTradeMarkType
(
    formSubmissionId        INTEGER         NOT NULL,
    trademarkId             INTEGER         NOT NULL,
    trademarkValue          VARCHAR(1000)   NOT NULL,    
    
    CONSTRAINT pk_formsubmissiontrademark PRIMARY KEY(formSubmissionId,trademarkId),
    
    CONSTRAINT fk_formsubmissiontrademark_formsubmission FOREIGN KEY (formSubmissionId) REFERENCES FormSubmission(id)
	ON DELETE CASCADE,
    CONSTRAINT fk_formsubmissiontrademark_trademark FOREIGN KEY (trademarkId) REFERENCES TradeMarkType(id)
	ON DELETE NO ACTION
) ENGINE=InnoDB;

-- --------------------- 6.FormSubmission history ----------------------------

CREATE TABLE EventType
(
	id                     INTEGER         NOT NULL,
    name                   VARCHAR(255)    NOT NULL,
    contacted              BOOLEAN         NOT NULL,

	CONSTRAINT pk_eventtype PRIMARY KEY(id)
) ENGINE=InnoDB;

CREATE  TABLE Users (
	id                      INTEGER         NOT NULL     AUTO_INCREMENT,
  	username               	VARCHAR(45) 	NOT NULL,
  	password 				VARCHAR(45) 	NOT NULL,
  	firstName 				VARCHAR(45) 	NOT NULL,
  	lastName               	VARCHAR(255) 	NOT NULL,
	email                  	VARCHAR(255) 	NOT NULL UNIQUE,
	phone                  	VARCHAR(255) 	NOT NULL,
  	enabled TINYINT NOT NULL DEFAULT 1 ,
  PRIMARY KEY (username),
  UNIQUE KEY uni_id (id))ENGINE=InnoDB;
  
CREATE TABLE UserRoles (
  user_role_id int(11) NOT NULL AUTO_INCREMENT,
  username varchar(45) NOT NULL,
  role varchar(45) NOT NULL,
  PRIMARY KEY (user_role_id),
  UNIQUE KEY uni_username_role (role,username),
  KEY fk_username_idx (username),
  CONSTRAINT fk_username FOREIGN KEY (username) REFERENCES Users (username));

CREATE TABLE FormSubmissionHistory 
(
    id                      INTEGER         NOT NULL     AUTO_INCREMENT,
    dateAndTime             VARCHAR(45)     NOT NULL,
    empName                 VARCHAR(45)     NOT NULL,         
    formSubmissionId        INTEGER         NOT NULL,
    description             VARCHAR(255)    NOT NULL,
    eventTypeId             INTEGER         NOT NULL,
    

	CONSTRAINT pk_formsubmissionhistory PRIMARY KEY(id),
    
    CONSTRAINT fk_formsubmissionhistory_formsubmission FOREIGN KEY (formSubmissionId) REFERENCES FormSubmission(id)
	ON DELETE CASCADE,
    CONSTRAINT fk_formsubmissionhistory_eventtype FOREIGN KEY (eventTypeId) REFERENCES EventType(id)
	ON DELETE NO ACTION,
    CONSTRAINT fk_formsubmissionhistory_user FOREIGN KEY (empName) REFERENCES Users(username)
	ON DELETE NO ACTION
) ENGINE=InnoDB;
ALTER TABLE FormSubmissionHistory  AUTO_INCREMENT = 30001;


--  -------------------- *** END *** -----------------------------------------


  
