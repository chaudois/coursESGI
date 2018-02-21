'use strict';

let weapon=require('./weapon');
let gun=require('./gun');
let arme=new gun('osef',0.3,0.2,1300,5500,50);

console.log(arme);
console.log('bonus de l\'arme = '+arme.bonus());
console.log('dégats de l\'arme = '+arme.damage());
