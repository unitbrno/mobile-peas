package skodavox.peas.unitbrno.cz.skodavoxapp.model;

public class Attender {

    private User user;

    private AttenderStatus attenderStatus;

    public Attender(User user, AttenderStatus attenderStatus) {
        this.user = user;
        this.attenderStatus = attenderStatus;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public AttenderStatus getAttenderStatus() {
        return attenderStatus;
    }

    public void setAttenderStatus(AttenderStatus attenderStatus) {
        this.attenderStatus = attenderStatus;
    }

    public enum AttenderStatus
    {
        SPEAKING("speaking"), ACTIVE("active"), INVITED("invited");

        private String status;

        AttenderStatus(String status) {
            this.status = status;
        }
    }
}
