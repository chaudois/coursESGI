'use strict';
let point=require('./point');

let circle=function(point,radius){
  this.point=point;
  this.radus=radius;

}
circle.prototype.area=function(){
  return 3.141529*(this.radius*this.radius);
}
circle.prototype.containsPoint=function(point){
  if(point.distanceToPoint(this.point)<this.radius){
    return true;
  }else{
    return false;
  }
}

module.exports=circle;
