'use strict';

let weapon=function(name,height,width,weight,price){
  this.name=name;
  this.height=height;
  this.width=width;
  this.weight=weight;
  this.price=price;
}
weapon.prototype.duration=function(){
  return (Math.round(Math.random()*10));
}
weapon.prototype.damage=function(){
   return (Math.round(Math.random()*1000));
}
weapon.prototype.bonus=function(){
  return (Math.round(Math.random()*2,1));
}

module.exports=weapon;
