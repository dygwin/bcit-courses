//Keeps track of whose turn it currently is
var turn;

//Used to determine if the tiles are occupied
var t1Occupied = "none";
var t2Occupied = "none";
var t3Occupied = "none";
var t4Occupied = "none";
var t5Occupied = "none";
var t6Occupied = "none";
var t7Occupied = "none";
var t8Occupied = "none";
var t9Occupied = "none";

//Whether each tile is allowed to be dragged
var dragon1Draggable = true;
var dragon2Draggable = true;
var dragon3Draggable = true;
var dragon4Draggable = true;
var dragon5Draggable = true;
var rabbit1Draggable = true;
var rabbit2Draggable = true;
var rabbit3Draggable = true;
var rabbit4Draggable = true;
var rabbit5Draggable = true;

//The current game piece being selected
var currentChoice;
//The current team who the game piece belongs to
var currentPlayer;

var gameWon = false;

//Generate a random number between 0 and 1
var starter = Math.random();

//Randomly determines which player will start the game
if(starter > 0.5){
	turn = "rabbit";
	document.getElementById("status").innerHTML = "<h2>Rabbit's turn</h2>";
} else {
	turn = "dragon";
	document.getElementById("status").innerHTML = "<h2>Dragon's turn</h2>";
}

//Updates the status div to show whose turn it is
updateStatus();	

//Allows the tiles to have elements dropped into it
function allowDrop(ev) {
	ev.preventDefault();
}

//Ensures the correct information is being transferred for each game piece
function drag(ev) {
	if(turn == "rabbit") {
		if(ev.target.id == "rabbit1" && rabbit1Draggable == true) {
			ev.dataTransfer.setData("Text", ev.target.id);
			currentChoice = "rabbit1";
			currentPlayer = "rabbit";
		} else if(ev.target.id == "rabbit2" && rabbit2Draggable == true) {
			ev.dataTransfer.setData("Text", ev.target.id);
			currentChoice = "rabbit2";
			currentPlayer = "rabbit";
		} else if(ev.target.id == "rabbit3" && rabbit3Draggable == true) {
			ev.dataTransfer.setData("Text", ev.target.id);
			currentChoice = "rabbit3";
			currentPlayer = "rabbit";
		} else if(ev.target.id == "rabbit4" && rabbit4Draggable == true) {
			ev.dataTransfer.setData("Text", ev.target.id);
			currentChoice = "rabbit4";
			currentPlayer = "rabbit";
		} else if(ev.target.id == "rabbit5" && rabbit5Draggable == true) {
			ev.dataTransfer.setData("Text", ev.target.id);
			currentChoice = "rabbit5";
			currentPlayer = "rabbit";
		}
	} else {
		if(ev.target.id == "dragon1" && dragon1Draggable == true) {
			ev.dataTransfer.setData("Text", ev.target.id);
			currentChoice = "dragon1";
			currentPlayer = "dragon";
		} else if(ev.target.id == "dragon2" && dragon2Draggable == true) {
			ev.dataTransfer.setData("Text", ev.target.id);
			currentChoice = "dragon2";
			currentPlayer = "dragon";
		} else if(ev.target.id == "dragon3" && dragon3Draggable == true) {
			ev.dataTransfer.setData("Text", ev.target.id);
			currentChoice = "dragon3";
			currentPlayer = "dragon";
		} else if(ev.target.id == "dragon4" && dragon4Draggable == true) {
			ev.dataTransfer.setData("Text", ev.target.id);
			currentChoice = "dragon4";
			currentPlayer = "dragon";
		} else if(ev.target.id == "dragon5" && dragon5Draggable == true) {
			ev.dataTransfer.setData("Text", ev.target.id);
			currentChoice = "dragon5";
			currentPlayer = "dragon";
		}
	}
}

