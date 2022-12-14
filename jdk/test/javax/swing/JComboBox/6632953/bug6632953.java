/*
 * Copyright (c) 2010, 2018 Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

/* @test
 * @bug 6632953 8078614
 * @summary MetalComboBoxUI.getBaseline(JComponent, int, int) throws IAE for valid width/height
 * @author Alexander Potochkin
 */
import javax.swing.JComboBox;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.metal.MetalComboBoxUI;

public class bug6632953 {

    public static void main(String... args) throws Exception {
        SwingUtilities.invokeAndWait(new Runnable() {

            @Override
            public void run() {

                for (UIManager.LookAndFeelInfo lafInfo
                        : UIManager.getInstalledLookAndFeels()) {
                    try {
                        UIManager.setLookAndFeel(lafInfo.getClassName());
                    } catch (UnsupportedLookAndFeelException ignored) {
                        continue;
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    MetalComboBoxUI ui = new MetalComboBoxUI();
                    ui.installUI(new JComboBox());
                    ui.getBaseline(new JComboBox(), 0, 0);
                    ui.getBaseline(new JComboBox(), 1, 1);
                    ui.getBaseline(new JComboBox(), 2, 2);
                    ui.getBaseline(new JComboBox(), 3, 3);
                    ui.getBaseline(new JComboBox(), 4, 4);
                }
            }
        });
    }
}
