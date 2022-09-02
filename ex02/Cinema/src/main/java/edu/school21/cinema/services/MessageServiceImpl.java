package edu.school21.cinema.services;

import edu.school21.cinema.models.Message;
import edu.school21.cinema.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    private MessageRepository messageRepository;

    @Autowired
    public void setMessageRepository(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public List<Message> findAll() {
        return messageRepository.findAll();
    }

    public boolean add(Message entity) {
        messageRepository.save(entity);
        return true;
    }

    public void delete(Message entity) {
        messageRepository.delete(entity);
    }

    public void update(Message entity) {
        messageRepository.update(entity);
    }

    public List<Message> findLast20ByFilmId(Long filmId) {
        return messageRepository.findLast20ByFilmId(filmId);
    }
}
