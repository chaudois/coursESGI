'use strict';

const car = require('./classCar');
const superCar=require('./SuperCar');
let c = new car('micra','nissan',1999);

console.log(c);
c.gasPedal();
c.gasPedal();
c.gasPedal();
c.gasPedal();

console.log(c);

String.prototype.randomChar = function(){//on peut rajouter des fonction auclass nativ
  let index=Math.random()*this.length;
  return this.charAt(index);
}
let oldCharAt=String.prototype.charAt;//on peut aussi les copier

//console.log(c.brand.randomChar());

let voiture=new superCar('C2','citroen');//une voiture qui a le meme comportement que car
voiture.gasPedal();
voiture.gasPedal();
voiture.gasPedal();
voiture.gasPedal();
console.log(voiture);
voiture.gasPedal=function(){//redefinition de la fonction gasPedal
  this.speed+=40;
}

voiture.gasPedal();
console.log(voiture);
console.log(voiture.toString());
