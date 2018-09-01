package com.szczepix.myinvest.views.content;

import com.szczepix.myinvest.models.ProfileModel;
import com.szczepix.myinvest.services.profileService.IProfileService;
import com.szczepix.myinvest.views.FXMLView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class ProfileView extends FXMLView {

    @FXML
    public Button saveButton;
    @FXML
    public TextField totalText;
    @FXML
    public TextField firstNameText;
    @FXML
    public TextField lastNameText;
    @FXML
    public TextField usernameText;
    @FXML
    public TextField emailText;
    @FXML
    public TextField phoneText;
    @FXML
    public TextField passwordText;
    @FXML
    public TextField repeatPasswordText;

    @Autowired
    private IProfileService profileService;

    private ProfileModel profileModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        profileModel = profileService.getProfile();

        enableButton(saveButton, this::onSaveButton);
        enableCompononet(firstNameText);
        enableCompononet(lastNameText);
        enableCompononet(usernameText);
        enableCompononet(emailText);
        enableCompononet(phoneText);
        enableCompononet(passwordText);
        enableCompononet(repeatPasswordText);

        firstNameText.setText(profileModel.getEntity().getFirstName());
        lastNameText.setText(profileModel.getEntity().getLastName());
        usernameText.setText(profileModel.getEntity().getUserName());
        emailText.setText(profileModel.getEntity().getEmail());
        phoneText.setText(profileModel.getEntity().getMobile());
        totalText.setText(getTotalProfiles());
    }

    private void onSaveButton(ActionEvent event) {
        try {
            if (validateForm()) {
                final ProfileModel profileModel = profileService.getProfile();
                profileModel.getEntity().setUserName(usernameText.getText());
                profileModel.getEntity().setFirstName(firstNameText.getText());
                profileModel.getEntity().setLastName(lastNameText.getText());
                profileModel.getEntity().setEmail(emailText.getText());
                profileModel.getEntity().setMobile(phoneText.getText());
                profileModel.getEntity().setPassword(passwordText.getText());
                profileService.save(profileModel);
            }
        } catch (Exception e) {
            System.out.println("Saving Error: " + e);
        }
    }

    private boolean validateForm() {
        return passwordText.getText().equals(repeatPasswordText.getText());
    }

    private String getTotalProfiles() {
        return "" + profileService.getProfiles().size();
    }
}

