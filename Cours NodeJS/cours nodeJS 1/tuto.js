function say(val,fonction){// déclare une fonction. deux parametre : une variable de type string, et une variable de type fonction
  var a = fonction || console.log// la variable a contient la fonction 'fonction' sauf si elle contient NULL, dans ce cas la
                                // variable a vaudra 'console.log'
  a(val);// on execute la fonction contenue dans 'a' avec la variable val passé en parametre
}
say("Hello",function(value){console.log("****"+value+"****")});//appel la fonction say, avec en parametre le string "Hello" et
                                                              // la fonction que l'ont déclare ici meme et qui apelle console.log
                                                              //avec des "*" en plus.
"use strict";// ajoute des mot clé au langage, dont 'let' qui force les variables
              //déclaré apres ce mot cle à ne pas etre remonté en haut de leur porté.
var s="azertyuiop";
say(s);
console.log("length : " + s.length);
console.log("bracket[2] : " + s[2]);
console.log("charAt(5) : "+s.charAt(5));

var arr = new Array();
arr2=new Array();
var arr3=["al1",'al2'];
console.log("display arr3 : " + arr3);
console.log("length arr3 : "+arr3.length);

console.log("boucle for : ");

for(var i =0;i<arr3.length;i++){
  console.log(arr3[i]);
}

console.log("foreach : ");

for(var s of arr3){
  console.log(s);
}


function leaveIt(){

  var w=77;//fonctionne
  console.log(w);
}
leaveIt();
