const ESGI = require("scanf");
console.log("veuillez entrer un chiffre pour calculer la somme de tout les chiffre le précédant : ")
chiffre=ESGI('%s');
total=0;
for(i=0;i<=chiffre;i++){

  console.log(total+"+"+i+" = "+(total+i));
  total+=i;
}
console.log("total : "+total);
