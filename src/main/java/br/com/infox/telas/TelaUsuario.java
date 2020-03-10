/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.infox.telas;

/**
 *Aula 13
 * https://www.youtube.com/watch?v=z_KK-amMTDo&list=PLbEOwbQR9lqxsTusvu8wfkUECrmcV81MU&index=15
 * @author w7
 */
import java.sql.*;
import br.com.infox.dal.ModuloConexao;
import javax.swing.Icon;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JLabel;



public class TelaUsuario extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public TelaUsuario() {
        initComponents();
        conexao = ModuloConexao.conector();
        
    }//constructor
    
    private void consultar(){
        String sql = "select * from tbusuarios where iduser=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtUsuId.getText());
            rs = pst.executeQuery();
            if (rs.next()) {
                txtUsuNome.setText(rs.getString(2));
                txtUsuFone.setText(rs.getString(3));
                txtUsuLogin.setText(rs.getString(4));
                txtUsuSenha.setText(rs.getString(5));
                cboUsuPerfil.setSelectedItem(rs.getString(6));
                
            }//if 
            else {
                JOptionPane.showMessageDialog(null, "Usuario não Encontrado/Cadastrado.", "User não encontrado", JOptionPane.WARNING_MESSAGE);
                limparCamposExcetoID();
            }//else
            
        }//try 
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e, "TelaUsuario.consultar() -> ERRO", JOptionPane.ERROR_MESSAGE);
        }//catch
        
    }//consultar
        
    private void adicionar(){
        String sql = "insert into  tbusuarios(iduser, usuario, fone, login, senha, perfil)  values(?, ?, ?, ?, ?, ?)";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtUsuId.getText());
            pst.setString(2, txtUsuNome.getText());
            pst.setString(3, txtUsuFone.getText());
            pst.setString(4, txtUsuLogin.getText());
            pst.setString(5, txtUsuSenha.getText());
            pst.setString(6, cboUsuPerfil.getSelectedItem().toString());
            //atualiza usuaros com dados do form
            if ((txtUsuId.getText().isEmpty())||(txtUsuNome.getText().isEmpty())||(txtUsuLogin.getText().isEmpty())||(txtUsuSenha.getText().isEmpty())||(cboUsuPerfil.getSelectedItem() == null)) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios!", "Preencha os campos obrigatórios", JOptionPane.WARNING_MESSAGE);
            } //if
            else {
                //confirmar insercao tabela
                int adicionado = pst.executeUpdate();
                if (adicionado > 0) {
                    //NAOFUNCIONOU//ImageIcon icon = createImageIcon("icones/salvosucesso.png");
                    //NAOFUNCIONOU//ImageIcon icon = createImageIcon("Z:/java/javaMySQL03_infoX/src/main/java/br/com/infox/icones/salvosucesso.png");
                    //NAOFUNCIONOU//JLabel lblIcon = new JLabel();
                    //NAOFUNCIONOU//lblIcon.setIcon(new javax.swing.ImageIcon("Z:\\java\\javaMySQL03_infoX\\src\\main\\java\\br\\com\\infox\\icones\\salvosucesso.png"));
                    JOptionPane.showMessageDialog(null, "Usuario: (" + txtUsuNome.getText() + ") foi adicionado com sucesso!", "Usuario adicionado com sucesso", JOptionPane.INFORMATION_MESSAGE);
                    limparTodosCampos();
                }//if
            }//else
            
        }//try
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e, "TelaUsuario.adicionar() -> ERRO", JOptionPane.ERROR_MESSAGE);
        }//catch
    }//adicionar
    
    private void alterar(){
        String sql="update tbusuarios set usuario=?, fone=?, login=?, senha=?, perfil=? where iduser=?";
        
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtUsuNome.getText());
            pst.setString(2, txtUsuFone.getText());
            pst.setString(3, txtUsuLogin.getText());
            pst.setString(4, txtUsuSenha.getText());
            pst.setString(5, cboUsuPerfil.getSelectedItem().toString());
            pst.setString(6, txtUsuId.getText());
                    
            if ((txtUsuId.getText().isEmpty()) || (txtUsuNome.getText().isEmpty()) || (txtUsuLogin.getText().isEmpty()) || (txtUsuSenha.getText().isEmpty()) || (cboUsuPerfil.getSelectedItem() == null)) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios!", "Preencha os campos obrigatórios", JOptionPane.WARNING_MESSAGE);
            } //if
            else {
                //confirmar alteracao tabela
                int adicionado = pst.executeUpdate();
                if (adicionado > 0) {
                    //NAOFUNCIONOU//ImageIcon icon = createImageIcon("icones/salvosucesso.png");
                    //NAOFUNCIONOU//ImageIcon icon = createImageIcon("Z:/java/javaMySQL03_infoX/src/main/java/br/com/infox/icones/salvosucesso.png");
                    //NAOFUNCIONOU//JLabel lblIcon = new JLabel();
                    //NAOFUNCIONOU//lblIcon.setIcon(new javax.swing.ImageIcon("Z:\\java\\javaMySQL03_infoX\\src\\main\\java\\br\\com\\infox\\icones\\salvosucesso.png"));
                    JOptionPane.showMessageDialog(null, "Dados do Usuario: (" + txtUsuNome.getText() + ") foram ALTERADOS com sucesso!", "Dados do usuario alterados com sucesso", JOptionPane.INFORMATION_MESSAGE);
                    limparTodosCampos();
                }//if
            }//else
        }//try 
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e,"TelaUsuario.alterar() -> ERRO", JOptionPane.ERROR_MESSAGE);
        }//catch
        
    }//alterar
    
    //metodo remover
    private void remover(){
        //confirmar remocao
        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover esse usuário?","Atenção - Deseja Remover?", JOptionPane.YES_NO_OPTION);
        
        if (confirma==JOptionPane.YES_OPTION) {
            String sql = "delete from tbusuarios where iduser=?";
            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtUsuId.getText());
                int apagado = pst.executeUpdate();
                if (apagado>0){
                    JOptionPane.showMessageDialog(null, "Usuario: (" + txtUsuNome.getText() + ") foi REMOVIDO com sucesso!", "Usuario REMOVIDO com sucesso", JOptionPane.INFORMATION_MESSAGE);
                    limparTodosCampos();
                }//if
            }//try 
            catch (Exception e) {
                JOptionPane.showMessageDialog(null, e,"TelaUsuario.remover() -> ERRO", JOptionPane.ERROR_MESSAGE);
            }//catch
            
        }//if 
        else {
            System.out.println("TelaUsuario.remover() - desistiu de deletar o usuario.");
        }//else
        
    }//remover
    
    private void limparTodosCampos(){
        txtUsuId.setText(null);
        limparCamposExcetoID();
    }//limparTodosCampos
    
    private void limparCamposExcetoID(){
        txtUsuNome.setText(null);
        txtUsuFone.setText(null);
        txtUsuLogin.setText(null);
        txtUsuSenha.setText(null);
       // cboUsuPerfil.setSelectedItem(null);
        
    }//limparCamposExcetoID
    
    
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblID = new javax.swing.JLabel();
        lblNome = new javax.swing.JLabel();
        lblLogin = new javax.swing.JLabel();
        lblSenha = new javax.swing.JLabel();
        lblPerfil = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtUsuId = new javax.swing.JTextField();
        txtUsuNome = new javax.swing.JTextField();
        txtUsuFone = new javax.swing.JTextField();
        txtUsuLogin = new javax.swing.JTextField();
        txtUsuSenha = new javax.swing.JTextField();
        cboUsuPerfil = new javax.swing.JComboBox<>();
        btnUsuCreate = new javax.swing.JButton();
        btnUsuRead = new javax.swing.JButton();
        btnUsuUpdate = new javax.swing.JButton();
        btnUsuDelete = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Usuarios");
        setMinimumSize(new java.awt.Dimension(300, 250));
        setPreferredSize(new java.awt.Dimension(620, 540));

        lblID.setText("ID *");

        lblNome.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblNome.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblNome.setText("Nome *");

        lblLogin.setText("Login *");

        lblSenha.setText("Senha *");

        lblPerfil.setText("Perfil *");

        jLabel6.setText("Fone");

        cboUsuPerfil.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "user", "admin" }));

        btnUsuCreate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/create.png"))); // NOI18N
        btnUsuCreate.setToolTipText("Adicionar");
        btnUsuCreate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUsuCreate.setPreferredSize(new java.awt.Dimension(80, 80));
        btnUsuCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuCreateActionPerformed(evt);
            }
        });

        btnUsuRead.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/read.png"))); // NOI18N
        btnUsuRead.setToolTipText("Consultar");
        btnUsuRead.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUsuRead.setPreferredSize(new java.awt.Dimension(80, 80));
        btnUsuRead.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuReadActionPerformed(evt);
            }
        });

        btnUsuUpdate.setIcon(new javax.swing.ImageIcon("Z:\\java\\javaMySQL03_infoX\\src\\main\\java\\br\\com\\infox\\icones\\update.png")); // NOI18N
        btnUsuUpdate.setToolTipText("Alterar");
        btnUsuUpdate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUsuUpdate.setPreferredSize(new java.awt.Dimension(80, 80));
        btnUsuUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuUpdateActionPerformed(evt);
            }
        });

        btnUsuDelete.setIcon(new javax.swing.ImageIcon("Z:\\java\\javaMySQL03_infoX\\src\\main\\java\\br\\com\\infox\\icones\\delete.png")); // NOI18N
        btnUsuDelete.setToolTipText("Remover");
        btnUsuDelete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUsuDelete.setPreferredSize(new java.awt.Dimension(80, 80));
        btnUsuDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuDeleteActionPerformed(evt);
            }
        });

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("* - campos obrigatorios");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblID)
                            .addComponent(lblNome)
                            .addComponent(jLabel6)
                            .addComponent(lblLogin))
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtUsuFone, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                    .addComponent(txtUsuLogin, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtUsuId, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(lblSenha)
                                        .addGap(27, 27, 27)
                                        .addComponent(txtUsuSenha))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(7, 7, 7))))
                            .addComponent(txtUsuNome))
                        .addGap(106, 106, 106))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblPerfil)
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnUsuCreate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)
                                .addComponent(btnUsuRead, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(46, 46, 46)
                                .addComponent(btnUsuUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(45, 45, 45)
                                .addComponent(btnUsuDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(cboUsuPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(97, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblID)
                    .addComponent(txtUsuId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNome)
                    .addComponent(txtUsuNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtUsuFone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblLogin)
                    .addComponent(txtUsuLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSenha)
                    .addComponent(txtUsuSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPerfil)
                    .addComponent(cboUsuPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(93, 93, 93)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUsuCreate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUsuRead, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUsuUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUsuDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(168, 168, 168))
        );

        setBounds(0, 0, 620, 540);
    }// </editor-fold>//GEN-END:initComponents

    private void btnUsuReadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuReadActionPerformed
        // Chama metodo Consultar
        consultar();
    }//GEN-LAST:event_btnUsuReadActionPerformed

    private void btnUsuCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuCreateActionPerformed
        // Chama metodo adicionar
        adicionar();
    }//GEN-LAST:event_btnUsuCreateActionPerformed

    private void btnUsuUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuUpdateActionPerformed
        // chama o metodo alterar
        alterar();
    }//GEN-LAST:event_btnUsuUpdateActionPerformed

    private void btnUsuDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuDeleteActionPerformed
        // chama o metodo remover
        remover();
    }//GEN-LAST:event_btnUsuDeleteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnUsuCreate;
    private javax.swing.JButton btnUsuDelete;
    private javax.swing.JButton btnUsuRead;
    private javax.swing.JButton btnUsuUpdate;
    private javax.swing.JComboBox<String> cboUsuPerfil;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel lblID;
    private javax.swing.JLabel lblLogin;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblPerfil;
    private javax.swing.JLabel lblSenha;
    private javax.swing.JTextField txtUsuFone;
    private javax.swing.JTextField txtUsuId;
    private javax.swing.JTextField txtUsuLogin;
    private javax.swing.JTextField txtUsuNome;
    private javax.swing.JTextField txtUsuSenha;
    // End of variables declaration//GEN-END:variables

/** Returns an ImageIcon, or null if the path was invalid. */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = TelaUsuario.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }//ImageIcon
}
