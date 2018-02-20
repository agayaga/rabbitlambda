var ApiBuilder = require('claudia-api-builder'),
  api = new ApiBuilder();

module.exports = api;

var db = require('./db.json')

api.get('/db', function () {
  return db;
});


api.post('/submit', function (request) {
  return request.body;
});
