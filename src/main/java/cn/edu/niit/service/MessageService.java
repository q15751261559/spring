package cn.edu.niit.service;

import cn.edu.niit.dao.MessageDao;
import cn.edu.niit.javabean.Book;
import cn.edu.niit.javabean.Message;

import java.util.List;

public class MessageService {
    private MessageDao messageDao=new MessageDao();

    public List<Message> searchAllMessage(int pageNum, int pageSize)
    {
        List<Message> messages = messageDao.selectAll(pageNum, pageSize);
        return messages;
    }

    public int countNum() {
        return messageDao.count();
    }
}
