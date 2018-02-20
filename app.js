var _ = require('underscore');
var ApiBuilder = require('claudia-api-builder'),
  api = new ApiBuilder();

module.exports = api;

var db = require('./db.json')

api.get('/db', function () {
  return db;
});

const uuidv1 = require('uuid/v1');
const AWS = require('aws-sdk');
var dynamoDb = new AWS.DynamoDB.DocumentClient();

api.post('/submit', function (request) {

  var correctAnswerCount = 0;

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
