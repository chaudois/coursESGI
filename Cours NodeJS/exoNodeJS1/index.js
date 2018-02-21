'use strict';

let Hippopotamus=require('./Hippopotamus');

let grosTas=new Hippopotamus('grosTas',550,20);
let tresMoche=new Hippopotamus('tresMoche',600,25);


let live=function(Hippopotamus){
  for(let i=0;i<15;i++){
    Hippopotamus.eat();
    Hippopotamus.eat();
    Hippopotamus.swim();
    Hippopotamus.swim();
    Hippopotamus.swim();
  }
}
for(let i=0;i<21;i++){
  live(grosTas);
  live(tresMoche);

  console.log(grosTas);
  console.log(tresMoche+"\n");
}
// if(grosTas.fight(tresMoche)==1){
//   console.log("grosTas a battu tresMoche");
// }else{
//   console.log("grosTas a perdu face à tresMoche ");
// }
