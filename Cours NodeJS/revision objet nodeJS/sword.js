'use strict';
let weapon=require('./weapon');

let sword=function(name,height,width,weight,price,legandary,year){
  weapon.apply(this,arguments);
  this.leganday=leganday;
  this.year=year;
}
module.exports=sword;
