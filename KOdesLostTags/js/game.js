function Questions(question, answer) {
  this.question = question;
  this.answer = answer; 

}
// class methods
Questions.prototype.xxx = function() {

	//somehting
};

var oppgave1 = new Questions("hvor skal normal tekst", "tag1");


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
bgImage.src = "images/background.png";

// Hero image
var heroReady = false;
var heroImage = new Image();
heroImage.onload = function () {
	heroReady = true;
};
heroImage.src = "images/hero.png";




//Tag1
var tag1Ready = false;
var tag1Image = new Image();
tag1Image.onload = function () {
	tag1Ready = true;
}
tag1Image.src = "images/p.png";




//Tag2
var tag2Ready = false;
var tag2Image = new Image();
tag2Image.onload = function () {
	tag2Ready = true;
}
tag2Image.src = "images/style.png";


//Tag3
var tag3Ready = false;
var tag3Image = new Image();
tag3Image.onload = function () {
	tag3Ready = true;
}
tag3Image.src = "images/h1.png";

//Tag4
var tag4Ready = false;
var tag4Image = new Image();
tag4Image.onload = function () {
	tag4Ready = true;
}
tag4Image.src = "images/body.png";





// Monster image
var monsterReady = false;
var monsterImage = new Image();
monsterImage.onload = function () {
	monsterReady = true;
};
monsterImage.src = "images/monster.png";

// Game objects
var hero = {
	speed: 256, // movement in pixels per second
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
	hero.x = canvas.width / 2;
	hero.y = canvas.height - ((canvas.height/4));

		// Throw the monster somewhere on the screen randomly

	//tag1.x = 0;
	//	tag1.y = 0;


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
	tag1.y += tag1.speed * modifier;
	tag2.y += tag1.speed * modifier;
	tag3.y += tag1.speed * modifier;
	tag4.y += tag1.speed * modifier;


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
	ctx.font = "24px Helvetica";
	ctx.textAlign = "left";
	ctx.textBaseline = "top";
	ctx.fillText("Poeng: " + monstersCaught, 32, 32);
	ctx.fillText("Oppgave: "+oppgave1.question ,32,60);
	
	
	

};

// The main game loop
var main = function () {
	var now = Date.now();
	var delta = now - then;

	update(delta / 1000);
	render();

	then = now;

	// Request to do this again ASAP
	requestAnimationFrame(main);
};

// Cross-browser support for requestAnimationFrame
var w = window;
requestAnimationFrame = w.requestAnimationFrame || w.webkitRequestAnimationFrame || w.msRequestAnimationFrame || w.mozRequestAnimationFrame;

// Let's play this game!
var then = Date.now();
reset();
main();
