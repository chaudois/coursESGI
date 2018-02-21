'use strict';

let express=require('express');
let bodyParser=require('body-parser');
let cookieParser=require('cookie-parser');
let app=express();
let port=5000;
app.use(cookieParser());
app.use(bodyParser());
require('./routes')(app);
app.listen(port,function(){
  console.log('démarage du serveur sur le port '+port);
})
