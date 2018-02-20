var _ = require('underscore');
var ApiBuilder = require('claudia-api-builder'),
  api = new ApiBuilder();

module.exports = api;

// var db = require('./db.json')

api.get('/db', function () {
  // return db;
  return getDB();
});

api.get('/db-android', function () {
  // return db;
  return {data: getDB()};
});

var request = require('sync-request');
function getDB(callback){
  var db = request('GET','https://s3.us-east-2.amazonaws.com/rabbitlambdapuri/db.json').getBody('utf8');
  return JSON.parse(db);
}

const uuidv1 = require('uuid/v1');
const AWS = require('aws-sdk');
var dynamoDb = new AWS.DynamoDB.DocumentClient();

api.post('/signin', function (request) {
  return {
    user_id: uuidv1()
  }
});

api.post('/submit', function (request) {

  var correctAnswerCount = 0;

  var db = getDB();

  _.each(request.body,function(answer){
    var question = _.find(db,function(item){
      return item.id == answer.id;
    });
    if(question){
      if(question.answers[question.correct_answer] == answer.answer){
        correctAnswerCount ++;
      }
    }
  })

  var params = {
    TableName: 'rabbit',
    Item: {
        id: uuidv1(),
        data: request.body
    }
  }
  dynamoDb.put(params).promise();

  return {
    total_questions: db.length,
    correct_answers: correctAnswerCount
  };

});

api.get('/stats', function () {
  return dynamoDb.scan({ TableName: 'rabbit' }).promise()
      .then(response => response.Items.length)
});
