'use strict';
let weapon=require('./weapon.js');

let gun=function(name,height,width,weight,price,bullets){
  weapon.apply(this,arguments);
  this.bullets=bullets;
}
gun.prototype.bonus=function(){
  return 1.5;
}
gun.prototype.damage=function(){
  return (this.weight/(this.height*this.width))+this.bonus();
}
module.exports=gun;
