'use strict';

const express=require('express');
const models = require('../models');
const School = models.School;
const router = express.Router();

router.get('/',function(req,res,next){
  let l = parseInt(req.query.limit) || 20;
  let o = parseInt(req.query.offset) ||0
  let options = {
    limit:l,
    offset:o
  }
  School.findAll(options).then(function(school){
    let result=[];
    for(let stud of school){
      result.push(stud.responsify());
    }
    res.json(result);
  }).catch(function(err){
    res.json({
      result: -1
    })
  })
})
router.get('/:school_id',function(req,res,next){
  School.find({
    where:{
      id:req.params.school_id
    }
  }).then(function(sch){
    if(sch){
      return res.json(sch.responsify());
    }
    res.json({
      result:404
    })
  })
})
router.post('/',function(req,res,next){
  let l = req.body.name;

  School.create({
    name:l
  }).then(function(sch){
    res.json(sch);
  }).catch(function(err){
    res.json({
      result:-1//sequelize error
    })
  })
});
module.exports=router;
