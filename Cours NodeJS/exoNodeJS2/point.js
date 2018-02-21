'use strict';

let point=function(x,y){
  this.x=x;
  this.y=y;
}
point.prototype.getX=function(){
  return this.x;
}
point.prototype.getY=function(){
  return this.y;
}
point.prototype.setX=function(x){
  this.x=x;
}
point.prototype.setX=function(y){
  this.y=y;
}
point.prototype.distanceToPoint=function(point){
  let A=this.x-point.x;
  let B=this.y-point.y;
  return Math.sqrt((A*A)+(B*B));
}

point.prototype.toString=function(){
  return "x : "+x+", y : "+y;
}

module.exports =point;
