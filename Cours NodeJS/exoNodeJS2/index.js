'use strict';

let point = require ("./point");
let circle = require ("./circle");
let monPoint=new point(5,6);
let monPoint2=new point(5,6);

let monCircle=new circle(monPoint,4);

if(monCircle.containsPoint(monPoint2)){
  console.log("le point 2 est contenu dans le cercle");
}else{
  console.log("le point 2 n'est pas contenu dans le cercle");
}
