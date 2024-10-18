package pe.edu.upeu.sysalmacenfx.dto;

import lombok.Data;

@Data
public class SessionManager {
    static SessionManager instance;
    Long userId;
    String userName;
    public static synchronized SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }
}
