/*
 * Copyright by the original author or authors.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mycelium.fundsIn;

import com.mycelium.Main;
import com.mycelium.ShuffleStartController;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ArrayList;

public class addMasterPrivController {
    public Button AddBtn;
    public Button cancelBtn;
    public TextField inputMasterPrivEdit;
    public ArrayList<String> privKeyList = new ArrayList<String>(1);
    ListProperty<String> listProperty = new SimpleListProperty<>();
    public ListView privKeyListView;
    public Main.OverlayUI overlayUI;


    // Called by FXMLLoader
    public void initialize() {
        privKeyListView.itemsProperty().bind(listProperty);
    }

    public void cancel(ActionEvent event) {
        overlayUI.done();
    }

    public void addInput(ActionEvent event) {
        // add MasterPriv, could be invalid still
        // todo: check input for being valid MasterPrivKey
        String newInput = inputMasterPrivEdit.getText();
        // get rid of possible blanks like used in prompt text and classic wallet
        String betterInput = newInput.replaceAll(" ","");
        // Limit entries to List to 1, not more MasterPrivKeys needed
        if (privKeyList.size()<1) {
            if (!privKeyList.contains(betterInput)) {
                privKeyList.add(betterInput);
            }
        }
        listProperty.set(FXCollections.observableArrayList(privKeyList));

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../shuffle_start.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ShuffleStartController controller = loader.getController();
        controller.setFundsInList(privKeyList);
    }


    public void next(ActionEvent actionEvent) {


    }
}
