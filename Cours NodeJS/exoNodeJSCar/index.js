'use strict';

let bodyParser=require('body-parser');
let cookieParser=require('cookie-parser');
let express = require('express');
let app=express();

app.use(cookieParser());
app.use(bodyParser());

require('./routes')(app);

app.listen(5000,function(){
  console.log('demarage du seveur sur le port 5000');
});
