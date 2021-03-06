<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id = "kodelosttags">
<center><h1>K.Odes Lost Tags</h1></center>

<script> 
function Questions(question, answer) {
  this.question = question;
  this.answer = answer; 

}


function Game() {
	this.score = 0;
	this.speed = 25;
	this.questionOnNow = 0;
	this.theQuestions = [oppgave1, oppgave2, oppgave3, oppgave4, oppgave5, oppgave6, oppgave7, oppgave8, oppgave9, oppgave10,oppgave11, oppgave12, oppgave13, oppgave14, oppgave15, oppgave16, oppgave17, oppgave18, oppgave19, oppgave20, oppgave14, oppgave15, oppgave12, oppgave17, oppgave11];
	this.questionDone = [];
	this.questionToBeAsked = [0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20, 10,14,3,5,2,];
	this.round = 0;
        this.correctAnswerCounter = 0; 
        this.wrongAnswerCounter = 0;
        


	this.chooseRandomNumber = function() {	
			this.questionOnNow = this.questionToBeAsked.pop();

	};

	this.isItCorrect = function(tagWeAreOn) {
		if (tagWeAreOn==this.theQuestions[this.questionOnNow].answer){
			console.log("OK");
			this.score++;                           
                        //audioCorrect.play(); 
                        this.correctAnswerCounter = 100;
                        return true;
		} else {
			console.log("ikke OK");
                        //audioWrong.play();
                        this.wrongAnswerCounter = 100;

                        return false;

		}	
	};

	this.createARandomList = function(){
		console.log("lengde:"+this.questionToBeAsked.length);
	 	
		var index;		
		var ant = this.questionToBeAsked.length;
		for (index = 0; index < ant; ++index) {
		    var random = Math.floor((Math.random() * this.questionToBeAsked.length) );
		 	console.log("random: "+random);

		 	var x = this.questionToBeAsked[random];
		 	this.questionToBeAsked.splice(random,1);
			console.log("x: "+x);		 	
			this.questionDone.push(x);
		}
		
		console.log("questionDone: "+this.questionDone);
		console.log("questionToBeAsked: "+this.questionToBeAsked);
		this.questionToBeAsked = this.questionDone;
		this.questionDone = [];
		console.log("questionToBeAsked: "+this.questionToBeAsked);				
	};
}

//TAG1 = <HEAD>
//TAG2 = <STYLE>
//TAG3 = <SCRIPT>
//TAG4 = <BODY>

var oppgave1 = new Questions("<title>", "tag1");
var oppgave2 = new Questions("color: red;", "tag2");
var oppgave3 = new Questions("<h1>", "tag4");
var oppgave4 = new Questions("var x = 1;", "tag3");
var oppgave5 = new Questions("<meta>", "tag1");
var oppgave6 = new Questions("font-weight: bold;", "tag2");
var oppgave7 = new Questions("// Kommentar", "tag3");
var oppgave8 = new Questions("<div>", "tag4");
var oppgave9 = new Questions("<link>", "tag1");
var oppgave10 = new Questions("text-align: center;", "tag2");
var oppgave11 = new Questions("window.onload", "tag3");
var oppgave12 = new Questions("<form>", "tag4");
var oppgave13 = new Questions("<base>", "tag1");
var oppgave14 = new Questions("<title> K.Odes Lost Tags </title>", "tag1");
var oppgave15 = new Questions("function()", "tag3");
var oppgave16 = new Questions("<br>", "tag4");
var oppgave17 = new Questions("<meta>", "tag1");
var oppgave18 = new Questions("float: right;", "tag2");
var oppgave19 = new Questions("<p>", "tag4");
var oppgave20 = new Questions("text-align: center;", "tag2");

console.log("----------");
var audio80 = new Audio("<c:url value="/resources/kOdesLostTags/Sound/kode80bpm.wav.m4a"/>");
var audio100 = new Audio("<c:url value="/resources/kOdesLostTags/Sound/kode100bpm.wav.m4a"/>");
var audio120 = new Audio("<c:url value="/resources/kOdesLostTags/Sound/kode120bpm.wav.m4a"/>");
var audiolevelup = new Audio("<c:url value="/resources/kOdesLostTags/Sound/levelup.wav.m4a"/>");
var audioEnd = new Audio("<c:url value="/resources/kOdesLostTags/Sound/finishedgame.wav.m4a"/>");
var audioStart = new Audio("<c:url value="/resources/kOdesLostTags/Sound/kodesnakker.wav.m4a"/>");
var audioWrong = new Audio("<c:url value="/resources/kOdesLostTags/Sound/wrong.mp3"/>");
var audioCorrect = new Audio("<c:url value="/resources/kOdesLostTags/Sound/right.mp3"/>");



