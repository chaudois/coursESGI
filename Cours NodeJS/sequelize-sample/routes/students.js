'use strict';

const express=require('express');
const models = require('../models');
const Student = models.Student;
const router = express.Router();

router.get('/',function(req,res,next){
  let l = parseInt(req.query.limit) || 20;
  let o = parseInt(req.query.offset) ||0
  let options = {
    limit:l,
    offset:o
  }
  Student.findAll(options).then(function(students){
    let result=[];
    for(let stud of students){
      result.push(stud.responsify());
    }
    res.json(result);
  }).catch(function(err){
    res.json({
      result: -1
    })
  })
})
router.get('/:stud_id',function(req,res,next){
  Student.find({
    where:{
      id:req.params.stud_id
    },
    include:[
      models.School,
      models.Project
    ]
  }).then(function(stud){
    if(stud){
      return res.json(stud.responsify());
    }
    res.json({
      result:404
    })
  })
})
router.post('/',function(req,res,next){
  let l = req.body.lastname;
  let f=req.body.firstname;
  let bd=req.body.birthdate;
  let s_id=req.body.school_id;
  Student.create({
    lastname:l,
    firstname:f,
    birthdate:bd,
    school_id:s_id
  }).then(function(stud){
    res.json(stud);
  }).catch(function(err){
    res.json({
      result:-1//sequelize error
    })
  })
});
router.delete('/:stud_id',function (req,res,next){
  Student.find({
    where:{
      id:req.params.stud_id
    }
  }).then(function(stud){
    if(stud){
      return stud.destroy().then(function(){
        res.json({
          result:1
        });
      });
    }
    res.json({
      result:404
    })
  })
})
router.post('/:stud_id/project/:proj_id',function(req,res,next){
  Student.find({
    where : {
      id:req.params.stud_id
    }
  }).then(function(stud){
    if(stud){
      return models.Project.find({
        where:{
          id:req.params.proj_id
        }
      }).then(function(proj){
        if(proj){
          return stud.addProject(proj).then(function(){
            res.json({
              result:1
            });
          })
        }
        res.json({
          result:405
        })
      })
    }
    res.json({
      result : 404
    })
  })
})
module.exports=router;
