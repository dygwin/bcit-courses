//Get the canvas element house and assign it to canvas
var canvas     = document.getElementById("house");
//Get the 2d context of the canvas and assign it to ctx
var ctx        = canvas.getContext("2d");

//X and Y coordinates are zero in the middle of the canvas
var x          = canvas.width / 2;
var y          = canvas.width / 2;

//X and Y coordinates for the smoke
var smokeX     = 140;
var smokeY     = 162;

//Used to move the smoke upwards
var smokeMover = 1;

//Keeps track of the stars X coordinate
var starX = [0, 30, 60, 90, 120, 150, 180, 210, 240, 270]
//Starts the stars off at random Y coordinates and keeps track of them
var starY = [Math.random() * 200, Math.random() * 200,
			 Math.random() * 200, Math.random() * 200,
			 Math.random() * 200, Math.random() * 200,
			 Math.random() * 200, Math.random() * 200,
			 Math.random() * 200, Math.random() * 200]

//The maximum number of snowflakes to be created
var maxSnowflakes = 200;			
//Array that holds the X coordinates for the snow flakes
var snowX         = [];
//Array that holds the Y coordinates for the snow flakes
var snowY         = [];
//Array that holds the radius' of the snow flakes
var snowRadius    = [];

//The radius of the smoke
var smokeRadius   = 1;

//The start and end angles for a full circle
var startAngle    = 0;
var endAngle      = 2 * Math.PI;

//A gradient for the sky
var skyGrd=ctx.createLinearGradient(0,0,0,300);
skyGrd.addColorStop(0,"#020038");
skyGrd.addColorStop(1,"#104D9E");

//A gradient for the moon
var moonGrd=ctx.createLinearGradient(0,0,0,60);
moonGrd.addColorStop(0,"black");
moonGrd.addColorStop(1,"#F5D878");

//A gradient for the smoke
var smokeGrd=ctx.createLinearGradient(0,0,0,50);
smokeGrd.addColorStop(0,"#3B3B3B");
smokeGrd.addColorStop(1,"#858585");

//A gradient for use on the building walls
var brownGrd=ctx.createLinearGradient(0,0,170,0);
brownGrd.addColorStop(0,"black");
brownGrd.addColorStop(1,"#9C6B38");

//A gradient for use on the roof
var roofGrd=ctx.createLinearGradient(0,0,170,0);
roofGrd.addColorStop(0,"black");
roofGrd.addColorStop(1,"#F2F2F2");

//A gradient for use on the windows
var yellowGrd=ctx.createLinearGradient(0,0,0,200);
yellowGrd.addColorStop(0,"black");
yellowGrd.addColorStop(1,"#F5F378");

//Adds 100 snowflakes to the screen to begin, with random attributes
for(var i = 0; i < maxSnowflakes; i++) {
	snowX[i] = Math.random()* canvas.width;
	snowY[i] = Math.random()* canvas.height;
	snowRadius[i] = Math.random()+ 1;
}

//Draws the ground beneath the house
function drawSky() {
	ctx.fillStyle = skyGrd;
	
	ctx.beginPath();
	ctx.rect(0, 0, canvas.height, canvas.width);
	ctx.closePath();
	ctx.fill();
}

//Draws a moon in the sky
function drawMoon() {
	//Set the colour of the moon to grey
	ctx.fillStyle = moonGrd;
	ctx.lineWidth = 1;
	
	//Draws the moon
	ctx.beginPath();
	ctx.arc(x + 100, y - 100, 30, startAngle, endAngle);
	ctx.closePath();
	ctx.fill();
	
	//Sets the colour to match the sky
	ctx.fillStyle = skyGrd;
	ctx.lineWidth = 1;
	
	//Draws a circle on top of the moon to make it look like a half moon
	ctx.beginPath();
	ctx.arc(x + 115, y - 115, 30, startAngle, endAngle);
	ctx.closePath();
	ctx.fill();
}

