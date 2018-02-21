'use strict';

let Car = function(model, brand, year){//creer la class
  this.model=model;//creer la variable de la class et l'atribut.
  this.brand=brand;
  this.year=year || 2017;
  this.speed=0;
}
Car.prototype.gasPedal=function(){//.prototype accède au methode de la classe
  if(this.speed === undefined){/// === vérifie l'égalité de valeur et de type
    this.speed=0;
  }else{
    this.speed+=10;
  }
}
module.exports = Car;
