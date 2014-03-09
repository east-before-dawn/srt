sleep = {};

$(document).ready(function() {
                  sleep.board = $('#board');
                  sleep.data = data;
                  $('#start').click(function() {
                                    $('.masthead').fadeOut();
                                    $('.mastfoot').fadeOut();
                                    sleep.next_question = 0;
                                    sleep.suggestions = [];
                                    loadNextQuestion();
                                    });
                  });

function loadNextQuestion() {
    clearBoard(function() {
               if (sleep.next_question < sleep.data.length) {
               loadQuestion(sleep.next_question);
               } else {
               loadReport();
               }
               });
}

function loadReport() {
    var dl = $("<dl/>").addClass("dl-horizontal");
    for (var i = 0; i < sleep.suggestions.length; i++) {
        var item = sleep.suggestions[i];
        dl.append($("<dt/>").text(item.title),
                  $("<dd/>").text(item.suggestion));
    }
    sleep.board.append(
                       $("<h1/>").addClass("cover-heading").text("Results and Suggestions"),
                       $("<p/>").addClass("left text-muted").text("Thank you for taking the survey! " +
                                                                  "I have compiled a list of suggestions tailored to your current sleeping situations:"),
                       dl,
                       $("<p/>").addClass("left").text("Also, if you are struggling to fall asleep for " +
                                                       "more than 10 - 15 minutes, get out of the bed, leave your sleeping area, and do something " +
                                                       "monotonous. When you feel drowsy, return to your bed. If you continue to feel wide-awake, try to " +
                                                       "relax and go back to bed in 20 - 30 minutes. Finally, don't get upset if you have trouble sleeping " +
                                                       "- we've all been there!")
                       );
    sleep.board.fadeIn(800);
}

function loadQuestion(index) {
    sleep.question_data = sleep.data[index];
    sleep.board.append("<div id='count'></div><div id='question'></div><div id='result'></div>");
    $('#count').append(
                       $("<p/>").addClass("text-muted")
                       .text("Question " + (index + 1) + " of " + sleep.data.length),
                       $("<br/>"));
    
    sleep.question = $("#question").append(
                                           $("<p/>").addClass("description").text(sleep.question_data.description));
    sleep.result = $("#result");
    for (var i = 0; i < sleep.question_data.options.length; i++) {
        var option_data = sleep.question_data.options[i];
        $('<div/>').addClass("btn-group option")
        .append($('<button/>')
                .attr('type', 'button')
                .addClass("btn btn-default")
                .text(option_data)
                .attr('id', i)
                .click(function() {
                       selectAnswer($(this).attr('id'));
                       }))
        .appendTo(sleep.question);
        if (sleep.question_data.long) {
            $("<br/>").appendTo(sleep.question);
        }
    }
    sleep.board.fadeIn();
}

function selectAnswer(index) {
    index = parseInt(index, 10);
    var correct = (sleep.question_data.sol.indexOf(index) != -1);
    
    sleep.result.empty();
    sleep.result.hide();
    
    var explanation = "";
    var suggestion = sleep.question_data.suggestion;
    var title = sleep.question_data.title;
    
    
    if (!correct) {
        explanation = sleep.question_data.bad;
    } else {
        explanation = sleep.question_data.good;
    }
    sleep.result.append($("<p/>")
                        .addClass("left")
                        .text(explanation));
    
    if (!correct) {
        sleep.result.append(
                            $("<div/>").addClass("bs-callout")
                            .append("<h4>Suggestion</h4>")
                            .append($("<p/>").text(suggestion)));
    }
    
    var next_text = "Next";
    if (sleep.next_question + 1 === sleep.data.length) {
        next_text = "See results";
    }
    
    var button = $("<button/>")
    .attr("type", "button")
    .addClass("btn btn-default")
    .text(next_text)
    .click(function() {
           if (!correct) {
           sleep.suggestions.push({
                                  "suggestion": suggestion,
                                  "title": title
                                  });
           }
           sleep.next_question++;
           loadNextQuestion();
           });
    
    sleep.result.append(
                        $("<div/>").addClass("btn-group pull-right")
                        .append(button));
    sleep.result.slideDown();
}

function clearBoard(func) {
    sleep.board.fadeOut(400, function() {
                        sleep.board.empty();
                        func();
                        });
}
