<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h1>K.Odes Lost Tags</h1>

<script> 
function Questions(question, answer) {
  this.question = question;
  this.answer = answer; 

}


function Game() {
	this.score = 0;
	this.speed = 25;
	this.questionOnNow = 0;
	this.theQuestions = [oppgave1, oppgave2, oppgave3, oppgave4, oppgave5, oppgave6, oppgave7, oppgave8, oppgave9, oppgave10];
	this.questionDone = [];
	this.questionToBeAsked = [0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9];
	this.round = 0;


	this.chooseRandomNumber = function() {	
			this.questionOnNow = this.questionToBeAsked.pop();

	};

	this.isItCorrect = function(tagWeAreOn) {
		if (tagWeAreOn==this.theQuestions[this.questionOnNow].answer){
			console.log("OK");
			this.score++;
		} else {
			console.log("ikke OK");

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


var oppgave1 = new Questions("1. Skal være <P>", "tag1");
var oppgave2 = new Questions("2. skal være <Style>", "tag2");
var oppgave3 = new Questions("3. skal være <h1>", "tag3");
var oppgave4 = new Questions("4. skal være <body>", "tag4");
var oppgave5 = new Questions("5. Skal være <p>", "tag1");
var oppgave6 = new Questions("6. skal være <Style>", "tag2");
var oppgave7 = new Questions("7. skal være <H1>", "tag3");
var oppgave8 = new Questions("8. skal være <body>", "tag4");
var oppgave9 = new Questions("9. Skal være <P>", "tag1");
var oppgave10 = new Questions("10. skal være <Style>", "tag2");


console.log("----------");
var audio80 = new Audio("<c:url value="/resources/kOdesLostTags/Sound/kode80bpm.wav.m4a"/>");
var audio100 = new Audio("<c:url value="/resources/kOdesLostTags/Sound/kode100bpm.wav.m4a"/>");
var audio120 = new Audio("<c:url value="/resources/kOdesLostTags/Sound/kode120bpm.wav.m4a"/>");
var audiolevelup = new Audio("<c:url value="/resources/kOdesLostTags/Sound/levelup.wav.m4a"/>");
var audioEnd = new Audio("<c:url value="/resources/kOdesLostTags/Sound/finishedgame.wav.m4a"/>");
var audioStart = new Audio("<c:url value="/resources/kOdesLostTags/Sound/kodesnakker.wav.m4a"/>");


var theGame = new Game();
theGame.createARandomList();
var first = true;

// Create the canvas
var canvas = document.createElement("canvas");
var ctx = canvas.getContext("2d");
canvas.width = 600;
canvas.height = 650;
document.body.appendChild(canvas);

// Background image
var bgReady = false;
var bgImage = new Image();
bgImage.onload = function () {
	bgReady = true;
};
bgImage.src = "<c:url value="/resources/kOdesLostTags/kOdesLostTagsJS/background.png"/>";

//Users/Hoxmark/Documents/github/ELG/ELG-prosjekt/src/main/webapp/resources/kOdesLostTags/kOdesLostTagsImages/background.png



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
tag1Image.src = "<c:url value="/resources/kOdesLostTags/kOdesLostTagsJS/p.png"/>";


//Tag2
var tag2Ready = false;
var tag2Image = new Image();
tag2Image.onload = function () {
	tag2Ready = true;
};
tag2Image.src = "<c:url value="/resources/kOdesLostTags/kOdesLostTagsJS/style.png"/>";


//Tag3
var tag3Ready = false;
var tag3Image = new Image();
tag3Image.onload = function () {
	tag3Ready = true;
};
tag3Image.src = "<c:url value="/resources/kOdesLostTags/kOdesLostTagsJS/h1.png"/>";

//Tag4
var tag4Ready = false;
var tag4Image = new Image();
tag4Image.onload = function () {
	tag4Ready = true;
}
tag4Image.src = "<c:url value="/resources/kOdesLostTags/kOdesLostTagsJS/body.png"/>";

// Monster image
var monsterReady = false;
var monsterImage = new Image();
monsterImage.onload = function () {
	monsterReady = true;
};
monsterImage.src = "monster.png";

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
		theGame.isItCorrect("tag1");
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
			theGame.isItCorrect("tag2");

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
			theGame.isItCorrect("tag3");

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
			theGame.isItCorrect("tag4");

		reset();
	}

	if( tag4.y>=600){
		reset();
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



	// Score
	ctx.fillStyle = "rgb(250, 250, 250)";
	ctx.font = "20px Helvetica";
	ctx.textAlign = "left";
	ctx.textBaseline = "top";
	ctx.fillText("Poeng: " + theGame.score, 32, 32);
	ctx.fillText("Runde: " + theGame.round, 400, 32);
	ctx.font = "30px Baskerville";
	ctx.fillText("Oppgave: "+ theGame.theQuestions[theGame.questionOnNow].question ,32,80);
	
};


var renderFinishScoore = function() {
    
	if (bgReady) {
		ctx.drawImage(bgFinishImage, 0, 0);
	}
        
        ctx.drawImage(kodeFinish, 250, 250);
        
        ctx.drawImage(tusenTakk, 130, 250);

	ctx.fillStyle = "rgb(250, 250, 250)";
	ctx.font = "24px Helvetica";
	ctx.textAlign = "left";
	ctx.textBaseline = "top";
	ctx.fillText("GRATULERER!", 32, 32);
	ctx.fillText("Du fikk " + theGame.score + " poeng", 100, 100);
	ctx.fillText("Nå har dr. K.Ode endelig fått tilbake tagsene sine!", 50, 600);

}

var renderStartGame = function () {
        ctx.drawImage(bgFinishImage, 0, 0);
	        
        ctx.drawImage(kodeFinish, 250, 250);
        
        
	ctx.fillStyle = "rgb(250, 250, 250)";
	ctx.font = "24px Helvetica";
	ctx.textAlign = "left";
	ctx.textBaseline = "top";
	ctx.fillText("Vi trenger din hjelp til å samle inn taggene til dr. K.Ode!", 10, 32);
	ctx.fillText("Du får opp et en kodesnut, så skal du velge hvilken ", 10, 64);
	ctx.fillText("kategori denne kodesnutten hører til.  ", 10, 96);

        
        //main();
}


// The main game loop
var main = function () {
       
    
    
	var now = Date.now();
	var delta = now - then;

	update(delta / 1000);
	render();

	then = now;

	// Request to do this again ASAP
        if (theGame.round >= 17){
		finishedGame();
	}
	else {
		requestAnimationFrame(main);
	}

};

var finishedGame = function () {
        audio120.pause();
        audioEnd.play();
        
	var now = Date.now();
	var delta = now - then;
	
	renderFinishScoore();
	then = now;
}

var startGame = function () {
        var now = Date.now();
	var delta = now - then;
	
	renderStartGame();
	then = now;
        
        //requestAnimationFrame(startGame);
}




// Cross-browser support for requestAnimationFrame
var w = window;
requestAnimationFrame = w.requestAnimationFrame || w.webkitRequestAnimationFrame || w.msRequestAnimationFrame || w.mozRequestAnimationFrame;

// Let's play this game!
var then = Date.now();
reset();
audio80.play();
 
//startGame();
main();
</script>
