package dev.sachith;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

/**
 * @author sachith
 */
@RegisterBeanMapper(Session.class)
interface SessionDAO {

    @SqlUpdate("CREATE TABLE IF NOT EXISTS sessions (id INT AUTO_INCREMENT PRIMARY KEY, title VARCHAR(255), description TEXT, speaker_name VARCHAR(255), file_upload_url VARCHAR(500))")
    void createTable();

    @SqlUpdate("INSERT INTO sessions (title, description, speaker_name, file_upload_url) VALUES (?, ?, ?, ?)")
    void insertSession(String title, String description, String speakerName, String fileUploadUrl);

    @SqlQuery("SELECT * FROM sessions WHERE id = ?")
    Session getSessionById(int id);

    @SqlQuery("SELECT * FROM sessions")
    List<Session> getAllSessions();

    @SqlUpdate("UPDATE sessions SET title = ?, description = ?, speaker_name = ?, file_upload_url = ? WHERE id = ?")
    void updateSession(String title, String description, String speakerName, String fileUploadUrl, int id);

    @SqlUpdate("DELETE FROM sessions WHERE id = ?")
    void deleteSession(int id);
}