//Ensures the tile is unoccupied and it is that game pieces turn before being dropped
function dropCheck(ev) {
	if((turn == "rabbit" && currentPlayer == "rabbit") || (turn == "dragon" && currentPlayer == "dragon") ) {
		if(ev.target.id == "t1" && t1Occupied == "none") {
			t1Occupied = currentPlayer;
			drop(ev);
		}
		if(ev.target.id == "t2" && t2Occupied == "none") {
			t2Occupied = currentPlayer;
			drop(ev);
		}
		if(ev.target.id == "t3" && t3Occupied == "none") {
			t3Occupied = currentPlayer;
			drop(ev);
		}
		if(ev.target.id == "t4" && t4Occupied == "none") {
			t4Occupied = currentPlayer;
			drop(ev);
		}
		if(ev.target.id == "t5" && t5Occupied == "none") {
			t5Occupied = currentPlayer;
			drop(ev);
		}
		if(ev.target.id == "t6" && t6Occupied == "none") {
			t6Occupied = currentPlayer;
			drop(ev);
		}
		if(ev.target.id == "t7" && t7Occupied == "none") {
			t7Occupied = currentPlayer;
			drop(ev);
		}
		if(ev.target.id == "t8" && t8Occupied == "none") {
			t8Occupied = currentPlayer;
			drop(ev);
		}
		if(ev.target.id == "t9" && t9Occupied == "none") {
			t9Occupied = currentPlayer;
			drop(ev);
		}
	}
}

//Gives the effects of the game piece being dropped and disables the piece
function drop(ev) {
	var data = ev.dataTransfer.getData("Text");
	
	if(currentChoice == "rabbit1" && rabbit1Draggable == true) {
		ev.target.appendChild(document.getElementById(data));
		ev.target.style.backgroundColor = "#8EC7F5";
		rabbit1Draggable = false;
		turn = "dragon";
		updateStatus();
	} else if(currentChoice == "rabbit2" && rabbit2Draggable == true) {
		ev.target.appendChild(document.getElementById(data));
		ev.target.style.backgroundColor = "#8EC7F5";
		rabbit2Draggable = false;
		turn = "dragon";
		updateStatus();
	} else if(currentChoice == "rabbit3" && rabbit3Draggable == true) {
		ev.target.appendChild(document.getElementById(data));
		ev.target.style.backgroundColor = "#8EC7F5";
		rabbit3Draggable = false;
		turn = "dragon";
		updateStatus();
	} else if(currentChoice == "rabbit4" && rabbit4Draggable == true) {
		ev.target.appendChild(document.getElementById(data));
		ev.target.style.backgroundColor = "#8EC7F5";
		rabbit4Draggable = false;
		turn = "dragon";
		updateStatus();
	} else if(currentChoice == "rabbit5" && rabbit5Draggable == true) {
		ev.target.appendChild(document.getElementById(data));
		ev.target.style.backgroundColor = "#8EC7F5";
		rabbit5Draggable = false;
		turn = "dragon";
		updateStatus();
	} else if(currentChoice == "dragon1" && dragon1Draggable == true) {
		ev.target.appendChild(document.getElementById(data));
		ev.target.style.backgroundColor = "#FCA4B0";
		dragon1Draggable = false;
		turn = "rabbit";
		updateStatus();
	} else if(currentChoice == "dragon2" && dragon2Draggable == true) {
		ev.target.appendChild(document.getElementById(data));
		ev.target.style.backgroundColor = "#FCA4B0";
		dragon2Draggable = false;
		turn = "rabbit";
		updateStatus();
	} else if(currentChoice == "dragon3" && dragon3Draggable == true) {
		ev.target.appendChild(document.getElementById(data));
		ev.target.style.backgroundColor = "#FCA4B0";
		dragon3Draggable = false;
		turn = "rabbit";
		updateStatus();
	} else if(currentChoice == "dragon4" && dragon4Draggable == true) {
		ev.target.appendChild(document.getElementById(data));	
		ev.target.style.backgroundColor = "#FCA4B0";
		dragon4Draggable = false;
		turn = "rabbit";
		updateStatus();
	} else if(currentChoice == "dragon5" && dragon5Draggable == true) {
		ev.target.appendChild(document.getElementById(data));
		ev.target.style.backgroundColor = "#FCA4B0";
		dragon5Draggable = false;
		turn = "rabbit";
		updateStatus();
	}

	ev.preventDefault();
}

