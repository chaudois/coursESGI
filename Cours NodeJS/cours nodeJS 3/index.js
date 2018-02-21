'use strict';

const express=require('express');

// express.get("/",function(req,res)){
//   res.type("html");
//   res.send("<html><body>RACINE</body></html>");
//
// }
// express.get("/hello/:name",function(req,res){
//   res.json({
//     "mesage":"hello"+req.params.name+"!"
//   });
// });
let app=express();//objet qui permet d'écouter
app.get('/bonjour',function(req,res){//.get = fonction qui envoie les donné ( GET ou POST) '/bonjour'=url ou l'utilisateur tape,
                                    //function va décrire le comportement de la page accédé
  res.type('html');//le retour est de type html
  res.send('<html><boddy>Bonjour</body></html>');//le corps du retour est le suivant
});
app.all('*',function(req,res){// .all = quelque soit la methode d'arrivé ( seulement si aucune autre fonction n'as été déclanché avant )
                              //'*' = quelque soit l'url accédé
  res.status(404);//status http retourné
  res.type('text');//type de retour
  res.send('NO WAY');//contenu du retour
});
app.listen(8888,function(){//lancement du serveur
  console.log("démarage du service sur le port 8888\n");
})
