"use strict";

let Hippopotamus=function(name,weight,tusksSize){
  this.name=name;
  this.weight=weight;
  this.tusksSize=tusksSize;
}
Hippopotamus.prototype.swim=function(){
  if(this.weight>0.003){
    this.weight-=0.003;
  }else{
    this.weight=0;
  }
}
Hippopotamus.prototype.eat=function(){
  this.weight++;
}
Hippopotamus.prototype.fight=function(Hippopotamus){
  if(Hippopotamus.weight>this.weight*1.2 ){
    return 0;
  }else if (Hippopotamus.weight*1.2<this.weight){
    return 1;
  }else if(Hippopotamus.tusksSize>this.tusksSize){
    return 0;
  }else if (Hippopotamus.tusksSize<=this.tusksSize) {
    return 1;
  }
}

Hippopotamus.prototype.toString=function(){
  return "name : "+this.name+", weight : "+this.weight+", tuskSize : "+this.tusksSize;
}

module.exports=Hippopotamus;
