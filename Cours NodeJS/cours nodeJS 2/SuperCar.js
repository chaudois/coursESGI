"use strict";
let car=require("./classCar");

let superCar=function(){
  car.apply(this, arguments);//applique le constructeur de car à this (superCar)

}

superCar.prototype=new car();//on met en place l'héritage, en remplacant toutes les methodes et variable de superCar par celles de car
superCar.prototype.constructor=superCar;//on précise que le constructeur de superCar vaut toujours le constructeur de superCar et nom de car

superCar.prototype.toString=function(){//on redéfinie le comportement de la fonction toString()
  return 'hello :)';
}

module.exports=superCar;