//A star that updates itself
function stars(i) {
	//Set the colour of the stars to yellow
	ctx.fillStyle = "yellow";
	ctx.lineWidth = 1;
	
	//Draws the stars at the index of starX and starY it was called
	ctx.beginPath();
	ctx.arc(starX[i], starY[i], 1, startAngle, endAngle);
	ctx.closePath();
	ctx.fill();
	
	//Increases the x coordinate of the star each time its called
	starX[i] += 0.1;
	
	//If the star goes off the screen, reset it
	if (starX[i] > 300) {
		//Reposition the star to the left side of the screen
		starX[i] = 0;
		//Give the star a new random Y coordinate
		starY[i] = Math.random() * 200;
	}
}

//Draw 10 stars in the sky
function drawStars() {
	for (var i = 0; i < 10; i++) {
		stars(i);
	}
}

//Draws the ground beneath the house
function drawGround() {
	//Sets up the styling for the ground
	ctx.strokeStyle = "black";
	ctx.fillStyle = "#F2F2F2";
	ctx.lineWidth = 1;
	
	//Ground
	ctx.beginPath();
	//Draw a large circle so that only the top is visible in the canvas
	ctx.arc(x, canvas.height + 425, 500, startAngle, endAngle);
	ctx.closePath();
	ctx.fill();
	ctx.stroke();
}

//Draws the walls of the building
function drawWalls() {
	//Sets up the styling for the walls
	ctx.strokeStyle = "black";
	ctx.fillStyle = brownGrd;
	ctx.lineWidth = 2;
	
	//Back wall
	ctx.beginPath();
	ctx.moveTo(x + 60, y - 30);
	ctx.lineTo(x + 60, y + 100);
	ctx.lineTo(x - 90, y + 100);
	ctx.lineTo(x - 90, y - 30);
	ctx.lineTo(x - 15, y - 80);
	ctx.lineTo(x + 60, y - 30);
	ctx.closePath();
	ctx.fill();
	ctx.stroke();
	
	//Left wall
	ctx.beginPath();
	ctx.moveTo(x - 90, y - 30);
	ctx.lineTo(x - 90, y + 100);
	ctx.lineTo(x - 60, y + 110);
	ctx.lineTo(x - 60, y - 30);
	ctx.closePath();
	ctx.fill();
	ctx.stroke();
	
	//Front wall
	ctx.beginPath();
	ctx.moveTo(x + 90, y - 20);
	ctx.lineTo(x + 90, y + 110);
	ctx.lineTo(x - 60, y + 110);
	ctx.lineTo(x - 60, y - 20);
	ctx.lineTo(x + 15, y - 70);
	ctx.lineTo(x + 90, y - 20);
	ctx.closePath();
	ctx.fill();
	ctx.stroke();
}

//Draws the left side of the roof
function drawLeftRoof() {
	//Sets up the styling
	ctx.strokeStyle = "black";
	ctx.fillStyle = roofGrd;
	ctx.lineWidth = 2;
	
	//Left side of the roof
	ctx.beginPath();
	ctx.moveTo(x - 65, y - 15);
	ctx.lineTo(x + 20, y - 70);
	ctx.lineTo(x - 15, y - 80);
	ctx.lineTo(x - 95, y - 30);
	ctx.lineTo(x - 65, y - 15);
	ctx.closePath();
	ctx.fill();
	ctx.stroke();
}

//Draws the right side of the roof
function drawRightRoof() {
	//Sets up the styling
	ctx.strokeStyle = "black";
	//Fill it black to look like shadows
	ctx.fillStyle = "black";
	ctx.lineWidth = 2;
	
	//Right side of the roof
	ctx.beginPath();
	ctx.moveTo(x + 20, y - 70);
	ctx.lineTo(x + 100, y - 17);
	ctx.lineTo(x + 20, y - 17);
	ctx.lineTo(x + 20, y - 70);
	ctx.closePath();
	ctx.fill();
	ctx.stroke();
}

