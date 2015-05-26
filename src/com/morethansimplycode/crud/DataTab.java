/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.morethansimplycode.crud;

import com.morethansimplycode.data.Data;
import java.awt.Color;
import java.awt.Component;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.Timer;

/**
 *
 * @author Oscar Date 21-may-2015 Time 22:15:09
 */
public class DataTab extends javax.swing.JPanel implements ActionListener {

    private final Timer tLeft = new Timer(6, (e) -> {
        if (DataTab.this.tabContainer.getX() < 0)
            left(5);

    });
    private final Timer tRight = new Timer(6, (e) -> {
        if (DataTab.this.tabContainer.getWidth() >= Math.abs(DataTab.this.tabContainer.getX()) + DataTab.this.panel.getWidth())
            left(-5);
    });

    private DataTabListener listener;
    private Data[] data;
    private DataTabItem selectedItem;
    private int selectedIndex = -1;

    /**
     * Creates new form DataTab
     */
    public DataTab() {

        initComponents();
    }

    public DataTabListener getListener() {
        return listener;
    }

    public void setItemSize(int width, int height) {

        Component[] components = tabContainer.getComponents();

        for (Component c : components) {

            if (c instanceof DataTabItem)
                ((DataTabItem) c).setDataTabItemSize(width, height);
        }
    }

    public void setListener(DataTabListener listener) {
        this.listener = listener;
    }

    public void setData(Data[] data) {

        this.data = data;
        generateTabItems();
    }

    public void setData(List<Data> data) {

        this.data = data.toArray(new Data[data.size()]);
        generateTabItems();
    }

    public Data[] getData() {
        return data;
    }

    private void generateTabItems() {

        tabContainer.setBounds(tabContainer.getBounds().x, tabContainer.getBounds().y, tabContainer.getBounds().width + 10, panel.getBounds().height);

        for (Data d : data) {

            DataTabItem item = new DataTabItem(d, 100, tabContainer.getHeight() - 10, this);
            tabContainer.add(item);
            tabContainer.setSize(tabContainer.getWidth() + item.getPreferredSize().width + 10, tabContainer.getHeight());
        }
        this.updateUI();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bLeft = new javax.swing.JButton();
        bRight = new javax.swing.JButton();
        panel = new javax.swing.JPanel();
        tabContainer = new javax.swing.JPanel();

        setMinimumSize(new java.awt.Dimension(85, 40));
        setPreferredSize(new java.awt.Dimension(200, 20));
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                resize(evt);
            }
        });
        setLayout(new java.awt.BorderLayout());

        bLeft.setText("<");
        bLeft.setMargin(new java.awt.Insets(0, 0, 0, 0));
        bLeft.setMaximumSize(new java.awt.Dimension(10, 19));
        bLeft.setMinimumSize(new java.awt.Dimension(10, 19));
        bLeft.setPreferredSize(new java.awt.Dimension(40, 10));
        bLeft.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                bLeftPressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                bLeftReleased(evt);
            }
        });
        bLeft.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bLeftActionPerformed(evt);
            }
        });
        add(bLeft, java.awt.BorderLayout.WEST);

        bRight.setText(">");
        bRight.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                bRightMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                bRightMouseReleased(evt);
            }
        });
        bRight.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bRightActionPerformed(evt);
            }
        });
        add(bRight, java.awt.BorderLayout.EAST);

        panel.setBackground(new java.awt.Color(255, 255, 255));
        panel.setLayout(null);

        tabContainer.setBackground(new java.awt.Color(255, 255, 255));
        tabContainer.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 10, 5));
        panel.add(tabContainer);
        tabContainer.setBounds(0, 0, 0, 40);

        add(panel, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void bRightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bRightActionPerformed

        if (selectedIndex < data.length - 1 && selectedIndex >= -1) {

            if (selectedItem != null) {
                selectedItem.setButtonBackground(Color.WHITE);
            }
            selectedIndex++;
            selectedItem = (DataTabItem) tabContainer.getComponent(selectedIndex);
            if (selectedItem != null) {
                selectedItem.setButtonBackground(Color.BLACK);
                selectedIndex = getDataTabIndex(selectedItem);
            }
            callListener();
        }

    }//GEN-LAST:event_bRightActionPerformed

    private void bLeftActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bLeftActionPerformed

        if (selectedIndex > 0) {

            if (selectedItem != null) {
                selectedItem.setButtonBackground(Color.WHITE);
            }
            selectedIndex--;
            selectedItem = (DataTabItem) tabContainer.getComponent(selectedIndex);
            if (selectedItem != null) {
                selectedItem.setButtonBackground(Color.BLACK);
                selectedIndex = getDataTabIndex(selectedItem);
            }
            callListener();
        } else if (selectedIndex == -1) {

            if (selectedItem != null) {
                selectedItem.setButtonBackground(Color.WHITE);
            }
            selectedIndex = data.length - 1;
            selectedItem = (DataTabItem) tabContainer.getComponent(selectedIndex);
            if (selectedItem != null) {
                selectedItem.setButtonBackground(Color.BLACK);
                selectedIndex = getDataTabIndex(selectedItem);
            }
            callListener();
        }


    }//GEN-LAST:event_bLeftActionPerformed

    private void resize(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_resize

        tabContainer.setBounds(tabContainer.getBounds().x, tabContainer.getBounds().y, tabContainer.getBounds().width, panel.getBounds().height);
        this.updateUI();
    }//GEN-LAST:event_resize

    private void bLeftPressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bLeftPressed

        tLeft.start();
    }//GEN-LAST:event_bLeftPressed

    private void bLeftReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bLeftReleased

        tLeft.stop();
    }//GEN-LAST:event_bLeftReleased

    private void bRightMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bRightMousePressed

        tRight.start();
    }//GEN-LAST:event_bRightMousePressed

    private void bRightMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bRightMouseReleased

        tRight.stop();
    }//GEN-LAST:event_bRightMouseReleased

    private void left(int movePixels) {

        Rectangle r = tabContainer.getBounds();
        r.x += movePixels;
        tabContainer.setBounds(r);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bLeft;
    private javax.swing.JButton bRight;
    private javax.swing.JPanel panel;
    private javax.swing.JPanel tabContainer;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent e) {

        if (selectedItem != null) {
            selectedItem.setButtonBackground(Color.WHITE);

        }

        selectedItem = (DataTabItem) tabContainer.getComponentAt(((DataTabItem) e.getSource()).getLocation());
        if (selectedItem != null) {
            selectedItem.setButtonBackground(Color.BLACK);
            selectedIndex = getDataTabIndex(selectedItem);
        }

        callListener();
    }

    public void callListener() {

        if (listener != null)
            listener.selectionChanged(selectedItem.value);
    }

    public int getDataTabIndex(DataTabItem item) {

        for (int i = 0; i < data.length; i++) {

            if (data[i].equals(item.value))
                return i;
        }
        return -1;
    }

}
