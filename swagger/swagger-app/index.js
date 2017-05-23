var express = require('express');  
var app = express();  
app.use('/root', express.static('dist'));  
app.get('/', function (req, res) {  
  res.send('Hello World!');  
});  
  
app.listen(8082, function () {  
  console.log('Example app listening on port 8082!');  
   console.log('http://10.120.1.135:8082/root/index.html');  
});  