//Draws the door of the house
function drawDoor() {
	//Sets up the styling
	ctx.strokeStyle = "black";
	ctx.fillStyle = "#73491C";
	ctx.lineWidth = 2;
	
	//Door
	ctx.beginPath();
	ctx.moveTo(x + 30, y + 110);
	ctx.lineTo(x + 30, y + 50);
	ctx.lineTo(x, y + 50);
	ctx.lineTo(x, y + 110);
	ctx.lineTo(x + 30, y + 110);
	ctx.closePath();
	ctx.fill();
	ctx.stroke();
	
	//Door knob
	ctx.fillStyle = "#AD8500";
	ctx.lineWidth = 0.5;
	ctx.beginPath();
	ctx.arc(x + 20, y + 80, 3, 0, 2 * Math.PI);
	ctx.closePath();
	ctx.fill();
	ctx.stroke();
}

//Draws both the windows on the house
function drawWindows() {
	//Sets up the styling
	ctx.strokeStyle = "black";
	ctx.fillStyle = yellowGrd;
	ctx.lineWidth = 2;
	
	//Left window
	ctx.beginPath();
	ctx.moveTo(x - 15, y);
	ctx.arc(x - 30, y, 15, Math.PI, 0);
	ctx.lineTo(x - 15, y + 30);
	ctx.lineTo(x - 45, y + 30);
	ctx.lineTo(x - 45, y);
	ctx.closePath();
	ctx.fill();
	ctx.stroke();
	//Left window frame
	ctx.beginPath();
	ctx.moveTo(x - 30, y - 15);
	ctx.lineTo(x - 30, y + 30);
	ctx.closePath();
	ctx.fill();
	ctx.stroke();
	
	//Right window
	ctx.beginPath();
	ctx.moveTo(x + 75, y);
	ctx.arc(x + 60, y, 15, Math.PI, 0);
	ctx.lineTo(x + 75, y + 30);
	ctx.lineTo(x + 45, y + 30);
	ctx.lineTo(x + 45, y);
	ctx.closePath();
	ctx.fill();
	ctx.stroke();
	//Right window frame
	ctx.beginPath();
	ctx.moveTo(x + 60, y - 15);
	ctx.lineTo(x + 60, y + 30);
	ctx.closePath();
	ctx.fill();
	ctx.stroke();
}

//Draws the chimney on top of the house
function drawChimney() {
	//Sets up the styling for the chimney
	ctx.strokeStyle = "black";
	ctx.fillStyle = "#8C8C8C";
	ctx.lineWidth = 2;
	
	//Chimney right side
	ctx.beginPath();
	ctx.moveTo(x + 65, y - 30);
	ctx.lineTo(x + 65, y - 60);
	ctx.lineTo(x + 50, y - 70);
	ctx.lineTo(x + 50, y - 30);
	ctx.closePath();
	ctx.fill();
	ctx.stroke();
	
	//Chimney left side
	ctx.beginPath();
	ctx.moveTo(x + 55, y - 30);
	ctx.lineTo(x + 55, y - 60);
	ctx.lineTo(x + 40, y - 70);
	ctx.lineTo(x + 40, y - 30);
	ctx.closePath();
	//Change the colour darker to make it look like it has shadows
	ctx.fillStyle = "#3D3D3D";
	ctx.fill();
	ctx.stroke();
	//Change the colour back for the rest of the chimney
	ctx.fillStyle = "#8C8C8C";
	
	//Chimney front
	ctx.beginPath();
	ctx.moveTo(x + 55, y - 60);
	ctx.lineTo(x + 65, y - 60);
	ctx.lineTo(x + 65, y - 60);
	ctx.lineTo(x + 55, y - 60);
	ctx.moveTo(x + 55, y - 60);
	ctx.closePath();
	ctx.fill();
	ctx.stroke();
	
	//Chimney top back
	ctx.beginPath();
	ctx.moveTo(x + 50, y - 75);
	ctx.lineTo(x + 38, y - 75);
	ctx.lineTo(x + 38, y - 70);
	ctx.lineTo(x + 50, y - 70);
	ctx.closePath();
	ctx.fill();
	ctx.stroke();
	
	//Chimney top right
	ctx.beginPath();
	ctx.moveTo(x + 67, y - 60);
	ctx.lineTo(x + 67, y - 65);
	ctx.lineTo(x + 50, y - 75);
	ctx.lineTo(x + 50, y - 70);
	ctx.closePath();
	ctx.fill();
	ctx.stroke();
	
	//Chimney top front
	ctx.beginPath();
	ctx.moveTo(x + 53, y - 60);
	ctx.lineTo(x + 67, y - 60);
	ctx.lineTo(x + 67, y - 65);
	ctx.lineTo(x + 53, y - 65);
	ctx.closePath();
	ctx.fill();
	ctx.stroke();
	
	//Chimney top left
	ctx.beginPath();
	ctx.moveTo(x + 53, y - 60);
	ctx.lineTo(x + 53, y - 65);
	ctx.lineTo(x + 38, y - 75);
	ctx.lineTo(x + 38, y - 70);
	ctx.closePath();
	ctx.fill();
	ctx.stroke();
}

