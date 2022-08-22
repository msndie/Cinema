package edu.school21.cinema.services;

import edu.school21.cinema.models.Message;
import edu.school21.cinema.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    private MessageRepository messageRepository;

    @Autowired
    public void setMessageRepository(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public List<Message> findAll() {
        List<Message> list = new LinkedList<>();
        messageRepository.findAll().forEach(list::add);
        return list;
    }

    public boolean add(Message entity) {
        messageRepository.save(entity);
        return true;
    }

    public void delete(Message entity) {
        messageRepository.delete(entity);
    }

    public void update(Message entity) {
        messageRepository.save(entity);
    }

    public List<Message> findLast20ByFilmId(Long filmId) {
        List<Message> messageList = messageRepository.findAllByFilmId(filmId);
        int size = messageList.size();
        System.out.println(size);
        return size > 20 ? messageList.subList(size - 20, size) : messageList;
    }
}
