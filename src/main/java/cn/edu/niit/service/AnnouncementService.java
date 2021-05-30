package cn.edu.niit.service;

import cn.edu.niit.dao.AnnouncementDao;
import cn.edu.niit.javabean.Announcement;

import java.util.List;

public class AnnouncementService {
    AnnouncementDao announcementDao=new AnnouncementDao();
    public List<Announcement> selectAnnouncement(int pageNum, int pageSize) {
        return announcementDao.selectAnnouncement(pageNum,pageSize);
    }
    public int countNum() {
        return announcementDao.count();
    }
}