var theGame = new Game();
theGame.createARandomList();
var first = true;
var startGameNow = false;
var failsInGame = 0;


// Create the canvas
var canvas = document.createElement("canvas");
var ctx = canvas.getContext("2d");
canvas.width = 600;
canvas.height = 650;
canvas.marginLeft = "auto";
canvas.marginRight = "auto";
document.getElementById('kodelosttags').appendChild(canvas);

// Background image
var bgReady = false;
var bgImage = new Image();
bgImage.onload = function () {
	bgReady = true;
};
bgImage.src = "<c:url value="/resources/kOdesLostTags/kOdesLostTagsJS/background.png"/>";



// KODE START1 image
var kodeStarter1 = new Image();
kodeStarter1.src = "<c:url value="/resources/kOdesLostTags/kOdesLostTagsJS/kOdesLostTagsImages/kodeStart/kodeGif.gif"/>";


// KODE FINISH image
var kodeFinish = new Image();
kodeFinish.src = "<c:url value="/resources/kOdesLostTags/kOdesLostTagsJS/kOdesLostTagsImages/professork-ode1_360.png"/>";

//TusenTakk
var tusenTakk = new Image();
tusenTakk.src = "<c:url value="/resources/kOdesLostTags/kOdesLostTagsJS/kOdesLostTagsImages/tusenTakk.png"/>";


// FINISH Background image
var bgFinishReady = false;
var bgFinishImage = new Image();
bgFinishImage.onload = function () {
	bgFinishReady = true;
};
bgFinishImage.src = "<c:url value="/resources/kOdesLostTags/kOdesLostTagsJS/backgroundFinish.jpg"/>";

// FINISH Background image
var bgGreenFinishReady = false;
var bgGreenFinishImage = new Image();
bgGreenFinishImage.onload = function () {
	bgGreenFinishReady = true;
};
bgGreenFinishImage.src = "<c:url value="/resources/kOdesLostTags/kOdesLostTagsJS/bkGreen.png"/>";



// Hero image
var heroReady = false;
var heroImage = new Image();
heroImage.onload = function () {
	heroReady = true;
};
heroImage.src = "<c:url value="/resources/kOdesLostTags/kOdesLostTagsJS/kodeUp.png"/>";

//Tag1
var tag1Ready = false;
var tag1Image = new Image();
tag1Image.onload = function () {
	tag1Ready = true;
};
tag1Image.src = "<c:url value="/resources/kOdesLostTags/kOdesLostTagsJS/kOdesLostTagsImages/head.png"/>";


//Tag2
var tag2Ready = false;
var tag2Image = new Image();
tag2Image.onload = function () {
	tag2Ready = true;
};
tag2Image.src = "<c:url value="/resources/kOdesLostTags/kOdesLostTagsJS/kOdesLostTagsImages/style.png"/>";


//Tag3
var tag3Ready = false;
var tag3Image = new Image();
tag3Image.onload = function () {
	tag3Ready = true;
};
tag3Image.src = "<c:url value="/resources/kOdesLostTags/kOdesLostTagsJS/kOdesLostTagsImages/script.png"/>";

//Tag4
var tag4Ready = false;
var tag4Image = new Image();
tag4Image.onload = function () {
	tag4Ready = true;
}
tag4Image.src = "<c:url value="/resources/kOdesLostTags/kOdesLostTagsJS/kOdesLostTagsImages/body.png"/>";



// Monster image
var monsterReady = false;
var monsterImage = new Image();
monsterImage.onload = function () {
	monsterReady = true;
};
monsterImage.src = "monster.png";

// Monster image

var correctImage = new Image();
correctImage.onload = function () {
	
};
correctImage.src = "<c:url value="/resources/images/check.png"/>";


var wrongImage = new Image();
wrongImage.onload = function () {
	
};
wrongImage.src = "<c:url value="/resources/kOdesLostTags/kOdesLostTagsJS/kOdesLostTagsImages/xx.png"/>";


// Game objects
var hero = {
	speed: 500, // movement in pixels per second
	x: 0,
	y: 0
};
var monster = {
	x: 0,
	y: 0, 
	speed: 20
};

var tag1 = {
	x: 0,
	y: 0,
	speed: 50
};

var tag2 = {
	x: 0,
	y: 0,
	speed: 100
};

var tag3 = {
	x: 0,
	y: 0,
	speed: 100
};

var tag4 = {
	x: 0,
	y: 0,
	speed: 100
};


var monstersCaught = 0;

// Handle keyboard controls
var keysDown = {};

addEventListener("keydown", function (e) {
	keysDown[e.keyCode] = true;
}, false);

addEventListener("keyup", function (e) {
	delete keysDown[e.keyCode];
}, false);