//Draws the path leading to the house
function drawPath() {
	//Sets the colour to light grey
	ctx.fillStyle = "#D1D1D1";
	
	//Top of house
	ctx.beginPath();
	ctx.moveTo(x, y + 111);
	ctx.lineTo(x + 5, canvas.height + 2);
	ctx.lineTo(x + 50, canvas.height + 2);
	ctx.lineTo(x + 30, y + 111);
	ctx.closePath();
	ctx.fill();
}

//Draw and animate the smoke
function updateSmoke() {
	//The styling for the smoke
	ctx.fillStyle = smokeGrd;
	//The starting position of the smoke
	ctx.beginPath();
	ctx.arc(smokeX + 55, smokeY - 75, smokeRadius, startAngle, endAngle);
	ctx.arc(smokeX + 60, smokeY - 80, smokeRadius, startAngle, endAngle);
	ctx.arc(smokeX + 65, smokeY - 75, smokeRadius, startAngle, endAngle);
	ctx.arc(smokeX + 50, smokeY - 85, smokeRadius, startAngle, endAngle);
	ctx.arc(smokeX + 70, smokeY - 90, smokeRadius, startAngle, endAngle);
	ctx.arc(smokeX + 75, smokeY - 75, smokeRadius, startAngle, endAngle);
	ctx.arc(smokeX + 80, smokeY - 80, smokeRadius, startAngle, endAngle);
	ctx.arc(smokeX + 85, smokeY - 80, smokeRadius, startAngle, endAngle);
	ctx.closePath();
	ctx.fill();

	//Update the smoke to make it gets larger and float away
	smokeX += 0.5;
	
	//Move the smoke upwards	
	smokeY -= smokeMover;
	smokeMover += 0.01;
	
	//Increase the radius of each cloud circle until it is 15px
	if (smokeRadius < 15){
		smokeRadius += 1;
	}
	//Reset the smoke after it goes off the top of the screen a while
	if (smokeY < -150){
		smokeX = 140;
		smokeY = 162;
		smokeRadius = 1
		smokeMover = 1;
	}
}

//Function to move and draw the snowflakes
function updateSnow()
{
	//Goes through all 100 snowflakes
	for(var i = 0; i < maxSnowflakes; i++)
	{
		//Updating X and Y coordinates
		//Each particle moves a little to the right or left to simulate snowfall
		snowY[i] += 2;
		snowX[i] += Math.random();
		snowX[i] -= Math.random();
		
		//Sending flakes back to the top when it exits the screen
		if(snowX[i] > canvas.width + 5 || snowX[i] < -5 || snowY[i] > canvas.height)
		{
			snowX[i] = Math.random()* canvas.width;
			snowY[i] = -10;
		}
		
		//Draws the snowflakes onto the canvas
		ctx.fillStyle = "white";
		ctx.beginPath();
		ctx.moveTo(snowX[i], snowY[i]);
		ctx.arc(snowX[i], snowY[i], snowRadius[i], 0, Math.PI * 2, true);
		ctx.fill();
	}
}

//Acts as animating function
//Draws everything and updates positions of all moving parts
function draw() {
	//Clears the canvas
	ctx.clearRect(0, 0, canvas.width, canvas.height);
	
	//Draws and updates everything
	drawSky();
	drawStars();
	drawMoon();
	drawGround();
	drawChimney();
	drawRightRoof();
	drawWalls();
	drawLeftRoof();
	drawDoor();
	drawWindows();
	drawPath();
	updateSmoke();
	updateSnow();
}

//animation loop
setInterval(draw, 25);

