package dev.sachith;

import java.util.List;

/**
 * @author sachith
 */
public class SessionService {
    private final SessionDAO sessionDAO;

    public SessionService(SessionDAO sessionDAO) {
        this.sessionDAO = sessionDAO;
    }

    public void createSession(Session session) {
        sessionDAO.insertSession(session.getTitle(), session.getDescription(), session.getSpeakerName(),
                session.getFileUploadUrl());
    }

    public List<Session> getAllSessions() {
        return sessionDAO.getAllSessions();
    }

    public Session getSession(int id) {
        return sessionDAO.getSessionById(id);
    }

    public void updateSession(int id, Session session) {
        sessionDAO.updateSession(session.getTitle(), session.getDescription(), session.getSpeakerName(),
                session.getFileUploadUrl(), id);
    }

    public void deleteSession(int id) {
        sessionDAO.deleteSession(id);
    }
}
