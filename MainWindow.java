package javanotepad;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class MainWindow extends javax.swing.JFrame {
    String contents;
    File openFile;

    public MainWindow() {
        initComponents();
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jFileChooser1 = new javax.swing.JFileChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        openMenu = new javax.swing.JMenuItem();
        saveMenu = new javax.swing.JMenuItem();
        saveAsMenu = new javax.swing.JMenuItem();
        exitMenu = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Java Notepad");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jMenu1.setText("File");

        openMenu.setText("Open");
        openMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openMenuActionPerformed(evt);
            }
        });
        jMenu1.add(openMenu);

        saveMenu.setText("Save");
        saveMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveMenuActionPerformed(evt);
            }
        });
        jMenu1.add(saveMenu);

        saveAsMenu.setText("Save As");
        saveAsMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveAsMenuActionPerformed(evt);
            }
        });
        jMenu1.add(saveAsMenu);

        exitMenu.setText("Exit");
        exitMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuActionPerformed(evt);
            }
        });
        jMenu1.add(exitMenu);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 763, Short.MAX_VALUE));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING,
                                javax.swing.GroupLayout.DEFAULT_SIZE, 448, Short.MAX_VALUE));

        pack();
    }// </editor-fold>

    private void openMenuActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        jFileChooser1.showOpenDialog(null);
        openFile = jFileChooser1.getSelectedFile();
        System.out.println(openFile.getAbsolutePath());

        try {
            RandomAccessFile f = new RandomAccessFile(openFile, "r");
            long size = openFile.length();
            contents = "";
            while (true) {
                String s = null;

                try {
                    s = f.readLine();
                } catch (IOException ex) {
                    Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
                }

                if (s == null) {
                    break;
                }
                contents += s + "\n";
            }
            jTextArea1.setText(contents);
            this.setTitle(this.getTitle() + " : " + openFile.getName());

        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void saveMenuActionPerformed(java.awt.event.ActionEvent evt) {

        if (openFile == null) {
            System.out.println("File Does Not Exist....");
            jFileChooser1.showSaveDialog(null);
            openFile = jFileChooser1.getSelectedFile();
            System.out.println("Name Of File Should Be Saved With " + openFile);
            try {
                FileWriter fw = new FileWriter(openFile);
                contents = jTextArea1.getText();
                fw.write(contents);
                this.setTitle(this.getTitle() + " : " + openFile.getName());
                fw.close();
            } catch (IOException ex) {
                Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            FileWriter fw;
            try {
                fw = new FileWriter(openFile);
                contents = jTextArea1.getText();
                fw.write(contents);
                fw.close();
            } catch (IOException ex) {
                Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void saveAsMenuActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        jFileChooser1.showSaveDialog(null);
        File tempFile = jFileChooser1.getSelectedFile();

        try {
            FileWriter fw = new FileWriter(tempFile);
            contents = jTextArea1.getText();
            fw.write(contents);
            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void exitMenuActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        contents = jTextArea1.getText();
        if (openFile == null) {
            int choice = JOptionPane.showConfirmDialog(rootPane, "Do You Want To Save Yout File!!");
            System.out.println(choice);
            if (choice == 1) {
                System.exit(0);
            } else if (choice == 0 && contents != null) {
                jFileChooser1.showSaveDialog(null);
                File tempFile = jFileChooser1.getSelectedFile();
                try {
                    FileWriter fw = new FileWriter(tempFile);
                    contents = jTextArea1.getText();
                    fw.write(contents);
                    fw.close();
                } catch (IOException ex) {
                    Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.exit(0);

            }
        }
    }

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify
    private javax.swing.JMenuItem exitMenu;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JMenuItem openMenu;
    private javax.swing.JMenuItem saveAsMenu;
    private javax.swing.JMenuItem saveMenu;
    // End of variables declaration
}
