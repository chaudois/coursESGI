'use strict';

const fs=require('fs');

fs.readFile('bonjour.txt',function(err,data){
  if(err){
    throw err;
  }else{
    fs.writeFile('bonjour.txt',data+'\najout de donné',function(err){
      if(err){
        throw err;
      }
      console.log('écriture OK');
    })
  }
});
