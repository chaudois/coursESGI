const scanf = require("scanf");
const calc=require("./calc");
console.log("veuillez entrer 5 chiffre pour calculer le plus grand et leur moyenne : ")
arr=new Array();
for(i=0;i<5;i++){

  arr.push(scanf('%d'));
}
console.log(arr);
max=0;
maxIndex=0;
for(i=0;i<arr.length;i++){
  if(arr[i]>max){
    max=arr[i];
    maxIndex=i;
  }
}
function moyenne(arr){
  total=0;
  for(chiffre of arr ){
    total+=chiffre;
  }
  return total/=arr.length;
}
console.log("la plus grosse valeur se trouve à l'index "+maxIndex);
console.log("la moyenne est de "+moyenne(arr));
