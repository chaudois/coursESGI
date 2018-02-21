const ESGI = require("scanf");
console.log("veuillez entrer une prmière ohrase ou mot : ")
messageUser1=ESGI('%s');
console.log("veuillez entrer une seconde phrase ou mot d'une taille deux fois supérieur à la précédante : ");
messageUser2=ESGI('%s');
if(messageUser1.length*2==messageUser2.length){
  console.log("correct");
}else{
  console.log("incorrect, taille du premier message : "+ messageUser1.length+", taille du second : "+messageUser2.length);
}
