package model;

public class Packages {
    public static class UserPackage{
        private String userName;
        private String passWord;
        private String firstName;
        private String lastName;
        private String type;

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getPassWord() {
            return passWord;
        }

        public void setPassWord(String passWord) {
            this.passWord = passWord;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        @Override
        public String toString() {
            return      "userName :         " + userName
                    + "\npassWord :         ************"// + passWord
                    + "\nfirstName :        " + firstName
                    + "\nlastName :         " + lastName
                    + "\ntype :             " + type;
        }
    }

    public static class BookPackage{
        //ID?
        private String name;
        private boolean isReceived;
        private String donatorName;
        private String receiverName;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isReceived() {
            return isReceived;
        }

        public void setReceived(boolean received) {
            isReceived = received;
        }

        public String getReceiverName() {
            return receiverName;
        }

        public void setReceiverName(String receiverName) {
            this.receiverName = receiverName;
        }

        public String getDonatorName() {
            return donatorName;
        }

        public void setDonatorName(String donatorName) {
            this.donatorName = donatorName;
        }

    }

    public static class LoginPackage{
        private String userName;
        private String passWord;

        public LoginPackage(String userName, String passWord) {
            this.userName = userName;
            this.passWord = passWord;
        }

        public LoginPackage() {
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getPassWord() {
            return passWord;
        }

        public void setPassWord(String passWord) {
            this.passWord = passWord;
        }
    }

    public static class EditFieldPackage{
        private String field;

        public EditFieldPackage(String field, String newValue) {
            this.field = field;
            this.newValue = newValue;
        }

        private String newValue;

        public EditFieldPackage() {
        }

        public void setField(String field) {
            this.field = field;
        }

        public void setNewValue(String newValue) {
            this.newValue = newValue;
        }

        public String getField() {
            return field;
        }

        public String getNewValue() {
            return newValue;
        }
    }

    public static class ChangePassWordPackage{
        String oldPassWord;

        public String getOldPassWord() {
            return oldPassWord;
        }

        public void setOldPassWord(String oldPassWord) {
            this.oldPassWord = oldPassWord;
        }

        public String getNewPassWord() {
            return newPassWord;
        }

        public void setNewPassWord(String newPassWord) {
            this.newPassWord = newPassWord;
        }

        String newPassWord;
    }

    public static class DonatePackage{
        protected String donatorName;
        protected String bookName;

        public String getDonatorName() {
            return donatorName;
        }

        public void setDonatorName(String donatorName) {
            this.donatorName = donatorName;
        }

        public String getBookName() {
            return bookName;
        }

        public void setBookName(String bookName) {
            this.bookName = bookName;
        }

    }

    public static class ReceivePackage extends DonatePackage{
        String receiverName;

        public String getReceiverName() {
            return receiverName;
        }

        public void setReceiverName(String receiverName) {
            this.receiverName = receiverName;
        }
    }
}
