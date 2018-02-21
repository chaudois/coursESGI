module.exports=function(chiffre){
  total=0;

  for(i=0;i<=chiffre;i++){

    console.log(total+"+"+i+" = "+(total+i));
    total+=i;
  }
  console.log("total : "+total);
}
