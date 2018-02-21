'use strict';

let express=require('express');
let bodyParser=require('body-parser');
const models=require('./models');

let app=express();
let port=5000;

let routes=require('./routes');
routes(app);
app.listen(port,function(){
  console.log('serveur démaré sur le port '+port);


})
