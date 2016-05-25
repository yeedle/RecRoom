/**
 * Created by Yeedle on 5/24/2016.
 */
var canvas = document.getElementById("canvas");
var c = canvas.getContext("2d");

//create te container that will hold the boincing balls.
var container = {
    x: 0,
    y: 0,
    width: 640,
    height: 640
};
//create the array of circles that will be animated
var circles = [{
    x: 50,
    y: 100,
    r: 10,
    vx: 10,
    vy: 9,
    color: 125
}, {
    x: 150,
    y: 80,
    r: 20,
    vx: 15,
    vy: 8,
    color: 205
}, {
    x: 90,
    y: 150,
    r: 5,
    vx: 5,
    vy: 15,
    color: 25
}, {
    x: 100,
    y: 50,
    r: 15,
    vx: 8,
    vy: 10,
    color: 100
}];

function animate() {
    //draw the container
    c.fillStyle = "#000000";
    c.fillRect(container.x, container.y, container.width, container.height);

    //loop throughj the circles array
    for (var i = 0; i < circles.length; i++) {
        //draw the circles
        c.fillStyle = 'hsl(' + circles[i].color++ + ', 100%, 50%)';
        c.beginPath();
        c.arc(circles[i].x, circles[i].y, circles[i].r, 0, Math.PI * 2, true);
        c.fill()

        //time to animate our circles ladies and gentlemen.
        if (circles[i].x - circles[i].r + circles[i].vx < container.x || circles[i].x + circles[i].r + circles[i].vx > container.x + container.width) {
            circles[i].vx = -circles[i].vx;
        }

        if (circles[i].y + circles[i].r + circles[i].vy > container.y + container.height || circles[i].y - circles[i].r + circles[i].vy < container.y) {
            circles[i].vy = -circles[i].vy;
        }

        circles[i].x += circles[i].vx
        circles[i].y += circles[i].vy
    }

    requestAnimationFrame(animate);
}
requestAnimationFrame(animate);