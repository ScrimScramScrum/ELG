DROP TABLE person_resemble_vote;
DROP TABLE person_multi_vote;
DROP TABLE ovingmultigame;
DROP TABLE ovingresemblegame;
DROP TABLE oving;
DROP TABLE resembletask;
DROP TABLE resembleresult;
DROP TABLE resemblegame;
DROP TABLE multiexercise;
DROP TABLE multiresult;
DROP TABLE multichoicegame;
DROP TABLE personclass;
DROP TABLE classes;
DROP TABLE person;

CREATE TABLE person(
        email VARCHAR(50) NOT NULL,
        fname VARCHAR(40) NOT NULL,
        lname VARCHAR(40) NOT NULL,
        hashPassword VARCHAR(300),
        administrator INT,
        CONSTRAINT person_pk PRIMARY KEY (email));

CREATE TABLE classes(   
classname VARCHAR(20) NOT NULL,
CONSTRAINT classes_pk PRIMARY KEY(classname));

CREATE TABLE personclass(
email VARCHAR(50) NOT NULL,
classname VARCHAR(20) NOT NULL,
CONSTRAINT persclass_pk PRIMARY KEY(email, classname));

CREATE TABLE multiresult(
        idResult INTEGER GENERATED ALWAYS AS IDENTITY,
        score INTEGER,
        email VARCHAR(50),
        idGame INTEGER,
        CONSTRAINT multiResult_pk PRIMARY KEY (idResult));

CREATE TABLE oving(
        idOving INTEGER GENERATED ALWAYS AS IDENTITY,
        ovingname VARCHAR(40),
        CONSTRAINT oving_pk PRIMARY KEY(idOving));

CREATE TABLE ovingresemblegame(
        idOving INTEGER,
        idGameResemble INTEGER,
isExtra INTEGER,
        CONSTRAINT ovingresemble_pk PRIMARY KEY(idOving, idGameResemble));

CREATE TABLE ovingmultigame(
        idOving INTEGER,
        idGameMulti INTEGER,
isExtra INTEGER,
        CONSTRAINT ovingmulti_pk PRIMARY KEY(idOving, idGameMulti));

CREATE TABLE multichoicegame(
        idGame INTEGER GENERATED ALWAYS AS IDENTITY,
        gamename VARCHAR(40),
        info VARCHAR(600),
        learning_goals VARCHAR(600),
        difficulty INTEGER,
        creator_id VARCHAR(50),
        CONSTRAINT multigame_pk PRIMARY KEY (idGame));

CREATE TABLE multiexercise(
        idExercise INTEGER GENERATED ALWAYS AS IDENTITY,
        alternative_1 VARCHAR(400) NOT NULL,
        alternative_2 VARCHAR(400) NOT NULL,
        alternative_3 VARCHAR(400) NOT NULL,
        alternative_4 VARCHAR(400) NOT NULL,
        solution VARCHAR(400) NOT NULL,
        task_text VARCHAR(400) NOT NULL,
        idGame INTEGER,
        CONSTRAINT multiexercise_pk PRIMARY KEY(idExercise));

CREATE TABLE resembleresult(
        idResembleResult INTEGER GENERATED ALWAYS AS IDENTITY,
        score INTEGER,
        email VARCHAR(50),
        idGame INTEGER,
        CONSTRAINT resembleResult_pk PRIMARY KEY (idResembleResult));

CREATE TABLE resemblegame(
        idGame INTEGER GENERATED ALWAYS AS IDENTITY,
        gamename VARCHAR(40),
        info VARCHAR(600),
        learning_goals VARCHAR(600),
        difficulty INTEGER,
        creator_id VARCHAR(50) NOT NULL,
        CONSTRAINT resemblegame_pk PRIMARY KEY (idGame));

CREATE TABLE resembletask(
        idTask INTEGER GENERATED ALWAYS AS IDENTITY,
        taskText VARCHAR(400) NOT NULL,
        solutionHTML VARCHAR(10000) NOT NULL,
        solutionCSS VARCHAR(10000) NOT NULL,
        startingHTML VARCHAR(10000) NOT NULL,
        startingCSS VARCHAR(10000) NOT NULL,
        width INTEGER,
        height INTEGER,
        idGame INTEGER,
        CONSTRAINT resembletask_pk PRIMARY KEY (idTask));

CREATE TABLE person_resemble_vote(
        idResembleGame INTEGER NOT NULL, 
        idPerson VARCHAR(50) NOT NULL, 
        CONSTRAINT person_resemble_vote_pk PRIMARY KEY(idResembleGame, idPerson));

CREATE TABLE person_multi_vote(
        idMultiGame INTEGER NOT NULL, 
        idPerson VARCHAR(50) NOT NULL, 
        CONSTRAINT person_multi_vote_pk PRIMARY KEY(idMultiGame, idPerson));


ALTER TABLE person_resemble_vote 
ADD CONSTRAINT person_resemble_vote_gameid_fk FOREIGN KEY(idResembleGame) REFERENCES resemblegame(idGame);

ALTER TABLE person_resemble_vote 
ADD CONSTRAINT person_resemble_vote_person_fk FOREIGN KEY(idPerson) REFERENCES person(email);

ALTER TABLE person_multi_vote 
ADD CONSTRAINT person_multi_vote_gameid_fk FOREIGN KEY(idMultiGame) REFERENCES multichoicegame(idGame);

ALTER TABLE person_multi_vote 
ADD CONSTRAINT person_multi_vote_person_fk FOREIGN KEY(idPerson) REFERENCES person(email);

ALTER TABLE multiresult
ADD CONSTRAINT person_fk FOREIGN KEY(email) REFERENCES person(email);

ALTER TABLE multiresult
ADD CONSTRAINT multichoicegame_fk FOREIGN KEY(idGame) REFERENCES multichoicegame(idGame);

ALTER TABLE multiexercise
ADD CONSTRAINT multichoicegame2_fk FOREIGN KEY(idGame) REFERENCES multichoicegame(idGame);

ALTER TABLE resembleresult
ADD CONSTRAINT person_fk2 FOREIGN KEY(email) REFERENCES person(email);

ALTER TABLE resembleresult
ADD CONSTRAINT resemblegame_fk FOREIGN KEY(idGame) REFERENCES resemblegame(idGame);

ALTER TABLE resemblegame
ADD CONSTRAINT creatorID_fk FOREIGN KEY(creator_id) REFERENCES person(email);

ALTER TABLE multichoicegame
ADD CONSTRAINT creatorID_fk2 FOREIGN KEY(creator_id) REFERENCES person(email);

ALTER TABLE resembletask
ADD CONSTRAINT resemblegame2_fk FOREIGN KEY(idGame) REFERENCES resemblegame(idGame);

ALTER TABLE personclass
ADD CONSTRAINT person3_fk FOREIGN KEY (email) REFERENCES person(email);

ALTER TABLE personclass
ADD CONSTRAINT class_fk FOREIGN KEY (classname) REFERENCES classes(classname);

ALTER TABLE ovingresemblegame
ADD CONSTRAINT ovingresemblegame_fk FOREIGN KEY (idGameResemble) REFERENCES resemblegame(idGame);

ALTER TABLE ovingmultigame
ADD CONSTRAINT ovingmultigame_fk FOREIGN KEY (idOving) REFERENCES oving(idOving);