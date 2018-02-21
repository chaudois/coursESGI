'use strict';

const express=require('express');
const models = require('../models');
const Project = models.Project;
const router = express.Router();

router.post('/',function(req,res,next){
  let l = req.body.title;
  let d=req.body.description;
  let rd = req.body.release_date;

  Project.create({
    title:l,
    description:d,
    release_date:rd
  }).then(function(pro){
    res.json(pro);
  }).catch(function(err){
    res.json({
      result:-1//sequelize error
    })
  })
});
module.exports=router;
