package com.twu.biblioteca;

import com.twu.biblioteca.lib.InputReaderWrapper;
import com.twu.biblioteca.lib.OutputWriterWrapper;
import com.twu.biblioteca.models.CurrentUser;
import com.twu.biblioteca.models.User;
import com.twu.biblioteca.services.UsersFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SessionHandler {

    private List<User> users;
    private InputReaderWrapper inputReaderWrapper;
    private OutputWriterWrapper outputWriterWrapper;

    public SessionHandler(
            List<User> users,
            InputReaderWrapper inputReaderWrapper,
            OutputWriterWrapper outputWriterWrapper
    ) {
        this.users = users;
        this.inputReaderWrapper = inputReaderWrapper;
        this.outputWriterWrapper = outputWriterWrapper;
    }

    public boolean login() {
        boolean isCredentialValid = false;

        try {
            Map<String, String> credential = this.promptCredential();
            String username = credential.get("username");
            String password =  credential.get("password");
            isCredentialValid = this.validateCredentials(username, password);
        } catch (IOException e) {
            System.err.println("An error has occurred");
        }

        if (!isCredentialValid) System.err.println("Invalid username or password");

        return isCredentialValid;
    }

    private boolean validateCredentials(String username, String password) {
        User user = User.findByLibraryId(username);
        if (user != null && user.password == password) {
            CurrentUser.set(user);
            return true;
        }

        return false;
    }

    private Map<String, String> promptCredential() throws IOException {
        this.outputWriterWrapper.writeString("Please input your username: ");
        String username = this.inputReaderWrapper.readString();

        this.outputWriterWrapper.writeString("Please input your password: ");
        String password = this.inputReaderWrapper.readString();

        Map<String, String> credential = new HashMap<>();
        credential.put("username", username);
        credential.put("password", password);

        return credential;
    }
}