//Checks if anyone has won or if there has been a tie
function checkWinner() {
	if(t1Occupied != "none" && t1Occupied == t2Occupied && t2Occupied == t3Occupied) {
		gameWon = true;
		changeColor("t1", "#F5E884");
		changeColor("t2", "#F5E884");
		changeColor("t3", "#F5E884");
		turn = "none";
	} else if(t4Occupied != "none" && t4Occupied == t5Occupied && t5Occupied == t6Occupied) {
		gameWon = true;
		changeColor("t4", "#B4E084");
		changeColor("t5", "#B4E084");
		changeColor("t6", "#B4E084");
		turn = "none";
	} else if(t7Occupied != "none" && t7Occupied == t8Occupied && t8Occupied == t9Occupied) {
		gameWon = true;
		changeColor("t7", "#B4E084");
		changeColor("t8", "#B4E084");
		changeColor("t9", "#B4E084");
		turn = "none";
	} else if(t1Occupied != "none" && t1Occupied == t4Occupied && t4Occupied == t7Occupied) {
		gameWon = true;
		changeColor("t1", "#B4E084");
		changeColor("t4", "#B4E084");
		changeColor("t7", "#B4E084");
		turn = "none";
	} else if(t2Occupied != "none" && t2Occupied == t5Occupied && t5Occupied == t8Occupied) {
		gameWon = true;
		changeColor("t2", "#B4E084");
		changeColor("t5", "#B4E084");
		changeColor("t8", "#B4E084");
		turn = "none";
	} else if(t3Occupied != "none" && t3Occupied == t6Occupied && t6Occupied == t9Occupied) {
		gameWon = true;
		changeColor("t3", "#B4E084");
		changeColor("t6", "#B4E084");
		changeColor("t9", "#B4E084");
		turn = "none";
	} else if(t1Occupied != "none" && t1Occupied == t5Occupied && t5Occupied == t9Occupied) {
		gameWon = true;
		changeColor("t1", "#B4E084");
		changeColor("t5", "#B4E084");
		changeColor("t9", "#B4E084");
		turn = "none";
	} else if(t3Occupied != "none" && t3Occupied == t5Occupied && t5Occupied == t7Occupied) {
		gameWon = true;
		changeColor("t3", "#B4E084");
		changeColor("t5", "#B4E084");
		changeColor("t7", "#B4E084");
		turn = "none";
	} else if(boardFull()) {
		turn = "none";
		changeColor("t1", "#F5E884");
		changeColor("t2", "#F5E884");
		changeColor("t3", "#F5E884");
		changeColor("t4", "#F5E884");
		changeColor("t5", "#F5E884");
		changeColor("t6", "#F5E884");
		changeColor("t7", "#F5E884");
		changeColor("t8", "#F5E884");
		changeColor("t9", "#F5E884");
	}
}

//Updates the status div to display whose turn it is, who won, or if its a tie
function updateStatus() {
	checkWinner();
	if(gameWon == true) {
		document.getElementById("status").innerHTML = "<h2>"+capitaliseFirstLetter(currentPlayer)+" won!</h2>";
	} else if(turn == "rabbit") {
		document.getElementById("status").innerHTML = "<h2>Rabbit's turn</h2>";
	} else if(turn == "dragon") {
		document.getElementById("status").innerHTML = "<h2>Dragon's turn</h2>";
	} else {
		document.getElementById("status").innerHTML = "<h2>Tie game!</h2>";
	}
}

//Capitalizes the first letter in a string
function capitaliseFirstLetter(string) {
    return string.charAt(0).toUpperCase() + string.slice(1);
}

//Changes the color of the id to the color given
function changeColor(id, color) {
	document.getElementById(id).style.backgroundColor = color;
}

//Check if all the tiles of the board are full
function boardFull() {
	var isBoardFull = false;
	
	if (t1Occupied != "none" && t2Occupied != "none" && t3Occupied != "none" && t4Occupied != "none" && t5Occupied != "none" && t6Occupied != "none" && t7Occupied != "none" && t8Occupied != "none" && t9Occupied != "none") {
		isBoardFull = true;
	}
	return isBoardFull;
}