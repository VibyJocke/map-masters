/**
 * Programvaruteknik KTH
 * VT 2011
 * Group: Pear Softworks
 *
 * Licensed under zlib/libpng
 *
 * Copyright (c) <2011> <Pear Softworks>
 *
 * This software is provided 'as-is', without any express or implied
 * warranty. In no event will the authors be held liable for any damages
 * arising from the use of this software.
 *
 * Permission is granted to anyone to use this software for any purpose,
 * including commercial applications, and to alter it and redistribute it
 * freely, subject to the following restrictions:

 * 1. The origin of this software must not be misrepresented; you must not
 * claim that you wrote the original software. If you use this software
 * in a product, an acknowledgment in the product documentation would be
 * appreciated but is not required.

 * 2. Altered source versions must be plainly marked as such, and must not be
 * misrepresented as being the original software.

 * 3. This notice may not be removed or altered from any source
 * distribution.
 *
 */
package project.view.listeners;

import project.controller.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Handles the anwser textfield listener.
 * @author Andreas Norberg
 */
public class AnswerActionListener extends AbstractAction {

    private Controller controller;

    /**
     * Creates a new <code>AnswerActionListener</code> instance.
     * @param controller The <code>Controller</code>.
     */
    public AnswerActionListener(Controller controller) {
        this.controller = controller;
    }

    /**
     * Events that occur when an anwser is submitted. Checks the answer.
     * @param e An ActionEvent.
     */
    public void actionPerformed(ActionEvent e) {
        JTextField tf = (JTextField) e.getSource();
        String answer = tf.getText();
        controller.checkAnswer(answer);
    }
}
