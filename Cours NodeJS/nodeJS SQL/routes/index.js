'use strict';
module.exports=function(app){
  let computerRouter = require('./computer');
  app.use('/computer',computerRouter);
}
