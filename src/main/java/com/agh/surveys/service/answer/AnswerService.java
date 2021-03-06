package com.agh.surveys.service.answer;

import com.agh.surveys.exception.answer.AnswerNotFoundException;
import com.agh.surveys.model.answer.Answer;
import com.agh.surveys.model.answer.type.AnswerDetails;
import com.agh.surveys.model.question.Question;
import com.agh.surveys.model.user.User;
import com.agh.surveys.repository.AnswerRepository;
import com.agh.surveys.repository.QuestionRepository;
import com.agh.surveys.service.user.UserService;
import com.agh.surveys.service.question.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnswerService implements IAnswerService {

    @Autowired
    AnswerRepository answerRepository;

    @Autowired
    UserService userService;

    @Autowired
    QuestionService questionService;

    @Autowired
    QuestionRepository questionRepository;

    @Override
    public List<Answer> findAll(Integer questionId) {
        return questionService.getQuestion(questionId).getAnswers();
    }

    @Override
    public Answer addAnswer(Integer questionID, String userID, AnswerDetails answerDetails) {
        User user = userService.getUserByNick(userID);
        Question question = questionService.getQuestion(questionID);
        Answer answer = new Answer(question, user, LocalDateTime.now(), answerDetails);
        answerRepository.save(answer);
        question.getAnswers().add(answer);
        questionRepository.save(question);
        return answer;
    }

    @Override
    public Answer getAnswer(Integer AnswerId) {
        return answerRepository.findById(AnswerId).orElseThrow(AnswerNotFoundException::new);
    }

    @Override
    public void deleteAnswer(Integer answerId){ answerRepository.deleteById(answerId); }

}
