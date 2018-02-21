'use strict';

const express = require('express');
const router = express.Router();
let bodyParser=require('body-parser');
router.use(bodyParser.json());
const models=require('../models');
const connexion=models.database;

router.get('/',function(req,res,next){
  console.log('reception d\'une requete GET ')

  let brand=req.query.brand;
  let cpu=req.query.cpu;
  let price = req.query.price;
  let sql="INSERT INTO Computer (brand,cpu,price) VALUES ('"+brand+"',"+cpu+","+price+")";
  connexion.query(sql,function(err){
    if(err){
      res.json({
        result:'erreur : '+JSON.stringify(err)
      })
    }else{
      res.json({
      result:'requete injecté'
    })

    }
  });

})
router.get('*',function(req,res,next){
  res.type('html');
  res.status(404);
  res.send('<html>404</html>');
})
module.exports = router;
