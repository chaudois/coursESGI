'use strict';
let car=require('./classcar');
let fs=require('fs');
let cars=[];
let i=0;

module.exports=function(app){
  app.use(function(req,res,next){
    fs.readFile('log.txt',function(err,data){
      if(err){
        throw err;
      }else if (req.url!='/favicon.ico'){
        let content=data+'\naccès à la page ';
        content+=req.url;
        content+=' methode : '+req.method;
        content+=''
        fs.writeFile('log.txt',content,function(err){
          if(err){
            throw err;
          }
          console.log(req.url);
          next();
        })
      }
    });
  })
  app.get('/home',function(req,res){
    res.type('html');
    let html='vous etes sur la page d\'accueil<br>';
    html+='ajouter un vehicule au garage <br>';
    html+='<form type="GET" action="/add">'+
    '<input type="text" placeholder="marque" name=marque><br>'+
    '<input type="text" placeholder="model" name=model><br>'+
    '<input type="number" placeholder="année" name=annee><br>'+
    '<input type="submit" value="ok"><br>';
    html+='<a href=\'/display\'> consulter la liste des vehicules du garage </a>\n';

    res.send(html);
  })

  app.get('/add',function(req,res){
    if(req.query.marque==0 || req.query.model==0 || req.query.annee==0){
      res.redirect('/home');
    }else{
      let cartemp=new car (req.query.marque,
                          req.query.model,
                          req.query.annee);
      cars.push(cartemp);
      res.redirect('/home');
    }
  });
  app.get('/display',function(req,res){
    res.type('html');
    let html='<html><body>voiture en stock : \n<ul>';
    for(let c of cars){
      html+='\n<li>'+c.model +' '+c.marque+' '+ c.annee+' </li>';
    }
    html+='</ul><br><a href=\'/home\'> retour à l\'accueil </a>\n</body></html>';
    console.log(cars);
    res.send(html);
  });
  app.get('*',function(req,res){
    res.redirect('/home');
  });
}