// Reset the game when the player catches a monster
var reset = function () {

    
    
    if (theGame.round==5){
        
        audio80.pause();
        audiolevelup.play();
        audio100.play();
        //audio100.fadeIn(0.0, 1.0);
	theGame.speed += 15;

    }
    
    if (theGame.round==10){
        theGame.speed += 35;
        audio100.pause();
        audiolevelup.play();
        audio120.play();
        //audio100.fadeIn(0.0, 1.0);

    } if (theGame.round==15){
        theGame.speed += 25;        
    }
	theGame.chooseRandomNumber()
	theGame.speed += 15;
	theGame.round ++;


	hero.x = canvas.width / 2;
	hero.y = canvas.height - 80;

	tag1.x = 25;
	tag1.y = (canvas.height)-(canvas.height-90);

	tag2.x = 175;
	tag2.y = (canvas.height)-(canvas.height-90);


	tag3.x = 325;
	tag3.y = (canvas.height)-(canvas.height-90);

	tag4.x = 475;
	tag4.y = (canvas.height)-(canvas.height-90);

};

// Update game objects
var update = function (modifier) {
	monster.y += monster.speed * modifier;
	tag1.y += theGame.speed * modifier;
	tag2.y += theGame.speed * modifier;
	tag3.y += theGame.speed * modifier;
	tag4.y += theGame.speed * modifier;


	if (37 in keysDown) { // Player holding left
		hero.x -= hero.speed * modifier;
	}


	if (39 in keysDown) { // Player holding right
		hero.x += hero.speed * modifier;
	}


	// Are they touching?
	if (
		hero.x <= (tag1.x + 100)
		&& tag1.x <= (hero.x + 30)
		&& hero.y <= (tag1.y + 50)
		&& tag1.y <= (hero.y + 30)
	) {
		++monstersCaught;
                if(!theGame.isItCorrect("tag1")){
                    failsInGame++;
                    
                };
		reset();
	}

	// Are they touching?
	if (
		hero.x <= (tag2.x + 100)
		&& tag2.x <= (hero.x + 30)
		&& hero.y <= (tag2.y + 50)
		&& tag2.y <= (hero.y + 30)
	) {
		++monstersCaught;
                if(!theGame.isItCorrect("tag2")){
                    failsInGame++;
                };
                        
		reset();
	}

		// Are they touching?
	if (
		hero.x <= (tag3.x + 100)
		&& tag3.x <= (hero.x + 30)
		&& hero.y <= (tag3.y + 50)
		&& tag3.y <= (hero.y + 30)
	) {
		++monstersCaught;
                if(!theGame.isItCorrect("tag3")){
                    failsInGame++;
                };

		reset();
	}

	// Are they touching?
	if (
		hero.x <= (tag4.x + 100)
		&& tag4.x <= (hero.x + 30)
		&& hero.y <= (tag4.y + 50)
		&& tag4.y <= (hero.y + 30)
	) {
		++monstersCaught;
                if(!theGame.isItCorrect("tag4")){
                    failsInGame++;
                };

		reset();
	}

	if( tag4.y>=600){
		reset();
	}

};


var updateStartKode = function (modifier) {
	if (13 in keysDown) { // Player pressing enter  
            startGameNow = true;            
	}
};

// Draw everything
var render = function () {
    if (bgReady) {
            ctx.drawImage(bgImage, 0, 0);
    }

    if (heroReady) {
            ctx.drawImage(heroImage, hero.x, hero.y);
    }


    if (tag1Ready){
            ctx.drawImage(tag1Image, tag1.x, tag1.y)
    }

    if (tag2Ready){
            ctx.drawImage(tag2Image, tag2.x, tag2.y)
    }

    if (tag3Ready){
            ctx.drawImage(tag3Image, tag3.x, tag3.y)
    }

    if (tag4Ready){
            ctx.drawImage(tag4Image, tag4.x, tag4.y)
    }


    if (theGame.correctAnswerCounter>0){
        ctx.drawImage(correctImage, 125,32);
        theGame.correctAnswerCounter --;
    } 
    if (theGame.wrongAnswerCounter >0){
        ctx.drawImage(wrongImage, 125,32);
        theGame.wrongAnswerCounter --;
    }

    // Score
    ctx.fillStyle = "rgb(250, 250, 250)";
    ctx.font = "20px Helvetica";
    ctx.textAlign = "left";
    ctx.textBaseline = "top";
    ctx.fillText("Poeng: " + theGame.score, 32, 32);
    ctx.fillText("Runde: " + theGame.round, 400, 32);
    ctx.font = "30px Baskerville";
    ctx.fillText(theGame.theQuestions[theGame.questionOnNow].question, 32, 60);

};


