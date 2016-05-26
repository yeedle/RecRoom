/**
 * Created by Yeedle on 5/24/2016.
 */
var canvas = document.getElementById("canvas");
var c = canvas.getContext("2d");
var connection;
var username = document.getElementById("username");

var connect = function () {
    try {
        connection = new WebSocket("ws://"+window.location.host+"/recroom/bubble?username="+username);
    } catch (error)
    {
        console.log(error)
    }
};

$("button").on("click",connect() );


var container;
connection.onopen = function (event) {
    
}
var circles;
connection.onmessage = function (event) {
    var message = JSON.parse(event.data);
    switch (message.type){
        case "GAME_STARTED":
            circles = message.newBubbles;
            break;
        case "JOINED_GAME":
            circles = message.newBubbles;
        default:
            break; 
    }
       
}
//create te container that will hold the boincing balls.
container = {
    x: 0,
    y: 0,
    width: 640,
    height: 640
};
//create the array of circles that will be animated


function animate() {
    //draw the container
    c.fillStyle = "#000000";
    c.fillRect(container.x, container.y, container.width, container.height);

    //loop throughj the circles array
    for (var i = 0; i < circles.length; i++) {
        //draw the circles
        c.fillStyle = 'hsl(' + circles[i].color++ + ', 100%, 50%)';
        c.beginPath();
        c.arc(circles[i].relativeXPosition, circles[i].relativeYPosition, circles[i].relativeRadius, 0, Math.PI * 2, true);
        c.fill()

        //time to animate our circles ladies and gentlemen.
        if (circles[i].relativeXPosition - circles[i].relativeRadius + circles[i].vx < container.x || circles[i].relativeXPosition + circles[i].relativeRadius + circles[i].vx > container.x + container.width) {
            circles[i].deltaX = -circles[i].deltaX;
        }

        if (circles[i].relativeYPosition + circles[i].relativeRadius + circles[i].vy > container.y + container.height || circles[i].relativeYPosition - circles[i].relativeRadius + circles[i].vy < container.y) {
            circles[i].deltaY = -circles[i].deltaY;
        }

        circles[i].relativeXPosition += circles[i].deltaX
        circles[i].relativeYPosition += circles[i].deltaY
    }

    requestAnimationFrame(animate);
}
requestAnimationFrame(animate);
