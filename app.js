var _ = require('underscore');
var ApiBuilder = require('claudia-api-builder'),
  api = new ApiBuilder();

module.exports = api;

var db = require('./db.json')

api.get('/db', function () {
  return db;
});


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

  return {
    total_questions: db.length,
    correct_answers: correctAnswerCount
  };
  
});