var renderFinishScoore = function(won) {
    
    if (bgReady) {		                
            if (theGame.score>15){
                ctx.drawImage(bgGreenFinishImage, 0, 0);
            } else {
                ctx.drawImage(bgFinishImage, 0, 0);
            }

    } 



    ctx.drawImage(kodeFinish, 250, 250);

    ctx.drawImage(tusenTakk, 130, 250);

    ctx.fillStyle = "rgb(250, 250, 250)";
    ctx.font = "24px Helvetica";
    ctx.textAlign = "left";
    ctx.textBaseline = "top";

    if (theGame.score>=25){
        ctx.fillText("GRATULERER du har fullf�rt K.Odes Lost Tags!", 32, 32);
        ctx.fillText("Du fikk " + theGame.score + " poeng", 100, 100);
        ctx.fillText("N� har professor K.Ode endelig f�tt tilbake sine tags!", 50, 600);

    } else if (theGame.score==0) {
        ctx.fillText("Uff... N� har kanskje K. Ode mistet sine tags", 32, 32);
        ctx.fillText("for alltid.... Du fikk bare " + theGame.score + " poeng", 100, 100);
        ctx.fillText("Du burde absolutt jobbe videre!", 50, 600);

    } else if (theGame.score<=5){
        ctx.fillText("Dette var ikke s� bra! ", 32, 32);
        ctx.fillText("Du fikk bare " + theGame.score + " poeng, men er vel tanken som teller.", 32, 100);
        ctx.fillText("Du burde nok jobbe litt til med dette!", 50, 600);


    } else if (theGame.score<=10){
        ctx.fillText("Ikke perfekt men...", 32, 32);
        ctx.fillText("Du fikk " + theGame.score + " poeng", 100, 100);
        ctx.fillText("Professor K.Ode hadde f�tt flere enn dette...", 32, 130);
        ctx.fillText("jobbe videre", 50, 600);


    } else if (theGame.score<=15){
        ctx.fillText("Du er flink!", 32, 32);
        ctx.fillText("Du fikk  " + theGame.score + " poeng", 100, 100);
        ctx.fillText("Ikke lenge til du finner alle!!", 50, 600);


    } else if (theGame.score<=24){
        ctx.fillText("GRATULERER Dette var kjempe bra!", 32, 32);
        ctx.fillText(" Du fikk  " + theGame.score + " poeng", 100, 100);
        ctx.fillText("Du er meget dyktig!", 50, 600);

    }


   setTimeout(function(){ location.reload(); }, 10000);



}




var renderStartGame = function () {
        ctx.drawImage(bgFinishImage, 0, 0);
	ctx.drawImage(kodeStarter1, 250, 250);

        
	ctx.fillStyle = "rgb(250, 250, 250)";
	ctx.font = "24px Helvetica";
	ctx.textAlign = "left";
	ctx.textBaseline = "top";
	ctx.fillText("� nei! Professor Kristian Ode har mistet sine tags.", 10, 32);
        ctx.fillText("Du f�r opp et en kodesnutt, s� skal du velge hvilken ", 10, 64);
	ctx.fillText("kategori denne kodesnutten h�rer til. Lykke til!   ", 10, 96);
        ctx.fillText("Du styrer professor K. Ode med piltastene", 10, 160);
        
        ctx.fillText("Trykk enter for � spille", 10, 600);

}





// The main game loop
var startGameFunc = function () {
    	var now = Date.now();
	var delta = now - then;

	update(delta / 1000);
	render();

	then = now;

	// Request to do this again ASAP
        
        if ((theGame.round >= 26)){
		finishedGame(true);
	} 
        
        else if (failsInGame==3){
            finishedGame(false);
            
        }
	
        else 
        if (startGameNow) {
		requestAnimationFrame(startGameFunc);
	}

};

var finishedGame = function (won) {
        audio120.pause();
        audio100.pause();
        audio80.pause();
        audioEnd.play();
        
	var now = Date.now();
	var delta = now - then;
	
	renderFinishScoore(won);
	then = now;
}

var startGame = function () {
        
        var now = Date.now();
	var delta = now - then;
	
	renderStartGame();
        updateStartKode(delta / 1000);
	then = now;
        //first = false;
        if(!startGameNow){
            requestAnimationFrame(startGame);
 
        } else {
            audioStart.pause();
            audio80.play();
            window.cancelAnimationFrame(startGame)
            startGameFunc();
            requestAnimationFrame(startGameFunc);
            
        }
        
}
// Cross-browser support for requestAnimationFrame
var w = window;
requestAnimationFrame = w.requestAnimationFrame || w.webkitRequestAnimationFrame || w.msRequestAnimationFrame || w.mozRequestAnimationFrame;

// Let's play this game!
var then = Date.now();
reset();
audioStart.play();
startGame();

</script>
</div>