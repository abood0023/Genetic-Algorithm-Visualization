
package geneticalgorithm;

import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class Main extends javax.swing.JFrame {
    private File imageFile;
    private BufferedImage img;
    private Image dimg;
    private ImageIcon imageIcon;
    private String file_path = null;
    private GeneticAlgorithm g ;
    
    
    public Main() {
        initComponents();
        setLocationRelativeTo(null);
        Specific_ratios_panel.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ratio_group = new javax.swing.ButtonGroup();
        main_panel = new javax.swing.JPanel();
        choose_file = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        default_ratio = new javax.swing.JRadioButton();
        specific_ratios = new javax.swing.JRadioButton();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        Specific_ratios_panel = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        pop_number = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        max_iterations = new javax.swing.JTextField();
        crossover_ratio = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        mutation_ratio = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.CardLayout());

        main_panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        choose_file.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        choose_file.setForeground(new java.awt.Color(0, 0, 255));
        choose_file.setText("Choose text file");
        choose_file.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        choose_file.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        choose_file.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                choose_fileActionPerformed(evt);
            }
        });
        main_panel.add(choose_file, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 252, 150, 40));

        jLabel2.setSize(80, 90);
        imageFile = new File("D:\\My university\\8th term\\Artificial Intelligence\\project\\GeneticAlgorithm\\src\\geneticalgorithm\\ImamUlogoMeta.png");
        img = null;
        try {
            img = ImageIO.read(imageFile);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        dimg = img.getScaledInstance(jLabel2.getWidth(), jLabel2.getHeight(),
            Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(dimg);
        jLabel2.setIcon(imageIcon);
        main_panel.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, 80, 90));

        jLabel3.setSize(290, 90);
        imageFile = new File("D:\\My university\\8th term\\Artificial Intelligence\\project\\GeneticAlgorithm\\src\\geneticalgorithm\\شعار الكلية.jpg");
        img = null;
        try {
            img = ImageIO.read(imageFile);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        dimg = img.getScaledInstance(jLabel3.getWidth(), jLabel3.getHeight(),
            Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(dimg);
        jLabel3.setIcon(imageIcon);
        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        main_panel.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 20, 290, 90));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(254, 254, 254));
        jLabel4.setText("Genetic Algorithm");
        jLabel4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel4.setDebugGraphicsOptions(javax.swing.DebugGraphics.FLASH_OPTION);
        main_panel.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 110, 440, 70));

        default_ratio.setBackground(java.awt.SystemColor.controlHighlight);
        ratio_group.add(default_ratio);
        default_ratio.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        default_ratio.setForeground(new java.awt.Color(0, 0, 255));
        default_ratio.setText("Default ratios");
        default_ratio.setToolTipText("");
        default_ratio.setBorderPainted(true);
        main_panel.add(default_ratio, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 220, 160, 40));

        specific_ratios.setBackground(java.awt.SystemColor.controlHighlight);
        ratio_group.add(specific_ratios);
        specific_ratios.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        specific_ratios.setForeground(new java.awt.Color(0, 0, 255));
        specific_ratios.setText("Specific ratios");
        specific_ratios.setBorderPainted(true);
        main_panel.add(specific_ratios, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 270, 160, 40));

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 153, 0));
        jButton1.setText("Start algorithm");
        jButton1.setToolTipText("");
        jButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        main_panel.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 370, 220, 60));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/geneticalgorithm/081716_ti_ExAC_main_free.jpg"))); // NOI18N
        main_panel.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        getContentPane().add(main_panel, "card2");

        Specific_ratios_panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel5.setText("Population number");
        Specific_ratios_panel.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 55, -1, -1));

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jButton2.setForeground(new java.awt.Color(204, 0, 0));
        jButton2.setText("Back");
        jButton2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        Specific_ratios_panel.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 403, 130, 40));

        jButton3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jButton3.setForeground(new java.awt.Color(0, 153, 51));
        jButton3.setText("Ok");
        jButton3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        Specific_ratios_panel.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 400, 130, 40));

        pop_number.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        pop_number.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pop_numberActionPerformed(evt);
            }
        });
        Specific_ratios_panel.add(pop_number, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 50, 220, 40));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel6.setText("Maximum iterations");
        Specific_ratios_panel.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 115, -1, -1));

        max_iterations.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        max_iterations.setPreferredSize(new java.awt.Dimension(6, 20));
        Specific_ratios_panel.add(max_iterations, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 110, 220, 40));

        crossover_ratio.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        Specific_ratios_panel.add(crossover_ratio, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 170, 220, 40));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel7.setText("Crossover ratio");
        jLabel7.setToolTipText("");
        Specific_ratios_panel.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 170, 170, 40));

        jLabel8.setText("Enter value between 1 and 100");
        Specific_ratios_panel.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 210, -1, 20));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel9.setText("%");
        Specific_ratios_panel.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 170, 30, 40));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel10.setText("Mutation ratio");
        jLabel10.setToolTipText("");
        Specific_ratios_panel.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 240, 150, 40));

        mutation_ratio.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        Specific_ratios_panel.add(mutation_ratio, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 240, 220, 40));

        jLabel11.setText("Enter value between 1 and 100");
        Specific_ratios_panel.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 280, -1, 20));

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel12.setText("%");
        Specific_ratios_panel.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 240, 30, 40));

        getContentPane().add(Specific_ratios_panel, "card3");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void choose_fileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_choose_fileActionPerformed
        // TODO add your handling code here:
        JFileChooser file = new JFileChooser();
        file.setDialogType(JFileChooser.FILES_AND_DIRECTORIES);
        file.showOpenDialog(null);
        File f = file.getSelectedFile();
        String filename = "";
        if (f != null){
            file_path = f.getAbsolutePath();
            file_path = file_path.replace("\\","\\\\");
            try {
                g.readFile(file_path);
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_choose_fileActionPerformed
    
    void errorMessage(String message) {
        JLabel label = new JLabel(message);
        label.setFont(new Font("Arial", Font.BOLD, 16));
        JOptionPane.showMessageDialog(null, label, "Error", JOptionPane.ERROR_MESSAGE);
    }
//                try{
//                    g.readFile("D:\\My university\\8th term\\ذكاء\\project\\Instance3.txt");
//                }catch(Exception e){    
//                }
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(file_path != null){
            if(default_ratio.isSelected()){
                Visualization v = new Visualization(g.orderedTasksByLevel());
                v.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
                this.setVisible(false);
                v.setVisible(true);
                v.setSize(1000, 600);
                v.setLocationRelativeTo(null);
                v.drawTaskGraph();
                v.drawEdges();
                try {
                    int n = g.geneticAlgorithm(250,2000,0.03,0.7);
                    v.drawBestStringSearch(g.best_overall,n);
                    System.out.println(n);
                   
                } catch (IOException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else if(specific_ratios.isSelected()){
                main_panel.setVisible(false);
                Specific_ratios_panel.setVisible(true);
            }else{
                errorMessage("Please choose ratios");
            }
        }else{
            errorMessage("Choose file please.");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void pop_numberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pop_numberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pop_numberActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        Specific_ratios_panel.setVisible(false);
        main_panel.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        int pop, iter;
        double cross, mutation;
        try {
            pop = Integer.parseInt(pop_number.getText());
            if(pop%2 != 0){
                errorMessage("Population number must be even number");
                return;
            }
        } catch (Exception e) {
            errorMessage("Enter valid value for the Population number");
            return;
        }
        try {
            iter = Integer.parseInt(max_iterations.getText());
        } catch (Exception e) {
            errorMessage("Enter valid value for the Maximum iterations");
            return;
        }
        try {
            cross = Double.parseDouble(crossover_ratio.getText());
            if(cross > 100.0 || cross < 1.0){
                errorMessage("Enter value between 1 and 100 for the Crossover ratio");
                return;
            }
        } catch (Exception e) {
            errorMessage("Enter valid value for the Crossover ratio");
            return;
        }
        try {
            mutation = Double.parseDouble(mutation_ratio.getText());
            if(mutation > 100.0 || mutation < 1.0){
                errorMessage("Enter value between 1 and 100 for the Mutation ratio");
                return;
            }
        } catch (Exception e) {
            errorMessage("Enter valid value for the Mutation ratio");
            return;
        }
        Visualization v = new Visualization(g.orderedTasksByLevel());
        v.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(false);
        v.setVisible(true);
        v.setSize(1000, 600);
        v.setLocationRelativeTo(null);
        v.drawTaskGraph();
        v.drawEdges();
        int n = 0;
        try {
            n = g.geneticAlgorithm(pop,iter,mutation/100,cross/100);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        v.drawBestStringSearch(g.best_overall, n);
        System.out.println(n);
    }//GEN-LAST:event_jButton3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch(Exception e){
            
        }
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Specific_ratios_panel;
    private javax.swing.JButton choose_file;
    private javax.swing.JTextField crossover_ratio;
    private javax.swing.JRadioButton default_ratio;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel main_panel;
    private javax.swing.JTextField max_iterations;
    private javax.swing.JTextField mutation_ratio;
    private javax.swing.JTextField pop_number;
    private javax.swing.ButtonGroup ratio_group;
    private javax.swing.JRadioButton specific_ratios;
    // End of variables declaration//GEN-END:variables
}
