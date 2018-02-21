'use strict';
let car=require('./car');
let fs=require('fs');
let cars=[];
module.exports=function(app){
  app.use(function(req,res,next){
    console.log('connexion à la page '+req.url);
    next();
  })
  app.get('/home',function(req,res){
    res.type('html');
    let html='<html><body>';
    html+='<form type="GET" action="/add">';
    html+='<input type="texte" placeholder="marque" name="marque"><br>';
    html+='<input type="texte" placeholder="model" name="model"><br>';
    html+='<input type="number" placeholder="annee" name="annee"><br>';
    html+='<input type="submit" value="ajouter"><br>';
    html+='<a href="/display"> afficher le garage</a><br>';
    html+='</body></html>';
    res.send(html);
  })
  app.get('/add',function(req,res){
    let c=new car(req.query.marque,req.query.model,req.query.annee);
    fs.readFile('garage.txt',function(err,data){
      if(err){
        res.redirect('/home');
      }
      fs.writeFile('  garage.txt',data+'\n'+c, function(err){
        
      });
    })
    cars.push(c);
    res.redirect('/home');
  })
  app.get('/display',function(req,res){
    let html='<html><body>liste des voitures enregistrées : <br><ul>';
    for(let ca of cars){
      html+='<li>'+ca.marque+' '+ca.model+' '+ca.annee+'</li><br>';
    }
    html+='</ul><br>';
    html+='<a href="/home" retour à home</a><br>';
    res.send(html);
  })
  app.get('*',function(req,res){
    res.send("404");
  })
}
