package timetable.models;

public class LoginConstructor {
        private  String username;
        private  String password;

        public LoginConstructor(String username, String password) {
            this.username = username;
            this.password = password;
        }

        public String getUsername() {return username;}
        public String getPassword() {return password;}

        @Override
        public String toString() {
            return "AdminAccount{" +
                    "username='" + username + '\'' +
                    ", password=" + password +
                    '}';
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public boolean checkPin(String username, String pin) {
            if(getUsername().equals(username) && getPassword().equals(pin)){
                return true;
            }
            return false;
        }
}
